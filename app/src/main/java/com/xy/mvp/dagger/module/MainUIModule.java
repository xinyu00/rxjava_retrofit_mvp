package com.xy.mvp.dagger.module;

import com.xy.mvp.presenter.MainUIPresenter;
import com.xy.mvp.ui.MainUI;

import dagger.Module;
import dagger.Provides;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

@Module
public class MainUIModule {
    private MainUI activity;
    public MainUIModule(MainUI activity){
        this.activity = activity;
    }
    @Provides
    public MainUIPresenter provideMainUIPresenter(){
        return new MainUIPresenter(activity);
    }
}
