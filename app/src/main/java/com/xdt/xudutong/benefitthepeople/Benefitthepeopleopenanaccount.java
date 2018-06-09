package com.xdt.xudutong.benefitthepeople;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\1\30 0030.
 */
//惠民宝————开户
public class Benefitthepeopleopenanaccount extends BaseActivity {
    @Override
    public void initView() {
        RelativeLayout mbenefitthepeopleaddbankcard_1selectbank = (RelativeLayout) findViewById(R.id.benefitthepeopleaddbankcard_1selectbank);
        LinearLayout mbenefitthepeopleopenanaccountback = (LinearLayout) findViewById(R.id.benefitthepeopleopenanaccountback);
        EditText mbenefitthepeopleopenanaccounteduittext1 = (EditText) findViewById(R.id.benefitthepeopleopenanaccounteduittext1);
        SpannableString ss = new SpannableString("请输入本人真实姓名");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        mbenefitthepeopleopenanaccounteduittext1.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        mbenefitthepeopleopenanaccountback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeopleaddbankcard_1selectbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleopenanaccountlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
