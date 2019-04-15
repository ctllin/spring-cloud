package com.ctl.test.sprinz;

import com.ctl.test.sprinz.service.Encoreable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * com.ctl.test.sprinz
 * Test
 * ctl 2019/3/29 2:18
 */
public class Test {
    public static void main(String[] args) {
        //测试时需要打开 com.ctl.test.sprinz.AnnotationConfig 和  com.ctl.test.sprinz.EncoreableIntroducer上面的注解
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext( AnnotationConfig.class);
        System.out.println(Arrays.deepToString(ctx.getBeanDefinitionNames()));
        Encoreable encoreable = (Encoreable) ctx.getBean("gangQinPerformance");
        encoreable.performEncore("123");
        encoreable = (Encoreable)ctx.getBean("encoreableImpl");
        encoreable.performEncore("456");
    }

}
