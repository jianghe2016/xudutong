package com.xdt.xudutong.huiminbao;

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

/**
 * Created by Administrator on 2018\2\12 0012.
 */

public class Funreturnsdetailed extends BaseActivity {

    private LinearLayout mfunreturnsdetailedlayoutbacktopview1;
    private PopupWindow popupWindow2;
    private TextView mfunreturnsdetailedlayoutlayout;
    private ImageView mfunreturnsdetailedlayoutbacktopviewimg11;

    @Override
    public void initView() {
        LinearLayout mfunreturnsdetailedlayoutback = (LinearLayout) findViewById(R.id.funreturnsdetailedlayoutback);
        mfunreturnsdetailedlayoutlayout = (TextView) findViewById(R.id.funreturnsdetailedlayoutlayout);
        mfunreturnsdetailedlayoutbacktopview1 = (LinearLayout) findViewById(R.id.funreturnsdetailedlayoutbacktopview1);
        final ImageView funreturnsdetailedlayoutbacktopviewimg1 = (ImageView) findViewById(R.id.funreturnsdetailedlayoutbacktopviewimg1);
        mfunreturnsdetailedlayoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mfunreturnsdetailedlayoutbacktopview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showbuttomdialog();

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.funreturnsdetailedlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private void showbuttomdialog() {
        View inflate2 = LayoutInflater.from(Funreturnsdetailed.this).inflate(R.layout.funreturnsdetailedlayoutpopwindow, null);
        popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow2.setAnimationStyle(R.style.AnimationFade);
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
        //popupWindow2.showAtLocation(mfunreturnsdetailedlayoutbacktopview1, Gravity.BOTTOM, 0, 0);
        // popupWindow2.showAtLocation(mfunreturnsdetailedlayoutbacktopview1, Gravity.BOTTOM, 0, 0);
        popupWindow2.showAsDropDown(mfunreturnsdetailedlayoutlayout, Gravity.TOP, 0, 0);
        //起始日期，结束日期，重置，完成四个按钮的逻辑
        TextView mfunreturnsdetailedlayoutpopwindowitem1 = (TextView) inflate2.findViewById(R.id.funreturnsdetailedlayoutpopwindowitem1);
        mfunreturnsdetailedlayoutbacktopviewimg11 = (ImageView) inflate2.findViewById(R.id.funreturnsdetailedlayoutbacktopviewimg11);
        TextView mfunreturnsdetailedlayoutpopwindowitem2 = (TextView) inflate2.findViewById(R.id.funreturnsdetailedlayoutpopwindowitem2);
        TextView mfunreturnsdetailedlayoutpopwindowitem3 = (TextView) inflate2.findViewById(R.id.funreturnsdetailedlayoutpopwindowitem3);
        TextView mfunreturnsdetailedlayoutpopwindowitem4 = (TextView) inflate2.findViewById(R.id.funreturnsdetailedlayoutpopwindowitem4);
        mfunreturnsdetailedlayoutpopwindowitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        mfunreturnsdetailedlayoutpopwindowitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        mfunreturnsdetailedlayoutpopwindowitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        mfunreturnsdetailedlayoutpopwindowitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });

    }
}
