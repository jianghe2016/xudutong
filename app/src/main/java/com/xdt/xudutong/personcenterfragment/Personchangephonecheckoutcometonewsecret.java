package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.VerifyCodemodifyPhoneMsgTest;
import com.xdt.xudutong.bean.VipcheckPhone;
import com.xdt.xudutong.bean.VipupdatePhone;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.Phonenumberreguest;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.TimerCount;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\12\7 0007.
 */
//更换手机号页面
public class Personchangephonecheckoutcometonewsecret extends BaseActivity {

    private String body;
    private String phonenumbertext;
    private String checkphonenumberdesc;
    private LinearLayout mperson_cardmanager_failinfo;
    private TextView mperson_cardmanager_checktext3;
    private String personusername;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_cardmanager_check);
    }

    @Override
    public void initView() {
        final Intent intent = getIntent();
        personusername = intent.getStringExtra("personusername");
        String mpersonnewsercettopviewtext = intent.getStringExtra("personnewsercettopviewtext");
        String mbutonsubmittext = intent.getStringExtra("butonsubmittext");
        TextView mperson_changephone_toptext = (TextView) findViewById(R.id.person_changephone_toptext);
        LinearLayout mperson_cardmanager_backlinearlayout = (LinearLayout) findViewById(R.id.person_cardmanager_backlinearlayout);
        TextView mperson_cardmanager_checkbuttonsubmit = (TextView) findViewById(R.id.person_cardmanager_checkbuttonsubmit);
        final Button mperson_cardmanager_checkbutton1 = (Button) findViewById(R.id.person_cardmanager_checkbutton1);
        final EditText mperson_cardmanager_checktext1 = (EditText) findViewById(R.id.person_cardmanager_checktext1);
        final EditText mperson_cardmanager_checktext2 = (EditText) findViewById(R.id.person_cardmanager_checktext2);
        mperson_cardmanager_failinfo = (LinearLayout) findViewById(R.id.person_cardmanager_failinfo);
        mperson_cardmanager_checktext3 = (TextView) findViewById(R.id.person_cardmanager_checktext3);
        if (!TextUtils.isEmpty(mpersonnewsercettopviewtext) && !TextUtils.isEmpty(mbutonsubmittext)) {
            mperson_changephone_toptext.setText(mpersonnewsercettopviewtext);
            mperson_cardmanager_checkbuttonsubmit.setText(mbutonsubmittext);
        }
        mperson_cardmanager_backlinearlayout.setOnClickListener(new View.OnClickListener() {
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
        //
        mperson_cardmanager_checkbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    phonenumbertext = mperson_cardmanager_checktext1.getText().toString();
                    boolean isreguextphonenumber = Phonenumberreguest.isreguextphonenumber(phonenumbertext);
                    if (!TextUtils.isEmpty(phonenumbertext)) {
                        if (isreguextphonenumber == true) {
                            TimerCount timer = new TimerCount(60000, 1000, mperson_cardmanager_checkbutton1);
                            timer.start();
                            //核查是否存在,如果不存在则进行更改
                            ShowVolleyrequestforcheckphone(phonenumbertext);
                        } else {
                            ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("请输入正确的手机号");
                        }
                    } else {
                        ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("输入不能为空");
                    }
                }
            }
        });
        mperson_cardmanager_checkbuttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String checkoutnumber = mperson_cardmanager_checktext2.getText().toString();
                    if (!TextUtils.isEmpty(checkoutnumber)) {
                        if (checkoutnumber.equals(body)) {
                            if (!TextUtils.isEmpty(personusername)) {
                                InputMethodManager imm = (InputMethodManager)
                                        getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                if (!TextUtils.isEmpty(phonenumbertext)) {
                                    ShowVolleyrequestforupdatephonenumber(personusername, phonenumbertext);
                                    mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                                    mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                                } else {
                                    ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("输入的手机号不能为空");
                                }

                            } else {
                                ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("系统繁忙");
                            }
                        } else {
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setText("验证码输入错误");
                        }
                    } else {
                        ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("输入不能为空");
                    }
                }
            }
        });
    }

    private void ShowVolleyrequestforupdatephonenumber(String personusername, final String phonenumber) {
        String url = ApiUrls.VIPUPDATEPHONE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        LogUtil.d("updatephone", personusername);
        LogUtil.d("updatephone2", phonenumber);
        params.put("username", personusername);
        params.put("phone", phonenumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipupdatePhone vipisuserexist = gson.fromJson(response.toString(), VipupdatePhone.class);
                        String code = vipisuserexist.getCode();
                        String desc = vipisuserexist.getDesc();
                        if (code.equals("R00001")) {
                            mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Personchangephonecheckoutcometonewsecret.this, Personchangephonemanager.class);
                            SpUtils.putParam(getApplicationContext(),"personmobile", phonenumber);
                            startActivity(intent);
                            finish();
                        } else {
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.VISIBLE);
                            if (!TextUtils.isEmpty(desc)) {
                                mperson_cardmanager_checktext3.setText(desc);
                            }
                            ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("系统繁忙");
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void ShowVolleyrequest(String mperson_center_forget_possword1text) {
        String url = ApiUrls.MODIFYPHONEMSGTEST;
        //获取设备号仅限手机
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("phoneNum", mperson_center_forget_possword1text);
        params.put("alias", DEVICE_ID);
        params.put("platform", "1004");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VerifyCodemodifyPhoneMsgTest mverifycodemodifyphonemsgtest = gson.fromJson(response.toString(), VerifyCodemodifyPhoneMsgTest.class);
                        String code = mverifycodemodifyphonemsgtest.getCode();
                        String desc = mverifycodemodifyphonemsgtest.getDesc();
                        checkphonenumberdesc = mverifycodemodifyphonemsgtest.getDesc();
                        if (code.equals("R00001")) {
                            mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                            body = mverifycodemodifyphonemsgtest.getContent().getData();
                            LogUtil.d("请求的验证码为r00111=", body);
                        } else if (code.equals("R00002")) {
                            body = mverifycodemodifyphonemsgtest.getContent().getData();
                            LogUtil.d("请求的验证码为r00222=", body);
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setText(desc);
                            ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage(desc);
                        } else {
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setText(desc);
                            ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("系统繁忙");
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

    private void ShowVolleyrequestforcheckphone(final String phonenumber) {
        String url = ApiUrls.VIPCHECKPHONE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phonenumber);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipcheckPhone vipisuserexist = gson.fromJson(response.toString(), VipcheckPhone.class);
                        String code = vipisuserexist.getCode();
                        String desc = vipisuserexist.getDesc();
                        if (code.equals("R00001")) {
                            //可以用
                            mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                            ShowVolleyrequest(phonenumber);
                        } else {
                            //不能用
                            mperson_cardmanager_failinfo.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setVisibility(View.VISIBLE);
                            mperson_cardmanager_checktext3.setText(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                mperson_cardmanager_checktext3.setVisibility(View.INVISIBLE);
                ToastUtils.getInstance(Personchangephonecheckoutcometonewsecret.this).showMessage("系统繁忙");
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


}
