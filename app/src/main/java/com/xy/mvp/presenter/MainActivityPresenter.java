package com.xy.mvp.presenter;

import android.util.Log;

import com.xy.mvp.model.net.ResponseInfo;
import com.xy.mvp.presenter.api.ResponseInfoApi;
import com.xy.mvp.ui.MainActivity;
import com.xy.mvp.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public class MainActivityPresenter {
    private MainActivity activity;
    private final ResponseInfoApi api;

    public MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
        //网络访问
        // 第一步：创建Builder，指定baseUrl和数据解析工具
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constant.BASEURL);
        builder.addConverterFactory(GsonConverterFactory.create());
        //第二步 创建Retrofit
        Retrofit retrofit = builder.build();

        //第三步 指定请求方式（get或post）和参数，通过定义接口的形式指定
        //第四步 将Retrofit和第三步的联网参数联系起来
        api = retrofit.create(ResponseInfoApi.class);
    }

    /**
     * 用户登录
     */
    public void login(String username, String password) {
//        Call<ResponseInfo> call = api.login(username, password, 3);
        Call<ResponseInfo> call = api.login(username, password,3);
        call.enqueue(new Callback<ResponseInfo>() {
            @Override
            public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
                Log.e("返回体1",response.body().data);
                //处理服务器回复内容
                if (response != null) {
                    if (response.isSuccessful()) {
                        //登录成功
                        activity.success();
                    } else {
                        //错误提示
                    }
                } else {
                    onFailure(call, new RuntimeException("服务器忙请稍后重试"));
                }
            }

            @Override
            public void onFailure(Call<ResponseInfo> call, Throwable t) {
                //异常处理 登录失败
                Log.e("返回体2",call.toString());
                activity.failed();
            }
        });
    }

}
