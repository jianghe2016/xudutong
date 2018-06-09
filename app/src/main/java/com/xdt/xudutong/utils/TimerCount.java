package com.xdt.xudutong.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Administrator on 2017\8\21 0021.
 */

public class TimerCount extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     * to {@link #start()} until the countdown is done and {@link #onFinish()}
     * is called.
     * @param countDownInterval The interval along the way to receive
     * {@link #onTick(long)} callbacks.
     */
    private Button bnt;

    public TimerCount(long millisInFuture, long countDownInterval, Button bnt) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.bnt = bnt;
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示

        // TODO Auto-generated method stub
        bnt.setClickable(false);
        bnt.setBackgroundColor(Color.parseColor("#c9caca"));
        bnt.setText(millisUntilFinished / 1000 + "S");
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        bnt.setClickable(true);
        bnt.setBackgroundColor(Color.parseColor("#26d77b"));
        bnt.setText("获取验证码");
    }
}
