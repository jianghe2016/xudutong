package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Littlegreenorderpay;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\1\3 0003.
 */
//订单支付页面
public class Littlegreeenbikeorderpay extends BaseActivity {

    private TextView mlittlegreeenbikeorderpaymoney;
    private TextView mlittlegreeenbikeorderbikenumber;
    private TextView mlittlegreeenbikeordercosttime;
    private TextView mlittlegreeenbikeordergetbiketime;
    private TextView mlittlegreeenbikeordergetbikeplace;
    private Littlegreenorderpay mContentBean = null;
    private LinearLayout mLittlegreeenbikeorderpayback;


    @Override
    public void initView() {
        mLittlegreeenbikeorderpayback = (LinearLayout) findViewById(R.id.littlegreeenbikeorderpayback);
        mlittlegreeenbikeorderpaymoney = (TextView) findViewById(R.id.littlegreeenbikeorderpaymoney);
        mlittlegreeenbikeorderbikenumber = (TextView) findViewById(R.id.littlegreeenbikeorderbikenumber);
        mlittlegreeenbikeordercosttime = (TextView) findViewById(R.id.littlegreeenbikeordercosttime);
        mlittlegreeenbikeordergetbiketime = (TextView) findViewById(R.id.littlegreeenbikeordergetbiketime);
        mlittlegreeenbikeordergetbikeplace = (TextView) findViewById(R.id.littlegreeenbikeordergetbikeplace);
        TextView mlittlegreeenbikeordersubmit = (TextView) findViewById(R.id.littlegreeenbikeordersubmit);
        final Intent intent = getIntent();
        mContentBean = (Littlegreenorderpay) getIntent().getExtras().getSerializable("data");
        //consume 消耗时间（秒）     //expense	金额（元）
        String mconsume = intent.getStringExtra("consume");
        String mexpense = intent.getStringExtra("expense");
        String mreturnTime = intent.getStringExtra("returnTime");
        mlittlegreeenbikeordersubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(Littlegreeenbikeorderpay.this,ReturnBikeActivity.class);
                //TODO:不知道用意，暂时注释
//                intent.putExtra("littlegreenpayforturnbike",1);
                intent.putExtra("data",mContentBean);
                startActivity(intent);
            }
        });

        mLittlegreeenbikeorderpayback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreeenbikeorderpay);
    }


}
