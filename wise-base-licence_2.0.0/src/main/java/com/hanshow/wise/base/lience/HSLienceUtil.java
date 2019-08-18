package com.hanshow.wise.base.lience;

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
public class HSLienceUtil {

    public static void main(String[] args) {

        //  System.out.println(createLicenceStr(109, 1338));
        System.out.println(createLicenceStr(12, 13));
    }

    private static String createLicenceStr(int storeNums, int deviceNums) {
        if (storeNums < 0 || deviceNums < 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("H");

        String storeNumStr = intToHex(storeNums);
        int storeNumStrLength = storeNumStr.length();
        buf.append(leftPad(intToHex(storeNumStrLength), 8, '0')).append(storeNumStr);

        String deviceNumStr = intToHex(deviceNums);
        int deviceNumStrLength = deviceNumStr.length();
        buf.append(leftPad(intToHex(deviceNumStrLength), 8, '0')).append(deviceNumStr);
        buf.append((buf.length() + 4 + 1 + 1) % 2 == 0 ? 0 : 1);
        buf.append("S");
        buf.insert(1, leftPad(intToHex((buf.length() + 4)), 4, '0'));
        return buf.toString();
    }

    private static String leftPad(String str, int size, char pad) {
        if (str == null) {
            return str;
        }
        int length = str.length();
        for (int i = 0; i < size - length; i++) {
            str = pad + str;
        }
        return str;
    }

    private static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        if (n == 0) {
            return "0";
        }
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }
}
