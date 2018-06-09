package com.xdt.xudutong.huiminbao;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.xdt.xudutong.frgment.BaseActivity;
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

public class Fundsbuyin extends BaseActivity {

    private FrameLayout mfundsbuyinlayoutall;
    private String mCardNo;
    private EditText mEtBuy;
    private String mCustNo;
    private String mFundCashqua;
    private TextView mTvChongzhi;
    private Button mFundsbuyinlayoutbutton;
    DecimalFormat df = new DecimalFormat("#.00");
    com.wind.keyboard.OfoKeyboardView mKeyboardView;
    private OfoKeyboard mKeyboard;
    private EditText mEtPsd;
    private LinearLayout mLlPsdLayout;
    private ImageView mIvDimiss;
    private Button mBtn_next;
    private TextView mPad_forget;

    @Override
    public void initView() {
        mKeyboard = new OfoKeyboard(this);
        mKeyboardView = (OfoKeyboardView) findViewById(R.id.keyboard_view);
        mLlPsdLayout = (LinearLayout) findViewById(R.id.ll_psd_layout);
        mIvDimiss = (ImageView) findViewById(R.id.iv_dismiss);
        mBtn_next = (Button) findViewById(R.id.btn_next);
        mEtPsd = (EditText) findViewById(R.id.et_psd);
        mPad_forget = (TextView) findViewById(R.id.tv_forget_psd);
        mEtBuy = (EditText) findViewById(R.id.et_buy);
        mTvChongzhi = (TextView) findViewById(R.id.tv_chongzhi);
        mTvChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(Fundsbuyin.this).showMessage("充值");
            }
        });
        LinearLayout mfundsbuyinlayoutback = (LinearLayout) findViewById(R.id.fundsbuyinlayoutback);
        mfundsbuyinlayoutall = (FrameLayout) findViewById(R.id.fundsbuyinlayoutall);
        LinearLayout mfundsbuyinlayoutselecticcard = (LinearLayout) findViewById(R.id.fundsbuyinlayoutselecticcard);
        mFundsbuyinlayoutbutton = (Button) findViewById(R.id.fundsbuyinlayoutbutton);
        mfundsbuyinlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFundsbuyinlayoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Fundsbuyin.this, Fundsbuyinsuccessful.class);
//                startActivity(intent);
                Log.e("弹出-----------","弹出");
                mLlPsdLayout.setVisibility(View.VISIBLE);
                mFundsbuyinlayoutbutton.setEnabled(false);
                mKeyboard.attachTo(mEtPsd,true);
            }
        });
        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mEtPsd.getText().toString()!= null && mEtPsd.getText().length() == 6){
                    NotSufficientFundsFragment mNotSufficientFundsFragment = new NotSufficientFundsFragment();
                    mNotSufficientFundsFragment.show(getSupportFragmentManager(),null);
                    initData();
                }else {
                    ToastUtils.getInstance(Fundsbuyin.this).showMessage("请输入正确密码");
                    return;
                }
            }
        });

        mIvDimiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlPsdLayout.setVisibility(View.GONE);
                mFundsbuyinlayoutbutton.setEnabled(true);
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
        mFundsbuyinlayoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("弹出-----------","弹出");
                mLlPsdLayout.setVisibility(View.VISIBLE);
                mFundsbuyinlayoutbutton.setEnabled(false);
                mKeyboard.attachTo(mEtPsd,true);
            }
        });
        mEtBuy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null &&s.toString().length() > 0 && !s.toString().startsWith(".") && !s.toString().endsWith(".") && !s.toString().startsWith("00")){
                    mFundsbuyinlayoutbutton.setEnabled(true);
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
                    mFundsbuyinlayoutbutton.setEnabled(false);
                    return;
                }
            }
        });
        mfundsbuyinlayoutselecticcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                showpopwindow();
                BankCardSelectFragment mBankCardSelectFragment = new BankCardSelectFragment();
                mBankCardSelectFragment.show(getSupportFragmentManager(),null);
               /* FragmentManager fm = getFragmentManager();
                EditNameDialogFragment dialog = EditNameDialogFragment.newInstance();*/

                //DialogFragment将自己提交给FragmentManager管理
                //DIALOG_DATE是一个string字符串，是DialogFragment在fm队列中对应的识别参数
//                FragmentManager fm = getFragmentManager();
//                EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
//                editNameDialog.show(fm, "EditNameDialog");

                //DiaFragment.newInstance().show(getSupportFragmentManager(),"dialog");

/*
                FragmentManager fm = getFragmentManager();
                EditNameDialogFragment fragment1 = EditNameDialogFragment.newInstance(R.layout.view1);
                EditNameDialogFragmenttwo fragment2 = EditNameDialogFragmenttwo.newInstance(R.layout.view2);
                EditNameDialogFragment.newInstance(R.layout.view1).show(fm, "DF_Dialog_2");*/
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.fundsbuyinlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private void showpopwindow() {
        // 一个自定义的布局，作为显示的内容
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.6); // 屏幕高（像素，如：800p）
        View contentView1 = LayoutInflater.from(Fundsbuyin.this).inflate(
                R.layout.home_huiminbaobuyinpopwindow, null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1,
                ViewGroup.LayoutParams.MATCH_PARENT, screenHeight, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow1.setAnimationStyle(R.style.AnimationFade2);
        popupWindow1.setTouchable(true);
        popupWindow1.setOutsideTouchable(false);
        popupWindow1.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popupWindow1.showAtLocation(mfundsbuyinlayoutall, Gravity.BOTTOM, 10, 15);
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowcanle = (LinearLayout) contentView1.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowcanle);
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowitem1 = (LinearLayout) contentView1.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowitem1);
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowitem2 = (LinearLayout) contentView1.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowitem2);
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowitem3 = (LinearLayout) contentView1.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowitem3);
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowitem4 = (LinearLayout) contentView1.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowitem4);
        mhome_huiminbaoselectcard_buttom_popwindowcanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        mhome_huiminbaoselectcard_buttom_popwindowitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        mhome_huiminbaoselectcard_buttom_popwindowitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        mhome_huiminbaoselectcard_buttom_popwindowitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        mhome_huiminbaoselectcard_buttom_popwindowitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
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
            mFundCashqua = RSAEncrypt.encrypt(mEtBuy.getText().toString().trim(),RSAEncrypt.getPublicKey(ICBC.ICBC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = ApiUrls.HUI_MIN_BAO_JI_JIN_BUY;

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
                                Intent intent = new Intent().setClass(Fundsbuyin.this,Fundsbuyinsuccessful.class);
                                startActivity(intent);
                            } else {
                                ToastUtils.getInstance(Fundsbuyin.this).showMessage(response.get("desc").toString());
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

    //是否有小绿功能的判断
    private void ShowPopupWindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.deal_error, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreenbikesearchresultnoxdtcard = (ImageView) inflate.findViewById(R.id.littlegreenbikesearchresultnoxdtcard);
        LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
/*        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
        mpopupWindow1.setTouchable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        mpopupWindow1.setFocusable(false);
        //  mpopupWindow1.setOutsideTouchable(true);
        mpopupWindow1.setOutsideTouchable(false);
        mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mfundsbuyinlayoutall, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow1.dismiss();
            }
        });
    }
}
