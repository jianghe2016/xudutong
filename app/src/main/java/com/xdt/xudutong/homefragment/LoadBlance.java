package com.xdt.xudutong.homefragment;

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
import com.xdt.xudutong.bean.BuscardloadBlance;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */

public class LoadBlance extends BaseActivity {

    private TextView home_balancenumber1;
    private TextView home_balancedate1;
    private TextView home_yue_name1;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button311);
        progressDialog = ProgressDialog.showDialog(LoadBlance.this);
        progressDialog.show();
    }
    @Override
    public void initView() {
        Intent intent = getIntent();
        String getqueryloadblanceusername = intent.getStringExtra("queryloadblanceusername");
        String getzhaqueryloadblancepassword = intent.getStringExtra("zhaqueryloadblancepassword");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        //Volley请求网络余额数据
        String loadblanceurl = ApiUrls.LOADBlANCE;
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", getqueryloadblanceusername);
        params.put("certId", getzhaqueryloadblancepassword);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, loadblanceurl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        BuscardloadBlance buscardloadblance = gson.fromJson(response.toString(), BuscardloadBlance.class);
                        int flag = buscardloadblance.getFlag();
                        if (flag == 1) {
                            successcontent();
                            BuscardloadBlance.ContentBean.DataBean dataBean = buscardloadblance.getContent().getData().get(0);
                            double electronoddfare = dataBean.getElectronoddfare();
                            String opdt = dataBean.getOpdt();
                            String name = dataBean.getName();
                            home_yue_name1.setText(name);
                            home_balancenumber1.setText(electronoddfare + "");
                            home_balancedate1.setText("该余额截止日期到" + opdt);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
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
        LinearLayout homebuttongroup_button311back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button311back);
        home_balancenumber1 = (TextView) findViewById(R.id.home_balancenumber);
        home_balancedate1 = (TextView) findViewById(R.id.home_balancedate);
        home_yue_name1 = (TextView) findViewById(R.id.home_yue_name);
        homebuttongroup_button311back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void failcontent() {
        msearch_content.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        msearch_content.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}