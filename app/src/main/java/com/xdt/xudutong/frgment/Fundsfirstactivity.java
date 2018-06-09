package com.xdt.xudutong.frgment;

import android.view.View;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2018\2\23 0023.
 */

public class Fundsfirstactivity extends BaseActivity {
    @Override
    public void initView() {
        View viewById = findViewById(R.id.hmb_x5webview);
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.fundsfirstactivitylayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(3);
    }
}
