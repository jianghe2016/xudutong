package com.xdt.xudutong.frgment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xdt.xudutong.R;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ExampleUtil;
import com.xdt.xudutong.view.TabPageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MainActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener {
    private FragmentManager mFragmentManager;

    private ViewPager mhomepager;
    private TabLayout mmiantablayout;
    private String[] text1;
    private int[] selectimg;
    //极光推送
    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getInstance().addActivity(this);
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 21) {
            int option2 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option2);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //获取FragmentManager
        mFragmentManager = getSupportFragmentManager();
        //获取radioGroup控件
        mhomepager = (ViewPager) findViewById(R.id.fl_content);
        mmiantablayout = (TabLayout) findViewById(R.id.miantablayout);

        text1 = new String[]{"主页", "本地生活", "许都通", "个人中心"};
        selectimg = new int[]{R.drawable.menu1_selector, R.drawable.menu2_selector, R.drawable.menu3_selector, R.drawable.menu4_selector};
        List<Fragment> fragments1 = new ArrayList<Fragment>();
        //建立一个集合把各个页面装进去
        HomePageFragment homePageFragment1 = new HomePageFragment();
        LocalLifeFragment localLifeFragment1 = new LocalLifeFragment();
        XudutongFragment xudutongFragment1 = new XudutongFragment();
        PersonCenterFragment personCenterFragment1 = new PersonCenterFragment();
        fragments1.add(homePageFragment1);
        fragments1.add(localLifeFragment1);
        fragments1.add(xudutongFragment1);
        fragments1.add(personCenterFragment1);

        //viewpager适配器
        TabPageAdapter tabPageradapter = new TabPageAdapter(mFragmentManager, fragments1);
        mhomepager.setAdapter(tabPageradapter);
        mhomepager.setCurrentItem(0);
        mmiantablayout.setupWithViewPager(mhomepager);

        for (int i = 0; i < mmiantablayout.getTabCount(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab_bottom_item_view, null);
            mmiantablayout.getTabAt(i).setCustomView(view);
            TextView tvTitle = (TextView) mmiantablayout.getTabAt(i).getCustomView().findViewById(R.id.tv_tab);
            tvTitle.setText(text1[i]);
            ImageView imgTab = (ImageView) mmiantablayout.getTabAt(i).getCustomView().findViewById(R.id.img_tab);
            imgTab.setImageDrawable(getResources().getDrawable(selectimg[i]));
        }
        //设置TabLayout点击事件
        mmiantablayout.setOnTabSelectedListener(this);
        registerMessageReceiver();  // used for receive msg
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy();
        }
        return false;
    }

    private Boolean isExit = false;

    private void exitBy() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            AppManager.getInstance().exit();
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    //滑动监听三个方法
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        View decorView = getWindow().getDecorView();
        switch (tab.getPosition()) {
            case 0:
                if (Build.VERSION.SDK_INT >= 21) {
                    int option2 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option2);
                    getWindow().setStatusBarColor(Color.TRANSPARENT);

                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= 21) {
                    int option2 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option2);
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= 21) {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT >= 21) {
                    int option2 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option2);
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                break;
            default:
                if (Build.VERSION.SDK_INT >= 21) {
                    int option2 = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option2);
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " 5555555555: " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " 7777777777: " + extras + "\n");
                    }
                    Log.i("不知道出现的什么111", messge);
                    Log.i("不知道出现的什么222", extras);
                }
            } catch (Exception e) {
                Log.i("捕捉到了推送的异常", "捕捉到了推送的异常");
            }
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}

