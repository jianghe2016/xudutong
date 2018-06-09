package com.xdt.xudutong.activitys;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingNewPsdActivity extends Base2Activity {

    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.tv_text1)
    TextView mTvText1;
    @BindView(R.id.et_new_psd)
    EditText mEtNewPsd;
    @BindView(R.id.iv_view2)
    ImageView mIvView2;
    @BindView(R.id.textView14)
    TextView mTextView14;
    @BindView(R.id.et_again_psd)
    EditText mEtAgainPsd;
    @BindView(R.id.iv_view3)
    ImageView mIvView3;
    @BindView(R.id.tv_text2)
    TextView mTvText2;
    @BindView(R.id.btn_alter_psd)
    Button mBtnAlterPsd;
    @BindView(R.id.keyboard_view)
    OfoKeyboardView mKeyboardView;
    private LinearLayout mBack;
    private TextView mTvTitle;
    private OfoKeyboard mKeyboard;
    int currentImg = 0;
    private int[] image;
    public static final  String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaRpKrIMWp9wJytrXAM6pAKH3C4iB1Sg5leFYnITe8j5OU+LwBWPhf483CKm7mRJdg1r6BKMHa0vvuZDU1CKttVD8h/y4Ip1tyKWkTwUgZxKK61YU93KsaIR56QQnZ3PKhQu5GrKBsr8u7XVfyvOXOe7UsNbfxb2pB3KehJNNGFwIDAQAB";
    private String mToken1;
    private String mToken2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_new_psd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mToken1 = SpUtils.getParam(this ,"access_token", "");
        mToken2 = SpUtils.getParam(this, "x_auth_token", "");
        if ("0".equals(getIntent().getExtras().getString("mark"))){
            mTvText1.setText("设置新密码");
            mTvTitle.setText("设置新密码");
            mBtnAlterPsd.setText("确认");
            mTvText.setVisibility(View.INVISIBLE);
        }else{
            mTvText1.setText("修改交易密码");
            mTvTitle.setText("修改交易密码");
            mTvText.setVisibility(View.VISIBLE);
        }
        image = new int[]{
                R.mipmap.change_psd_open_eye,
                R.mipmap.change_psd_close_eye,
        };
        //获取到keyboard对象
        mKeyboard = new OfoKeyboard(this);
        mEtAgainPsd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        mEtNewPsd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        mBack = (LinearLayout) findViewById(R.id.back);
        //眼的点击按钮
        ImageView mperson_twice_password_eyeselect2 = (ImageView) findViewById(R.id.iv_view2);
        ImageView mperson_twice_password_eyeselect3 = (ImageView) findViewById(R.id.iv_view3);
        final ImageView mperson_itemtwo_twice_passwordimg2 = (ImageView) findViewById(R.id.iv_view2);
        final ImageView mperson_itemtwo_twice_passwordimg3 = (ImageView) findViewById(R.id.iv_view3);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEtNewPsd.setOnTouchListener(new View.OnTouchListener() {
            int touch_flag=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touch_flag++;
                if(touch_flag==2){
                    touch_flag=0;
                    mKeyboard.attachTo(mEtNewPsd, true) ;
                }
                return false; }});
        mEtAgainPsd.setOnTouchListener(new View.OnTouchListener() {
            int touch_flag=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touch_flag++;
                if(touch_flag==2){
                    touch_flag=0;
                    mKeyboard.attachTo(mEtAgainPsd, true) ;
                }
                return false; }});
        mperson_twice_password_eyeselect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 1) {
                    currentImg = -1;
                }
                mperson_itemtwo_twice_passwordimg2.setImageResource(image[++currentImg]);
                if (currentImg == 0) {
                    mEtNewPsd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEtNewPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                    mEtAgainPsd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mEtAgainPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
            mKeyboard.attachTo(mEtAgainPsd, true);//eiditext绑定keyboard，false表示普通数字键盘
            mKeyboard.attachTo(mEtNewPsd, true);//eiditext绑定keyboard，false表示普通数字键盘
    }


    @OnClick({R.id.et_new_psd, R.id.iv_view2, R.id.et_again_psd, R.id.iv_view3, R.id.btn_alter_psd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_new_psd:
                LogUtil.e("new==========","0000000000");
//                mKeyboard.attachTo(mEtNewPsd, true);//eiditext绑定keyboard，false表示普通数字键盘
                break;
            case R.id.iv_view2:
                break;
            case R.id.et_again_psd:
                LogUtil.e("again==========","**************");
//                mKeyboard.attachTo(mEtAgainPsd, true);//eiditext绑定keyboard，false表示普通数字键盘
                break;
            case R.id.iv_view3:
                break;
            case R.id.btn_alter_psd:
                if (!TextUtils.isEmpty(mEtNewPsd.getText().toString()) && mEtNewPsd.getText().toString().length() == 6 && (mEtNewPsd.getText().toString().equals(mEtAgainPsd.getText().toString()))){
                    LogUtil.e("000000000===",mEtNewPsd.getText().toString());
                    LogUtil.e("111111111===",mEtAgainPsd.getText().toString());
                    initData();
                }else {
                    LogUtil.e("000000000===",mEtNewPsd.getText().toString());
                    LogUtil.e("111111111===",mEtAgainPsd.getText().toString());
                    ToastUtils.getInstance(this).showMessage("密码设置错误");
                    return;
                }
                break;
        }
    }

    private void initData() {
        String rsaSign = null;
        try {
            rsaSign = RSAEncrypt.encrypt(mEtNewPsd.getText().toString(),RSAEncrypt.getPublicKey(KEY));
            LogUtil.e("rsaSign==========",rsaSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ApiUrls.SETTING_PAY_PSD;
        Map<String,String> params = new HashMap<>();
        params.put("payPassword",rsaSign);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        Object object = response.opt("code");
                        String desc = response.opt("desc").toString();
                        if ("R00001".equals(object.toString())){
                            ToastUtils.getInstance(SettingNewPsdActivity.this).showMessage(desc);
                            SpUtils.putParam(SettingNewPsdActivity.this,"paypsd","1");
                            finish();
                        }else {
                            Object desc2 = null;
                            try {
                                desc2 = response.get("desc");
                                ToastUtils.getInstance(SettingNewPsdActivity.this).showMessage(desc2 + "");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(SettingNewPsdActivity.this).showMessage("系统繁忙");
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
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

}
