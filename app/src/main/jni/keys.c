#include <jni.h>

JNIEXPORT jstring JNICALL
Java_net_callofdroidy_gardener_MainActivity_getGooglePlaceApiKey(JNIEnv *env, jobject instance) {

    return (*env)->  NewStringUTF(env, "TmF0aXZlNWVjcmV0UEBzc3cwcmQx");
}

JNIEXPORT jstring JNICALL
Java_net_callofdroidy_gardener_MainActivity_getOpenWeatherApiKey(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "TmF0aXZlNWVjcmV0UEBzc3cwcmQy");
}

