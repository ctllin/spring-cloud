package com.ctl.intrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class AgentMainS {
    static final Logger logger = LoggerFactory.getLogger(AgentMainS.class);

    public static void agentmain(String args, Instrumentation inst) {
        logger.info("################################【agentmain】#######################################");
        inst.addTransformer(new JavaAgentMethodClassFileTransformer());
//        logger.info("agentmain args={}", args);
//        Class[] classes = inst.getAllLoadedClasses();
//        for (Class cls : classes) {
//            logger.info("agentmain className={}", cls.getName());
//        }
    }

    public static void main(String[] args) {
        logger.info(" AgentMain main is call ............");
    }
}
/*
Java SE 6 的新特性：虚拟机启动后的动态 instrument
在 Java SE 5 当中，开发者只能在 premain 当中施展想象力，所作的 Instrumentation 也仅限与 main 函数执行前，这样的方式存在一定的局限性。
在 Java SE 5 的基础上，Java SE 6 针对这种状况做出了改进，开发者可以在main 函数开始执行以后，再启动自己的 Instrumentation 程序。
在 Java SE 6 的 Instrumentation 当中，有一个跟 premain“并驾齐驱”的“agentmain”方法，可以在 main 函数开始运行之后再运行。跟 premain 函数一样， 开发者可以编写一个含有“agentmain”函数的 Java 类：
public static void agentmain (String agentArgs, Instrumentation inst); [1]
public static void agentmain (String agentArgs);[2]
同样，[1] 的优先级比 [2] 高，将会被优先执行。跟 premain 函数一样，开发者可以在 agentmain 中进行对类的各种操作。其中的 agentArgs 和 Inst 的用法跟 premain 相同。
与“Premain-Class”类似，开发者必须在 manifest 文件里面设置“Agent-Class”来指定包含 agentmain 函数的类。
可是，跟 premain 不同的是，agentmain 需要在 main 函数开始运行后才启动，这样的时机应该如何确定呢，这样的功能又如何实现呢？
在 Java SE 6 文档当中，开发者也许无法在 java.lang.instrument 包相关的文档部分看到明确的介绍，更加无法看到具体的应用 agnetmain 的例子。不过，在 Java SE 6 的新特性里面，有一个不太起眼的地方，揭示了 agentmain 的用法。这就是Java SE 6 当中提供的 Attach API。
Attach API 不是 Java 的标准 API，而是 Sun 公司提供的一套扩展 API，用来向目标 JVM ”附着”（Attach）代理工具程序的。有了它，开发者可以方便的监控一个 JVM，运行一个外加的代理程序。
Attach API 很简单，只有 2 个主要的类，都在 com.sun.tools.attach 包里面：VirtualMachine 代表一个 Java 虚拟机，也就是程序需要监控的目标虚拟机，提供了 JVM 枚举，Attach 动作和 Detach 动作（Attach 动作的相反行为，从 JVM 上面解除一个代理）等等 ;VirtualMachineDescriptor 则是一个描述虚拟机的容器类，配合 VirtualMachine 类完成各种功能。
为了简单起见，我们举例简化如下：依然用类文件替换的方式，将一个返回 1 的函数替换成返回 2 的函数，Attach API 写在一个线程里面，用睡眠等待的方式，每隔半秒时间检查一次所有的 Java 虚拟机，当发现有新的虚拟机出现的时候，就调用 attach 函数，随后再按照 Attach API 文档里面所说的方式装载 Jar 文件。等到 5 秒钟的时候，attach 程序自动结束。而在 main 函数里面，程序每隔半秒钟输出一次返回值（显示出返回值从 1 变成 2）。
TransClass 类和 Transformer 类的代码不变，参看上一节介绍。 含有 main 函数的 TestMainInJar 代码为：

*/
