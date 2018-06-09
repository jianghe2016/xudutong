package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\1 0001.
 */

public class Benfitthepeopleordering extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenfitthepeopleorderinglayoutback = (LinearLayout) findViewById(R.id.benfitthepeopleorderinglayoutback);
        mbenfitthepeopleorderinglayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benfitthepeopleorderinglayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
