package com.xdt.xudutong.xudutong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
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
import com.xdt.xudutong.adapder.YingyewangdianRecyclerAdapter;
import com.xdt.xudutong.bean.BranchgetDistanceListBypage;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.Yingyewangdiandingwei;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.RecyclerViewDivider;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class Xdtyingyewangdian extends BaseActivity {
    private int pageNum = 2;
    private int refreshTime = 0;
    private int times = 0;
    private XRecyclerView mxdt_yingyewangdianrecycleview;
    private String yingyewangdianlongitudenowa;
    private String yingyewangdianlatitudenowb;
    private double yingyewangdianlatitudenow;
    private double yingyewangdianlongitudenow;
    private String yingyewangdianlocalsplace;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_yingyewangdian);
        progressDialog = ProgressDialog.showDialog(Xdtyingyewangdian.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        yingyewangdianlatitudenow = intent.getDoubleExtra("yingyewangdianlatitudenow", 34.027316628966226);
        yingyewangdianlongitudenow = intent.getDoubleExtra("yingyewangdianlongitudenow", 113.85178161066717);
        yingyewangdianlocalsplace = intent.getStringExtra("yingyewangdianlocalsplace");
        yingyewangdianlongitudenowa = yingyewangdianlongitudenow + "";
        yingyewangdianlatitudenowb = yingyewangdianlatitudenow + "";
        String c = "1";
        String d = "10";
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        LinearLayout mxdt_yingyewangdianback = (LinearLayout) findViewById(R.id.xdt_yingyewangdianback);
        mxdt_yingyewangdianrecycleview = (XRecyclerView) findViewById(R.id.xdt_yingyewangdianrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mxdt_yingyewangdianrecycleview.setLayoutManager(layoutManager);
        mxdt_yingyewangdianrecycleview.addItemDecoration(new RecyclerViewDivider(Xdtyingyewangdian.this, LinearLayoutManager.VERTICAL));
        mxdt_yingyewangdianrecycleview.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mxdt_yingyewangdianback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        ShowVolleyRequestforqueryjihuowangdian(yingyewangdianlongitudenowa, yingyewangdianlatitudenowb, c, d);
    }
    //113.85178161066717,34.027316628966226

    private void ShowVolleyRequestforqueryjihuowangdian(String yingyewangdianlongitudenowa, String yingyewangdianlatitudenowb, String c, String d) {
        String url = ApiUrls.GETDISTANCELISTBYPAGE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("branchLng", yingyewangdianlongitudenowa);
        params.put("branchLat", yingyewangdianlatitudenowb);
        params.put("pageNum", c);
        params.put("pageCount", d);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        BranchgetDistanceListBypage branchgetdistancelistbypage = gson.fromJson(response.toString(), BranchgetDistanceListBypage.class);
                        String desc = branchgetdistancelistbypage.getDesc().toString();
                        String code = branchgetdistancelistbypage.getCode();
                        if (code.equals("R00001")) {
                           successcontent();
                            List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata = branchgetdistancelistbypage.getContent().getData();
                            for (int i = 0; i < branchgetdistancelistbypagedata.size(); i++) {
                                String branchName = branchgetdistancelistbypagedata.get(i).getBranchName();
                                LogUtil.d("branchName=", branchName);
                            }
                            ShowData(branchgetdistancelistbypagedata);
                            //选出当月一号和当前日期
                            LogUtil.d("登录成功=", desc);
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

    private void ShowVolleyRequestforqueryjihuowangdian2(String branchLng, String branchLat, int pageNum, int pageCount, final List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata) {
        String url = ApiUrls.GETDISTANCELISTBYPAGE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("branchLng", branchLng);
        params.put("branchLat", branchLat);
        params.put("pageNum", pageNum + "");
        params.put("pageCount", pageCount + "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        BranchgetDistanceListBypage branchgetdistancelistbypage = gson.fromJson(response.toString(), BranchgetDistanceListBypage.class);
                        String desc = branchgetdistancelistbypage.getDesc().toString();
                        List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata2 = branchgetdistancelistbypage.getContent().getData();
                        String code = branchgetdistancelistbypage.getCode();
                        if (code.equals("R00001")) {
                            branchgetdistancelistbypagedata.addAll(branchgetdistancelistbypagedata2);
                            ShowData(branchgetdistancelistbypagedata);
                            LogUtil.d("登录成功=", desc);
                        } else {
                            ToastUtils.getInstance(Xdtyingyewangdian.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    private void ShowData(final List<BranchgetDistanceListBypage.ContentBean.DataBean> branchgetdistancelistbypagedata) {
        final YingyewangdianRecyclerAdapter myAdapter = new YingyewangdianRecyclerAdapter(Xdtyingyewangdian.this, branchgetdistancelistbypagedata);
        mxdt_yingyewangdianrecycleview.setAdapter(myAdapter);
        mxdt_yingyewangdianrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                //refresh data here
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //清空集合
                        branchgetdistancelistbypagedata.clear();
                        //请求数据
                        String c = "1";
                        String d = "10";
                        ShowVolleyRequestforqueryjihuowangdian(yingyewangdianlongitudenowa, yingyewangdianlatitudenowb, c, d);
                        //刷新列表
                        myAdapter.notifyDataSetChanged();
                        mxdt_yingyewangdianrecycleview.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            //滚动加载
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //不清空集合
                        int pageCount = 5;
                        //请求网络
                        //   ShowVolleyRequestforqueryjihuowangdian2(pageNum, pageCount, branchgetdistancelistbypagedata);
                        ShowVolleyRequestforqueryjihuowangdian2(yingyewangdianlongitudenowa, yingyewangdianlatitudenowb, pageNum, pageCount, branchgetdistancelistbypagedata);
                        //刷新列表
                        //关闭加载更多动画页面
                        mxdt_yingyewangdianrecycleview.loadMoreComplete();
                    }
                }, 3000);
                pageNum++;
            }
        });


        myAdapter.setOnItemClickListener(new YingyewangdianRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String branchLng = branchgetdistancelistbypagedata.get(position).getBranchLng();
                String branchLat = branchgetdistancelistbypagedata.get(position).getBranchLat();
                String branchAddress = branchgetdistancelistbypagedata.get(position).getBranchAddress();
                Double branchLat1 = Double.valueOf(branchLat);
                Double branchLng1 = Double.valueOf(branchLng);
                double[] doubles = bdToGaoDe(branchLat1, branchLng1);
                double aDouble0 = doubles[0];
                double aDouble1 = doubles[1];
                Bundle bundle = new Bundle();
                bundle.putString("yingyewangdainallbranchLat1", aDouble0 + "");
                bundle.putString("yingyewangdainallbranchLng1", aDouble1 + "");
                bundle.putString("yingyewangdianlocalsplace", yingyewangdianlocalsplace);
               /* bundle.putString("dingweilocallatitude", yingyewangdianlatitudenowb);
                bundle.putString("dingweilocallongitude", yingyewangdianlongitudenowa);*/
                Intent intent = new Intent(Xdtyingyewangdian.this, Yingyewangdiandingwei.class);
                LogUtil.d("发送的aDoublelat+++++=", aDouble0 + "");
                LogUtil.d("发送的aDoublelng+++++=", aDouble1 + "");
                LogUtil.d("发送的branchAddress+++++=", branchAddress + "");
                LogUtil.d("发送的当前定位111+++++=", yingyewangdianlatitudenowb + "");
                LogUtil.d("发送的当前定位222+++++=", yingyewangdianlongitudenowa + "");
                intent.putExtras(bundle);
                // 返回intent
                setResult(15, intent);
                finish();
            }
        });
    }

    private double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }
    private void failcontent() {
        mxdt_yingyewangdianrecycleview.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        mxdt_yingyewangdianrecycleview.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
