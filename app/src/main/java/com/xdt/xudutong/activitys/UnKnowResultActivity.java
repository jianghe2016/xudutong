package com.xdt.xudutong.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnKnowResultActivity extends AppCompatActivity {

    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_result)
    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_know_result);
        ButterKnife.bind(this);
        mTvTitle.setText("扫描结果");
        mTvResult.setText(getIntent().getStringExtra("result"));
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
