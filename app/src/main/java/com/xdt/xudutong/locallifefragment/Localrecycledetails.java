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
import com.xdt.xudutong.adapder.LocalLiferecycleDetailsAdapter;
import com.xdt.xudutong.bean.Localliferecycledetails;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\7\4 0004.
 */

public class Localrecycledetails extends BaseActivity {
    private ProgressDialog progressDialog;
    private RecyclerView locallife_localrecycledetailsrecycleview1;
    private int[] localliferecycleoneimgs1;
    private int[] localliferecycleoneimgs2;
    private int[] localliferecycleoneimgs3;
    private int[] localliferecycleoneimgs4;
    private int[] localliferecycleoneimgs5;
    private int[] localliferecycleoneimgs6;
    private List imglist;
    private int locallifelocallifeidone1;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallife_localrecycleviewdetails);
        progressDialog = ProgressDialog.showDialog(Localrecycledetails.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String locallifelocallifeidone = intent.getStringExtra("locallifelocallifeidone");
        locallifelocallifeidone1 = intent.getIntExtra("locallifelocallifeidoneid", 0);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout localrecycledetailsbacl1 = (LinearLayout) findViewById(R.id.localrecycledetailsbacl);
        locallife_localrecycledetailsrecycleview1 = (RecyclerView) findViewById(R.id.locallife_localrecycledetailsrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        locallife_localrecycledetailsrecycleview1.setLayoutManager(layoutManager);
        ShowVolleyRequest(locallifelocallifeidone);
        localrecycledetailsbacl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        initData();
    }


    private void initData() {
        localliferecycleoneimgs1 = new int[]{R.drawable.localliferecycledetailsone1, R.drawable.localliferecycledetailsone2,
                R.drawable.localliferecycledetailsone3, R.drawable.localliferecycledetailsone4};
        localliferecycleoneimgs2 = new int[]{R.drawable.localliferecycledetailstwo1, R.drawable.localliferecycledetailstwo2};
        localliferecycleoneimgs3 = new int[]{R.drawable.localliferecycledetailsthree1, R.drawable.localliferecycledetailsthree2,
                R.drawable.localliferecycledetailsthree3, R.drawable.localliferecycledetailsthree4,
                R.drawable.localliferecycledetailsthree5};
        localliferecycleoneimgs4 = new int[]{R.drawable.localliferecycledetailsfour1, R.drawable.localliferecycledetailsfour2,
                R.drawable.localliferecycledetailsfour3};
        localliferecycleoneimgs5 = new int[]{R.drawable.localliferecycledetailsfive1, R.drawable.localliferecycledetailsfive2,
                R.drawable.localliferecycledetailsfive3, R.drawable.localliferecycledetailsfive4, R.drawable.localliferecycledetailsfive5};
        localliferecycleoneimgs6 = new int[]{R.drawable.localliferecycledetailssix1, R.drawable.localliferecycledetailssix2,
                R.drawable.localliferecycledetailssix3, R.drawable.localliferecycledetailssix4,
                R.drawable.localliferecycledetailssix5, R.drawable.localliferecycledetailssix6, R.drawable.localliferecycledetailssix7};
        imglist = new ArrayList();
        imglist.add(localliferecycleoneimgs1);
        imglist.add(localliferecycleoneimgs2);
        imglist.add(localliferecycleoneimgs3);
        imglist.add(localliferecycleoneimgs4);
        imglist.add(localliferecycleoneimgs5);
        imglist.add(localliferecycleoneimgs6);
    }

    private void ShowVolleyRequest(String locallifelocallifeidone) {
        String url = ApiUrls.SELECTCOMPANY;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("parentNum", locallifelocallifeidone);
        params.put("levelNum", "2");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Localliferecycledetails localliferecycledetails = gson.fromJson(response.toString(), Localliferecycledetails.class);
                        String desc = localliferecycledetails.getDesc();
                        int flag = localliferecycledetails.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<Localliferecycledetails.ContentBean.DataBean> localliferecycledetailsdta = localliferecycledetails.getContent().getData();
                            ShowData(imglist.get(locallifelocallifeidone1), localliferecycledetailsdta);
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
            //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            //所以这个方法在此处是不需要的
            //    @Override
            //    protected Map<String, String> getParams() {
            //          Map<String, String> map = new HashMap<String, String>();
            //            map.put("name1", "value1");
            //            map.put("name2", "value2");

            //        return params;
            //    }
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

    private void ShowData(Object o, final List<Localliferecycledetails.ContentBean.DataBean> localliferecycledetailsdta) {
        LocalLiferecycleDetailsAdapter localLiferecycleDetailsAdapter = new LocalLiferecycleDetailsAdapter(Localrecycledetails.this, (int[]) o, localliferecycledetailsdta);
        locallife_localrecycledetailsrecycleview1.setAdapter(localLiferecycleDetailsAdapter);
        localLiferecycleDetailsAdapter.setOnItemClickListener(new LocalLiferecycleDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String valnum = localliferecycledetailsdta.get(position).getVALNUM();
                String val = localliferecycledetailsdta.get(position).getVAL();
                Intent intent = new Intent(Localrecycledetails.this, Localrecycledetailsnext.class);
                intent.putExtra("locallifelocallifevalnum", valnum);
                startActivity(intent);
            }
        });

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
