package ren.perry.baseperrymvp.mvp.contract;

import ren.perry.baseperrymvp.bean.GankBean;
import ren.perry.mvplibrary.base.BaseModel;
import ren.perry.mvplibrary.base.BasePresenter;
import ren.perry.mvplibrary.base.BaseView;
import ren.perry.mvplibrary.net.ApiException;
import rx.Observable;

/**
 * ren.perry.basemvpframe.mvp.contract
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

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
