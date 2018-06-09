package com.xdt.xudutong.huiminbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.orhanobut.logger.Logger;
import com.wind.keyboard.OfoKeyboard;
import com.wind.keyboard.OfoKeyboardView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class Fundsredemption extends Base2Activity {
    private String mCardNo;
    private EditText mEtShuhui;
    private String mCustNo;
    private String mFundCashqua;
    private TextView mTvMoney;
    private TextView mTvAll;
    private String mZhanghu="100";
    private View parentView;
    DecimalFormat df = new DecimalFormat("#.00");
    com.wind.keyboard.OfoKeyboardView mKeyboardView;
    private OfoKeyboard mKeyboard;
    private EditText mEtPsd;
    private LinearLayout mLlPsdLayout;
    private ImageView mIvDimiss;
    private Button mBtn_next;
    private TextView mPad_forget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentView = LayoutInflater.from(this).inflate(R.layout.funsredemptionlayout,null);
        setContentView(parentView);
        initView();
    }

    public void initView() {
        mKeyboard = new OfoKeyboard(this);
        mKeyboardView = (OfoKeyboardView) findViewById(R.id.keyboard_view);
        mLlPsdLayout = (LinearLayout) findViewById(R.id.ll_psd_layout);
        mIvDimiss = (ImageView) findViewById(R.id.iv_dismiss);
        mBtn_next = (Button) findViewById(R.id.btn_next);
        mEtPsd = (EditText) findViewById(R.id.et_psd);
        mPad_forget = (TextView) findViewById(R.id.tv_forget_psd);
        LinearLayout mfunsredemptionlayoutback = (LinearLayout) findViewById(R.id.funsredemptionlayoutback);
        final Button mfunsredemptionlayoutbacksubmit = (Button) findViewById(R.id.funsredemptionlayoutbacksubmit);
        mEtShuhui = (EditText) findViewById(R.id.et_shuhui);
        mTvMoney = (TextView) findViewById(R.id.tv_money);
        mTvMoney.setText("可赎回金额:"+mZhanghu+"元");
        mTvAll = (TextView) findViewById(R.id.tv_all);

        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mEtPsd.getText().toString()!= null && mEtPsd.getText().length() == 6){
                    initData();
                }else {
                    ToastUtils.getInstance(Fundsredemption.this).showMessage("请输入正确密码");
                    return;
                }
            }
        });

        mIvDimiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlPsdLayout.setVisibility(View.GONE);
                mfunsredemptionlayoutbacksubmit.setEnabled(true);
            }
        });
        mTvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("全部赎回==========","全部赎回");
                mEtShuhui.setText(df.format(Double.parseDouble(mZhanghu)));
            }
        });
       mEtPsd.setOnTouchListener(new View.OnTouchListener() {
           int touch_flag=0;
           @Override
           public boolean onTouch(View v, MotionEvent event) {
                   touch_flag++;
                   if(touch_flag==2){
                       touch_flag=0;
                       mKeyboard.attachTo(mEtPsd,true);
                   }
                   return false;
           }
       });
        mfunsredemptionlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfunsredemptionlayoutbacksubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("弹出-----------","弹出");
                mLlPsdLayout.setVisibility(View.VISIBLE);
                mfunsredemptionlayoutbacksubmit.setEnabled(false);
                mKeyboard.attachTo(mEtPsd,true);
            }
        });
        mEtShuhui.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null &&s.toString().length() > 0 && !s.toString().startsWith(".") && !s.toString().endsWith(".") && !s.toString().startsWith("00")){
                    mfunsredemptionlayoutbacksubmit.setEnabled(true);
                    if (s.toString().contains(".")){
                        int posDot = s.toString().indexOf(".");
                        if (s.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
//                        mAmount = df.format(Double.parseDouble(s.toString()));
                    }else {
//                        mAmount = df.format(Double.parseDouble(s.toString()));
                    }
                }else {
                    mfunsredemptionlayoutbacksubmit.setEnabled(false);
                    return;
                }
            }
        });
    }

    private void initData(){
//        custNo	Y	合作方客户唯一编号（RSA加密,例如：许都通号）
//        cardNo	Y	客户卡号（联名卡）（RSA加密）
//        fundId	Y	基金代码（默认添益快线：000848）
//        fundCashqua	Y	赎回份额（RSA加密）

        try {
            mCardNo = RSAEncrypt.encrypt(ICBC.TEST_NUM, RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
            mCustNo = RSAEncrypt.encrypt(ICBC.CUST_NO,RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
            mFundCashqua = RSAEncrypt.encrypt(mEtShuhui.getText().toString().trim(),RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ApiUrls.HUI_MIN_BAO_JI_JIN_SHUHUI;

        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("fundId", "000848");
        params.put("cardNo", mCardNo);
        params.put("custNo", mCustNo);
        params.put("fundCashqua", mFundCashqua);

        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response + "");
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                                Intent intent = new Intent().setClass(Fundsredemption.this,Fundsredemptionsuccessful.class);
                                startActivity(intent);
                            } else {
                                ToastUtils.getInstance(Fundsredemption.this).showMessage(response.get("desc").toString());
                            }
                        } catch (Exception e) {
                            LogUtil.e(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }


    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
