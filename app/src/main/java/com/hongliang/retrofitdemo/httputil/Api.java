package com.hongliang.retrofitdemo.httputil;


import com.hongliang.retrofitdemo.httputil.bean.ResponseData;
import com.hongliang.retrofitdemo.login.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Api {


    /**
     * 得追加在Url后面，后台能识别
     * @param post
     * @return
     */
    @POST("user/register")
    Call<ResponseData<LoginBean>> register(@QueryMap Map<String ,String> post);

}
