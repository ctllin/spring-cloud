package com.ctl.test.sprinz;

import com.ctl.test.sprinz.service.Encoreable;
import com.ctl.test.sprinz.service.impl.EncoreableImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * com.ctl.test.spring.aop.aspect
 * PerformanceAspect
 * ctl 2019/3/28 22:49
 */
//@Aspect //使用com.ctl.test.sprinz.Test测试时需要打开此注释
@Component
public class EncoreableIntroducer {


    @Pointcut("execution (* com.ctl.test.sprinz..*.*(..))")
    public void performance(){}

    @Before("performance()")
    public void silencePhone(){
        System.out.println("手机静音！");
    }

    @Before("performance()")
    public void takeSeats(){
        System.out.println("请坐！");
    }

    @AfterReturning("performance()")
    public void applause(){
        System.out.println("表演结束，鼓掌！！！");
    }

    @AfterThrowing("performance()")
    public void demanRefund(){
        System.out.println("表演失败，退票！！！");
    }



    @Pointcut(" execution (* com.ctl.test.sprinz..*.*(..))")
    public void pcut() {
    }

    @Before("pcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        System.out.println(Arrays.deepToString(joinPoint.getArgs()));
    }

    /**
     * 此注解可以将新加的接口引入到原来调用目标函数的bean中
     */
    @DeclareParents(value = "com.ctl.test.sprinz.service.Performance+", defaultImpl = EncoreableImpl.class)
    public static Encoreable encoreable;
}
