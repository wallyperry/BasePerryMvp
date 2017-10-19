package ren.perry.mvplibrary.net;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ren.perry.mvplibrary.BaseMvpApplication;
import ren.perry.mvplibrary.PerryMvp;

/**
 * ren.perry.mvpLibrary.net
 *
 * @author perry
 * @date 2017/10/19
 * Wechat  917351143
 */

public class NetClient {
    private volatile static NetClient netClient;
    private final OkHttpClient client;

    private NetClient() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseMvpApplication.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        /*
         * connectTimeout:   设置连接超时
         * readTimeout:      设置读超时
         * writeTimeout:     设置写超时
         */
        client = new OkHttpClient.Builder()
                .connectTimeout(PerryMvp.getInstance().getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(PerryMvp.getInstance().getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(PerryMvp.getInstance().getWriteTimeout(), TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetWorkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    public static NetClient getInstance() {
        if (netClient == null) {
            synchronized (NetClient.class) {
                if (netClient == null) {
                    netClient = new NetClient();
                }
            }
        }
        return netClient;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }
}
