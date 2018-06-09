package com.xdt.xudutong.personcenterfragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.xdt.xudutong.bean.VipupdateUserInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Personsetbirthday extends BaseActivity {
    private EditText setbirthday;
    private int year, monthOfYear, dayOfMonth;
    private TextView person_updatabirthdaybaocun1;
    private LinearLayout prson_setbirthdayback1;
    private String token1;
    private String token2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_setbirthday);
    }
    @Override
    public void initView() {
        prson_setbirthdayback1 = (LinearLayout) findViewById(R.id.prson_setbirthdayback);
        setbirthday = (EditText) findViewById(R.id.showDiaglogBtn);
        person_updatabirthdaybaocun1 = (TextView) findViewById(R.id.person_updatabirthdaybaocun);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        initData();
    }

    private void initData() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");

        prson_setbirthdayback1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        setbirthday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Personsetbirthday.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                              int day) {
                            int mYear = year;
                            int mMonth = month;
                            int mDay = day;
                            setbirthday.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);
                    datePickerDialog.show();
                }
            }
        });
        person_updatabirthdaybaocun1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String setbirthdaystring = setbirthday.getText().toString();
                    if (token1 != null && !token1.isEmpty()) {
                        if (!setbirthdaystring.isEmpty()) {
                            ShowVolleyRequest(setbirthdaystring);
                        } else {
                            ToastUtils.getInstance(Personsetbirthday.this).showMessage("请输入修改日期");
                        }
                    } else {
                        ToastUtils.getInstance(Personsetbirthday.this).showMessage("请重新登录");
                    }
                }
            }
        });


    }

    private void ShowVolleyRequest(final String setbirthdaystring) {
        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("birthday", setbirthdaystring);
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
                            VipupdateUserInfo VipupdateUserInfo = gson.fromJson(response.toString(), VipupdateUserInfo.class);
                            int flag = VipupdateUserInfo.getFlag();
                            Bundle bundle = new Bundle();
                            bundle.putString("sexupbirthday", setbirthdaystring);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            // 返回intent
                            setResult(16, intent);
                            finish();
                        } else {
                            ToastUtils.getInstance(Personsetbirthday.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(Personsetbirthday.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personsetbirthday.this).showMessage("系统繁忙");
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
