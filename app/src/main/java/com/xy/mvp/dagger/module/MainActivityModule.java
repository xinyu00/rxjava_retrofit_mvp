package com.xy.mvp.dagger.module;

import com.xy.mvp.presenter.MainActivityPresenter;
import com.xy.mvp.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */
@Module
public class MainActivityModule {
    private MainActivity activity;
    public MainActivityModule(MainActivity activity){
        this.activity = activity;
    }
    @Provides
    public MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter(activity);
    }
}
