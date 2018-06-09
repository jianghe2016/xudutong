package com.xdt.xudutong.personcenterfragment;

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
import com.xdt.xudutong.bean.IdcardgetCert;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Personitemthreetwo extends BaseActivity {

    private int realId;
    private TextView person_ture_name2_turename1;
    private TextView person_ture_name2_tureidcard1;
    private TextView person_ture_name2_turephonenumber1;
    private String token1;
    private String token2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_true_name2);
    }
    @Override
    public void initView() {
        LinearLayout person_ture_name2_turenameback1 = (LinearLayout) findViewById(R.id.person_ture_name2_turenameback);
        person_ture_name2_turename1 = (TextView) findViewById(R.id.person_ture_name2_turename);
        person_ture_name2_tureidcard1 = (TextView) findViewById(R.id.person_ture_name2_tureidcard);
        person_ture_name2_turephonenumber1 = (TextView) findViewById(R.id.person_ture_name2_turephonenumber);
        person_ture_name2_turenameback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        ShowVolleyRequestforcard();
    }
    //请求个人信息
    private void ShowVolleyRequestforcard() {
        String url = ApiUrls.LOADUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        //    params.put("train_date", trainstartdata);
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
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            realId = viploaduserinfo.getContent().getData().getRealId();
                            LogUtil.d("请求个人信息成功=","请求个人信息成功");
                            ShowVolleyRequestforturename(realId);
                        } else {
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            String desc = viploaduserinfo.getDesc();
                            ToastUtils.getInstance(Personitemthreetwo.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemthreetwo.this).showMessage("系统繁忙");
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

    //请求实名认证信息
    private void ShowVolleyRequestforturename(int realIdstring1) {
        String realIdstring = String.valueOf(realIdstring1);
        String urltruename = ApiUrls.GETCERT;
        Map<String, String> params = new HashMap<>();
        params.put("certId", realIdstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
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
                            IdcardgetCert idcardgetcert = gson.fromJson(response.toString(), IdcardgetCert.class);
                            IdcardgetCert.ResponseBean.BodyBean body = idcardgetcert.getResponse().getBody();

                            String idcardNo = body.getIdcardNo();
                            String name = body.getName();
                            String phoneNo = body.getPhoneNo();
                            //截取身份证号
                            String substring2 = idcardNo.substring(idcardNo.length() - 4, idcardNo.length());
                            //截取电话号码
                            String substring4 = phoneNo.substring(phoneNo.length() - 4,phoneNo.length());
                            person_ture_name2_turename1.setText(name);
                            person_ture_name2_tureidcard1.setText("**************" + substring2);
                            person_ture_name2_turephonenumber1.setText("***" + substring4);
                        } else {
                            ToastUtils.getInstance(Personitemthreetwo.this).showMessage("系统繁忙");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(Personitemthreetwo.this).showMessage("系统繁忙");
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
