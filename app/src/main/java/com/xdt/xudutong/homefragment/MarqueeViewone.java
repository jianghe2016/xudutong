package com.xdt.xudutong.homefragment;

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
import com.xdt.xudutong.bean.ActivitygetActivity;
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
 * Created by Administrator on 2017/5/24.
 */

public class MarqueeViewone extends BaseActivity {
    private JSONObject jsonObject;
    private WebView marqueeviewtext11;
    private LinearLayout marqueeviewoneback1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.marqueeviewone);
    }
    @Override
    public void initView() {
        marqueeviewoneback1 = (LinearLayout) findViewById(R.id.marqueeviewoneback);
        marqueeviewtext11 = (WebView) findViewById(R.id.marqueeviewtext1);
        Intent intent = getIntent();
        String marqueeviewid = intent.getStringExtra("marqueeviewid");
        WebSettings wSet = marqueeviewtext11.getSettings();
        wSet.setJavaScriptEnabled(true);
        //wView.loadUrl("file:///android_asset/index.html");
        //wView.loadUrl("content://com.android.htmlfileprovider/sdcard/index.html");
        //Volley请求网络进行判断
        marqueeviewoneback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        String url = ApiUrls.GETACTIVITY;
        Map<String, String> params = new HashMap<String, String>();
        params.put("id",marqueeviewid);
        jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String s;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ActivitygetActivity activitygetactivity = gson.fromJson(response.toString(), ActivitygetActivity.class);
                        String desc = activitygetactivity.getDesc().toString();
                        int flag = activitygetactivity.getFlag();
                        if(flag==1){
                            List<ActivitygetActivity.ContentBean.DataBean> marqueeview1 = activitygetactivity.getContent().getData();
                            LogUtil.d("登录跑马灯描述为=",desc);
                            for (int i = 0; i < marqueeview1.size(); i++) {
                                s = marqueeview1.get(i).getContent().toString();
                            }
                            marqueeviewtext11.loadDataWithBaseURL(null,s, "text/html", "utf-8", null);
                        }else{
                            ToastUtils.getInstance(MarqueeViewone.this).showMessage(desc);
                    }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=",error.toString());
                ToastUtils.getInstance(MarqueeViewone.this).showMessage("系统繁忙");
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
