package com.xdt.xudutong.huiminbao;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.QiRiAnnualized.MyMarkerView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.HuiMinBaoYuEBean;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class FundsBalance extends BaseActivity implements View.OnClickListener {
    int currentImg = 0;
    private int[] image;
    private RelativeLayout mRelEarnings;
    private RelativeLayout mRelDeal;
    private Intent mIntent;
    private String mCardNo;
    private HuiMinBaoYuEBean mHuiMinBaoYuEBean;
    private TextView mMfundsbuyinlayoutmoney;
    private LineChart lcIncomDetails;
    private String mEndDate;
    private String mBeginDate;

    @Override
    public void initView() {
        //七日年化收益图代码开始
        lcIncomDetails = (LineChart) findViewById(R.id.chart1);

        // 视图描述
        lcIncomDetails.setDescription("七日年化收益");
        //
        lcIncomDetails.getLegend().setEnabled(false);
        //
        lcIncomDetails.setAlpha(0.5f);
        //
        lcIncomDetails.setBorderColor(Color.rgb(213, 216, 214));
        // 设置全视图是否可以点击
        lcIncomDetails.setTouchEnabled(true);
        // 设置视图每日收益是否可以点击
        lcIncomDetails.setDragEnabled(true);
        // 设置视图内其他区域是否可以点击
        lcIncomDetails.setScaleEnabled(false);

        lcIncomDetails.setPinchZoom(true);
        // 设置全视图背景色
        lcIncomDetails.setBackgroundColor(Color.WHITE);

        lcIncomDetails.setDrawGridBackground(true);
        // 表格视图背景色
        lcIncomDetails.setGridBackgroundColor(Color.WHITE);

        lcIncomDetails.setAutoScaleMinMaxEnabled(true);

        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        // set the marker to the chart
        lcIncomDetails.setMarkerView(mv);
        // enable/disable highlight indicators (the lines that indicate the
        // highlighted Entry)

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        XAxis xAxis = lcIncomDetails.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.enableGridDashedLine(2f, 2f, 0f);
        xAxis.setDrawGridLines(true);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAvoidFirstLastClipping(true);

        xAxis.setSpaceBetweenLabels(0);
        xAxis.setTextSize(10f);
        YAxis leftAxis = lcIncomDetails.getAxisLeft();
        leftAxis.setStartAtZero(false);
        leftAxis.enableGridDashedLine(2f, 2f, 0f);
        leftAxis.setDrawLimitLinesBehindData(false);
        leftAxis.setTypeface(tf);
        leftAxis.setTextSize(10f);
        leftAxis.setLabelCount(7, false);
        YAxis rightAxis = lcIncomDetails.getAxisRight();
        rightAxis.setEnabled(false);
        //
        setData();
        lcIncomDetails.animateX(2000);
        lcIncomDetails.animateY(2000);
        // mChart.animateXY(3000, 3000);

        lcIncomDetails.setScaleMinima(1f, 1f);

        // mChart.centerViewPort(10, 50);

        lcIncomDetails.getData().setHighlightEnabled(true);
        lcIncomDetails.highlightValue(lcIncomDetails.getHighestVisibleXIndex(), 1);

        lcIncomDetails.invalidate();
        lcIncomDetails.getHighestVisibleXIndex();

        //七日年化收益图代码结束
        image = new int[]{
                R.drawable.xut_open_eye,
                R.drawable.xdt_close_eye,
        };
        mRelEarnings = (RelativeLayout) findViewById(R.id.rel_earnings);
        mRelDeal = (RelativeLayout) findViewById(R.id.rel_deal);
        mRelEarnings.setOnClickListener(this);
        mRelDeal.setOnClickListener(this);
        LinearLayout mfundsbuyinlayoutback = (LinearLayout) findViewById(R.id.fundsbuyinlayoutback);
        LinearLayout mfundsbuyinlayouteyelin = (LinearLayout) findViewById(R.id.fundsbuyinlayouteyelin);
        TextView mfundsbuyinlayoutredemption = (TextView) findViewById(R.id.fundsbuyinlayoutredemption);
        TextView mfundsbuyinlayoutbuyin = (TextView) findViewById(R.id.fundsbuyinlayoutbuyin);
        final ImageView fundsbuyinlayouteye = (ImageView) findViewById(R.id.fundsbuyinlayouteye);
        mMfundsbuyinlayoutmoney = (TextView) findViewById(R.id.fundsbuyinlayoutmoney);
        mfundsbuyinlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfundsbuyinlayouteyelin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                fundsbuyinlayouteye.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
//                    if (mHuiMinBaoYuEBean.getContents().getData().getSumPurchaseAmount() != 0){
//                        mMfundsbuyinlayoutmoney.setText(mHuiMinBaoYuEBean.getContents().getData().getSumPurchaseAmount()+"");
//                    }else {
                        mMfundsbuyinlayoutmoney.setText("0.00");
                } else {
                    mMfundsbuyinlayoutmoney.setText("***,***");
                }
            }
        });
        mfundsbuyinlayoutbuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FundsBalance.this, Fundsbuyin.class);
                startActivity(intent);
            }
        });
        mfundsbuyinlayoutredemption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FundsBalance.this, Fundsredemption.class);
                startActivity(intent);
            }
        });
        //获取余额，收益
        initData();
        //获取七日年化收益
        initQiRiAnnualized();
    }

    //获取最近七天时间
    private void setData() {
        List<String> mList = new ArrayList<>();
        for (int i = -6;i <1;i++){
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Calendar c = Calendar.getInstance();
            //当前时间
            mBeginDate = format.format(new Date());
            //一周前时间
            c.setTime(new Date());
            c.add(Calendar.DATE, i);
            Date d = c.getTime();
            mEndDate = format.format(d);
            mList.add(mEndDate);
            Log.e("一周前时间=====：",mEndDate);
        }


        Log.e("现在时间***:",mBeginDate);
        Log.e("一周前时间=====：",mEndDate);
        String[] bb = {"1.25", "5.05", "1.75", "6.03", "1.88", "3.33", "2.93"};
        //设置收益折线弯折出小圆圈的颜色
        int[] circleColor =
                {Color.rgb(244, 117, 117), Color.rgb(244, 117, 117), Color.rgb(244, 117, 117), Color.rgb(244, 117, 117), Color.rgb(244, 117, 117), Color.rgb(244, 117, 117),
                        Color.GRAY};

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < mList.size(); i++) {
            xVals.add(mList.get(i));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < bb.length; i++) {
            yVals.add(new Entry(Float.parseFloat(bb[i]), i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet Line");
        set1.setDrawCubic(true); // 设置折线是否圆滑
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true); // 设置是否显示收益区域内得颜色
        set1.setDrawCircles(true); // �����
        set1.setDrawCircleHole(true);
        set1.setHighlightEnabled(false);
        set1.setCircleColors(circleColor);
        set1.setLineWidth(1f); //设置收益线宽
        set1.setCircleSize(3); // 折线玩着点的圆圈大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(255, 5, 5)); // ����������
        set1.setFillColor(Color.rgb(244, 117, 117));//设置收益范围内的颜色
        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);

        lcIncomDetails.setData(data);
    }

    private void initQiRiAnnualized() {
        String url = ApiUrls.HUI_MIN_BAO_JI_JIN_ZHEXIANTU;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("fundId","000848");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json("曲线图=======【"+response+"】");
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                            }else {
                                ToastUtils.getInstance(FundsBalance.this).showMessage(response.get("desc").toString());
                            }
                        } catch (Exception e) {
                            LogUtil.e(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

    private void initData() {
        try {
            mCardNo = RSAEncrypt.encrypt(ICBC.TEST_NUM,RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiUrls.HUI_MIN_BAO_YU_E;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("fundId","000848");
        params.put("cardNo", mCardNo);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                                mHuiMinBaoYuEBean = new Gson().fromJson(String.valueOf(response), HuiMinBaoYuEBean.class);
                                mMfundsbuyinlayoutmoney.setText(mHuiMinBaoYuEBean.getContents().getData().getSumPurchaseAmount()+"");
                                ToastUtils.getInstance(FundsBalance.this).showMessage(response.get("desc").toString());
                            }else {
                                ToastUtils.getInstance(FundsBalance.this).showMessage(response.get("desc").toString());
                                mMfundsbuyinlayoutmoney.setText("0.00");
                            }
                        } catch (Exception e) {
                            LogUtil.e(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }


    @Override
    public void setMyContentView() {
        setContentView(R.layout.funds1layout);
    }

    @Override
    public void setSteepStatusBar(boolean isSetStatusBar) {
        super.setSteepStatusBar(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_earnings:
                mIntent = new Intent().setClass(this,DealActivity.class);
                mIntent.putExtra("flag","0");
                startActivity(mIntent);
                break;
            case R.id.rel_deal:
                mIntent = new Intent().setClass(this,DealActivity.class);
                mIntent.putExtra("flag","2");
                startActivity(mIntent);
                break;
        }
    }
}
