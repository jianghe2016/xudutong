package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.UpdatePsw;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ExampleUtil;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Persontwice_password extends BaseActivity {

    private Button mbutton;
    private EditText mperson_twice_passwrod1;
    private EditText mperson_twice_passwrod2;
    private String username;
    private String body;
    private String mperson_twice_passwrod22;
    private String mperson_twice_passwrod11;
    private LinearLayout person_twice_passwrodback1;
    int currentImg = 0;
    private int[] image;
    private LinearLayout mperson_twice_password_eyeselect1;
    private LinearLayout mperson_twice_password_eyeselect2;
    private ImageView mperson_twice_password_closeeye1;
    private ImageView mperson_twice_password_closeeye2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_twice_password);

    }

    @Override
    public void initView() {
        image = new int[]{
                R.drawable.person_centeropeneye,
                R.drawable.person_centercloseeye,
        };
        mbutton = (Button) findViewById(R.id.person_twice_passwrod);
        mperson_twice_password_eyeselect1 = (LinearLayout) findViewById(R.id.person_twice_password_eyeselect1);
        mperson_twice_password_eyeselect2 = (LinearLayout) findViewById(R.id.person_twice_password_eyeselect2);
        mperson_twice_password_closeeye1 = (ImageView) findViewById(R.id.person_twice_password_closeeye1);
        mperson_twice_password_closeeye2 = (ImageView) findViewById(R.id.person_twice_password_closeeye2);
        person_twice_passwrodback1 = (LinearLayout) findViewById(R.id.person_twice_passwrodback);
        mperson_twice_passwrod1 = (EditText) findViewById(R.id.person_twice_passwrod1);
        mperson_twice_passwrod2 = (EditText) findViewById(R.id.person_twice_passwrod2);
        mperson_twice_passwrod1.addTextChangedListener(new SearchWather3(mperson_twice_passwrod1));
        mperson_twice_passwrod2.addTextChangedListener(new SearchWather3(mperson_twice_passwrod2));
        initData();
    }

    private void initData() {
        person_twice_passwrodback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mperson_twice_password_eyeselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_twice_password_closeeye1.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mperson_twice_passwrod1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mperson_twice_passwrod1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mperson_twice_password_eyeselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_twice_password_closeeye2.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mperson_twice_passwrod2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mperson_twice_passwrod2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        Intent intent = getIntent();
        //上个页面传过来用户输入的账号和验证码用于网络请求的参数
        username = intent.getStringExtra("mperson_center_forget_possword1text");
        body = intent.getStringExtra("body");
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取屏幕上的密码
                mperson_twice_passwrod11 = mperson_twice_passwrod1.getText().toString();
                mperson_twice_passwrod22 = mperson_twice_passwrod2.getText().toString();
                if (!mperson_twice_passwrod11.isEmpty() && !mperson_twice_passwrod22.isEmpty()) {
                    if (mperson_twice_passwrod11.length() < 6) {
                        ToastUtils.getInstance(Persontwice_password.this).showMessage("密码长度不能小于6位");
                    } else {
                        boolean booleanisnumberorabc = ExampleUtil.booleanisnumberorabc(mperson_twice_passwrod11);
                        if (booleanisnumberorabc == true) {
                            if (mperson_twice_passwrod11.equals(mperson_twice_passwrod22)) {
                                ShowVolleyrequest(username, mperson_twice_passwrod11, body);
                            } else {
                                ToastUtils.getInstance(Persontwice_password.this).showMessage("两次输入不一致");
                            }
                        } else {
                            ToastUtils.getInstance(Persontwice_password.this).showMessage("请输入带有字母和数字的密码");
                        }

                    }

                } else {
                    ToastUtils.getInstance(Persontwice_password.this).showMessage("输入信息不能为空");
                }
            }
        });
    }

    private void ShowVolleyrequest(String username, String mperson_twice_passwrod11, String body) {
        String url = ApiUrls.UPDATEPSW;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", mperson_twice_passwrod11);
        params.put("verifyCode", body);
        params.put("platform", "1002");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        UpdatePsw updatepsw = gson.fromJson(response.toString(), UpdatePsw.class);
                        boolean flag = updatepsw.isFlag();
                        if (flag == true) {
                            finish();
                           /* body = vipisuserexist.getResponse().getBody();
                            mperson_center_forget_possword2.setText(body);*/
                        } else {
                            String desc = updatepsw.getDesc();
                            ToastUtils.getInstance(Persontwice_password.this).showMessage(desc);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Persontwice_password.this).showMessage("系统繁忙");
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

    private class SearchWather3 implements TextWatcher {
        private EditText newperson_item3;

        public SearchWather3(EditText newperson_item3) {
            this.newperson_item3 = newperson_item3;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String editable = newperson_item3.getText().toString();
            String str3 = stringFilter3(editable.toString());
            if (!editable.equals(str3)) {
                newperson_item3.setText(str3);
                //设置新的光标所在位置
                newperson_item3.setSelection(str3.length());
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private String stringFilter3(String s) {
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }

}
