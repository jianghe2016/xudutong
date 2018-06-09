package com.xdt.xudutong.personcenterfragment;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xdt.xudutong.R;

public class PersonEightMessagetoneFragmentonefragment extends Fragment {
    private View view;
    private XRecyclerView mperson_eight_messageone_fragment_onefragmentrecycleview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.person_eight_messageone_fragment_onefragment
                , container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        mperson_eight_messageone_fragment_onefragmentrecycleview = (XRecyclerView) view.findViewById(R.id.person_eight_messageone_fragment_onefragmentrecycleview);
        ImageView monlyProgress = (ImageView) view.findViewById(R.id.onlyProgress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_progress_anim);
//        monlyProgress.startAnimation(animation);
    }

    private void initData() {

    }

}
