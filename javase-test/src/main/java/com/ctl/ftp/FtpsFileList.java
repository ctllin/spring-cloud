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

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class FtpsFileList {
    private static final Logger logger = LoggerFactory.getLogger(FtpsFileList.class);
    static  ChannelSftp sftp = null;
    static  Channel channel = null;
    static  Channel channelExec = null;
    static  Session sshSession = null;
    static  Session sessionExec = null;
    static final String host = "192.168.3.117";
    static final String username = "wise";
    static final String password = "wise";
    static final Integer port = 22;
    static final String warPathName = "/home/wise/tomcat_8010/webapps/wise-base-acl-impl.war";
    static final String warPath = "/home/wise/tomcat_8010/webapps/";
    static final String warName = "wise-base-acl-impl";

    public static void main(String[] args) {
        listFileNames("192.168.3.117", 22, "wise", "wise", "/home/wise/tomcat_8010/webapps");
        executeCommand("cp /home/wise/tomcat_8010/webapps/wise-base-acl-impl.war /home/wise/tomcat_8010/webapps/wise-base-acl-impl.war.ctl");

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

    /**
     * 获取连接
     * @return
     */
    private static ChannelExec getChannelExec() {
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
    public static boolean executeCommand(String command) {
        boolean flag = false;
        ChannelExec channelExec = getChannelExec();
        if (channelExec == null) {
            return flag;
        }
        try {
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            channelExec.setCommand(command);

            InputStream in = channelExec.getInputStream();
            channelExec.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
            closeChannel(channelExec);

            flag = true;
        } catch (Exception e) {
            System.out.println(e);
            flag = false;
        }finally {
            if(channelExec!=null){
                closeChannel(channelExec);
                closeSession(sessionExec);
            }
        }
        return flag;
    }


}
