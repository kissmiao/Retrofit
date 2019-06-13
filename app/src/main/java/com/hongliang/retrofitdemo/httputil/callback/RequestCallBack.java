package com.hongliang.retrofitdemo.httputil.callback;


import com.hongliang.retrofitdemo.httputil.bean.BaseBean;

import retrofit2.Call;
import retrofit2.Response;

public abstract class RequestCallBack<T extends BaseBean> extends OkCallback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (200 == response.code()) {
            onAfter(call);
            String code = ((BaseBean) response.body()).getErrorCode();
            if (!code.equals("0")) {
                onFail(call, new Throwable(((BaseBean) response.body()).getErrorMsg()), response);
            } else {
                onSuccessful(call, response);
            }
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }


}
