package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Administrator on 2017/5/23.
 */
//出行记录页面，选择普通公交卡或者许都通卡查询
public class Homebuttongroupbuttonsixtwo extends BaseActivity {
    private LinearLayout homebuttongroup_button61back1;
    private LinearLayout home_buttongroup_button62item1;
    private LinearLayout home_buttongroup_button62item2;
    private String startdate;
    private String enddate;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button62);
    }

    @Override
    public void initView() {
        homebuttongroup_button61back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button62back);
        home_buttongroup_button62item1 = (LinearLayout) findViewById(R.id.home_buttongroup_button62item1);
        home_buttongroup_button62item2 = (LinearLayout) findViewById(R.id.home_buttongroup_button62item2);
        initData();
    }

    private void initData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
        startdate = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        enddate = sDateFormat2.format(new java.util.Date());
        final Intent intent = getIntent();
        final int xdtcardstates = intent.getIntExtra("xdtcardstates", 0);
        final boolean personssecretstates = intent.getBooleanExtra("personssecretstates", true);
        final int realrestates = intent.getIntExtra("realrestates", -1);
        final String xdtcardNo = intent.getStringExtra("xdtcardNo");
        final String realIdnumber = intent.getStringExtra("realIdnumber");
        //普通公交卡查询
        home_buttongroup_button62item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonsixtwonexttwo.class);
                    intent.putExtra("personssecretstates", personssecretstates);
                    intent.putExtra("realrestates", realrestates);
                    intent.putExtra("gongjiaointentnext", "乘车记录普通公交卡查询");
                    startActivity(intent);
                }
            }
        });
        //许都通卡查询，乘车记录（走消费记录接口）
        home_buttongroup_button62item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    if (personssecretstates == true) {
                        if (realrestates == 1) {
                            switch (xdtcardstates) {
                                case 0://无卡
                                    Intent intent0 = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonone.class);
                                    intent0.putExtra("xdtcardstates", xdtcardstates);
                                    //表示公交出行页面的进入许都通卡乘车记录
                                    intent0.putExtra("xdtcardsaledetailstopview", "乘车记录");
                                    startActivity(intent0);
                                    break;
                                case 1://正常
                                    Intent intent1 = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttononenext.class);
                                    intent1.putExtra("zhanghao", xdtcardNo);
                                    intent1.putExtra("realIdnumber", realIdnumber);
                                    intent1.putExtra("startdate11", startdate);
                                    intent1.putExtra("endedata11", enddate);
                                    intent1.putExtra("xdtcardstates", true);
                                    intent1.putExtra("personssecretstates", true);
                                    intent1.putExtra("xdtcardsaledetailstopview", "乘车记录");
                                    startActivity(intent1);
                                    break;
                                case 3://挂失
                                    ToastUtils.getInstance(Homebuttongroupbuttonsixtwo.this).showMessage("该账户处于挂失状态");
                                    break;
                                case 11://解绑
                                    Intent intent = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonone.class);
                                    //2表示公交出行页面的进入许都通卡乘车记录
                                    intent.putExtra("xdtcardsaledetailstopview", "乘车记录");
                                    startActivity(intent);
                                    break;
                                default:
                                    Intent intentnoral = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonone.class);
                                    //表示公交出行页面的进入许都通卡乘车记录
                                    intentnoral.putExtra("xdtcardsaledetailstopview", "乘车记录");
                                    startActivity(intentnoral);
                                    break;
                            }
                        } else {
                            Intent intent = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonone.class);
                            //表示公交出行页面的进入许都通卡乘车记录
                            intent.putExtra("xdtcardsaledetailstopview", "乘车记录");
                            startActivity(intent);

                        }
                    } else {
                        Intent intent = new Intent(Homebuttongroupbuttonsixtwo.this, Homebuttongroupbuttonone.class);
                        //表示公交出行页面的进入许都通卡乘车记录
                        intent.putExtra("xdtcardsaledetailstopview", "乘车记录");
                        startActivity(intent);

                    }
                }
            }
        });
        homebuttongroup_button61back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
    }
}
