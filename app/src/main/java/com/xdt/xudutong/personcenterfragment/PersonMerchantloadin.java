package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Merchantlogin;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class PersonMerchantloadin extends BaseActivity {
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_merchant_record);
    }
    @Override
    public void initView() {
        LinearLayout mperson_merchant_recordback = (LinearLayout) findViewById(R.id.person_merchant_recordback);
        final EditText mperson_merchant_recordloadin_eduittext1 = (EditText) findViewById(R.id.person_merchant_recordloadin_eduittext1);
        final EditText mperson_merchant_recordloadin_eduittext2 = (EditText) findViewById(R.id.person_merchant_recordloadin_eduittext2);

        // 新建一个可以添加属性的文本对象
        SpannableString ss1 = new SpannableString("用户名");
        SpannableString ss2 = new SpannableString("请输入您的密码");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(14, true);
        // 附加属性到文本
        ss1.setSpan(ass, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        mperson_merchant_recordloadin_eduittext1.setHint(new SpannedString(ss1)); // 一定要进行转换,否则属性会消失
        mperson_merchant_recordloadin_eduittext2.setHint(new SpannedString(ss2)); // 一定要进行转换,否则属性会消失
        TextView mperson_merchant_recordsubmit = (TextView) findViewById(R.id.person_merchant_recordsubmit);
        mperson_merchant_recordback.setOnClickListener(new View.OnClickListener() {
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
        mperson_merchant_recordsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String mperson_merchant_recordloadin_text1 = mperson_merchant_recordloadin_eduittext1.getText().toString();
                    String mperson_merchant_recordloadin_text2 = mperson_merchant_recordloadin_eduittext2.getText().toString();
                    if (!mperson_merchant_recordloadin_text1.isEmpty() && !mperson_merchant_recordloadin_text2.isEmpty()) {
                        ShowVolleyRequestformerchanloadin(mperson_merchant_recordloadin_text1, mperson_merchant_recordloadin_text2);
                    } else {
                        ToastUtils.getInstance(PersonMerchantloadin.this).showMessage("输入信息不能为空");
                    }
                }
            }
        });
    }


    private void ShowVolleyRequestformerchanloadin(String mperson_merchant_recordloadin_text1, String mperson_merchant_recordloadin_text2) {
        String url = ApiUrls.MERCHANTLOGIN;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("empCode", mperson_merchant_recordloadin_text1);
        params.put("empPwd", mperson_merchant_recordloadin_text2);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Merchantlogin Merchantlogindata = gson.fromJson(response.toString(), Merchantlogin.class);
                        String code = Merchantlogindata.getCode();
                        String desc = Merchantlogindata.getDesc();
                        if (code.equals("R00001")) {
                            String netid = Merchantlogindata.getContent().getNetid();
                            String netname = Merchantlogindata.getContent().getNetname();
                            Intent intent = new Intent(PersonMerchantloadin.this, PersonMerchantSelect.class);
                            intent.putExtra("loadinnetid", netid);
                            intent.putExtra("loadinnetname", netname);
                            LogUtil.d("网点netid=", netid);
                            startActivity(intent);
                        } else {
                            ToastUtils.getInstance(PersonMerchantloadin.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(PersonMerchantloadin.this).showMessage("系统繁忙");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

}
