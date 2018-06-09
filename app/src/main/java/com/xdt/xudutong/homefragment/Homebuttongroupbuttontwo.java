package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
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
 * Created by Administrator on 2017/5/22.
 */

public class Homebuttongroupbuttontwo extends BaseActivity {

    private TextView next2;
    private EditText number1;
    private EditText number2;
    private LinearLayout homebuttongroup_button2back1;
    private String startdate;
    private String enddate;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button1);
    }

    @Override
    public void initView() {
        homebuttongroup_button2back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button1back);
        number1 = (EditText) findViewById(R.id.home_buttom1_number1);
        number2 = (EditText) findViewById(R.id.home_buttom1_number2);
        next2 = (TextView) findViewById(R.id.person_next);
        initData();
    }

    private void initData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
        startdate = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        enddate = sDateFormat2.format(new java.util.Date());
        homebuttongroup_button2back1.setOnClickListener(new View.OnClickListener() {
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
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    ShowVolleyRequest();
                }
            }
            //请求网络
            private void ShowVolleyRequest() {
                String url = ApiUrls.VERIFICATION;
                LogUtil.d("解析的数据为=", "进入了消费界面");
                final String s = number1.getText().toString().trim();
                String s1 = number2.getText().toString().trim();
                LogUtil.d("输入的账号为=",s.toString());
                LogUtil.d("输入的密码为=",s1.toString());

                //Volley请求网络进行判断
                Map<String, String> params = new HashMap<String, String>();
                params.put("cardNo", s);
                params.put("searchPwd",s1);
                JSONObject jsonObject = new JSONObject(params);
                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Gson gson = new Gson();
                                CheckSearchPwd verificationSearchPwd = gson.fromJson(response.toString(), CheckSearchPwd.class);
                                String desc = verificationSearchPwd.getDesc().toString();
                                int flag = verificationSearchPwd.getFlag();
                                if(flag==1){
                                    Intent intent = new Intent(Homebuttongroupbuttontwo.this, Homebuttongroupbuttontwonext.class);
                                    intent.putExtra("zhanghao",s);
                                    intent.putExtra("startdate21", startdate);
                                    intent.putExtra("endedata21", enddate);
                                    intent.putExtra("home_buttongroupbuttononenextheadview1", "充值记录");
                                    startActivity(intent);
                                    LogUtil.d("登录成功=",desc);
                                }else{
                                    ToastUtils.getInstance(Homebuttongroupbuttontwo.this).showMessage(desc);
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LogUtil.d("请求的数据为=",error.toString());
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
