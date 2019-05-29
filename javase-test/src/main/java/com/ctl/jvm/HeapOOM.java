package com.ctl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: HeapOOM</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-05-29 11:12
 */
public class HeapOOM {
    static class OOMObject {

    }
    //-Xms2M -Xmx2M -Xss128k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\tmp -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime
    //生成的hprof可以使用Java VisualVM打开
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
        //say();
    }
    static void say(){
        say();
    }
}
