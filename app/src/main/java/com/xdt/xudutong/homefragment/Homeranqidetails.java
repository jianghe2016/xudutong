package com.xdt.xudutong.homefragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\11\13 0013.
 */

public class Homeranqidetails extends BaseActivity {

    private TextView mhome_button_ranqidetailsubmitbutton;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_ranqidetails);
    }

    @Override
    public void initView() {
        LinearLayout mhome_ranqidetailstopback = (LinearLayout) findViewById(R.id.home_ranqidetailstopback);
        mhome_button_ranqidetailsubmitbutton = (TextView) findViewById(R.id.home_button_ranqidetailsubmitbutton);
        mhome_ranqidetailstopback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        mhome_button_ranqidetailsubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }
}
