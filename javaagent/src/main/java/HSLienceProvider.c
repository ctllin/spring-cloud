#include "HSLienceProvider.h"
JNIEXPORT jstring JNICALL Java_HSLienceProvider_getHspk(JNIEnv *env, jobject jj)
{
	char *str = "Hg8ygBWh2MTJt8r4kg+styJjhqGTgyPkuJygOgERg8d4KqpdkDUtlPMXR4eGD5k2mv/qsTATaPF3dtlbvmpe9+iGal8IDP/XUCh+nvyUkxiNA7lpM7gxYYT3zWVcuq7E1zTGYwyIW8V57H/4eHvM8NlE8EHgBunfmhKdE2TBx4piilp9NNqnkIMlLH3VJ+G8rh3kQrbOXIPB/jLISjsnBNuqBNO1MQWqQTViOtZ6PZPB8J9h8vlY3jxHGA0OUy3axnephYa6dXRtIQECdg529SpMbbtOTpKZiX10X1NG1nA/+7Rh4AiemTylZBAL6M4T4+F4o6hE2rpq84hYKziRhg==";
	return (*env)->NewStringUTF(env,str);
}
