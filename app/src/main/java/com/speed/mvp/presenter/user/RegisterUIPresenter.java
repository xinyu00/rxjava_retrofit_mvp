package com.speed.mvp.presenter.user;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.speed.mvp.dagger.PerActivity;
import com.speed.mvp.entity.ResponseInfo;
import com.speed.mvp.net.api.NetBack;
import com.speed.mvp.net.UserNet;
import com.speed.mvp.net.api.Api;
import com.speed.mvp.net.api.ApiService;
import com.speed.mvp.net.api.HostType;
import com.speed.mvp.ui.user.RegisterUI;
import com.speed.mvp.utils.UrlUtils;

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
    public RegisterUIPresenter(Activity activity) {
        this.activity = (RegisterUI)activity;
        api = Api.getDefault(HostType.TYPE1, UrlUtils.BASEURL);
        subscriptions = new ArrayList<>();
    }

    /**
     * 用户注册
     */
    public void register(final String phone, final String password) {
        UserNet.getInstance(api).getRegister(phone, password, 0, 0, new NetBack() {
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
