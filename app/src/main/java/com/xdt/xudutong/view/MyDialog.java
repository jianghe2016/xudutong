package com.xdt.xudutong.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2017\10\11 0011.
 */

public class MyDialog extends Dialog {
    private LinearLayout positiveButton;


    public MyDialog(Context context) {
        super(context, R.style.my_dialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.person_fankuidialog, null);  //通过LayoutInflater获取布局
        positiveButton = (LinearLayout) view.findViewById(R.id.person_fankui_dialog_cancel);
        setContentView(view);  //设置view
    }

    //确定按钮监听
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }

}
