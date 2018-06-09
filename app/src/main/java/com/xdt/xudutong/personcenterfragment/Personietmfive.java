package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
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
import com.xdt.xudutong.adapder.PersonpresssellectCollectpressAdapter;
import com.xdt.xudutong.bean.PressselectCollectPress;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.locallifefragment.LocalNewsmoreDetails;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Personietmfive extends BaseActivity {

    private RecyclerView personcollectrecycleview1;
    private String token1;
    private String token2;
    private ArrayList<PressselectCollectPress.ContentBean.DataBean> newslist;
    private PersonpresssellectCollectpressAdapter sceneryAdapter;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_collection);
    }

    @Override
    public void initView() {
        newslist = new ArrayList<>();
        LinearLayout personitemfiveback1 = (LinearLayout) findViewById(R.id.personitemfiveback);
        personcollectrecycleview1 = (RecyclerView) findViewById(R.id.personcollectrecycleview);
        personcollectrecycleview1.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        personcollectrecycleview1.setLayoutManager(llm);
        personitemfiveback1.setOnClickListener(new View.OnClickListener() {
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
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        if (token1 != null && !token1.isEmpty()) {
            ShowVolleyRequest();
        } else {
            ToastUtils.getInstance(Personietmfive.this).showMessage("会话已过期，请重新登录");
            Intent intent = new Intent(Personietmfive.this, Personuser_comein.class);
            startActivity(intent);
            finish();
        }

    }

    private void ShowVolleyRequest() {
        String url = ApiUrls.SELECTCOLLECTPRESS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        //    params.put("train_date", trainstartdata);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            PressselectCollectPress pressselectcollectpress = gson.fromJson(response.toString(), PressselectCollectPress.class);
                            List<PressselectCollectPress.ContentBean.DataBean> pressselectcollectpressdata = pressselectcollectpress.getContent().getData();
                            if (!pressselectcollectpressdata.isEmpty()) {
                                ShowData(pressselectcollectpressdata);
                            } else {
                                ToastUtils.getInstance(Personietmfive.this).showMessage("暂未收藏新闻");
                            }
                        } else {
                            ToastUtils.getInstance(Personietmfive.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(Personietmfive.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personietmfive.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void ShowData(final List<PressselectCollectPress.ContentBean.DataBean> pressselectcollectpressdata) {
        newslist.addAll(pressselectcollectpressdata);
        sceneryAdapter = new PersonpresssellectCollectpressAdapter(Personietmfive.this);
        sceneryAdapter.setDatas(newslist);
        personcollectrecycleview1.setAdapter(sceneryAdapter);
        sceneryAdapter.setOnItemClickListener(new PersonpresssellectCollectpressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Personietmfive.this, LocalNewsmoreDetails.class);
                int id = newslist.get(position).getID();
                String s = String.valueOf(id);
                intent.putExtra("newsdetailsid", s);
                startActivity(intent);
            }

            @Override
            public void onDelClick(int collectid, int position) {
                ShowVolleyRequestfordelect(collectid + "", position);
            }
        });
    }

    private void ShowVolleyRequestfordelect(String collectid, final int positions) {
        String url3 = ApiUrls.DELETECOLLECTPRESS;
        //删除收藏
        Map<String, String> params = new HashMap<String, String>();
        params.put("collectid", collectid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                            if (codestring.equals("R00001")) {
                                newslist.remove(positions);
                                sceneryAdapter.notifyDataSetChanged();
                                ToastUtils.getInstance(Personietmfive.this).showMessage("取消成功");
                            } else {
                                Object desc = response.get("desc");
                                ToastUtils.getInstance(Personietmfive.this).showMessage(desc + "");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personietmfive.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
}
