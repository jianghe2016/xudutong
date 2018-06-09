package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;

import static com.xdt.xudutong.utils.EventMsg.PERSONSSECRETSTATES;
import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017\9\17 0017.
 */

public class Personssecret extends BaseActivity {
    int currentImg = 0;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_center_secret);
    }

    @Override
    public void initView() {
        TextView mperson_secrstorpush1 = (TextView) findViewById(R.id.person_secrstorpush);
        TextView mperson_secrstorpushtext1 = (TextView) findViewById(R.id.person_secrstorpushtext1);
        //显示下面说明的字体
        TextView mperson_secrstorpushtext2 = (TextView) findViewById(R.id.person_secrstorpushtext2);
        TextView mperson_secrstorpushtext3 = (TextView) findViewById(R.id.person_secrstorpushtext3);
        TextView mperson_secrstorpushtext4 = (TextView) findViewById(R.id.person_secrstorpushtext4);
        TextView mperson_secrstorpushtext5 = (TextView) findViewById(R.id.person_secrstorpushtext5);
        //返回键
        LinearLayout mperson_settingnextsecretback = (LinearLayout) findViewById(R.id.person_settingnextsecretback);
        SwitchButton switchButton = (SwitchButton) findViewById(R.id.toggleButtonsecrct);
        mperson_settingnextsecretback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        Intent intent = getIntent();
        int secrstorpush = intent.getIntExtra("secrstorpush", 1);
        if (secrstorpush == 1) {
            //获取是否是免密码查询状态
            boolean personssecretstates = SpUtils.getParam(getApplicationContext(), Personssecretstates, true);

            if (personssecretstates == true) {
                switchButton.setChecked(true);
            } else {
                switchButton.setChecked(false);
            }
            switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    SpUtils.putParam(getApplication(), Personssecretstates, isChecked);
                    EventBus.getDefault().post(new EventMsg(PERSONSSECRETSTATES, isChecked));
                }

            });
        } else {
            boolean j_push_open = SpUtils.getParam(getApplicationContext(), "j_push_open", true);
            if (j_push_open == true) {
                switchButton.setChecked(true);
            } else {
                switchButton.setChecked(false);
            }
            switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    if (isChecked == true) {
                        SpUtils.putParam(getApplication(), "j_push_open", true);
                        //开启极光推送
                        JPushInterface.resumePush(getApplicationContext());
                        Log.i("开启", "开启");
                    } else {
                        //关闭极光推送
                        SpUtils.putParam(getApplication(), "j_push_open", false);
                        JPushInterface.stopPush(getApplicationContext());
                        Log.i("关闭", "关闭");
                    }
                }

            });
            //如果收到的是2，则显示推送界面，处理逻辑
            mperson_secrstorpush1.setText("消息管理");
            mperson_secrstorpushtext1.setText("消息推送提醒");
            mperson_secrstorpushtext2.setText("应用程序“我城许昌” 可以更改消息推送提醒设置。");
            mperson_secrstorpushtext3.setVisibility(View.INVISIBLE);
            mperson_secrstorpushtext4.setVisibility(View.INVISIBLE);
            mperson_secrstorpushtext5.setVisibility(View.INVISIBLE);
        }
    }
}
