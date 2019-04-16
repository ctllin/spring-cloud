package com.ctl.test.javaapi.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * com.ctl.test.javaapi.jvm
 * ThreadMXBeanTest
 * ctl 2019/3/23 20:59
 */
public class ThreadMXBeanTest {
    public static void main(String[] args) {

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (int i = 0; i < threadInfos.length; i++) {
            System.out.println(threadInfos[i]);
        }
    }
}
