package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.xdt.xudutong.adapder.BusquerycityAdapter;
import com.xdt.xudutong.bean.MobilebusselectCity;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\26 0026.
 */

public class Homebuttongroupbuttonsixquerycity extends BaseActivity {

    private RecyclerView bus_querycityrecycle1;
    private List<MobilebusselectCity.ContentBean.DataBean> mobilebusselectcitydata;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void initView() {
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        LinearLayout homebuttongroup_button6querycityback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button6querycityback);
        bus_querycityrecycle1 = (RecyclerView) findViewById(R.id.bus_querycityrecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//LinearLayoutManager.HORIZONTAL
        bus_querycityrecycle1.setLayoutManager(layoutManager);
        homebuttongroup_button6querycityback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        ShowVolleyRequest();
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button6querycity);
        progressDialog = ProgressDialog.showDialog(Homebuttongroupbuttonsixquerycity.this);
        progressDialog.show();
    }


    private void ShowVolleyRequest() {
        String url = ApiUrls.SELECTCITY;
        //Volley请求网络进行判断
        ;
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        MobilebusselectCity mobilebusselectcity = gson.fromJson(response.toString(), MobilebusselectCity.class);
                        int flag = mobilebusselectcity.getFlag();
                        if (flag == 1) {
                            successcontent();
                            mobilebusselectcitydata = mobilebusselectcity.getContent().getData();
                            ShowData(mobilebusselectcitydata);
                        } else {
                            failcontent();
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void ShowData(final List<MobilebusselectCity.ContentBean.DataBean> mobilebusselectcitydata) {
        BusquerycityAdapter busquerycityadapter = new BusquerycityAdapter(Homebuttongroupbuttonsixquerycity.this, mobilebusselectcitydata);
        bus_querycityrecycle1.setAdapter(busquerycityadapter);
        busquerycityadapter.setOnItemClickListener(new BusquerycityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MobilebusselectCity.ContentBean.DataBean dataBean = mobilebusselectcitydata.get(position);
                String cityname = dataBean.getCityname();
                String cityid = dataBean.getCityid();
                Intent intent = new Intent(Homebuttongroupbuttonsixquerycity.this, Homebuttongroupbuttonsix.class);
                SpUtils.putParam(getApplicationContext(), "cityname", cityname);
                SpUtils.putParam(getApplicationContext(), "cityid", cityid);
                Bundle bundle = new Bundle();
                bundle.putString("cityname", cityname);
                bundle.putString("cityid", cityid);
                intent.putExtras(bundle);
                // 返回intent
                setResult(110, intent);
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
