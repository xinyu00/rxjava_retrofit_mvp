package com.xy.mvp.presenter.user;

import android.text.TextUtils;
import android.util.Log;

import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.ApiService;
import com.xy.mvp.presenter.api.HostType;
import com.xy.mvp.ui.user.LoginUI;
import com.xy.mvp.utils.ConstantUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */
@PerActivity
public class LoginUIPresenter{
    private ApiService api;
    private LoginUI activity;
    private List<Subscription> subscriptions;
    @Inject
    public LoginUIPresenter(LoginUI activity) {
        this.activity = activity;
        api = Api.getDefault(HostType.TYPE1, ConstantUtils.BASEURL);
        subscriptions = new ArrayList<>();
    }

    /**
     * 用户登录
     */
    public void login(String username, String password) {
        api.rxlogin(Api.getCacheControl(), username, password, 3)
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
                        s.request(Long.MAX_VALUE);
                        subscriptions.add(s);
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

    public void cancleNet(){
        for (Subscription subscription : subscriptions){
            subscription.cancel();
        }
    }
}
