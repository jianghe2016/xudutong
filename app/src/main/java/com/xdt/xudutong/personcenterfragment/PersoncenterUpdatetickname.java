package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.xdt.xudutong.bean.VipupdateUserInfo;
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
 * Created by Administrator on 2017\7\5 0005.
 */

public class PersoncenterUpdatetickname extends BaseActivity {

    private EditText person_ticknameedittext1;
    private TextView person_ticknamebaocun1;
    private LinearLayout person_setticknameback1;
    private String token1;
    private String token2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_settickname);
    }

    @Override
    public void initView() {
        person_setticknameback1 = (LinearLayout) findViewById(R.id.person_setticknameback);
        person_ticknameedittext1 = (EditText) findViewById(R.id.person_ticknameedittext);
        person_ticknamebaocun1 = (TextView) findViewById(R.id.person_ticknamebaocun);
        initData();
    }
    private void initData() {

        person_setticknameback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        person_ticknamebaocun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
                token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
                String person_ticknameedittextstring = person_ticknameedittext1.getText().toString();
                if (person_ticknameedittextstring!=null&&!person_ticknameedittextstring.isEmpty()){
                    if (token1 !=null&&!token1.isEmpty()){
                        ShowVolleyRequest(person_ticknameedittextstring);
                    }else{
                        ToastUtils.getInstance(PersoncenterUpdatetickname.this).showMessage("系统繁忙");
                    }

                }else{
                    ToastUtils.getInstance(PersoncenterUpdatetickname.this).showMessage("输入不能为空");
                }
            }
        });

    }

    private void ShowVolleyRequest(final String person_ticknameedittextstring) {
        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("nickname", person_ticknameedittextstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ;
                        if (codestring.equals("R00001")) {
                            VipupdateUserInfo VipupdateUserInfo = gson.fromJson(response.toString(), VipupdateUserInfo.class);
                            // 设置返回数据
                            Bundle bundle = new Bundle();
                            bundle.putString("person_ticknameedittextstring", person_ticknameedittextstring);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            // 返回intent
                            setResult(14, intent);
                            finish();
                        } else {
                            ToastUtils.getInstance(PersoncenterUpdatetickname.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(PersoncenterUpdatetickname.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(PersoncenterUpdatetickname.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token",token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }


}
