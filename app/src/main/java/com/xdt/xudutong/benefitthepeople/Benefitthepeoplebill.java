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

public class Benefitthepeoplebill extends BaseActivity {

    private LinearLayout mbenefitthepeoplebilllayoutback;
    private LinearLayout mbenefitthepeopletransactiondetaildetailssecect;

    @Override
    public void initView() {
        mbenefitthepeoplebilllayoutback = (LinearLayout) findViewById(R.id.benefitthepeoplebilllayoutback);
        mbenefitthepeopletransactiondetaildetailssecect = (LinearLayout) findViewById(R.id.benefitthepeopletransactiondetaildetailssecect);
        ImageView mbenefitthepeopletransactiondetailselectdate = (ImageView) findViewById(R.id.benefitthepeopletransactiondetailselectdate2);
        mbenefitthepeoplebilllayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mbenefitthepeopletransactiondetailselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showbuttomdialog();
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
        setContentView(R.layout.benefitthepeoplebilllayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

/*    private void showbuttomdialog() {
        View inflate2 = LayoutInflater.from(Benefitthepeoplebill.this).inflate(R.layout.littlegreenbikerecordlayout2, null);
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
        popupWindow2.showAtLocation(mbenefitthepeoplebilllayoutback, Gravity.BOTTOM, 0, 0);
        //起始日期，结束日期，重置，完成四个按钮的逻辑
        LinearLayout mlittlegreenbikerecordlayoutstartdate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutstartdate);
        LinearLayout mlittlegreenbikerecordlayoutenddate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutenddate);
        TextView mlittlegreenbikerecordlayoutrestart = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutrestart);
        TextView mlittlegreenbikerecordlayoutsutmit = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutsutmit);
        mlittlegreenbikerecordlayoutstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplebill.this, Mydatepickdemo.class);
                startActivity(intent);
            }
        });
        mlittlegreenbikerecordlayoutenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplebill.this, Mydatepickdemo.class);
                startActivity(intent);
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
    }*/
    private void showbuttomdialog() {
        View inflate2 = LayoutInflater.from(Benefitthepeoplebill.this).inflate(R.layout.littlegreenbikerecordlayout2, null);
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
        popupWindow2.showAtLocation(mbenefitthepeoplebilllayoutback, Gravity.BOTTOM, 0, 0);
        //起始日期，结束日期，重置，完成四个按钮的逻辑
        LinearLayout mlittlegreenbikerecordlayoutstartdate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutstartdate);
        LinearLayout mlittlegreenbikerecordlayoutenddate = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikerecordlayoutenddate);
        TextView mlittlegreenbikerecordlayoutrestart = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutrestart);
        TextView mlittlegreenbikerecordlayoutsutmit = (TextView) inflate2.findViewById(R.id.littlegreenbikerecordlayoutsutmit);
        mlittlegreenbikerecordlayoutstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplebill.this, Mydatepickdemo.class);
                startActivity(intent);
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbikerecordlayoutenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplebill.this, Mydatepickdemo.class);
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
        View inflate2 = LayoutInflater.from(Benefitthepeoplebill.this).inflate(R.layout.benefitthepeopletransactiondetaillayouttop, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, 300, 350, true);
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
        int offsetX = Math.abs(popupWindow2.getContentView().getMeasuredWidth() - mbenefitthepeopletransactiondetaildetailssecect.getWidth()) / 2;
        popupWindow2.showAsDropDown(mbenefitthepeopletransactiondetaildetailssecect, offsetX, 0, Gravity.BOTTOM);
    }
}
