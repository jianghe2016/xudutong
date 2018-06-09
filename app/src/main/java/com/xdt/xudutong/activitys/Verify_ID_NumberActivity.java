package com.xdt.xudutong.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;

public class Verify_ID_NumberActivity extends Base2Activity implements View.OnClickListener{

    private LinearLayout mBack;
    private TextView mTv_title;
    private EditText mEt_id_num;
    private Button mBtn_next;
    private String mIdNum;
    private Intent mIntent;
    private String mPayPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__id__number);
        initView();
    }


    private void initView() {
        mPayPsd = SpUtils.getParam(this,"paypsd","");
        mIdNum = SpUtils.getParam(this,"id_num","");
        mBack = (LinearLayout) findViewById(R.id.back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_title.setText("");
        mEt_id_num = (EditText) findViewById(R.id.et_id_number);
        mBtn_next = (Button) findViewById(R.id.btn_next);
        mBack.setOnClickListener(this);
        mBtn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_next:
                    if (mEt_id_num.getText().toString().toUpperCase().equals(mIdNum)){
                        ToastUtils.getInstance(this).showMessage("验证成功");
//                        if ("1".equals(mPayPsd)){
//                            mIntent= new Intent().setClass(this,DealPsdActivity.class);
//                        }else {
                            mIntent = new Intent().setClass(this,SettingNewPsdActivity.class);
                        mIntent.putExtra("mark","2");
//                        }
                        startActivity(mIntent);
                    }else {
                        ToastUtils.getInstance(this).showMessage("验证失败");
                        return;
                    }
                break;
        }
    }
}
