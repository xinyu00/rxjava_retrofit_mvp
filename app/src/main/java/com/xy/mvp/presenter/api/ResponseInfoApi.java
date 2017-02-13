package com.xy.mvp.presenter.api;

import com.xy.mvp.model.net.ResponseInfo;
import com.xy.mvp.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public interface ResponseInfoApi {
    @GET(Constant.LOGIN)
    Call<ResponseInfo> login(
            @Query("username")// 参数的名字
                    String username, // 该参数的值
            @Query("password")
                    String password,
            @Query("type")
                    int type);
}
