package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Librarygetbookinfo;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */

public class Homecardgroupbuttonsixnextnext extends BaseActivity {

    private ImageView buttonnextnext61;

    private TextView buttonnextnext69;
    private Librarygetbookinfo librarygetbookinfo;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button62);
        progressDialog = ProgressDialog.showDialog(Homecardgroupbuttonsixnextnext.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String isbnnumber = intent.getStringExtra("isbnnumber");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        ShowVolleyRequest(isbnnumber.trim());
        LinearLayout home_cardgroup_button61back1 = (LinearLayout) findViewById(R.id.home_cardgroup_button61back);
        home_cardgroup_button61back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonnextnext61 = (ImageView) findViewById(R.id.cardbuttonnextnext61);
        TextView buttonnextnext62 = (TextView) findViewById(R.id.cardbuttonnextnext62);
        TextView buttonnextnext63 = (TextView) findViewById(R.id.cardbuttonnextnext63);
        TextView buttonnextnext64 = (TextView) findViewById(R.id.cardbuttonnextnext64);
        TextView buttonnextnext65 = (TextView) findViewById(R.id.cardbuttonnextnext65);
        TextView buttonnextnext66 = (TextView) findViewById(R.id.cardbuttonnextnext66);
        TextView buttonnextnext67 = (TextView) findViewById(R.id.cardbuttonnextnext67);
        buttonnextnext69 = (TextView) findViewById(R.id.cardbuttonnextnext69);
        //接收上个页面用户输入的图书名字
        Intent intent0 = getIntent();
        String bookmetaTitle1 = intent0.getStringExtra("bookmetaTitle1");
        String author1 = intent0.getStringExtra("author1");
        //索书号,全部数量,可供借阅,所在馆,所在藏书地点
        String callno = intent0.getStringExtra("callno");
        String copycount = intent0.getStringExtra("copycount");
        String loanableCount = intent0.getStringExtra("loanableCount");
        String curlibname = intent0.getStringExtra("curlibname");
        String curlocalname = intent0.getStringExtra("curlocalname");

        if (bookmetaTitle1 != null && !bookmetaTitle1.isEmpty()) {
            buttonnextnext62.setText(bookmetaTitle1);
        }
        if (author1 != null && !author1.isEmpty()) {
            buttonnextnext63.setText(author1);
        }
        if (callno != null && !callno.isEmpty()) {
            buttonnextnext64.setText(callno);
        }
        if (curlibname != null && !curlibname.isEmpty()) {
            buttonnextnext65.setText(curlibname);
        }
        if (curlocalname != null && !curlocalname.isEmpty()) {
            buttonnextnext66.setText(curlocalname);
        }
        if (copycount != null && !copycount.isEmpty()&&loanableCount!=null&&!loanableCount.isEmpty()) {
            buttonnextnext67.setText(loanableCount + "/" + copycount);
        }
    }

    private void ShowVolleyRequest(String isbnnumber) {
        String url = ApiUrls.GETBOOKINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("isbn", isbnnumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        librarygetbookinfo = gson.fromJson(response.toString(), Librarygetbookinfo.class);
                        int flag = librarygetbookinfo.getFlag();
                        if (flag == 1) {
                            successcontent();
                            String coverUrl = librarygetbookinfo.getContent().getData().getCoverUrl();
                            Glide.with(Homecardgroupbuttonsixnextnext.this).load(coverUrl).into(buttonnextnext61);
                            buttonnextnext69.setText(librarygetbookinfo.getContent().getData().getSummary());
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

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
