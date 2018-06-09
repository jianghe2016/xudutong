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
import com.xdt.xudutong.adapder.SceneryAdapter;
import com.xdt.xudutong.bean.ViewselectView;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\28 0028.
 */

public class Scenery extends BaseActivity {

    private RecyclerView locallife_scenery_recyclefirst1;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallife_scenery);
        progressDialog = ProgressDialog.showDialog(Scenery.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout locallife_sceneryback1 = (LinearLayout) findViewById(R.id.locallife_sceneryback);
        locallife_scenery_recyclefirst1 = (RecyclerView) findViewById(R.id.locallife_scenery_recyclefirst);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        locallife_scenery_recyclefirst1.setLayoutManager(llm);
        locallife_sceneryback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        ShowVolleyRequest();
    }


    private void ShowVolleyRequest() {
        String url = ApiUrls.SELECTVIEW;
        //Volley请求网络进行判断
        //请求网络图片
        Map<String, String> params = new HashMap<String, String>();
        //params.put("train_date", trainstartdata);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ViewselectView viewselectview = gson.fromJson(response.toString(), ViewselectView.class);
                        int flag = viewselectview.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<ViewselectView.ContentBean.DataBean> viewselectviewdata = viewselectview.getContent().getData();
                            ShowData(viewselectviewdata);
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

    private void ShowData(final List<ViewselectView.ContentBean.DataBean> viewselectviewdata) {
        SceneryAdapter sceneryAdapter = new SceneryAdapter(Scenery.this, viewselectviewdata);
        locallife_scenery_recyclefirst1.setAdapter(sceneryAdapter);
        sceneryAdapter.setOnItemClickListener(new SceneryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = viewselectviewdata.get(position).getID();
                //拿到id转化为string方便传递
                String sceneryid = String.valueOf(id);
                String view_name = viewselectviewdata.get(position).getVIEW_NAME();
                Intent intent = new Intent(Scenery.this, SceneryDetails.class);
                intent.putExtra("sceneryid", sceneryid);
                startActivity(intent);
            }
        });
    }

    private void failcontent() {
        locallife_scenery_recyclefirst1.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        locallife_scenery_recyclefirst1.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
