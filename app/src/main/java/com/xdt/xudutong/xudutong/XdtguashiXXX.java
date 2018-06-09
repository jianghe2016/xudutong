package com.xdt.xudutong.xudutong;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Xdtguashiadapter;
import com.xdt.xudutong.bean.CitygetUserCards;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class XdtguashiXXX extends BaseActivity {

    private String token1;
    private String token2;
    private LinearLayout mxdt_guashiback;
    private RecyclerView mxdt_guashirecycleview;
    private List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody;
    private Button mxdt_guashi;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_guashi);
    }
    @Override
    public void initView() {
        mxdt_guashiback = (LinearLayout) findViewById(R.id.xdt_guashiback);
        mxdt_guashirecycleview = (RecyclerView) findViewById(R.id.xdt_guashirecycleview);
        mxdt_guashi = (Button) findViewById(R.id.xdt_guashi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(XdtguashiXXX.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mxdt_guashirecycleview.setLayoutManager(layoutManager);
        initData();
    }

    private void initData() {
        token1 = SpUtils.getParam(XdtguashiXXX.this, "access_token", "");
        token2 = SpUtils.getParam(XdtguashiXXX.this, "x_auth_token", "");
        ShowVolleyRequest();
        mxdt_guashiback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mxdt_guashi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    ToastUtils.getInstance(XdtguashiXXX.this).showMessage("此功能敬请期待");
                }
            }
        });
        //请求绑卡信息，如有绑卡则显示


    }
    //因为卡的种类没有确定，所以条目展示内容暂时无法确定，故recycleview，无法展示内容
    private void ShowVolleyRequest() {
        String url = ApiUrls.GETUSERCARDS;
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
                            //请求个人信息
                            ShowVolleyRequestforcard();
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            String code = citygetusercards.getCode();
                            citygetusercardsbody = citygetusercards.getResponse().getBody();
                        } else {

                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            String desc = citygetusercards.getDesc();
                            ToastUtils.getInstance(XdtguashiXXX.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(XdtguashiXXX.this).showMessage("系统繁忙");
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
                             int realId = viploaduserinfo.getContent().getData().getRealId();
                            LogUtil.d("请求个人信息成功=", "请求个人信息成功");
                            ShowVolleyRequestforturename(realId);
                        } else {
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            String desc = viploaduserinfo.getDesc();
                            ToastUtils.getInstance(XdtguashiXXX.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(XdtguashiXXX.this).showMessage("系统繁忙");
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
                            //获取身份证号
                            //获取电话号码
                            //获取真实姓名
                            String idcardNo = body.getIdcardNo();
                            String name = body.getName();
                            String phoneNo = body.getPhoneNo();
                            Showdata(idcardNo,name,phoneNo);

                            LogUtil.d("请求实名认证成功=", idcardNo);
                            LogUtil.d("请求实名认证成功=", name);
                            LogUtil.d("请求实名认证成功=", phoneNo);
                            LogUtil.d("请求实名认证成功=", "请求实名认证信息成功");
                        } else {
                            ToastUtils.getInstance(XdtguashiXXX.this).showMessage("系统繁忙");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(XdtguashiXXX.this).showMessage("系统繁忙");
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

    private void Showdata(String idcardNo, String name, String phoneNo) {
        Xdtguashiadapter xdtguashiadapter = new Xdtguashiadapter(XdtguashiXXX.this, citygetusercardsbody,name,idcardNo,phoneNo);
        mxdt_guashirecycleview.setAdapter(xdtguashiadapter);
    }



}
