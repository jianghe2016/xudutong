package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017\9\1 0001.
 */

public class Homeguahaojiuzhendetails extends BaseActivity {
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_guaohaojiuzhendetails);
    }

    @Override
    public void initView() {
        LinearLayout homebuttongroup_buttonguahaojiuzhendetailsback1 = (LinearLayout) findViewById(R.id.homebuttongroup_buttonguahaojiuzhendetailsback);
        Button home_guaohaojiuzhendetailsbuttonyes1 = (Button) findViewById(R.id.home_guaohaojiuzhendetailsbuttonyes);
        Button home_guaohaojiuzhendetailsbuttoncancle1 = (Button) findViewById(R.id.home_guaohaojiuzhendetailsbuttoncancle);
        homebuttongroup_buttonguahaojiuzhendetailsback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        home_guaohaojiuzhendetailsbuttonyes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeguahaojiuzhendetails.this,Homeguahaojiuzhendetailsnext.class);
                startActivity(intent);
            }
        });
        home_guaohaojiuzhendetailsbuttoncancle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
