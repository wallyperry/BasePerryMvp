package ren.perry.mvplibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 *
 * @author valentine
 * @date 2017/5/29
 */

@SuppressWarnings("unused")
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private Unbinder unbinder;

    protected P mPresenter;

    /**
     * 宿主Activity
     */
    protected FragmentActivity activity;

    /**
     * 根View
     */
    protected View rootView;

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;

    /**
     * 仅第一次可见时调用
     */
    protected void fetchData() {
    }

    protected Bundle savedInstanceState;
    protected Toast toast;

    /**
     * 设置跟布局的资源ID
     *
     * @return layout
     */
    protected abstract int setLayoutResourceId();

    /**
     * 创建Presenter
     *
     * @return P extends BasePresenter
     */
    protected abstract P onCreatePresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInitiated = true;
        initFetchData();
    }

    protected void initFetchData() {
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutResourceId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        this.savedInstanceState = savedInstanceState;
        initData(getArguments());
        initView();
        return rootView;
    }

    protected void toast(String s) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(activity, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @param arguments 接受到的从其他地方传过来的参数
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    protected void initData(Bundle arguments) {
        if (arguments == null) {
            return;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
