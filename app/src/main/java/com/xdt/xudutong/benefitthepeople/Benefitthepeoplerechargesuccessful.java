package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\2 0002.
 */
//充值成功
public class Benefitthepeoplerechargesuccessful extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenefitthepeoplerechargesuccessfullayoutback = (LinearLayout) findViewById(R.id.benefitthepeoplerechargesuccessfullayoutback);
        TextView mbenefitthepeoplerechargesuccessfullayoutmakesure = (TextView) findViewById(R.id.benefitthepeoplerechargesuccessfullayoutmakesure);
        mbenefitthepeoplerechargesuccessfullayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplerechargesuccessfullayoutmakesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplerechargesuccessfullayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
