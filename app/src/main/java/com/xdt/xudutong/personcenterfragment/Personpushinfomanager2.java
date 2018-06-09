package com.xdt.xudutong.personcenterfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Find_tab_Adapter;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.NoScrollViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017\9\27 0027.
 */

public class Personpushinfomanager2 extends BaseActivity {

    private TabLayout mperson_eight_messageactivity_tablayout;
    private NoScrollViewPager mperson_eight_messageactivity_viewpager;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_eight_messageactivity);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        int personturenamestates = intent.getIntExtra("personturenamestates", 5);
        Bundle bundle = new Bundle();
        bundle.putInt("personturenamestates", personturenamestates);
        int personpushinfomanager1selectbutton = intent.getIntExtra("personpushinfomanager1selectbutton", 1);
        LinearLayout mperson_eight_messageactivity_back = (LinearLayout) findViewById(R.id.person_eight_messageactivity_back);
        mperson_eight_messageactivity_tablayout = (TabLayout) findViewById(R.id.person_eight_messageactivity_tablayout);
        mperson_eight_messageactivity_viewpager = (NoScrollViewPager) findViewById(R.id.person_eight_messageactivity_viewpager);
        mperson_eight_messageactivity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData(bundle,personpushinfomanager1selectbutton);
    }

    private void initData(Bundle bundle, int personpushinfomanager1selectbutton) {
        //顶部四个选项卡
        PersonEightMessageoneFragment mpersonEightMessageoneFragment = new PersonEightMessageoneFragment();
        PersonEightMessagetwoFragment PersonEightMessagetwoFragment = new PersonEightMessagetwoFragment();
        PersonEightMessagethreeFragment PersonEightMessagethreeFragment = new PersonEightMessagethreeFragment();
        PersonEightMessagefourFragment PersonEightMessagefourFragment = new PersonEightMessagefourFragment();
        //传值给第三个fragment
        PersonEightMessagethreeFragment.setArguments(bundle);
        List<Fragment> fregmentlist=new ArrayList<>();
        fregmentlist.add(mpersonEightMessageoneFragment);
        fregmentlist.add(PersonEightMessagetwoFragment);
        fregmentlist.add(PersonEightMessagethreeFragment);
        fregmentlist.add(PersonEightMessagefourFragment);
        List<String> titlelist=new ArrayList<>();
        titlelist.add("订单信息");
        titlelist.add("小绿骑行");
        titlelist.add("实名认证");
        titlelist.add("智慧医疗");
        mperson_eight_messageactivity_tablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titlelist.size(); i++) {
            mperson_eight_messageactivity_tablayout.addTab(mperson_eight_messageactivity_tablayout.newTab().setText(titlelist.get(i)));
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Find_tab_Adapter find_tab_adapter = new Find_tab_Adapter(supportFragmentManager, fregmentlist, titlelist);
        mperson_eight_messageactivity_viewpager.setAdapter(find_tab_adapter);
        setIndicator(this, mperson_eight_messageactivity_tablayout, 30, 30);
        mperson_eight_messageactivity_tablayout.setupWithViewPager(mperson_eight_messageactivity_viewpager);
        mperson_eight_messageactivity_tablayout.getTabAt(personpushinfomanager1selectbutton).select();
        //禁止tablelayout+viewpager的点击事件
        setTabLayoutCanClick(true);
        mperson_eight_messageactivity_viewpager.setNoScroll(false);
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }
    public void setTabLayoutCanClick(boolean canClick){
        LinearLayout tabStrip= (LinearLayout) mperson_eight_messageactivity_tablayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if(tabView !=null){
                tabView.setClickable(canClick);
            }
        }
    }
}
