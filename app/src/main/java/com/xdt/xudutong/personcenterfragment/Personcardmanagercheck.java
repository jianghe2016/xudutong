package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.xdt.xudutong.bean.CitycardLoss;
import com.xdt.xudutong.bean.VipisUserExist;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.TimerCount;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.GUASHICARD;

/**
 * Created by Administrator on 2017\10\27 0027.
 */

public class Personcardmanagercheck extends BaseActivity {

    private Button mperson_cardmanager_checkbutton1;
    private TextView mperson_cardmanager_checktext3;
    private String body;
    private LinearLayout person_cardmanager_failinfo1;

    private String token1;
    private String token2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_cardmanager_check);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String cityCardno = intent.getStringExtra("cityCardno");
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        EditText mperson_cardmanager_checktext1 = (EditText) findViewById(R.id.person_cardmanager_checktext1);
        mperson_cardmanager_checktext1.setKeyListener(null);
        person_cardmanager_failinfo1 = (LinearLayout) findViewById(R.id.person_cardmanager_failinfo);
        if (!TextUtils.isEmpty(username)) {
            mperson_cardmanager_checktext1.setText(username);
        }
        final EditText mperson_cardmanager_checktext2 = (EditText) findViewById(R.id.person_cardmanager_checktext2);
        mperson_cardmanager_checktext3 = (TextView) findViewById(R.id.person_cardmanager_checktext3);
        mperson_cardmanager_checkbutton1 = (Button) findViewById(R.id.person_cardmanager_checkbutton1);
        Button mperson_cardmanager_checkbuttonsubmit = (Button) findViewById(R.id.person_cardmanager_checkbuttonsubmit);
        LinearLayout person_cardmanager_backlinearlayout1 = (LinearLayout) findViewById(R.id.person_cardmanager_backlinearlayout);
        person_cardmanager_backlinearlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mperson_cardmanager_checkbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (!TextUtils.isEmpty(username)) {
                        TimerCount timer = new TimerCount(60000, 1000, mperson_cardmanager_checkbutton1);
                        timer.start();
                        ShowVolleyrequest(username);
                    }
                }
            }
        });
        //解绑提交按钮
        mperson_cardmanager_checkbuttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String checknmber = mperson_cardmanager_checktext2.getText().toString().trim();
                if (!checknmber.isEmpty()) {
                    if (checknmber.equals(body)) {
                        //判断是挂失还是卡解绑
                        requestguashi(cityCardno);
                    } else {
                        person_cardmanager_failinfo1.setVisibility(View.VISIBLE);
                        //返回后台描述
                        mperson_cardmanager_checktext3.setText("验证码输入错误");
                    }
                } else {
                    ToastUtils.getInstance(Personcardmanagercheck.this).showMessage("输入验证码不能为空");
                }
            }
            }
        });
    }
    private void ShowVolleyrequest(String mperson_center_forget_possword1text) {
        String url = ApiUrls.MSGVERIFY;
        //获取设备号仅限手机
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", mperson_center_forget_possword1text);
        params.put("clientId", DEVICE_ID);
        params.put("appPlatform", "1003");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipisUserExist vipisuserexist = gson.fromJson(response.toString(), VipisUserExist.class);
                        String code = vipisuserexist.getCode();
                        String desc = vipisuserexist.getDesc();
                        if (code.equals("R00001")) {
                            body = vipisuserexist.getResponse().getBody();
                            LogUtil.d("请求的验证码为=", body);
                        } else if (code.equals("R00002")) {
                            body = vipisuserexist.getResponse().getBody();
                            ToastUtils.getInstance(Personcardmanagercheck.this).showMessage(desc);
                        } else {
                            ToastUtils.getInstance(Personcardmanagercheck.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personcardmanagercheck.this).showMessage("系统繁忙");
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

    //卡管理挂失
    private void requestguashi(String citycardnumber) {
        String url = ApiUrls.CARDLOSS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("cardNo", citycardnumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CitycardLoss citycardloss = gson.fromJson(response.toString(), CitycardLoss.class);
                        String code = citycardloss.getCode();
                        //判断是否解绑成功
                        if (code.equals("R00001")) {
                            ToastUtils.getInstance(Personcardmanagercheck.this).showMessage("您可以到许都通营业网点办理补卡业务");
                            LogUtil.d("挂失接口请求到的内容为", String.valueOf(citycardloss.getContent().getData().getBytes()));
                            // 设置返回数据
                            Intent intent = new Intent();
                            // 返回intent
                            setResult(104, intent);
                            EventBus.getDefault().post(new EventMsg(GUASHICARD, true));
                            finish();
                        } else {
                            person_cardmanager_failinfo1.setVisibility(View.VISIBLE);
                            //返回后台描述
                            String desc = citycardloss.getDesc();
                            mperson_cardmanager_checktext3.setText(desc);
                            LogUtil.d("descccccc", desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_cardmanager_failinfo1.setVisibility(View.GONE);
                LogUtil.d("请求个人中心许都通卡管理挂失页面失败=", error.toString());
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
