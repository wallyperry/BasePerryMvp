package ren.perry.baseperrymvp.mvp.model;

import ren.perry.baseperrymvp.api.ApiEngine;
import ren.perry.baseperrymvp.bean.GankBean;
import ren.perry.baseperrymvp.mvp.contract.MainContract;
import ren.perry.mvplibrary.rx.RxSchedulers;
import rx.Observable;

/**
 * ren.perry.basemvpframe.mvp.model
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

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
