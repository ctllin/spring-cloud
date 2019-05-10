package com.ctl.ftp;

/**
 * <p>Title: s</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-05-10 17:59
 */

import java.text.SimpleDateFormat;
import java.util.*;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp.LsEntry;

public class FtpsFileList {
    private static final Logger logger = LoggerFactory.getLogger(FtpsFileList.class);
    static  ChannelSftp sftp = null;
    static  Channel channel = null;
    static  Session sshSession = null;
    static final String host = "192.168.3.117";
    static final String username = "wise";
    static final String password = "wise";
    static final Integer port = 22;
    static final String warPathName = "/home/wise/tomcat_8010/webapps/rtmart-base-acl-impl.war";
    static final String warPath = "/home/wise/tomcat_8010/webapps/";
    static final String warName = "rtmart-base-acl-impl";

    public static void main(String[] args) {
        listFileNames("192.168.3.117", 22, "wise", "wise", "/home/wise/tomcat_8010/webapps");
//        ChannelSftp sftp = getSftp();
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String time = sdf.format(date);
//        try {
//            sftp.rename(warPathName,warPath+warName+"-"+time+".war.back");
//        } catch (SftpException e) {
//           logger.error("重命名失败",e);
//        }
//        close();
    }

    public static  ChannelSftp getSftp() {
        JSch jsch = new JSch();
        try {
            jsch.getSession(username, host, port);
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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("链接错误", e);
            return null;
        }
        return sftp;
    }

    public static void close() {
        try {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        } catch (Exception e) {
            logger.error("关闭失败", e);
        }
    }

    private static List<String> listFileNames(String host, int port, String username, final String password, String dir) {
        List<String> list = new ArrayList<String>();
        ChannelSftp sftp = null;
        Channel channel = null;
        Session sshSession = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
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
            Vector<?> vector = sftp.ls(dir);
            for (Object item : vector) {
                LsEntry entry = (LsEntry) item;
                logger.info(entry.getFilename());
            }

        } catch (Exception e) {
            logger.error("链接错误", e);
        } finally {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(sshSession);
        }
        return list;
    }

    private static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
