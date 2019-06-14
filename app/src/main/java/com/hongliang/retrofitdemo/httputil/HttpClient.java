package com.hongliang.retrofitdemo.httputil;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongliang.retrofitdemo.ExecutorCallAdapterFactory;
import com.hongliang.retrofitdemo.httputil.converter.MyGsonConverterFactory;
import com.hongliang.retrofitdemo.httputil.interceptor.CacheInterceptor;
import com.hongliang.retrofitdemo.httputil.interceptor.HeaderInterceptor;
import com.hongliang.retrofitdemo.httputil.interceptor.NetWorkInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpClient {
    private Api api;

    private static OkHttpClient client;
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private HttpClient() {
        Cache cache = new Cache(new File(BuildConfigs.PATH_CACHE), BuildConfigs.DEFAULT_CACHE_SIZE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new NetWorkInterceptor())
                .addInterceptor(new HeaderInterceptor())//设置Header
                .addInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(BuildConfigs.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(BuildConfigs.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(BuildConfigs.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .retryOnConnectionFailure(true);//错误重连

        client = builder.build();

        Gson gson = new GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .client(client)
                .addConverterFactory(MyGsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addCallAdapterFactory(ExecutorCallAdapterFactory.INSTANCE)
                .build();
        api = retrofit.create(Api.class);


    }


    private static class HttpClientHolder {
        private static final HttpClient INSTANCE = new HttpClient();
    }


    public static HttpClient getInstance() {
        return HttpClientHolder.INSTANCE;
    }


    public Api getApiService() {
        return api;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }


}
