package com.xdt.xudutong.homefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maning.calendarlibrary.MNCalendarVertical;
import com.maning.calendarlibrary.listeners.OnCalendarRangeChooseListener;
import com.maning.calendarlibrary.model.MNCalendarVerticalConfig;
import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.waituse.LittlegreenbikeRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018\3\5 0005.
 */

public class VerticalListpick extends AppCompatActivity {
    private Context context;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String startTime;
    private String endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpickdemolayout);
        context = this;
        LinearLayout mlistpickdemolayoutback = (LinearLayout) findViewById(R.id.listpickdemolayoutback);
        TextView mlistpickdemolayoutsubmit = (TextView) findViewById(R.id.listpickdemolayoutsubmit);
        MNCalendarVertical mmnCalendarVertical = (MNCalendarVertical) findViewById(R.id.mnCalendarVertical);
        mlistpickdemolayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mlistpickdemolayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(VerticalListpick.this, LittlegreenbikeRecord.class);
                    bundle.putString("littlestartdate", startTime);
                    bundle.putString("littleenddate", endTime);
                    intent.putExtras(bundle);
                    setResult(63, intent);
                    finish();
                } else {
                    ToastUtils.getInstance(VerticalListpick.this).showMessage("请选择开始日期或者结束日期");
                }
            }
        });

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        // 区间选取完成监听

        mmnCalendarVertical.setOnCalendarRangeChooseListener(new OnCalendarRangeChooseListener() {
            @Override
            public void onRangeDate(Date startDate, Date endDate) {
                startTime = sdf.format(startDate);
                endTime = sdf.format(endDate);
                //  Toast.makeText(context, "开始日期:" + startTime + ",结束日期:" + endTime, Toast.LENGTH_SHORT).show();
            }
        });


        // 自定义设置相关

        MNCalendarVerticalConfig mnCalendarVerticalConfig = new MNCalendarVerticalConfig.Builder()
                .setMnCalendar_showWeek(true)                   //是否显示星期栏
                .setMnCalendar_showLunar(false)                  //是否显示阴历
                .setMnCalendar_colorWeek("#727272")             //星期栏的颜色
                .setMnCalendar_titleFormat("yyyy-MM")           //每个月的标题样式
                .setMnCalendar_colorTitle("#000000")            //每个月标题的颜色
                .setMnCalendar_colorSolar("#0f0f0f")            //阳历的颜色
                .setMnCalendar_colorLunar("#cccccc")            //阴历的颜色
                .setMnCalendar_colorBeforeToday("#cccccc")      //今天之前的日期的颜色
                .setMnCalendar_colorRangeBg("#23160241")        //区间中间的背景颜色
                .setMnCalendar_colorRangeText("#17a0f1")        //区间文字的颜色
                .setMnCalendar_colorStartAndEndBg("#17a0f1")     //开始结束的背景颜色
                .setMnCalendar_countMonth((year - 2015) * 12 + month)                    //显示多少月(默认6个月)
                .build();
        mmnCalendarVertical.setConfig(mnCalendarVerticalConfig);









/*
        MNCalendar mnCalendar = (MNCalendar) findViewById(R.id.mnCalendar);
        MNCalendarConfig build = new MNCalendarConfig.Builder()
                //星期的文字颜色
                .setMnCalendar_colorWeek("#00ff00")
                //阴历的颜色
                .setMnCalendar_colorLunar("#FF0000")
                //阳历的颜色
                .setMnCalendar_colorSolar("#9BCCAF")
                //今天的背景色
                .setMnCalendar_colorTodayBg("#00FFFF")
                //今天的文字颜色
                .setMnCalendar_colorTodayText("#000000")
                //不是本月的文字颜色
                .setMnCalendar_colorOtherMonth("#F1EDBD")
                //标题的颜色
                .setMnCalendar_colorTitle("#FF0000")
                //选中日期的背景色
                .setMnCalendar_colorSelected("#FFFF00")
                //是否显示阴历
                .setMnCalendar_showLunar(true)
                //是否显示标题
                .setMnCalendar_showWeek(true)
                //显示标题的样式
                .setMnCalendar_TitleDateFormat("yyyy年MM月")
                .build();
        mnCalendar.setConfig(build);*/



       /* Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 0);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);

        int i = nextYear.get(Calendar.YEAR);
        Date today = new Date(i-1900-5,0,1);
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);

        calendar.init(today, nextYear.getTime()).inMode(RANGE);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                LogUtil.d("datedatedatedate",df.format(date)+"");
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });*/
    }
}
