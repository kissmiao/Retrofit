package com.hongliang.retrofitdemo;

import android.app.Application;

import android.util.Log;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("TAG", "---BaseApplication");
        instance = this;

    }

    public static BaseApplication getInstance() {
        return instance;
    }


}
