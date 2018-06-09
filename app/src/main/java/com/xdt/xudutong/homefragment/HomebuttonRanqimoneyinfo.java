package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\17 0017.
 */

public class HomebuttonRanqimoneyinfo extends BaseActivity {

    private ImageView mhome_button_watermoneyinfoitem1img;
    private TextView mhome_button_watermoneyinfoitem1text;
    private TextView home_button_watermoneyinfoitem2text1;
    private TextView home_button_watermoneyinfoitem2text2;
    private TextView mhome_button_watermoneyinfoitem3text2;
    private TextView mhome_button_watermoneyinfoitem4text1;
    private TextView mhome_button_watermoneyinfoitem4text2;
    private TextView mhome_button_watermoneyinfoitemsubmit;
    private TextView mhome_button_watermoneyinfoheadviewtext2;
    private TextView mhome_button_energymoneyinfoitem5text1;
    private LinearLayout mhome_button_energymoneyinfoback;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_button_energymoneyinfo);
    }

    @Override
    public void initView() {
        mhome_button_energymoneyinfoback = (LinearLayout) findViewById(R.id.home_button_energymoneyinfoback);
        //item1
        mhome_button_watermoneyinfoitem1img = (ImageView) findViewById(R.id.home_button_energymoneyinfoitem1img);
        mhome_button_watermoneyinfoitem1text = (TextView) findViewById(R.id.home_button_energymoneyinfoitem1text);
        mhome_button_watermoneyinfoheadviewtext2 = (TextView) findViewById(R.id.home_button_energymoneyinfoheadviewtext2);
        //item2
        home_button_watermoneyinfoitem2text1 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem2text1);
        home_button_watermoneyinfoitem2text2 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem2text2);
        //item3
        mhome_button_watermoneyinfoitem3text2 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem3text2);
        //item4
        mhome_button_watermoneyinfoitem4text1 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem4text1);
        mhome_button_watermoneyinfoitem4text2 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem4text2);
        //item5
        mhome_button_energymoneyinfoitem5text1 = (TextView) findViewById(R.id.home_button_energymoneyinfoitem5text1);
        //提交
        mhome_button_watermoneyinfoitemsubmit = (TextView) findViewById(R.id.home_button_energymoneyinfoitemsubmit);
        initData();
    }

    private void initData() {
        int item1img = R.drawable.home_ranqiimg;
        mhome_button_watermoneyinfoitem1img.setImageResource(item1img);
        mhome_button_watermoneyinfoheadviewtext2.setText("许昌市");
        mhome_button_watermoneyinfoitem1text.setText("燃气费");
        home_button_watermoneyinfoitem2text1.setText("家庭");
        home_button_watermoneyinfoitem2text2.setText("我家");
        mhome_button_watermoneyinfoitem3text2.setText("许昌市天伦燃气有限公司");
        mhome_button_watermoneyinfoitem4text1.setText("户号");
        mhome_button_watermoneyinfoitem4text2.setText("查看纸质账单");
        mhome_button_energymoneyinfoitem5text1.setText("户号AA+10位数字,或10位数字");
        mhome_button_energymoneyinfoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mhome_button_watermoneyinfoitemsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomebuttonRanqimoneyinfo.this, Homeranqidetails.class);
                startActivity(intent);
            }
        });
    }
}
