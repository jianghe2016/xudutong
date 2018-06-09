package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2018\1\4 0004.
 */

public class Littlegreenbikepaysuccessful extends BaseActivity {
    @Override
    public void initView() {
        TextView mlittlegreembikepayuccessfulcostmoney = (TextView) findViewById(R.id.littlegreembikepayuccessfulcostmoney);
        TextView mlittlegreembikepayuccessfulbikenumber = (TextView) findViewById(R.id.littlegreembikepayuccessfulbikenumber);
        TextView mlittlegreembikepayuccessfulcosttime = (TextView) findViewById(R.id.littlegreembikepayuccessfulcosttime);
        TextView mlittlegreembikepayuccessfulgetbiketime = (TextView) findViewById(R.id.littlegreembikepayuccessfulgetbiketime);
        TextView mlittlegreembikepayuccessfulgetbikeplace = (TextView) findViewById(R.id.littlegreembikepayuccessfulgetbikeplace);
        TextView mlittlegreembikepayuccessfulreturnbiketime = (TextView) findViewById(R.id.littlegreembikepayuccessfulreturnbiketime);
        TextView mlittlegreembikepayuccessfulreturnbikeplace = (TextView) findViewById(R.id.littlegreembikepayuccessfulreturnbikeplace);
        Intent intent = getIntent();
        //consume 消耗时间（秒）     //expense	金额（元）
        String mconsume = intent.getStringExtra("consume");
        String mexpense = intent.getStringExtra("expense");
        String mreturnTime = intent.getStringExtra("returnTime");
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreenbikepaysuccessfullayout);
    }
}
