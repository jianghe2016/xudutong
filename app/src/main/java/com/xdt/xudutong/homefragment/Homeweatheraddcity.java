package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Weatheraddhotcityrecycleadapter;
import com.xdt.xudutong.adapder.Weatheramohuchaxuncityrecycleadapter;
import com.xdt.xudutong.bean.Weatherfindcity;
import com.xdt.xudutong.bean.Weatherlikecity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ProgressDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Homeweatheraddcity extends BaseActivity {

    private RecyclerView home_weather_addcityrecycleview1;
    private ListView home_weather_addcitysearchrecycleview1;
    private EditText home_weather_addcityeduitetxt1;
    private LinearLayout home_weather_add_content1;
    private TextView mweather_addcity_text1;
    private TextView mweather_addcity_text2;
    private TextView mweather_addcity_text3;
    private TextView mweather_addcity_text4;
    private TextView mweather_addcity_text5;
    private TextView mweather_addcity_text6;
    private TextView mweather_addcity_text7;
    private LinearLayout home_weather_addcityback1;
    private ProgressDialog progressDialog;
    private LinearLayout memptystates_layout;
    private LinearLayout msearch_content;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_weather_addcity);
        progressDialog = ProgressDialog.showDialog(Homeweatheraddcity.this);
        progressDialog.show();
    }

    @Override
    public void initView() {
        memptystates_layout = (LinearLayout) findViewById(R.id.emptystates_layout);
        msearch_content = (LinearLayout) findViewById(R.id.search_content);
        home_weather_addcityback1 = (LinearLayout) findViewById(R.id.home_weather_addcityback);
        home_weather_add_content1 = (LinearLayout) findViewById(R.id.home_weather_add_content);
        home_weather_addcityrecycleview1 = (RecyclerView) findViewById(R.id.home_weather_addcityrecycleview);
        home_weather_addcityrecycleview1.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        home_weather_addcityeduitetxt1 = (EditText) findViewById(R.id.home_weather_addcityeduitetxt);
        home_weather_addcitysearchrecycleview1 = (ListView) findViewById(R.id.home_weather_addcitysearchrecycleview);
        //七个固定的按钮
        mweather_addcity_text1 = (TextView) findViewById(R.id.weather_addcity_text1);
        mweather_addcity_text2 = (TextView) findViewById(R.id.weather_addcity_text2);
        mweather_addcity_text3 = (TextView) findViewById(R.id.weather_addcity_text3);
        mweather_addcity_text4 = (TextView) findViewById(R.id.weather_addcity_text4);
        mweather_addcity_text5 = (TextView) findViewById(R.id.weather_addcity_text5);
        mweather_addcity_text6 = (TextView) findViewById(R.id.weather_addcity_text6);
        mweather_addcity_text7 = (TextView) findViewById(R.id.weather_addcity_text7);
        ShowVolleyRequest();
        initData();
    }

    private void initData() {
        home_weather_addcityback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });

        mweather_addcity_text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "许昌市");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "许昌县");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "魏都区");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "禹州市");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "长葛市");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "鄢陵市");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mweather_addcity_text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", "101180401");
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", "襄城县");
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", "xuchang");
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        home_weather_addcityeduitetxt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                home_weather_addcitysearchrecycleview1.setVisibility(View.VISIBLE);
                home_weather_add_content1.setVisibility(View.INVISIBLE);
                ShowVolleyRequest2(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void ShowVolleyRequest() {
        String url = ApiUrls.FINDCITY;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("showflag", "1");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Weatherfindcity weatherfindcity = gson.fromJson(response.toString(), Weatherfindcity.class);
                        int flag = weatherfindcity.getFlag();
                        if (flag == 1) {
                            successcontent();
                            List<Weatherfindcity.ContentBean.DataBean> weatherfindcitydata = weatherfindcity.getContent().getData();
                            ShowData(weatherfindcitydata);
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

    private void ShowVolleyRequest2(CharSequence charSequence) {
        String url = ApiUrls.LIKECITY;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("nameen", charSequence.toString());
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Weatherlikecity weatherlikecity = gson.fromJson(response.toString(), Weatherlikecity.class);
                        int flag = weatherlikecity.getFlag();
                        if (flag == 1) {
                            List<Weatherlikecity.ContentBean.DataBean> weatherlikecitydata = weatherlikecity.getContent().getData();
                            ShowData2(weatherlikecitydata);
                        } else {
                            String desc = weatherlikecity.getDesc();
                            ToastUtils.getInstance(Homeweatheraddcity.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Homeweatheraddcity.this).showMessage("系统繁忙");
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

    private void ShowData2(final List<Weatherlikecity.ContentBean.DataBean> weatherlikecitydata) {
        Weatheramohuchaxuncityrecycleadapter weatheramohuchaxuncityrecycleadapter = new Weatheramohuchaxuncityrecycleadapter(Homeweatheraddcity.this, weatherlikecitydata);
        home_weather_addcitysearchrecycleview1.setAdapter(weatheramohuchaxuncityrecycleadapter);
        home_weather_addcitysearchrecycleview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fastClick()) {
                    Weatherlikecity.ContentBean.DataBean dataBean = weatherlikecitydata.get(i);
                    String areaid = dataBean.getAreaid();
                    String city = dataBean.getCity();
                    String nameen = dataBean.getDistricten();
                    //保存起来供天气页面显示用的请求数据
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", areaid);
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", city);
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", nameen);
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void ShowData(final List<Weatherfindcity.ContentBean.DataBean> weatherfindcitydata) {
        Weatheraddhotcityrecycleadapter weatheraddrecycleadapter = new Weatheraddhotcityrecycleadapter(Homeweatheraddcity.this, weatherfindcitydata);
        home_weather_addcityrecycleview1.setAdapter(weatheraddrecycleadapter);
        weatheraddrecycleadapter.setOnItemClickListener(new Weatheraddhotcityrecycleadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    String areaid = weatherfindcitydata.get(position).getAreaid();
                    String city = weatherfindcitydata.get(position).getDistrictcn();
                    String nameen = weatherfindcitydata.get(position).getNameen();
                    SpUtils.putParam(Homeweatheraddcity.this, "areaid", areaid);
                    SpUtils.putParam(Homeweatheraddcity.this, "weathercity", city);
                    SpUtils.putParam(Homeweatheraddcity.this, "nameen", nameen);
                    Intent intent = new Intent(Homeweatheraddcity.this, Homeweather.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
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
