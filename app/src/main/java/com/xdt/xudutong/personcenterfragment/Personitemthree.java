package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.Phonenumberreguest;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Personitemthree extends BaseActivity {

    private TextView person_next;
    private EditText truename1;
    private EditText trueidnumber1;
    private EditText truephonenumber1;
    private String truename1textstring;
    private String trueidnumber1textstring;
    private LinearLayout mperson_turename_delect1;
    private LinearLayout mperson_turename_delect2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_true_name);
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        String loadusername = intent.getStringExtra("loadusername");
        LinearLayout persontruenameback1 = (LinearLayout) findViewById(R.id.persontruenameback);
        person_next = (TextView) findViewById(R.id.person_nextturename);
        truename1 = (EditText) findViewById(R.id.name1);
        trueidnumber1 = (EditText) findViewById(R.id.idnumber1);
        truephonenumber1 = (EditText) findViewById(R.id.phonenumber1);
        mperson_turename_delect1 = (LinearLayout) findViewById(R.id.person_turename_delect1);
        mperson_turename_delect2 = (LinearLayout) findViewById(R.id.person_turename_delect2);
        persontruenameback1.setOnClickListener(new View.OnClickListener() {
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
        initData();
    }

    private void initData() {
        mperson_turename_delect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truename1.setText("");
            }
        });
        mperson_turename_delect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trueidnumber1.setText("");
            }
        });
        //下面有身份证号的正则

        person_next.setOnClickListener(new View.OnClickListener() {
            //保存一卡通注册所填的数据
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    truename1textstring = truename1.getText().toString();
                    trueidnumber1textstring = trueidnumber1.getText().toString();
                    //获取手机号码
                    String s = truephonenumber1.getText().toString();
                    Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
                    Matcher b2 = idNumPattern.matcher(trueidnumber1textstring);
                    boolean isreguextphonenumber = Phonenumberreguest.isreguextphonenumber(s);
                    if (b2.matches()) {
                        if (isreguextphonenumber == true) {
                            if (!truename1textstring.isEmpty() && !trueidnumber1textstring.isEmpty() && !s.isEmpty()) {
                                Intent intent = new Intent(Personitemthree.this, Persontruenameaddidcardphoto.class);
                                SpUtils.putParam(getApplicationContext(), "truename1textstring", truename1textstring);
                                SpUtils.putParam(getApplicationContext(), "trueidnumber1textstring", trueidnumber1textstring);
                                SpUtils.putParam(getApplicationContext(), "truephonenumber1textstring", s);
                                startActivity(intent);
                            } else {
                                ToastUtils.getInstance(Personitemthree.this).showMessage("输入不能为空");
                            }
                        } else {
                            ToastUtils.getInstance(Personitemthree.this).showMessage("请输入正确的手机号码");
                        }

                    } else {
                        ToastUtils.getInstance(Personitemthree.this).showMessage("请输入正确的证件号码");
                    }
                    SharedPreferences.Editor sharedata = getSharedPreferences("data", 0).edit();
                    sharedata.putString("name", "shenrenkui");
                    sharedata.commit();
                    SpUtils.putParam(getApplicationContext(), "truename1", truename1textstring.toString());
                    SpUtils.putParam(getApplicationContext(), "idcard1", trueidnumber1textstring);
                }
            }
        });

    }


}
