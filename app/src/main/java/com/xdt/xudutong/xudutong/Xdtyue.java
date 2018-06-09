package com.xdt.xudutong.xudutong;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;

/**
 * Created by Administrator on 2017\8\21 0021.
 */

public class Xdtyue extends BaseActivity {

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_yueresult);
    }

    @Override
    public void initView() {
        LinearLayout mhomebuttongroup_button311back = (LinearLayout) findViewById(R.id.xdt_yue_back);
        TextView mhome_balancenumber = (TextView) findViewById(R.id.home_yue_number);
        Intent intent = getIntent();
        String yuequeryresult1 = intent.getStringExtra("yuequeryresult");
        //  if (yuequeryresult1!=null&&!yuequeryresult1.isEmpty()){
        if (yuequeryresult1 != null && !yuequeryresult1.isEmpty()) {
            mhome_balancenumber.setText(yuequeryresult1);
        } else {
            setContentView(R.layout.search_empty);
            LinearLayout search_empty_back1 = (LinearLayout) findViewById(R.id.search_empty_back);
            TextView search_empty_toptext1 = (TextView) findViewById(R.id.search_empty_toptext);
            search_empty_toptext1.setText("余额");
            search_empty_back1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fastClick()) {
                        finish();
                    }
                }
            });
            ToastUtils.getInstance(Xdtyue.this).showMessage("网络繁忙");
        }

        mhomebuttongroup_button311back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }
}
