package com.obito.helloandroidjni;


public class NativeObject {


    private NativeObject() {
    }

    private static class SingletonHolder{
        static NativeObject sInstance = new NativeObject();
    }
    public static NativeObject getInstance() {

        return SingletonHolder.sInstance;
    }


    static {
        System.loadLibrary("hello-android-jni");
    }

    public native String getMsgFromJni();
}
