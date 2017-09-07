#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_obito_helloandroidjni_NativeObject_getMsgFromJni(JNIEnv *env, jobject instance) {
//    return (*env)->NewStringUTF(env, "returnValue");
    return env->NewStringUTF("returnValue");
}