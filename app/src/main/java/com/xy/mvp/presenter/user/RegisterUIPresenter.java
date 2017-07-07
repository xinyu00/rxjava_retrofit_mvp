package com.xy.mvp.presenter.user;

import com.alibaba.fastjson.JSON;
import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.entity.ResponseInfo;
import com.xy.mvp.net.NetBack;
import com.xy.mvp.net.UserNet;
import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.ApiService;
import com.xy.mvp.presenter.api.HostType;
import com.xy.mvp.ui.user.RegisterUI;
import com.xy.mvp.utils.UrlUtils;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * anthor:Created by tianchen on 2017/4/23.
 * email:963181974@qq.com
 */
@PerActivity
public class RegisterUIPresenter {
    private ApiService api;
    private RegisterUI activity;
    private List<Subscription> subscriptions;

    @Inject
    public RegisterUIPresenter(RegisterUI activity) {
        this.activity = activity;
        api = Api.getDefault(HostType.TYPE1, UrlUtils.BASEURL);
        subscriptions = new ArrayList<>();
    }

    /**
     * 用户注册
     */
    public void register(final String phone, final String password) {
        UserNet.getInstance(api).getRegister(phone,password,0,0,new NetBack(){
            @Override
            public void onSuccess(String s) {
                ResponseInfo info = JSON.parseObject(s, ResponseInfo.class);
                if ("0".equals(info.code)) {
                    activity.success(info.msg);
                } else {
                    activity.failed(info.msg);
                }
            }

            @Override
            public void onFailed(Throwable e) {
                activity.failed(e.toString());
            }

            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
                subscriptions.add(s);
            }
        });
    }

    public void clearNet() {
        for (Subscription s : subscriptions) {
            s.cancel();
        }
    }
}
