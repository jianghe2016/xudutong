package com.xdt.xudutong.homefragment;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\17 0017.
 */

public class Homebuttonwatermoneyinfo extends BaseActivity {

    private LinearLayout mhome_button_watermoneyinfoback;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_button_watermoneyinfo);
    }

    @Override
    public void initView() {
        mhome_button_watermoneyinfoback = (LinearLayout) findViewById(R.id.home_button_watermoneyinfoback);
        initData();
    }

    private void initData() {
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
