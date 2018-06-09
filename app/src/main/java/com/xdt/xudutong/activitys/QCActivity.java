package com.xdt.xudutong.activitys;

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
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.Jumindianzijiankangka;
import com.xdt.xudutong.bean.RSABean;
import com.xdt.xudutong.rsa.RSAEncrypt;
import com.xdt.xudutong.rsa.RSASignature;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToRoundCorner;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class QCActivity extends Base2Activity{
    public int steepstatesflag = 4;
    private LinearLayout mBack;
    private CircleImageView mIvHead;
    private ImageView mIvIcon;
    private TextView mTvName;
    private ImageView mIvCode;
    private String mToken1;
    private String mToken2;
    //商户私钥PKCS8
    public static String PRIVATEKEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMh9KKL/XzOiIWmjizahuDFw8/7irx7RoN9gc9EiOjYrWUpagSiyxS/1Z/0RkwXY/pCViEqsQvfp7Q9nX4moy6h2X85iinJdrvMHdTMT4afalhlNbxYV27hPBZR8iMqqy+A23RKPV9jsRp/ddL9rbqpHl7tKg+LYEAROqVrmIZb7AgMBAAECgYAJAl7OHE2p41I/DgNWfCPrzNiaDpOx/deN0ibCMhiNkJsdlT2Sl532zr9ShvSlYZ/m3WFXgbAstc1aUNnhNL2aCvgWYmmNL6sDkkBgmVEKcHxgVXMCxr1pNmf5FtGMJed7uCgMSo+pqCGot2P7KZld17zXwLOcGzlQutZZshtd2QJBAPHmHYG/EtD+Ph0lP+2rUzsBw75jbFMloAiEQo1XONXpENxZccV49L6xwOmgc/odFdA0QYAey743RaS/2ZoQ+S8CQQDULRPYMbihcss7ga9fcQcJ6DwEdDd5J6Aof8ybQPVpIOiX/eU77EJWfycCuFfSoYOuLg9YOmz/0Z7sDn1YfXP1AkAcPIJBPO6TWrohaNG3ioIipygUN0LTydaOZ/Hk0cOepKAFOKOhK2dWQyWDn+kpzu9W6GKk8/NQdt84RfS+mTCdAkAXKGhfE2Y0cOQPtaLfhuWexjMuxUR8u792TCSX2WovtEDOvg50EOBYsn4ehOJJAgnuS+au2YmmDkoLN7Wkn+S1AkBTHHBiwSijrCyLyu3ZjZixNbcYzmpvSHRNmByswsQu25IfV17SUu8noGXhZPkz8DliB6OeLpXqP/GsnmV7VLPq";
//    电子居民健康卡公钥
    public static String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCL31+BQzSZDkZVTeYGO/P6frD/qXs6aWWt55dFbNNJLbSuG8n1ZLxWWcn5UJves+PiCd02jT7zJo2LqJGPS/ytlhBBJz4AVPhDu/YEXjHTRWd5+X5dVB2xOVQB5n4Urv7KlrRLMZUX1vZMFWhNqLv9J6DBjGFWYhhzNA0eGQK5dQIDAQAB";
    private Jumindianzijiankangka mJumindianzijiankangka;
    private Bitmap mCodeBitmap;
    private Bundle mBundle;
    private String mRealName;
    private String mSex;

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
            this.getWindow().setStatusBarColor(Color.parseColor("#262727"));
        }
        setContentView(R.layout.activity_qc);
        initView();
    }


    public void initView() {
        mRealName = getIntent().getExtras().getString("realName");
        mSex = getIntent().getExtras().getString("sex");
        mToken1 = SpUtils.getParam(this ,"access_token", "");
        mToken2 = SpUtils.getParam(this, "x_auth_token", "");
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvHead = (CircleImageView) findViewById(R.id.iv_head);
        mIvCode = (ImageView) findViewById(R.id.iv_code);
        mIvIcon = (ImageView) findViewById(R.id.iv_icon);
        mTvName = (TextView) findViewById(R.id.tv_name);
        if ("男".equals(mSex)){
            mIvIcon.setImageResource(R.mipmap.qr_code_sex_man);
        }else {
            mIvIcon.setImageResource(R.mipmap.qr_code_sex);
        }
        mTvName.setText(mRealName);
        if (ApplicationController.mBitmap != null) {
//            bitmap = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(String.valueOf(mViploadUserInfo.getContent().getData().getHeadImg())), 5);
            mIvHead.setImageBitmap(ApplicationController.mBitmap);
        } else {
            mIvHead.setImageResource(R.mipmap.my_code_headimg);
        }
        initData();
    }
//

    private void initData() {
        String biz_content = "access_token=" + mToken1+"&x_auth_token=" + mToken2;
        LogUtil.e("biz_content=======",biz_content);
        String sign = RSASignature.sign(biz_content,PRIVATEKEY,"UTF-8");
        LogUtil.e("加签sign========",sign);
        Gson gosn = new Gson();
        RSABean mRSABean = new RSABean();
        mRSABean.setBiz_content(biz_content);
        mRSABean.setTimestamp(System.currentTimeMillis()+"");
        mRSABean.setSign(sign);
        String obj = gosn.toJson(mRSABean);
        LogUtil.e("obj=======",obj);
        String rsaSign = null;
        try {
            rsaSign = RSAEncrypt.encrypt(obj,RSAEncrypt.getPublicKey(PUBLICKEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.e("加密rsaSign==========",rsaSign);
        String url = ApiUrls.DIANZIJUMINKA_HTTPS;
        Map<String,String> params = new HashMap<>();
        params.put("rsaSign",rsaSign);
        params.put("method","");
        params.put("serialNumber","A000001");
        params.put("platformType","1");
        params.put("timestamp",System.currentTimeMillis()+"");
        params.put("organizationCode","J000001");
        params.put("content","");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LogUtil.e("onResponse=========",response+"");
                        Object object = response.opt("code");
                        if ("R00002".equals(object.toString()) | "R00000".equals(object.toString())){
                            mJumindianzijiankangka = new Gson().fromJson(String.valueOf(response),Jumindianzijiankangka.class);
                            try {
                                String s = RSAEncrypt.decrypt(mJumindianzijiankangka.getContent().getData(),RSAEncrypt.getPrivateKey(PRIVATEKEY));
                                mCodeBitmap = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(s), 5);
                                mIvCode.setImageBitmap(mCodeBitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Object desc = null;
                            try {
                                desc = response.get("desc");
                                ToastUtils.getInstance(QCActivity.this).showMessage(desc + "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(QCActivity.this).showMessage("系统繁忙");
                        Log.e("LOGIN-ERROR", error.getMessage(), error);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        headers.put("access_token", mToken1);
                        headers.put("x_auth_token", mToken2);
                        return headers;
                    }
                };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }
}
