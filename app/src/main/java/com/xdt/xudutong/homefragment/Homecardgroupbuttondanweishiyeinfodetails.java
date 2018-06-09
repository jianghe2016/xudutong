package com.xdt.xudutong.homefragment;

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
import com.xdt.xudutong.adapder.HomeshiyedanweiRecyclerAdapter;
import com.xdt.xudutong.bean.DsepgetInstitutionBean;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttondanweishiyeinfodetails extends BaseActivity {
    private RecyclerView mhome_cardgroup_buttondanweishiyedetailsinforecycle;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttondanweishiyedetailsinfo);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttondanweishiyeinfodetails.this);
        progressDialog.show();
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        String shiyedanwei = intent.getStringExtra("shiyedanwei");
        ShowVolleyRequestforuqueryshiyedanwei(shiyedanwei);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout home_cardgroup_buttondanweishiyedetailsinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttondanweishiyedetailsinfoback);
        mhome_cardgroup_buttondanweishiyedetailsinforecycle = (RecyclerView) findViewById(R.id.home_cardgroup_buttondanweishiyedetailsinforecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mhome_cardgroup_buttondanweishiyedetailsinforecycle.setLayoutManager(layoutManager);
        home_cardgroup_buttondanweishiyedetailsinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }

    private void ShowVolleyRequestforuqueryshiyedanwei(String danwei) {
        String urltruename = ApiUrls.GETINSTITUTIONBEAN;
        Map<String, String> params = new HashMap<>();
        params.put("orgName", danwei);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetInstitutionBean dsepgetinstitutionbean = gson.fromJson(response.toString(), DsepgetInstitutionBean.class);
                        String code = dsepgetinstitutionbean.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            List<DsepgetInstitutionBean.ContentBean.DataBean> dsepgetinstitutionbeandata = dsepgetinstitutionbean.getContent().getData();
                            showData(dsepgetinstitutionbeandata);
                            LogUtil.d("请求事业单位成功=", "请求事业单位成功");
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

    private void showData(List<DsepgetInstitutionBean.ContentBean.DataBean> dsepgetinstitutionbeandata) {
        HomeshiyedanweiRecyclerAdapter homejiguandanweiRecyclerAdapter = new HomeshiyedanweiRecyclerAdapter(Homecardgroupbuttondanweishiyeinfodetails.this, dsepgetinstitutionbeandata);
        mhome_cardgroup_buttondanweishiyedetailsinforecycle.setAdapter(homejiguandanweiRecyclerAdapter);
    }

    private void failcontent() {
        mhome_cardgroup_buttondanweishiyedetailsinforecycle.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        mhome_cardgroup_buttondanweishiyedetailsinforecycle.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
