package com.ctl.intrumentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JavaAgentMethod {
    static final Logger logger = LoggerFactory.getLogger(JavaAgentMethod.class);
    public void method1(){
        logger.info("load method1 is call......");
    }
    public String method2(){
        logger.info("load method2 is call......");
        return ""+System.currentTimeMillis();
    }
}
