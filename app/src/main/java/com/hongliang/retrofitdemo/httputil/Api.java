package com.hongliang.retrofitdemo.httputil;


import com.hongliang.retrofitdemo.httputil.bean.BaseBean;
import com.hongliang.retrofitdemo.login.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
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
    Call<BaseBean<LoginBean>> register(@QueryMap Map<String ,String> post);


    @POST("user/login")
    Call<BaseBean<LoginBean>> login(@QueryMap Map<String ,String> post);


    @POST("user/login")
    Observable<BaseBean<LoginBean>> logins(@QueryMap Map<String, String> post);



    @POST("user/login")
    OkCall<BaseBean<LoginBean>> login2(@QueryMap Map<String ,String> post);

}
