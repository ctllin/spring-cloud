package com.ctl.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Goal which touches a timestamp file.
 *
 * @phase process-sources
 */
@Mojo(name = "executeCommandSftp")
class SftpExecCommandMavenMojo extends AbstractMojo {
    static  Channel channelExec = null;
    static  Session sessionExec = null;
    /**
     * //sftp host
     * //sftp username
     * //sftp password
     * //sftp port
     * //war路径 /home/wise/tomcat_8010/webapps/
     * //war 名称 rtmart-base-acl-impl.war
     * //war路径 /home/wise/tomcat_8010/warback/
     */
    private static final Logger logger = LoggerFactory.getLogger(SftpExecCommandMavenMojo.class);
    /**
     * @parameter expression="${sftp.host}"
     * @required
     */
    @Parameter
    private String host; //= "192.168.3.117";
    @Parameter
    private String username; //= "root";
    @Parameter
    private String password; //= "123456";
    @Parameter
    private Integer port;//= 12321;
    @Parameter
    private String warPath; //= "/home/wise/tomcat_8010/webapps/";
    @Parameter
    private String warName; //= "rtmart-base-acl-impl.war";
    @Parameter
    private String barkWarPath;
    @Parameter
    private String ftpType; //= "ftp";
    @Parameter
    private String execCommandStr;

    private void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
    /**
     * 获取连接
     * @return
     */
    private  ChannelExec getChannelExec() {
        try {
            if (channelExec != null && channelExec.isConnected()) {
                return (ChannelExec) channelExec;
            }
            JSch jSch = new JSch();
            if (sessionExec == null || !sessionExec.isConnected()) {
                sessionExec = jSch.getSession(username,host , port);
                sessionExec.setPassword(password);
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                sessionExec.setConfig(config);
                sessionExec.setTimeout(3000);
                sessionExec.connect();
            }
            channelExec = sessionExec.openChannel("exec");
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            if (sessionExec != null) {
                sessionExec.disconnect();
                sessionExec = null;
            }
            channelExec = null;
        }
        return channelExec == null ?null : (ChannelExec) channelExec;
    }
    /**
     * 执行服务器端命令
     */
    public  boolean executeCommand(String command) {
        boolean flag = false;
        ChannelExec channelExec = getChannelExec();
        if (channelExec == null) {
            return flag;
        }
        try {
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(date);
            String bacPath = null;
            if (barkWarPath != null && !"".equals(barkWarPath)) {
                bacPath = barkWarPath + warName + "." + time + ".bak";
            } else {
                bacPath = warPath + warName + "." + time + ".bak";
            }
            if(command==null||"".equals(command.trim())){
                command = "cp " + warPath + warName + " " + bacPath;
                execCommandStr=command;
                logger.info("默认executeCommand={}",command);
            }
            channelExec.setCommand(command);
            InputStream in = channelExec.getInputStream();
            channelExec.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
            reader.close();
            closeChannel(channelExec);
            flag = true;
        } catch (Exception e) {
            logger.error("执行命令["+command+"]失败",e);
            flag = false;
        }finally {
            if(channelExec!=null){
                closeChannel(channelExec);
                closeSession(sessionExec);
            }
        }
        return flag;
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        boolean executeCommand = executeCommand(execCommandStr);
        if(executeCommand){
            logger.info("执行命令[{}]成功",execCommandStr);
        }else {
            logger.info("执行命令[{}]失败",execCommandStr);
        }
    }
}
