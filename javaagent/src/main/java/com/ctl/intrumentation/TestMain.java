package com.ctl.intrumentation;

public class TestMain {
//    VM options 增加 -javaagent:E:\github\spring-cloud\javaagent\target\javaagent-1.0-SNAPSHOT.jar
//    META-INF/MANIFEST.MF 增加下面两行
//    Premain-Class: com.ctl.intrumentation.MyAgent
//    Main-Class:  com.ctl.intrumentation.TestMain
    public static void main(String[] args) {
        JavaAgentMethod people = new JavaAgentMethod();
        people.method1();
        System.out.println(people.method2());
    }
}
