package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.xdt.xudutong.bean.VipcheckPwd;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.JIEBANGCARD;

/**
 * Created by Administrator on 2017\10\27 0027.
 */

public class Personcardmanagerjiebangcheck extends BaseActivity {
    private TextView mperson_cardmanagerjiebang_checktext3;
    private ImageView mperson_cardmanager_failinfo;
    private String xdtguashiactivityflag;
    private String cityCardno;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_cardmanager_checkjiebang);
    }
    @Override
    public void initView() {
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        cityCardno = intent.getStringExtra("cityCardno");
        xdtguashiactivityflag = intent.getStringExtra("cardactivityflag");
        if (!TextUtils.isEmpty(xdtguashiactivityflag)){
            LogUtil.d("请求的数据为=", xdtguashiactivityflag);
        }

        LinearLayout mperson_cardmanagerjiebang_backlinearlayout = (LinearLayout) findViewById(R.id.person_cardmanagerjiebang_backlinearlayout);
        final EditText mperson_cardmanagerjiebang_checktext2 = (EditText) findViewById(R.id.person_cardmanagerjiebang_checktext2);
        mperson_cardmanagerjiebang_checktext3 = (TextView) findViewById(R.id.person_cardmanagerjiebang_checktext3);
        mperson_cardmanager_failinfo = (ImageView) findViewById(R.id.person_cardmanager_failinfo);
        Button mperson_cardmanagerjiebang_checkbuttonsubmit = (Button) findViewById(R.id.person_cardmanagerjiebang_checkbuttonsubmit);
        TextView mperson_cardmanagerjiebang_checktext4 = (TextView) findViewById(R.id.person_cardmanagerjiebang_checktext4);
        mperson_cardmanagerjiebang_backlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });

        mperson_cardmanagerjiebang_checktext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personcardmanagerjiebangcheck.this, Personcenterforgetpassword.class);
                    startActivity(intent);
                }
            }
        });
        //解绑提交按钮
        mperson_cardmanagerjiebang_checkbuttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String checknmberpassword = mperson_cardmanagerjiebang_checktext2.getText().toString().trim();
                if (!TextUtils.isEmpty(checknmberpassword)) {
                    if (!TextUtils.isEmpty(username)) {
                        ShowVolleyrequest(username, checknmberpassword);
                    }
                } else {
                    ToastUtils.getInstance(Personcardmanagerjiebangcheck.this).showMessage("请输入密码");
                }
            }
            }
        });
    }


    private void ShowVolleyrequest(final String username, String oldpassword) {
        String url = ApiUrls.CHECKPWD;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("oldPwd", oldpassword);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipcheckPwd vipcheckpwd = gson.fromJson(response.toString(), VipcheckPwd.class);
                        boolean flag = vipcheckpwd.isFlag();
                        if (flag == true) {
                            if (!TextUtils.isEmpty(cityCardno)) {
                                requestjiebang(cityCardno);
                            }
                        } else {
                            String desc = vipcheckpwd.getDesc();
                            mperson_cardmanagerjiebang_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            //返回后台描述
                            mperson_cardmanagerjiebang_checktext3.setText(desc);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personcardmanagerjiebangcheck.this).showMessage("系统繁忙");
                mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                mperson_cardmanagerjiebang_checktext3.setVisibility(View.INVISIBLE);
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

    //卡管理解绑
    private void requestjiebang(String citycardnumber) {
        String url = ApiUrls.UNBINDCARD;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("cityCardno", citycardnumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CitygetUserCards citygetUserCards = gson.fromJson(response.toString(), CitygetUserCards.class);
                        String code = citygetUserCards.getCode();
                        //判断是否解绑成功
                        if (code.equals("R00001")) {
                            LogUtil.d("解绑成功=", "解绑成功");
                            // 设置返回数据
                            Intent intent = new Intent();
                            // 返回intent
                            setResult(103, intent);
                            EventBus.getDefault().post(new EventMsg(JIEBANGCARD, true));
                            finish();
                        } else {
                            mperson_cardmanagerjiebang_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            //返回后台描述
                            String desc = citygetUserCards.getDesc();
                            mperson_cardmanagerjiebang_checktext3.setText(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                mperson_cardmanagerjiebang_checktext3.setVisibility(View.INVISIBLE);
                LogUtil.d("请求个人中心许都通卡管理解绑页面失败=", error.toString());
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
}
