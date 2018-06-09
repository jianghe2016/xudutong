package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.frgment.MainActivity;

/**
 * Created by Administrator on 2017/5/10.
 */

public class Personitemtwotwice_passwordsuccess extends BaseActivity {

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_itemtwo_twice_passwordsuccess);
    }

    @Override
    public void initView() {
        LinearLayout mperson_itemtwo_twice_passwordsuccessback = (LinearLayout) findViewById(R.id.person_itemtwo_twice_passwordsuccessback);
        Button mperson_twice_passwordsuccess_submit = (Button) findViewById(R.id.person_twice_passwordsuccess_submit);
        mperson_itemtwo_twice_passwordsuccessback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwotwice_passwordsuccess.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mperson_twice_passwordsuccess_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwotwice_passwordsuccess.this, Personuser_comein.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
