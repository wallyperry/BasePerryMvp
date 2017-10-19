# BasePerryMvp
基于MVP+RxJava+Retrofit的通用型的基础网络请求架构，其包含了BaseActivity、BaseFragment、BaseModel、BasePresenter、BaseView。

## 依赖
##### Step 1.
添加Jitpack到您的root gradle，如果无法导包，一般情况下都是这个原因，请仔细检查
 ```xml
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        classpath 'me.tatarka:gradle-retrolambda:3.2.4'
    }
}

allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
##### Step 2.
在需要使用到的Module中添加以下依赖
```xml
dependencies {
    compile 'com.github.weipeilong123:BasePerryMvp:1.0.0'
}
```

## 使用方法
Demo目录结构
##### ![](http://perry.ren/apk/BasePerryMvp.png )
##### Step 1.
创建Bean模型,将你的API接口请求的结果用[GsonFormat快速生成JSon实体类](http://www.cnblogs.com/1024zy/p/6370305.html "GsonFormat快速生成JSon实体类")
<hr>

GankBean.class
```java
public class GankBean {
    /**
     * error : false
     * results : [{"_id":"597e016c421aa90ca209c523","createdAt":"2017-07-30T23:55:24.154Z","desc":"Android终端","publishedAt":"2017-09-21T13:27:15.675Z","source":"chrome","type":"Android","url":"https://github.com/termux/termux-app","used":true,"who":"Jason"},{"_id":"597f2861421aa90ca209c527","createdAt":"2017-07-31T20:53:53.217Z","desc":"Google从 API 21 新增了接口 android.app.usage , 通过这个api我们可以统计到每个app的使用情况，启动次数，启动时间等，也可以判断是否前后台，比较方便。","images":["http://img.gank.io/c778f7da-b580-490b-961d-34706a57d326"],"publishedAt":"2017-09-21T13:27:15.675Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/bdf47afe110d","used":true,"who":"Tamic (码小白)"},{"_id":"5980492b421aa90ca209c533","createdAt":"2017-08-01T17:26:03.968Z","desc":"内存泄漏简要分析","publishedAt":"2017-09-21T13:27:15.675Z","source":"web","type":"Android","url":"https://zhuanlan.zhihu.com/p/28169456?group_id=874607398092935168","used":true,"who":"Li Wenjing"},{"_id":"59c3201e421aa9727ddd19b6","createdAt":"2017-09-21T10:12:46.866Z","desc":"安卓版本的VegaScroll","images":["http://img.gank.io/22a7d123-7776-4954-8f73-176ad9e18ddf"],"publishedAt":"2017-09-21T13:27:15.675Z","source":"web","type":"Android","url":"https://github.com/xmuSistone/VegaLayoutManager","used":true,"who":"stone"},{"_id":"59bbe478421aa9118c8262ca","createdAt":"2017-09-15T22:32:24.21Z","desc":"Android上取代HashMap的ArrayMap","images":["http://img.gank.io/a87f186b-47cc-44a4-98c7-a2dd05576fe7"],"publishedAt":"2017-09-20T13:17:38.709Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/088b9383f974","used":true,"who":"Niekon"},{"_id":"59bf3bb0421aa9118887ac35","createdAt":"2017-09-18T11:21:20.893Z","desc":"Glide v4最全中文文档来了！","publishedAt":"2017-09-20T13:17:38.709Z","source":"web","type":"Android","url":"https://github.com/Muyangmin/glide-docs-cn","used":true,"who":"Muyangmin"},{"_id":"59c1c557421aa9727fdb25c3","createdAt":"2017-09-20T09:33:11.73Z","desc":"你知道APP从启动到主页面显示经历了哪些过程吗？","publishedAt":"2017-09-20T13:17:38.709Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487014&idx=1&sn=ce2e5378d0dd8a8bc6b85358a8aaa47c","used":true,"who":"陈宇明"},{"_id":"59c1d24d421aa972845f2077","createdAt":"2017-09-20T10:28:29.264Z","desc":"全民 Google ARCore，解决官方不支持设备问题","publishedAt":"2017-09-20T13:17:38.709Z","source":"web","type":"Android","url":"https://github.com/tomthecarrot/arcore-for-all","used":true,"who":null},{"_id":"59b7909f421aa911847a0391","createdAt":"2017-09-12T15:45:35.132Z","desc":"View的事件分发机制\u2014\u2014由浅入深解析","publishedAt":"2017-09-19T12:07:31.405Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/e6ceb7f767d8","used":true,"who":"匡罡"},{"_id":"59bfe526421aa911847a03b3","createdAt":"2017-09-18T23:24:22.545Z","desc":"一个简约番茄时钟的实现思路","images":["http://img.gank.io/cc9e461f-3e8d-456f-bba5-e15b80509dfb"],"publishedAt":"2017-09-19T12:07:31.405Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/09/18/TomatoView%E5%AE%9E%E7%8E%B0/","used":true,"who":"HuYounger"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 597e016c421aa90ca209c523
         * createdAt : 2017-07-30T23:55:24.154Z
         * desc : Android终端
         * publishedAt : 2017-09-21T13:27:15.675Z
         * source : chrome
         * type : Android
         * url : https://github.com/termux/termux-app
         * used : true
         * who : Jason
         * images : ["http://img.gank.io/c778f7da-b580-490b-961d-34706a57d326"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
```

##### Step 2.
将您的Application类继承自BaseMvpApplication，并在AndroidManifest中注册。
<hr>

MyApp.class
```java
public class MyApp extends BaseMvpApplication {

    @Override
    public void setTimeout(int connect, int read, int write) {
        super.setTimeout(15, 12, 12);//设置连接超时，读超时，写超时
    }
}
```

AndroidManifest.xml
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
              package="ren.perry.baseperrymvp">

        <uses-permission android:name="android.permission.INTERNET"/>
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

        <application
            android:name=".MyApp"
            android:allowBackup="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
            <activity android:name=".MainActivity">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN"/>

                    <category android:name="android.intent.category.LAUNCHER"/>
                </intent-filter>
            </activity>
        </application>
    </manifest>
```

##### Step 3.
创建网络请求相关的类
<hr>

ApiService.class
```java
public interface ApiService {

    /**
     * 干货集中营
     */
    String GANK_URL = "http://gank.io";

    //---------------------------------我只是个分割线-------------------------------------------------

    /**
     * 干货数据
     *
     * @param type type
     * @param page page
     * @return TopBean
     */
    @GET("/api/data/{type}/10/{page}")
    Observable<GankBean> gankData(
            @Path("type") String type,
            @Path("page") int page);
}
```

ApiEngine.class
```java
public class ApiEngine {
    private volatile static ApiEngine apiEngine;

    private ApiEngine() {
    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getGankService() {
        return new Retrofit.Builder()
                .baseUrl(ApiService.GANK_URL)
                .client(NetClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiService.class);
    }
}
```

##### Step 4.
创建MVP模式相关的类
<hr>
MainContract.class

```java
public interface MainContract {
    interface View extends BaseView {
        void onSuccess(GankBean bean);

        void onError(ApiException.ResponseException e);
    }

    interface Model extends BaseModel {
        Observable<GankBean> getGankData(String type, int page);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getGankData(String type, int page);
    }
}
```

MainModel.class
```java
public class MainModel implements MainContract.Model {
    @Override
    public Observable<GankBean> getGankData(String type, int page) {
        return ApiEngine.getInstance()
                .getGankService()
                .gankData(type, page)
//                .retryWhen(FuncTry.tryError())  //失败5s后自动重试
                .compose(RxSchedulers.<GankBean>switchThread());
    }
}
```

MainPresenter.class
```java
public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getGankData(String type, int page) {
        addSubscribe(mModel.getGankData(type, page)
                .subscribe(new BaseSubscriber<GankBean>() {
                    @Override
                    public void onError(ApiException.ResponseException e) {
                        mView.onError(e);
                    }

                    @Override
                    public void onNext(GankBean bean) {
                        mView.onSuccess(bean);
                    }
                }));
    }
}
```

##### Step 5.
将Activity、Fragment继承自BaseActivity、BaseFragment,并增加泛型Presenter。
<hr>

MainActivity.class
```java
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Bind(R.id.iv)
    ImageView iv;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);//创建Presenter对象
    }

    @Override
    public void onSuccess(GankBean bean) {
        //请求成功返回的结果
        Log.e("MainActivity", "获取到数据：" + bean.getResults().size() + "条数据");
        Glide.with(this)
                .load(bean.getResults().get(0).getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    @Override
    public void onError(ApiException.ResponseException e) {
        //请求失败返回的结果
        Log.e("MainActivity", "获取数据失败：" + e.code + "," + e.message);
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.getGankData("福利", 1);
    }
}
```

<hr>

## 最后

##### [Github 地址](https://github.com/weipeilong123/BasePerryMvp "Github 地址")

##### [APK 下载](http://perry.ren/apk/BasePerryMvp.apk "APK下载")
