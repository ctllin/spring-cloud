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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Goal which touches a timestamp file.
 *
 * @phase process-sources
 */
@Mojo(name = "ftp")
class FtpMavenMojo extends AbstractMojo {
    /**
     * Location of the file.
     * //sftp host
     * //sftp username
     * //sftp password
     * //sftp port
     * //war路径 /home/wise/tomcat_8010/webapps/
     * //war 名称 rtmart-base-acl-impl.war
     * //war路径 /home/wise/tomcat_8010/warback/
     *
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private static final Logger logger = LoggerFactory.getLogger(FtpMavenMojo.class);
    /**
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

    public void execute() {
        logger.info("ftpType={}", ftpType);
        if (ftpType != null && "sftp".equals(ftpType)) {
            ChannelSftp sftp = null;
            Channel channel = null;
            Session sshSession = null;
            JSch jsch = new JSch();
            try {
                logger.info("username={},host={},port={},password={}", username, host, port, password);
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
                String bacPath;
                if (barkWarPath != null && !"".equals(barkWarPath)) {
                    try {
                        sftp.mkdir(barkWarPath);
                    } catch (SftpException e) {
                        logger.error("备份失败", e);
                        return;
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
        } else {
            FTPClient fClient = null;
            try {
                fClient = new FTPClient();
                fClient.connect(host, port);
                fClient.login(username, password);
                fClient.setDataTimeout(60000);       //设置传输超时时间为60秒
                fClient.setConnectTimeout(60000);       //连接超时为6
                int reply = fClient.getReplyCode();
                if (FTPReply.isPositiveCompletion(reply)) {// 登陆到ftp服务器
                    fClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    fClient.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
                    logger.info("Client loin success：" + fClient.getStatus());
                } else {
                    fClient.disconnect();
                    logger.info("Client loin fail：");
                    return;
                }
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String time = sdf.format(date);
                String bacPath = null;
                if (barkWarPath != null && !"".equals(barkWarPath)) {
                    fClient.mkd(barkWarPath);
                    bacPath = barkWarPath + warName + "." + time + ".bak";
                } else {
                    bacPath = warPath + warName + "." + time + ".bak";
                }
                boolean renameResult = fClient.rename(warPath + warName, bacPath);
                if (renameResult) {
                    logger.info("war包原路径{},war包备份路径{}", warName + warPath, bacPath);
                } else {
                    logger.info("备份失败,检查待备份文件是否存在");
                }
            } catch (Exception e) {
                fClient = null;
                logger.error("Client login fail or execute back fail:", e);
            } finally {
                if (fClient != null) {
                    try {
                        fClient.logout();
                        fClient.disconnect();
                    } catch (IOException e) {
                        logger.error("ftp client exit fail", e);
                    }

                }
            }
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

    public static void main(String[] args) {
        new FtpMavenMojo().execute();
    }
}
