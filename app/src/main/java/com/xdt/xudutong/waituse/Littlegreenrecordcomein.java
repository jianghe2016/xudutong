package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ToastUtils;
/**
 * Created by Administrator on 2018\1\16 0016.
 */

public class Littlegreenrecordcomein extends BaseActivity {
    @Override
    public void initView() {
        Intent intent = getIntent();
        final int userid = intent.getIntExtra("userid",0);
        LinearLayout mlittlegreemrecordcomeinback = (LinearLayout) findViewById(R.id.littlegreemrecordcomeinback);
        final EditText mlittlegreemrecordcomeineduittext = (EditText) findViewById(R.id.littlegreemrecordcomeineduittext);
        TextView mlittlegreemrecordcomeinsubmit = (TextView) findViewById(R.id.littlegreemrecordcomeinsubmit);
        mlittlegreemrecordcomeinback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mlittlegreemrecordcomeinsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xdtcardnumber = mlittlegreemrecordcomeineduittext.getText().toString();
                if (!TextUtils.isEmpty(xdtcardnumber)){
                    Intent intent = new Intent(Littlegreenrecordcomein.this,LittlegreenbikeRecord.class);
                    intent.putExtra("xdtcardnumber",xdtcardnumber);
                    startActivity(intent);
                }else{
                    ToastUtils.getInstance(Littlegreenrecordcomein.this).showMessage("请输入许都通卡号");
                }

            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreenrecordcomein2);
    }
}
