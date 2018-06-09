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
import com.xdt.xudutong.bean.VipcheckPwd;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\12\12 0012.
 */

public class PersonChangePhonenumberSecretcheck extends BaseActivity {
    private TextView mperson_cardmanagerjiebang_checktext3;
    private ImageView mperson_cardmanager_failinfo;
    private String xdtguashiactivityflag;
    private String username;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_cardmanager_checkjiebang);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        username = intent.getStringExtra("personusername");
        LogUtil.d("usernameusername=", username);
        LinearLayout mperson_cardmanagerjiebang_backlinearlayout = (LinearLayout) findViewById(R.id.person_cardmanagerjiebang_backlinearlayout);
        TextView mperson_jiebang_topview = (TextView) findViewById(R.id.person_jiebang_topview);
        final EditText mperson_cardmanagerjiebang_checktext2 = (EditText) findViewById(R.id.person_cardmanagerjiebang_checktext2);
        mperson_cardmanagerjiebang_checktext3 = (TextView) findViewById(R.id.person_cardmanagerjiebang_checktext3);
        mperson_cardmanager_failinfo = (ImageView) findViewById(R.id.person_cardmanager_failinfo);
        Button mperson_cardmanagerjiebang_checkbuttonsubmit = (Button) findViewById(R.id.person_cardmanagerjiebang_checkbuttonsubmit);
        TextView mperson_cardmanagerjiebang_checktext4 = (TextView) findViewById(R.id.person_cardmanagerjiebang_checktext4);
        mperson_jiebang_topview.setText("登录密码验证");
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
                    Intent intent = new Intent(PersonChangePhonenumberSecretcheck.this, Personcenterforgetpassword.class);
                    startActivity(intent);
                }
            }
        });
        mperson_cardmanagerjiebang_checkbuttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String checknmberpassword = mperson_cardmanagerjiebang_checktext2.getText().toString().trim();
                    if (!TextUtils.isEmpty(checknmberpassword)) {
                        if (!TextUtils.isEmpty(username)) {
                            ShowVolleyrequest(username, checknmberpassword);
                            LogUtil.d("11111111111111", "111111111111");
                        } else {
                            LogUtil.d("222222222", "2222222222222");
                            ToastUtils.getInstance(PersonChangePhonenumberSecretcheck.this).showMessage("系统繁忙");
                        }
                    } else {
                        ToastUtils.getInstance(PersonChangePhonenumberSecretcheck.this).showMessage("请输入密码");
                    }
                }
            }
        });
    }

    //后台核查库里有没有该账号
    private void ShowVolleyrequest(final String username, String oldpassword) {
        LogUtil.d("usernameusername", username);
        LogUtil.d("oldPwdoldPwd", oldpassword);
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
                            LogUtil.d("成功", "成功");
                            mperson_cardmanagerjiebang_checktext3.setVisibility(View.INVISIBLE);
                            mperson_cardmanager_failinfo.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(PersonChangePhonenumberSecretcheck.this, Personchangephonecheckoutcometonewsecret.class);
                            intent.putExtra("personnewsercettopviewtext", "更换手机号");
                            intent.putExtra("butonsubmittext", "确认修改");
                            if (!TextUtils.isEmpty(username)) {
                                intent.putExtra("personusername", username);
                            }
                            startActivity(intent);
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
                ToastUtils.getInstance(PersonChangePhonenumberSecretcheck.this).showMessage("系统繁忙");
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


}
