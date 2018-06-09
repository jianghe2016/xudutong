package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.VipisUserExist;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.Phonenumberreguest;
import com.xdt.xudutong.utils.TimerCount;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Personcenterforgetpassword extends BaseActivity {

    private Button mbutton;
    private EditText mperson_center_forget_possword1;
    private EditText mperson_center_forget_possword2;
    private Button mperson_center_forget_possword3;

    private String body;
    private LinearLayout person_center_forget_passwordback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_center_forget_password);
    }

    @Override
    public void initView() {
        //提交按钮
        mbutton = (Button) findViewById(R.id.person_twice_passwrod);
        mperson_center_forget_possword1 = (EditText) findViewById(R.id.person_center_forget_possword1);
        mperson_center_forget_possword2 = (EditText) findViewById(R.id.person_center_forget_possword2);
        //验证码按钮
        mperson_center_forget_possword3 = (Button) findViewById(R.id.person_center_forget_possword3);
        person_center_forget_passwordback1 = (LinearLayout) findViewById(R.id.person_center_forget_passwordback);
        initData();
    }

    private void initData() {
        person_center_forget_passwordback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        //获取屏幕上的账号和验证码

        //点击获取验证码
        mperson_center_forget_possword3.setOnClickListener(new View.OnClickListener() {
            private String mperson_center_forget_possword1text;

            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    mperson_center_forget_possword1text = mperson_center_forget_possword1.getText().toString();
                    boolean isreguextphonenumber = Phonenumberreguest.isreguextphonenumber(mperson_center_forget_possword1text);
                    if (isreguextphonenumber== true) {
                        if (!mperson_center_forget_possword1text.isEmpty()) {
                            TimerCount timer = new TimerCount(60000, 1000, mperson_center_forget_possword3);
                            timer.start();
                            ShowVolleyrequest(mperson_center_forget_possword1text);
                        } else {
                            ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("用户名不能为空");
                        }
                    }else{
                        ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("请输入正确的手机号");
                    }

                }
            }
        });
        //点击提交
        mbutton.setOnClickListener(new View.OnClickListener() {
            private String mperson_center_forget_possword1text;
            private String mperson_center_forget_possword2txt;

            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    mperson_center_forget_possword1text = mperson_center_forget_possword1.getText().toString();
                    mperson_center_forget_possword2txt = mperson_center_forget_possword2.getText().toString();
                    if (!mperson_center_forget_possword1text.isEmpty()) {
                        Pattern idNumPattern = Pattern.compile("[1][3458]\\d{9}");
                        Matcher b2 = idNumPattern.matcher(mperson_center_forget_possword1text);
                        if (b2.matches()==true){
                            if (!mperson_center_forget_possword2txt.isEmpty() && mperson_center_forget_possword2txt.equals(body)) {
                                Intent intent = new Intent(Personcenterforgetpassword.this, Persontwice_password.class);
                                //把账号和验证码传给下一个页面
                                intent.putExtra("mperson_center_forget_possword1text", mperson_center_forget_possword1text);
                                intent.putExtra("body", body);
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("请输入正确的验证码");
                            }
                        }else{
                            ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("请输入正确的手机号");
                        }

                    } else {
                        ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("用户名不能为空");
                    }
                }
            }
        });
    }

    private void ShowVolleyrequest(String mperson_center_forget_possword1text) {
        String url = ApiUrls.MSGVERIFY;
        //获取设备号仅限手机
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        LogUtil.d("手机设备为=", DEVICE_ID);
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", mperson_center_forget_possword1text);
        params.put("clientId", DEVICE_ID);
        params.put("appPlatform", "1002");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("验证码========",response+"");
                        Gson gson = new Gson();
                        VipisUserExist vipisuserexist = gson.fromJson(response.toString(), VipisUserExist.class);
                        String code = vipisuserexist.getCode();
                        String desc = vipisuserexist.getDesc();
                        if (code.equals("R00001")) {
                            body = vipisuserexist.getResponse().getBody();
                            LogUtil.d("收到的忘记密码的验证码为", body);
                        } else if (code.equals("R00002")) {
                            body = vipisuserexist.getResponse().getBody();
                            ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage(desc);
                        } else {
                            ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage(desc);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personcenterforgetpassword.this).showMessage("系统繁忙");
                LogUtil.e("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

}



