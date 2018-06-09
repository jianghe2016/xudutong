package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.ToastUtils;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

public class HomeshiyeweiFragment extends Fragment {

    private TextView home_button_danweiinfo_shiyequery1;
    private View view;
    private EditText home_button_danweiinfo_shiyetext1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shiyefragment, container, false);
        initView();
        return view;
    }

    private void initView() {
        home_button_danweiinfo_shiyetext1 = (EditText) view.findViewById(R.id.home_button_danweiinfo_shiyetext);
        home_button_danweiinfo_shiyequery1 = (TextView) view.findViewById(R.id.home_button_danweiinfo_shiyequery);
        initData();
    }

    private void initData() {
        home_button_danweiinfo_shiyequery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String danwei = home_button_danweiinfo_shiyetext1.getText().toString();
                if (!danwei.isEmpty()) {
                    Intent intent = new Intent(getContext(), Homecardgroupbuttondanweishiyeinfodetails.class);
                    intent.putExtra("shiyedanwei", danwei);
                    startActivity(intent);
                } else {
                    ToastUtils.getInstance(getContext()).showMessage("输入不能为空");
                }
            }
        });
    }
}
