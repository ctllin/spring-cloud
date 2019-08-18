package com.hanshow.wise.base.lience;//import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title: HSLienceProvider</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
  1）将改项目打成jar包,放入tomcat的lib文件加下面
  2）将生成的so文件放入到usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib 其中的一个文件加下面
 public class HSLienceConstants {
     public static final String HS_LIENCE_PROVIDER_UTIL="com.hanshow.wise.base.lience.util.HSLienceProviderUtil";
     public static final String HS_LIENCE_PROVIDER="com.hanshow.wise.base.lience.HSLienceProvider";
 }
3）
 try {
     //如果tomcat lib文件夹下没有改jar包,则进去catch里面这时可以进行处理
     //虽然可以有多个项目执行该try catch 但是 HSLienceProvider中的System.loadLibrary("hspk") 在tomcat启动后只会执行一次且只要该tomcat中的项目
     //都可以调用该类的native方法,war包重新部署不会导致so重新加载只有tomcat重启才会导致so加载
     //多个tomcat情况，每个tomcat加载so互不影响
     Class<?> aClass = Class.forName(HSLienceConstants.HS_LIENCE_PROVIDER);
     HSLienceProvider hsLienceProvider = new HSLienceProvider();
     lienceStr = new String(Base64Utils.decodeFromString(rsa.decrypt(hsLienceProvider.getHspk(), lience)));
     logger.info("lienceStr={},storeNum={},deviceNum={},native_so", lienceStr, hsLienceProvider.getValue(lienceStr, 1), hsLienceProvider.getValue(lienceStr, 2));
 } catch (ClassNotFoundException e) {
     logger.error("grantServerClassIsNotExist");
     destory();
     return;
 }
 * @author guolin
 * @version 1.0
 * @date 2019-04-30 17:42
 */
public class HSLienceProvider {
    //    private static Logger logger = LoggerFactory.getLogger(HSLienceProvider.class);
    static {
        String os = System.getProperty("os.name");
        if (!os.equalsIgnoreCase("Linux")) {
//            logger.info("非linux系统");
            System.out.println("非linux系统");
        } else {
            try {
                ///usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib  so文件需要放到这里面其中的一个文件
//                String property = System.getProperty("java.library.path");
                System.loadLibrary("hspk");//export LD_LIBRARY_PATH=/home/wise/ctest,\u6CE8\u610F\u540D\u5B57\u53BB\u70B9lib\u548C.so(libhspk.so\u5728/home/wise/ctest\u6587\u4EF6\u5939\u4E0B\u9762)
            } catch (UnsatisfiedLinkError e) {
//                logger.error("hspk load UnsatisfiedLinkError", e);
                System.out.println("hspk load UnsatisfiedLinkError");

            } catch (Exception e) {
//                logger.error("hspk load error", e);
                System.out.println("hspk load error");

            }
        }
    }

    public native String getHspk();

    /**
     * 获取授权额门店或设备数
     * @param str 授权的licence文件内容
     * @param seq 1是获取门店数量2获取设备数量
     * @return
     */
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

#!/bin/bash
function getdir(){
for element in `ls $1`
do
dir_or_file=$1"/"$element
if [ -d $dir_or_file ]
then
getdir $dir_or_file
else
#echo $dir_or_file >> path.path

 if [[ $dir_or_file == *java ]]; then
  echo $dir_or_file
	javac -cp .:/home/wise/ctest/slf4j-api-1.7.25.jar $dir_or_file
 fi
fi
done
}
root_dir="."
getdir $root_dir

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
