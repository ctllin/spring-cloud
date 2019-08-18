#include "com_hanshow_wise_base_lience_HSLienceProvider.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* \u5341\u516D\u8FDB\u5236\u6570\u8F6C\u6362\u4E3A\u5341\u8FDB\u5236\u6570 */
long hexToDec(char *source);

/* \u8FD4\u56DEch\u5B57\u7B26\u5728sign\u6570\u7EC4\u4E2D\u7684\u5E8F\u53F7 */
int getIndexOfSigns(char ch);
/* \u5B57\u7B26\u4E32\u622A\u53D6 */
char* substring(char* str,int pos,int length);


JNIEXPORT jstring JNICALL Java_com_hanshow_wise_base_lience_HSLienceProvider_getHspk(JNIEnv *env, jobject jj)
{
	char *str = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCw/l5+MAcpRtCmROrQ2tddQ2ka41w69yYop2IJoAo2j39dIdOgPRMQkPlf2KdJ3b+FP4kPZF6xmV6nR3kcPjFrNahTrufRi4BHGUKqqixUXHknkEF6pDZ3zm5DwkFDuYgLn8qZE/3Txz4QQsPk2THIwcCnffY7dEhUNFJSdwt76hxX2n+w3fI3HncjbvowpMqElLBDfeDnliFIf+l1qY/ywCSS1QAswTZwHKS4Hu/0h/QbWhroHAEpLQcrEeTShtsxWVlWtElk+lGCyFXZoT+E2ZEY6wpmJ8yd/CdFG5gMFJT45dXXNVkTeqrhzOTYctrW3D+rmtdsrVAoV3q4UjKTAgMBAAECggEACGsAuYW1QXxRvia2+FoANjpXjGTV5SQOF47mdci70559k0NhmlRtTLNoeOuIAVZfnonIDghbDJ1byB75HqPCm/PAXsKJ2giFgFwuNInFc/m19XDB/IBE6uvRiL17dyoi01K1QxSVVPY7+HAEeyTtXjwA2rZ9+JPuQLsAU1XAIROMI0WYNJfuWA60Ipnv20yQCALojrFAs2Fa7n12FB0muuvaz7QWj5ShrzawpNJDGMRJ4Uk4S24qWzlUj8QTQKNYXyYIrBTMg4bW6zobQGXmXkFBsLCH7FcjpgROqwcEk3IFeGOfSLuVxo3j0ZtvJwlY5bduG3J6eVYcKCAD4jJsyQKBgQDeLE2HMd0RWzWb58hGU60nxkeddc4tR59jxuCY9mKBKgVSvf9j9DRVrnbCHbTKL/Xr/E84SjKDB+Xl9ZjjFoPKKSvtOYn9hOq2w6CBrNf/NMA5mZjS80vf/VSIf0BH9KNj8KZ4NTqFzuH8qpvyqjraioqrgoD5qfvFuoty17gS3wKBgQDL8RiNjA0J7NMTd5mwOuPQsG0Ous/dS4eCobP27Y9DV2ySTIGmwsJ8vAhJ9pKP9bwchkWkjhrz329kIxr3U9JczDBky30y9oke44mGRibmHwQTFlctHF7sSUWVItqbM+RtXT6nRkBEBXc5DpkbXwuS+j5f74/P0Ed4V0d4yf2qzQKBgQDRo9xEBbkj93zOcBd6xNpO/bn1yoOSFlNAQxkLPPIWDyCWwtGJ75SyhL1qzb0aS8cxthLKerjPC1TTeLubDFOvHspkPjcHEbSoYE/EDOSD27LGpzidVRXzdYeehPSS8TR7yUhLhW6DdIOpdxNQF3ERZkkTLUGmU89O9IdBBmya4QKBgDn66UgUsQBJXBkEv6TimTITdm3hnocDmhsBbfCdyUUT7cIKDjIi3LgpnRGqEDDtY/WYFuSZogADXd/SOzBlnEDVrEx7rbR3crRdbBPIjfaOitICX/ZrQnFaw3WK2yuUrTSKde2Ig2zTQPnvtIeblebLTkBoEzK5vBfyeFdW1c9hAoGBAM+59osrUnqfA27ABvBkWo5s1vEA7IB42RxwFsS8NqqNTtX8mOKvJESIjVef3PmmKJcdmx3LqsRVeqkORXfHi+TdqtAw8jgliOh/tN2Y/P1bpf31blARxau83oHn4H9zKun5pSx/HkKDPWfCmcMY1AeAsIr63HpPTERqNlPRLe7u";
	return (*env)->NewStringUTF(env,str);
}
JNIEXPORT jint JNICALL Java_com_hanshow_wise_base_lience_HSLienceProvider_getValue(JNIEnv *env, jobject jj, jstring js, jint ji){
    char *source=(*env)->GetStringUTFChars(env, js,NULL);
    int len = strlen(source);
    if(len>7){
        char* head;
        char* head_="H";

        char* lengthStr;
        int lengthStrToInt=0;

        char* firstParamLenthStr;
        int firstParamLenthStrToInt=0;

        char* firstParamStr;
        int firstParamStrToInt=0;

        char* secondParamLenthStr;
        int secondParamLenthStrToInt=0;

        char* secondParamStr;
        int secondParamStrToInt=0;

        char* tailfValidStr;
        int tailfValidStrToInt=0;

        char* tail;
        char* tail_="S";

        head = substring(source,0,1); //\u4EE5H\u5F00\u5934
        int eq = strcmp( head,head_); //eq=0\u76F8\u7B49, \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570 , \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570\uFF1B
        if(eq!=0){//eq
          return -2;
        }

        tail = substring(source,len-1,1);//\u4EE5S\u7ED3\u5C3E
        eq = strcmp( tail,tail_); //eq=0\u76F8\u7B49, \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570 , \u5F53s1 < s2\u65F6\uFF0C\u8FD4\u56DE\u8D1F\u6570\uFF1B
        if(eq!=0){//eq
          return -3;
        }

        tailfValidStr = substring(source,len-2,1); //\u5012\u6570\u7B2C\u4E8C\u4F4D\u4E3A\u5947\u5076\u6821\u9A8C\u4F4D
        tailfValidStrToInt = atoi(tailfValidStr);
        if((tailfValidStrToInt+len)%2!=0){
            return -4;
        }

        lengthStr = substring(source,1,4); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
        lengthStrToInt = hexToDec(lengthStr);
        if(len!=lengthStrToInt){
            return -5;
        }

        firstParamLenthStr = substring(source,5,8); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
        firstParamLenthStrToInt = hexToDec(firstParamLenthStr);
        if(firstParamLenthStrToInt<=0){
            return -6;
        }

        firstParamStr = substring(source,5+8,firstParamLenthStrToInt); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
        firstParamStrToInt = hexToDec(firstParamStr);
        if(ji==1){
            return firstParamStrToInt;
        }

        secondParamLenthStr = substring(source,5+8+firstParamLenthStrToInt,8); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
        secondParamLenthStrToInt = hexToDec(secondParamLenthStr);
        if(secondParamLenthStrToInt<=0){
            return -7;
        }

        secondParamStr = substring(source,5+8+firstParamLenthStrToInt+8,secondParamLenthStrToInt); //\u4ECE\u5305\u4E2D\u83B7\u53D6\u957F\u5EA6\u4E0E\u5B9E\u9645\u957F\u5EA6\u5BF9\u6BD4
        secondParamStrToInt = hexToDec(secondParamStr);
        if(ji==2){
            return secondParamStrToInt;
        }
    }
    return -1;
}

/* \u5341\u516D\u8FDB\u5236\u6570\u8F6C\u6362\u4E3A\u5341\u8FDB\u5236\u6570 */
long hexToDec(char *source)
{
    long sum = 0;
    long t = 1;
    int i, len;

    len = strlen(source);
    for(i=len-1; i>=0; i--)
    {
        sum += t * getIndexOfSigns(*(source + i));
        t *= 16;
    }

    return sum;
}

/* \u8FD4\u56DEch\u5B57\u7B26\u5728sign\u6570\u7EC4\u4E2D\u7684\u5E8F\u53F7 */
int getIndexOfSigns(char ch)
{
    if(ch >= '0' && ch <= '9')
    {
        return ch - '0';
    }
    if(ch >= 'A' && ch <='F')
    {
        return ch - 'A' + 10;
    }
    if(ch >= 'a' && ch <= 'f')
    {
        return ch - 'a' + 10;
    }
    return -1;
}

char* substring(char* ch,int pos,int length)
{
    //\u5B9A\u4E49\u5B57\u7B26\u6307\u9488 \u6307\u5411\u4F20\u9012\u8FDB\u6765\u7684ch\u5730\u5740
    char* pch=ch;
    //\u901A\u8FC7calloc\u6765\u5206\u914D\u4E00\u4E2Alength\u957F\u5EA6\u7684\u5B57\u7B26\u6570\u7EC4\uFF0C\u8FD4\u56DE\u7684\u662F\u5B57\u7B26\u6307\u9488\u3002
    char* subch=(char*)calloc(sizeof(char),length+1);
    int i;
 //\u53EA\u6709\u5728C99\u4E0Bfor\u5FAA\u73AF\u4E2D\u624D\u53EF\u4EE5\u58F0\u660E\u53D8\u91CF\uFF0C\u8FD9\u91CC\u5199\u5728\u5916\u9762\uFF0C\u63D0\u9AD8\u517C\u5BB9\u6027\u3002
    pch=pch+pos;
//\u662Fpch\u6307\u9488\u6307\u5411pos\u4F4D\u7F6E\u3002
    for(i=0;i<length;i++)
    {
        subch[i]=*(pch++);
//\u5FAA\u73AF\u904D\u5386\u8D4B\u503C\u6570\u7EC4\u3002
    }
    subch[length]='\0';//\u52A0\u4E0A\u5B57\u7B26\u4E32\u7ED3\u675F\u7B26\u3002
    return subch;       //\u8FD4\u56DE\u5206\u914D\u7684\u5B57\u7B26\u6570\u7EC4\u5730\u5740\u3002
}

