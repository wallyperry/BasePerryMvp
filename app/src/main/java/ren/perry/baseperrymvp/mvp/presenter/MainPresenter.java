package ren.perry.baseperrymvp.mvp.presenter;

import ren.perry.baseperrymvp.bean.GankBean;
import ren.perry.baseperrymvp.mvp.contract.MainContract;
import ren.perry.baseperrymvp.mvp.model.MainModel;
import ren.perry.mvplibrary.net.ApiException;
import ren.perry.mvplibrary.rx.BaseSubscriber;

/**
 * ren.perry.basemvpframe.mvp
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

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
