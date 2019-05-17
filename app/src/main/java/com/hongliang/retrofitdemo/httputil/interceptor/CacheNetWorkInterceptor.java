package com.hongliang.retrofitdemo.httputil.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheNetWorkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
          /*String cacheControl = request.header("Cache-Control");
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60";
            }*/
        int maxAge = 60;
        return response.newBuilder()
                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build();


    }
}
