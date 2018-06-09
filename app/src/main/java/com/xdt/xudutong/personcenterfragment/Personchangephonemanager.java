package com.xdt.xudutong.personcenterfragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.view.LogUtil;

/**
 * Created by Administrator on 2017\12\7 0007.
 */
//手机号管理页面
public class Personchangephonemanager extends BaseActivity {
    private LinearLayout mperson_phonenumbermanager;
    private TextView mperson_changephone_text1;
    private String personmobile;
    private Handler showPopWindowHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showPopupWindow();//你自己定义的显示PopupWindow的方法
                    break;
            }
        }
    };


    @Override
    public void setMyContentView() {
        setContentView(R.layout.personchangephone);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        personmobile = SpUtils.getParam(getApplicationContext(),"personmobile","");
        final String personusername = intent.getStringExtra("personusername");

        mperson_phonenumbermanager = (LinearLayout) findViewById(R.id.person_phonenumbermanager);
        mperson_changephone_text1 = (TextView) findViewById(R.id.person_changephone_text1);
        LinearLayout mperson_changephone_back = (LinearLayout) findViewById(R.id.person_changephone_back);

        LinearLayout mperson_changephone_submit1 = (LinearLayout) findViewById(R.id.person_changephone_submit1);
        LinearLayout mperson_changephone_submit2 = (LinearLayout) findViewById(R.id.person_changephone_submit2);
        if (!TextUtils.isEmpty(personmobile)) {
            mperson_changephone_text1.setText("您的手机号：" + personmobile);
        }

        mperson_changephone_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        //手机验证码
        mperson_changephone_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personchangephonemanager.this, Personchangeoldphonecheckout.class);
                    intent.putExtra("personcheckchangephonetopview", "手机验证");
                    if (!TextUtils.isEmpty(personmobile)) {
                        intent.putExtra("personmobile", personmobile);
                    }
                    if (!TextUtils.isEmpty(personusername)) {
                        intent.putExtra("personusername", personusername);
                    }
                    startActivityForResult(intent, 481);
                }
            }
        });
        //登录密码验证
        mperson_changephone_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personchangephonemanager.this, PersonChangePhonenumberSecretcheck.class);
                    if (!TextUtils.isEmpty(personusername)) {
                        intent.putExtra("personusername", personusername);
                    }
                    LogUtil.d("usernameusername=", personusername);
                    startActivityForResult(intent, 481);
                }
            }
        });
    }

    private void showPopupWindow() {
        showDialog();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    //    String mnewphonenumber = intent.getStringExtra("newphonenumber");
        String mnewphonenumber = SpUtils.getParam(getApplicationContext(), "personmobile", "");
        mperson_changephone_text1.setText("您的手机号：" + mnewphonenumber);
        personmobile = mnewphonenumber;
        if (!TextUtils.isEmpty(mnewphonenumber)) {
            showPopWindowHandler.sendEmptyMessageDelayed(0, 10);
        }
    }

    private void showDialog() {
/*        AlertDialog.Builder builder = new AlertDialog.Builder(Personchangephonemanager.this, R.style.my_dialog);
        View view = LayoutInflater.from(Personchangephonemanager.this).inflate(R.layout.person_write_dialog, null);  //通过LayoutInflater获取布局
        TextView person_write_successdialog_text1 = (TextView) view.findViewById(R.id.person_write_successdialog_text1);
        TextView person_fankui_dialog_cancel1 = (TextView) view.findViewById(R.id.person_write_successdialog_button1);
        person_write_successdialog_text1.setText("修改成功");
        //dialog.setView(view,0, 0, 0, 0);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Personchangephonemanager.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        });
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();*/


        AlertDialog.Builder builder = new AlertDialog.Builder(Personchangephonemanager.this, R.style.my_dialog);
        final AlertDialog dialog = builder.create();
        View view = LayoutInflater.from(Personchangephonemanager.this).inflate(R.layout.person_write_dialog, null);  //通过LayoutInflater获取布局
        TextView person_write_successdialog_text1 = (TextView) view.findViewById(R.id.person_write_successdialog_text1);
        TextView person_fankui_dialog_cancel1 = (TextView) view.findViewById(R.id.person_write_successdialog_button1);
        person_write_successdialog_text1.setText("修改成功");
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        int layout_width = (int) ((widthPixels) * 0.5);// 宽度
        //  lp.height = (int) ((widthPixels) * 0.6);
        int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
        dialog.getWindow().setLayout(layout_width, 400); //对话框大小应根据屏幕大小调整
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0; // 新位置X坐标
        lp.y = -100; // 新位置Y坐标
        dialogWindow.setAttributes(lp);
        person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
