package com.xdt.xudutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.view.LogUtil;

public class AlterDealPsdActivity extends Base2Activity implements View.OnClickListener{

    private TextView mTvPhone;
    private LinearLayout mBack;
    private TextView mTvTitle;
    private RelativeLayout mPhone;
    private RelativeLayout mPsd;
    private Intent mIntent;
    private String mMobile;
    private String mPayPsd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_deal_psd);
        initView();
    }

    public void initView() {
        mPayPsd = SpUtils.getParam(this,"paypsd","");
        mMobile = getIntent().getExtras().getString("mobile");
        LogUtil.e("手机号===========",mMobile);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvPhone.setText(mMobile.substring(0,3)+"****" + mMobile.substring(8,mMobile.length()));
        mBack = (LinearLayout) findViewById(R.id.back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mPhone = (RelativeLayout) findViewById(R.id.phone);
        mPsd = (RelativeLayout) findViewById(R.id.psd);
        mTvTitle.setText("修改交易密码");
        mPhone.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mPsd.setOnClickListener(this);
    }

//    @Override
//    public void setMyContentView() {
//        setContentView(R.layout.activity_alter_deal_psd);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.phone:
                mIntent = new Intent().setClass(this,GetCodeActivity.class);
                mIntent.putExtra("mobile",mMobile);
                startActivity(mIntent);
                break;
            case R.id.psd:
                if ("1".equals(mPayPsd)){
                    mIntent= new Intent().setClass(this,DealPsdActivity.class);
                }else {
                    mIntent = new Intent().setClass(this,SettingNewPsdActivity.class);
                    mIntent.putExtra("mark","2");
                }
                startActivity(mIntent);
                break;

        }
    }
}
