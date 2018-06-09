package com.xdt.xudutong.huiminbao;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Find_tab_Adapter;
import com.xdt.xudutong.frgment.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\9\27 0027.
 */

public class Funstransactionrecord extends BaseActivity {

    private TabLayout mperson_eight_messageactivity_tablayout;
    private ViewPager mperson_eight_messageactivity_viewpager;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.funstransactiondetailslayout);
    }

    @Override
    public void initView() {
        LinearLayout mfunstransactiondetailslayouback = (LinearLayout) findViewById(R.id.funstransactiondetailslayouback);
        mperson_eight_messageactivity_tablayout = (TabLayout) findViewById(R.id.funstransactiondetailslayouback_tablelayout);
        mperson_eight_messageactivity_viewpager = (ViewPager) findViewById(R.id.funstransactiondetailslayouback_viewpager);
        mfunstransactiondetailslayouback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(3);
    }

    private void initData() {

        //顶部四个选项卡
        FunstransactiondetailsFragment1 funstransactiondetailsfragment1 = new FunstransactiondetailsFragment1();
        FunstransactiondetailsFragment2 funstransactiondetailsfragment2 = new FunstransactiondetailsFragment2();
        FunstransactiondetailsFragment3 funstransactiondetailsfragment3 = new FunstransactiondetailsFragment3();
        List<Fragment> fregmentlist=new ArrayList<>();
        fregmentlist.add(funstransactiondetailsfragment1);
        fregmentlist.add(funstransactiondetailsfragment2);
        fregmentlist.add(funstransactiondetailsfragment3);
        List<String> titlelist=new ArrayList<>();
        titlelist.add("全部");
        titlelist.add("购买");
        titlelist.add("赎回");
        mperson_eight_messageactivity_tablayout.setTabMode(TabLayout.MODE_FIXED);
        mperson_eight_messageactivity_tablayout.addTab(mperson_eight_messageactivity_tablayout.newTab().setText(titlelist.get(0)));
        mperson_eight_messageactivity_tablayout.addTab(mperson_eight_messageactivity_tablayout.newTab().setText(titlelist.get(1)));
        mperson_eight_messageactivity_tablayout.addTab(mperson_eight_messageactivity_tablayout.newTab().setText(titlelist.get(2)));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Find_tab_Adapter find_tab_adapter = new Find_tab_Adapter(supportFragmentManager, fregmentlist, titlelist);
        mperson_eight_messageactivity_viewpager.setAdapter(find_tab_adapter);
        setIndicator(this, mperson_eight_messageactivity_tablayout, 30, 30);
        mperson_eight_messageactivity_tablayout.setupWithViewPager(mperson_eight_messageactivity_viewpager);
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
}
