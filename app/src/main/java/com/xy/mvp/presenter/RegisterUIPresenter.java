package com.xy.mvp.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.model.net.ResponseInfo;
import com.xy.mvp.presenter.api.Api;
import com.xy.mvp.presenter.api.ApiService;
import com.xy.mvp.presenter.api.HostType;
import com.xy.mvp.ui.RegisterUI;
import com.xy.mvp.utils.Constant;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.xy.mvp.utils.NetWorkUtils.getIPAddress;

/**
 * anthor:Created by tianchen on 2017/4/23.
 * email:963181974@qq.com
 */
@PerActivity
public class RegisterUIPresenter  {
    private ApiService api;
    private RegisterUI activity;
    @Inject
    public RegisterUIPresenter(RegisterUI activity) {
        this.activity = activity;
        api = Api.getDefault(HostType.TYPE1, Constant.BASEURL);
    }

    /**
     * 用户注册
     */
    public void register(String phone, String password) {
        api.register(phone, password,getIPAddress(activity),0, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        activity.failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(String s) {
                        ResponseInfo info = JSON.parseObject(s,ResponseInfo.class);
                        if ("0".equals(info.code)){
                            activity.success(info.msg);
                        }else {
                            activity.failed(info.msg);
                        }
                    }
                });
    }
}
