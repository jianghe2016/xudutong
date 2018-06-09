package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.Find_tab_Adapter;
import com.xdt.xudutong.datepick.SimpleMonthAdapter;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.VerticalListpick;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018\1\5 0005.
 */

public class LittlegreenbikeRecord extends BaseActivity {

    private TabLayout mlittlegreenbiketakerecordtablelayout;
    private ViewPager mlittlegreenbiketakerecordviewpager;
    private ImageView mlittlegreembikerecordselectdate;
    private LinearLayout malllittlegreenrecordlayout;
    private TextView mlittlegreenbikerecordlayoutstartdateget11;
    private TextView mlittlegreenbikerecordlayoutenddateget22;
    private LinearLayout mlittlegreenbikerecordlayoutstartdate;
    private LinearLayout mlittlegreenbikerecordlayoutenddate;
    private LittlegreenBiketakerecordOne littlegreenBiketakerecordone;
    private LittlegreenBiketakerecordTwo littlegreenBiketakerecordtwo;
    private String xdtcardnumber;

    private Find_tab_Adapter find_tab_adapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private int count = 0;
    private String littlestartdate1;
    private String littleenddate1;
    private String littlegreenstarttime;
    private String littlegreenendtime;

    @Override
    public void initView() {
        Intent intent = getIntent();
        xdtcardnumber = intent.getStringExtra("xdtcardnumber");
        LinearLayout mlittlegreembikerecordback = (LinearLayout) findViewById(R.id.littlegreembikerecordback);
        malllittlegreenrecordlayout = (LinearLayout) findViewById(R.id.alllittlegreenrecordlayout);
        mlittlegreembikerecordselectdate = (ImageView) findViewById(R.id.littlegreembikerecordselectdate);
        mlittlegreenbiketakerecordtablelayout = (TabLayout) findViewById(R.id.littlegreenbiketakerecordtablelayout);
        mlittlegreenbiketakerecordviewpager = (ViewPager) findViewById(R.id.littlegreenbiketakerecordviewpager);
        mlittlegreembikerecordback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        littlegreenBiketakerecordone = new LittlegreenBiketakerecordOne();
        littlegreenBiketakerecordtwo = new LittlegreenBiketakerecordTwo();
        fragmentList.add(littlegreenBiketakerecordone);
        fragmentList.add(littlegreenBiketakerecordtwo);
        stringList = new ArrayList<>();
        stringList.add("刷卡");
        stringList.add("扫码");
        mlittlegreenbiketakerecordtablelayout.setTabMode(TabLayout.MODE_FIXED);
        mlittlegreenbiketakerecordtablelayout.addTab(mlittlegreenbiketakerecordtablelayout.newTab().setText(stringList.get(0)));
        mlittlegreenbiketakerecordtablelayout.addTab(mlittlegreenbiketakerecordtablelayout.newTab().setText(stringList.get(1)));
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        find_tab_adapter = new Find_tab_Adapter(supportFragmentManager, fragmentList, stringList);
        mlittlegreenbiketakerecordviewpager.setAdapter(find_tab_adapter);
        mlittlegreenbiketakerecordtablelayout.setupWithViewPager(mlittlegreenbiketakerecordviewpager);
        mlittlegreembikerecordselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbuttomdialog();
                if (!TextUtils.isEmpty(littlegreenstarttime) && !TextUtils.isEmpty(littlegreenendtime)) {
                    mlittlegreenbikerecordlayoutstartdateget11.setText(littlegreenstarttime);
                    mlittlegreenbikerecordlayoutenddateget22.setText(littlegreenendtime);
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("xdtcardnumber", xdtcardnumber);

        littlegreenBiketakerecordone.setArguments(bundle);
        littlegreenBiketakerecordtwo.setArguments(bundle);
    }

    private void showbuttomdialog() {
        View inflate2 = LayoutInflater.from(LittlegreenbikeRecord.this).inflate(R.layout.littlegreenbikerecordlayout, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade2);
        popupWindow2.setTouchable(true);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popupWindow2.showAtLocation(malllittlegreenrecordlayout, Gravity.BOTTOM, 0, 0);
        //起始日期，结束日期，重置，完成四个按钮的逻辑
        mlittlegreenbikerecordlayoutstartdate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutstartdate2);
        mlittlegreenbikerecordlayoutenddate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutenddate2);
        TextView mlittlegreenbikerecordlayoutrestart = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutrestart2);
        TextView mlittlegreenbikerecordlayoutsutmit = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutsutmit2);
        //得到的开始日期和结束日期
        mlittlegreenbikerecordlayoutstartdateget11 = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutstartdateget11);
        mlittlegreenbikerecordlayoutenddateget22 = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutenddateget22);
        mlittlegreenbikerecordlayoutstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*      Intent intent = new Intent(LittlegreenbikeRecord.this, Mydatepickdemo.class);
                startActivity(intent);*/
                Intent intent = new Intent(LittlegreenbikeRecord.this, VerticalListpick.class);
                startActivityForResult(intent, 61); //REQUESTCODE--->1
            }
        });
        mlittlegreenbikerecordlayoutenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LittlegreenbikeRecord.this, VerticalListpick.class);
                startActivityForResult(intent, 62); //REQUESTCODE--->1
            }
        });
        mlittlegreenbikerecordlayoutrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        final Bundle bundle = new Bundle();
        bundle.putString("xdtcardnumber", xdtcardnumber);
        mlittlegreenbikerecordlayoutsutmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                littlegreenstarttime = mlittlegreenbikerecordlayoutstartdateget11.getText().toString();
                littlegreenendtime = mlittlegreenbikerecordlayoutenddateget22.getText().toString();
                if (!TextUtils.isEmpty(xdtcardnumber)) {
                    if (!littlegreenstarttime.equals("起始日期") && !littlegreenendtime.equals("结束日期")) {
                        count++;
                 /*   Littlegreenexchange littlegreenexchange = new Littlegreenexchange();
                    littlegreenexchange.setLittlestartdate(littlegreenstarttime);
                    littlegreenexchange.setLittleenddate(littlegreenendtime);*/

                        String replace = littlegreenstarttime.replace("-", "");
                        String replace2 = littlegreenendtime.replace("-", "");

                        EventBus.getDefault().post(new EventMsg(EventMsg.LITTLEGREENSTARTTIME, replace + "-" + replace2));
                    } else {
                        ToastUtils.getInstance(LittlegreenbikeRecord.this).showMessage("请选择要查询的日期");
                    }


                } else {
                    ToastUtils.getInstance(LittlegreenbikeRecord.this).showMessage("请核实输入的许都通卡号");
                }


                popupWindow2.dismiss();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreenbikerecord);
    }

    /**
     * @param preDay 天数  这里指的是 可选择的天数，而不是显示天数，比如：可选择天数跨越3个月，那么显示天数为 这三个月所有的天数，不可选择的天 灰色显示
     */
    public List<SimpleMonthAdapter.CalendarDay> getCanAdvanceDate(int preDay) {
        List<SimpleMonthAdapter.CalendarDay> retList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        long startTime = calendar.getTimeInMillis();//初始可选择时间，可根据需求修改
        for (int i = 0; i < preDay; i++) {
            SimpleMonthAdapter.CalendarDay calendarDay = new SimpleMonthAdapter.CalendarDay(startTime);
            retList.add(calendarDay);
            startTime += 24 * 60 * 60 * 1000;
        }
        return retList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 61) {
            if (resultCode == 63) {
                String littlestartdate = data.getStringExtra("littlestartdate");
                String littleenddate = data.getStringExtra("littleenddate");
                LogUtil.d("littlestartdate", littlestartdate);
                LogUtil.d("littleenddate", littleenddate);
                if (null != mlittlegreenbikerecordlayoutstartdate && null != mlittlegreenbikerecordlayoutenddate) {
                    if (!TextUtils.isEmpty(littlestartdate)) {
                        mlittlegreenbikerecordlayoutstartdateget11.setText(littlestartdate);
                    }
                    if (!TextUtils.isEmpty(littleenddate)) {
                        mlittlegreenbikerecordlayoutenddateget22.setText(littleenddate);
                    }


                }
            }
        }
        if (requestCode == 62) {
            if (resultCode == 63) {
                String littlestartdate = data.getStringExtra("littlestartdate");
                String littleenddate = data.getStringExtra("littleenddate");
                LogUtil.d("littlestartdate22", littlestartdate);
                LogUtil.d("littleenddate22", littleenddate);
                if (null != mlittlegreenbikerecordlayoutstartdate && null != mlittlegreenbikerecordlayoutenddate) {
                    if (!TextUtils.isEmpty(littlestartdate)) {
                        mlittlegreenbikerecordlayoutstartdateget11.setText(littlestartdate);
                        if (!TextUtils.isEmpty(littleenddate)) {
                            mlittlegreenbikerecordlayoutenddateget22.setText(littleenddate);
                        }
                    }
                }
            }

        }
    }
}
