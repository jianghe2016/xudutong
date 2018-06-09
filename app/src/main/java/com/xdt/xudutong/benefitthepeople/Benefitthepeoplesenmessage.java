package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.TimerCountshape;

/**
 * Created by Administrator on 2018\2\3 0003.
 */
//发送短信注销页面的功能
public class Benefitthepeoplesenmessage extends BaseActivity {

    private LinearLayout mbenefitthepeoplesenmessagelayout1;
    private Button mbenefitthepeoplesenmessagelayoutsendmessage;

    @Override
    public void initView() {
        mbenefitthepeoplesenmessagelayout1 = (LinearLayout) findViewById(R.id.benefitthepeoplesenmessagelayout1);
        LinearLayout mBenefitthepeoplesenmessageback = (LinearLayout) findViewById(R.id.Benefitthepeoplesenmessageback);
        mbenefitthepeoplesenmessagelayoutsendmessage = (Button) findViewById(R.id.benefitthepeoplesenmessagelayoutsendmessage);
        Button benefitthepeoplesenmessagelayoutsubmit = (Button) findViewById(R.id.benefitthepeoplesenmessagelayoutsubmit);
        mBenefitthepeoplesenmessageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenefitthepeoplesenmessagelayoutsendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerCountshape timer = new TimerCountshape(60000, 1000, mbenefitthepeoplesenmessagelayoutsendmessage,Benefitthepeoplesenmessage.this);
                timer.start();
            }
        });
        benefitthepeoplesenmessagelayoutsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmydiaog();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeoplesenmessagelayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private void showmydiaog() {
        View inflate = LinearLayout.inflate(Benefitthepeoplesenmessage.this, R.layout.benfitthepeopleaddiccarddialog1, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        final Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.7f;
        getWindow().setAttributes(attributes);
        popupWindow.showAtLocation(mbenefitthepeoplesenmessagelayout1, Gravity.CENTER, 0, 0);
        TextView mbenfitthepeopleaddiccarddialog1_text = (TextView) inflate.findViewById(R.id.benfitthepeopleaddiccarddialog1_text);
        Button mbenfitthepeopleaddiccarddialog1_canle = (Button) inflate.findViewById(R.id.benfitthepeopleaddiccarddialog1_canle);
        Button mbenfitthepeopleaddiccarddialog1_delect = (Button) inflate.findViewById(R.id.benfitthepeopleaddiccarddialog1_delect);
        mbenfitthepeopleaddiccarddialog1_text.setText("请确保账户余额为0，并且理财产品份额均已赎回。注销后将无法进行部分业务使用，您确定要注销账户吗？");
        mbenfitthepeopleaddiccarddialog1_canle.setBackground(getResources().getDrawable(R.drawable.shape_edittext2));
        mbenfitthepeopleaddiccarddialog1_delect.setBackground(getResources().getDrawable(R.drawable.shape_edittext2_1));
        mbenfitthepeopleaddiccarddialog1_delect.setText("注销");
        mbenfitthepeopleaddiccarddialog1_delect.setTextColor(getResources().getColor(R.color.benefitthepeoplesenmessagelayoutaround));
        mbenfitthepeopleaddiccarddialog1_canle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams attributes1 = window.getAttributes();
                attributes1.alpha = 1f;
                window.setAttributes(attributes1);
                popupWindow.dismiss();
            }
        });
        mbenfitthepeopleaddiccarddialog1_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benefitthepeoplesenmessage.this, Benefitthepeoplelogout.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
