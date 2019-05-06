package com.ctl.jni;//import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * <p>Title: HSLienceProvider</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-30 17:42
 */
public class HSLienceProvider {
    static
    {
        System.loadLibrary("hspk");//export LD_LIBRARY_PATH=/home/wise/ctest,\u6CE8\u610F\u540D\u5B57\u53BB\u70B9lib\u548C.so(libhspk.so\u5728/home/wise/ctest\u6587\u4EF6\u5939\u4E0B\u9762)
        //System.load("/home/wise/ctest/libhspk.so");//\u76F4\u63A5\u52A0\u8F7D
    }
    public native String getHspk();
    public native int getValue(String str,int seq);

    public static void main(String[] args) {
        System.out.println(new HSLienceProvider().getHspk());
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (!str.equals("quit")) {
            System.out.println("请输入报文:");
            str = scanner.nextLine();
            try {
                System.out.println(new HSLienceProvider().getValue(str.split("#")[0],Integer.parseInt(str.split("#")[1]) ));
            } catch (NumberFormatException e) {
                str="quit";
            }
        }
        scanner.close();
    }
}
//javac HSLienceProvider.java
//javah HSLienceProvide
//\u521B\u5EFAHSLienceProvide.c
//gcc -fPIC -D_REENTRANT -I/home/wise/ctest/jdk1.8.0_151/include -I/home/wise/ctest/jdk1.8.0_151/include/linux -c HSLienceProvider.c
//gcc -shared HSLienceProvider.o -o libhspk.so
/*
javac HSLienceProvider.java
gcc -fPIC -D_REENTRANT -I/home/wise/ctest/jdk1.8.0_151/include -I/home/wise/ctest/jdk1.8.0_151/include/linux -c HSLienceProvider.c
gcc -shared HSLienceProvider.o -o libhspk.so
java HSLienceProvider
*/
