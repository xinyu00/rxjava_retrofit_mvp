# MVP

##### 这是一个基于rxjava、retrofit开发的mvp框架
##### 这里面有rxjava、retrofit的使用，还有一些其他的框架，你可以根据当前框架学习其使用方法。
##### 这是一个示例框架，作者持续更新中。
##### author: tianchen
##### qq: 963181974
##### email: 963181974@qq.com


#### 以下是我开发此框架遇到的一些问题和解决办法：

1. ToastUtils类的设计中的实际问题
Toast使用Activity的Context导致内存泄露。
解决办法：
Toast使用全局的Context,通过getApplicationContext()获取。即可避免App销毁Context仍旧有泄露的情况。

