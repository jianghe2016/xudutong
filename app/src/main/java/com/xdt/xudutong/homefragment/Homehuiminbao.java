package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\9\1 0001.
 */

public class Homehuiminbao extends BaseActivity {
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_huiminbao);
    }

    @Override
    public void initView() {
        LinearLayout homebuttongroup_buttonhuiminbaoback1 = (LinearLayout) findViewById(R.id.homebuttongroup_buttonhuiminbaoback);
        ImageView home_huiuminbaobutton = (ImageView) findViewById(R.id.home_huiuminbaobutton);
        homebuttongroup_buttonhuiminbaoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        home_huiuminbaobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homehuiminbao.this, Homehuiminbaodetails.class);
                startActivity(intent);
            }
        });
    }
}
