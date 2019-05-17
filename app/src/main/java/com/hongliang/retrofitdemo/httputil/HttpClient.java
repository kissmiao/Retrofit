package com.hongliang.retrofitdemo.httputil;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongliang.retrofitdemo.BaseApplication;
import com.hongliang.retrofitdemo.httputil.converter.MyGsonConverterFactory;
import com.hongliang.retrofitdemo.httputil.interceptor.CacheInterceptor;
import com.hongliang.retrofitdemo.httputil.interceptor.CacheNetWorkInterceptor;
import com.hongliang.retrofitdemo.httputil.interceptor.HeaderInterceptor;
import com.hongliang.retrofitdemo.httputil.interceptor.NetWorkInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
//https://www.jianshu.com/p/b1979c25634f
//https://www.jianshu.com/p/308f3c54abdd
//https://www.jianshu.com/p/73216939806a

//https://blog.csdn.net/u010724819/article/details/78123397
//https://www.jianshu.com/p/3411423e903f

//理解整个流程，带着问题看
//每一个代码的入口都看看
//判断每一个主要类都是在干嘛
//协同博客一起查看

public class HttpClient {

    private Api api;
//    addNetworkInterceptor添加的是网络拦截器Network Interfacetor它会在request和response时分别被调用一次；
//    addInterceptor添加的是应用拦截器Application Interceptor他只会在response被调用一次。


    private HttpClient() {
        Cache cache = new Cache(new File(BuildConfigs.PATH_CACHE), BuildConfigs.DEFAULT_CACHE_SIZE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new NetWorkInterceptor())
                .addInterceptor(new HeaderInterceptor())//设置Header
                .addNetworkInterceptor(new CacheNetWorkInterceptor())//设置缓存
                .addInterceptor(new CacheInterceptor())
                .cache(cache)
                .connectTimeout(BuildConfigs.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(BuildConfigs.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(BuildConfigs.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .retryOnConnectionFailure(true);//错误重连
        //  okhttp3.internal.Util.checkDuration();




        OkHttpClient client = builder.build();


        Gson gson = new GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConst.BASE_URL)
                .client(client)
                .addConverterFactory(MyGsonConverterFactory.create(gson))
                //   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //   .callbackExecutor()
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

}
