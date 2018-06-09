package com.xdt.xudutong.homefragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.CitygetUserCards;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017\8\29 0029.
 */

public class Homexiaolvbikejilu extends BaseActivity {
    private TextView home_xiaolvbikejiluquerysubmit1;
    private TextView home_xiaolvbikejiluquerystartdate1;
    private int year, monthOfYear, dayOfMonth;

    private TextView home_xiaolvbikejiluqueryenddate1;
    private EditText home_xiaolvbikejiluqueryeduittext1;
    private String token1;
    private String token2;
    private int real;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.homebikebikejilu);
    }

    @Override
    public void initView() {
        //返回键
        LinearLayout home_xiaolvbikejiluqueryback1 = (LinearLayout) findViewById(R.id.home_xiaolvbikejiluqueryback);
        //许都通卡号
        home_xiaolvbikejiluqueryeduittext1 = (EditText) findViewById(R.id.home_xiaolvbikejiluqueryeduittext);
        //开始日期
        home_xiaolvbikejiluquerystartdate1 = (TextView) findViewById(R.id.home_xiaolvbikejiluquerystartdate);
        //结束日期
        home_xiaolvbikejiluqueryenddate1 = (TextView) findViewById(R.id.home_xiaolvbikejiluqueryenddate);
        //查询键
        home_xiaolvbikejiluquerysubmit1 = (TextView) findViewById(R.id.home_xiaolvbikejiluquerysubmit);
        //确定取消
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        home_xiaolvbikejiluqueryback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        real = intent.getIntExtra("real", 0);
        ShowVolleyRequestforcard();
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        home_xiaolvbikejiluquerystartdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homexiaolvbikejilu.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            int mYear = year;
                            int mMonth = monthOfYear;
                            int mDay = dayOfMonth;
                            home_xiaolvbikejiluquerystartdate1.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);

                    datePickerDialog.show();
                }
            }
        });
        //点击结束日期按钮
        home_xiaolvbikejiluqueryenddate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homexiaolvbikejilu.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            int mYear = year;
                            int mMonth = monthOfYear;
                            int mDay = dayOfMonth;
                            home_xiaolvbikejiluqueryenddate1.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);

                    datePickerDialog.show();
                }
            }
        });
        //点击提交按钮
        home_xiaolvbikejiluquerysubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String s = home_xiaolvbikejiluqueryeduittext1.getText().toString();
                    String starday = home_xiaolvbikejiluquerystartdate1.getText().toString();
                    String endday = home_xiaolvbikejiluqueryenddate1.getText().toString();
                    if (s != null && !s.isEmpty()) {
                        if (!starday.isEmpty()) {
                            if (!endday.isEmpty()) {
                                String s1 = starday.replaceAll("-", "");
                                String s2 = endday.replaceAll("-", "");
                                Intent intent = new Intent(Homexiaolvbikejilu.this, Homexiaolvbikejiludetails.class);
                                intent.putExtra("xiaolvjiluxdtnumber", s);
                                intent.putExtra("xiaolvjilustarday", s1);
                                intent.putExtra("xiaolvjiluendday", s2);
                                startActivity(intent);
                            } else {
                                ToastUtils.getInstance(Homexiaolvbikejilu.this).showMessage("请输入结束日期");
                            }
                        } else {
                            ToastUtils.getInstance(Homexiaolvbikejilu.this).showMessage("请输入开始日期");
                        }
                    } else {
                        ToastUtils.getInstance(Homexiaolvbikejilu.this).showMessage("请输入许都通卡号");
                    }

                }
            }
        });

    }

    //请求卡号
    private void ShowVolleyRequestforcard() {
        String url = ApiUrls.GETUSERCARDS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        if (code1string.equals("R00001")) {
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            CitygetUserCards.ResponseBean response1 = citygetusercards.getResponse();
                            if (!response1.equals("")) {
                                CitygetUserCards.ResponseBean.BodyBean bodyBean = response1.getBody().get(0);
                                String cityCardno = bodyBean.getCityCardno();
                                Log.i("许都通卡号为", cityCardno);
                                //获取是否是免密码查询状态
                                if (real == 1) {
                                    boolean personssecretstates = SpUtils.getParam(getApplicationContext(), Personssecretstates, true);
                                    if (personssecretstates == true) {
                                        home_xiaolvbikejiluqueryeduittext1.setText(cityCardno);
                                    }

                                }
                            } else {
                                String desc = citygetusercards.getDesc();
                                ToastUtils.getInstance(Homexiaolvbikejilu.this).showMessage(desc);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求小绿许都通卡号失败的数据为=", error.toString());
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
