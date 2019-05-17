package com.hongliang.retrofitdemo.httputil.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public final class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final int SUCCESS = 0;    // 成功
    private static final int FAILURE = 1;        // 失败 提示失败msg
    private static final int TOKEN_EXPIRE = 2;  // token过期
    private static final int SERVER_EXCEPTION = -1;  // 服务器异常

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


       /* MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());

        Reader reader = new InputStreamReader(inputStream, charset);

        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }*/


        try {
            return gson.fromJson(response, type);
        } finally {
            value.close();
        }


    }

//    private void verify(String json) {
//        ResponseData result = gson.fromJson(json, ResponseData.class);
//        if (result.getErrorCode() != SUCCESS) {
//            switch (result.getErrorCode()) {
//                case FAILURE:
//                case SERVER_EXCEPTION:
//                    throw new MyException(result.getErrorMsg());
//                case TOKEN_EXPIRE:
//                    throw new MyException(result.getErrorMsg());
//                default:
//                    throw new MyException(result.getErrorMsg());
//            }
//        }
//    }


}

