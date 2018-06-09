package com.xdt.xudutong.waituse;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.LittlegreenbikerecordtwoXrecycleveiwAdapter;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\1\5 0005.
 */

public class LittlegreenBiketakerecordTwo extends Fragment {
    // 第一次可见时的状态
    private int pageNum = 1;
    private View view;
    private XRecyclerView mlittlegreenbikerecordtwoXrecycleview;
    private LittlegreenbikerecordtwoXrecycleveiwAdapter littlegreenbikerecordtwoXrecycleveiwAdapter;
    private JSONArray datas1;
    JSONArray littlegreenrecordjsonArray = null;
    private String xdtcardnumber;
    private Object data;
    private String s2;
    private String s1;
    private LinearLayout memptystates_layout2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.littlegreenbiketakerecordtwo, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        Bundle arguments = getArguments();
        xdtcardnumber = arguments.getString("xdtcardnumber");

        initview();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        String substring = format.substring(0, 6);
        initdata(substring+"01",format);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.LITTLEGREENSTARTTIME:
                data = event.data;
                String[] split = data.toString().split("-");
                LogUtil.d("splitsplit", "splitsplit" + split[0]+"---"+split[1]);
                if (null!=datas1){
                    datas1.clear();
                    littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
                }
                pageNum = 1;
                initdata(split[0], split[1]);
                break;

        }
    }//, String littlegreenstarttime, String littlegreenendtime
    private void initview() {
        datas1 = new JSONArray();
        mlittlegreenbikerecordtwoXrecycleview = (XRecyclerView) view.findViewById(R.id.littlegreenbikerecordtwoXrecycleview);
        memptystates_layout2 = (LinearLayout) view.findViewById(R.id.emptystates_layout2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mlittlegreenbikerecordtwoXrecycleview.setLayoutManager(linearLayoutManager);
        mlittlegreenbikerecordtwoXrecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mlittlegreenbikerecordtwoXrecycleview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mlittlegreenbikerecordtwoXrecycleview.setArrowImageView(R.drawable.ic_loading_rotate);
        littlegreenbikerecordtwoXrecycleveiwAdapter = new LittlegreenbikerecordtwoXrecycleveiwAdapter();
        mlittlegreenbikerecordtwoXrecycleview.setAdapter(littlegreenbikerecordtwoXrecycleveiwAdapter);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void initdata(final String littlegreenstarttime, final String littlegreenendtime) {
        int pagersize = 10;
        requestforlittlegreenrecordtwo(littlegreenstarttime, littlegreenendtime, 10, 1);
        if (null != datas1) {
            datas1.clear();
            littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
        }
        mlittlegreenbikerecordtwoXrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                if (null != datas1) {
                    datas1.clear();
                    littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
                }
                requestforlittlegreenrecordtwo(littlegreenstarttime,littlegreenendtime, 10, 1);
                mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestforlittlegreenrecordtwo(littlegreenstarttime,littlegreenendtime, 10, 2);
                    }
                }, 2000);

            }
        });
    }

    private void requestforlittlegreenrecordtwo(String littlegreenstarttime, String littlegreenendtime, int pagersize, final int myflag) {
        //查询小绿推送骑行记录
        String url = ApiUrls.WSBIKEAPPSCANCODECYCLINGRECORD;
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("startTime",littlegreenstarttime);
        stringMap.put("endTime", littlegreenendtime);
        stringMap.put("pageNum", pageNum + "");
        stringMap.put("searchStatus", "1");
        stringMap.put("cardfaceno", xdtcardnumber);
        stringMap.put("pageCount", pagersize + "");

   /*     stringMap.put("startTime","20180201");
        stringMap.put("endTime", "20180324");
        stringMap.put("pageNum", pageNum + "");
        stringMap.put("searchStatus", "0");
        stringMap.put("cardfaceno", "4127277186835");
        stringMap.put("pageCount", pagersize + "");*/
        LogUtil.d("startTime22",littlegreenstarttime );
        LogUtil.d("endTime22", littlegreenendtime);
        LogUtil.d("pageNum22", pageNum + "");
        LogUtil.d("searchStatus22", "0");
        LogUtil.d("cardfaceno22", xdtcardnumber + "");
        LogUtil.d("pageCount22", pagersize + "");
        JSONObject jsonObject = new JSONObject(stringMap);
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            private JSONObject content;
            private String desc;
            private String code;

            @Override
            public void onResponse(JSONObject response) {
                String s = response.toString();
                LogUtil.d("sssss222", s.toString());
                try {
                    code = response.getString("code");
                    desc = response.getString("desc");
                    content = response.getJSONObject("content");
                        if (null!=code&&code.equals("R00001")) {
                            littlegreenrecordjsonArray = JSON.parseArray(content.getString("data"));
                            if (myflag == 1) {
                                //下拉刷新
                                if (littlegreenrecordjsonArray.size() > 0) {
                                    s1 = littlegreenrecordjsonArray.getJSONObject(0).get("leasetime").toString().trim();
                                    LogUtil.d("sssssss1122",s1);
                                    datas1.addAll(littlegreenrecordjsonArray);
                                    littlegreenbikerecordtwoXrecycleveiwAdapter.setDatas(datas1);
                                    mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
                                } else {
                                    memptystates_layout2.setVisibility(View.VISIBLE);
                                    ToastUtils.getInstance(getContext()).showMessage("暂无查询结果");
                                }
                            } else if (myflag == 2) {
                                //滚动加载
                                if (littlegreenrecordjsonArray.size() > 0) {
                                    s2 = littlegreenrecordjsonArray.getJSONObject(0).get("leasetime").toString().trim();

                                   LogUtil.d("sssssss2222",s2);
                                    if (s1.equals(s2)) {
                                        ToastUtils.getInstance(getContext()).showMessage("没有更多数据了");
                                        mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                                    }else{
                                        datas1.addAll(littlegreenrecordjsonArray);
                                        littlegreenbikerecordtwoXrecycleveiwAdapter.setDatas(datas1);
                                        mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                                    }
                                } else {
                                    ToastUtils.getInstance(getContext()).showMessage("没有更多数据了");
                                    mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                                }
                            }
                            memptystates_layout2.setVisibility(View.GONE);
                        } else {
                            if (datas1.size() > 0) {
                                ToastUtils.getInstance(getContext()).showMessage("没有更多数据了");
                                mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
                                mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                                memptystates_layout2.setVisibility(View.GONE);
                            }else{
                                mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
                                mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                                memptystates_layout2.setVisibility(View.VISIBLE);
                            }
                        }

                } catch (Exception e) {
                    mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
                    mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mlittlegreenbikerecordtwoXrecycleview.refreshComplete();
                mlittlegreenbikerecordtwoXrecycleview.loadMoreComplete();
                ToastUtils.getInstance(getContext()).showMessage("系统繁忙");
                LogUtil.d("请求小绿error数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(getContext()).getRequestQueue().add(jsonRequest);

    }
}
