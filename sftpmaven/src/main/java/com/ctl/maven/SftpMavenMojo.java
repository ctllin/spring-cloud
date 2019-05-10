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

import com.jcraft.jsch.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * @phase process-sources
 */
@Mojo(name = "sftp")
class SftpMavenMojo extends AbstractMojo {
    /**
     * Location of the file.
     * //sftp host
     * //sftp username
     * //sftp password
     * //sftp port
     * //war路径 /home/wise/tomcat_8010/webapps/
     * //war 名称 rtmart-base-acl-impl.war
     * //war路径 /home/wise/tomcat_8010/warback/
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private static final Logger logger = LoggerFactory.getLogger(SftpMavenMojo.class);
    @Parameter
    private String host;
    @Parameter
    private String username;
    @Parameter
    private String password;
    @Parameter
    private Integer port;
    @Parameter
    private String warPath;
    @Parameter
    private String warName;
    @Parameter
    private String barkWarPath;

    public void execute() {
        ChannelSftp sftp = null;
        Channel channel = null;
        Session sshSession = null;
        JSch jsch = new JSch();
        try {
            logger.info("username={},host={},port={}", username, host, port);
            //sshSession=jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.debug("Session connected!");
            channel = sshSession.openChannel("sftp");
            channel.connect();
            logger.debug("Channel connected!");
            sftp = (ChannelSftp) channel;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String time = sdf.format(date);
            String bacPath = null;
            if (barkWarPath != null && !"".equals(barkWarPath)) {
                try {
                    sftp.mkdir(barkWarPath);
                } catch (SftpException e) {
                    
                }
                bacPath = barkWarPath + warName + "." + time + ".bak";
            } else {
                bacPath = warPath + warName + "." + time + ".bak";
            }
            sftp.rename(warPath + warName, bacPath);
            logger.info("war包原路径{},war包备份路径{}", warName + warPath, bacPath);
        } catch (Exception e) {
            logger.error("链接错误或包路径错误", e);
        } finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        }
    }

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
}
