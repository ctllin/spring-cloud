package com.ctl.intrumentation;

/**
 * com.ctl.intrumentation
 * AttachTest
 * ctl 2019/3/20 19:28
 */

import java.io.IOException;
import java.util.List;

import com.sun.tools.attach.*;

public class AttachTest {
    public static void main(String[] args) throws AttachNotSupportedException,IOException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith("AttachTest")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("E:\\github\\spring-cloud\\javaagent\\target\\javaagent-1.0-SNAPSHOT.jar");
                virtualMachine.detach();
            }
        }
        JavaAgentMethod people = new JavaAgentMethod();
        people.method1();
        System.out.println(people.method2());
    }
}
//premain 虽然可以热部署，但是还需要重新创建类加载器，虽然，这的确也符合 JVM 关于类的唯一性的定义。
//但是，有一种情况，如果使用的是系统类加载器，我们也无法创建新的ClassLoader对象。那么我们也就无法重新加载类了，怎么办呢？还好 Java 6 为我们提供了一种方法，
//也就是今天的主角 agentmain。