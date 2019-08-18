package com.ctl.maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * <p>Title: SSHMojo</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-05-23 16:36
 */
@Mojo(name = "ssh")
public class SSHMojo extends AbstractMojo {
    @Parameter
    private String host; //= "192.168.3.117";
    @Parameter
    private String username; //= "wise";
    @Parameter
    private String password; //= "wise";
    @Parameter
    private String cmd; //= "ls";
    @Parameter
    private Integer port;//= 12321;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
//        host = "192.168.3.117";
//        username = "wise";
//        password = "wise";
//        cmd = "cd /home/soft && tree";
//        port = 22;
        Connection connection = port == null ? login(host, username, password) : login(host, username, password, port);
        if (connection == null) {
            logger.info("获取链接失败,退出");
            return;
        }
        String execute = execute(connection, cmd);
        logger.info("执行结果---------------------------->{}", execute);
    }

    public static void main(String[] args) throws MojoFailureException, MojoExecutionException {
        new SSHMojo().execute();
    }

    private static final Logger logger = LoggerFactory.getLogger(SSHMojo.class);
    private static String DEFAULTCHART = "UTF-8";

    /**
     * 登录主机
     *
     * @return 登录成功返回连接，否则返回空
     */
    public static Connection login(String ip,String userName,String userPwd) {
       return login(ip,userName,userPwd,22);
    }
    public static Connection login(String ip,String userName,String userPwd,int port) {
        boolean flg = false;
        Connection conn = null;
        try {
            String ipPattern = "(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

            if (ip != null && ip.matches(ipPattern)) {

            } else {
                logger.info("ip={}地址错误", ip);
                return null;
            }
            if (userName == null || "".equals(userName.trim()) || userPwd == null || "".equals(userPwd.trim())) {
                logger.info("用户密码不能为空");
                return null;
            }
            if (port < 1 && port > 65535) {
                logger.info("端口={}错误", port);
                return null;
            }
            logger.info("host={},port={},username={},password={}", ip, port, userName, userPwd);
            conn = new Connection(ip, port);
            conn.connect();//连接
            flg = conn.authenticateWithPassword(userName, userPwd);//认证
            if (flg) {
                logger.info("=========登录成功conn={}=========", conn);
                return conn;
            }
        } catch (IOException e) {
            logger.error("=========登录失败=========", e);
        }
        return conn;
    }
    /**
     * 远程执行shll脚本或者命令
     * @param cmd 即将执行的命令
     * @return 命令执行完后返回的结果值
     */
    public static String execute(Connection conn, String cmd) {
        if (cmd == null || "".equals(cmd.trim())) {
            logger.info("cmd为空退出");
            return null;
        }
        String result = "";
        try {
            if (conn != null) {
                Session session = conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if (result == null || "".equals(result.trim())) {
                    logger.info("得到标准输出为空,链接conn:" + conn + ",执行的命令：" + cmd);
                    result = processStdout(session.getStderr(), DEFAULTCHART);
                } else {
                    logger.info("执行命令成功,链接conn:" + conn + ",执行的命令：" + cmd);
                }
                conn.close();
                session.close();
            }
        } catch (Exception e) {
            logger.info("执行命令失败,链接conn:" + conn + ",执行的命令：" + cmd + "  " + e.getMessage());
            logger.error("执行命令失败", e);
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     * @param in  输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    private static String processStdout(InputStream in, String charset) {
        InputStream stdout = new StreamGobbler(in);
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer("\n");
        try {
            br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("解析脚本出错：", e);
        } catch (Exception e) {
            logger.error("解析脚本出错：", e);
        } finally {
            if (stdout != null) {
                try {
                    stdout.close();
                } catch (IOException e) {
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return buffer.toString();
    }
}
