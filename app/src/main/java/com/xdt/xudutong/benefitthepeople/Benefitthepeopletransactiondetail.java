package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.view.Mydatepickdemo;

/**
 * Created by Administrator on 2018\2\3 0003.
 */

public class Benefitthepeopletransactiondetail extends BaseActivity {

    private LinearLayout mbenefitthepeopletransactiondetaillayout;
    private LinearLayout mbenefitthepeopletransactiondetaildetailssecect;

    @Override
    public void initView() {
        mbenefitthepeopletransactiondetaillayout = (LinearLayout) findViewById(R.id.benefitthepeopletransactiondetaillayout);
        LinearLayout benefitthepeopletransactiondetailback = (LinearLayout) findViewById(R.id.benefitthepeopletransactiondetailback);
        mbenefitthepeopletransactiondetaildetailssecect = (LinearLayout) findViewById(R.id.benefitthepeopletransactiondetaildetailssecect);
        ImageView mbenefitthepeopletransactiondetailselectdate = (ImageView) findViewById(R.id.benefitthepeopletransactiondetailselectdate);
        mbenefitthepeopletransactiondetailselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbuttomdialog();
            }
        });
        benefitthepeopletransactiondetailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeopletransactiondetaildetailssecect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbuttomdialogtop();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopletransactiondetaillayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private void showbuttomdialog() {
        View inflate2 = LayoutInflater.from(Benefitthepeopletransactiondetail.this).inflate(R.layout.littlegreenbikerecordlayout2, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade2);
        popupWindow2.setTouchable(true);
        popupWindow2.setOutsideTouchable(true);
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
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.showAtLocation(mbenefitthepeopletransactiondetaillayout, Gravity.BOTTOM, 0, 0);
        //起始日期，结束日期，重置，完成四个按钮的逻辑
        LinearLayout mlittlegreenbikerecordlayoutstartdate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutstartdate);
        LinearLayout mlittlegreenbikerecordlayoutenddate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutenddate);
        TextView mlittlegreenbikerecordlayoutrestart = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutrestart);
        TextView mlittlegreenbikerecordlayoutsutmit = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutsutmit);
        mlittlegreenbikerecordlayoutstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopletransactiondetail.this, Mydatepickdemo.class);
                startActivity(intent);
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbikerecordlayoutenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeopletransactiondetail.this, Mydatepickdemo.class);
                startActivity(intent);
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbikerecordlayoutrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbikerecordlayoutsutmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
    }

    private void showbuttomdialogtop() {
        View inflate2 = LayoutInflater.from(Benefitthepeopletransactiondetail.this).inflate(R.layout.benefitthepeopletransactiondetaillayouttop, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2,300, 350, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setTouchable(true);
        popupWindow2.setOutsideTouchable(true);
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
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
       int offsetX = Math.abs(popupWindow2.getContentView().getMeasuredWidth()-mbenefitthepeopletransactiondetaildetailssecect.getWidth()) / 2;
        popupWindow2.showAsDropDown(mbenefitthepeopletransactiondetaildetailssecect, offsetX, 0, Gravity.BOTTOM);
    }
}
