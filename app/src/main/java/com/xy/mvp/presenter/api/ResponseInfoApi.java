package com.xy.mvp.presenter.api;

import com.xy.mvp.utils.Constant;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public interface ResponseInfoApi {
    //    @GET(Constant.LOGIN)
//    Call<String> login(       //普通版
//    @Query("username")// 参数的名字
//            String username, // 该参数的值
//    @Query("password")
//    String password,
//    @Query("type")
//    int type);
    @GET(Constant.LOGIN)
    public Flowable<String> rxlogin(        //rxjava版本
                                            @Query("username")// 参数的名字
                                                      String username, // 该参数的值
                                            @Query("password")
                                                      String password,
                                            @Query("type")
                                                      int type);
}
