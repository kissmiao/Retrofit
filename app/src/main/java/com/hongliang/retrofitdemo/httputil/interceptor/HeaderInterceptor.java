package com.hongliang.retrofitdemo.httputil.interceptor;


import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

//        Map<String, String> header = DeviceInfo.getDeviceInfoMap();
//        String stringheaders = header.get("headerInfos");
//        builder.addHeader("headerInfos", stringheaders);
//
//        RequestBody requestBody = originalRequest.body();
//        Buffer buffer = new Buffer();
//        requestBody.writeTo(buffer);
//        String oldParamsJson = buffer.readUtf8();
//        String sign = EncryptUtils.encryptMD5ToString(stringheaders + oldParamsJson + originalRequest.headers().get("key")).toLowerCase();
//        builder.addHeader("sign", sign.toLowerCase());
//        builder.removeHeader("key");

        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        requestBuilder.tag("");
        return chain.proceed(request);
    }
}
