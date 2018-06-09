package com.xdt.xudutong.activitys;

import android.content.Intent;
import android.os.Bundle;
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
import com.xdt.xudutong.personcenterfragment.Personitemtwotwice_passwordsuccess;
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

public class AlterLoginPasswordActivity extends BaseActivity {

    private LinearLayout mBack;
    private TextView mTvTitle;
    private LinearLayout mLl_layout;
    private LinearLayout mLine;
    private TextView mTvOld;
    private TextView mTextView;
    private TextView mTextView1;
    private TextView mTextView2;
    private int[] image;
    private EditText mperson_itemtwo_twice_passwordtext1;
    private EditText mperson_itemtwo_twice_passwordtext2;
    private EditText mperson_itemtwo_twice_passwordtext3;
    private TextView mperson_twice_password_submit;
    int currentImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        initView();
    }

    public void initView() {
        String mark = getIntent().getStringExtra("mark");
        image = new int[]{
                R.mipmap.change_psd_open_eye,
                R.mipmap.change_psd_close_eye,
        };
        mBack = (LinearLayout) findViewById(R.id.back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLine = (LinearLayout) findViewById(R.id.line);
        mTextView = (TextView) findViewById(R.id.tv_text);
        mTextView1 = (TextView) findViewById(R.id.tv_text1);
        mTextView2 = (TextView) findViewById(R.id.tv_text2);
        mLl_layout = (LinearLayout) findViewById(R.id.ll_layout);
        mTvOld = (TextView) findViewById(R.id.tv_old);
        mperson_itemtwo_twice_passwordtext2 = (EditText) findViewById(R.id.et_new_psd);
        mperson_itemtwo_twice_passwordtext3 = (EditText) findViewById(R.id.et_again_psd);
        mperson_itemtwo_twice_passwordtext1 = (EditText) findViewById(R.id.et_old_psd);
        mperson_twice_password_submit = (TextView) findViewById(R.id.btn_alter_psd);
        //眼的点击按钮
        ImageView mperson_twice_password_eyeselect1 = (ImageView) findViewById(R.id.iv_view1);
        ImageView mperson_twice_password_eyeselect2 = (ImageView) findViewById(R.id.iv_view2);
        ImageView mperson_twice_password_eyeselect3 = (ImageView) findViewById(R.id.iv_view3);
        final ImageView mperson_itemtwo_twice_passwordimg1 = (ImageView) findViewById(R.id.iv_view1);
        final ImageView mperson_itemtwo_twice_passwordimg2 = (ImageView) findViewById(R.id.iv_view2);
        final ImageView mperson_itemtwo_twice_passwordimg3 = (ImageView) findViewById(R.id.iv_view3);
        mperson_itemtwo_twice_passwordtext2.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext2));
        mperson_itemtwo_twice_passwordtext3.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext3));
        mperson_itemtwo_twice_passwordtext1.addTextChangedListener(new SearchWather3(mperson_itemtwo_twice_passwordtext1));
        mperson_twice_password_eyeselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_itemtwo_twice_passwordimg1.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mperson_itemtwo_twice_passwordtext1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mperson_itemtwo_twice_passwordtext1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
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
        if ("login".equals(mark)){
            mTvTitle.setText("修改登录密码");
            initData();
        }else {

        }
    }

    @Override
    public void setMyContentView() {
            setContentView(R.layout.activity_alter_password);
    }

    private void initData() {
        Intent intent = getIntent();
        final String itemtwoxiugaiwordusername = intent.getStringExtra("itemtwoxiugaiwordusername");
        LogUtil.e("修改密码收到的username====", itemtwoxiugaiwordusername.toString());
        mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("输入不能为空");
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
                                ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("系统繁忙");
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
                                            ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("密码长度不能小于6位");
                                        } else {
                                            boolean booleanisnumberorabc = ExampleUtil.booleanisnumberorabc(s);
                                            if (booleanisnumberorabc == true) {
                                                if (s.equals(s1)) {
                                                    ShowVolleyrequestforupdatePwd(username, s);
                                                } else {
                                                    ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("两次输入密码不一致");
                                                }
                                            } else {
                                                ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("请输入带有字母和数字的密码");
                                            }
                                        }
                                    } else {
                                        ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("输入不能为空");
                                    }

                                }
                            });
                        } else {
                            ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("原密码不正确");
                            mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("原密码不正确");
                                }
                            });
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
                mperson_twice_password_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("系统繁忙");
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
                            Intent intent = new Intent(AlterLoginPasswordActivity.this, Personitemtwotwice_passwordsuccess.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String desc = vipupdatepwd.getDesc();
                            ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage(desc);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(AlterLoginPasswordActivity.this).showMessage("系统繁忙");
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
