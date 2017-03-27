package com.xy.mvp.presenter.api;

import com.xy.mvp.utils.Constant;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * anthor:Created by tianchen on 2017/3/27.
 * email:963181974@qq.com
 */

public interface ApiService {
    @GET(Constant.LOGIN)
    public Flowable<String> rxlogin(
            @Header("Cache-Control") String cacheControl,
            @Query("username")
                    String username,
            @Query("password")
                    String password,
            @Query("type")
                    int type);
}