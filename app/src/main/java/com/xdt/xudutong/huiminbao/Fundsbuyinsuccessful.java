package com.xdt.xudutong.huiminbao;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class Fundsbuyinsuccessful extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mfundsbuychargesuccessfullayoutback = (LinearLayout) findViewById(R.id.fundsbuychargesuccessfullayoutback);
        TextView mfundsbuychargesuccessfullayoutsubmit = (TextView) findViewById(R.id.fundsbuychargesuccessfullayoutsubmit);
        mfundsbuychargesuccessfullayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfundsbuychargesuccessfullayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.fundsbuychargesuccessfullayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
