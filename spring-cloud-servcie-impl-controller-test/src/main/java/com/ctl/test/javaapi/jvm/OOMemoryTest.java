package com.ctl.test.javaapi.jvm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * com.ctl.test.javaapi.jvm
 * OutOfMemory
 * -Xmx1g -Xms1g -Xmn128M -Xss128k -XX:ThreadStackSize=10
 * 　在JVM的启动参数中加入-XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime，
 * 按照参数的顺序分别输出GC的简要信息，GC的详细信息、GC的时间信息及GC造成的应用暂停的时间。
 * ctl 2019/3/22 22:40
 */
public class OOMemoryTest {
    private int a = 100000;
    private int b = 100000;
    private int c = 100000;
    private int d = 100000;
    private int e = 100000;
    private String name = "OOMemoryTest";

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("唐僧", "pwd1", 25);
        Person p2 = new Person("孙悟空", "pwd2", 26);
        Person p3 = new Person("猪八戒", "pwd3", 27);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("总共有:" + set.size() + " 个元素!"); //结果：总共有:3 个元素!
        p3.setAge(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变

        set.remove(p3); //此时remove不掉，造成内存泄漏

        set.add(p3); //重新添加，居然添加成功
        System.out.println("总共有:" + set.size() + " 个元素!"); //结果：总共有:4 个元素!
        for (Person person : set) {
            System.out.println(person);
        }
        //int[] array = new int[10000*1000000]; //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        OOMemoryTest ooMemoryTest = new OOMemoryTest();
        int i = 0;
        //ooMemoryTest.method(); //Exception in thread "main" java.lang.StackOverflowError
        ooMemoryTest.method2();
    }

    public void method() {
        int a1 = 100000;
        int b1 = 100000;
        int c1 = 100000;
        int d1 = 100000;
        int e1 = 100000;
        OOMemoryTest ooMemoryTest = new OOMemoryTest();
        ooMemoryTest.method();
    }

    public void method2() {
        int a1 = 100000;
        int b1 = 100000;
        int c1 = 100000;
        int d1 = 100000;
        int e1 = 100000;
        // for (int i = 0; i < 100000; i++) {
        try {
            // Thread.sleep(100);
            method2();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        //  }
    }
}

class Person {
    public Person() {
    }

    public Person(String name, String pwd, int age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    private String name;
    private String pwd;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(pwd, person.pwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, pwd, age);
    }
}
