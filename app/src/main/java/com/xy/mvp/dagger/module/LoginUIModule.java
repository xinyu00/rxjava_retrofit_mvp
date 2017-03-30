package com.xy.mvp.dagger.module;

import com.xy.mvp.presenter.LoginUIPresenter;
import com.xy.mvp.ui.LoginUI;

import dagger.Module;
import dagger.Provides;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

@Module
public class LoginUIModule {
    private LoginUI activity;
    public LoginUIModule(LoginUI activity){
        this.activity = activity;
    }
    @Provides
    public LoginUIPresenter provideLoginUIPresenter(){
        return new LoginUIPresenter(activity);
    }
}
