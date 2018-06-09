package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Personitemthreethree extends BaseActivity {
    private TextView mperson_ture_name3_turename;
    private TextView mperson_ture_name3_tureidcard;
    private TextView personcenterturename4_next1;
    private String token1;
    private String token2;
    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_true_name3);

    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        int messageflag = intent.getIntExtra("messageflag",0);
        personcenterturename4_next1 = (TextView) findViewById(R.id.personcenterturename4_next);
        LinearLayout mperson_ture_name3_turenameback = (LinearLayout) findViewById(R.id.person_ture_name3_turenameback);
        mperson_ture_name3_turename = (TextView) findViewById(R.id.person_ture_name3_turename);
        mperson_ture_name3_tureidcard = (TextView) findViewById(R.id.person_ture_name3_tureidcard);
        mperson_ture_name3_turenameback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        initData();
    }

    private void initData() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        personcenterturename4_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        ShowVolleyRequestforcard();
    }

    private void ShowVolleyRequestforcard() {
        String url = ApiUrls.LOADUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        // params.put("train_date", trainstartdata);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String code1string;
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            String realName = viploaduserinfo.getContent().getData().getRealName();
                            String certId = viploaduserinfo.getContent().getData().getCertId();
                            String substring2 = certId.substring(certId.length() - 4, certId.length());
                            mperson_ture_name3_turename.setText("真实姓名 : " + realName);
                            mperson_ture_name3_tureidcard.setText("身份证号 : " + "**************" + substring2);
                        } else {
                            startActivity(new Intent(Personitemthreethree.this, Personuser_comein.class));
                            ToastUtils.getInstance(Personitemthreethree.this).showMessage("会话已过期，请重新登陆");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemthreethree.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

}
