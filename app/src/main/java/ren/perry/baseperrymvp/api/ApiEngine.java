package ren.perry.baseperrymvp.api;

import ren.perry.mvplibrary.net.NetClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ren.perry.basemvpframe.api
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

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
