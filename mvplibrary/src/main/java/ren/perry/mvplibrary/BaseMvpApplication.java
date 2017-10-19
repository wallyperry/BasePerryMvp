package ren.perry.mvplibrary;

import android.app.Application;

/**
 * ren.perry.mvpLibrary
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

public abstract class BaseMvpApplication extends Application {
    private static BaseMvpApplication mvpApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mvpApplication == null) {
            mvpApplication = this;
        }
    }

    /**
     * 设置超时时间
     *
     * @param connect 连接超时
     * @param read    读超时
     * @param write   写超时
     */
    public void setTimeout(int connect, int read, int write) {
        PerryMvp.getInstance().setConnectTimeout(connect);
        PerryMvp.getInstance().setReadTimeout(read);
        PerryMvp.getInstance().setWriteTimeout(write);
    }

    public static BaseMvpApplication getContext() {
        return mvpApplication;
    }

}
