package com.xdt.xudutong.personcenterfragment;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.VerifyCodeget;
import com.xdt.xudutong.bean.Vipregister;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ExampleUtil;
import com.xdt.xudutong.utils.TimerCount;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/11.
 */

public class Personnewpersonwrite extends BaseActivity {
    private EditText newperson_item1;
    private EditText newperson_item2;
    private EditText newperson_item3;
    private EditText newperson_item4;
    private EditText newperson_item5;
    private Editable newperson_item11;
    private Button newperson_item6;
    private TextView newperson_item7;
    private String str3;
    private String str4;
    private String str5;
    private ProgressBar person_newpewrsonwriteprogressbar1;
    private TextView mnewperson_itemhomeplace;
    private EditText mnewperson_itemhomeplace2;
    private String huji_code;
    private CheckBox prson_newpersonwrite_select1;
    private TextView mperson_zhucenote;
    private String yanzhengdata;
    private boolean savecomeinremberpassword = false;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.prson_newpersonwrite);
    }
    @Override
    public void initView() {
        LinearLayout person_newpewrsonwriteback1 = (LinearLayout) findViewById(R.id.person_newpewrsonwriteback);
        person_newpewrsonwriteback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    finish();
                }
            }
        });
        person_newpewrsonwriteprogressbar1 = (ProgressBar) findViewById(R.id.person_newpewrsonwriteprogressbar);
        newperson_item1 = (EditText) findViewById(R.id.newperson_item1);
        newperson_item2 = (EditText) findViewById(R.id.newperson_item2);
        newperson_item3 = (EditText) findViewById(R.id.newperson_item3);
        newperson_item4 = (EditText) findViewById(R.id.newperson_item4);
        newperson_item5 = (EditText) findViewById(R.id.newperson_item5);
        newperson_item6 = (Button) findViewById(R.id.newperson_item6);
        newperson_item7 = (TextView) findViewById(R.id.newperson_item7);
        prson_newpersonwrite_select1 = (CheckBox) findViewById(R.id.prson_newpersonwrite_select);
        mnewperson_itemhomeplace = (TextView) findViewById(R.id.newperson_itemhomeplace);
        mnewperson_itemhomeplace2 = (EditText) findViewById(R.id.newperson_itemhomeplace2);
        //注册按钮
        mperson_zhucenote = (TextView) findViewById(R.id.person_zhucenote);

        newperson_item3.addTextChangedListener(new SearchWather3(newperson_item3));
        newperson_item4.addTextChangedListener(new SearchWather4(newperson_item4));
        newperson_item5.addTextChangedListener(new SearchWather5(newperson_item5));
        newperson_item11 = newperson_item1.getText();
        LinearLayout person_newpersonwriteparents1 = (LinearLayout) findViewById(R.id.person_newpersonwriteparents);
        person_newpersonwriteparents1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        initData();
    }

    private String stringFilter3(String s) {
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }

    private String stringFilter4(String s) {
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }

    private String stringFilter5(String s) {
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
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
            str3 = stringFilter3(editable.toString());
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


    private class SearchWather4 implements TextWatcher {
        private EditText newperson_item4;

        public SearchWather4(EditText newperson_item4) {
            this.newperson_item4 = newperson_item4;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String editable = newperson_item4.getText().toString();
            str4 = stringFilter4(editable.toString());
            if (!editable.equals(str4)) {
                newperson_item4.setText(str4);
                //设置新的光标所在位置
                newperson_item4.setSelection(str4.length());
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private class SearchWather5 implements TextWatcher {
        private EditText newperson_item5;

        public SearchWather5(EditText newperson_item5) {
            this.newperson_item5 = newperson_item5;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String editable = newperson_item5.getText().toString();
            str5 = stringFilter5(editable.toString());
            if (!editable.equals(str5)) {
                newperson_item5.setText(str5);
                //设置新的光标所在位置
                newperson_item5.setSelection(str5.length());
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void initData() {

        //家庭住址的填写
        mnewperson_itemhomeplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personnewpersonwrite.this, Personwritedetails.class);
                    intent.putExtra("jiatingzhuzhi", "填写现居住地址");
                    //1表示第一次进入这个页面，即从注册页面进入，2表示从主页面补充个人信息
                    intent.putExtra("homeactivityaddhomeid", 1);
                    startActivityForResult(intent, 61);
                }
            }
        });
        //注册协议按钮
        mperson_zhucenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personnewpersonwrite.this, PersonZhuce.class);
                    startActivity(intent);
                }
            }
        });
        newperson_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String text2 = newperson_item2.getText().toString();
                    if (!text2.isEmpty()) {
                        Pattern idNumPattern = Pattern.compile("[1][345678]\\d{9}");
                        Matcher b2 = idNumPattern.matcher(text2);
                        if (b2.matches() == true) {
                            TimerCount timer = new TimerCount(60000, 1000, newperson_item6);
                            timer.start();
                            ShowVolleyrequest(text2);
                        } else {
                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("请输入正确的手机号");
                        }

                    } else {
                        ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("请输入正确的手机号");
                    }
                }
            }
        });
        //选中同意协议
        prson_newpersonwrite_select1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    savecomeinremberpassword = true;
                } else {
                    savecomeinremberpassword = false;
                }
            }
        });
        //点击提交
        newperson_item7.setOnClickListener(new View.OnClickListener() {
            //保存一卡通注册所填的数据
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (savecomeinremberpassword == true) {
                        //获取注册用户填写额信息
                        String s = newperson_item1.getText().toString();
                        String s1 = newperson_item2.getText().toString();
                        String s2 = newperson_item3.getText().toString();
                        String s3 = newperson_item4.getText().toString();
                        String s4 = newperson_item5.getText().toString();
                        String s5 = mnewperson_itemhomeplace.getText().toString();
                        String s6 = mnewperson_itemhomeplace2.getText().toString();
                        Log.i("获取注册用户名", s);
                        Log.i("获取注册手机号", s1);
                        Log.i("获取注册密码", s2);
                        Log.i("获取注册密码2", s3);
                        Log.i("获取注册验证码", s4);
                        if (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s.isEmpty()&& !s5.isEmpty()) {
                            boolean validTagAndAlias = ExampleUtil.isValidTagAndAlias(s);
                            if (validTagAndAlias == true) {
                                if (s2.length()<6){
                                    ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("密码长度不能小于6位");
                                }else{
                                    boolean booleanisnumberorabc = ExampleUtil.booleanisnumberorabc(s2);
                                    if (booleanisnumberorabc==true){
                                        if (s2.equals(s3)){
                                            if (!TextUtils.isEmpty(s4)) {
                                                //请求网络传入需要的四个参数
                                                person_newpewrsonwriteprogressbar1.setVisibility(View.VISIBLE);
                                                ShowVolleyrequest2(s, s1, s2, s4, s5, s6);
                                            } else {
                                                ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("验证码不能为空");
                                            }
                                        }else{
                                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("两次输入密码不一致");
                                        }
                                    }else{
                                        ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("请输入带有字母和数字的密码");
                                    }
                                }

                            } else {
                                ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("昵称不能输入非法字符");
                            }
                        } else {
                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("输入信息不能为空");
                        }

                    } else {
                        ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("请查看并同意用户协议");
                    }
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 61) {
            if (resultCode == 62) {
                String writedetailshuji_addr1 = data.getStringExtra("writedetailshuji_addr");
                huji_code = data.getStringExtra("writedetailshuji_code");
                mnewperson_itemhomeplace.setText(writedetailshuji_addr1);
            }
        }
    }


    //注册用户
    private void ShowVolleyrequest2(String s, String s1, String s2, String s4, String s5, String s6) {
        String url = ApiUrls.REGISTER;
        Map<String, String> params = new HashMap<String, String>();
        params.put("nickname", s);
        params.put("username", s1);
        params.put("password", s2);
        params.put("verifyCode", s4);
        params.put("huji_addr", s5);
        params.put("huji_code", huji_code);
        params.put("id_addr", s6);
        params.put("platform", "1001");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Vipregister vipregister = gson.fromJson(response.toString(), Vipregister.class);
                        int flag = vipregister.getFlag();
                        if (flag == 1) {
                            person_newpewrsonwriteprogressbar1.setVisibility(View.GONE);
                            showDialog();
                        } else {
                            person_newpewrsonwriteprogressbar1.setVisibility(View.GONE);
                            String  desc= vipregister.getDesc();
                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_newpewrsonwriteprogressbar1.setVisibility(View.GONE);
                ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("系统繁忙");
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void showDialog() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Personnewpersonwrite.this, R.style.my_dialog);
        final android.app.AlertDialog dialog = builder.create();
        View view = LayoutInflater.from(Personnewpersonwrite.this).inflate(R.layout.person_write_dialog, null);  //通过LayoutInflater获取布局
        TextView person_fankui_dialog_cancel1 = (TextView) view.findViewById(R.id.person_write_successdialog_button1);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        int layout_width = (int) ((widthPixels) * 0.5);// 宽度
        int layout_height = (int) ((heightPixels) * 0.2);// 高度
        //  lp.height = (int) ((widthPixels) * 0.6);
        // int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
        dialog.getWindow().setLayout(layout_width, layout_height); //对话框大小应根据屏幕大小调整

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0; // 新位置X坐标
        lp.y = -100; // 新位置Y坐标
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("注册成功");
                finish();
            }
        });
        dialog.show();
    }

    //传入手机号进行网络核实，获取验证码
    private void ShowVolleyrequest(String s1) {
        //获取设备号仅限手机
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        LogUtil.d("手机设备为=", DEVICE_ID);
        String url = ApiUrls.VERIFYCODE;
        //Volley请求网络进行判断is);
        Map<String, String> params = new HashMap<String, String>();
        params.put("phoneNum", s1);
        params.put("alias", DEVICE_ID);
        params.put("platform", "1001");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VerifyCodeget verifycodeget = gson.fromJson(response.toString(), VerifyCodeget.class);
                        int flag = verifycodeget.getFlag();
                        String desc = verifycodeget.getDesc();
                        if (flag == 1) {
                            //获取注册验证码
                            yanzhengdata = verifycodeget.getContent().getData();
                            LogUtil.d("注册验证码数据为=", yanzhengdata.toString());
                        } else if (flag == 2) {
                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage(desc);
                        } else {
                            ToastUtils.getInstance(Personnewpersonwrite.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personnewpersonwrite.this).showMessage("系统繁忙");
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
}
