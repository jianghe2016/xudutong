package com.xdt.xudutong.locallifefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.LocalLiferecycleDetailsnextAdapter;
import com.xdt.xudutong.bean.Localliferecycledetailsnext;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\7\4 0004.
 */

public class Localrecycledetailsnext extends BaseActivity {

    private RecyclerView locallife_localrecycledetailsrecycleview1;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallife_localrecycleviewdetailsnext);
        progressDialog = ProgressDialog.showDialog(Localrecycledetailsnext.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String locallifelocallifevalnum = intent.getStringExtra("locallifelocallifevalnum");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout locallife_localrecycledetailsnextback1 = (LinearLayout) findViewById(R.id.locallife_localrecycledetailsnextback);
        locallife_localrecycledetailsrecycleview1 = (RecyclerView) findViewById(R.id.locallife_localrecycledetailsnextrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        locallife_localrecycledetailsrecycleview1.setLayoutManager(layoutManager);
        locallife_localrecycledetailsnextback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        ShowVolleyRequest(locallifelocallifevalnum);
    }

    private void ShowVolleyRequest(String locallifelocallifevalnum) {
        String url = ApiUrls.SELECTCOMPANY;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("parentNum", locallifelocallifevalnum);
        params.put("levelNum", "3");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Localliferecycledetailsnext localliferecycledetailsnext = gson.fromJson(response.toString(), Localliferecycledetailsnext.class);
                        String desc = localliferecycledetailsnext.getDesc();
                        int flag = localliferecycledetailsnext.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<Localliferecycledetailsnext.ContentBean.DataBean> localliferecycledetailsnextdata = localliferecycledetailsnext.getContent().getData();
                            ShowData(localliferecycledetailsnextdata);
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
    }
    private void ShowData(List<Localliferecycledetailsnext.ContentBean.DataBean> localliferecycledetailsnextdata) {
        LocalLiferecycleDetailsnextAdapter localLiferecycleDetailsAdapter = new LocalLiferecycleDetailsnextAdapter(Localrecycledetailsnext.this, localliferecycledetailsnextdata);
        locallife_localrecycledetailsrecycleview1.setAdapter(localLiferecycleDetailsAdapter);
    }
    private void failcontent() {
        locallife_localrecycledetailsrecycleview1.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        locallife_localrecycledetailsrecycleview1.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
