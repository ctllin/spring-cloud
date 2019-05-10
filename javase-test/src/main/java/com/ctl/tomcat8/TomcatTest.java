package com.ctl.tomcat8;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>Title: TomcatTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *  调用tomcat命令卸载服务
 * @author guolin
 * @version 1.0
 * @date 2019-05-10 09:55
 */
public class TomcatTest {
    static Logger logger = LoggerFactory.getLogger(TomcatTest.class);
    //tomcat中部署的项目rtmart-base-acl-impl.war
    static final String appName = "rtmart-base-acl-impl";
    static final String UESERNAME = "root";
    static final String PASSWORD = "123456";
    static final String UNDEPLOY_URL = "http://192.168.3.117:8071/manager/text/undeploy?path=/";// appName就是你需要卸载的应用

    public static void main(String[] args) throws IOException {
        URL url = null;
        BufferedReader breader = null;
        InputStream is = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            url = new URL(UNDEPLOY_URL + appName);
            String userPassword = UESERNAME + ":" + PASSWORD;//此处为用户名密码
            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());//在classpath中添加rt.jar包，在%java_home%/jre/lib/rt.jar
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            int statusCode = conn.getResponseCode();
            is = conn.getInputStream();
            breader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = breader.readLine()) != null) {
                resultBuffer.append(line);
            }
            //http请求失败
            if (statusCode != HttpStatus.SC_OK) {
                logger.info("step-2:卸载" + appName + " ERROR！");
                throw new RuntimeException("step-2:卸载" + appName + " ERROR！");
            } else {
                String responseBody = new String(resultBuffer.toString());
                //说明不成功
                if (!responseBody.contains("OK")) {
                    logger.info("step-2:卸载" + appName + " ERROR！");
                    throw new RuntimeException("step-2:卸载" + appName + " ERROR！");
                }
            }
            logger.info("step-2:卸载" + appName + "成功！");

        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("step-2:卸载" + appName + " ERROR !" + e.getMessage());
        } finally {
            if (breader != null)
                breader.close();
            if (is != null)
                is.close();
        }

    }
}
//通过程序调用tomcat的manager的text模式的命令来自动部署项目
//现在我需要做一个tomcat 的自动部署的功能 就是在不重启tomcat的情况下 可以发布 重启、升级应用
//1、tomcat的manager的text的模式是可以实现这一功能的 
//public final static String WEB_LIST="http://localhost:8080/manager/text/list";
//public final static String STOP_URL="http://localhost:8080/manager/stop?path=/"; //停止
//public final static String START_URL="http://localhost:8080/manager/start?path=/";//启动
//public final static String DEPLOY_URL="http://localhost:8080/manager/text/deploy?path=/"; //部署
//public final static String UNDEPLOY_URL="http://localhost:8080/manager/text/undeploy?path=/";//卸载
//
//2、由于manager的text命令访问是需要用户名密码才能访问的，所以必须放开访问权限
//<role rolename="manager-gui"/>
//<role rolename="manager-script"/>
//<role rolename="manager-jmx"/>
//<user username="tomcat" password="tomcat" roles="manager-gui,manager-script,manager-jmx"/>
