package com.xdt.xudutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.orhanobut.logger.Logger;
import com.wind.keyboard.OfoKeyboard;
import com.wind.keyboard.OfoKeyboardView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.widget.PayPsdInputView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DealPsdActivity extends Base2Activity implements View.OnClickListener{

    PayPsdInputView mPassword;
    private Button mBtn_next;
    private LinearLayout mBack;
    private TextView mTv_title;
    com.wind.keyboard.OfoKeyboardView mKeyboardView;
    private OfoKeyboard mKeyboard;
    private String mToken1;
    private String mToken2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_psd);
        initView();
    }

    private void initView() {
        mToken1 = SpUtils.getParam(this ,"access_token", "");
        mToken2 = SpUtils.getParam(this, "x_auth_token", "");
        //获取到keyboard对象
        mKeyboard = new OfoKeyboard(this);
        mPassword = (PayPsdInputView) findViewById(R.id.password);
        mKeyboardView = (OfoKeyboardView) findViewById(R.id.keyboard_view);
        mBtn_next = (Button)findViewById(R.id.btn_next);
        mBack = (LinearLayout) findViewById(R.id.back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_title.setText("修改交易密码");
        mBack.setOnClickListener(this);
        mPassword.setOnClickListener(this);
        mBtn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.password:
                mKeyboard.attachTo(mPassword,true);//eiditext绑定keyboard，false表示普通数字键盘
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_next:
                if (mPassword.getText().toString().length() == 6) {
                    initData();
                }else {
                    ToastUtils.getInstance(this).showMessage("请输入6位原交易密码");
                }
                break;
        }

    }

    private void initData() {
        String rsaSign = null;
        try {
            rsaSign = RSAEncrypt.encrypt(mPassword.getText().toString(),RSAEncrypt.getPublicKey(SettingNewPsdActivity.KEY));
            LogUtil.e("rsaSign==========",rsaSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ApiUrls.Verification_PAY_PSD;
        Map<String,String> params = new HashMap<>();
        params.put("payPassword",rsaSign);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        Object object = response.opt("code");
                        String desc = response.opt("desc").toString();
                        if ("R00001".equals(object.toString())){
                            ToastUtils.getInstance(DealPsdActivity.this).showMessage(desc);
                            Intent mIntent = new Intent().setClass(DealPsdActivity.this,SettingNewPsdActivity.class);
                            mIntent.putExtra("mark","2");
                            startActivity(mIntent);
                            finish();
                        }else {
                            Object desc2 = null;
                            try {
                                desc2 = response.get("desc");
                                ToastUtils.getInstance(DealPsdActivity.this).showMessage(desc2 + "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(DealPsdActivity.this).showMessage("系统繁忙");
                        Log.e("LOGIN-ERROR", error.getMessage(), error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", mToken1);
                headers.put("x_auth_token", mToken2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
}
