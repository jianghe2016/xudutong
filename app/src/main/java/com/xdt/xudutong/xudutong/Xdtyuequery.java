package com.xdt.xudutong.xudutong;

import android.content.Context;
import android.content.Intent;
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
import com.xdt.xudutong.bean.CitygetBalance;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\21 0021.
 */

public class Xdtyuequery extends BaseActivity {

    private LinearLayout mxdt_yuequeryback;
    private EditText mxdt_yuetext1;
    private EditText mxdt_yuetext2;
    private TextView mxdt_yuesubmit;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_yuequery);
    }
    @Override
    public void initView() {
        mxdt_yuequeryback = (LinearLayout) findViewById(R.id.xdt_yuequeryback);
        mxdt_yuetext1 = (EditText) findViewById(R.id.xdt_yuetext1);
        mxdt_yuetext2 = (EditText) findViewById(R.id.xdt_yuetext2);
        mxdt_yuesubmit = (TextView) findViewById(R.id.xdt_yuesubmit);

        mxdt_yuequeryback.setOnClickListener(new View.OnClickListener() {
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
        mxdt_yuesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String s = mxdt_yuetext1.getText().toString();
                String s1 = mxdt_yuetext2.getText().toString();
                if (!s.isEmpty() && !s1.isEmpty()) {
                    ShowVolleyrequestforgetBalance(s, s1);
                } else {
                    ToastUtils.getInstance(Xdtyuequery.this).showMessage("输入不能为空");
                }
            }
        }
        });
    }

    private void ShowVolleyrequestforgetBalance(String s, String s1) {
        String url = ApiUrls.GETBALANCE;
        //Volley请求网络进行判断is);
        Map<String, String> params = new HashMap<String, String>();
        params.put("citycardno", s);
        params.put("searchPwd", s1);

        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CitygetBalance citygetbalance = gson.fromJson(response.toString(), CitygetBalance.class);
                        int flag = citygetbalance.getFlag();
                        String desc = citygetbalance.getDesc();
                        if (flag == 1) {
                            Double data = citygetbalance.getContent().getData();
                            Intent intent = new Intent(Xdtyuequery.this, Xdtyue.class);
                            intent.putExtra("yuequeryresult", data + "");
                            startActivity(intent);
                        } else {
                            ToastUtils.getInstance(Xdtyuequery.this).showMessage("卡号或密码不正确");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Xdtyuequery.this).showMessage("系统繁忙");
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
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }
}
