package com.xy.mvp.dagger.component;

import com.xy.mvp.dagger.module.MainUIModule;
import com.xy.mvp.ui.MainUI;

import dagger.Component;

/**
 * anthor:Created by tianchen on 2017/2/13.
 * email:963181974@qq.com
 */
//第三步： 通过接口将创建实例的代码和目标关联在一起
@Component(modules = MainUIModule.class)
interface MainUIComponent {
    void in(MainUI activity);
}
