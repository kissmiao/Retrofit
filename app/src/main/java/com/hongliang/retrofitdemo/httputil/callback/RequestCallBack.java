package com.hongliang.retrofitdemo.httputil.callback;


import com.hongliang.retrofitdemo.httputil.bean.BaseBean;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RequestCallBack<T> extends BaseCallback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (200 == response.code()) {
            onAfter(call);
            int code = ((BaseBean) response.body()).getErrorCode();
            if (code != 0) {
                onFail(call, new Throwable(((BaseBean) response.body()).getErrorMsg()), response);
            } else {
                onSuccessful(call, response);
            }
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }


}
