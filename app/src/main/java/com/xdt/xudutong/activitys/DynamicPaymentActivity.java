package com.xdt.xudutong.activitys;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.DynamicPaymentBean;
import com.xdt.xudutong.bean.RSABean;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.rsa.RSASignature;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.ToRoundCorner;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.xdt.xudutong.activitys.QCActivity.PRIVATEKEY;

public class DynamicPaymentActivity extends Base2Activity {

    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.select_payment_way)
    TextView mSelectPaymentWay;
    @BindView(R.id.rel_select_way)
    RelativeLayout mRelSelectWay;
    @BindView(R.id.titlle_bar_layout2)
    LinearLayout mTitlleBarLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setStatusBarColor(Color.parseColor("#2673D7"));
        }
        setContentView(R.layout.activity_dynamic_payment);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitlleBarLayout2.setBackgroundColor(Color.parseColor("#2673D7"));
        mTvTitle.setText("动态二维码");
        initData();
    }

    private void initData() {
        LogUtil.e("isPad ==============", isPad(this) + "");
        String access_token = "42619d2e-e866-42b0-bebe-13e623e501e5";
        String x_auth_token = "9e915e15-2930-4441-887f-ed38bd480a33";
        String accNo = "6216261000000002485";
        String mobile = "13525677809";
        String cardAttr = "01";
        String deviceID = "123456999";
        String deviceType = "1";
        String accountIdHash = "00000002";
        String sourceIP = "111.13.100.91";
        String biz_content = "access_token=" + access_token + "&x_auth_token=" + x_auth_token + "&accNo=" + accNo + "&mobile=" + mobile + "&cardAttr=" + cardAttr + "&deviceID=" + deviceID + "&deviceType=" + deviceType + "&accountIdHash=" + accountIdHash + "&sourceIP=" + sourceIP;
        String sign = RSASignature.sign(biz_content, PRIVATEKEY, "UTF-8");
        Log.e("sign===========", sign);
        Gson mGson = new Gson();
        RSABean mRSABean = new RSABean();
        mRSABean.setBiz_content(biz_content);
        mRSABean.setTimestamp(System.currentTimeMillis() + "");
        mRSABean.setSign(sign);
        String obj = mGson.toJson(mRSABean);
        LogUtil.e("obj=======", obj);
        String rsaSign = null;
        try {
            rsaSign = RSAEncrypt.encrypt(obj, RSAEncrypt.getPublicKey(QCActivity.PUBLICKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.e("加密rsaSign==========", rsaSign);
        String url = ApiUrls.DYNAMIC_PAYMENT;
        Map<String, String> params = new HashMap<>();
        params.put("rsaSign", rsaSign);
        params.put("method", "");
        params.put("serialNumber", "A000001");
        params.put("platformType", "1");
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("organizationCode", "J000001");
        params.put("content", "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response + "");
                        Object object = response.opt("code");
                        if ("R00002".equals(object.toString()) | "R00000".equals(object.toString()) | "R00003".equals(object.toString())) {
                            DynamicPaymentBean mDynamicPaymentBean = new Gson().fromJson(String.valueOf(response), DynamicPaymentBean.class);
                            try {
                                String s = RSAEncrypt.decrypt(mDynamicPaymentBean.getContent().getData(), RSAEncrypt.getPrivateKey(QCActivity.PRIVATEKEY));
                                Bitmap mCodeBitmap = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(s), 5);
                                mIvCode.setImageBitmap(mCodeBitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Object desc = null;
                            try {
                                desc = response.get("desc");
                                ToastUtils.getInstance(DynamicPaymentActivity.this).showMessage(desc + "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(DynamicPaymentActivity.this).showMessage("系统繁忙");
                        Log.e("LOGIN-ERROR", error.getMessage(), error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", "42619d2e-e866-42b0-bebe-13e623e501e5");
                headers.put("x_auth_token", "9e915e15-2930-4441-887f-ed38bd480a33");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }


    /**
     * 判断当前设备是手机还是平板
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @OnClick({R.id.back, R.id.rel_select_way})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rel_select_way:
                break;
        }
    }
}
