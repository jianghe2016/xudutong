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
import com.xdt.xudutong.adapder.HomejiguandanweiRecyclerAdapter;
import com.xdt.xudutong.bean.DsepgetGovernmentUnit;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttondanweijiguaninfodetails extends BaseActivity {
    private RecyclerView mhome_cardgroup_buttondanweijiguandetailsinforecycleview;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;
    private LinearLayout memptystates_layout1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttondanweijiguandetailsinfo);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttondanweijiguaninfodetails.this);
        progressDialog.show();
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        String jiguandanwei = intent.getStringExtra("jiguandanwei");
        ShowVolleyRequestforuqueryjiguandanwei(jiguandanwei);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        memptystates_layout1 = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout home_cardgroup_buttondanweijiguandetailsinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttondanweijiguandetailsinfoback);
        home_cardgroup_buttondanweijiguandetailsinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mhome_cardgroup_buttondanweijiguandetailsinforecycleview = (RecyclerView) findViewById(R.id.home_cardgroup_buttondanweijiguandetailsinforecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mhome_cardgroup_buttondanweijiguandetailsinforecycleview.setLayoutManager(layoutManager);
    }


    private void ShowVolleyRequestforuqueryjiguandanwei(String danwei) {
        String urltruename = ApiUrls.GETGOVERNMENTUNIT;
        Map<String, String> params = new HashMap<>();
        params.put("orgName", danwei);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetGovernmentUnit dsepgetgovernmentunit = gson.fromJson(response.toString(), DsepgetGovernmentUnit.class);
                        String code = dsepgetgovernmentunit.getCode();

                        if (code.equals("R00001")) {
                            successcontent();
                            List<DsepgetGovernmentUnit.ContentBean.DataBean> dsepgetgovernmentunitdata = dsepgetgovernmentunit.getContent().getData();
                            showData(dsepgetgovernmentunitdata);
                        } else {
                            failcontent();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(Homecardgroupbuttondanweijiguaninfodetails.this).showMessage("系统繁忙");
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

    private void showData(List<DsepgetGovernmentUnit.ContentBean.DataBean> dsepgetgovernmentunitdata) {
        HomejiguandanweiRecyclerAdapter homejiguandanweiRecyclerAdapter = new HomejiguandanweiRecyclerAdapter(Homecardgroupbuttondanweijiguaninfodetails.this, dsepgetgovernmentunitdata);
        mhome_cardgroup_buttondanweijiguandetailsinforecycleview.setAdapter(homejiguandanweiRecyclerAdapter);
    }

    private void failcontent() {
        mhome_cardgroup_buttondanweijiguandetailsinforecycleview.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        mhome_cardgroup_buttondanweijiguandetailsinforecycleview.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }

}
