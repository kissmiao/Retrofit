package com.hongliang.retrofitutils.converter;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public final class RequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType
            .parse("application/json; charset=UTF-8");
    static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;


    RequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
        System.out.println("#IRequestBodyConverter初始化#");

    }

    @Override
    public RequestBody convert(T value) throws IOException {

        if(value instanceof String){
            return RequestBody.create(MEDIA_TYPE, (String) value);
        }
        return RequestBody.create(MEDIA_TYPE, gson.toJson(value));
    }


}
