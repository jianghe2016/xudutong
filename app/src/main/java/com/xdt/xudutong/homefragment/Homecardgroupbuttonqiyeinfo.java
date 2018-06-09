package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttonqiyeinfo extends BaseActivity {

    private LinearLayout home_cardgroup_buttonqiyeinfoback1;
    private EditText home_cardgroup_buttonqiyeinfotext1;
    private TextView home_cardgroup_buttonqiyeinfosubmit1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttonqiyeinfo);
    }

    @Override
    public void initView() {
        home_cardgroup_buttonqiyeinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonqiyeinfoback);
        home_cardgroup_buttonqiyeinfotext1 = (EditText) findViewById(R.id.home_cardgroup_buttonqiyeinfotext);
        home_cardgroup_buttonqiyeinfosubmit1 = (TextView) findViewById(R.id.home_cardgroup_buttonqiyeinfosubmit);
        initData();
    }

    private void initData() {
        home_cardgroup_buttonqiyeinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
                        Intent intent = new Intent(Homecardgroupbuttonqiyeinfo.this, Homecardgroupbuttonqiyeinfodetails.class);
                        intent.putExtra("qiyeinfotext1", qiyeinfotext1);
                        intent.putExtra("getiheadtext1", "企业信息");
                        startActivity(intent);
                    } else {
                        ToastUtils.getInstance(Homecardgroupbuttonqiyeinfo.this).showMessage("输入信息不能为空");
                    }
                }
            }
        });
    }

}
