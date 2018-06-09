package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
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
import com.xdt.xudutong.bean.VipupdatePwd;
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

public class Personitemtwotwice_password extends BaseActivity {
    private int[] image;
    private EditText mperson_itemtwo_twice_passwordtext1;
    private LinearLayout person_itemtwo_twice_passwordback1;
    private EditText mperson_itemtwo_twice_passwordtext2;
    private EditText mperson_itemtwo_twice_passwordtext3;
    private TextView mperson_twice_password_submit;
    int currentImg = 0;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_itemtwo_twice_password);
    }

    @Override
    public void initView() {
        image = new int[]{
                R.drawable.person_centeropeneye,
                R.drawable.person_centercloseeye,
        };
        person_itemtwo_twice_passwordback1 = (LinearLayout) findViewById(R.id.person_itemtwo_twice_passwordback);
        mperson_itemtwo_twice_passwordtext2 = (EditText) findViewById(R.id.person_itemtwo_twice_passwordtext2);
        mperson_itemtwo_twice_passwordtext3 = (EditText) findViewById(R.id.person_itemtwo_twice_passwordtext3);
        mperson_itemtwo_twice_passwordtext1 = (EditText) findViewById(R.id.person_itemtwo_twice_passwordtext1);
        mperson_twice_password_submit = (TextView) findViewById(R.id.person_twice_password_submit);
        //眼的点击按钮
        LinearLayout mperson_twice_password_eyeselect2 = (LinearLayout) findViewById(R.id.person_twice_password_eyeselect2);
        LinearLayout mperson_twice_password_eyeselect3 = (LinearLayout) findViewById(R.id.person_twice_password_eyeselect3);
        final ImageView mperson_itemtwo_twice_passwordimg2 = (ImageView) findViewById(R.id.person_itemtwo_twice_passwordimg2);
        final ImageView mperson_itemtwo_twice_passwordimg3 = (ImageView) findViewById(R.id.person_itemtwo_twice_passwordimg3);
        mperson_itemtwo_twice_passwordtext2.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext2));
        mperson_itemtwo_twice_passwordtext3.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext3));
        mperson_itemtwo_twice_passwordtext1.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext1));
        mperson_twice_password_eyeselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_itemtwo_twice_passwordimg2.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mperson_itemtwo_twice_passwordtext2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mperson_itemtwo_twice_passwordtext2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mperson_twice_password_eyeselect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_itemtwo_twice_passwordimg3.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mperson_itemtwo_twice_passwordtext3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mperson_itemtwo_twice_passwordtext3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        final String itemtwoxiugaiwordusername = intent.getStringExtra("itemtwoxiugaiwordusername");
        LogUtil.d("修改密码收到的username====", itemtwoxiugaiwordusername.toString());
        person_itemtwo_twice_passwordback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("输入不能为空");
                }
            }
        });
        mperson_itemtwo_twice_passwordtext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                mperson_itemtwo_twice_passwordtext1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            //获取焦点
                        } else {
                            //失去焦点进行请求
                            if (!TextUtils.isEmpty(itemtwoxiugaiwordusername)) {
                                ShowVolleyrequest(itemtwoxiugaiwordusername, s.toString());
                            } else {
                                ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("系统繁忙");
                            }

                        }
                    }
                });
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

                    private boolean flag;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipcheckPwd vipcheckpwd = gson.fromJson(response.toString(), VipcheckPwd.class);
                        flag = vipcheckpwd.isFlag();
                        if (flag == true) {
                            mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String s = mperson_itemtwo_twice_passwordtext2.getText().toString();
                                    String s1 = mperson_itemtwo_twice_passwordtext3.getText().toString();
                                    if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1)) {
                                        if (s.length() < 6) {
                                            ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("密码长度不能小于6位");
                                        } else {
                                            boolean booleanisnumberorabc = ExampleUtil.booleanisnumberorabc(s);
                                            if (booleanisnumberorabc == true) {
                                                if (s.equals(s1)) {
                                                    ShowVolleyrequestforupdatePwd(username, s);
                                                } else {
                                                    ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("两次输入密码不一致");
                                                }
                                            } else {
                                                ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("请输入带有字母和数字的密码");
                                            }
                                        }
                                    } else {
                                        ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("输入不能为空");
                                    }

                                }
                            });
                        } else {
                            ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("原密码不正确");
                            mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("原密码不正确");
                                }
                            });
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
                mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("系统繁忙");
                    }
                });
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

    private void ShowVolleyrequestforupdatePwd(String username, String newpassword) {
        String url = ApiUrls.UPDATEPWD;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("newPwd", newpassword);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private boolean flag;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VipupdatePwd vipupdatepwd = gson.fromJson(response.toString(), VipupdatePwd.class);
                        flag = vipupdatepwd.isFlag();
                        if (flag == true) {
                            Intent intent = new Intent(Personitemtwotwice_password.this, Personitemtwotwice_passwordsuccess.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String desc = vipupdatepwd.getDesc();
                            ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage(desc);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemtwotwice_password.this).showMessage("系统繁忙");
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
