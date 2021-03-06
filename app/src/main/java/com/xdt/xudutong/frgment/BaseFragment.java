package com.xdt.xudutong.frgment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/8.
 */

public abstract class BaseFragment extends Fragment {
    //主页面的上下文
    public  MainActivity mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        //获取上下文
        mContext = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    //一定要实现的方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 定义为抽象方法,子类必须实现
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化事件
     */
    public void initEvent() {

    }

    //此方法只执行一次
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //只要activity 不销毁,此方法 就只调用一次
        initData();
        initEvent();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
