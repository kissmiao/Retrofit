package com.hongliang.retrofitdemo.httputil;


import com.hongliang.retrofitdemo.BaseApplication;

import java.io.File;

public class BuildConfigs {


    public static final int DEFAULT_CACHE_SIZE = 150 * 1024 * 1024; // 50 MiB
    /*超时时间-默认10秒*/
    public static final int DEFAULT_CONNECT_TIMEOUT = 20;
    public static final int DEFAULT_READ_TIMEOUT = 20;
    public static final int DEFAULT_WRITE_TIMEOUT = 20;
    /*有网情况下的本地缓存时间默认60秒*/
    public static final int DEFAULT_COOKIE_NETWORK_TIME = 0;
    /*无网络的情况下本地缓存时间默认30天*/
    public static final int DEFAULT_COOKIE_NO_NETWORK_TIME = 24 * 60 * 60 * 30;


    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";


}
