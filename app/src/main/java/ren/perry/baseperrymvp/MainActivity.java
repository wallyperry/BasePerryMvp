package ren.perry.baseperrymvp;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ren.perry.baseperrymvp.bean.GankBean;
import ren.perry.baseperrymvp.mvp.contract.MainContract;
import ren.perry.baseperrymvp.mvp.presenter.MainPresenter;
import ren.perry.mvplibrary.base.BaseActivity;
import ren.perry.mvplibrary.net.ApiException;
import ren.perry.mvplibrary.utils.SpUtils;

/**
 * MainActivity
 *
 * @author perry
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.tvSpData)
    TextView tvSpData;

    private final String sp_key = "test";
    private SpUtils spUtils;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        spUtils = new SpUtils(this, "sp_test");

    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onSuccess(GankBean bean) {
        String result = "获取到数据：" + bean.getResults().size() + "条数据";
        tv1.setText(result);
    }

    @Override
    public void onError(ApiException.ResponseException e) {
        String result = "获取数据失败：" + e.code + "," + e.message;
        tv1.setText(result);
    }

    @OnClick({R.id.btnGetData, R.id.btnSpSave, R.id.btnSpGet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGetData:
                mPresenter.getGankData("Android", 1);
                break;
            case R.id.btnSpSave:
                spUtils.putString(sp_key, et1.getText().toString());
                break;
            case R.id.btnSpGet:
                tvSpData.setText(spUtils.getString(sp_key, ""));
                break;
        }
    }
}
