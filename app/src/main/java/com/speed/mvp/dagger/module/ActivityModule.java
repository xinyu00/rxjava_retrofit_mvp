package com.speed.mvp.dagger.module;

import android.app.Activity;

import com.speed.mvp.base.BaseActivity;
import com.speed.mvp.dagger.PerActivity;
import com.speed.mvp.presenter.BaseActivityPresenter;
import com.speed.mvp.presenter.user.LoginUIPresenter;
import com.speed.mvp.presenter.user.RegisterUIPresenter;
import com.speed.mvp.ui.user.LoginUI;
import com.speed.mvp.ui.user.RegisterUI;

import dagger.Module;
import dagger.Provides;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides @PerActivity
    Activity activity() {
        return this.activity;
    }

    @Provides @PerActivity
    LoginUIPresenter provideLoginPresenter() {
        return new LoginUIPresenter((LoginUI) activity());
    }

    @Provides @PerActivity
    RegisterUIPresenter provideRegisterPresenter() {
        return new RegisterUIPresenter((RegisterUI) activity());
    }

    @Provides @PerActivity
    BaseActivityPresenter provideBasePresenter() {
        return new BaseActivityPresenter((BaseActivity) activity());
    }
}
