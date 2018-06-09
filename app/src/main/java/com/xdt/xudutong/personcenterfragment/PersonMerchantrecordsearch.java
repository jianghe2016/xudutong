package com.xdt.xudutong.personcenterfragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.xdt.xudutong.adapder.HomeMerchantrecordrecycleveiwAdapter1;
import com.xdt.xudutong.bean.MerchantconsumeRecord;
import com.xdt.xudutong.bean.MerchantconsumeSummary;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class PersonMerchantrecordsearch extends BaseActivity {

    private XRecyclerView mhome_merchantrecordseearchrecycleview;
    private Animation mShowAction;
    private Animation mHiddenAction;
    private int year, monthOfYear, dayOfMonth;
    private int pageNum = 1;
    private List<MerchantconsumeRecord.ContentBean.ListBean> merchantconsumesummarylist1;
    private HomeMerchantrecordrecycleveiwAdapter1 homeMerchantrecordrecycleveiwAdapter1;
    private List<MerchantconsumeSummary.ContentBean.ListBean> merchantconsumesummarylist2;
    private String getmerchantslecturl;
    private TextView mhome_merchantrecordseearchalltotal;
    private TextView mhome_merchantrecordseearchallcount;
    private TextView mhome_merchantrecordseearchallmoney;
    private String loadinnetid;
    private String completemerchantslecturl;
    private String nowdate;
    private ImageView mhome_merchantrecordseearchnothingbackaround;
    private TextView mhome_merchantrecordseearchdetailsselectnetid;
    private JSONArray contentJSONArray;
    private TextView mhome_merchantrecordseearchdetailsselectdate1;
    private TextView mhome_merchantrecordseearchdetailsselectdate2;
    private JSONArray datas1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_merchantrecordseearch);
    }
    @Override
    public void initView() {
        final Intent intent = getIntent();
        loadinnetid = intent.getStringExtra("loadinnetid");
        String merchantslect = intent.getStringExtra("merchantslecttoptext");
        String loadinnetname = intent.getStringExtra("loadinnetname");
        getmerchantslecturl = intent.getStringExtra("merchantslecturl");
        completemerchantslecturl = ApiUrls.HOST + getmerchantslecturl;
        //completemerchantslecturl = "http://app.xcsjmykt.com:8001/xudutongapp" + getmerchantslecturl;
        LogUtil.d("url", completemerchantslecturl);
        LogUtil.d("getmerchantslecturl", getmerchantslecturl);
        datas1 = new JSONArray();
        LinearLayout mhome_merchantrecordseearchback = (LinearLayout) findViewById(R.id.home_merchantrecordseearchback);
        TextView mhome_merchantrecordseearchdetailstopviewtext = (TextView) findViewById(R.id.home_merchantrecordseearchdetailstopviewtext);
        mhome_merchantrecordseearchnothingbackaround = (ImageView) findViewById(R.id.home_merchantrecordseearchnothingbackaround);
        mhome_merchantrecordseearchdetailsselectdate1 = (TextView) findViewById(R.id.home_merchantrecordseearchdetailsselectdate1);
        mhome_merchantrecordseearchdetailsselectdate2 = (TextView) findViewById(R.id.home_merchantrecordseearchdetailsselectdate2);
        mhome_merchantrecordseearchalltotal = (TextView) findViewById(R.id.home_merchantrecordseearchalltotal);
        mhome_merchantrecordseearchallcount = (TextView) findViewById(R.id.home_merchantrecordseearchallcount);
        mhome_merchantrecordseearchallmoney = (TextView) findViewById(R.id.home_merchantrecordseearchallmoney);
        //headview旋转的箭头
        final ImageView home_merchantrecordseearchimg1rightjiantou1around = (ImageView) findViewById(R.id.home_merchantrecordseearchimg1rightjiantou1around);
        //headview旋转的箭头布局00
        final LinearLayout home_merchantrecordseearchimg1linearlayout = (LinearLayout) findViewById(R.id.home_merchantrecordseearchimg1linearlayout);
        //headview收缩的布局
        final LinearLayout mhome_merchantrecordseearchdateselectlinearlayout = (LinearLayout) findViewById(R.id.home_merchantrecordseearchdateselectlinearlayout);
        //headview提交
        TextView mhome_merchantrecordseearchsubmit = (TextView) findViewById(R.id.home_merchantrecordseearchsubmit);
        mhome_merchantrecordseearchdetailsselectnetid = (TextView) findViewById(R.id.home_merchantrecordseearchdetailsselectnetid);
        mhome_merchantrecordseearchrecycleview = (XRecyclerView) this.findViewById(R.id.home_merchantrecordseearchrecycleview);
        setxrecycleview();

        mhome_merchantrecordseearchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int i = monthOfYear + 1;
        Serializable serializable = (dayOfMonth + 1) < 10 ? "0" + dayOfMonth : dayOfMonth;
        nowdate = year + "-" + i + "-" + serializable;
        mhome_merchantrecordseearchdetailsselectdate1.setText(nowdate);
        mhome_merchantrecordseearchdetailsselectdate2.setText(nowdate);
        if (!TextUtils.isEmpty(loadinnetname)) {
            mhome_merchantrecordseearchdetailsselectnetid.setText(loadinnetname);
        }
        //不同页面的结果不同
        if (!TextUtils.isEmpty(merchantslect)) {
            mhome_merchantrecordseearchdetailstopviewtext.setText(merchantslect);
        }
        if (getmerchantslecturl.equals("merchant/transactionDetails")) {
            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, nowdate, nowdate, 0, 1, 10, 1);
        } else {
            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, nowdate, nowdate, 1, 1, 10, 1);
        }

        mhome_merchantrecordseearchsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String startdate = mhome_merchantrecordseearchdetailsselectdate1.getText().toString();
                    String enddate = mhome_merchantrecordseearchdetailsselectdate2.getText().toString();
                    if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                        if (null!=datas1) {
                            datas1.clear();
                            homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                        }
                        Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 0, 1, 10, 1);
                    } else {
                        if (null!=datas1){
                            datas1.clear();
                            homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                        }
                        Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 1, 1, 10, 1);
                    }
                }
            }
        });
        mhome_merchantrecordseearchdetailsselectdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(PersonMerchantrecordsearch.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                              int day) {
                            int mYear = year;
                            int mMonth = month;
                            int mDay = day;
                            mhome_merchantrecordseearchdetailsselectdate1.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);
                    datePickerDialog.show();
                }
            }
        });
        mhome_merchantrecordseearchdetailsselectdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(PersonMerchantrecordsearch.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                              int day) {
                            int mYear = year;
                            int mMonth = month;
                            int mDay = day;
                            mhome_merchantrecordseearchdetailsselectdate2.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);
                    datePickerDialog.show();
                }
            }
        });
        home_merchantrecordseearchimg1linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mhome_merchantrecordseearchdateselectlinearlayout.getVisibility() == view.GONE) {
                    home_merchantrecordseearchimg1rightjiantou1around.setImageDrawable(getResources().getDrawable(R.drawable.home_merchantrecordseearchimg1rightjiantou1));
                    mhome_merchantrecordseearchdateselectlinearlayout.setVisibility(View.VISIBLE);
                } else {
                    home_merchantrecordseearchimg1rightjiantou1around.setImageDrawable(getResources().getDrawable(R.drawable.home_merchantrecordseearchimg1rightjiantou2));
                    mhome_merchantrecordseearchdateselectlinearlayout.setVisibility(View.GONE);
                    mhome_merchantrecordseearchrecycleview.setPadding(0, 40, 0, 0);
                }
            }
        });
        mhome_merchantrecordseearchdetailsselectnetid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(PersonMerchantrecordsearch.this, PersonMerchantrecordsearchNetid.class);
                    intent.putExtra("loadinnetid", loadinnetid);
                    startActivityForResult(intent, 30);
                }
            }
        });
    }

    private void setxrecycleview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mhome_merchantrecordseearchrecycleview.setLayoutManager(layoutManager);

        mhome_merchantrecordseearchrecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mhome_merchantrecordseearchrecycleview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mhome_merchantrecordseearchrecycleview.setArrowImageView(R.drawable.ic_loading_rotate);
        homeMerchantrecordrecycleveiwAdapter1 = new HomeMerchantrecordrecycleveiwAdapter1(getmerchantslecturl);
        mhome_merchantrecordseearchrecycleview.setAdapter(homeMerchantrecordrecycleveiwAdapter1);
        mhome_merchantrecordseearchrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        pageNum = 1;
                        String startdate = mhome_merchantrecordseearchdetailsselectdate1.getText().toString();
                        String enddate = mhome_merchantrecordseearchdetailsselectdate2.getText().toString();
                        if (null != datas1) {
                            datas1.clear();
                        }
                        if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 0, 1, 10, 1);
                        } else {
                            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 1, 1, 10, 1);
                        }
                        homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                        mhome_merchantrecordseearchrecycleview.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                LogUtil.d("触发了滚动加载", "触发了滚动加载");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        String startdate = mhome_merchantrecordseearchdetailsselectdate1.getText().toString();
                        String enddate = mhome_merchantrecordseearchdetailsselectdate2.getText().toString();
                        if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 0, pageNum, 7, 2);
                        } else {
                            Requestexpendtransactiondetails(completemerchantslecturl, loadinnetid, startdate, enddate, 1, pageNum, 7, 2);
                        }
                        mhome_merchantrecordseearchrecycleview.loadMoreComplete();
                    }
                }, 2000);
                pageNum++;
            }
        });
    }

    private void Requestexpendtransactiondetails(final String url, String loadinnetid, final String startdate, final String enddate, int reportType, final int pageNum, int pageSize, final int myflag) {
        //加载更多传参
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", startdate);
        params.put("endTime", enddate);
        params.put("netid", loadinnetid);
        params.put("reportType", reportType + "");
        params.put("pageSize", pageSize + "");
        params.put("pageNum", pageNum + "");
        LogUtil.d("url=",url);
        LogUtil.d("startdate111111=", startdate.toString());
        LogUtil.d("reportType=", reportType+"");
        LogUtil.d("enddate222222=", enddate.toString());
        LogUtil.d("loadinnetid333333=", loadinnetid.toString());
        LogUtil.d("pageSize444444=", pageSize + "");
        LogUtil.d("pageNum555555=", pageNum + "");
        LogUtil.d("url566666=", url + "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String codestring = response.getString("code");
                    if (codestring.equals("R00001")) {
                        JSONObject content = response.getJSONObject("content");
                        mhome_merchantrecordseearchalltotal.setText(content.get("total").toString());
                        mhome_merchantrecordseearchallcount.setText(content.get("count").toString());
                        mhome_merchantrecordseearchallmoney.setText(content.get("countMoney").toString());
                        //取出的list集合
                        contentJSONArray = JSON.parseArray(content.getString("list"));
                        datas1.addAll(contentJSONArray);
                        /*JSONArray contentjsonarray2 = new JSONArray();
                        contentjsonarray2.addAll(contentJSONArray);*/
                        if (contentJSONArray.size() > 0) {
                            mhome_merchantrecordseearchnothingbackaround.setVisibility(View.INVISIBLE);
                            mhome_merchantrecordseearchrecycleview.setVisibility(View.VISIBLE);
                            homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                            homeMerchantrecordrecycleveiwAdapter1.setDatas(datas1);

                        } else {
                            if (myflag == 2) {
                                mhome_merchantrecordseearchnothingbackaround.setVisibility(View.INVISIBLE);
                                mhome_merchantrecordseearchrecycleview.setVisibility(View.VISIBLE);
                                //说明没有下一页的数据，显示没有更多数据了
                                LogUtil.d("没没有更多数据=", "没没有更多数据");
                                mhome_merchantrecordseearchrecycleview.loadMoreComplete();
                                ToastUtils.getInstance(PersonMerchantrecordsearch.this).showMessage("没有更多数据了");
                                // mhome_merchantrecordseearchrecycleview.setLoadingMoreEnabled(false);
                            } else {
                                //没有数据，显示没有数据的背景图
                                mhome_merchantrecordseearchnothingbackaround.setVisibility(View.VISIBLE);
                                mhome_merchantrecordseearchrecycleview.setVisibility(View.INVISIBLE);
                                ToastUtils.getInstance(PersonMerchantrecordsearch.this).showMessage("暂无查询结果");
                            }
                        }
                    } else {
                        String descstring = response.get("desc").toString();
                        ToastUtils.getInstance(PersonMerchantrecordsearch.this).showMessage(descstring);
                    }
                } catch (org.json.JSONException e) {
                    LogUtil.d("小绿页面被捕捉的异常为=", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(PersonMerchantrecordsearch.this).showMessage("系统繁忙");
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

    //充值交易明细
/*    private void ShowDataTopuptradingdetails(final String Completemerchantslecturls, final String startdate, final String enddate) {
        homeMerchantrecordrecycleveiwAdapter1.setDatas();
        for (int i = 0; i < finaljsonArray.length(); i++) {
            try {
                String s = finaljsonArray.get(i).toString();
                LogUtil.d("ssss=", s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        mhome_merchantrecordseearchrecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < finaljsonArray.length(); i++) {
                            finaljsonArray.remove(i);
                        }
                        if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                            Requestexpendtransactiondetails(Completemerchantslecturls, loadinnetid, startdate, enddate, 0, 1, 5, 1);
                        } else {
                            Requestexpendtransactiondetails(Completemerchantslecturls, loadinnetid, startdate, enddate, 1, 1, 5, 1);
                        }
                        homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                        mhome_merchantrecordseearchrecycleview.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                            Requestexpendtransactiondetails(Completemerchantslecturls, loadinnetid, startdate, enddate, 0, pageNum, 5, 2);
                        } else {
                            Requestexpendtransactiondetails(Completemerchantslecturls, loadinnetid, startdate, enddate, 1, pageNum, 5, 2);
                        }
                        // homeMerchantrecordrecycleveiwAdapter1.notifyDataSetChanged();
                        homeMerchantrecordrecycleveiwAdapter1.setDatas(contentJSONArray2);
                        mhome_merchantrecordseearchrecycleview.loadMoreComplete();
                    }
                }, 3000);

            }
        });
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30) {
            if (resultCode == 31 || resultCode == 32) {
                String listusersnetid = data.getStringExtra("listusersnetid");
                String listUsersname = data.getStringExtra("listUsersname");
                //开始请求返回结果
                mhome_merchantrecordseearchdetailsselectnetid.setText(listUsersname);
                if (getmerchantslecturl.equals("merchant/transactionDetails")) {
                    Requestexpendtransactiondetails(completemerchantslecturl, listusersnetid, nowdate, nowdate, 0, 1, 10, 1);
                } else {
                    Requestexpendtransactiondetails(completemerchantslecturl, listusersnetid, nowdate, nowdate, 1, 1, 10, 1);
                }
            }
        }
    }
}
