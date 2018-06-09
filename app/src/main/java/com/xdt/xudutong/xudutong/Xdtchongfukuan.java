package com.xdt.xudutong.xudutong;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class Xdtchongfukuan extends BaseActivity {


    private LinearLayout mxdt_fukuanback;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_fukuan);
    }


    @Override
    public void initView() {
        mxdt_fukuanback = (LinearLayout) findViewById(R.id.xdt_fukuanback);
        initData();
    }

    private void initData() {
        mxdt_fukuanback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
