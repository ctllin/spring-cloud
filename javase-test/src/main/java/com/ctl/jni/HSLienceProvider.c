#include "HSLienceProvider.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/* \u5341\u516D\u8FDB\u5236\u6570\u8F6C\u6362\u4E3A\u5341\u8FDB\u5236\u6570 */
long hexToDec(char *source);

/* \u8FD4\u56DEch\u5B57\u7B26\u5728sign\u6570\u7EC4\u4E2D\u7684\u5E8F\u53F7 */
int getIndexOfSigns(char ch);
/* \u5B57\u7B26\u4E32\u622A\u53D6 */
char* substring(char* str,int pos,int length);


JNIEXPORT jstring JNICALL Java_HSLienceProvider_getHspk(JNIEnv *env, jobject jj)
{
	char *str = "Hg8ygBWh2MTJt8r4kg+styJjhqGTgyPkuJygOgERg8d4KqpdkDUtlPMXR4eGD5k2mv/qsTATaPF3dtlbvmpe9+iGal8IDP/XUCh+nvyUkxiNA7lpM7gxYYT3zWVcuq7E1zTGYwyIW8V57H/4eHvM8NlE8EHgBunfmhKdE2TBx4piilp9NNqnkIMlLH3VJ+G8rh3kQrbOXIPB/jLISjsnBNuqBNO1MQWqQTViOtZ6PZPB8J9h8vlY3jxHGA0OUy3axnephYa6dXRtIQECdg529SpMbbtOTpKZiX10X1NG1nA/+7Rh4AiemTylZBAL6M4T4+F4o6hE2rpq84hYKziRhg==";
	return (*env)->NewStringUTF(env,str);
}
JNIEXPORT jint JNICALL Java_HSLienceProvider_getValue(JNIEnv *env, jobject jj, jstring js, jint ji){
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

