package com.ctl.test.sprinz;

/**
 * com.ctl.test.sprinz
 * AnnotationConfig
 * ctl 2019/3/29 2:22
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
//@EnableAspectJAutoProxy //使用com.ctl.test.sprinz.Test测试时需要打开此注释
//@ImportResource("classpath:applicationContext.xml")
public class AnnotationConfig {

}
