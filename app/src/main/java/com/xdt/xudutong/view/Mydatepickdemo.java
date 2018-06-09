package com.xdt.xudutong.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.datepick.DatePickerView;
import com.xdt.xudutong.datepick.SimpleMonthAdapter;
import com.xdt.xudutong.frgment.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018\1\16 0016.
 */
//废弃页面，暂时没用
public class Mydatepickdemo extends BaseActivity {
    private DatePickerView datePickerView;
    @Override
    public void initView() {
        LinearLayout mlittlegreeenbikerecordselectdateback = (LinearLayout) findViewById(R.id.littlegreeenbikerecordselectdateback);
        TextView mlittlegreeenbikerecordselectdatesuccess = (TextView) findViewById(R.id.littlegreeenbikerecordselectdatesuccess);
        mlittlegreeenbikerecordselectdateback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mlittlegreeenbikerecordselectdatesuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        datePickerView = (DatePickerView) findViewById(R.id.mydate_pickerforlittle);
        //给adapter 设置数据
        datePickerView.getDayPickerView().setCountMap(getCanAdvanceDate(7000));
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.mydatepickforlittlegreenselectdate);
    }
    /**
     * @param preDay 天数  这里指的是 可选择的天数，而不是显示天数，比如：可选择天数跨越3个月，那么显示天数为 这三个月所有的天数，不可选择的天 灰色显示
     */
    public List<SimpleMonthAdapter.CalendarDay> getCanAdvanceDate(int preDay) {
        List<SimpleMonthAdapter.CalendarDay> retList = new ArrayList<>();
/*        Calendar calendar = Calendar.getInstance();
        long startTime = calendar.getTimeInMillis();//初始可选择时间，可根据需求修改*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 1, 1, 1, 1, 1);
        long startTime = calendar.getTimeInMillis();
        System.out.println(calendar.getTimeInMillis());
        //  Log.d("startTimestartTime",startTime+"startTime");
        for (int i = 0; i < preDay; i++) {
            SimpleMonthAdapter.CalendarDay calendarDay = new SimpleMonthAdapter.CalendarDay(startTime);
            retList.add(calendarDay);
            startTime += 24 * 60 * 60 * 1000;
        }
        return retList;
    }
}
