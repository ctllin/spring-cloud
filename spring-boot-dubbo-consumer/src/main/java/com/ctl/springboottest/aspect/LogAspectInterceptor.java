package com.ctl.springboottest.aspect;

import com.ctl.springboottest.constants.DubboLogConstants;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <p>Title: LogAcpectAspect</p>
 * <p>Description: 接口权限拦截 判断该用户是否拥有调用此接口的权限</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-19 10:47
 */
@Aspect
@Component
public class LogAspectInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LogAspectInterceptor.class);

    //配置切入点 只对配置LogAcpect注解的接口进行切入
    @Pointcut("@annotation(com.ctl.springboottest.aspect.LogAcpect)")
    public void controllerAspect() {
        logger.info("log注解权限拦截切入点加载");
    }

    //在执行方法前获取LogAcpect
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        //logger.debug("=====LogAcpectAspect前置通知开始=====");
        //    访问目标方法参数,有三种方法(实际有四种)
        //    1.joinpoint.getargs():获取带参方法的参数
        //    2.joinpoint.getTarget():.获取他们的目标对象信息
        //    3..joinpoint.getSignature():(signature是信号,标识的意思):获取被增强的方法相关信息
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            //controller请求地址没有参数直接放行
            logger.info("请求接口没有参数,不进行权限校验");
            return;
        }
        //获取请求头参数

        //获取注解方法参数值
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAcpect logAcpect = method.getAnnotation(LogAcpect.class);
        String module = logAcpect.module();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put(DubboLogConstants.TRACE_LOG_ID, uuid);
    }
    //在执行方法前获取LogAcpect
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        //logger.debug("=====LogAcpectAspect前置通知开始=====");
        //    访问目标方法参数,有三种方法(实际有四种)
        //    1.joinpoint.getargs():获取带参方法的参数
        //    2.joinpoint.getTarget():.获取他们的目标对象信息
        //    3..joinpoint.getSignature():(signature是信号,标识的意思):获取被增强的方法相关信息
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            //controller请求地址没有参数直接放行
            logger.info("请求接口没有参数,不进行权限校验");
            return;
        }
        //获取请求头参数

        //获取注解方法参数值
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAcpect logAcpect = method.getAnnotation(LogAcpect.class);
        String module = logAcpect.module();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.remove(DubboLogConstants.TRACE_LOG_ID);
    }
}
