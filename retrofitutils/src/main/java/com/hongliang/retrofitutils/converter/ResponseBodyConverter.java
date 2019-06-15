package com.hongliang.retrofitutils.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public final class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {


    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final Type type;

    public ResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson = gson;
        this.adapter = adapter;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            return gson.fromJson(response, type);
        } finally {
            value.close();
        }
    }


}

