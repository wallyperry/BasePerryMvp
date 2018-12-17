package ren.perry.mvplibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * BaseActivity
 *
 * @author valentine
 * @date 2017/5/29
 */

@SuppressWarnings("unused")
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    protected Context context;
    protected Bundle savedInstanceState;
    protected Toast toast;

    /**
     * 初始化布局
     *
     * @return layout
     */
    protected abstract int initLayoutId();

    /**
     * 执行逻辑代码
     */
    protected abstract void initView();

    /**
     * 设置状态栏
     * StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_color), 30);
     */
    protected void initStatusBar() {

    }

    /**
     * 创建Presenter
     *
     * @return P extend BasePresenter
     */
    protected abstract P onCreatePresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;

        initLayoutId();
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        setContentView(initLayoutId());
        ButterKnife.bind(this);
        context = this;
        initStatusBar();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected void toastShow(String s) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void toastCancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
