package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttongetiinfo extends BaseActivity {

    private LinearLayout home_cardgroup_buttonqiyeinfoback1;
    private EditText home_cardgroup_buttonqiyeinfotext1;
    private TextView home_cardgroup_buttonqiyeinfosubmit1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttongetiinfo);
    }

    @Override
    public void initView() {
        home_cardgroup_buttonqiyeinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonshanghuinfoback);
        home_cardgroup_buttonqiyeinfotext1 = (EditText) findViewById(R.id.home_cardgroup_buttonshanghuinfotext);
        home_cardgroup_buttonqiyeinfosubmit1 = (TextView) findViewById(R.id.home_cardgroup_buttonshanghuinfosubmit);
        initData();
    }

    private void initData() {
        home_cardgroup_buttonqiyeinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        home_cardgroup_buttonqiyeinfosubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String qiyeinfotext1 = home_cardgroup_buttonqiyeinfotext1.getText().toString();
                    if (!qiyeinfotext1.isEmpty()) {
                        Intent intent = new Intent(Homecardgroupbuttongetiinfo.this, Homecardgroupbuttonqiyeinfodetails.class);
                        intent.putExtra("qiyeinfotext1", qiyeinfotext1);
                        intent.putExtra("getiheadtext1", "个体经营信息");
                        startActivity(intent);
                    }
                }
            }
        });
    }

}
