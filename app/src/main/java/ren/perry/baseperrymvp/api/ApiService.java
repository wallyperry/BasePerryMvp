package ren.perry.baseperrymvp.api;

import ren.perry.baseperrymvp.bean.GankBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ren.perry.basemvpframe.api
 *
 * @author perry
 * @date 2017/10/19
 * WeChat  917351143
 */

@SuppressWarnings("WeakerAccess")
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
