package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\3 0003.
 */

public class BenefitthepeopleWithdrawresult extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenefitthepeoplewithdrawlayoutback = (LinearLayout) findViewById(R.id.benefitthepeoplewithdrawlayoutback);
        TextView mbenefitthepeoplewithdrawlayoutsubmit = (TextView) findViewById(R.id.benefitthepeoplewithdrawlayoutsubmit);
        mbenefitthepeoplewithdrawlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplewithdrawlayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplewithdrawresultlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
