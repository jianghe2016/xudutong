package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.PersonpushinfomanagerAdapter;
import com.xdt.xudutong.bean.PushfindPush;
import com.xdt.xudutong.homefragment.MarqueeViewone;
import com.xdt.xudutong.locallifefragment.LocalNewsmoreDetails;
import com.xdt.xudutong.tianjian.ASKWebViewUtilsActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\9\27 0027.
 */

public class Personpushinfomanager extends BaseActivity {
    private int refreshTime = 0;
    private int times = 0;
    private XRecyclerView mperson_center_pushinfo_managerxrecycleview;
    private PersonpushinfomanagerAdapter personpushinfomanagerAdapter;
    private String username;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_center_pushinfo_manager);
    }
    @Override
    public void initView() {
        LinearLayout mperson_center_pushinfo_managersetting = (LinearLayout) findViewById(R.id.person_center_pushinfo_managersetting);
        LinearLayout mperson_center_pushinfo_managerback = (LinearLayout) findViewById(R.id.person_center_pushinfo_managerback);
        mperson_center_pushinfo_managerback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mperson_center_pushinfo_managersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personpushinfomanager.this, Personssecret.class);
                    intent.putExtra("secrstorpush", 2);
                    startActivity(intent);
                }
            }
        });
        mperson_center_pushinfo_managerxrecycleview = (XRecyclerView) findViewById(R.id.person_center_pushinfo_managerxrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mperson_center_pushinfo_managerxrecycleview.setLayoutManager(layoutManager);
        mperson_center_pushinfo_managerxrecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mperson_center_pushinfo_managerxrecycleview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        username = intent.getStringExtra("personusername");
        String pagernumber = "10";
        String a = "1";
        if (null != username) {
            VolleyRequestforpushinfomanager(username, pagernumber, a);
        }


    }

    private void VolleyRequestforpushinfomanager(String username, String pagercount, String pagernumber) {

        String url = ApiUrls.FINDPUSH;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("pushUser", username);
        params.put("pageCount", pagercount);
        params.put("pageNum", pagernumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PushfindPush pushfindPush = gson.fromJson(response.toString(), PushfindPush.class);
                        String code = pushfindPush.getCode();
                        if (code.equals("R00001")) {
                            List<PushfindPush.ContentBean.DataBean> pushfindPushdata = pushfindPush.getContent().getData();
                            ShowData(pushfindPushdata);
                        } else {
                            String desc = pushfindPush.getDesc();
                            ToastUtils.getInstance(Personpushinfomanager.this).showMessage(desc);
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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

    private void VolleyRequestforpushinfomanager2(String pushUser, String pageCount, String pageNum, final List<PushfindPush.ContentBean.DataBean> pushfindPushdata1) {

        String url = ApiUrls.FINDPUSH;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("pushUser", pushUser);
        params.put("pageCount", pageCount);
        params.put("pageNum", pageNum);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PushfindPush pushfindPush = gson.fromJson(response.toString(), PushfindPush.class);
                        String code = pushfindPush.getCode();
                        if (code.equals("R00001")) {
                            List<PushfindPush.ContentBean.DataBean> pushfindPushdata = pushfindPush.getContent().getData();
                            if (null != pushfindPushdata && !pushfindPushdata.isEmpty()) {
                                pushfindPushdata1.addAll(pushfindPushdata);
                                ShowData(pushfindPushdata1);
                            }
                        } else {
                            String desc = pushfindPush.getDesc();
                            ToastUtils.getInstance(Personpushinfomanager.this).showMessage(desc);
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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

    private void ShowData(final List<PushfindPush.ContentBean.DataBean> pushfindPushdata) {
        personpushinfomanagerAdapter = new PersonpushinfomanagerAdapter(Personpushinfomanager.this, pushfindPushdata);
        mperson_center_pushinfo_managerxrecycleview.setAdapter(personpushinfomanagerAdapter);
        mperson_center_pushinfo_managerxrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //清空集合
                        pushfindPushdata.clear();
                        //请求数据
                        String pagercount = "14";
                        String a = "1";
                        VolleyRequestforpushinfomanager(username, pagercount, a);
                        //刷新列表
                        personpushinfomanagerAdapter.notifyDataSetChanged();
                        mperson_center_pushinfo_managerxrecycleview.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.i("滚动记载了", times + "");

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        String pageCount = "2";
                        VolleyRequestforpushinfomanager2(username, pageCount, times + 8 + "", pushfindPushdata);
                        mperson_center_pushinfo_managerxrecycleview.loadMoreComplete();
                        personpushinfomanagerAdapter.notifyDataSetChanged();
                    }
                }, 1000);

                times++;
            }
        });

      /*  mAdapter = new MyAdapter(listData);
        mperson_center_pushinfo_managerxrecycleview.setAdapter(mAdapter);*/
        personpushinfomanagerAdapter.setOnItemClickListener(new PersonpushinfomanagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    PushfindPush.ContentBean.DataBean dataBean = pushfindPushdata.get(position);
                    String appAlias = dataBean.getAppAlias();
                    String extra = dataBean.getExtra();
                    switch (appAlias) {
                        case "certCheckPush":
                            //实名认证模块
                            //认证成功
                            Intent intent1 = new Intent(Personpushinfomanager.this, Personitemthreethree.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent1);
                            break;
                        case "certfalsePush":
                            //实名认证模块
                            //认证失败
                            Intent intent2 = new Intent(Personpushinfomanager.this, Personitemthreethreefail.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
                            break;
                        case "activityPush":
                            //公告
                            Intent intent3 = new Intent(Personpushinfomanager.this, MarqueeViewone.class);
                            intent3.putExtra("marqueeviewid", extra);
                            intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent3);
                            break;
                        case "citynewsPush":
                            //新闻
                            Intent intent4 = new Intent(Personpushinfomanager.this, LocalNewsmoreDetails.class);
                            intent4.putExtra("newsdetailsid", extra);
                            startActivity(intent4);
                            break;
                        case "tjpush":
                            //天健推送
                            String title = dataBean.getTitle();
                            Intent intent5 = new Intent(Personpushinfomanager.this, ASKWebViewUtilsActivity.class);
                            intent5.putExtra("jpush_topviewtext", title);
                            intent5.putExtra("url", extra);
                            startActivity(intent5);
                            break;
                        default:
                            System.out.println("other");
                    }
                }
            }
        });
    }


}
