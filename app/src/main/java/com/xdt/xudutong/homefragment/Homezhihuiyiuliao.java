package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Homezhihuiyiliaorecycleadapter;
import com.xdt.xudutong.bean.TianjianRequest;
import com.xdt.xudutong.tianjian.ASKWebViewUtilsActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\10\19 0019.
 */

public class Homezhihuiyiuliao extends BaseActivity {
    private ProgressDialog progressDialog;
    private RecyclerView mhome_zhihuiyiliaoactivity;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_zhihuiyiliao);
        progressDialog = ProgressDialog.showDialog(Homezhihuiyiuliao.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        final Intent intent = getIntent();
        String homevolleygetidcardNo1 = intent.getStringExtra("homevolleygetidcardNo1");
        String homevolleygetphoneNo1 = intent.getStringExtra("homevolleygetphoneNo1");
        String homevolleygetname1 = intent.getStringExtra("homevolleygetname1");
        String homehuji_code1 = intent.getStringExtra("homehuji_code1");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout homebuttongroup_zhihuiyiliaoback1 = (LinearLayout) findViewById(R.id.homebuttongroup_zhihuiyiliaoback);
        homebuttongroup_zhihuiyiliaoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mhome_zhihuiyiliaoactivity = (RecyclerView) findViewById(R.id.home_zhihuiyiliaoactivity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mhome_zhihuiyiliaoactivity.setLayoutManager(layoutManager);
        //天健接口请求
        ShowVolleyRequestfortianjian(homevolleygetidcardNo1, homevolleygetphoneNo1, homevolleygetname1, homehuji_code1);
    }

    private void ShowVolleyRequestfortianjian(String homevolleygetidcardNo1, String homevolleygetphoneNo1, String homevolleygetname1, String homehuji_code1) {
        // final String requestBody = "verbId=queryRoles&name=测试&idNo=500106198402262045&mobileTel=15310673622&homeId=411023205&deviceType=android";
        final String requestBody = "verbId=queryRoles&name=" + homevolleygetname1 + "&idNo=" + homevolleygetidcardNo1 + "&mobileTel=" + homevolleygetphoneNo1 + "&homeId=" + homehuji_code1 + "&deviceType=android";
        String urltruename = ApiUrls.TIANJIANACTION;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        LogUtil.d("tianjianresponseresponse", response + "");
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("flag");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("0")) {
                            TianjianRequest tianjianRequest = gson.fromJson(response.toString(), TianjianRequest.class);
                            List<TianjianRequest.DataBean> tianjianRequestdata = tianjianRequest.getData();
                            successcontent();
                            List listyiliaoimg = new ArrayList();
                            List listyiliaotext = new ArrayList();
                            final List listyiliaourl = new ArrayList();
                            for (int i = 0; i < tianjianRequestdata.size(); i++) {
                                TianjianRequest.DataBean dataBean1 = tianjianRequestdata.get(i);
                                String roleType = dataBean1.getRoleType();
                                Log.i("roleTyperoleType", roleType);
                                if (roleType.equals("2")) {
                                    listyiliaoimg.add(R.drawable.home_doctore);
                                    listyiliaotext.add("签约医生");
                                    listyiliaourl.add(dataBean1.getRoleAddress());
                                } else if (roleType.equals("1")) {
                                    listyiliaoimg.add(R.drawable.home_doctore2);
                                    listyiliaotext.add("家庭医生");
                                    listyiliaourl.add(dataBean1.getRoleAddress());
                                } else if (roleType.equals("3")) {
                                    listyiliaoimg.add(R.drawable.zhihuiyiliao_manager);
                                    listyiliaotext.add("管理层");
                                    listyiliaourl.add(dataBean1.getRoleAddress());
                                }
                            }
                            Homezhihuiyiliaorecycleadapter homezhihuiyiliaorecycleadapter = new Homezhihuiyiliaorecycleadapter(Homezhihuiyiuliao.this, listyiliaoimg, listyiliaotext);
                            mhome_zhihuiyiliaoactivity.setAdapter(homezhihuiyiliaorecycleadapter);
                            homezhihuiyiliaorecycleadapter.setOnItemClickListener(new Homezhihuiyiliaorecycleadapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Object o = listyiliaourl.get(position);
                                    Intent intent = new Intent(Homezhihuiyiuliao.this, ASKWebViewUtilsActivity.class);
                                    intent.putExtra("url", o.toString());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            failcontent();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                failcontent();
                LogUtil.d("请求天健失败error的数据为=", error.toString());
            }
        }) {

            @Override
            public String getBodyContentType() {
                return String.format("application/x-www-form-urlencoded; charset=utf-8");
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody.toString().getBytes("UTF-8");
                } catch (Exception e) {
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void failcontent() {
        mhome_zhihuiyiliaoactivity.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        mhome_zhihuiyiliaoactivity.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
