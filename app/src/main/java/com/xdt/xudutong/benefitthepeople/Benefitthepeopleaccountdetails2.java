package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\2 0002.
 */

public class Benefitthepeopleaccountdetails2 extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenefitthepeopleaccountdetailslayoutback = (LinearLayout) findViewById(R.id.benefitthepeopleaccountdetailslayoutback);

        mbenefitthepeopleaccountdetailslayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleaccountdetailslayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
