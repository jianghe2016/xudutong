package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.xdt.xudutong.bean.CheckSearchPwd;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */

public class Homebuttongroupbuttonone extends BaseActivity {

    private TextView personnext;
    private EditText number1;
    private EditText number2;

    private LinearLayout homebuttongroup_button1back1;
    private String startdate;
    private String enddate;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button1);
    }

    @Override
    public void initView() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
        startdate = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        enddate = sDateFormat2.format(new java.util.Date());
        homebuttongroup_button1back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button1back);
        personnext = (TextView) findViewById(R.id.person_next);
        number1 = (EditText) findViewById(R.id.home_buttom1_number1);
        number2 = (EditText) findViewById(R.id.home_buttom1_number2);
        initData();
    }


    //初始化数据
    private void initData() {
        homebuttongroup_button1back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
            }
        });
        personnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String s = number1.getText().toString().trim();
                    String s1 = number2.getText().toString().trim();
                    if (s1.length()<6){
                        ShowVolleyRequest(s,s1);
                    }else{
                        ToastUtils.getInstance(Homebuttongroupbuttonone.this).showMessage("输入的密码长度不能小于6位");
                    }

                }
            }

            private void ShowVolleyRequest(final String s, final String s1) {
                String url = ApiUrls.VERIFICATION;


                //Volley请求网络进行判断
                Map<String, String> params = new HashMap<String, String>();
                params.put("cardNo", s);
                params.put("searchPwd", s1);
                JSONObject jsonObject = new JSONObject(params);
                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Gson gson = new Gson();
                                CheckSearchPwd verificationSearchPwd = gson.fromJson(response.toString(), CheckSearchPwd.class);
                                String desc = verificationSearchPwd.getDesc().toString();
                                int flag = verificationSearchPwd.getFlag();
                                if (flag == 1) {
                                    Intent intent1 = getIntent();
                                    String xdtcardsaledetailstopview = intent1.getStringExtra("xdtcardsaledetailstopview");
                                    Intent intent = new Intent(Homebuttongroupbuttonone.this, Homebuttongroupbuttononenext.class);
                                    intent.putExtra("zhanghao", s);
                                    intent.putExtra("startdate11", startdate);
                                    intent.putExtra("endedata11", enddate);
                                    intent.putExtra("endedata11", enddate);
                                    if (!TextUtils.isEmpty(xdtcardsaledetailstopview)){
                                        intent.putExtra("xdtcardsaledetailstopview", xdtcardsaledetailstopview);
                                        intent.putExtra("realIdnumber", s1);
                                    }
                                    startActivity(intent);
                                } else {
                                    ToastUtils.getInstance(Homebuttongroupbuttonone.this).showMessage(desc);
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(Homebuttongroupbuttonone.this).showMessage("系统繁忙");
                        LogUtil.d("请求的数据为=", error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Accept", "application/json");
                        headers.put("Content-Type", "application/json; charset=UTF-8");
                        return headers;
                    }
                };
                ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
            }
        });

    }
}
