package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
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
import com.xdt.xudutong.bean.CheckName;
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
 * Created by Administrator on 2017/5/23.
 */

public class Homebuttongroupbuttonsixtwonexttwo extends BaseActivity {


    private EditText mhome_cardgroup_button622text2;
    private EditText mhome_cardgroup_button622text;
    private LinearLayout home_cardgroup_button622back1;
    private TextView home_cardgroup_button622nextsubmit1;
    private String startdate;
    private String enddate;
    private String personssecretstates;
    private String realrestates;
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_button62nexttwo);
    }
    @Override
    public void initView() {
        home_cardgroup_button622back1 = (LinearLayout) findViewById(R.id.home_cardgroup_button622back);
        mhome_cardgroup_button622text = (EditText) findViewById(R.id.home_cardgroup_button622text);
        mhome_cardgroup_button622text2 = (EditText) findViewById(R.id.home_cardgroup_button622text2);
        home_cardgroup_button622nextsubmit1 = (TextView) findViewById(R.id.home_cardgroup_button622nextsubmit);
        initData();
    }

    private void initData() {
        home_cardgroup_button622back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
        startdate = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        enddate = sDateFormat2.format(new java.util.Date());
        Intent intent = getIntent();
        String gongjiaointentnext = intent.getStringExtra("gongjiaointentnext");
        personssecretstates = intent.getStringExtra("personssecretstates");
        realrestates = intent.getStringExtra("realrestates");
        if (gongjiaointentnext.equals("卡内余额普通公交卡查询")) {
            home_cardgroup_button622nextsubmit1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fastClick()) {
                    String s = mhome_cardgroup_button622text.getText().toString();
                    String s1 = mhome_cardgroup_button622text2.getText().toString();
                    if (!s.isEmpty() && !s1.isEmpty()) {
                        boolean b = checkPhone2(s1);
                        if (b == true) {
                            Shownamecheck(s, s1);
                        } else {
                            ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("请输入正确的身份证号");
                        }


                    } else {
                        ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("输入不能为空");
                    }
                }
                }


            });


        } else if (gongjiaointentnext.equals("乘车记录普通公交卡查询")) {
            home_cardgroup_button622nextsubmit1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fastClick()) {
                    String s = mhome_cardgroup_button622text.getText().toString();
                    String s1 = mhome_cardgroup_button622text2.getText().toString();
                    if (!s.isEmpty() && !s1.isEmpty()) {
                        boolean b = checkPhone2(s1);
                        if (b == true) {
                            Shownamecheck2(s, s1);
                        } else {
                            ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("请输入正确的身份证号");
                        }

                    } else {
                        ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("输入不能为空");
                    }

                }
            }
            });

        }


    }

    private void Shownamecheck(final String s, final String s1) {
        String url = ApiUrls.CHECKNAME;
        LogUtil.d("输入的账号为=",s.toString());
        LogUtil.d("输入的密码为=",s1.toString());
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", s);
        params.put("certId",s1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CheckName checkname = gson.fromJson(response.toString(), CheckName.class);
                        String desc = checkname.getDesc().toString();
                        int flag = checkname.getFlag();
                        if(flag==1){
                            Intent intent3 = new Intent(Homebuttongroupbuttonsixtwonexttwo.this, LoadBlance.class);
                            intent3.putExtra("queryloadblanceusername", s);
                            intent3.putExtra("zhaqueryloadblancepassword", s1);
                            startActivity(intent3);
                            LogUtil.d("登录成功=",desc);
                        }else{
                            ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("卡号或密码不正确");
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
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);



    }
    private void Shownamecheck2(final String s, final String s1) {
        String url = ApiUrls.CHECKNAME;
        LogUtil.d("输入的账号为=",s.toString());
        LogUtil.d("输入的密码为=",s1.toString());
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", s);
        params.put("certId",s1);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CheckName checkname = gson.fromJson(response.toString(), CheckName.class);
                        int flag = checkname.getFlag();
                        if(flag==1){
                            //乘车记录的普通公交卡
                            Intent intent2 = new Intent(Homebuttongroupbuttonsixtwonexttwo.this, Homebuttongroupbuttonfournext.class);
                            intent2.putExtra("zhanghao41",s);
                            intent2.putExtra("idnumber41", s1);
                            intent2.putExtra("chengcheputongstartDate", startdate);
                            intent2.putExtra("chengcheputongenddate", enddate);
                            intent2.putExtra("personssecretstates", personssecretstates);
                            intent2.putExtra("realrestates", realrestates);
                            startActivity(intent2);
                        }else{
                            ToastUtils.getInstance(Homebuttongroupbuttonsixtwonexttwo.this).showMessage("卡号或密码不正确");
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
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
    //验证身份证号
    public boolean checkPhone2(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
}
