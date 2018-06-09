package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PubfundgetXcPubfund;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.utils.ToastUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Homeyanglaoquery extends BaseActivity {
    private TextView next_submit22;
    private List<PubfundgetXcPubfund.ContentBean.DataBean> card21data;
    private EditText useridnum;
    private LinearLayout home_cardgroup_button2back1;
    private TextView headtext1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_yanglaoquery);
    }
    @Override
    public void initView() {
        headtext1 = (TextView) findViewById(R.id.yanglaoheadtext);
        home_cardgroup_button2back1 = (LinearLayout) findViewById(R.id.home_yanglaosubmitback);
        useridnum = (EditText) findViewById(R.id.home_yanglaoidnumber);
        next_submit22 = (TextView) findViewById(R.id.home_yanglaosubmit);
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Finaltext.YILIAOREQUESTCODE) {
            if (resultCode == Finaltext.YILIAORESULTCODE) {
                String yanglaobaoxian2 = data.getStringExtra("yanglaobaoxian");
                Log.i("收到了。。。。", yanglaobaoxian2);
                headtext1.setText(yanglaobaoxian2);

            }
        }
    }

    private void initData() {
        Intent intent = getIntent();
        final String yanglaobaoxian = intent.getStringExtra("yanglaobaoxian");
        headtext1.setText(yanglaobaoxian);

        home_cardgroup_button2back1.setOnClickListener(new View.OnClickListener() {
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

        //提交按钮的逻辑
        next_submit22.setOnClickListener(new View.OnClickListener() {
            private String useridnumberstring;

            //点击提交按钮的逻辑
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String s = useridnum.getText().toString();
                    useridnumberstring = s;
                    //身份证号正则
                    Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
                    Matcher b2 = idNumPattern.matcher(useridnumberstring);
                    if (b2.matches()) {
                        Intent intent = new Intent(Homeyanglaoquery.this, Home_yanglaodetails.class);
                        intent.putExtra("yanglaoidcardnumber", useridnumberstring);
                        intent.putExtra("yanglaobaoxian", yanglaobaoxian);
                        startActivityForResult(intent, Finaltext.YILIAOREQUESTCODE);
                    } else {
                        ToastUtils.getInstance(Homeyanglaoquery.this).showMessage("请输入正确的身份证号");
                    }
                }
            }
        });
    }
}

