package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.ProductRecyclerAdaptertrainresult;
import com.xdt.xudutong.bean.TrainTicketgetTrainTicket;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\23 0023.
 */

public class Homebuttongroupbuttonfivequeryresult extends BaseActivity {

    private TextView home_buttongroup_button5resulttextview1;
    private RecyclerView home_buttongroup_button5resultrecycle1;
    private ProgressDialog progressDialog;
    private String trainstartdata;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button5result);
        progressDialog = ProgressDialog.showDialog(Homebuttongroupbuttonfivequeryresult.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        //日期，出发地代号，目的地代号
        trainstartdata = intent.getStringExtra("trainstartdata");
        String accuratetarinqueryone1 = SpUtils.getParam(getApplicationContext(), "accuratetarinqueryone", "");
        String accuratetarinquerytwo1 = SpUtils.getParam(getApplicationContext(), "accuratetarinquerytwo", "");
        ShowVolleyRequest(accuratetarinqueryone1, accuratetarinquerytwo1, trainstartdata);
        LinearLayout homebuttongroup_button5resultback1 = (LinearLayout) findViewById(R.id.homebuttongroup_button5resultback);
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        home_buttongroup_button5resulttextview1 = (TextView) findViewById(R.id.home_buttongroup_button5resulttextview);
        home_buttongroup_button5resultrecycle1 = (RecyclerView) findViewById(R.id.home_buttongroup_button5resultrecycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        home_buttongroup_button5resultrecycle1.setLayoutManager(llm);
        homebuttongroup_button5resultback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }

            }
        });
        home_buttongroup_button5resulttextview1.setText(trainstartdata);
    }


    private void ShowVolleyRequest(String accuratetarinqueryone1, String accuratetarinquerytwo1, String trainstartdata) {
        String url = ApiUrls.GETTRAINTICKET;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("train_date", trainstartdata);
        params.put("from_station", accuratetarinqueryone1);
        params.put("to_station", accuratetarinquerytwo1);
        params.put("purpose_codes", "ADULT");
        LogUtil.d("train_date",trainstartdata);
        LogUtil.d("from_station",accuratetarinqueryone1);
        LogUtil.d("to_station",accuratetarinquerytwo1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (code1string.equals("R00001")) {
                            successcontent();
                            TrainTicketgetTrainTicket trainticketgettrainticket = gson.fromJson(response.toString(), TrainTicketgetTrainTicket.class);
                            List<TrainTicketgetTrainTicket.ContentBean.DataBean> trainticketgettrainticketdata = trainticketgettrainticket.getContent().getData();
                            ShowData(trainticketgettrainticketdata);
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                ToastUtils.getInstance(Homebuttongroupbuttonfivequeryresult.this).showMessage("系统繁忙");
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

    private void ShowData(List<TrainTicketgetTrainTicket.ContentBean.DataBean> trainticketgettrainticketdata) {
        ProductRecyclerAdaptertrainresult productRecyclerAdapter = new ProductRecyclerAdaptertrainresult(Homebuttongroupbuttonfivequeryresult.this, trainticketgettrainticketdata);
        home_buttongroup_button5resultrecycle1.setAdapter(productRecyclerAdapter);
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
