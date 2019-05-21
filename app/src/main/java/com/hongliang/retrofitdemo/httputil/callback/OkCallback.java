package com.hongliang.retrofitdemo.httputil.callback;

import android.util.Log;
import android.widget.Toast;

import com.hongliang.retrofitdemo.BaseApplication;
import com.hongliang.retrofitdemo.httputil.HttpErrorConstants;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class OkCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        //这里可以对其他Code 吗码进行监听，做特殊处理
        if (200 == response.code()) {
            onAfter(call);
            onSuccessful(call, response);
        } else {
            onFail(call, new Throwable(response.message()), response);
        }
    }

    public abstract void onSuccessful(Call<T> call, Response<T> response);

    @Override
    public void onFailure(Call<T> call, Throwable exception) {

        String errorMsg;
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            //网络未连接
            errorMsg = HttpErrorConstants.ERR_UNKNOWNHOSTEXCEPTION_ERROR;
        } else if (exception instanceof SocketTimeoutException) {
            //连接超时
            errorMsg = HttpErrorConstants.ERR_SOCKETTIMEOUTEXCEPTION_ERROR;
        } else {
            //其他网络异常
            errorMsg = HttpErrorConstants.ERR_NETEXCEPTION_ERROR;
        }
        onFail(call, new Throwable(errorMsg), null);
    }

    protected void onFail(Call<T> call, Throwable t, Response<T> response) {
        onAfter(call);

        Log.i("YouShuSDK", "init failed : " + t.getMessage());
        Toast.makeText(BaseApplication.getInstance(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * 用于关闭Dialog操作等，标志整个请求完成
     */
    protected void onAfter(Call<T> call) {

    }


}
