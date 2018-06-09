package com.xdt.xudutong.huiminbao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.FragmentAdapter;
import com.xdt.xudutong.base.Base2Activity;

import java.util.ArrayList;
import java.util.List;

public class DealActivity extends Base2Activity{

    private String mFlag;
    private TextView mTv_title;
    private LinearLayout mBack;
    private XTabLayout mXTabLayout;
    private ViewPager mViewPager;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        initView();
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mXTabLayout = (XTabLayout) findViewById(R.id.xTablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mFlag = getIntent().getExtras().getString("flag");
        if ("0".equals(mFlag)){
            mTv_title.setText("收益明细");
        }else {
            mTv_title.setText("交易记录");
        }
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        if ("0".equals(mFlag)){
            titles.add("近一月收益");
            titles.add("近一周收益");
            for (int i = 0; i < titles.size(); i++) {
                if(i % 2 == 0){
                    fragments.add(new MonthEarningsFragment());
               }else{
                    fragments.add(new WeekEarningsFragment());
                }
            }
        }else {
            titles.add("近一周交易");
            titles.add("近一月交易");
            for (int i = 0; i < titles.size(); i++) {
                if(i % 2 == 0){
                    fragments.add(new WeekDealFragment());
                }else{
                    fragments.add(new MonthDealFragment());
                }
            }
        }

        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adatper);
        mViewPager.setOffscreenPageLimit(0);
        //将TabLayout和ViewPager关联起来。
        mXTabLayout.setupWithViewPager(mViewPager);
    }
}
