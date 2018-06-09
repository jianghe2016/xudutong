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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingPsdActivity extends Base2Activity {

    @BindView(R.id.rel_deal_psd)
    RelativeLayout mRelDealPsd;
    @BindView(R.id.rel_login_psd)
    RelativeLayout mRelLoginPsd;
    private Intent mIntent;
    private LinearLayout mBack;
    private TextView mTvTitle;
    private String mMobile;
    private String mPersonusername;
    private String mPayPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_psd);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPayPsd = SpUtils.getParam(this,"paypsd","");
    }

    public void initView() {
        mMobile = getIntent().getExtras().getString("mobile");
        mPersonusername = getIntent().getExtras().getString("itemtwoxiugaiwordusername");
        mBack = (LinearLayout) findViewById(R.id.back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("设置新密码");
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick({R.id.rel_deal_psd, R.id.rel_login_psd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_deal_psd:
                if ("1".equals(mPayPsd)){
                    mIntent = new Intent().setClass(this,AlterDealPsdActivity.class);
                    mIntent.putExtra("mobile",mMobile);
                }else {
                    mIntent = new Intent().setClass(this,SettingNewPsdActivity.class);
                    mIntent.putExtra("mark","0");
                }
                startActivity(mIntent);
                break;
            case R.id.rel_login_psd:
                mIntent = new Intent().setClass(this,AlterLoginPasswordActivity.class);
                mIntent.putExtra("itemtwoxiugaiwordusername", mPersonusername);
                mIntent.putExtra("mark","login");
                startActivity(mIntent);
                break;
        }
    }
}
