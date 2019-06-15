package com.hongliang.retrofitutils;

import com.hongliang.retrofitutils.util.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;


public class RetrofitManage {


    private static final Map<String, Retrofit> retrofitMap = new ConcurrentHashMap<>(2);

    private RetrofitManage() {

    }

    private static volatile Retrofit retrofit;

    private static final class RetrofitManageHolder {
        private final static RetrofitManage retrofitManage = new RetrofitManage();
    }

    public static RetrofitManage getInstents() {
        return RetrofitManageHolder.retrofitManage;
    }

    public RetrofitManage addRetrofit(Retrofit retrofit, String retrofitName) {
        retrofitMap.put(retrofitName, retrofit);
        return this;
    }

    public RetrofitManage addRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
        return this;
    }


    public <T> T onCreate(Class<T> service) {
        Utils.checkState(retrofit != null, "retrofit == null");
        return retrofit.create(service);
    }

    public <T> T onCreate(Class<T> service, String retrofitName) {
        Retrofit retrofit = retrofitMap.get(retrofitName);
        Utils.checkState(retrofit != null,
                String.format("retrofit named with '%s' was not found , have you put it in retrofitName ?", retrofitName));
        return retrofit.create(service);
    }


}
