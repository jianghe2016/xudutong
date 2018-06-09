package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.SettlementaccountBinding;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.RSAutilsmy;
import com.xdt.xudutong.utils.TimerCountshape;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018\1\30 0030.
 */
//惠民宝—添加银行卡
public class Benefitthepeopleaddbankcard extends BaseActivity {
    private String rsaopenkey = RSAutilsmy.RSAOPENKEY;
    private String eventNo;
    private String smsSendNo;

    @Override
    public void initView() {
        final PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(rsaopenkey);
        LinearLayout mbenefitthepeopleaddbankcard_1back = (LinearLayout) findViewById(R.id.benefitthepeopleaddbankcard_1back);
        LinearLayout mbenefitthepeopleaddbankcard_1selectbank0 = (LinearLayout) findViewById(R.id.benefitthepeopleaddbankcard_1selectbank0);
        final Button mbenefitthepeopleaddbankcard_1sendmessage = (Button) findViewById(R.id.benefitthepeopleaddbankcard_1sendmessage);
        final Button mbenefitthepeopleaddbankcard_1submit = (Button) findViewById(R.id.benefitthepeopleaddbankcard_1submit);
        final EditText mbenefitthepeopleaddbankcard_1edutext1 = (EditText) findViewById(R.id.benefitthepeopleaddbankcard_1edutext1);
        final EditText mbenefitthepeopleaddbankcard_1edutext2 = (EditText) findViewById(R.id.benefitthepeopleaddbankcard_1edutext2);

        final EditText mbenefitthepeopleaddbankcard_1edutext4 = (EditText) findViewById(R.id.benefitthepeopleaddbankcard_1edutext4);
        final EditText mbenefitthepeopleaddbankcard_1edutext5 = (EditText) findViewById(R.id.benefitthepeopleaddbankcard_1edutext5);
        SpannableString ss = new SpannableString("请输入本人真实姓名");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        mbenefitthepeopleaddbankcard_1edutext1.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        mbenefitthepeopleaddbankcard_1back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeopleaddbankcard_1submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputmessage = mbenefitthepeopleaddbankcard_1edutext5.getText().toString();
                if (!TextUtils.isEmpty(inputmessage)) {
                    if (!TextUtils.isEmpty(eventNo) && !TextUtils.isEmpty(smsSendNo)) {
                        String minputmessage = RSAutilsmy.encryptDataByPublicKey(inputmessage.getBytes(), publicKey);
                        String meventNo = RSAutilsmy.encryptDataByPublicKey(eventNo.getBytes(), publicKey);
                        String msmsSendNo = RSAutilsmy.encryptDataByPublicKey(smsSendNo.getBytes(), publicKey);

                        ShowVolleyRequestforaddiccardsubmit(minputmessage, meventNo, msmsSendNo);
                    } else {
                        ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("输入短信验证码不正确");
                    }
                } else {
                    ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("输入短信验证码不能为空");
                }
            }
        });
        mbenefitthepeopleaddbankcard_1selectbank0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleaddbankcard.this, Benefitthepeopleaddbankcardselectbank.class);
                startActivity(intent);
            }
        });
        mbenefitthepeopleaddbankcard_1sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String custName = "城天";
                    String certNo = "540102198506020114";
                    String mobileNo = "18827212721";
                    String bindMedium = "6212560200123701123";
                    String corpMediumId = "15993622360";
                    String corpCisNo = "349";
                    String mediumId = "6214760200600605312";
                    String a = RSAutilsmy.encryptDataByPublicKey(custName.getBytes(), publicKey).replaceAll("\r|\n", "");
                    String b = RSAutilsmy.encryptDataByPublicKey(certNo.getBytes(), publicKey).replaceAll("\r|\n", "");
                    String c = RSAutilsmy.encryptDataByPublicKey(mobileNo.getBytes(), publicKey).replaceAll("\r|\n", "");
                    String d = RSAutilsmy.encryptDataByPublicKey(bindMedium.getBytes(), publicKey).replaceAll("\r|\n", "");
                    String e = RSAutilsmy.encryptDataByPublicKey(corpMediumId.getBytes(), publicKey).replaceAll("\r|\n", "");
                    String g = RSAutilsmy.encryptDataByPublicKey(mediumId.getBytes(), publicKey).replaceAll("\r|\n", "");


                    String s1 = mbenefitthepeopleaddbankcard_1edutext1.getText().toString();
                    String s2 = mbenefitthepeopleaddbankcard_1edutext2.getText().toString();
                    String s4 = mbenefitthepeopleaddbankcard_1edutext4.getText().toString();
                    if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && !TextUtils.isEmpty(s4)) {
                        Pattern idNumPattern = Pattern.compile("[1][34578]\\d{9}");
                        Matcher b2 = idNumPattern.matcher(s4);
                        if (b2.matches() == true) {
                            TimerCountshape timer = new TimerCountshape(60000, 1000, mbenefitthepeopleaddbankcard_1sendmessage, Benefitthepeopleaddbankcard.this);
                            timer.start();
                            ShowVolleyRequestforaddiccardsendmessage(a, b, c, d, e, corpCisNo, g);
                        }else{
                            ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("请输入正确的手机号");
                        }
                    } else {
                        ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("输入信息不能为空");
                    }
                }

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleaddbankcard_1);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);

    }

    @Override
    public void setSteepStatusBar(boolean isSetStatusBar) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    //请求绑卡
    public void ShowVolleyRequestforaddiccardsendmessage(String a, String b, String c, String d, String e, String corpCisNo, String g) {
        String url = ApiUrls.ACCOUNTBINDING;
        Map<String, String> params = new HashMap<String, String>();
        params.put("custName", a);
        params.put("certNo", b);
        params.put("mobileNo", c);
        params.put("bindMedium", d);
        params.put("corpMediumId", e);
        params.put("corpCisNo", "349");
        params.put("mediumId", g);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SettlementaccountBinding sttlementaccountBinding = gson.fromJson(response.toString(), SettlementaccountBinding.class);
                        String code = sttlementaccountBinding.getCode();
                        LogUtil.d("codecode",code);
                        if (code.equals("R00001")) {
                            SettlementaccountBinding.ContentBean.DataBean data = sttlementaccountBinding.getContent().getData();
                            //工行交易订单号
                            eventNo = data.getEventNo();
                            //短信验证码发送编号，只有启用短信验证码模式时返回
                            smsSendNo = data.getSmsSendNo();
                            ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("短信发送成功，请注意查收");
                        } else {
                            String desc = sttlementaccountBinding.getDesc();
                            ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("VolleyError",error+"");
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

    //请求绑卡  原合作方交易单号（RSA加密）,短信验证码编号（RSA加密）,短信验证码（RSA加密）
    public void ShowVolleyRequestforaddiccardsubmit(String minputmessage, String meventNo, String msmsSendNo) {
        String url = ApiUrls.ACCOUNTSCODEVERIFY;
        Map<String, String> params = new HashMap<String, String>();
        params.put("corpSernoOriginal", meventNo);
        params.put("smsSendNo", msmsSendNo);
        params.put("smsSCode", minputmessage);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals("R00001")){
                                ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("绑卡成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("系统繁忙");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Benefitthepeopleaddbankcard.this).showMessage("系统繁忙");
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
