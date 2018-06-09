package com.xdt.xudutong.homefragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.unionpay.UPPayAssistEx;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.HomePhonemoneyselectbuttonAdapter;
import com.xdt.xudutong.adapder.Homephonemonetselectmoneyadapter;
import com.xdt.xudutong.bean.DxbosscheckArea;
import com.xdt.xudutong.bean.DxbossgetTelbalance;
import com.xdt.xudutong.bean.JiaoFeiOrderBean;
import com.xdt.xudutong.bean.PayBean;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.Phonenumberreguest;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xdt.xudutong.R.id.home_cardgroup_buttonphonemoneyinfo2eduittext2;

/**
 * Created by Administrator on 2017\11\13 0013.
 */

public class Homebuttonphonemoneyinfo2 extends BaseActivity {
    private Context mContext = null;
    private Handler mHandler = null;
    private Button mhome_cardgroup_buttonphonemoneyinfo2submit;
    private final String mMode = "01";
    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";
    private boolean eduittextphonenumber = false;
    /**
     * 座机电话格式验证
     **/
    private final String PHONE_CALL_PATTERN = "^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(-\\d{1,4})?$";
    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     **/
    private final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";
    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709
     **/
    private final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";
    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
     * ,187,188,147,178,1705
     **/
    private final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
    private EditText mhome_cardgroup_buttonphonemoneyinfo2eduittext1;
    private EditText mhome_cardgroup_buttonphonemoneyinfo2eduittext2;
    private RecyclerView mhome_cardgroup_buttonphonemoneyinfo2recycleview;
    private RecyclerView home_cardgroup_buttonphonemoneyinfo2recycleview2;
    private Homephonemonetselectmoneyadapter homephonemonetselectmoneyadapter;
    private LinearLayout mhome_cardgroup_button_phonemoneyinfo2;
    private ProgressDialog mLoadingDialog;
    private String telephonebalance;
    private TextView mhome_cardgroup_buttonphonemoneyinfo2phoneplace;
    private String[] moneylist;
    private String mOrderId;
    private JiaoFeiOrderBean mJiaoFeiOrderBean;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_cardgroup_buttonphonemoneyinfo2);
        mContext = this;
       // mHandler = new Handler(this);
    }

    @Override
    public void initView() {

        LinearLayout home_cardgroup_buttonphonemoneyinfo2back = (LinearLayout) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2back);
        //整个页面
        mhome_cardgroup_button_phonemoneyinfo2 = (LinearLayout) findViewById(R.id.home_cardgroup_button_phonemoneyinfo2);
        //recycleview
        mhome_cardgroup_buttonphonemoneyinfo2recycleview = (RecyclerView) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2recycleview);
        home_cardgroup_buttonphonemoneyinfo2recycleview2 = (RecyclerView) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2recycleview2);
        mhome_cardgroup_buttonphonemoneyinfo2eduittext1 = (EditText) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2eduittext1);
        mhome_cardgroup_buttonphonemoneyinfo2eduittext2 = (EditText) findViewById(home_cardgroup_buttonphonemoneyinfo2eduittext2);
        //选择联系人按钮
        mhome_cardgroup_buttonphonemoneyinfo2phoneplace = (TextView) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2phoneplace);
        mhome_cardgroup_buttonphonemoneyinfo2submit = (Button) findViewById(R.id.home_cardgroup_buttonphonemoneyinfo2submit);
        LinearLayout mhome_cardgroup_buttonphonemonetinfo2chosepeople = (LinearLayout) findViewById(R.id.home_cardgroup_buttonphonemonetinfo2chosepeople);
        SpannableString s = new SpannableString("请输入您的手机号");
        SpannableString s2 = new SpannableString("请输入金额");
        AbsoluteSizeSpan textSize = new AbsoluteSizeSpan(16, true);
        s.setSpan(textSize, 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        s2.setSpan(textSize, 0, s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mhome_cardgroup_buttonphonemoneyinfo2eduittext1.setHint(s);
        mhome_cardgroup_buttonphonemoneyinfo2eduittext2.setHint(s2);
        mhome_cardgroup_buttonphonemoneyinfo2recycleview.setLayoutManager(new GridLayoutManager(Homebuttonphonemoneyinfo2.this, 3));
        home_cardgroup_buttonphonemoneyinfo2recycleview2.setLayoutManager(new GridLayoutManager(Homebuttonphonemoneyinfo2.this, 4));
        moneylist = new String[]{"10元", "20元", "30元", "50元", "100元", "200元",};
        String[] recycleview2texts = {"物业费", "水费", "电费", "燃气费", "暖气费", "有线电视",};
        int[] recycleview2imgs = {R.drawable.home_phonemoneybuttonimg1, R.drawable.home_phonemoneybuttonimg2,
                R.drawable.home_phonemoneybuttonimg3, R.drawable.home_phonemoneybuttonimg4,
                R.drawable.home_phonemoneybuttonimg5, R.drawable.home_phonemoneybuttonimg6};
        homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
        mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
        HomePhonemoneyselectbuttonAdapter homePhonemoneyselectbuttonAdapter = new HomePhonemoneyselectbuttonAdapter(Homebuttonphonemoneyinfo2.this, recycleview2imgs, recycleview2texts);
        home_cardgroup_buttonphonemoneyinfo2recycleview2.setAdapter(homePhonemoneyselectbuttonAdapter);
        //默认显示自己的号码
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String te1 = tm.getLine1Number();//获取本机号码
        if (!TextUtils.isEmpty(te1)) {
            String replace1 = te1.replace(" ", "");
            String replace2 = replace1.replace("+86", "");
            mhome_cardgroup_buttonphonemoneyinfo2eduittext1.setText(replace2);
            ShowVolleyRequestforcheckArea(replace2);
        }


        home_cardgroup_buttonphonemoneyinfo2back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    finish();
                }
            }
        });
        mhome_cardgroup_button_phonemoneyinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
        //选择联系人按钮
        mhome_cardgroup_buttonphonemonetinfo2chosepeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = ContactsContract.Contacts.CONTENT_URI;
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, 11);
            }
        });
        //输入框监听
        mhome_cardgroup_buttonphonemoneyinfo2eduittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
 /*               boolean chinaMobilePhoneNum = CheckPhonenumber.isChinaMobilePhoneNum(s.toString());
                boolean chinaTelecomPhoneNum = CheckPhonenumber.isChinaTelecomPhoneNum(s.toString());
                boolean chinaUnicomPhoneNum = CheckPhonenumber.isChinaUnicomPhoneNum(s.toString());
                boolean phoneCallNum = CheckPhonenumber.isPhoneCallNum(s.toString());
                if (chinaMobilePhoneNum == true) {
                    eduittextphonenumber = true;
                    homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                    mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                    mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText("中国移动");
                } else if (chinaTelecomPhoneNum == true) {
                    eduittextphonenumber = true;
                    homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                    mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                    mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText("中国电信");
                } else if (chinaUnicomPhoneNum == true) {
                    eduittextphonenumber = true;
                    homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                    mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                    mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText("中国联通");
                } else if (phoneCallNum == true) {
                    eduittextphonenumber = true;
                    homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                    mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                    mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText("座机");
                } else {
                    eduittextphonenumber = false;
                    homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                    mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                    mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText("请输入正确的号码");
                }*/
                ShowVolleyRequestforcheckArea(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                initData();
            }
        });

        mhome_cardgroup_buttonphonemoneyinfo2eduittext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mhome_cardgroup_buttonphonemoneyinfo2submit.setBackground(Homebuttonphonemoneyinfo2.this.getResources().getDrawable(R.drawable.shape_phonemoneybuttonselect1));
                } else {
                    mhome_cardgroup_buttonphonemoneyinfo2submit.setBackground(Homebuttonphonemoneyinfo2.this.getResources().getDrawable(R.drawable.shape_phonemoneybuttonunselect1));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initData() {
        mhome_cardgroup_buttonphonemoneyinfo2submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String geteduittextphone = mhome_cardgroup_buttonphonemoneyinfo2eduittext1.getText().toString();
                    String geteduittextphone2 = mhome_cardgroup_buttonphonemoneyinfo2eduittext2.getText().toString();
                    if (!TextUtils.isEmpty(geteduittextphone)) {
                        if (!TextUtils.isEmpty(geteduittextphone2)) {
                            final boolean isreguextphonenumber = Phonenumberreguest.isreguextphonenumber(geteduittextphone);
                            if (isreguextphonenumber == true) {
                                //进入下个页面
//                                Intent intent = new Intent(Homebuttonphonemoneyinfo2.this, Homebuttonphonemoneyinfodetails.class);
                                // startActivity(intent);
//                                String userId = "222222";
//                                String orderType = "1";
//                                String orderSubtype = "1";
//                                String orderMoneyTotal = "1000";
                                ShowVolleyRequestfororder2();
                            } else {
                                ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage("请输入正确的手机号");
                            }
                        } else {
                            ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage("输入金额不能为空");
                        }
                    } else {
                        ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage("输入手机号不能为空");
                    }
                }
            }
        });
    }

    private void ShowVolleyRequestforcheckArea(final String gettelephonenumber) {
        String url = ApiUrls.CHECKAREA;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("telephone", gettelephonenumber);
        JSONObject jsonObject = new JSONObject(params);
        //Volley请求网络进行判断
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        DxbosscheckArea dxbosscheckarea = gson.fromJson(response.toString(), DxbosscheckArea.class);
                        String code = dxbosscheckarea.getCode();
                        if (code.equals("R00001")) {
                            // successcontent();
                            DxbosscheckArea.ContentBean.DataBean data = dxbosscheckarea.getContent().getData();
                            String carrier = data.getCarrier();
                            if (!TextUtils.isEmpty(carrier)) {
                                mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText(carrier);
                            }
                            LogUtil.d("33333333333", "11111111");

                            if (carrier.equals("河南电信")) {
                     /*           LogUtil.d("2222222222","11111111");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LogUtil.d("11111111111","11111111");

                                    }
                                });*/
                                eduittextphonenumber = true;
                                homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                                mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                                homephonemonetselectmoneyadapter.setOnItemClickLitener(new Homephonemonetselectmoneyadapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, String selectmoney, int position) {
                                        LogUtil.d("777777777777", "999999999");
                                        if (eduittextphonenumber == true) {
                                            LogUtil.d("888888888888", "999999999");
                                            Pattern p = Pattern.compile("[0-9\\.]+");
                                            Matcher m = p.matcher(selectmoney);
                                            while (m.find()) {
                                                mhome_cardgroup_buttonphonemoneyinfo2eduittext2.setText(m.group());
                                                //TODO:
                                                //TODO:
                                                //TODO:
//                                                ShowVolleyRequestfororder2(m.group());
                                            }
                                            mhome_cardgroup_buttonphonemoneyinfo2submit.setBackground(Homebuttonphonemoneyinfo2.this.getResources().getDrawable(R.drawable.shape_phonemoneybuttonselect1));

                                        } else {
                                            LogUtil.d("99999999999", "999999999");
                                            mhome_cardgroup_buttonphonemoneyinfo2submit.setBackground(Homebuttonphonemoneyinfo2.this.getResources().getDrawable(R.drawable.shape_phonemoneybuttonunselect1));
                                        }

                                    }
                                });
                                ShowVolleyRequestforphonebalance(gettelephonenumber);
                            } else {
                                eduittextphonenumber = false;
                                homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                                mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                                ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage("暂时只提供河南电信用户查询缴费");
                            }

                        } else {
                            eduittextphonenumber = false;
                            homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                            mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
                            String desc = dxbosscheckarea.getDesc();
                            mhome_cardgroup_buttonphonemoneyinfo2phoneplace.setText(desc);
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                eduittextphonenumber = false;
                homephonemonetselectmoneyadapter = new Homephonemonetselectmoneyadapter(Homebuttonphonemoneyinfo2.this, moneylist, eduittextphonenumber);
                mhome_cardgroup_buttonphonemoneyinfo2recycleview.setAdapter(homephonemonetselectmoneyadapter);
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

    private void ShowVolleyRequestforphonebalance(String gettelephonenumber) {
        String url = ApiUrls.GETTELBALANCE;
        //Volley请求网络进行判断
        LogUtil.e("telephone=========",gettelephonenumber);
        Map<String, String> params = new HashMap<String, String>();
        params.put("telephone", gettelephonenumber);
        JSONObject jsonObject = new JSONObject(params);
        //Volley请求网络进行判断
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LogUtil.e("response===========",response.toString());
                        Gson gson = new Gson();
                        DxbossgetTelbalance dxbosscheckarea = gson.fromJson(response.toString(), DxbossgetTelbalance.class);
                        String code = dxbosscheckarea.getCode();
                        if (code.equals("R00001")) {
                            // successcontent();
                            telephonebalance = dxbosscheckarea.getContent().getData().getContent().getBalance();
                            LogUtil.e("telephonebalance=============="+telephonebalance);
                        } else {
                           ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage(response.optString("desc"));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // failcontent();
                LogUtil.e("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(20* 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void showpopwindow(final String orderId, String geteduittextphone2) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        // 一个自定义的布局，作为显示的内容
        int screenHeight = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.6); // 屏幕高（像素，如：800p）
        View contentView1 = LayoutInflater.from(Homebuttonphonemoneyinfo2.this).inflate(
                R.layout.home_phone_money_buttom_popwindow, null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1,
                LayoutParams.MATCH_PARENT, screenHeight, true);
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
        popupWindow1.showAtLocation(mhome_cardgroup_button_phonemoneyinfo2, Gravity.BOTTOM, 10, 15);
        LinearLayout mhome_phone_money_buttom_popwindowimg1 = (LinearLayout) contentView1.findViewById(R.id.home_phone_money_buttom_popwindowimg1);
        Button mhome_phone_money_buttom_popwindowsubmitbutton = (Button) contentView1.findViewById(R.id.home_phone_money_buttom_popwindowsubmitbutton);
        TextView mhome_phone_money_buttom_popwindowpayformoney = (TextView) contentView1.findViewById(R.id.home_phone_money_buttom_popwindowpayformoney);
        Double jine = Double.valueOf(mhome_cardgroup_buttonphonemoneyinfo2eduittext2.getText().toString().trim());
        String s = fnum.format(jine);
        mhome_phone_money_buttom_popwindowpayformoney.setText("￥ " + s + "元");
        TextView mhome_phonemoney_popwindowtext2 = (TextView) contentView1.findViewById(R.id.home_phonemoney_popwindowtext2);
        TextView mhome_phone_money_buttom_popwindowpayfororderinfo = (TextView) contentView1.findViewById(R.id.home_phone_money_buttom_popwindowpayfororderinfo);
        LogUtil.e("geteduittextphone2======"+geteduittextphone2);
        Double aDouble = Double.valueOf(geteduittextphone2);
        String dd = fnum.format(aDouble);
//        mhome_phone_money_buttom_popwindowpayformoney.setText("￥ " + dd + "元");
        if (!TextUtils.isEmpty(telephonebalance)) {
            mhome_phonemoney_popwindowtext2.setText(dd+ "元");
        } else {
            mhome_phonemoney_popwindowtext2.setText("系统繁忙");
        }
        if (!TextUtils.isEmpty(orderId)) {
            mhome_phone_money_buttom_popwindowpayfororderinfo.setText(orderId);
        } else {
            mhome_phone_money_buttom_popwindowpayfororderinfo.setText("系统繁忙");
        }

        mhome_phone_money_buttom_popwindowsubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // context
// title
// message
// 进度是否是不确定的，这只和创建进度条有关
    /*            mLoadingDialog = ProgressDialog.show(v.getContext(), // context
                        "", // title
                        "正在努力的获取tn中,请稍候...", // message
                        true);
                if(hasWindowFocus()) {

                    if (mLoadingDialog == null) {

                        mLoadingDialog = ProgressDialog.show(Homebuttonphonemoneyinfo2.this, // context
                                "", // title
                                "正在努力的获取tn中,请稍候...", // message
                                true);

                    }

                    mLoadingDialog.show();
                }*/


                /*************************************************
                 * 步骤1：从网络开始,获取交易流水号即TN
                 ************************************************/
                if (!TextUtils.isEmpty(orderId)){
                    ShowVolleyRequestfororder3(orderId);
                }else{
                    ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage("系统繁忙");
                }
               // new Thread(Homebuttonphonemoneyinfo2.this).start();
                popupWindow1.dismiss();
            }
        });
        mhome_phone_money_buttom_popwindowimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/

        if (requestCode == 11) {
            if (data == null) {
                return;
            }
            //处理返回的data,获取选择的联系人信息
            Uri uri = data.getData();
            String[] contacts = getPhoneContacts(uri);
            String phoneNum = contacts[1];
            //t添加联系人姓名和电话号码到页面
            String s = "";
            //tv_main_name.setText(contacts[0]);
            if(contacts[1].startsWith("+86")){
                phoneNum = contacts[1].substring(3,phoneNum.length()).replace(" ","");
            }else {
                phoneNum = contacts[1].replace(" ","");
            }
            mhome_cardgroup_buttonphonemoneyinfo2eduittext1.setText(phoneNum);

        } else {
            if (data == null) {
                return;
            }
            String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
            String str = data.getExtras().getString("pay_result");
            if (str.equalsIgnoreCase("success")) {

                // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
                // result_data结构见c）result_data参数说明
                if (data.hasExtra("result_data")) {
                    String result = data.getExtras().getString("result_data");
                    try {
                        JSONObject resultJson = new JSONObject(result);
                        String sign = resultJson.getString("sign");
                        String dataOrg = resultJson.getString("data");
                        // 此处的verify建议送去商户后台做验签
                        // 如要放在手机端验，则代码必须支持更新证书
                        boolean ret = verify(dataOrg, sign, mMode);
                        if (ret) {
                            // 验签成功，显示支付结果
                            msg = "支付成功！";
                        } else {
                            // 验签失败
                            msg = "支付失败！";
                        }
                    } catch (JSONException e) {
                    }
                }
                // 结果result_data为成功时，去商户后台查询一下再展示成功
                msg = "支付成功！";
            } else if (str.equalsIgnoreCase("fail")) {
                msg = "支付失败！";
            } else if (str.equalsIgnoreCase("cancel")) {
                msg = "用户取消了支付";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("支付结果通知");
            builder.setMessage(msg);
            builder.setInverseBackgroundForced(true);
            // builder.setCustomTitle();
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }

    }

    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

  /*  @Override
    public boolean handleMessage(Message msg) {
        Log.e("dddddddd", " " + "" + msg.obj);
     *//*   if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }*//*

        String tn = "";
        if (msg.obj == null || ((String) msg.obj).length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("错误提示");
            builder.setMessage("网络连接失败,请重试!");
            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            tn = (String) msg.obj;
            *//*************************************************
             * 步骤2：通过银联工具类启动支付插件
             ************************************************//*
            // doStartUnionPayPlugin(this, tn, mMode);

        }

        return false;
    }

    @Override
    public void run() {
        String tn = null;
        InputStream is;
        try {
            String url = TN_URL_01;
            URL myURL = new URL(url);
            URLConnection ucon = myURL.openConnection();
            ucon.setConnectTimeout(120000);
            is = ucon.getInputStream();
            int i = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            tn = baos.toString();
            is.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = mHandler.obtainMessage();
        msg.obj = tn;
        mHandler.sendMessage(msg);
    }*/

    int startpay(Activity act, String tn, int serverIdentifier) {
        return 0;
    }

    private boolean verify(String msg, String sign64, String mode) {
        // 此处的verify，商户需送去商户后台做验签
        return true;
    }

    //请求订单接口文档
    private void ShowVolleyRequestfororder2() {
        String urll = ApiUrls.JIAO_FEI;
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", "374");
        params.put("orderType", "1");
        params.put("orderSubtype", "1");
        params.put("orderMoneyTotal", "1");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urll, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code = response.optString("code").toString();
                        String desc = response.optString("desc").toString();
                        Log.e("response=======",response+"");
                        if ("R00000".equals(code)){
                            JiaoFeiOrderBean mJiaoFeiOrderBean = new Gson().fromJson(response.toString(), JiaoFeiOrderBean.class);
                            mOrderId = mJiaoFeiOrderBean.getContent().getOrderId();
                            showpopwindow(mOrderId,telephonebalance);
                        }else {
                            ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    //消费交易接口文档
    private void ShowVolleyRequestfororder3(String geteduittextphone2) {
        String urll = ApiUrls.APPCONSUMERGETTN;
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderId", geteduittextphone2);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urll, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String code = response.optString("code").toString();
                        String desc = response.optString("desc").toString();
                        Log.e("response=======",response+"");
                        if ("R00000".equals(code)){
                            PayBean mPayBean = new Gson().fromJson(response.toString(), PayBean.class);
                            String serverMode = "00";
                            String tn = mPayBean.getContent().getTn();
                            LogUtil.e("tn==========",tn);
                            UPPayAssistEx.startPay (Homebuttonphonemoneyinfo2.this, null, null, tn, serverMode);
                        }else {
                            ToastUtils.getInstance(Homebuttonphonemoneyinfo2.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

}
