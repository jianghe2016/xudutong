package com.xdt.xudutong.personcenterfragment;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Find_tab_Adapter;

import java.util.ArrayList;
import java.util.List;

public class PersonEightMessageoneFragment extends Fragment {
    private View view;
    private TabLayout mperson_eight_message_onefragment_tablelayout;
    private ViewPager mperson_eight_message_onefragment_viewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.person_eight_messageone_fragment, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        mperson_eight_message_onefragment_tablelayout = (TabLayout) view.findViewById(R.id.person_eight_message_onefragment_tablelayout);
        mperson_eight_message_onefragment_viewpager = (ViewPager) view.findViewById(R.id.person_eight_message_onefragment_viewpager);
    }

    private void initData() {
        //顶部两个选项卡
        PersonEightMessagetoneFragmentonefragment mpersonEightMessagetoneFragmentonefragment = new PersonEightMessagetoneFragmentonefragment();
        PersonEightMessagetoneFragmenttwofragment mpersonEightMessagetoneFragmenttwofragment = new PersonEightMessagetoneFragmenttwofragment();
        List<Fragment> fragmentlist=new ArrayList<>();
        fragmentlist.add(mpersonEightMessagetoneFragmentonefragment);
        fragmentlist.add(mpersonEightMessagetoneFragmenttwofragment);
        List<String> titletext=new ArrayList<>();
        titletext.add("未付款");
        titletext.add("已付款");
        mperson_eight_message_onefragment_tablelayout.setTabMode(TabLayout.MODE_FIXED);
        mperson_eight_message_onefragment_tablelayout.addTab(mperson_eight_message_onefragment_tablelayout.newTab().setText(titletext.get(0)));
        mperson_eight_message_onefragment_tablelayout.addTab(mperson_eight_message_onefragment_tablelayout.newTab().setText(titletext.get(1)));
        FragmentManager childFragmentManager = getChildFragmentManager();
        Find_tab_Adapter find_tab_adapter = new Find_tab_Adapter(childFragmentManager,fragmentlist,titletext);
        mperson_eight_message_onefragment_viewpager.setAdapter(find_tab_adapter);
        mperson_eight_message_onefragment_tablelayout.setupWithViewPager(mperson_eight_message_onefragment_viewpager);

    }

}
