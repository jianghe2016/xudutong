package com.xdt.xudutong.personcenterfragment;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ProtocolgetProtocol;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\9\16 0016.
 */

public class PersonZhuce extends BaseActivity {
    private WebView locallifenewsmoredetailswebview1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallifenewsmoredetails);
    }



    @Override
    public void initView() {
        LinearLayout locallifenewsmoredetailsback1 = (LinearLayout) findViewById(R.id.locallifenewsmoredetailsback);
        locallifenewsmoredetailswebview1 = (WebView) findViewById(R.id.locallifenewsmoredetailswebview);
        TextView mnewstopview = (TextView) findViewById(R.id.newstopview);
        mnewstopview.setText("用户注册协议");
        locallifenewsmoredetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        WebSettings wSet = locallifenewsmoredetailswebview1.getSettings();
        wSet.setJavaScriptEnabled(true);
        String url = ApiUrls.GETPROTOCOL;
        //请求网络数据展示到webview上去
        Map<String, String> params = new HashMap<String, String>();
        params.put("point", "register");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ProtocolgetProtocol protocolgetprotocol = gson.fromJson(response.toString(), ProtocolgetProtocol.class);

                        int flag = protocolgetprotocol.getFlag();
                        if (flag == 1) {
                            String content = protocolgetprotocol.getContent().getData().get(0).getContent();
                            locallifenewsmoredetailswebview1.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                        } else {
                            String desc = protocolgetprotocol.getDesc().toString();
                            ToastUtils.getInstance(PersonZhuce.this).showMessage(desc);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(PersonZhuce.this).showMessage("系统繁忙");
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
