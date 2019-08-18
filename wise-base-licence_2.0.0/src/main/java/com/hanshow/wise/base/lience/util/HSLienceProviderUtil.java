package com.hanshow.wise.base.lience.util;//import org.apache.commons.lang3.StringUtils;

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
public class HSLienceProviderUtil {
    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCw/l5+MAcpRtCmROrQ2tddQ2ka41w69yYop2IJoAo2j39dIdOgPRMQkPlf2KdJ3b+FP4kPZF6xmV6nR3kcPjFrNahTrufRi4BHGUKqqixUXHknkEF6pDZ3zm5DwkFDuYgLn8qZE/3Txz4QQsPk2THIwcCnffY7dEhUNFJSdwt76hxX2n+w3fI3HncjbvowpMqElLBDfeDnliFIf+l1qY/ywCSS1QAswTZwHKS4Hu/0h/QbWhroHAEpLQcrEeTShtsxWVlWtElk+lGCyFXZoT+E2ZEY6wpmJ8yd/CdFG5gMFJT45dXXNVkTeqrhzOTYctrW3D+rmtdsrVAoV3q4UjKTAgMBAAECggEACGsAuYW1QXxRvia2+FoANjpXjGTV5SQOF47mdci70559k0NhmlRtTLNoeOuIAVZfnonIDghbDJ1byB75HqPCm/PAXsKJ2giFgFwuNInFc/m19XDB/IBE6uvRiL17dyoi01K1QxSVVPY7+HAEeyTtXjwA2rZ9+JPuQLsAU1XAIROMI0WYNJfuWA60Ipnv20yQCALojrFAs2Fa7n12FB0muuvaz7QWj5ShrzawpNJDGMRJ4Uk4S24qWzlUj8QTQKNYXyYIrBTMg4bW6zobQGXmXkFBsLCH7FcjpgROqwcEk3IFeGOfSLuVxo3j0ZtvJwlY5bduG3J6eVYcKCAD4jJsyQKBgQDeLE2HMd0RWzWb58hGU60nxkeddc4tR59jxuCY9mKBKgVSvf9j9DRVrnbCHbTKL/Xr/E84SjKDB+Xl9ZjjFoPKKSvtOYn9hOq2w6CBrNf/NMA5mZjS80vf/VSIf0BH9KNj8KZ4NTqFzuH8qpvyqjraioqrgoD5qfvFuoty17gS3wKBgQDL8RiNjA0J7NMTd5mwOuPQsG0Ous/dS4eCobP27Y9DV2ySTIGmwsJ8vAhJ9pKP9bwchkWkjhrz329kIxr3U9JczDBky30y9oke44mGRibmHwQTFlctHF7sSUWVItqbM+RtXT6nRkBEBXc5DpkbXwuS+j5f74/P0Ed4V0d4yf2qzQKBgQDRo9xEBbkj93zOcBd6xNpO/bn1yoOSFlNAQxkLPPIWDyCWwtGJ75SyhL1qzb0aS8cxthLKerjPC1TTeLubDFOvHspkPjcHEbSoYE/EDOSD27LGpzidVRXzdYeehPSS8TR7yUhLhW6DdIOpdxNQF3ERZkkTLUGmU89O9IdBBmya4QKBgDn66UgUsQBJXBkEv6TimTITdm3hnocDmhsBbfCdyUUT7cIKDjIi3LgpnRGqEDDtY/WYFuSZogADXd/SOzBlnEDVrEx7rbR3crRdbBPIjfaOitICX/ZrQnFaw3WK2yuUrTSKde2Ig2zTQPnvtIeblebLTkBoEzK5vBfyeFdW1c9hAoGBAM+59osrUnqfA27ABvBkWo5s1vEA7IB42RxwFsS8NqqNTtX8mOKvJESIjVef3PmmKJcdmx3LqsRVeqkORXfHi+TdqtAw8jgliOh/tN2Y/P1bpf31blARxau83oHn4H9zKun5pSx/HkKDPWfCmcMY1AeAsIr63HpPTERqNlPRLe7u";

    public static String getHspk() {
        return PRIVATE_KEY;
    }

    public static int getValue(String str, int seq) {
        if (str == null || "".equals(str.trim())) {
            return -1;
        }
        int len = str.length();
        if (len > 7) {
            String head;
            String head_ = "H";

            String lengthStr;
            int lengthStrToInt = 0;

            String firstParamLenthStr;
            int firstParamLenthStrToInt = 0;

            String firstParamStr;
            int firstParamStrToInt = 0;

            String secondParamLenthStr;
            int secondParamLenthStrToInt = 0;

            String secondParamStr;
            int secondParamStrToInt = 0;

            String tailfValidStr;
            int tailfValidStrToInt = 0;

            String tail;
            String tail_ = "S";

            head = str.substring(0, 1); //\u4EE5H\u5F00\u5934
            //eq=0\u76F8\u7B49, \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570 , \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570\uFF1B
            if (!head_.equals(head)) {//eq
                return -2;
            }

            tail = str.substring(len - 1, len);//\u4EE5S\u7ED3\u5C3E
            //eq=0\u76F8\u7B49, \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570 , \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570\uFF1B
            if (!tail_.equals(tail)) {//eq
                return -3;
            }

            tailfValidStr = str.substring(len - 2, len - 1); //\u5012\u6570\u7B2C\u4E8C\u4F4D\u4E3A\u5947\u5076\u6821\u9A8C\u4F4D
            tailfValidStrToInt = Integer.parseInt(tailfValidStr);
            if ((tailfValidStrToInt + len) % 2 != 0) {
                return -4;
            }

            lengthStr = str.substring(1, 5); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
            lengthStrToInt = Integer.parseInt(lengthStr, 16);
            if (len != lengthStrToInt) {
                return -5;
            }

            firstParamLenthStr = str.substring(5, 13); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
            firstParamLenthStrToInt = Integer.parseInt(firstParamLenthStr, 16);
            if (firstParamLenthStrToInt <= 0) {
                return -6;
            }

            firstParamStr = str.substring(13, 13 + firstParamLenthStrToInt); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
            firstParamStrToInt = Integer.parseInt(firstParamStr, 16);
            if (seq == 1) {
                return firstParamStrToInt;
            }

            secondParamLenthStr = str.substring(13 + firstParamLenthStrToInt, 21 + firstParamLenthStrToInt); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
            secondParamLenthStrToInt = Integer.parseInt(secondParamLenthStr, 16);
            if (secondParamLenthStrToInt <= 0) {
                return -7;
            }

            secondParamStr = str.substring(21 + firstParamLenthStrToInt, 21 + firstParamLenthStrToInt + secondParamLenthStrToInt); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
            secondParamStrToInt = Integer.parseInt(secondParamStr, 16);
            if (seq == 2) {
                return secondParamStrToInt;
            }
        }
        return -1;
    }
}
