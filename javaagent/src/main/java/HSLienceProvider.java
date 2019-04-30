
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
        System.loadLibrary("hspk");//export LD_LIBRARY_PATH=/home/wise/ctest,注意名字去点lib和.so(libhspk.so在/home/wise/ctest文件夹下面)
        //System.load("/home/wise/ctest/libhspk.so");//直接加载
    }
    public native String getHspk();

    public static void main(String[] args) {
        System.out.println(new HSLienceProvider().getHspk());
    }
}
//javac HSLienceProvider.java
//javah HSLienceProvide
//创建HSLienceProvide.c
//gcc -fPIC -D_REENTRANT -I/home/wise/ctest/jdk1.8.0_151/include -I/home/wise/ctest/jdk1.8.0_151/include/linux -c HSLienceProvider.c
//gcc -shared HSLienceProvider.o -o libhspk.so

/**
gcc -fPIC -D_REENTRANT -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -c HSLienceProvider.c
gcc -shared HSLienceProvider.o -o libhspk.so
*/
