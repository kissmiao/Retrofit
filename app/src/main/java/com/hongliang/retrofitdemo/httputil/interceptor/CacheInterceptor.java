package com.hongliang.retrofitdemo.httputil.interceptor;



import com.hongliang.retrofitdemo.BaseApplication;
import com.hongliang.retrofitdemo.httputil.BuildConfigs;
import com.hongliang.retrofitdemo.httputil.NetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtils.isNetWorkAvailable(BaseApplication.getInstance())) {//没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetWorkUtils.isNetWorkAvailable(BaseApplication.getInstance())) {//有网情况下，从服务器获取
            int maxAge = BuildConfigs.DEFAULT_COOKIE_NETWORK_TIME;
        // 有网络时, 缓存最大保存时长为60s
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {//没网情况下，一律从缓存获取
            // 无网络时，设置超时为30天
            int maxStale = BuildConfigs.DEFAULT_COOKIE_NO_NETWORK_TIME;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }

}
