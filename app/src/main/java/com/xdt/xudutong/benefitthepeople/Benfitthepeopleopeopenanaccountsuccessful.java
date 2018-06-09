package com.xdt.xudutong.benefitthepeople;

import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\2\1 0001.
 */
//开户成功
public class Benfitthepeopleopeopenanaccountsuccessful extends BaseActivity {
    @Override
    public void initView() {
        LinearLayout mbenfitthepeopleopeopenanaccountsuccessfulback = (LinearLayout) findViewById(R.id.benfitthepeopleorderinglayoutback);
        mbenfitthepeopleopeopenanaccountsuccessfulback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyContentView() {
        //setContentView(R.layout.benfitthepeopleopeopenanaccountsuccessfullayout);
        setContentView(R.layout.benfitthepeopleorderinglayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }
}
