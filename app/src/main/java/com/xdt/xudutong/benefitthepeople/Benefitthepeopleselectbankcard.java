package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\3 0003.
 */
//选择银行卡页面
public class Benefitthepeopleselectbankcard extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenefitthepeopleselectbankcardlayoutback = (LinearLayout) findViewById(R.id.benefitthepeopleselectbankcardlayoutback);
        ImageView mbenefitthepeopleselectbankcardlayoutselcet = (ImageView) findViewById(R.id.benefitthepeopleselectbankcardlayoutselcet);
        mbenefitthepeopleselectbankcardlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeopleselectbankcardlayoutselcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleselectbankcardlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
