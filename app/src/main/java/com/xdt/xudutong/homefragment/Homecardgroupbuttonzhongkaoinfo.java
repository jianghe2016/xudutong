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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttonzhongkaoinfo extends BaseActivity {

    private EditText mhome_button_zhongkaoinfoeditText1;
    private EditText mhome_button_zhongkaoinfoeditText2;
    private TextView mhome_button_zhongkaoinfosubmit;
    private LinearLayout home_cardgroup_buttonzhongkaoinfoback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttonzhongkaoinfo);
    }

    @Override
    public void initView() {
        home_cardgroup_buttonzhongkaoinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonzhongkaoinfoback);
        mhome_button_zhongkaoinfoeditText1 = (EditText) findViewById(R.id.home_button_zhongkaoinfoeditText1);
        mhome_button_zhongkaoinfoeditText2 = (EditText) findViewById(R.id.home_button_zhongkaoinfoeditText2);
        mhome_button_zhongkaoinfosubmit = (TextView) findViewById(R.id.home_button_zhongkaoinfosubmit);
        initData();
    }

    private void initData() {
        home_cardgroup_buttonzhongkaoinfoback1.setOnClickListener(new View.OnClickListener() {
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
        mhome_button_zhongkaoinfosubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String zhongkaotext1 = mhome_button_zhongkaoinfoeditText1.getText().toString();
                    String zhongkaotext2 = mhome_button_zhongkaoinfoeditText2.getText().toString();
                    Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
                    Matcher b2 = idNumPattern.matcher(zhongkaotext1);
                    if (!zhongkaotext1.isEmpty() && !zhongkaotext2.isEmpty()) {
                        if (b2.matches()) {
                            Intent intent = new Intent(Homecardgroupbuttonzhongkaoinfo.this, Homecardgroupbuttonzhongkaoinfodetails.class);
                            intent.putExtra("zhongkaoidnumber", zhongkaotext1);
                            intent.putExtra("zhongkaoexamnumber", zhongkaotext2);
                            startActivity(intent);
                        } else {
                            ToastUtils.getInstance(Homecardgroupbuttonzhongkaoinfo.this).showMessage("请输入正确的身份证号");
                        }
                    } else {
                        ToastUtils.getInstance(Homecardgroupbuttonzhongkaoinfo.this).showMessage("输入信息不能为空");
                    }
                }
            }
        });
    }


}
