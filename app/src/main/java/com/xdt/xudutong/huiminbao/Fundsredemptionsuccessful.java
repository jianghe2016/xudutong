package com.xdt.xudutong.huiminbao;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class Fundsredemptionsuccessful extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mfundsredemptionsuccessfullayoutback = (LinearLayout) findViewById(R.id.fundsredemptionsuccessfullayoutback);
        TextView mfundsredemptionsuccessfullayoutsubmit = (TextView) findViewById(R.id.fundsredemptionsuccessfullayoutsubmit);
        mfundsredemptionsuccessfullayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfundsredemptionsuccessfullayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.fundsredemptionsuccessfullayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
