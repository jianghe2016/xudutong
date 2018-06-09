package com.xdt.xudutong.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2017\8\21 0021.
 */

public class TimerCountshape2 extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     * to {@link #start()} until the countdown is done and {@link #onFinish()}
     * is called.
     * @param countDownInterval The interval along the way to receive
     * {@link #onTick(long)} callbacks.
     */
    private Button bnt;
    private Context context;

    public TimerCountshape2(long millisInFuture, long countDownInterval, Button bnt, Context context) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.bnt = bnt;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示

        // TODO Auto-generated method stub
        bnt.setClickable(false);
        bnt.setBackground(context.getResources().getDrawable(R.drawable.shape_edittext3_2));
        bnt.setText(millisUntilFinished / 1000 + "S");
        bnt.setTextColor(context.getResources().getColor(R.color.whitecolortext));
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        bnt.setClickable(true);
        bnt.setBackground(context.getResources().getDrawable(R.drawable.shape_edittext3_2));
        bnt.setText("获取验证码");
    }
}
