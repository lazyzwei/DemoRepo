LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := hello-android-jni
LOCAL_SRC_FILES := hello-android-jni.cpp

include $(BUILD_SHARED_LIBRARY)