package com.xdt.xudutong.homefragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.JiuzhenRecyclerAdapter;
import com.xdt.xudutong.adapder.JiuzhenRecyclerAdaptertwo;
import com.xdt.xudutong.frgment.BaseActivity;

import java.util.Calendar;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class Homeguahaojiuzhen extends BaseActivity {
    private PopupWindow popupwindow;
    private LinearLayout home_guaohaojiuzhenitem1;
    private LinearLayout home_guaohaojiuzhenitem11;
    private LinearLayout home_guaohaojiuzhenitem2;
    private LinearLayout home_guaohaojiuzhenitem22;
    private LinearLayout home_guaohaojiuzhenitem3;
    private LinearLayout home_guaohaojiuzhenitem33;
    private FrameLayout mhome_guaohaojiuzhen1item;
    private FrameLayout mhome_guaohaojiuzhen2item;
    private FrameLayout mhome_guaohaojiuzhen3item;
    private TextView mhome_guaohaojiuzhenitem111;
    private TextView mhome_guaohaojiuzhenitem222;
    private TextView mhome_guaohaojiuzhenitem333;
    private TextView pupwindowvisit;
    private LinearLayout homebuttongroup_buttonguahaojiuzhenback1;
    private int year, monthOfYear, dayOfMonth;
    private TextView homebuttongroup_buttonguahaojiuzhenquery1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_guaohaojiuzhen);
    }

    @Override
    public void initView() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        homebuttongroup_buttonguahaojiuzhenback1 = (LinearLayout) findViewById(R.id.homebuttongroup_buttonguahaojiuzhenback);
        pupwindowvisit = (TextView) findViewById(R.id.pupwindowvisit);
        //帧布局进行选择
        mhome_guaohaojiuzhen1item = (FrameLayout) findViewById(R.id.home_guaohaojiuzhen1item);
        mhome_guaohaojiuzhen2item = (FrameLayout) findViewById(R.id.home_guaohaojiuzhen2item);
        mhome_guaohaojiuzhen3item = (FrameLayout) findViewById(R.id.home_guaohaojiuzhen3item);
        //6个，1为蓝色，11为黑色，
        home_guaohaojiuzhenitem1 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem1);
        home_guaohaojiuzhenitem11 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem11);
        home_guaohaojiuzhenitem2 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem2);
        home_guaohaojiuzhenitem22 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem22);
        home_guaohaojiuzhenitem3 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem3);
        home_guaohaojiuzhenitem33 = (LinearLayout) findViewById(R.id.home_guaohaojiuzhenitem33);
        //下面蓝线的显示
        mhome_guaohaojiuzhenitem111 = (TextView) findViewById(R.id.home_guaohaojiuzhenitem111);
        mhome_guaohaojiuzhenitem222 = (TextView) findViewById(R.id.home_guaohaojiuzhenitem222);
        mhome_guaohaojiuzhenitem333 = (TextView) findViewById(R.id.home_guaohaojiuzhenitem333);
        //提交按钮
        homebuttongroup_buttonguahaojiuzhenquery1 = (TextView) findViewById(R.id.homebuttongroup_buttonguahaojiuzhenquery);
        initData();
    }

    private void initData() {

        homebuttongroup_buttonguahaojiuzhenback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String[] date = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        final String[] doctor = new String[]{"许昌市中心医院", "许昌市市立医院", "许昌市人民医院", "许昌市妇幼保健院",
                "许昌市公疗医院", "许昌市中医院", "许昌市建安医院"};
        final String[] doctorroom = new String[]{"不限", "内科", "外科", "妇产科", "儿科", "眼科", "骨科", "口腔科", "皮肤科", "内分泌科", "心脑血管", "精神内科"};
        mhome_guaohaojiuzhen1item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示蓝线
                mhome_guaohaojiuzhenitem111.setVisibility(View.VISIBLE);
                mhome_guaohaojiuzhenitem222.setVisibility(View.INVISIBLE);
                mhome_guaohaojiuzhenitem333.setVisibility(View.INVISIBLE);

                home_guaohaojiuzhenitem11.setVisibility(View.INVISIBLE);
                home_guaohaojiuzhenitem22.setVisibility(View.VISIBLE);
                home_guaohaojiuzhenitem33.setVisibility(View.VISIBLE);

                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(Homeguahaojiuzhen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month,
                                          int day) {
                        int mYear = year;
                        int mMonth = month;
                        int mDay = day;
                    /*    startoffdate1.setText(new StringBuilder().append(mYear).append("-")
                                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                .append((mDay < 10) ? "0" + mDay : mDay));*/

                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.show();




            }
        });
        mhome_guaohaojiuzhen2item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示蓝线
                mhome_guaohaojiuzhenitem111.setVisibility(View.INVISIBLE);
                mhome_guaohaojiuzhenitem222.setVisibility(View.VISIBLE);
                mhome_guaohaojiuzhenitem333.setVisibility(View.INVISIBLE);

                home_guaohaojiuzhenitem11.setVisibility(View.VISIBLE);
                home_guaohaojiuzhenitem22.setVisibility(View.INVISIBLE);
                home_guaohaojiuzhenitem33.setVisibility(View.VISIBLE);

                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView2(doctor);
                    popupwindow.showAsDropDown(pupwindowvisit, 30, 0, Gravity.CENTER_HORIZONTAL);
                }

            }
        });
        mhome_guaohaojiuzhen3item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示蓝线
                mhome_guaohaojiuzhenitem111.setVisibility(View.INVISIBLE);
                mhome_guaohaojiuzhenitem222.setVisibility(View.INVISIBLE);
                mhome_guaohaojiuzhenitem333.setVisibility(View.VISIBLE);

                home_guaohaojiuzhenitem11.setVisibility(View.VISIBLE);
                home_guaohaojiuzhenitem22.setVisibility(View.VISIBLE);
                home_guaohaojiuzhenitem33.setVisibility(View.INVISIBLE);

                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView(doctorroom);
                    popupwindow.showAsDropDown(pupwindowvisit, 30, 0, Gravity.CENTER_HORIZONTAL);


                }
            }
        });
        homebuttongroup_buttonguahaojiuzhenquery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeguahaojiuzhen.this,Homeguahaojiuzhendetails.class);
                startActivity(intent);
            }
        });
    }
    public void initmPopupWindowView0(final String[] date) {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * 1);
        int hight = (int) (wm.getDefaultDisplay().getHeight() * 0.4);

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.jiuzhenpopview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable drawable = getResources().getDrawable(R.color.whiteblacktext);
        popupwindow.setBackgroundDrawable(drawable);
        popupwindow.setOutsideTouchable(true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        // popupwindow.setAnimationStyle(R.style.AnimationFade2);
        // 自定义view添加触摸事件
        RecyclerView rererecycle1 = (RecyclerView) customView.findViewById(R.id.jiuzhenpopviewrecycleview);
        rererecycle1.setLayoutManager(new GridLayoutManager(Homeguahaojiuzhen.this, 3));
        JiuzhenRecyclerAdapter productRecyclerAdapter = new JiuzhenRecyclerAdapter(Homeguahaojiuzhen.this, date);
        rererecycle1.setAdapter(productRecyclerAdapter);
        popupwindow.setOutsideTouchable(true);
        productRecyclerAdapter.setOnItemClickListener(new JiuzhenRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                popupwindow.dismiss();
            }
        });
    }


    public void initmPopupWindowView2(final String[] doctor) {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * 1);
        int hight = (int) (wm.getDefaultDisplay().getHeight() * 0.4);

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.jiuzhenpopview_itemtwo,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable drawable = getResources().getDrawable(R.color.whiteblacktext);
        popupwindow.setBackgroundDrawable(drawable);
        popupwindow.setOutsideTouchable(true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        // popupwindow.setAnimationStyle(R.style.AnimationFade2);
        // 自定义view添加触摸事件
        RecyclerView rererecycle1 = (RecyclerView) customView.findViewById(R.id.jiuzhenpopviewrecycleviewtwo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rererecycle1.setLayoutManager(layoutManager);

        JiuzhenRecyclerAdaptertwo productRecyclerAdapter = new JiuzhenRecyclerAdaptertwo(Homeguahaojiuzhen.this, doctor);
        rererecycle1.setAdapter(productRecyclerAdapter);
        popupwindow.setOutsideTouchable(true);
        productRecyclerAdapter.setOnItemClickListener(new JiuzhenRecyclerAdaptertwo.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                popupwindow.dismiss();
            }
        });


    }
    public void initmPopupWindowView(final String[] date) {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * 1);
        int hight = (int) (wm.getDefaultDisplay().getHeight() * 0.4);

        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.jiuzhenpopview_item,
                null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupwindow = new PopupWindow(customView, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable drawable = getResources().getDrawable(R.color.whiteblacktext);
        popupwindow.setBackgroundDrawable(drawable);
        popupwindow.setOutsideTouchable(true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        // popupwindow.setAnimationStyle(R.style.AnimationFade2);
        // 自定义view添加触摸事件
        RecyclerView rererecycle1 = (RecyclerView) customView.findViewById(R.id.jiuzhenpopviewrecycleview);
        rererecycle1.setLayoutManager(new GridLayoutManager(Homeguahaojiuzhen.this, 3));
        JiuzhenRecyclerAdapter productRecyclerAdapter = new JiuzhenRecyclerAdapter(Homeguahaojiuzhen.this, date);
        rererecycle1.setAdapter(productRecyclerAdapter);
        popupwindow.setOutsideTouchable(true);
        productRecyclerAdapter.setOnItemClickListener(new JiuzhenRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                popupwindow.dismiss();
            }
        });
    }
}
