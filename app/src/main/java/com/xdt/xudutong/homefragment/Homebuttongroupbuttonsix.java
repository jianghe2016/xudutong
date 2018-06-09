package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Homebuttongroupbuttonsix extends BaseActivity {
    private LinearLayout busquerycity1;
    private LinearLayout busqueryload1;
    private TextView buttongroup_button6_cityselect1;
    private EditText home_buttongroupbuttonsixlinselect1;
    private String cityname1;
    private LinearLayout homebuttongroup_button6back1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button6);
    }
    @Override
    public void initView() {
        homebuttongroup_button6back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button6back);
        home_buttongroupbuttonsixlinselect1 = (EditText) findViewById(R.id.home_buttongroupbuttonsixlinselect);
        busquerycity1 = (LinearLayout) findViewById(R.id.busquerycity);
        busqueryload1 = (LinearLayout) findViewById(R.id.busqueryload);
        buttongroup_button6_cityselect1 = (TextView) findViewById(R.id.buttongroup_button6_cityselect);
        initData();
    }

    private void initData() {
        homebuttongroup_button6back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        cityname1 = SpUtils.getParam(getApplicationContext(), "cityname", "许昌");
        buttongroup_button6_cityselect1.setText(cityname1);
        //查询城市按钮
        busquerycity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonsix.this, Homebuttongroupbuttonsixquerycity.class);
                    startActivityForResult(intent, 111);
                }
            }
        });
        //查询公交路线按钮
        busqueryload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String buslineselect = home_buttongroupbuttonsixlinselect1.getText().toString().trim();
                if (cityname1 == null || cityname1.isEmpty()) {
                    ToastUtils.getInstance(Homebuttongroupbuttonsix.this).showMessage("请选择城市");
                } else {
                    if (buslineselect != null && !buslineselect.isEmpty()) {
                        Intent intent1 = new Intent(Homebuttongroupbuttonsix.this, Homebuttongroupbuttonsixquerybusline.class);
                        intent1.putExtra("buslinenumber", buslineselect);
                        startActivity(intent1);
                    } else {
                        ToastUtils.getInstance(Homebuttongroupbuttonsix.this).showMessage("请输入正确的公交路线");
                    }
                }
            }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == 110) {
            String cityname = data.getStringExtra("cityname");
            buttongroup_button6_cityselect1.setText(cityname);
        }
    }
}
