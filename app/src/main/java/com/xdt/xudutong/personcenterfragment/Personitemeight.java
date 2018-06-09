package com.xdt.xudutong.personcenterfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
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
import com.xdt.xudutong.activitys.SettingPsdActivity;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.frgment.MainActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.Personpushinfomanager1;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.TUICHU;
import static com.xdt.xudutong.utils.Finaltext.LITTLEGREENTRUENAMEREALSTATES;
import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017\9\17 0017.
 */

public class Personitemeight extends BaseActivity implements View.OnClickListener {

    private int personturenamestates;
    private String personusername;
    private String token1;
    private String token2;
    private LinearLayout person_setting_item41;
    private TextView person_setting_item4line;
    private String personmobile;
    private Intent mIntent;
    private String mMobile;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_settingview);
    }

    @Override
    public void initView() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        Intent intent = getIntent();
        personturenamestates = intent.getIntExtra("personturenamestates", -1);
        //获取实名认证状态
        personusername = intent.getStringExtra("personusername");
        personmobile = intent.getStringExtra("personmobile");
        mMobile = getIntent().getExtras().getString("mobile");
        person_setting_item41 = (LinearLayout) findViewById(R.id.person_setting_item4);
        person_setting_item4line = (TextView) findViewById(R.id.person_setting_item4line);
        if (personturenamestates != 1) {
            person_setting_item41.setVisibility(View.GONE);
            person_setting_item4line.setVisibility(View.GONE);
        }
        ShowVolleyRequestforuserinfo();
        findViewById(R.id.person_settingnextback).setOnClickListener(this);
        findViewById(R.id.person_setting_item1).setOnClickListener(this);
        findViewById(R.id.person_setting_item1down).setOnClickListener(this);
        findViewById(R.id.person_setting_item2).setOnClickListener(this);
        findViewById(R.id.person_setting_item3).setOnClickListener(this);
        person_setting_item41.setOnClickListener(this);
        findViewById(R.id.person_setting_item5).setOnClickListener(this);
        findViewById(R.id.person_setting_item6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (fastClick()) {
            switch (v.getId()) {
                //返回
                case R.id.person_settingnextback:
                    finish();
                    break;
                //返回
                //帮助与反馈
                case R.id.person_setting_item5:
                    Intent intent55 = new Intent(Personitemeight.this, PersonAskHelp.class);
                    if (!TextUtils.isEmpty(personusername)) {
                        intent55.putExtra("itemtwoPersonAskHelpusername", personusername);
                    }
                    startActivity(intent55);
                    break;
            case R.id.person_setting_item1down:
                //更改手机号
                Intent intent1 = new Intent(Personitemeight.this,Personchangephonemanager.class);
                if (!TextUtils.isEmpty(personmobile)){
                   SpUtils.putParam(getApplicationContext(),"personmobile",personmobile);
                }
                if (!TextUtils.isEmpty(personusername)){
                    intent1.putExtra("personusername",personusername);
                }
                startActivity(intent1);
                break;
                //实名认证
                case R.id.person_setting_item1:
                    switch (personturenamestates) {
                        case -1:
                            //未认证
                            Intent intent = new Intent(Personitemeight.this, Personitemthree.class);
                            startActivity(intent);
                            break;
                        case 0:
                            //正在认证
                            Intent intent2 = new Intent(Personitemeight.this, Personitemthreetwo.class);
                            startActivity(intent2);
                            break;
                        case 1:
                            //认证成功
                            Intent intent3 = new Intent(Personitemeight.this, Personitemthreethree.class);
                            startActivity(intent3);
                            break;
                        case 2:
                            Intent intent4 = new Intent(Personitemeight.this, Personitemthreethreefail.class);
                            startActivity(intent4);
                            break;
                        case 3:
                            Intent intent5 = new Intent(Personitemeight.this, Personitemthreethreefail.class);
                            startActivity(intent5);
                            break;
                        default:
                            break;
                    }
                    break;
                //修改密码
                case R.id.person_setting_item2:
                    if (!TextUtils.isEmpty(personusername)) {
//                        Intent intent22 = new Intent(Personitemeight.this, Personitemtwotwice_password.class);
//                        intent22.putExtra("itemtwoxiugaiwordusername", personusername);
//                        startActivity(intent22);
                        mIntent = new Intent().setClass(this, SettingPsdActivity.class);
                        mIntent.putExtra("mobile",mMobile);
                        mIntent.putExtra("itemtwoxiugaiwordusername", personusername);
                        startActivity(mIntent);
                    } else {
                        ToastUtils.getInstance(Personitemeight.this).showMessage("用户名不能为空");
                    }
                    break;
                //消息管理
                case R.id.person_setting_item3:
                    //Intent intent44 = new Intent(Personitemeight.this, Personpushinfomanager.class);
                    Intent intent44 = new Intent(Personitemeight.this, Personpushinfomanager1.class);
                    if (null != personusername) {
                        intent44.putExtra("personusername", personusername);
                        intent44.putExtra("personturenamestates", personturenamestates);
                    }
                    startActivity(intent44);
                    break;
                //隐私
                case R.id.person_setting_item4:
                    switch (personturenamestates) {
                        case 1: 
                            //认证成功
                            Intent intent11 = new Intent(Personitemeight.this, Personssecret.class);
                            intent11.putExtra("secrstorpush", 1);
                            startActivity(intent11);
                            break;
                        default:
                            System.out.println("default");
                            break;
                    }

                    break;
                //退出登录
                case R.id.person_setting_item6:
                    AlertDialog.Builder builder = new AlertDialog.Builder(Personitemeight.this);
                    builder.setMessage("确认退出吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            EventBus.getDefault().post(new EventMsg(TUICHU, false));
                            SpUtils.removeParam(getApplicationContext(), "access_token");
                            SpUtils.removeParam(getApplicationContext(), "x_auth_token");
                            SpUtils.removeParam(getApplicationContext(), "refresh_token");
                            SpUtils.removeParam(getApplicationContext(), "loadingstate");
                            SpUtils.removeParam(getApplicationContext(), "personmobile");
                            SpUtils.removeParam(getApplicationContext(),"appAlias");
                            SpUtils.removeParam(getApplicationContext(),"cardNo");
                            SpUtils.removeParam(getApplicationContext(), Personssecretstates);
                            SpUtils.removeParam(getApplicationContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                            SpUtils.removeParam(getApplicationContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                            SpUtils.removeParam(getApplicationContext(), LITTLEGREENTRUENAMEREALSTATES);
                            ApplicationController.mBitmap = null;
                            ApplicationController.mPopWindow = "-1";
                            Intent intent = new Intent(Personitemeight.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
            }
        }
    }
    private void ShowVolleyRequestforuserinfo() {
        String urltruename = ApiUrls.LOADUSERINFO;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                                mMobile = response.optJSONObject("content").optJSONObject("data").get("mobile").toString();
                                SpUtils.putParam(getApplicationContext(), "loadingstate", true);
                            } else {
                                startActivity(new Intent(Personitemeight.this, Personuser_comein.class));
                                finish();
                                SpUtils.putParam(getApplicationContext(), "loadingstate", false);
                            }
                        } catch (JSONException e) {
                            startActivity(new Intent(Personitemeight.this, Personuser_comein.class));
                            finish();
                            SpUtils.putParam(getApplicationContext(), "loadingstate", false);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                SpUtils.putParam(getApplicationContext(), "loadingstate", false);
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
    private void ShowVolleyRequestforuserinfo(final int ownflag) {
        String urltruename = ApiUrls.LOADUSERINFO;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            Gson gson = new Gson();
                            if (code1string.equals("R00001")) {
                                ViploadUserInfo viploaduserinfo = gson.fromJson(response.toString(), ViploadUserInfo.class);
                                ViploadUserInfo.ContentBean.DataBean viploaduserinfodata = viploaduserinfo.getContent().getData();
                                int real = viploaduserinfodata.getReal();
                               // realName = viploaduserinfodata.getRealName();
                                //username = viploaduserinfodata.getUsername();
                                if (ownflag == 2) {
                                    if (real == 1) {
                                        //请绑卡
                                        Intent intent = new Intent(Personitemeight.this, PersoncenterAddCard.class);
                                        startActivityForResult(intent, 32);
                                    } else if (real == 0) {
                                        //正在认证
                                        ToastUtils.getInstance(Personitemeight.this).showMessage("正在进行实名认证");
                                    } else if (real == -1) {
                                        //未认证
                                        Intent intent = new Intent(Personitemeight.this, Personitemthree.class);
                                       /* if (!TextUtils.isEmpty(username)) {
                                            intent.putExtra("loadusername", username);
                                        }*/
                                        startActivity(intent);
                                        ToastUtils.getInstance(Personitemeight.this).showMessage("请先进行实名认证");
                                    } else {
                                        //认证失败
                                        startActivity(new Intent(Personitemeight.this, Personitemthreethreefail.class));
                                        ToastUtils.getInstance(Personitemeight.this).showMessage("请重新进行实名认证");
                                    }
                                }

                            } else {
                                ToastUtils.getInstance(Personitemeight.this).showMessage("会话已过期，请重新登录");
                                Intent intent = new Intent(Personitemeight.this, Personuser_comein.class);
                                startActivity(intent);
                                finish();
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
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
}
