package com.hongliang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hongliang.retrofitdemo.httputil.HttpClient;
import com.hongliang.retrofitdemo.httputil.bean.BaseBean;
import com.hongliang.retrofitdemo.httputil.callback.RequestCallBack;
import com.hongliang.retrofitdemo.login.LoginBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 玩安卓部分功能需要Cookie本地持久化
 * https://www.jianshu.com/p/b1ab67ebdfca
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button register;
    /**
     * 登录
     */
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                onRegister();
                break;

            case R.id.login:
                onLogin();
                break;
        }
    }


    private void onRegister() {
        Map<String, String> map = new HashMap();
        map.put("username", "13720232954");
        map.put("password", "123qwe");
        map.put("repassword", "123qwe");

        HttpClient.getInstance().getApiService().register(map).enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
            @Override
            public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                Toast.makeText(MainActivity.this, "请求成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onFail(Call<BaseBean<LoginBean>> call, Throwable t, Response<BaseBean<LoginBean>> response) {
                super.onFail(call, t, response);
            }

            @Override
            protected void onAfter(Call<BaseBean<LoginBean>> call) {
                super.onAfter(call);
            }
        });
    }


    private void onLogin() {
        Map<String, String> map = new HashMap();
        map.put("username", "13720232954");
        map.put("password", "123qwe");

        HttpClient.getInstance().getApiService().login(map).enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
            @Override
            public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                Toast.makeText(MainActivity.this, "请求成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onFail(Call<BaseBean<LoginBean>> call, Throwable t, Response<BaseBean<LoginBean>> response) {
                super.onFail(call, t, response);
            }

            @Override
            protected void onAfter(Call<BaseBean<LoginBean>> call) {
                super.onAfter(call);
            }
        });
    }


}
