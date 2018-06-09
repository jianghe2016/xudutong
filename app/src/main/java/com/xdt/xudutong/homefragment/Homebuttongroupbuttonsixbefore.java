package com.xdt.xudutong.homefragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;

/**
 * Created by Administrator on 2017/5/23.
 */
//线路查询，公交出行功能
public class Homebuttongroupbuttonsixbefore extends BaseActivity {
    private LinearLayout home_gongjiaonextactivity1;
    private LinearLayout home_cardyuenextactivity1;
    @Override
    public void setMyContentView() {
        setContentView(R.layout.home_buttongroup_button6before);
    }
    @Override
    public void initView() {
        final Intent intent = getIntent();
        int xdtcardstates = intent.getIntExtra("xdtcardstates", 0);
        boolean personssecretstates = intent.getBooleanExtra("personssecretstates", true);
        int realrestates = intent.getIntExtra("realrestates", -1);
        String xdtcardNo = intent.getStringExtra("xdtcardNo");
        String realIdnumber = intent.getStringExtra("realIdnumber");
        String realName = intent.getStringExtra("realName");
        LinearLayout homebuttongroup_button61back1 = (LinearLayout) findViewById(R.id.homebuttongroup_button61back);
        home_gongjiaonextactivity1 = (LinearLayout) findViewById(R.id.home_gongjiaonextactivity);
        home_cardyuenextactivity1 = (LinearLayout) findViewById(R.id.home_cardyuenextactivity);
        homebuttongroup_button61back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData(personssecretstates,realrestates,xdtcardstates,xdtcardNo,realIdnumber,realName);
    }

    private void initData(final boolean personssecretstates, final int realrestates, final int xdtcardstates, final String xdtcardNo, final String realIdnumber, final String realName) {
        home_gongjiaonextactivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonsixbefore.this, Homebuttongroupbuttonsix.class);
                    startActivity(intent);
                }
            }
        });
        home_cardyuenextactivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Homebuttongroupbuttonsixbefore.this, Homebuttongroupbuttonsixtwo.class);
                    intent.putExtra("personssecretstates", personssecretstates);
                    intent.putExtra("realrestates", realrestates);
                    intent.putExtra("xdtcardstates", xdtcardstates);
                    intent.putExtra("xdtcardNo", xdtcardNo);
                    intent.putExtra("realIdnumber", realIdnumber);
                    startActivity(intent);
                }

            }
        });
    }
}
