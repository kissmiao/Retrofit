package com.hongliang.retrofitutils;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;

public interface OkCall<T> extends retrofit2.Call<T> {
    @Override
    Response<T> execute() throws IOException;

    @Override
    void enqueue(Callback<T> callback);

    OkCall<T> tag(Object obj);

    @Override
    boolean isExecuted();

    @Override
    void cancel();

    @Override
    boolean isCanceled();

    @Override
    OkCall<T> clone();

    @Override
    Request request();


}
