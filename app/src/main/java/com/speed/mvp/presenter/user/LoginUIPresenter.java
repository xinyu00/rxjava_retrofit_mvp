package com.speed.mvp.presenter.user;

import android.text.TextUtils;
import android.util.Log;

import com.speed.mvp.dagger.PerActivity;
import com.speed.mvp.net.api.NetBack;
import com.speed.mvp.net.UserNet;
import com.speed.mvp.net.api.Api;
import com.speed.mvp.net.api.ApiService;
import com.speed.mvp.net.api.HostType;
import com.speed.mvp.ui.user.LoginUI;
import com.speed.mvp.utils.UrlUtils;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */
@PerActivity
public class LoginUIPresenter {
    private ApiService api;
    private LoginUI activity;
    private List<Subscription> subscriptions;

    @Inject
    public LoginUIPresenter(LoginUI activity) {
        this.activity = activity;
        api = Api.getDefault(HostType.TYPE1, UrlUtils.BASEURL);
        subscriptions = new ArrayList<>();
    }

    /**
     * 用户登录
     */
    public void login(String username, String password) {
        UserNet.getInstance(api).getLogin(username, password, 3, new NetBack() {
            @Override
            public void onSuccess(String s) {
                if (!TextUtils.isEmpty(s)) {
                    Log.e("json串", s);
                    activity.success();
                } else {

                }
            }

            @Override
            public void onFailed(Throwable e) {
                Log.e("异常", e.toString());
                activity.failed();
            }

            @Override
            public void onSubscribe(Subscription s) {
                subscriptions.add(s);
            }
        });
    }

    public void cancleNet() {
        for (Subscription subscription : subscriptions) {
            subscription.cancel();
        }
    }
}