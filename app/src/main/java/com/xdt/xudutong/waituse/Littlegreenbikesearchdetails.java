package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.personcenterfragment.Personitemfour;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\1\3 0003.
 */
//小绿扫描结果页面，暂由惠民宝按钮进入测试
public class Littlegreenbikesearchdetails extends BaseActivity {

    private String mDeposit;
    private TextView mTvYajin;

    @Override
    public void initView() {
        final Intent intent = getIntent();
        final String mshedid = intent.getStringExtra("shedid");
        final String mlockid = intent.getStringExtra("lockid");
        final String littlegreencardfaceno = intent.getStringExtra("littlegreencardfaceno");
        final int userid = intent.getIntExtra("userid",0);

        final int cardStatus = intent.getIntExtra("cardStatus",0);
        final String littlebikenumber = intent.getStringExtra("littlebikenumber");
        final String littlebikeprice = intent.getStringExtra("littlebikeprice");
        final String littlebikefreetime = intent.getStringExtra("littlebikefreetime");
        final String littlebikemaxcost = intent.getStringExtra("littlebikemaxcost");
        //新增押金
        mDeposit = intent.getStringExtra("deposit");

        LinearLayout mlittlegreembikesearchdetailsback = (LinearLayout) findViewById(R.id.littlegreembikesearchdetailsback);

        TextView mlittlegreembikesearchdetailstext2 = (TextView) findViewById(R.id.littlegreembikesearchdetailstext2);
        TextView mlittlegreembikesearchdetailstext3 = (TextView) findViewById(R.id.littlegreembikesearchdetailstext3);
        TextView mlittlegreembikesearchdetailstext4 = (TextView) findViewById(R.id.littlegreembikesearchdetailstext4);
        TextView mlittlegreembikesearchdetailstext5 = (TextView) findViewById(R.id.littlegreembikesearchdetailstext5);
        TextView mlittlegreembikesearchdetailstext6 = (TextView) findViewById(R.id.littlegreembikesearchdetailstext6);
        TextView mlittlegreembikesearchdetailssubmit = (TextView) findViewById(R.id.littlegreembikesearchdetailssubmit);
        mTvYajin = (TextView) findViewById(R.id.tv_ya_jin);
        if (!TextUtils.isEmpty(mshedid)) {
            mlittlegreembikesearchdetailstext2.setText(mlockid);
        }
        if (!TextUtils.isEmpty(littlebikenumber)) {
            mlittlegreembikesearchdetailstext3.setText(littlebikenumber);
        } if (!TextUtils.isEmpty(littlebikeprice)) {
            mlittlegreembikesearchdetailstext4.setText(littlebikeprice+"元/60分钟");
        } if (!TextUtils.isEmpty(littlebikefreetime)) {
            mlittlegreembikesearchdetailstext5.setText("前"+littlebikefreetime+"分钟");
        } if (!TextUtils.isEmpty(littlebikemaxcost)) {
            mlittlegreembikesearchdetailstext6.setText(littlebikemaxcost+"元");
        }
        if (!TextUtils.isEmpty(mDeposit)) {
            mTvYajin.setText("押金余额"+mDeposit+"元");
        }
        mlittlegreembikesearchdetailsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mlittlegreembikesearchdetailssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid!=0){
                    if (!TextUtils.isEmpty(littlegreencardfaceno)){
                        switch (cardStatus) {
                            case 0:
                                Intent intent = new Intent(Littlegreenbikesearchdetails.this, Personitemfour.class);
                                intent.putExtra("xdtguashi", "卡管理");
                                startActivity(intent);
                                ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("请先绑定您的许都通卡");
                                break;
                            case 1:
                                showlittlegreenbikopenlock(mshedid, mlockid, littlegreencardfaceno,userid);
                                break;
                            case 3:
                                ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("该账户处于解绑状态");
                                break;
                            case 11:
                                ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("该账户处于解绑状态");
                                break;
                            default:
                                LogUtil.d("挂失状态。。。。", "default");
                                break;
                        }

                    }else{
                        ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("你不绑卡，你用啥小绿");
                    }

                }else{
                    ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("你不登录，你用啥小绿");
                }

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreenbikesearchdetails);
    }

    private void showlittlegreenbikopenlock(String shedid, String lockid, String littlegreencardfaceno, int userid) {
        //扫描车桩二维码获取车辆信息
        String url = ApiUrls.APPSCANCODEOPENLOCK;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("shedid", shedid);
        params.put("lockid", lockid);
        params.put("cardfaceno", littlegreencardfaceno);
        params.put("userid", userid+"");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals("R00001")) {
//                                Intent intent = new Intent(Littlegreenbikesearchdetails.this, LittlegreenbikeComein.class);
//                                startActivity(intent);
                                finish();
                            } else {
                                String desc = response.getString("desc");
                                ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage(desc);
                            }
                        } catch (JSONException e) {
                            ToastUtils.getInstance(Littlegreenbikesearchdetails.this).showMessage("系统异常");
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
}
