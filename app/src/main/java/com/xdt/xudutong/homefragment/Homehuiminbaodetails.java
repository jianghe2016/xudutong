package com.xdt.xudutong.homefragment;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\9\1 0001.
 */

public class Homehuiminbaodetails extends BaseActivity {
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_huiminbaodetails);
    }

    @Override
    public void initView() {
        LinearLayout homebuttongroup_buttonhuiminbaodetailsback = (LinearLayout) findViewById(R.id.homebuttongroup_buttonhuiminbaodetailsback);

        homebuttongroup_buttonhuiminbaodetailsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }
}
