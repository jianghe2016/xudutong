package com.xdt.xudutong.huiminbao;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;

/**
 * Created by Administrator on 2018\2\24 0024.
 */

public class EditNameDialogFragmenttwo extends DialogFragment {
    private View view;

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_huiminbaobuyin, container);
        return view;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        view = inflater.inflate(R.layout.fragment_huiminbaobuyin, container);
        Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.x = 0;
        lp.y = 0;
        lp.width = lp.MATCH_PARENT;
        lp.height = 600;
        lp.alpha = 0.7f;
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        dialogWindow.setAttributes(lp);
        initview();
        return view;
    }

    private void initview() {
        LinearLayout mhome_huiminbaoselectcard_buttom_popwindowitem4 = (LinearLayout) view.findViewById(R.id.home_huiminbaoselectcard_buttom_popwindowitem4);
        mhome_huiminbaoselectcard_buttom_popwindowitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                EditNameDialogFragmenttwo df = new EditNameDialogFragmenttwo();
                Bundle bundle = new Bundle();
                bundle.putString("name", "1111");
                df.setArguments(bundle);
              //  ft.replace(R.id.content_container, df);
                ft.addToBackStack(null);
                ft.commit();*/
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static EditNameDialogFragmenttwo newInstance() {
        Bundle args = new Bundle();
        EditNameDialogFragmenttwo fragment = new EditNameDialogFragmenttwo();
        fragment.setArguments(args);
        return fragment;
    }
    public static EditNameDialogFragmenttwo newInstance(int layoutId) {
        Bundle bundle = new Bundle();//把所有需要传递的数据都放在Bundle中
        bundle.putInt("layoutId", layoutId);
        EditNameDialogFragmenttwo fragment = new EditNameDialogFragmenttwo();
        fragment.setArguments(bundle);//通过setArguments把Bundle传递过去
        return fragment;
    }

}
