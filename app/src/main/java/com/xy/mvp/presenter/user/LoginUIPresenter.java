package com.xy.mvp.presenter.user;

import android.text.TextUtils;
import android.util.Log;

import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.net.NetBack;
import com.xy.mvp.net.UserNet;
import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.ApiService;
import com.xy.mvp.presenter.api.HostType;
import com.xy.mvp.ui.user.LoginUI;
import com.xy.mvp.utils.UrlUtils;

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