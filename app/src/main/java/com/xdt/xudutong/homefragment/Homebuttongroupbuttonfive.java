package com.xdt.xudutong.homefragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/5/23.
 */

public class Homebuttongroupbuttonfive extends BaseActivity {

    private EditText startoff1;
    private EditText endoff1;
    private TextView startoffdate1;
    private TextView next51;
    private int year, monthOfYear, dayOfMonth;
    private String accuratetarinqueryone;
    private String accuratetarinquerytwo;
    private LinearLayout homebuttongroup_button5back1;
    private LinearLayout startoffdateselect1;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button5);
    }
    @Override
    public void initView() {
        homebuttongroup_button5back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button5back);
        startoffdateselect1 = (LinearLayout) findViewById(R.id.startoffdateselect);
        accuratetarinqueryone = SpUtils.getParam(getApplicationContext(), "accuratetarinqueryonetext", "");
        accuratetarinquerytwo = SpUtils.getParam(getApplicationContext(), "accuratetarinquerytwotext", "");
        startoff1 = (EditText) findViewById(R.id.startoff);
        endoff1 = (EditText) findViewById(R.id.endoff);
        //给起始地点  结束地点赋值
        startoff1.setText(accuratetarinqueryone);
        endoff1.setText(accuratetarinquerytwo);

        startoffdate1 = (TextView) findViewById(R.id.startoffdate);
        next51 = (TextView) findViewById(R.id.person_next51);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int i = monthOfYear + 1;
        Serializable serializable = (dayOfMonth + 1) < 10 ? "0" + dayOfMonth : dayOfMonth;
        Serializable serializable2 = i< 10 ? "0" +  i: i;
        startoffdate1.setText(year + "-" + serializable2 + "-" + serializable);
        initData();
    }

    private void initData() {
        homebuttongroup_button5back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }

            }
        });
        startoff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonfive.this, Homebuttongroupbuttonfivenextone.class);
                    startActivityForResult(intent, 12);
                }

            }
        });
        endoff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonfive.this, Homebuttongroupbuttonfivenexttwo.class);
                    startActivityForResult(intent, 13);
                }
            }
        });
        startoffdateselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    /**
                     * 实例化一个DatePickerDialog的对象
                     * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                     */
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Homebuttongroupbuttonfive.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                              int day) {
                            int mYear = year;
                            int mMonth = month;
                            int mDay = day;
                            startoffdate1.setText(new StringBuilder().append(mYear).append("-")
                                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                                    .append((mDay < 10) ? "0" + mDay : mDay));

                        }
                    }, year, monthOfYear, dayOfMonth);
                    datePickerDialog.show();
                }
            }
        });
        next51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    String startoffdate1txt = startoffdate1.getText().toString();
                    String accuratetarinqueryone = startoff1.getText().toString();
                    String accuratetarinquerytwo = endoff1.getText().toString();
                    if (!accuratetarinqueryone.isEmpty() && !accuratetarinquerytwo.isEmpty()) {
                        if (!startoffdate1txt.isEmpty()) {
                            if (accuratetarinqueryone.equals(accuratetarinquerytwo)) {
                                ToastUtils.getInstance(Homebuttongroupbuttonfive.this).showMessage("出发地和目的地不能相同");
                            } else {
                                Intent intent = new Intent(Homebuttongroupbuttonfive.this, Homebuttongroupbuttonfivequeryresult.class);
                                intent.putExtra("trainstartdata", startoffdate1txt);
                                startActivity(intent);
                            }

                        } else {
                            ToastUtils.getInstance(Homebuttongroupbuttonfive.this).showMessage("请选择正确的出发日期");
                        }
                    } else {
                        ToastUtils.getInstance(Homebuttongroupbuttonfive.this).showMessage("请输入地点进行查询");
                    }

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 12:
                if (resultCode == 14) {

                    String accuratetarinqueryonetext = data.getStringExtra("accuratetarinqueryonetext");
                    startoff1.setText(accuratetarinqueryonetext);
                }
                break;
            case 13:
                if (resultCode == 15) {
                    String accuratetarinquerytwotext = data.getStringExtra("accuratetarinquerytwotext");
                    endoff1.setText(accuratetarinquerytwotext);
                }
                break;
        }


    }
}
