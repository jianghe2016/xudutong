package com.xdt.xudutong.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.personcenterfragment.PersoncenterAddCard;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;

public class PopupWindowActivity extends Base2Activity {


    LinearLayout mFlMyContainer;

    private Handler showPopWindowHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Showdialogforsearchresultbad();
                    break;
                case 3:
                    Showdialogforsearchresultnobike();
                    break;
                case 4:
                    Showdialogforsearchresultbikestop();
                    break;
                case 5:
                    Showdialogforsearchresultnostartwork();
                    break;
                case 6:
                    Showdialogforcomein();
                    break;
                case 7:
                    showpopwindowtruename();
                    break;
                case 8:
                    showpopwindowaddcard();
                    break;
                case 9:
                    Showdialogfornocardstates();
                    break;
            }
        }
    };

    @Override
    public void setSteepStatusBar(boolean isSetStatusBar) {
        super.setSteepStatusBar(true);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getWindow().setStatusBarColor(Color.parseColor("#2673d7"));
        }
        setContentView(R.layout.activity_dialog);
        mFlMyContainer = (LinearLayout) findViewById(R.id.fl_my_container);
        initData();
    }

    private void initData() {
        String mflag = getIntent().getExtras().getString("flag");
        if ("2".equals(mflag)) {
            showPopWindowHandler.sendEmptyMessageDelayed(2,10);
//            Showdialogforsearchresultbad();
        } else if ("3".equals(mflag)) {
            showPopWindowHandler.sendEmptyMessageDelayed(3,10);
//            Showdialogforsearchresultnobike();
        } else if ("4".equals(mflag)) {
            showPopWindowHandler.sendEmptyMessageDelayed(4,10);
//            Showdialogforsearchresultbikestop();
        } else if("5".equals(mflag)){
            showPopWindowHandler.sendEmptyMessageDelayed(5,10);
//            Showdialogforsearchresultnostartwork();
        }else if("6".equals(mflag)){
            showPopWindowHandler.sendEmptyMessageDelayed(6,10);
        }else if("7".equals(mflag)){
            showPopWindowHandler.sendEmptyMessageDelayed(7,10);
        }else if("8".equals(mflag)){
            showPopWindowHandler.sendEmptyMessageDelayed(8,10);
        }else {
            showPopWindowHandler.sendEmptyMessageDelayed(9,10);
        }
    }

    //请实名认证的弹窗
    public void showpopwindowtruename() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbiketruenamedialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbiketruenamedialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbiketruenamedialogimg1);
        ImageView mlittlegreenbikeaddcarddialogcancle = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcancle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(false);
        mlittlegreenbikeaddcarddialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户点击消失，进行记录。
               finish();
            }
        });
        mlittlegreenbiketruenamedialogimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未认证
                Intent intent = new Intent(PopupWindowActivity.this, Personitemthree.class);
                startActivity(intent);
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                finish();
            }
        });
        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);

    }

    //登录弹窗
    private void Showdialogforcomein() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.littlegreenbikecomeindialog, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate.findViewById(R.id.littlegreeenbikestartcomein);
        LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
/*        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
        mpopupWindow1.setTouchable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        mpopupWindow1.setOutsideTouchable(false);
        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopupWindowActivity.this, Personuser_comein.class));
                finish();
            }
        });
        mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                finish();
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //车桩故障
    private void Showdialogforsearchresultbad() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbikesearchresultbad, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbadimg1);
        LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbaddissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow

        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbaddissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //无车
    private void Showdialogforsearchresultnobike() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbikesearchresultnobike, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbadimg1);
        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultnobike1);
//        LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbaddissmiss);
        LinearLayout mlittlegreenbikesearchresultbaddissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultnobikedissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mlittlegreenbikesearchresultbaddissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
    }

    //站点停用
    private void Showdialogforsearchresultbikestop() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbikesearchresultbikestop, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbikestopimg1);
        LinearLayout mlittlegreenbikesearchresultbikestopdissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbikestopdissmiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbikestopdissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //没有到营业时间
    private void Showdialogforsearchresultnostartwork() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbikesearchresultnostartwork, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ImageView mlittlegreeenbikestartcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikesearchresultbikenostartworkimg1);
        LinearLayout mlittlegreenbikesearchresultbikenostartworkdismiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikesearchresultbikenostartworkdismiss);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;

        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
        mlittlegreenbikesearchresultbikenostartworkdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //是否有小绿功能的判断
    private void Showdialogfornocardstates() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.littlegreenbikenocardstatesdialog, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreenbikesearchresultnoxdtcard = (ImageView) inflate.findViewById(R.id.littlegreenbikesearchresultnoxdtcard);
        LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
/*        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
        mpopupWindow1.setTouchable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        mpopupWindow1.setFocusable(false);
        //  mpopupWindow1.setOutsideTouchable(true);
        mpopupWindow1.setOutsideTouchable(false);
        mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                finish();
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //请绑卡的弹窗
    public void showpopwindowaddcard() {
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.littlegreenbikeaddcarddialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbikeaddcarddialogcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcomein);
        ImageView mlittlegreenbikeaddcarddialogcancle = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcancle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(false);
        mlittlegreenbikeaddcarddialogcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户点击消失，进行记录。
                finish();
            }
        });
        mlittlegreenbikeaddcarddialogcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未认证
                Intent intent = new Intent(PopupWindowActivity.this, PersoncenterAddCard.class);
                startActivity(intent);
                finish();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
              finish();
            }
        });
        popupWindow2.showAtLocation(mFlMyContainer, Gravity.CENTER, 0, 0);

    }   //卡故障的弹窗----卡挂失使用
}
