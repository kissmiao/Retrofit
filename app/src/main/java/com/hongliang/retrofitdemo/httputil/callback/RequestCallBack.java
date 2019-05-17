package com.hongliang.retrofitdemo.httputil.callback;


import com.hongliang.retrofitdemo.httputil.bean.ResponseData;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RequestCallBack<T extends ResponseData> extends OkCallback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (200 == response.code()) {
            onAfter(call);
            int code = ((ResponseData) response.body()).getHead().getCode();
            if (code == 601 || code == 602) {

                return;
            } else {
                onSuccessful(call, response);
            }
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }


}
