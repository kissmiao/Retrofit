package com.hongliang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hongliang.retrofitdemo.httputil.Api;
import com.hongliang.retrofitdemo.httputil.callback.AcceptConsumer;
import com.hongliang.retrofitdemo.httputil.callback.RequestCallBack;
import com.hongliang.retrofitdemo.login.BaseBean;
import com.hongliang.retrofitdemo.login.LoginBean;
import com.hongliang.retrofitutils.RetrofitManage;
import com.hongliang.retrofitutils.callback.ConsumerError;
import com.hongliang.retrofitutils.util.CallManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 玩安卓部分功能需要Cookie本地持久化
 * https://www.jianshu.com/p/b1ab67ebdfca
 * https://www.jianshu.com/p/6a88c4b4bb26
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 登录
     */
    private Button mLogin;


    private Disposable disposable;
    /**
     * 使用RxJava登录
     */
    private Button mLogin2;
    /**
     * 重新Call可添加tag
     */
    private Button mLogin3;
    /**
     * 注册
     */
    private Button mRegister;
    /**
     * 取消请求
     */
    private Button mClean;


    private int tag = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mLogin2 = (Button) findViewById(R.id.login2);
        mLogin2.setOnClickListener(this);
        mLogin3 = (Button) findViewById(R.id.login3);
        mLogin3.setOnClickListener(this);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        mClean = (Button) findViewById(R.id.clean);
        mClean.setOnClickListener(this);
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
            case R.id.login2:
                onLogin2();
                break;
            case R.id.login3:
                onLogin3();
                break;
            case R.id.clean:
                CallManager.getInstance().cancel(tag);
                break;
        }
    }


    private void onRegister() {
        Map<String, String> map = new HashMap();
        map.put("username", "13720232958");
        map.put("password", "123qwe");
        map.put("repassword", "123qwe");

        RetrofitManage.getInstents().onCreate(Api.class)
                .register(map)
                .enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
                    @Override
                    public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                        Toast.makeText(MainActivity.this, "注册成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
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

        RetrofitManage.getInstents().onCreate(Api.class)
                .login(map)
                .enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
                    @Override
                    public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                        Toast.makeText(MainActivity.this, "登录成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
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


    private void onLogin2() {
        Map<String, String> map = new HashMap();
        map.put("username", "13720232954");
        map.put("password", "123qwe");
        disposable = RetrofitManage.getInstents().onCreate(Api.class)
                .logins(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AcceptConsumer<LoginBean>() {
                    @Override
                    public void onSuccessful(BaseBean<LoginBean> loginBeanBaseBean) {
                        Toast.makeText(MainActivity.this, "登录成功：" + loginBeanBaseBean.getData().getUsername(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(BaseBean<LoginBean> loginBeanBaseBean) {

                        Toast.makeText(MainActivity.this, "失败：" + loginBeanBaseBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }, new ConsumerError<Throwable>() {
                    @Override
                    public void onError(int errorCode, String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void onLogin3() {
        Map<String, String> map = new HashMap();
        map.put("username", "13720232954");
        map.put("password", "123qwe");

        RetrofitManage.getInstents().onCreate(Api.class)
                .login2(map)
                .tag(tag)
                .enqueue(new RequestCallBack<BaseBean<LoginBean>>() {
                    @Override
                    public void onSuccessful(Call<BaseBean<LoginBean>> call, Response<BaseBean<LoginBean>> response) {
                        Toast.makeText(MainActivity.this, "登录成功：" + response.body().getData().getUsername(), Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
