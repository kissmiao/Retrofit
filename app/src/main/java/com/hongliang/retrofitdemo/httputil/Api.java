package com.hongliang.retrofitdemo.httputil;


import com.hongliang.retrofitdemo.httputil.bean.InitBean;
import com.hongliang.retrofitdemo.httputil.bean.ResponseData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//https://blog.csdn.net/wzl_show/article/details/76169501
public interface Api {


//    @POST("init")
//    Call<InitBean> oninit(@Body Map<String, String> post);

    /**
     * 初始化
     * @param post
     * @return
     */
    @POST("init")
    Call<ResponseData<InitBean>> oninit(@Body Map<String, String> post);
}
