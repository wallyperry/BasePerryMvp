package ren.perry.baseperrymvp;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.baseperrymvp.R;
import ren.perry.baseperrymvp.bean.GankBean;
import ren.perry.baseperrymvp.mvp.contract.MainContract;
import ren.perry.baseperrymvp.mvp.presenter.MainPresenter;
import ren.perry.mvplibrary.base.BaseActivity;
import ren.perry.mvplibrary.net.ApiException;

/**
 * MainActivity
 *
 * @author perry
 */
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
        return new MainPresenter(this);
    }

    @Override
    public void onSuccess(GankBean bean) {
        Log.e("MainActivity", "获取到数据：" + bean.getResults().size() + "条数据");
        Glide.with(this)
                .load(bean.getResults().get(0).getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    @Override
    public void onError(ApiException.ResponseException e) {
        Log.e("MainActivity", "获取数据失败：" + e.code + "," + e.message);
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mPresenter.getGankData("福利", 1);
    }
}
