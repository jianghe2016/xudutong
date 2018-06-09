package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.xdt.xudutong.adapder.WuyedetailsRecyclerAdapter;
import com.xdt.xudutong.bean.ZwjfeegetBillInfos;
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
 * Created by Administrator on 2017\7\10 0010.
 */

public class Homecardgroupbuttonfournextdetails extends BaseActivity {

    private RecyclerView home_cardgroup_button4nextdetailsrecycleview1;
    private ProgressDialog progressDialog;
    private TextView mhome_cardgroup_button4nextdetailsrecycleviewitemtwo1;
    private TextView mhome_cardgroup_button4nextdetailsrecycleviewitemtwo2;
    private TextView mhome_cardgroup_button4nextdetailsrecycleviewitemtwo3;
    private TextView mhome_cardgroup_button4nextdetailsrecycleviewitemtwo4;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button4nextdetails);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonfournextdetails.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String gethouseId = intent.getStringExtra("houseId");
        if (gethouseId != null && !gethouseId.isEmpty()) {
            ShowVolleyRequest(gethouseId);
        } else {
            ToastUtils.getInstance(Homecardgroupbuttonfournextdetails.this).showMessage("系统繁忙");
        }
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        LinearLayout homebuttongroup_button4nextdetailsback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button4nextdetailsback);
        home_cardgroup_button4nextdetailsrecycleview1 = (RecyclerView) findViewById(R.id.home_cardgroup_button4nextdetailsrecycleview);
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo1 = (TextView) findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitemtwo1);
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo2 = (TextView) findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitemtwo2);
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo3 = (TextView) findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitemtwo3);
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo4 = (TextView) findViewById(R.id.home_cardgroup_button4nextdetailsrecycleviewitemtwo4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Homecardgroupbuttonfournextdetails.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_cardgroup_button4nextdetailsrecycleview1.setLayoutManager(layoutManager);
        homebuttongroup_button4nextdetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }

    private void ShowVolleyRequest(String gethouseId) {
        String url = ApiUrls.GETBILLINFOS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("house_id", gethouseId);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ZwjfeegetBillInfos zwjfeegetbillinfos = gson.fromJson(response.toString(), ZwjfeegetBillInfos.class);
                        int flag = zwjfeegetbillinfos.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<ZwjfeegetBillInfos.ContentBean.DataBean.FeeInfosBean> zwjfeegetbillinfosfee_infos = zwjfeegetbillinfos.getContent().getData().getFee_infos();
                            ZwjfeegetBillInfos.ContentBean.DataBean zwjfeegetbillinfosdata = zwjfeegetbillinfos.getContent().getData();
                            ShowDta(zwjfeegetbillinfosfee_infos, zwjfeegetbillinfosdata);
                            LogUtil.d("请求成功=====", "请求成功");
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

    private void ShowDta(List<ZwjfeegetBillInfos.ContentBean.DataBean.FeeInfosBean> zwjfeegetbillinfosfee_infos, ZwjfeegetBillInfos.ContentBean.DataBean zwjfeegetbillinfosdata) {
        WuyedetailsRecyclerAdapter wuyedetailsRecyclerAdapter = new WuyedetailsRecyclerAdapter(Homecardgroupbuttonfournextdetails.this, zwjfeegetbillinfosfee_infos);
        home_cardgroup_button4nextdetailsrecycleview1.setAdapter(wuyedetailsRecyclerAdapter);
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo1.setText("应收总金额: " + zwjfeegetbillinfosdata.getReceivable_amount());
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo3.setText("总滞纳金: " + zwjfeegetbillinfosdata.getLate_fee_amount());
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo2.setText("总优惠金额: " + zwjfeegetbillinfosdata.getPrivilege_amount());
        mhome_cardgroup_button4nextdetailsrecycleviewitemtwo4.setText(zwjfeegetbillinfosdata.getPay_amount());
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
