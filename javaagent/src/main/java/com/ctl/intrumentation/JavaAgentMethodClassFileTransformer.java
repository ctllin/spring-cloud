package com.ctl.intrumentation;


import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class JavaAgentMethodClassFileTransformer implements ClassFileTransformer {
    static final Logger logger = LoggerFactory.getLogger(JavaAgentMethodClassFileTransformer.class);

    /**
     * 通过javassist修改字节码
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        logger.info("loadclass={}", className);
        if ("com/ctl/intrumentation/JavaAgentMethod".equals(className)) {
            try {
                //通过javassist修改method1 和 method2 方法字节码
                CtClass ctClass = ClassPool.getDefault().get(className.replace('/', '.'));

                CtMethod method1 = ctClass.getDeclaredMethod("method1");
                method1.insertBefore("logger.info(\"before method1----\");");
                method1.insertAfter("logger.info(\"after method1----\");");

                CtMethod method2 = ctClass.getDeclaredMethod("method2");
                method2.insertBefore("logger.info(\"before method2----\");");
                method2.insertAfter("logger.info(\"after method2----\");");
                return ctClass.toBytecode();

            } catch (NotFoundException e) {
                logger.error("转换异常1", e);
            } catch (CannotCompileException e) {
                logger.error("转换异常2", e);
            } catch (IOException e) {
                logger.error("转换异常3", e);
            }
        }
        return classfileBuffer;
    }
}
