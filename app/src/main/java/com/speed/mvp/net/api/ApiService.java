package com.speed.mvp.net.api;

import com.speed.mvp.utils.UrlUtils;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */

public interface ApiService {
    @GET(UrlUtils.USER)
    Flowable<String> rxlogin(
            @Header("Cache-Control") String cacheControl,
            @Query("username")
                    String username,
            @Query("password")
                    String password,
            @Query("type")
                    int type);

    @GET(UrlUtils.USER)
    Flowable<String> register(
            @Query("phone")
                    String phone,
            @Query("password")
                    String password,
            @Query("role")
                    int role,
            @Query("type")
                    int type);

    @GET(UrlUtils.TAOBAOURL)
    Flowable<String> getIp(
            @Query("ip")
                    String ip);

}
