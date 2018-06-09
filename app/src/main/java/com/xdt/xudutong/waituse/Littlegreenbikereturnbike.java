package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

/**
 * Created by Administrator on 2018\1\4 0004.
 */

public class Littlegreenbikereturnbike extends BaseActivity{
    @Override
    public void initView() {
        TextView mjianpanfukuan = (TextView) findViewById(R.id.jianpanfukuan);
        LinearLayout mlittlegreeenbiketurnbikeback = (LinearLayout) findViewById(R.id.littlegreeenbiketurnbikeback);
        Intent intent = getIntent();
        int littlegreenpayforturnbike = intent.getIntExtra("littlegreenpayforturnbike", 0);
        mlittlegreeenbiketurnbikeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (littlegreenpayforturnbike==1){
            mjianpanfukuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Littlegreenbikereturnbike.this,Littlegreenbikepaysuccessful.class);
                    startActivity(intent);
                }
            });
        }else{
            LogUtil.d("不是订单支付页面过来的","不是订单支付页面过来的");
            ToastUtils.getInstance(Littlegreenbikereturnbike.this).showMessage("暂未产生扣费信息");
        }
    }

    @Override
    public void setMyContentView() {
setContentView(R.layout.littlegreenbikereturnbikelayout);
    }
}
