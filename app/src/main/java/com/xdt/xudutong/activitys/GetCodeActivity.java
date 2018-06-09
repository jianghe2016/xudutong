package com.xdt.xudutong.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.arron.passwordview.PasswordView;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.VerifyCodemodifyPhoneMsgTest;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.CountDownTimerUtils;
import com.xdt.xudutong.utils.SPManager;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetCodeActivity extends Base2Activity implements PasswordView.PasswordListener{

    private String tag = getClass().getSimpleName();
    private PasswordView passwordView;
    private TextView mTv_phone;
    private LinearLayout mBack;
    private TextView mTv_title;
    private String mMobile;
    private TextView mTvTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_code);
        initView();
    }

    public void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mMobile = getIntent().getStringExtra("mobile");
        passwordView = (PasswordView) findViewById(R.id.passwordView);
        mTv_phone = (TextView) findViewById(R.id.tv_phone);
        mBack = (LinearLayout) findViewById(R.id.back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_title.setText("修改密码");
        mTv_phone.setText("验证码已发送至"+ mMobile.substring(0,3) + "****"+mMobile.substring(8,mMobile.length()));
        passwordView.setPasswordListener(this);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowVolleyrequest();
            }
        });
        ShowVolleyrequest();
    }

//    @Override
//    public void setMyContentView() {
//        setContentView(R.layout.activity_get_code);
//    }

    private void ShowVolleyrequest() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(mTvTime,60000,1000);
        countDownTimerUtils.start();
        String url = ApiUrls.MODIFYPHONEMSGTEST;
        //获取设备号仅限手机
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("phoneNum", mMobile);
        params.put("alias", DEVICE_ID);
        params.put("platform", "1004");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LogUtil.e("获取验证码onResponse=======",response+"");
                        Gson gson = new Gson();
                        VerifyCodemodifyPhoneMsgTest mverifycodemodifyphonemsgtest = gson.fromJson(response.toString(), VerifyCodemodifyPhoneMsgTest.class);
                        String code = mverifycodemodifyphonemsgtest.getCode();
                        String desc = mverifycodemodifyphonemsgtest.getDesc();

//                        LogUtil.e("code=======",SPManager.getInstance(GetCodeActivity.this).getCode());
//                        checkphonenumberdesc = mverifycodemodifyphonemsgtest.getDesc();
                        if (code.equals("R00001")|| code.equals("R00002")) {
                            if (code.equals("R00001")){

                            }else {

                            }
                            ApplicationController applicationContext = (ApplicationController)getApplicationContext();
                            applicationContext.saveCode(mverifycodemodifyphonemsgtest.getContent().getData());
                            LogUtil.e("code=======",mverifycodemodifyphonemsgtest.getContent().getData());
                            ToastUtils.getInstance(GetCodeActivity.this).showMessage(desc);
                        }else {
                            ToastUtils.getInstance(GetCodeActivity.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(GetCodeActivity.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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

//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//        if (passwordView.getPassword().toString().equals(SPManager.getInstance(this).getCode()) && passwordView.getPassword().length()==6){
//            Intent intent = new Intent().setClass(this,Verify_ID_NumberActivity.class);
//            startActivity(intent);
//        }
//    }

    @Override
    public void passwordChange(String changeText) {
        Log.e(tag, "changeText = " + changeText);
        if (passwordView.getPassword().length() == 6 && passwordView.getPassword().equals(SPManager.getInstance(this).getCode())){
            Intent intent = new Intent().setClass(this,Verify_ID_NumberActivity.class);
            startActivity(intent);
        }else {
            if (passwordView.getPassword().length()==6 && !passwordView.getPassword().equals(SPManager.getInstance(this).getCode())){
                ToastUtils.getInstance(this).showMessage("请输入正确验证码");
            }
            return;
        }
    }

    @Override
    public void passwordComplete() {
        Log.e(tag, "passwordComplete");
    }

    @Override
    public void keyEnterPress(String password, boolean isComplete) {
        Log.e(tag, "password = " + password + " isComplete = " + isComplete);
//        if (passwordView.getPassword().length() == 6 && passwordView.getPassword().equals(SPManager.getInstance(this).getCode())){
//            Intent intent = new Intent().setClass(this,Verify_ID_NumberActivity.class);
//            startActivity(intent);
//        }else {
//            ToastUtils.getInstance(this).showMessage("请输入正确验证码");
//            return;
//        }
    }

}
