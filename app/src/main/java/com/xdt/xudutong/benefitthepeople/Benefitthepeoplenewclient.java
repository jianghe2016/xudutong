package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\3 0003.
 */
//新客户页面
public class Benefitthepeoplenewclient extends BaseActivity {
    @Override
    public void initView() {
        Intent intent = getIntent();
        final int cardStatus = intent.getIntExtra("cardStatus", 0);
        LinearLayout mbenefitthepeoplenewclientback = (LinearLayout) findViewById(R.id.benefitthepeoplenewclientback);
        Button mbenefitthepeoplenewclientsubmit = (Button) findViewById(R.id.benefitthepeoplenewclientsubmit);
        mbenefitthepeoplenewclientback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplenewclientsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplenewclient.this, Benefitthepeopleaboutopenanaccount.class);
                intent.putExtra("cardStatus",cardStatus);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplenewclientlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
