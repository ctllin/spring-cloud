package com.ctl.test.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 直接继承WebMvcConfigurationSupport
 */
@Configuration
public class WebMvcConfg2 extends WebMvcConfigurationSupport {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("sse/index").setViewName("sse1");
//        registry.addViewController("sse/index1").setViewName("pages/sse1");
//        registry.addViewController("sse/index2").setViewName("static/pages/sse1");
    }
}

