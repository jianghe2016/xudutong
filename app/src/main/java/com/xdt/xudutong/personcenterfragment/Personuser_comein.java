package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.xdt.xudutong.bean.Tokenauth;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.frgment.MainActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.DataCleanManager;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Personuser_comein extends BaseActivity {

    private EditText person_item1_usename;
    private EditText person_item1_password;
    private TextView newusename;
    private TextView mcomein;
    private TextView forgetpassword;
    private LinearLayout personcomeinback1;
    private LinearLayout mperson_comein_layout;
    private boolean savecomeinremberpassword = false;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_listforzero);
    }

    @Override
    public void initView() {
        personcomeinback1 = (LinearLayout) findViewById(R.id.personcomeinback);
        mperson_comein_layout = (LinearLayout) findViewById(R.id.person_comein_layout);
        mcomein = (TextView) findViewById(R.id.person_comein1);
        newusename = (TextView) findViewById(R.id.person_center_newusename);
        forgetpassword = (TextView) findViewById(R.id.person_center_forget_password);
        person_item1_usename = (EditText) findViewById(R.id.person_item1_usename);
        person_item1_password = (EditText) findViewById(R.id.person_item1_password);
        CheckBox prson_comeinremberpassword1 = (CheckBox) findViewById(R.id.prson_comeinremberpassword);
        SpannableString ss = new SpannableString("手机号/用户名");
        SpannableString ss2 = new SpannableString("请输入您的密码");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(14, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //记住密码功能，进行回显
        String savecomeinusename = SpUtils.getParam(getApplicationContext(), "savecomeinusename", "");
        String savecomeinpassword = SpUtils.getParam(getApplicationContext(), "savecomeinpassword", "");
        boolean savecomeinpasswordstates = SpUtils.getParam(getApplicationContext(), "savecomeinpasswordstates", false);
        if (savecomeinpasswordstates){
            prson_comeinremberpassword1.setChecked(true);
            if (!TextUtils.isEmpty(savecomeinusename) && !TextUtils.isEmpty(savecomeinpassword)) {
                person_item1_usename.setText(savecomeinusename);
                person_item1_password.setText(savecomeinpassword);
            }
        }else{
            prson_comeinremberpassword1.setChecked(false);
            SpUtils.removeParam(getApplicationContext(),"savecomeinusename");
            SpUtils.removeParam(getApplicationContext(),"savecomeinpassword");
        }
        // 设置hint
        person_item1_usename.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        person_item1_password.setHint(new SpannedString(ss2)); // 一定要进行转换,否则属性会消失

        personcomeinback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* SpUtils.removeParam(getApplicationContext(), "access_token");
                SpUtils.removeParam(getApplicationContext(), "x_auth_token");
                SpUtils.removeParam(getApplicationContext(), "refresh_token");
                SpUtils.removeParam(getApplicationContext(), Personssecretstates);
                SpUtils.removeParam(getApplicationContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                SpUtils.removeParam(getApplicationContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                SpUtils.removeParam(getApplicationContext(), LITTLEGREENTRUENAMEREALSTATES);*/
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
        });
        //用户注册按钮
        newusename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personuser_comein.this, Personnewpersonwrite.class);
                    startActivity(intent);
                }
            }
        });
        //忘记密码逻辑
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personuser_comein.this, Personcenterforgetpassword.class);
                    startActivity(intent);
                }
            }
        });
        prson_comeinremberpassword1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    savecomeinremberpassword = true;
                } else {
                    savecomeinremberpassword = false;
                }
            }
        });
        //登录逻辑
        mcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String usenametextstring = person_item1_usename.getText().toString();
                String passwordtextstring = person_item1_password.getText().toString();
                Log.i("shuruzhanghaowei", usenametextstring);
                Log.i("shurumimawei", passwordtextstring);
                if (!usenametextstring.isEmpty()) {
                    if (!passwordtextstring.isEmpty()) {

                        ShowVolleyrequest(usenametextstring, passwordtextstring);
                    } else {
                        ToastUtils.getInstance(Personuser_comein.this).showMessage("密码不能为空");
                    }
                } else {
                    ToastUtils.getInstance(Personuser_comein.this).showMessage("账号不能为空");
                }
            }
            }
        });

        person_item1_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))
                {
                    //获取输入框的值即为：账号，密码
                    //do something;
                    String usenametextstring = person_item1_usename.getText().toString();
                    String passwordtextstring = person_item1_password.getText().toString();
                    Log.i("shuruzhanghaowei", usenametextstring);
                    Log.i("shurumimawei", passwordtextstring);
                    if (!usenametextstring.toString().isEmpty()) {
                        if (!passwordtextstring.toString().isEmpty()) {
                            ShowVolleyrequest(usenametextstring, passwordtextstring);
                        } else {
                            ToastUtils.getInstance(Personuser_comein.this).showMessage("密码不能为空");
                        }
                    } else {
                        ToastUtils.getInstance(Personuser_comein.this).showMessage("账号不能为空");
                    }
                }
                return false;

            }
        });
        //为了让登录键上移做出的修改
        // addLayoutListener(mperson_comein_layout,mcomein);
    }

    private void ShowVolleyrequest(final String usenametextstring, final String passwordtextstring) {
        String url = ApiUrls.AUTH;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("certType", "app");
        params.put("clientName", "android");
        params.put("username", usenametextstring);
        params.put("password", passwordtextstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Tokenauth tokenauth = gson.fromJson(response.toString(), Tokenauth.class);
                        boolean flag = tokenauth.isFlag();
                        if (flag ==true) {
                            ToastUtils.getInstance(Personuser_comein.this).showMessage("登录成功");
                            DataCleanManager.clearAllCache(getApplicationContext());
                            Tokenauth.ResponseBean.BodyBean tokenauthdata = tokenauth.getResponse().getBody();
                            String access_token = tokenauthdata.getAccess_token();
                            String x_auth_token = tokenauthdata.getX_auth_token();
                            String refresh_token = tokenauthdata.getRefresh_token();
                            SpUtils.putParam(getApplicationContext(), "access_token", access_token);
                            SpUtils.putParam(getApplicationContext(), "x_auth_token", x_auth_token);
                            SpUtils.putParam(getApplicationContext(), "refresh_token", refresh_token);
                            SpUtils.putParam(getApplicationContext(), "loadingstate", true);
                            SpUtils.putParam(getApplicationContext(), "loadingusername", true);
                            //保存账号和密码
                            if (savecomeinremberpassword == true) {
                                SpUtils.putParam(getApplicationContext(), "savecomeinusename", usenametextstring);
                                SpUtils.putParam(getApplicationContext(), "savecomeinpassword", passwordtextstring);
                                SpUtils.putParam(getApplicationContext(), "savecomeinpasswordstates", true);
                                LogUtil.d("账号为=", usenametextstring.toString());
                                LogUtil.d("密码为=", passwordtextstring.toString());
                            }else{
                                SpUtils.putParam(getApplicationContext(), "savecomeinpasswordstates", false);
                            }
                            Intent intent = new Intent(Personuser_comein.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.getInstance(Personuser_comein.this).showMessage("账号密码错误");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personuser_comein.this).showMessage("系统繁忙");
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
    /**
     *  1、获取main在窗体的可视区域
     *  2、获取main在窗体的不可视区域高度
     *  3、判断不可视区域高度
     *      1、大于100：键盘显示  获取Scroll的窗体坐标
     *                           算出main需要滚动的高度，使scroll显示。
     *      2、小于100：键盘隐藏
     *
     * @param main 根布局
     * @param scroll 需要显示的最下方View
     */
/*    public void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 30) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    main.scrollTo(0, srollHeight);
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }*/

}
