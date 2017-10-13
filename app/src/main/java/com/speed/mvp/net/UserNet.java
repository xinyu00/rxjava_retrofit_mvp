package com.speed.mvp.net;

import com.speed.mvp.net.api.Api;
import com.speed.mvp.net.api.ApiService;
import com.speed.mvp.net.api.NetBack;
import com.speed.mvp.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tianchen on 2017/7/7.
 * email:963181974@qq.com
 */

public class UserNet {

    private ApiService api;

    public static UserNet getInstance(ApiService api) {
        UserNet userNet = new UserNet();
        userNet.api = api;
        return userNet;
    }

    public void getLogin(String username, String password, int type, final NetBack back) {
        api.rxlogin(Api.getCacheControl(), username, password, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        back.onFailed(e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("已经停止");
                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        back.onSubscribe(s);
                    }

                    @Override
                    public void onNext(String s) {
                        back.onSuccess(s);
                    }
                });
    }

    public void getRegister(String phone, String password, int role, int type, final NetBack back) {
        api.register(phone, password, role, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        back.onFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        back.onSubscribe(s);
                    }

                    @Override
                    public void onNext(String s) {
                        back.onSuccess(s);
                    }
                });
    }


}
