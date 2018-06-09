package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.KuaidiAdapter;
import com.xdt.xudutong.bean.KuaidiqueryKuaidiInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\9 0009.
 */


public class Homecardgroupbuttonkuaidiinfodetails extends BaseActivity {
    private KuaidiqueryKuaidiInfo kuaidiquerykuaidiinfo;
    private String accuratetarinqueryone;
    private RecyclerView cardbut55;
    private List<KuaidiqueryKuaidiInfo.ContentBean.DataBeanX.DataBean> kuaidiquerykuaidiinfodata;
    private String kuaidinumber1;
    private ProgressDialog progressDialog;
    private TextView mkuaiditopitem1;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;
    private Handler mHandler;
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttonkuaididetailsinfo);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonkuaidiinfodetails.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        mHandler = new Handler();
        Intent intent = getIntent();
        kuaidinumber1 = intent.getStringExtra("kuaidinumber1");
        ShowVolleyRequest(kuaidinumber1);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        cardbut55 = (RecyclerView) findViewById(R.id.cardlistview5);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Homecardgroupbuttonkuaidiinfodetails.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cardbut55.setLayoutManager(linearLayoutManager);
        mkuaiditopitem1 = (TextView) findViewById(R.id.kuaiditopitem1);
        TextView mkuaiditopitem2 = (TextView) findViewById(R.id.kuaiditopitem2);
        mkuaiditopitem2.setText(kuaidinumber1);
        LinearLayout home_cardgroup_buttonkuaididetailsinfoback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonkuaididetailsinfoback);
        home_cardgroup_buttonkuaididetailsinfoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }

    private void ShowVolleyRequest(String kuaidinumber1) {
        String url = ApiUrls.QUERYKUAIDIINFO;
        //Volley请求网络进行判断
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("num", kuaidinumber1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        kuaidiquerykuaidiinfo = gson.fromJson(response.toString(), KuaidiqueryKuaidiInfo.class);
                        int flag = kuaidiquerykuaidiinfo.getFlag();
                        if (flag == 1) {
                            final String com = kuaidiquerykuaidiinfo.getContent().getData().getCom();
                            String message = kuaidiquerykuaidiinfo.getContent().getData().getMessage();
                            if (message.equals("ok")) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        String accuratetarinqueryone = DataAnalysetwo.readFile3(Homecardgroupbuttonkuaidiinfodetails.this, com);
                                        mkuaiditopitem1.setText(accuratetarinqueryone);
                                        successcontent();
                                    }
                                });
                            } else {
                                failcontent();
                                ToastUtils.getInstance(Homecardgroupbuttonkuaidiinfodetails.this).showMessage(message);
                            }

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
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }

    private void failcontent() {
        cardbut55.setVisibility(View.GONE);
        progressDialog.dismiss();
        memptystates_layout.setVisibility(View.VISIBLE);
    }

    private void showData(List<KuaidiqueryKuaidiInfo.ContentBean.DataBeanX.DataBean> kuaidiquerykuaidiinfodata) {
        KuaidiAdapter kuaidiAdapter = new KuaidiAdapter(Homecardgroupbuttonkuaidiinfodetails.this, kuaidiquerykuaidiinfodata);
        cardbut55.setAdapter(kuaidiAdapter);
    }

    private void successcontent() {
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
        msearch_content.setVisibility(View.VISIBLE);
        cardbut55.setVisibility(View.VISIBLE);
        kuaidiquerykuaidiinfodata = kuaidiquerykuaidiinfo.getContent().getData().getData();
        showData(kuaidiquerykuaidiinfodata);
    }
}
