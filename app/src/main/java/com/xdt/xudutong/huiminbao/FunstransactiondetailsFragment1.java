package com.xdt.xudutong.huiminbao;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class FunstransactiondetailsFragment1 extends Fragment {
    private View view;
    private Animation animation;
    private ImageView monlyProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.funstransactiondetailsfragment1
                , container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
//        monlyProgress = (ImageView) view.findViewById(R.id.funstransactiondetailsfragment1progress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_progress_anim);
//        monlyProgress.startAnimation(animation);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    animation.cancel();
                    monlyProgress.clearAnimation();
                    monlyProgress.setVisibility(View.GONE);
                }
            }, 2000);   //2秒
        }
    }

    private void initData() {

    }
}
