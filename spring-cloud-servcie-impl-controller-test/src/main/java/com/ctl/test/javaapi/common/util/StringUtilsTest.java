package com.ctl.test.javaapi.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title: Test</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-25 16:16
 */
public class StringUtilsTest {
    static String str = "12345678901234567890";

    public static void main(String[] args) {
        String leftPadStr = StringUtils.leftPad("123", 4, '0');
        System.out.println(leftPadStr);//0123
        String left = StringUtils.left(str, 1);//1
        System.out.println(StringUtils.remove(str,'6'));//123457890123457890
    }
}
