package com.xdt.xudutong.waituse;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProblemsActivity extends Base2Activity {

    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.ll_biaozhun)
    LinearLayout mLlBiaozhun;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.ll_banfa)
    LinearLayout mLlBanfa;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.ll_peishang)
    LinearLayout mLlPeishang;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_tishi)
    TextView mTvTishi;
    @BindView(R.id.ll_tishi)
    LinearLayout mLlTishi;
    private int a = 1,b=1,c=1,d = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        ButterKnife.bind(this);
        mTvTitle.setText("常见问题");

    }

    @OnClick({R.id.back, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_1:
                if (a == 1){
                    mLlBiaozhun.setVisibility(View.VISIBLE);
                    mTv1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_zhankai),null, null, null);
                    a = 2;
                }else {
                    mLlBiaozhun.setVisibility(View.GONE);
                    mTv1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_shouqi),null, null, null);
                    a = 1;
                }
                break;
            case R.id.tv_2:
                if (b == 1){
                    mLlBanfa.setVisibility(View.VISIBLE);
                    mTv2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_zhankai),null, null, null);
                    b = 2;
                }else {
                    mLlBanfa.setVisibility(View.GONE);
                    mTv2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_shouqi),null, null, null);
                    b = 1;
                }
                break;
            case R.id.tv_3:
                if (c == 1){
                    mLlPeishang.setVisibility(View.VISIBLE);
                    mTv3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_zhankai),null, null, null);
                    c = 2;
                }else {
                    mLlPeishang.setVisibility(View.GONE);
                    mTv3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_shouqi),null, null, null);
                    c = 1;
                }
                break;
            case R.id.tv_4:
                if (d == 1){
                    mLlTishi.setVisibility(View.VISIBLE);
                    mTv4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_zhankai),null, null, null);
                    d = 2;
                }else {
                    mLlTishi.setVisibility(View.GONE);
                    mTv4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.bike_problem_shouqi),null, null, null);
                    d = 1;
                }
                break;
        }
    }
}
