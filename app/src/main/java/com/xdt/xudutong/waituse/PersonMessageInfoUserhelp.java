package com.xdt.xudutong.waituse;

import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\1\2 0002.
 */
//小绿用户指南页面
public class PersonMessageInfoUserhelp extends BaseActivity {
    @Override
    public void setMyContentView() {
        setContentView(R.layout.personuserhelp_messageinfo);
    }

    @Override
    public void initView() {
        LinearLayout mperson_messageinfo_userhelpback = (LinearLayout) findViewById(R.id.person_messageinfo_userhelpback);
        TextView mperson_messageinfo_userhelptext1 = (TextView) findViewById(R.id.person_messageinfo_userhelptext1);
        String text1="打开实名认证界面，根据步骤提示，依次填写<font color='#2772d7'>基本信息--身份证明</font>，然后提交等待审核。";
        mperson_messageinfo_userhelptext1.setText(Html.fromHtml(text1));
        mperson_messageinfo_userhelpback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
