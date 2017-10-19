package ren.perry.baseperrymvp;

import ren.perry.mvplibrary.BaseMvpApplication;

/**
 * ren.perry.basemvpframe
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

public class MyApp extends BaseMvpApplication {

    @Override
    public void setTimeout(int connect, int read, int write) {
        super.setTimeout(15, 12, 12);
    }
}
