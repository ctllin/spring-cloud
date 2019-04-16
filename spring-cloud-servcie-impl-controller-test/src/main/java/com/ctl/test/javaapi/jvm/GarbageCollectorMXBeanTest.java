package com.ctl.test.javaapi.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * <p>Title: GarbageCollectorMXBeanTest</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * 查看JVM使用的垃圾收集器/设置垃圾收集器JVM参数
 * @author guolin
 * @version 1.0
 * @date 2019-03-26 11:05
 */
public class GarbageCollectorMXBeanTest {
    public static void main(String[] args) {
        /* -XX:+UseParallelOldGC和-XX:+UseParallelGC结果一样，因为MXBean名字一样，但是实际使用的不一样 */
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : beans) {
            System.out.println(bean.getName());
        }
    }
}
