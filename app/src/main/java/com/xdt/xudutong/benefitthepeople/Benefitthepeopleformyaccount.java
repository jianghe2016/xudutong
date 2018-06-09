package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
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
import com.xdt.xudutong.bean.SettlementaccounBalanceQuery;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.RSAutilsmy;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\2\5 0005.
 */
//个人信息页面
public class Benefitthepeopleformyaccount extends BaseActivity {
    int currentImg = 0;
    private int[] image;
    private String rsaopenkey = RSAutilsmy.RSAOPENKEY;
    private TextView mbenefitthepeopleformyaccountlayoutmoney;
    private String accountBalance;
    @Override
    public void initView() {
        final PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(rsaopenkey);
        image = new int[]{
                R.drawable.xdt_close_eye,
                R.drawable.xut_open_eye,
        };
        LinearLayout mbenefitthepeopleformyaccountlayoutback = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayoutback);
        TextView mbenefitthepeopleformyaccountlayoutmoneydetails = (TextView) findViewById(R.id.mbenefitthepeopleformyaccountlayoutmoneydetails);
        LinearLayout mbenefitthepeopleformyaccountlayouteyes = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayouteyes);
        final ImageView mbenefitthepeopleformyaccountlayouteyesimg = (ImageView) findViewById(R.id.mbenefitthepeopleformyaccountlayouteyesimg);
        mbenefitthepeopleformyaccountlayoutmoney = (TextView) findViewById(R.id.mbenefitthepeopleformyaccountlayoutmoney);
        LinearLayout mbenefitthepeopleformyaccountlayoutmoneylayout = (LinearLayout) findViewById(R.id.mbenefitthepeopleformyaccountlayoutmoneylayout);

        LinearLayout mbenefitthepeopleformyaccountlayoutitem0 = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayoutitem0);
        LinearLayout mbenefitthepeopleformyaccountlayoutmiddleitem1 = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayoutmiddleitem1);
        LinearLayout mbenefitthepeopleformyaccountlayoutmiddleitem2 = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayoutmiddleitem2);
        LinearLayout mbenefitthepeopleformyaccountlayoutmiddleitem3 = (LinearLayout) findViewById(R.id.benefitthepeopleformyaccountlayoutmiddleitem3);
        mbenefitthepeopleformyaccountlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeopleformyaccountlayouteyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mbenefitthepeopleformyaccountlayouteyesimg.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mbenefitthepeopleformyaccountlayoutmoney.setText("***,***");
                } else {
                    if (!TextUtils.isEmpty(accountBalance)){
                        mbenefitthepeopleformyaccountlayoutmoney.setText(accountBalance);
                    }else{
                        ToastUtils.getInstance(Benefitthepeopleformyaccount.this).showMessage("请检查网络");
                    }

                }
            }
        });
        mbenefitthepeopleformyaccountlayoutmoneydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this,Benefitthepeopletransactiondetail.class);
                startActivity(intent);
            }

        });
        mbenefitthepeopleformyaccountlayoutitem0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this, Benfitthepeopleaddiccard.class);
                startActivity(intent);
            }
        });
        mbenefitthepeopleformyaccountlayoutmoneylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this, Benefitthepeopleaccountdetails.class);
                startActivity(intent);
            }
        });
        //充值，提现，账单三个按钮
        mbenefitthepeopleformyaccountlayoutmiddleitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this, Benefitthepeoplererechargeforcard.class);
                startActivity(intent);
            }
        });
        mbenefitthepeopleformyaccountlayoutmiddleitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this, BenefitthepeopleWithdrawing.class);
                startActivity(intent);
            }
        });
        mbenefitthepeopleformyaccountlayoutmiddleitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopleformyaccount.this, Benefitthepeoplebill.class);
                startActivity(intent);
            }
        });
        String a="6214760200601600080";
        String b = RSAutilsmy.encryptDataByPublicKey(a.getBytes(), publicKey).replaceAll("\r|\n", "");

        LogUtil.d("mediumId,aaaa",a);
        LogUtil.d("mediumId,bbbb",b);
        Requestvolleyformymoney(b);
    }

    private void Requestvolleyformymoney(String mediumId) {
        String murl = ApiUrls.ACCOUNBALANCEQUERY;
        Map<String, String> params = new HashMap<>();
        params.put("mediumId", mediumId);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, murl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SettlementaccounBalanceQuery settlementaccounBalanceQuery = gson.fromJson(response.toString(), SettlementaccounBalanceQuery.class);
                        final String code = settlementaccounBalanceQuery.getCode();
                        if (code.equals("R00001")){
                            SettlementaccounBalanceQuery.ContentBean content = settlementaccounBalanceQuery.getContent();
                            accountBalance = content.getData().getAccountBalance();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("工商二类户余额请求异常=", error.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(Benefitthepeopleformyaccount.this).getRequestQueue().add(jsonRequest);


    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleformyaccountlayout);
    }



}
