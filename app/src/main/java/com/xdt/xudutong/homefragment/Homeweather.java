package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.WeathergetAirQuality;
import com.xdt.xudutong.bean.WeathergetWeather;
import com.xdt.xudutong.bean.WeathergetWeatherList;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.utils.Reguestgetnumberforweather;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ProgressDialog;
import com.xdt.xudutong.view.WeatherItem;
import com.xdt.xudutong.view.WeatnerChartView;
import com.xdt.xudutong.view.WeatnerChartViewtwo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Homeweather extends BaseActivity {

    private WeatnerChartViewtwo chart2;
    private WeatnerChartView chart1;
    private TextView mweather_linear1text1;
    private TextView mweather_linear1text2;
    private TextView mweather_linear1text3;
    private TextView mweather_linear1text4;
    private TextView mweather_linear1text5;
    private TextView mweather_linear1text6;
    private TextView mweather_linear2text1;
    private TextView mweather_linear2text2;
    private TextView mweather_linear2text3;
    private TextView mweather_linear2text4;
    private TextView mweather_linear2text5;
    private TextView mweather_linear2text6;
    private TextView mweather_linear3text1;
    private TextView mweather_linear3text2;
    private TextView mweather_linear3text3;
    private TextView mweather_linear3text4;
    private TextView mweather_linear3text5;
    private TextView mweather_linear3text6;
    private TextView mweather_linear4text1;
    private TextView mweather_linear4text2;
    private TextView mweather_linear4text3;
    private TextView mweather_linear4text4;
    private TextView mweather_linear4text5;
    private TextView mweather_linear4text6;
    private TextView mweather_linear5text1;
    private TextView mweather_linear5text2;
    private TextView mweather_linear5text3;
    private TextView mweather_linear5text4;
    private TextView mweather_linear5text5;
    private TextView mweather_linear5text6;
    private TextView mweather_text1;
    private TextView mweather_text2;
    private TextView mweather_text3;
    private TextView mweather_text4;
    private TextView mweather_text5;
    private TextView mweather_text6;
    private TextView mhome_weather_add2;
    private LinearLayout home_weatherback1;
    private LinearLayout mhpme_weathertopview1;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_weather);
        progressDialog = ProgressDialog.showDialog(Homeweather.this);
        progressDialog.show();
    }
    @Override
    public void initView() {
        //获取下个页面保存的城市代号
        String spgetareaid = SpUtils.getParam(Homeweather.this, "areaid", "101180401");
        String spgetcitypinyin = SpUtils.getParam(Homeweather.this, "nameen", "xuchang");
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        home_weatherback1 = (LinearLayout) findViewById(R.id.home_weatherback);
        mhome_weather_add2 = (TextView) findViewById(R.id.home_weather_add2);
        mhpme_weathertopview1 = (LinearLayout) findViewById(R.id.hpme_weathertopview1);
        //顶部的六个控件
        mweather_text1 = (TextView) findViewById(R.id.weather_text1);
        mweather_text2 = (TextView) findViewById(R.id.weather_text2);
        mweather_text3 = (TextView) findViewById(R.id.weather_text3);
        mweather_text4 = (TextView) findViewById(R.id.weather_text4);
        mweather_text5 = (TextView) findViewById(R.id.weather_text5);
        mweather_text6 = (TextView) findViewById(R.id.weather_text6);
        //**日
        mweather_linear1text1 = (TextView) findViewById(R.id.weather_linear1text1);
        mweather_linear1text2 = (TextView) findViewById(R.id.weather_linear1text2);
        mweather_linear1text3 = (TextView) findViewById(R.id.weather_linear1text3);
        mweather_linear1text4 = (TextView) findViewById(R.id.weather_linear1text4);
        mweather_linear1text5 = (TextView) findViewById(R.id.weather_linear1text5);
        mweather_linear1text6 = (TextView) findViewById(R.id.weather_linear1text6);
        //昨天今天，周一，周二，周三
        mweather_linear2text1 = (TextView) findViewById(R.id.weather_linear2text1);
        mweather_linear2text2 = (TextView) findViewById(R.id.weather_linear2text2);
        mweather_linear2text3 = (TextView) findViewById(R.id.weather_linear2text3);
        mweather_linear2text4 = (TextView) findViewById(R.id.weather_linear2text4);
        mweather_linear2text5 = (TextView) findViewById(R.id.weather_linear2text5);
        mweather_linear2text6 = (TextView) findViewById(R.id.weather_linear2text6);
        chart1 = (WeatnerChartView) findViewById(R.id.weather_char1);
        chart2 = (WeatnerChartViewtwo) findViewById(R.id.weather_char2);
        //底部三个均分的横向列表
        mweather_linear3text1 = (TextView) findViewById(R.id.weather_linear3text1);
        mweather_linear3text2 = (TextView) findViewById(R.id.weather_linear3text2);
        mweather_linear3text3 = (TextView) findViewById(R.id.weather_linear3text3);
        mweather_linear3text4 = (TextView) findViewById(R.id.weather_linear3text4);
        mweather_linear3text5 = (TextView) findViewById(R.id.weather_linear3text5);
        mweather_linear3text6 = (TextView) findViewById(R.id.weather_linear3text6);

        mweather_linear4text1 = (TextView) findViewById(R.id.weather_linear4text1);
        mweather_linear4text2 = (TextView) findViewById(R.id.weather_linear4text2);
        mweather_linear4text3 = (TextView) findViewById(R.id.weather_linear4text3);
        mweather_linear4text4 = (TextView) findViewById(R.id.weather_linear4text4);
        mweather_linear4text5 = (TextView) findViewById(R.id.weather_linear4text5);
        mweather_linear4text6 = (TextView) findViewById(R.id.weather_linear4text6);

        mweather_linear5text1 = (TextView) findViewById(R.id.weather_linear5text1);
        mweather_linear5text2 = (TextView) findViewById(R.id.weather_linear5text2);
        mweather_linear5text3 = (TextView) findViewById(R.id.weather_linear5text3);
        mweather_linear5text4 = (TextView) findViewById(R.id.weather_linear5text4);
        mweather_linear5text5 = (TextView) findViewById(R.id.weather_linear5text5);
        mweather_linear5text6 = (TextView) findViewById(R.id.weather_linear5text6);
        //请求中间和底部数据
        ShowVolleyRequest(spgetareaid, spgetcitypinyin);
        // mhpme_weathertopview1.getWindow().setFormat(PixelFormat.RGBA_8888);mweather_text6
        mweather_text6.setBackgroundColor(Color.argb(140, 51, 51, 51));
        mhpme_weathertopview1.setBackgroundColor(Color.argb(30, 0, 3, 51));
        mhome_weather_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homeweather.this, Homeweatheraddcity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        String spgetweathercity = SpUtils.getParam(Homeweather.this, "weathercity", "许昌市");
        mhome_weather_add2.setText(spgetweathercity);
        initData();
    }

    private void initData() {
        home_weatherback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    // 设置返回数据
                    Bundle bundle = new Bundle();
                    bundle.putString("yanglaobaoxian", "");
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    // 返回intent
                    setResult(Finaltext.YILIAORESULTCODE, intent);
                    finish();
                }
            }
        });

    }


    //请求中间和底部数据
    private void ShowVolleyRequest(final String spgetareaid, final String spgetcitypinyin) {
        String url = ApiUrls.GETWEATHERLIST;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("citykey", spgetareaid);
        params.put("cityPinyin", spgetcitypinyin);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        WeathergetWeatherList weathergetweatherlist = gson.fromJson(response.toString(), WeathergetWeatherList.class);
                        int flag = weathergetweatherlist.getFlag();
                        if (flag == 1) {
                            successcontent();
                            //请求顶部数据
                            ShowVolleyRequest2(spgetareaid);
                            //请求空气质量
                            ShowVolleyRequest3(spgetcitypinyin);
                            WeathergetWeatherList.ContentBean.DataBean data = weathergetweatherlist.getContent().getData();
                            List<WeathergetWeatherList.ContentBean.DataBean.ForecastBean> forecast = data.getForecast();
                            ShowData(data);
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


    //请求顶部数据
    private void ShowVolleyRequest2(String spgetareaid) {
        String url = ApiUrls.GETWEATHER;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("citykey", spgetareaid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        WeathergetWeather weathergetweather = gson.fromJson(response.toString(), WeathergetWeather.class);
                        int flag = weathergetweather.getFlag();
                        if (flag == 1) {
                            WeathergetWeather.ContentBean.DataBean weathergetweatherdata = weathergetweather.getContent().getData();
                            ShowData2(weathergetweatherdata);
                        } else {
                            String desc = weathergetweather.getDesc();
                            ToastUtils.getInstance(Homeweather.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homeweather.this).showMessage("系统繁忙");
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


    //展示中间及折线图数据
    private void ShowData(WeathergetWeatherList.ContentBean.DataBean data) {
        //顶部的当天温度在此获取
        String wendu = data.getWendu();
        mweather_text1.setText(wendu);
        WeathergetWeatherList.ContentBean.DataBean.YesterdayBean yesterday = data.getYesterday();
        //获得昨天的天气最高温和最低温
        String high5 = yesterday.getHigh();
        Float yesterdayhight = Reguestgetnumberforweather.Reguestgetnumberforweather(high5);
        String low = yesterday.getLow();
        Float yesterdaylow = Reguestgetnumberforweather.Reguestgetnumberforweather(low);
        //获取昨天的日期和周的数字,并展示到控件上
        Setyesterdaydate1(yesterday);
        //获得剩下五天的集合，分别获取的最高温和最低温
        List<WeathergetWeatherList.ContentBean.DataBean.ForecastBean> forecast = data.getForecast();
        //获取剩下五天的日期和周的数字，并展示
        Setyesterdaydate1remain(forecast);
        //获取五天的数据正则截取添加到集合中
        String high = forecast.get(0).getHigh();
        Float reguestgetnumberforweather = Reguestgetnumberforweather.Reguestgetnumberforweather(high);
        String high1 = forecast.get(1).getHigh();
        Float reguestgetnumberforweather1 = Reguestgetnumberforweather.Reguestgetnumberforweather(high1);
        String high2 = forecast.get(2).getHigh();
        Float reguestgetnumberforweather2 = Reguestgetnumberforweather.Reguestgetnumberforweather(high2);
        String high3 = forecast.get(3).getHigh();
        Float reguestgetnumberforweather3 = Reguestgetnumberforweather.Reguestgetnumberforweather(high3);
        String high4 = forecast.get(4).getHigh();
        Float reguestgetnumberforweather4 = Reguestgetnumberforweather.Reguestgetnumberforweather(high4);

        String low0 = forecast.get(0).getLow();
        Float low00 = Reguestgetnumberforweather.Reguestgetnumberforweather(low0);
        String low1 = forecast.get(1).getLow();
        Float low11 = Reguestgetnumberforweather.Reguestgetnumberforweather(low1);
        String low2 = forecast.get(2).getLow();
        Float low22 = Reguestgetnumberforweather.Reguestgetnumberforweather(low2);
        String low3 = forecast.get(3).getLow();
        Float low33 = Reguestgetnumberforweather.Reguestgetnumberforweather(low3);
        String low4 = forecast.get(4).getLow();
        Float low44 = Reguestgetnumberforweather.Reguestgetnumberforweather(low4);
        ArrayList<WeatherItem> list = new ArrayList<WeatherItem>();
        list.add(new WeatherItem("", yesterdayhight));
        list.add(new WeatherItem("", reguestgetnumberforweather));
        list.add(new WeatherItem("", reguestgetnumberforweather1));
        list.add(new WeatherItem("", reguestgetnumberforweather2));
        list.add(new WeatherItem("", reguestgetnumberforweather3));
        list.add(new WeatherItem("", reguestgetnumberforweather4));
        chart1.SetTuView(list, "");//单位: 摄氏度
        chart1.invalidate();
        ArrayList<WeatherItem> list1 = new ArrayList<WeatherItem>();
        list1.add(new WeatherItem("", yesterdaylow));
        list1.add(new WeatherItem("", low00));
        list1.add(new WeatherItem("", low11));
        list1.add(new WeatherItem("", low22));
        list1.add(new WeatherItem("", low33));
        list1.add(new WeatherItem("", low44));
        chart2.SetTuView(list1, "");
        chart2.invalidate();

    }


    private void Setyesterdaydate1(WeathergetWeatherList.ContentBean.DataBean.YesterdayBean yesterday) {
        //获取昨天日期，并截取，set控件
        String date = yesterday.getDate();
        String[] ri = date.split("日");
        String s = ri[0];
        mweather_linear1text1.setText(s + "日");
        mweather_linear2text1.setText("昨天");
        String type = yesterday.getType();
        String fx = yesterday.getFx();
        String fl = yesterday.getFl();
        mweather_linear3text1.setText(type);
        mweather_linear4text1.setText(fx);
        mweather_linear5text1.setText(fl);
    }

    private void Setyesterdaydate1remain(List<WeathergetWeatherList.ContentBean.DataBean.ForecastBean> forecast) {

        //分别获取剩下五天日期，并截取，set控件
        String date = forecast.get(0).getDate();
        String[] ri = date.split("日");
        String s = ri[0];
        mweather_linear1text2.setText(s + "日");
        mweather_linear2text2.setText("今天");
        String type = forecast.get(0).getType();
        String fengxiang = forecast.get(0).getFengxiang();
        String fengli = forecast.get(0).getFengli();
        mweather_linear3text2.setText(type);
        mweather_linear4text2.setText(fengxiang);
        mweather_linear5text2.setText(fengli);

        String date1 = forecast.get(1).getDate();
        String[] ri1 = date1.split("日");
        String s1 = ri1[0];
        String ss1 = ri1[1];
        mweather_linear1text3.setText(s1 + "日");
        mweather_linear2text3.setText(ss1);
        String type1 = forecast.get(1).getType();
        String fengxiang1 = forecast.get(1).getFengxiang();
        String fengli1 = forecast.get(1).getFengli();
        mweather_linear3text3.setText(type1);
        mweather_linear4text3.setText(fengxiang1);
        mweather_linear5text3.setText(fengli1);

        String date2 = forecast.get(2).getDate();
        String[] ri2 = date2.split("日");
        String s2 = ri2[0];
        String ss2 = ri2[1];
        mweather_linear1text4.setText(s2 + "日");
        mweather_linear2text4.setText(ss2);
        String type2 = forecast.get(2).getType();
        String fengxiang2 = forecast.get(2).getFengxiang();
        String fengli2 = forecast.get(2).getFengli();
        mweather_linear3text4.setText(type2);
        mweather_linear4text4.setText(fengxiang2);
        mweather_linear5text4.setText(fengli2);

        String date3 = forecast.get(3).getDate();
        String[] ri3 = date3.split("日");
        String s3 = ri3[0];
        String ss3 = ri3[1];
        mweather_linear1text5.setText(s3 + "日");
        mweather_linear2text5.setText(ss3);
        String type3 = forecast.get(3).getType();
        String fengxiang3 = forecast.get(3).getFengxiang();
        String fengli3 = forecast.get(3).getFengli();
        mweather_linear3text5.setText(type3);
        mweather_linear4text5.setText(fengxiang3);
        mweather_linear5text5.setText(fengli3);

        String date4 = forecast.get(4).getDate();
        String[] ri4 = date4.split("日");
        String s4 = ri4[0];
        String ss4 = ri4[1];
        mweather_linear1text6.setText(s4 + "日");
        mweather_linear2text6.setText(ss4);
        String type4 = forecast.get(4).getType();
        String fengxiang4 = forecast.get(4).getFengxiang();
        String fengli4 = forecast.get(4).getFengli();
        mweather_linear3text6.setText(type4);
        mweather_linear4text6.setText(fengxiang4);
        mweather_linear5text6.setText(fengli4);
    }

    //展示顶部数据
    private void ShowData2(WeathergetWeather.ContentBean.DataBean weathergetweatherdata) {
        String type = weathergetweatherdata.getType();
        String high = weathergetweatherdata.getHigh();
        String low = weathergetweatherdata.getLow();
        String fengxiang = weathergetweatherdata.getFengxiang();
        String fengli = weathergetweatherdata.getFengli();
        String date = weathergetweatherdata.getDate();
        mweather_text2.setText(type);
        mweather_text3.setText(high + "," + low);
        mweather_text4.setText(fengxiang + ":" + fengli);
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int nowmonth = cmonth + 1;
        mweather_text5.setText(cyear + "-" + nowmonth + "-" + date);
    }

    //请求空气质量
    private void ShowVolleyRequest3(String spgetcitypinyin) {
        String url = ApiUrls.GETAIRQUALITY;
        //Volley请求网络进行判断

        Map<String, String> params = new HashMap<String, String>();
        params.put("cityPinyin", spgetcitypinyin);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        WeathergetAirQuality weathergetairquality = gson.fromJson(response.toString(), WeathergetAirQuality.class);
                        int flag = weathergetairquality.getFlag();
                        if (flag == 1) {
                            WeathergetAirQuality.ContentBean.DataBean weathergetairqualitydata = weathergetairquality.getContent().getData();
                            ShowData3(weathergetairqualitydata);
                        } else {
                            String desc = weathergetairquality.getDesc();
                            ToastUtils.getInstance(Homeweather.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homeweather.this).showMessage("系统繁忙");

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

    private void ShowData3(WeathergetAirQuality.ContentBean.DataBean weathergetairqualitydata) {
        String qualityNumber = weathergetairqualitydata.getQualityNumber();
        if (!TextUtils.isEmpty(qualityNumber)) {
            String[] split = qualityNumber.split(" ");
            String qualityNumberone = split[1];
            mweather_text6.setText("空气质量: " + qualityNumberone);
        }

    }


    //截取数字
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    private void failcontent() {
        msearch_content.setVisibility(View.GONE);
        memptystates_layout.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    private void successcontent() {
        msearch_content.setVisibility(View.VISIBLE);
        memptystates_layout.setVisibility(View.GONE);
        progressDialog.dismiss();
    }
}
