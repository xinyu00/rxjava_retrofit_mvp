package com.xy.mvp.dagger.component;

import com.xy.mvp.dagger.module.LoginUIModule;
import com.xy.mvp.ui.LoginUI;

import dagger.Component;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */
//第三步： 通过接口将创建实例的代码和目标关联在一起
@Component(modules = LoginUIModule.class)
interface LoginUIComponent {
    void in(LoginUI activity);
}
