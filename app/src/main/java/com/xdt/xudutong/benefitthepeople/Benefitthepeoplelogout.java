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
//注销成功页面的功能
public class Benefitthepeoplelogout extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenefitthepeoplelogoutlayoutback = (LinearLayout) findViewById(R.id.benefitthepeoplelogoutlayoutback);
        Button mbenefitthepeoplelogoutlayoutsubmit = (Button) findViewById(R.id.benefitthepeoplelogoutlayoutsubmit);
        mbenefitthepeoplelogoutlayoutback .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplelogoutlayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplelogout.this, Benefitthepeopleformyaccount.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplelogoutlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
