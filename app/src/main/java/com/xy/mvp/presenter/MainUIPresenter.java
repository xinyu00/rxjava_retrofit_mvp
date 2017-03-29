package com.xy.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.ApiService;
import com.xy.mvp.presenter.api.HostType;
import com.xy.mvp.ui.MainUI;
import com.xy.mvp.utils.Constant;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

public class MainUIPresenter {
    private MainUI activity;
    private final ApiService api;

    @Inject
    public MainUIPresenter(MainUI activity) {
        this.activity = activity;
        api = Api.getDefault(HostType.TYPE1, Constant.BASEURL);
    }

    /**
     * 用户登录rxjava版本
     */
    public void login(String username, String password) {
        api.rxlogin(Api.getCacheControl(),username, password, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.e("异常", e.toString());
                        activity.failed();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            Log.e("json串", s);
                            activity.success();
                        } else {

                        }
                    }
                });
    }


}
