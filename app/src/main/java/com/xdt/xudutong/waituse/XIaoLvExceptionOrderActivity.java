package com.xdt.xudutong.waituse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapters.ExceptionOrderAdapter;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.ExceptionOrderBean;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/1.
 */

public class XIaoLvExceptionOrderActivity extends Base2Activity {

    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.list_view)
    ListView mListView;
    private List<ExceptionOrderBean.ContentEntity.DataEntity> mList;
    private List<ExceptionOrderBean.ContentEntity.DataEntity> totalList = new ArrayList<>();
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;
    private ExceptionOrderAdapter mExceptionOrderAdapter;
    private int pageNum = 0;
    private String mXdtcardNo;
    private ExceptionOrderBean mExceptionOrderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_order);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mSwiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.locallife_headviewtext));
        mXdtcardNo = getIntent().getStringExtra("xdtcardNo");
        mTvTitle.setText("异常订单");
        mExceptionOrderAdapter = new ExceptionOrderAdapter(totalList,this);
        mListView.setAdapter(mExceptionOrderAdapter);
        mSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                totalList.clear();
                pageNum = 0;
                initData();
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if ( scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        pageNum += 1;
                        initData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        initData();
    }

    private void initData() {
        LogUtil.e("pageNum==========",pageNum+"");
        mList = new ArrayList<>();
        String url = ApiUrls.XIAO_LV_EXCEPTION_ORDER;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageNum",pageNum+"");
        params.put("pageCount","10");
        params.put("cardfaceno",mXdtcardNo);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        mSwiperefreshlayout.setRefreshing(false);
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                                mExceptionOrderBean = new Gson().fromJson(response.toString(),ExceptionOrderBean.class);
                                mList = mExceptionOrderBean.getContent().getData();
                                totalList.addAll(mList);
                                Log.e("==========",mList.size()+"");
                                mExceptionOrderAdapter.notifyDataSetChanged();
                            } else {
                                ToastUtils.getInstance(XIaoLvExceptionOrderActivity.this).showMessage(response.get("desc").toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwiperefreshlayout.setRefreshing(false);
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
//                headers.put("access_token", token1);
//                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
