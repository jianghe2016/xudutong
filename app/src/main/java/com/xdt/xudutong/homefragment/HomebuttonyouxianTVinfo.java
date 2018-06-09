package com.xdt.xudutong.homefragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\17 0017.
 */

public class HomebuttonyouxianTVinfo extends BaseActivity {

    private ImageView mhome_button_watermoneyinfoitem1img;
    private TextView mhome_button_watermoneyinfoitem1text;
    private TextView home_button_watermoneyinfoitem2text1;
    private TextView home_button_watermoneyinfoitem2text2;
    private TextView mhome_button_watermoneyinfoitem3text2;
    private TextView mhome_button_watermoneyinfoitem4text1;
    private TextView mhome_button_watermoneyinfoitem4text2;
    private TextView mhome_button_watermoneyinfoitemsubmit;
    private TextView mhome_button_watermoneyinfoheadviewtext2;
    private LinearLayout mhome_button_watermoneyinfoback;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_button_watermoneyinfo);
    }

    @Override
    public void initView() {
        mhome_button_watermoneyinfoback = (LinearLayout) findViewById(R.id.home_button_watermoneyinfoback);
        //item1
        mhome_button_watermoneyinfoitem1img = (ImageView) findViewById(R.id.home_button_watermoneyinfoitem1img);
        mhome_button_watermoneyinfoitem1text = (TextView) findViewById(R.id.home_button_watermoneyinfoitem1text);
        mhome_button_watermoneyinfoheadviewtext2 = (TextView) findViewById(R.id.home_button_watermoneyinfoheadviewtext2);
        //item2
        home_button_watermoneyinfoitem2text1 = (TextView) findViewById(R.id.home_button_watermoneyinfoitem2text1);
        home_button_watermoneyinfoitem2text2 = (TextView) findViewById(R.id.home_button_watermoneyinfoitem2text2);
        //item3
        mhome_button_watermoneyinfoitem3text2 = (TextView) findViewById(R.id.home_button_watermoneyinfoitem3text2);
        //item4
        mhome_button_watermoneyinfoitem4text1 = (TextView) findViewById(R.id.home_button_watermoneyinfoitem4text1);
        mhome_button_watermoneyinfoitem4text2 = (TextView) findViewById(R.id.home_button_watermoneyinfoitem4text2);
        //提交
        mhome_button_watermoneyinfoitemsubmit = (TextView) findViewById(R.id.home_button_watermoneyinfoitemsubmit);
        initData();
    }

    private void initData() {
        int item1img = R.drawable.home_youxiantvimg;
        mhome_button_watermoneyinfoitem1img.setImageResource(item1img);
        mhome_button_watermoneyinfoheadviewtext2.setText("许昌市");
        mhome_button_watermoneyinfoitem1text.setText("有线电视");
        home_button_watermoneyinfoitem2text1.setText("家庭");
        home_button_watermoneyinfoitem2text2.setText("我家");
        mhome_button_watermoneyinfoitem3text2.setText("河南广电");
        mhome_button_watermoneyinfoitem4text1.setText("身份证号码");
        mhome_button_watermoneyinfoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }
}