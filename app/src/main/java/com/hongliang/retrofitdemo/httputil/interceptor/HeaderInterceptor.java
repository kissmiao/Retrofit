package com.hongliang.retrofitdemo.httputil.interceptor;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

//https://blog.csdn.net/wuyinlei/article/details/57087872
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
   /*

        Gson gson =  new GsonBuilder().disableHtmlEscaping().create();
        Map<String, String> header = DeviceInfo.getDeviceInfoMap();
        Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        String stringheaders = header.get("headerInfos");


        RequestBody requestBody = originalRequest.body();
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        String oldParamsJson = buffer.readUtf8();
        Log.i("LOG","oldParamsJson"+oldParamsJson);
        //    HashMap<String, Object> rootMap = new HashMap<>();
        //    rootMap = gson.fromJson(oldParamsJson, HashMap.class);  //原始参数
        String stringParams = gson.toJson(oldParamsJson);
        Log.i("LOG","stringParams"+stringParams);

        String sign = EncryptUtils.encryptMD5ToString(stringheaders + stringParams + "mosi5123123123").toLowerCase();
        builder.addHeader("sign", sign.toLowerCase());*/
        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
