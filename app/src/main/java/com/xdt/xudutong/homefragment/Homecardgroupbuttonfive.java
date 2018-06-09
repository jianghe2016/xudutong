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
 * Created by Administrator on 2017/5/24.
 */

public class Homecardgroupbuttonfive extends BaseActivity {
    private EditText cardbt50;
    private LinearLayout homebuttongroup_button5back1;
    private TextView home_button_kuaidisubmit1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button5);
    }
    @Override
    public void initView() {
        homebuttongroup_button5back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button5back);
        cardbt50 = (EditText) findViewById(R.id.cardbutton50);
        home_button_kuaidisubmit1 = (TextView) findViewById(R.id.home_button_kuaidisubmit);
        initData();
    }

    private void initData() {
        homebuttongroup_button5back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    finish();
                }
            }
        });
        //快递查询按钮
        home_button_kuaidisubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String s = cardbt50.getText().toString();
                if (!s.isEmpty()) {
                    Intent intent = new Intent(Homecardgroupbuttonfive.this, Homecardgroupbuttonkuaidiinfodetails.class);
                    intent.putExtra("kuaidinumber1", s);
                    startActivity(intent);
                } else {
                    ToastUtils.getInstance(Homecardgroupbuttonfive.this).showMessage("输入不能为空");
                }
            }
            }
        });

    }
}

