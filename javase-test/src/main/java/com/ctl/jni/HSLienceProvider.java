package com.ctl.jni;//import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger logger = LoggerFactory.getLogger(HSLienceProvider.class);
    static {
        String os = System.getProperty("os.name");
        if (!os.equalsIgnoreCase("Linux")) {
            logger.info("非linux系统");
        } else {
            try {
                //加载lience私钥
                System.loadLibrary("hspk");//export LD_LIBRARY_PATH=/home/wise/ctest,\u6CE8\u610F\u540D\u5B57\u53BB\u70B9lib\u548C.so(libhspk.so\u5728/home/wise/ctest\u6587\u4EF6\u5939\u4E0B\u9762)
                //System.load(ConfigUtils.getType("hslience.path"));//\u76F4\u63A5\u52A0\u8F7D
                System.out.println("加载完毕");
            } catch (UnsatisfiedLinkError e) {
                logger.error("hspk加载失败UnsatisfiedLinkError", e);
            } catch (Exception e){
                logger.error("hspk加载失败", e);
            }
        }
    }

    public native String getHspk();

    public native int getValue(String str, int seq);

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(new HSLienceProvider().getHspk());
    }
}
//javac HSLienceProvider.java
//javah HSLienceProvide
//\u521B\u5EFAHSLienceProvide.c
//gcc -fPIC -D_REENTRANT -I/home/wise/ctest/jdk1.8.0_151/include -I/home/wise/ctest/jdk1.8.0_151/include/linux -c HSLienceProvider.c
//gcc -shared HSLienceProvider.o -o libhspk.so
/*

javac -cp .:./slf4j-api-1.7.25.jar *.java
mkdir -p com/hanshow/wise/base/privileges/lience/
cp HSLienceProvider.class com/hanshow/wise/base/privileges/lience/
mkdir -p com/hanshow/wise/base/privileges/util/
cp ConfigUtils.class com/hanshow/wise/base/privileges/util/

javah com.hanshow.wise.base.privileges.lience.HSLienceProvider
gcc -fPIC -D_REENTRANT -I/home/wise/ctest/jdk1.8.0_151/include -I/home/wise/ctest/jdk1.8.0_151/include/linux -c com_hanshow_wise_base_privileges_lience_HSLienceProvider.c
gcc -shared com_hanshow_wise_base_privileges_lience_HSLienceProvider.o -o libhspk.so
java  -cp .:./slf4j-api-1.7.25.jar:./logback-classic-1.2.3.jar:./logback-core-1.2.3.jar  com.hanshow.wise.base.privileges.lience.HSLienceProvider
*/
