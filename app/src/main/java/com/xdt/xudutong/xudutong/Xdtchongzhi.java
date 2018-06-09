package com.xdt.xudutong.xudutong;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class Xdtchongzhi extends BaseActivity {

    private LinearLayout mxdt_chongzhiback;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_chongzhi);
    }

    @Override
    public void initView() {
        mxdt_chongzhiback = (LinearLayout) findViewById(R.id.xdt_chongzhiback);
        initData();
    }

    private void initData() {
        mxdt_chongzhiback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }


}
