package com.xy.mvp.dagger.module;

import android.app.Activity;

import com.xy.mvp.base.BaseActivity;
import com.xy.mvp.dagger.PerActivity;
import com.xy.mvp.presenter.BaseActivityPresenter;
import com.xy.mvp.presenter.user.RegisterUIPresenter;
import com.xy.mvp.ui.user.RegisterUI;

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
    RegisterUIPresenter provideRegisterPresenter() {
        return new RegisterUIPresenter((RegisterUI) activity());
    }

    @Provides @PerActivity
    BaseActivityPresenter provideBasePresenter() {
        return new BaseActivityPresenter((BaseActivity) activity());
    }
}
