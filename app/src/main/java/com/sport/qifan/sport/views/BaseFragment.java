package com.sport.qifan.sport.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qifan on 2016/11/11.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    private Unbinder unbinder;
    private View root;
    /**
     * 设置Activity的layout
     * @return layout的id
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initDatas();

    /**
     * 初始化控件的事件
     */
    public abstract void initEvents();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, root);
        initDatas();
        initEvents();
        return root;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
