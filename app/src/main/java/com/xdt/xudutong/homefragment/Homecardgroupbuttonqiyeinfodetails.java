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
import com.xdt.xudutong.adapder.HomegetiRecyclerAdapter;
import com.xdt.xudutong.adapder.HomeqiyegerenRecyclerAdapter;
import com.xdt.xudutong.bean.DsepgetEnterprise;
import com.xdt.xudutong.bean.DsepgetPrivately;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class Homecardgroupbuttonqiyeinfodetails extends BaseActivity {
    private TextView mhome_cargroup_buttonqiyeinfodetailstext1;
    private TextView mhome_cargroup_buttonqiyeinfodetailstext2;
    private TextView mhome_cargroup_buttonqiyeinfodetailstext3;
    private TextView mhome_cargroup_buttonqiyeinfodetailstext4;
    private RecyclerView home_qiyequeryrecycleview1;
    private ProgressDialog progressDialog;
    private String getiheadtext1;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cargroup_buttonqiyeinfodetails);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonqiyeinfodetails.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        getiheadtext1 = intent.getStringExtra("getiheadtext1");
        String qiyeinfotext1 = intent.getStringExtra("qiyeinfotext1");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        TextView mhome_cargroup_buttonqiyeinfodetailstext4a = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext4a);
        TextView mhome_cargroup_buttonqiyeinfodetailstext21 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext21);
        TextView home_cargroup_buttonqiyeinfodetailsheadtext1 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailsheadtext);
        switch (getiheadtext1) {
            case "个体经营信息":
                home_cargroup_buttonqiyeinfodetailsheadtext1.setText("个体工商");
                mhome_cargroup_buttonqiyeinfodetailstext4a.setText("行业门类");
                mhome_cargroup_buttonqiyeinfodetailstext21.setText("经营者");
                ShowVolleyRequestforuquerygeti(qiyeinfotext1);
                break;
            case "企业信息":
                home_cargroup_buttonqiyeinfodetailsheadtext1.setText(getiheadtext1);
                ShowVolleyRequestforuqueryqiye(qiyeinfotext1);
                break;
            default:
                System.out.println("default");
                break;

        }



        LinearLayout home_cardgroup_buttonqiyeinfodetailsback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonqiyeinfodetailsback);
        mhome_cargroup_buttonqiyeinfodetailstext1 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext1);
        mhome_cargroup_buttonqiyeinfodetailstext2 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext2);
        mhome_cargroup_buttonqiyeinfodetailstext3 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext3);
        mhome_cargroup_buttonqiyeinfodetailstext4 = (TextView) findViewById(R.id.home_cargroup_buttonqiyeinfodetailstext4);

        home_qiyequeryrecycleview1 = (RecyclerView) findViewById(R.id.home_qiyequeryrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_qiyequeryrecycleview1.setLayoutManager(layoutManager);
        home_cardgroup_buttonqiyeinfodetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }

    private void ShowVolleyRequestforuqueryqiye(String qiyeinfotext1) {
        String urltruename = ApiUrls.GETENTERPRISE;
        Map<String, String> params = new HashMap<>();
        params.put("companyName", qiyeinfotext1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetEnterprise dsepgetenterprise = gson.fromJson(response.toString(), DsepgetEnterprise.class);
                        String code = dsepgetenterprise.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            List<DsepgetEnterprise.ContentBean.DataBean> dsepgetenterprisedata = dsepgetenterprise.getContent().getData();
                            showData(dsepgetenterprisedata);
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

    //个体
    private void ShowVolleyRequestforuquerygeti(String qiyeinfotext1) {
        String urltruename = ApiUrls.GETPRIVATELY;
        Map<String, String> params = new HashMap<>();
        params.put("traName", qiyeinfotext1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetPrivately dsepgetprivately = gson.fromJson(response.toString(), DsepgetPrivately.class);
                        String code = dsepgetprivately.getCode();
                        if (code.equals("R00001")) {
                            successcontent();
                            List<DsepgetPrivately.ContentBean.DataBean> dsepgetprivatelydata = dsepgetprivately.getContent().getData();
                            showDataforgeti(dsepgetprivatelydata);
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

    private void showData(List<DsepgetEnterprise.ContentBean.DataBean> dsepgetenterprisedata) {
        mhome_cargroup_buttonqiyeinfodetailstext1.setText(dsepgetenterprisedata.get(0).getCompanyName());
        mhome_cargroup_buttonqiyeinfodetailstext2.setText(dsepgetenterprisedata.get(0).getLegalPerson());
        mhome_cargroup_buttonqiyeinfodetailstext3.setText(dsepgetenterprisedata.get(0).getRegCap() + "万元");
        mhome_cargroup_buttonqiyeinfodetailstext4.setText(dsepgetenterprisedata.get(0).getRegCapName());
        String[] yanglaolisttext = new String[]{"统一社会信用代码", "登记注册号", "登记机关", "登记状态", "经营范围"};
        HomeqiyegerenRecyclerAdapter homeqiyegerenRecyclerAdapter = new HomeqiyegerenRecyclerAdapter(Homecardgroupbuttonqiyeinfodetails.this, dsepgetenterprisedata, yanglaolisttext);
        home_qiyequeryrecycleview1.setAdapter(homeqiyegerenRecyclerAdapter);
    }

    //个体
    private void showDataforgeti(List<DsepgetPrivately.ContentBean.DataBean> dsepgetprivatelydata) {
        mhome_cargroup_buttonqiyeinfodetailstext1.setText(dsepgetprivatelydata.get(0).getTraName());
        mhome_cargroup_buttonqiyeinfodetailstext2.setText(dsepgetprivatelydata.get(0).getOperatorName());
        mhome_cargroup_buttonqiyeinfodetailstext3.setText(dsepgetprivatelydata.get(0).getRegCap() + "万元");
        mhome_cargroup_buttonqiyeinfodetailstext4.setText(dsepgetprivatelydata.get(0).getIndustryPhy());
        String[] yanglaolisttext = new String[]{"统一社会信用代码", "登记注册号", "经营场所", "经营范围"};
        HomegetiRecyclerAdapter homeqiyegerenRecyclerAdapter = new HomegetiRecyclerAdapter(Homecardgroupbuttonqiyeinfodetails.this, dsepgetprivatelydata, yanglaolisttext);
        home_qiyequeryrecycleview1.setAdapter(homeqiyegerenRecyclerAdapter);
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
