package com.hongliang.retrofitdemo.httputil.interceptor;


import com.hongliang.retrofitdemo.BaseApplication;
import com.hongliang.retrofitdemo.NetWorkUtils;

import java.io.IOException;
import java.net.SocketException;

import okhttp3.Interceptor;
import okhttp3.Response;


public class NetWorkInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetWorkUtils.isNetWorkAvailable(BaseApplication.getInstance())) {

            throw new SocketException("网络未连接！");
        } else {
            return chain.proceed(chain.request());
        }


    }


}
