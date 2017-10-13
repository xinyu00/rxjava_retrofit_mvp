MVP快速开发架构
====
##### 这是一个基于rxjava、retrofit、dagger2等开发的mvp框架
##### 这里面有rxjava、retrofit、butterknife、greendao、fastjson、fresco、dagger2、leaks
##### 持续更新中。。。。。。
##### author: tianchen
##### qq交流群: 669486780

组成结构：
-------  
包名：com.speed.mvp

* base     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;　//基类
    * AndroidApplication
    * BaseActivity
* dagger   &nbsp;&nbsp;&nbsp; //dagger2相关文件
    * component
    * modele
* db       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //数据库操作
    * dao
    * impl
* entity   &nbsp;&nbsp;&nbsp; //实体类
    * ResponseInfo
* net      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //网络层、
    * api   //网络api
        * Api        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;//okhttp初始化，拦截器设置
        * ApiService &nbsp; &nbsp; &nbsp; //retrofit请求服务
        * HostType   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;//Url缓存类型
    * NetBack   
* presenter    &nbsp;&nbsp;&nbsp; //交互层
    * user       &nbsp;  &nbsp;  &nbsp; //用户模块Presenter
    * IndexUI    &nbsp;  &nbsp;  &nbsp; //主界面Presenter
    * NavigateUI  &nbsp;  &nbsp;  &nbsp; //导航页Presenter
* ui           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //View层
    * user       &nbsp;  &nbsp;  &nbsp; //用户模块
    * IndexUI    &nbsp;  &nbsp;  &nbsp; //主界面
    * NavigateUI  &nbsp;  &nbsp;  &nbsp; //导航页
* utils        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //工具类
    * AppManager    &nbsp;  &nbsp;  &nbsp; //APP统一管理:负责Activity栈堆管理
    * ConfigConstants    &nbsp;  &nbsp;  &nbsp; //APP统一管理类:负责Activity栈堆管理
    * DesignUtils   &nbsp;  &nbsp;  &nbsp; //App缓存常量配置
    * FileUtils     &nbsp;  &nbsp;  &nbsp; //文件处理工具类
    * FormatUtil    &nbsp;  &nbsp;  &nbsp; //数据格式转换类
    * FrescoUtils   &nbsp;  &nbsp;  &nbsp; //Fresco初始化工具类
    * JniTest       &nbsp;  &nbsp;  &nbsp; //Jni测试类
    * LogUtils      &nbsp;  &nbsp;  &nbsp; //Log日志类
    * MD5Utils      &nbsp;  &nbsp;  &nbsp; //MD5转换类
    * NetWorkUtils  &nbsp;  &nbsp;  &nbsp; //网络状态工具类
    * SpUtils       &nbsp;  &nbsp;  &nbsp; //SharedPreferences处理工具类
    * ToastUtils    &nbsp;  &nbsp;  &nbsp; //Toast工具类
    * UrlUtils      &nbsp;  &nbsp;  &nbsp; //网络Url工具类
* view         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //自定义View



