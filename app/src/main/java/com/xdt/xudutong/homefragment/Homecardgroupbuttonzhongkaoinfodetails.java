package com.xdt.xudutong.homefragment;

import android.content.Intent;
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
import com.xdt.xudutong.bean.DsepgetSeniorEntranceExam;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\10 0010.
 */

public class Homecardgroupbuttonzhongkaoinfodetails extends BaseActivity {

    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext1;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext2;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext3;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext32;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext4;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext5;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext6;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext7;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext8;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext9;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext10;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext11;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext12;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext13;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext14;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext15;
    private TextView mhome_cardgroup_buttonzhongkaoinfodetailstext91;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttonzhongkaoinfodetails);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonzhongkaoinfodetails.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String zhongkaoidnumber = intent.getStringExtra("zhongkaoidnumber");
        String zhongkaoexamnumber = intent.getStringExtra("zhongkaoexamnumber");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        LinearLayout home_cardgroup_buttonzhongkaoinfodetailsback1 = (LinearLayout) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailsback);
        mhome_cardgroup_buttonzhongkaoinfodetailstext1 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext1);
        mhome_cardgroup_buttonzhongkaoinfodetailstext2 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext2);
        mhome_cardgroup_buttonzhongkaoinfodetailstext3 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext3);
        mhome_cardgroup_buttonzhongkaoinfodetailstext32 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext32);
        mhome_cardgroup_buttonzhongkaoinfodetailstext4 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext4);
        mhome_cardgroup_buttonzhongkaoinfodetailstext5 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext5);
        mhome_cardgroup_buttonzhongkaoinfodetailstext6 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext6);
        mhome_cardgroup_buttonzhongkaoinfodetailstext7 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext7);
        mhome_cardgroup_buttonzhongkaoinfodetailstext8 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext8);
        mhome_cardgroup_buttonzhongkaoinfodetailstext9 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext9);
        mhome_cardgroup_buttonzhongkaoinfodetailstext91 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext91);
        mhome_cardgroup_buttonzhongkaoinfodetailstext10 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext10);
        mhome_cardgroup_buttonzhongkaoinfodetailstext11 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext11);
        mhome_cardgroup_buttonzhongkaoinfodetailstext12 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext12);
        mhome_cardgroup_buttonzhongkaoinfodetailstext13 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext13);
        mhome_cardgroup_buttonzhongkaoinfodetailstext14 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext14);
        mhome_cardgroup_buttonzhongkaoinfodetailstext15 = (TextView) findViewById(R.id.home_cardgroup_buttonzhongkaoinfodetailstext15);
        home_cardgroup_buttonzhongkaoinfodetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        ShowVolleyRequestforuqueryzhongkao(zhongkaoidnumber, zhongkaoexamnumber);
    }

    private void ShowVolleyRequestforuqueryzhongkao(String zhongkaotext1, String zhongkaotext2) {
        String urltruename = ApiUrls.GETSENIORENTRANCEEXAM;
        Map<String, String> params = new HashMap<>();
        params.put("cardNum", zhongkaotext1);
        params.put("examNum", zhongkaotext2);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DsepgetSeniorEntranceExam dsepgetseniorentranceexam = gson.fromJson(response.toString(), DsepgetSeniorEntranceExam.class);
                        String code = dsepgetseniorentranceexam.getCode();
                        if (code.equals("R00001")) {
                          successcontent();
                            List<DsepgetSeniorEntranceExam.ContentBean.DataBean> dsepgetseniorentranceexamdata = dsepgetseniorentranceexam.getContent().getData();
                             DsepgetSeniorEntranceExam.ContentBean.DataBean dataBean = dsepgetseniorentranceexamdata.get(0);
                            showData(dataBean);
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

    private void showData(DsepgetSeniorEntranceExam.ContentBean.DataBean dataBean) {
        mhome_cardgroup_buttonzhongkaoinfodetailstext1.setText(dataBean.getFullName());
        mhome_cardgroup_buttonzhongkaoinfodetailstext2.setText(dataBean.getExOfYear() + "年  " + "毕业院校 :" + dataBean.getFinishSch());
        mhome_cardgroup_buttonzhongkaoinfodetailstext3.setText(dataBean.getAllresult());
        mhome_cardgroup_buttonzhongkaoinfodetailstext32.setText("/700");
        mhome_cardgroup_buttonzhongkaoinfodetailstext4.setText(dataBean.getExamNum());
        mhome_cardgroup_buttonzhongkaoinfodetailstext5.setText(dataBean.getCardNum());
        mhome_cardgroup_buttonzhongkaoinfodetailstext6.setText(dataBean.getLanguages()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext7.setText(dataBean.getMath()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext8.setText(dataBean.getForeignLanguage()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext9.setText(dataBean.getChemistry()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext91.setText(dataBean.getPhysical()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext10.setText(dataBean.getMoral()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext11.setText(dataBean.getHistory()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext12.setText(dataBean.getSports()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext13.setText(dataBean.getExperiment()+"分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext14.setText(dataBean.getCarePoints() + "分");
        mhome_cardgroup_buttonzhongkaoinfodetailstext15.setText(dataBean.getRegCare());
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

