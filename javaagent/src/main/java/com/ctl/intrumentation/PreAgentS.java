package com.ctl.intrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
public class PreAgentS {
    static final Logger logger = LoggerFactory.getLogger(PreAgentS.class);
    /**
     * 该方法是一个类作为agent类必备的
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs,Instrumentation inst){
        logger.info("################################【premain】#######################################");

        //加入ClassFileTransfomer
        inst.addTransformer(new JavaAgentMethodClassFileTransformer());
//        logger.info("premain agentArgs={}", agentArgs);
//        Class[] classes = inst.getAllLoadedClasses();
//        for (Class cls : classes) {
//            logger.info("premain className={}", cls.getName());
//        }
    }

}
//在 Java SE 5 当中，开发者可以让 Instrumentation 代理在 main 函数运行前执行。简要说来就是如下几个步骤：
//(1) 编写 premain 函数
//编写一个 Java 类，包含如下两个方法当中的任何一个：
//public static void premain(String agentArgs, Instrumentation inst);  [1]
//public static void premain(String agentArgs); [2]
//其中，[1] 的优先级比 [2] 高，将会被优先执行（[1] 和 [2] 同时存在时，[2] 被忽略）。在这个 premain 函数中，开发者可以进行对类的各种操作。
//agentArgs 是 premain 函数得到的程序参数，随同 “-javaagent”一起传入。与 main 函数不同的是，这个参数是一个字符串而不是一个字符串数组，如果程序参数有多个，程序将自行解析这个字符串。
//Inst 是一个 java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入。java.lang.instrument.Instrumentation 是 instrument 包中定义的一个接口，也是这个包的核心部分，集中了其中几乎所有的功能方法，例如类定义的转换和操作等等。
//(2) jar 文件打包
//将这个 Java 类打包成一个 jar 文件，并在其中的 manifest 属性当中加入” Premain-Class”来指定步骤 1 当中编写的那个带有 premain 的 Java 类。（可能还需要指定其他属性以开启更多功能）