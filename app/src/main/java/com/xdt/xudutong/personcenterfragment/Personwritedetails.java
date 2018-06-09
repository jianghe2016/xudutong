package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.WritedetailsRecyclerAdapter;
import com.xdt.xudutong.bean.HujidifindHujidis;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.Homezhihuiyiuliao;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\26 0026.
 */

public class Personwritedetails extends BaseActivity {

    private RecyclerView buttongroup_button52_rlistview1;
    private ProgressBar mhomebuttongroup_button52progressbar;
    private int homeactivityaddhomeid1;
    private String token1;
    private String token2;
    private String mHujiAddr;
    private String mHujiCode;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button52);
    }

    @Override
    public void initView() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        LinearLayout homebuttongroup_button52back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button52back);
        EditText mbuttongroup_button52_eduittext = (EditText) findViewById(R.id.buttongroup_button52_eduittext);
        buttongroup_button52_rlistview1 = (RecyclerView) findViewById(R.id.buttongroup_button52_rlistview);
        mhomebuttongroup_button52progressbar = (ProgressBar) findViewById(R.id.homebuttongroup_button52progressbar);
        TextView writepersondetailstoptext1 = (TextView) findViewById(R.id.writepersondetailstoptext);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        buttongroup_button52_rlistview1.setLayoutManager(llm);
        Intent intent = getIntent();
        String jiatingzhuzhi = intent.getStringExtra("jiatingzhuzhi");
        homeactivityaddhomeid1 = intent.getIntExtra("homeactivityaddhomeid", 0);
        writepersondetailstoptext1.setText(jiatingzhuzhi);
        homebuttongroup_button52back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    finish();
                }
            }
        });
        mbuttongroup_button52_eduittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String findhujidis = ApiUrls.FINDHUJIDIS;
                ShowVolleyRequestforhome1(s.toString(), findhujidis);

            }
        });
    }

    private void ShowVolleyRequestforhome1(String s, String url) {
        Map<String, String> params = new HashMap<>();
        params.put("huji_addr", s);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            Gson gson = new Gson();
                            if (code1string.equals("R00001")) {
                                HujidifindHujidis hujidifindhujidis = gson.fromJson(response.toString(), HujidifindHujidis.class);
                                int flag = hujidifindhujidis.getFlag();
                                if (flag == 1) {
                                    List<HujidifindHujidis.ContentBean.DataBean> hujidifindhujidisdata = hujidifindhujidis.getContent().getData();
                                    if (!hujidifindhujidisdata.isEmpty()) {
                                        Showdata(hujidifindhujidisdata);
                                        buttongroup_button52_rlistview1.setVisibility(View.VISIBLE);
                                        mhomebuttongroup_button52progressbar.setVisibility(View.GONE);
                                    } else {
                                        ToastUtils.getInstance(Personwritedetails.this).showMessage("系统繁忙");
                                    }
                                } else {
                                    ToastUtils.getInstance(Personwritedetails.this).showMessage("暂无结果");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    private void Showdata(final List<HujidifindHujidis.ContentBean.DataBean> hujidifindhujidisdata) {
        WritedetailsRecyclerAdapter productRecyclerAdapter = new WritedetailsRecyclerAdapter(Personwritedetails.this, hujidifindhujidisdata);
        buttongroup_button52_rlistview1.setAdapter(productRecyclerAdapter);
        productRecyclerAdapter.setOnItemClickListener(new WritedetailsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    mHujiAddr = hujidifindhujidisdata.get(position).getHuji_addr();
                    mHujiCode = hujidifindhujidisdata.get(position).getHuji_code();
                    if (homeactivityaddhomeid1 == 1) {
                        // 设置返回数据
                        Bundle bundle = new Bundle();
                        bundle.putString("writedetailshuji_addr", mHujiAddr);
                        bundle.putString("writedetailshuji_code", mHujiCode);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        // 返回intent
                        setResult(62, intent);
                        finish();
                    } else if (homeactivityaddhomeid1 == 2) {
                        Intent intent1 = getIntent();
                        String requestusername = intent1.getStringExtra("requestusername");
                        String realIdnumber = intent1.getStringExtra("realIdnumber");
                        String realName = intent1.getStringExtra("realName");
                        ShowVolleyRequestforVipupdateUserInfo(mHujiCode, mHujiAddr);
                        Intent intent = new Intent(Personwritedetails.this, Homezhihuiyiuliao.class);
                        intent.putExtra("homevolleygetidcardNo1", realIdnumber);
                        intent.putExtra("homevolleygetphoneNo1", requestusername);
                        intent.putExtra("homevolleygetname1", realName);
                        intent.putExtra("homehuji_code1", mHujiCode);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent().setClass(Personwritedetails.this,Personitemtwo.class);
                        intent.putExtra("address",mHujiAddr);
                        intent.putExtra("address_code",mHujiCode);
                        // 返回intent
                        setResult(15, intent);
                        finish();
                    }
                }
            }
        });
    }

    private void ShowVolleyRequestforVipupdateUserInfo(String huji_code, String huji_addr) {

        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("huji_code", huji_code);
        params.put("huji_addr", huji_addr);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            //更新用户信息成功
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
