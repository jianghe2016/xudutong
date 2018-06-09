package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Find_tab_Adapter;
import com.xdt.xudutong.frgment.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\8\9 0009.
 */


public class Homecardgroupbuttondanweiinfo extends BaseActivity {
    private ViewPager home_button_danweiinfoviewpager1;
    //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter
    private TabLayout mTab;
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private HomejiguandanweiFragment HomejiguandanweiFragment;
    private HomeshiyeweiFragment HomeshiyeweiFragment;
    private LinearLayout home_cardgroup_buttondanweiinfoback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttondanweiinfo);
    }

    @Override
    public void initView() {
        home_cardgroup_buttondanweiinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttondanweiinfoback);
        mTab = (TabLayout) findViewById(R.id.home_button_danweiinfotablayout);
        home_button_danweiinfoviewpager1 = (ViewPager) findViewById(R.id.home_button_danweiinfoviewpager);
        initData();
    }

    private void initData() {
        home_cardgroup_buttondanweiinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    finish();
                }
            }
        });


        //初始化各fragment
        HomejiguandanweiFragment = new HomejiguandanweiFragment();
        HomeshiyeweiFragment = new HomeshiyeweiFragment();


        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(HomejiguandanweiFragment);
        list_fragment.add(HomeshiyeweiFragment);


        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("机关单位信息");
        list_title.add("事业单位信息");
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mTab.addTab(mTab.newTab().setText(list_title.get(0)));
        mTab.addTab(mTab.newTab().setText(list_title.get(1)));


        FragmentManager mFragmentManager = getSupportFragmentManager();
      fAdapter = new Find_tab_Adapter(mFragmentManager, list_fragment, list_title);

        home_button_danweiinfoviewpager1.setAdapter(fAdapter);
        mTab.setupWithViewPager(home_button_danweiinfoviewpager1);
    }
}
