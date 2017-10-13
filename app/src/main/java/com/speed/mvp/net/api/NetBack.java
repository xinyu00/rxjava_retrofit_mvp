package com.speed.mvp.net.api;

import org.reactivestreams.Subscription;

/**
 * Created by tianchen on 2017/7/7.
 */

public interface NetBack {
    void onSuccess(String s);
    void onFailed(Throwable e);
    void onSubscribe(Subscription s);
}

