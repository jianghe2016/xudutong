package com.xdt.xudutong.locallifefragment;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.PressselectPress;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\7\3 0003.
 */

public class LocalFragmentNewsmoreDetails extends BaseActivity {
    private JSONObject jsonObject;
    private WebView locallifenewsmoredetailswebview1;
    private LinearLayout locallifenewsmoredetailsback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallifenewsmoredetails);
    }
    @Override
    public void initView() {
        locallifenewsmoredetailswebview1 = (WebView) findViewById(R.id.locallifenewsmoredetailswebview);
        locallifenewsmoredetailsback1 = (LinearLayout) findViewById(R.id.locallifenewsmoredetailsback);
        initData();
    }
    private void initData() {
        locallifenewsmoredetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        Intent intent = getIntent();
        //  locallifefragmentnewsid
        String locallifefragmentnewsid = intent.getStringExtra("locallifefragmentnewsid");
        WebSettings wSet = locallifenewsmoredetailswebview1.getSettings();
        wSet.setJavaScriptEnabled(true);
        String url = ApiUrls.SELECTPRESS;

        //请求网络数据展示到webview上去
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", locallifefragmentnewsid);
        jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String s;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PressselectPress pressselectpress = gson.fromJson(response.toString(), PressselectPress.class);
                        String desc = pressselectpress.getDesc().toString();
                        int flag = pressselectpress.getFlag();
                        if (flag == 1) {
                            List<PressselectPress.ContentBean.DataBean> pressselectpressdata = pressselectpress.getContent().getData();
                            for (int i = 0; i < pressselectpressdata.size(); i++) {
                                s = pressselectpressdata.get(i).getCONTENT();
                            }

                            locallifenewsmoredetailswebview1.loadDataWithBaseURL(null, s, "text/html", "utf-8", null);
                        } else {
                            String desc1 = pressselectpress.getDesc();
                            ToastUtils.getInstance(LocalFragmentNewsmoreDetails.this).showMessage(desc1);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(LocalFragmentNewsmoreDetails.this).showMessage("系统繁忙");
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
}
