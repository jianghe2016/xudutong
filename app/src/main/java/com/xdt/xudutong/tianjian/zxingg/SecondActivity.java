package com.xdt.xudutong.tianjian.zxingg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.decoding.InactivityTimer;
import com.xdt.xudutong.R;
import com.xdt.xudutong.activitys.PopupWindowActivity;
import com.xdt.xudutong.activitys.UnKnowResultActivity;
import com.xdt.xudutong.bean.WsbikeappScanCodescanCode;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.BrowserActivity;
import com.xdt.xudutong.homefragment.BrowserActivityy;
import com.xdt.xudutong.tianjian.zxing.ZxingEvent;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ImageUtil;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.Littlegreenbikesearchdetails;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 定制化显示扫描界面
 */
public class SecondActivity extends BaseActivity {

    private CaptureFragment captureFragment;
    private int saomaxdt;
    private TextView mhome_saoma_myzxinglight;
    private FrameLayout mfl_my_container;
    private String mCardNum;
    private InactivityTimer mInactivityTimer;
    private int mCardStatus;
    private boolean mLoadingstate;
    private String mGreenStatus;
    private int mReal;
    private String mFlag;

    @Override
    public void initView() {
        mFlag = getIntent().getExtras().getString("flag");
        Intent intent = getIntent();
        mCardNum = intent.getStringExtra("littlegreencardfaceno");
        saomaxdt = intent.getIntExtra("saomaxdt", 0);
        if ("0".equals(mFlag)){
            saomaxdt = getIntent().getExtras().getInt("saomaxdt");
            mCardStatus = getIntent().getExtras().getInt("cardStatus");
            mLoadingstate = getIntent().getExtras().getBoolean("loadingstate");
            mGreenStatus = getIntent().getExtras().getString("greenStatus");
            mReal = getIntent().getExtras().getInt("real");
        }else {

        }
        LinearLayout mhome_saoma_myzxingback = (LinearLayout) findViewById(R.id.home_saoma_myzxingback);
        LinearLayout msaomalinear1 = (LinearLayout) findViewById(R.id.saomalinear1);
        LinearLayout msaomalinear2 = (LinearLayout) findViewById(R.id.saomalinear2);
        mfl_my_container = (FrameLayout) findViewById(R.id.fl_my_container);
        mhome_saoma_myzxinglight = (TextView) findViewById(R.id.home_saoma_myzxinglight);
        mhome_saoma_myzxingback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        msaomalinear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image*//*");
                startActivityForResult(intent, 123);
*/

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 123);
            }
        });
        msaomalinear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!isOpen) {
                        CodeUtils.isLightEnable(true);
                        isOpen = true;
                        mhome_saoma_myzxinglight.setText("关灯");
                    } else {
                        CodeUtils.isLightEnable(false);
                        isOpen = false;
                        mhome_saoma_myzxinglight.setText("开灯");
                    }
                } catch (Exception e) {
                    ToastUtils.getInstance(SecondActivity.this).showMessage("请检查闪光灯是否存在");
                }
            }
        });
    }

    public static boolean isOpen = false;


    @Override
    public void setMyContentView() {
        setContentView(R.layout.activity_second);
        mInactivityTimer = new InactivityTimer(this);
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }


    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            if (saomaxdt == 1) {
                ZxingEvent event = new ZxingEvent();
                event.result = result;
                EventBus.getDefault().post(event);
                finish();
            } else if (saomaxdt == 2) {
                if (result != null && result.length() == 32){
                    if (!"0".equals(mFlag)){
                        showlittlegreenbikeresult(result);
                    }else {
                        if (mLoadingstate == true){
                            if (mReal == 1){
                                if (mCardStatus == 1){
                                    if ("1".equals(mGreenStatus)){
                                        showlittlegreenbikeresult(result);
                                    }else {
                                        Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                                        mIntent.putExtra("flag","9");
                                        startActivity(mIntent);
                                    }
                                }else {
                                    Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                                    mIntent.putExtra("flag","8");
                                    startActivity(mIntent);
                                }
                            }else {
                                Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                                mIntent.putExtra("flag","7");
                                startActivity(mIntent);
                            }
                        }else {
                            Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                            mIntent.putExtra("flag","6");
                            startActivity(mIntent);
                        }
                    }
                }else {
                    Intent mIntent = new Intent().setClass(SecondActivity.this,UnKnowResultActivity.class);
                    mIntent.putExtra("result",result);
                    startActivity(mIntent);
                }
            } else {
                Intent resultIntent = new Intent(SecondActivity.this, BrowserActivityy.class);
                resultIntent.putExtra("urll", result);
                Log.d("urllurllurll", result);
                SecondActivity.this.startActivity(resultIntent);
                finish();
            }

        }

        @Override
        public void onAnalyzeFailed() {
            if (saomaxdt == 1) {
                ZxingEvent event = new ZxingEvent();
                event.result = "非法链接";
                EventBus.getDefault().post(event);
                finish();
            } else {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("urll", "http://www.xudutong.com/navi/ykthome");
                SecondActivity.this.startActivity(new Intent(SecondActivity.this, BrowserActivity.class));
                finish();
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Intent resultIntent = new Intent(SecondActivity.this, BrowserActivityy.class);
                            resultIntent.putExtra("urll", result);
                            Log.d("urllurllurll", result);
                            SecondActivity.this.startActivity(resultIntent);
                            finish();

                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(SecondActivity.this, "非法链接", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void showlittlegreenbikeresult(String littleorderString) {
//        LogUtil.e("mCardNum=======",mCardNum);
        //扫描车桩二维码获取车辆信息
        String url = ApiUrls.WSBIKEAPPSCANCODESCANCODE;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderno", littleorderString);
        params.put("cardfaceno", SpUtils.getParam(SecondActivity.this,"cardNo",""));
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        final Gson gson = new Gson();
                        WsbikeappScanCodescanCode wsbikemakecardrecord1 = gson.fromJson(response.toString(), WsbikeappScanCodescanCode.class);
                        String code = wsbikemakecardrecord1.getCode();
                        if (code.equals("R00001")) {
                            WsbikeappScanCodescanCode.ContentEntity.DataEntity data = wsbikemakecardrecord1.getContent().getData();
                            String shedid = data.getShedid();
                            String lockid = data.getLockid();
                            Intent intent1 = getIntent();
                            String littlegreencardfaceno = intent1.getStringExtra("littlegreencardfaceno");
                            int cardStatus = intent1.getIntExtra("cardStatus", 0);
                            int userid = intent1.getIntExtra("userid", 0);
                            Intent intent = new Intent(SecondActivity.this, Littlegreenbikesearchdetails.class);
                            if (!TextUtils.isEmpty(shedid)) {
                                intent.putExtra("shedid", shedid);
                            }
                            if (!TextUtils.isEmpty(shedid)) {
                                intent.putExtra("lockid", lockid);
                            }
                            if (!TextUtils.isEmpty(littlegreencardfaceno)) {
                                intent.putExtra("littlegreencardfaceno", littlegreencardfaceno);
                            }
                            intent.putExtra("cardStatus", cardStatus);
                            intent.putExtra("userid", userid);
                            intent.putExtra("littlebikenumber", data.getBikecardsn());
                            intent.putExtra("littlebikeprice", data.getPrice());
                            intent.putExtra("littlebikefreetime", data.getFreetime());
                            intent.putExtra("littlebikemaxcost", data.getPeakvalue());
                            intent.putExtra("deposit",data.getDeposit());
                            startActivity(intent);
                            finish();
                        } else if (code.equals("R00002")) {
                            //车桩故障
                            Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                            mIntent.putExtra("flag","2");
                            startActivity(mIntent);
//                            Showdialogforsearchresultbad();
                        } else if (code.equals("R00003")) {
                            Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                            mIntent.putExtra("flag","3");
                            startActivity(mIntent);
                            //该车桩无车
//                            Showdialogforsearchresultnobike();
                        } else if (code.equals("R00004")) {
                            //该站已停用
                            Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                            mIntent.putExtra("flag","4");
                            startActivity(mIntent);
//                            Showdialogforsearchresultbikestop();
                        } else if (code.equals("R00005")) {
                            //非正常营业时间
                            Intent mIntent = new Intent().setClass(SecondActivity.this,PopupWindowActivity.class);
                            mIntent.putExtra("flag","5");
                            startActivity(mIntent);
//                            Showdialogforsearchresultnostartwork();
                        } else if (code.equals("E00000")) {
                            //参数不能为空

                        } else if (code.equals("E00001")) {
                            //获取车桩信息失败

                        } else if (code.equals("E00002")) {
                            //请求错误

                        } else {
                            String desc = wsbikemakecardrecord1.getDesc();
                            ToastUtils.getInstance(SecondActivity.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    //车桩故障
    private void Showdialogforsearchresultbad() {
        View inflate2 = LayoutInflater.from(SecondActivity.this).inflate(R.layout.littlegreenbikesearchresultbad, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbadimg1);
        LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbaddissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow

        popupWindow2.showAtLocation(mfl_my_container, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbaddissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
    }

      private void Showdialogforsearchresultnobike() {
        View inflate2 = LayoutInflater.from(SecondActivity.this).inflate(R.layout.littlegreenbikesearchresultnobike, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbadimg1);
          ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultnobike1);
//        LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbaddissmiss);
          LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultnobikedissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
                LogUtil.e("dismiss============","R0000000000");
            }
        });
        mlittlegreenbikesearchresultbaddissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mfl_my_container, Gravity.CENTER, 0, 0);
    }

    //站点停用
    private void Showdialogforsearchresultbikestop() {
        View inflate2 = LayoutInflater.from(SecondActivity.this).inflate(R.layout.littlegreenbikesearchresultbikestop, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbikestopimg1);
        LinearLayout mlittlegreenbikesearchresultbikestopdissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbikestopdissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mfl_my_container, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbikestopdissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
    }

    //没有到营业时间
    private void Showdialogforsearchresultnostartwork() {
        View inflate2 = LayoutInflater.from(SecondActivity.this).inflate(R.layout.littlegreenbikesearchresultnostartwork, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbikenostartworkimg1);
        LinearLayout mlittlegreenbikesearchresultbikenostartworkdismiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbikenostartworkdismiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });



        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mfl_my_container, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbikenostartworkdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
    }
}
