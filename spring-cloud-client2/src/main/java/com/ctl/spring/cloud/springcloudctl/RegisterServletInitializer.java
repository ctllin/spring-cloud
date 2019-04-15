package com.ctl.spring.cloud.springcloudctl;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <p>Title: RegisterServletInitializer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-01-14 13:00
 */
//mvn clean package -Dmaven.test.skip=true 可以在Tomcat的webapps目录下面直接部署
//Tomcat 启动端口 1001 将war改名为ROOT.war部署
public class RegisterServletInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ConsumerApplication.class);
    }
}
