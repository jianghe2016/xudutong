package com.xdt.xudutong.waituse;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.Littlegreenorderpay;

public class ReturnBikeActivity extends Base2Activity{

    TextView mTvConsumption;
    TextView mTvText;
    TextView mBikeNumber;
    TextView mTvRunTime;
    TextView mTv1;
    TextView mTvBorrowedTime;
    TextView mTvBorrowedPlace;
    TextView mTv2;
    TextView mTvReturnTime;
    TextView mTvReturnPlace;
    TextView mLittlegreeenbikeordersubmit;
    private LinearLayout mBack;
    private TextView mTvTitle;
    private Littlegreenorderpay mContentBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_bike);
        initView();
    }

    private void initView() {
//        expense	金额（元）
//        returnTime	还车时间
//        consume	消耗时间（分）
//        rentTime	租车时间
//        deposit	用户剩余押金
//        returnShed	还车地点
//        leaseShed	租车地点
//        bikesn	车辆卡号
        mContentBean = (Littlegreenorderpay) getIntent().getExtras().getSerializable("data");
        mBack = (LinearLayout) findViewById(R.id.back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvConsumption = (TextView) findViewById(R.id.tv_consumption);
        mBikeNumber = (TextView) findViewById(R.id.bike_number);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mTvRunTime = (TextView) findViewById(R.id.tv_run_time);
        mTvReturnTime = (TextView) findViewById(R.id.tv_return_time);
        mTvReturnPlace = (TextView) findViewById(R.id.tv_return_place);
        mTvBorrowedTime = (TextView) findViewById(R.id.tv_borrowed_time);
        mTvBorrowedPlace = (TextView) findViewById(R.id.tv_borrowed_place);
        mTvTitle.setText("还车成功");
        mTvConsumption.setText(mContentBean.getContent().getExpense()+"");
        mBikeNumber.setText("车号：NO."+mContentBean.getContent().getBikesn());
        mTvText.setText("扣除押金 " +mContentBean.getContent().getExpense()+" 元后，押金余额"+ mContentBean.getContent().getDeposit()+" 元");
        mTvRunTime.setText("骑行时间: "+mContentBean.getContent().getConsume()+"");
        mTvBorrowedTime.setText(mContentBean.getContent().getRentTime());
        mTvBorrowedPlace.setText(mContentBean.getContent().getLeaseShed());
        mTvReturnTime.setText(mContentBean.getContent().getReturnTime());
        mTvReturnPlace.setText(mContentBean.getContent().getReturnShed());
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
