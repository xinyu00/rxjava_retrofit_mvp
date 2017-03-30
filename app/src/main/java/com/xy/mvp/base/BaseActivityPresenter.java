package com.xy.mvp.base;

/**
 * anthor:Created by tianchen on 2017/3/30.
 * email:963181974@qq.com
 * Presenter层基类
 */

public abstract class BaseActivityPresenter<T extends BaseActivity> {
    protected T activity;

    public BaseActivityPresenter(T activity) {
        this.activity = activity;
    }

}
