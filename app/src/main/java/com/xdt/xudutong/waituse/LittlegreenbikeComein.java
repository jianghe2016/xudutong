package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.CitygetBalance;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonsevenxiaolv;
import com.xdt.xudutong.personcenterfragment.PersoncenterAddCard;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.tianjian.zxingg.SecondActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.ApplicationController.getContext;
import static com.xdt.xudutong.utils.Finaltext.PERSONCENTERTRUENAMEREALSTATES2;

/**
 * Created by Administrator on 2018\1\3 0003.
 */
//小绿骑行登录页面
public class LittlegreenbikeComein extends BaseActivity {
    private LinearLayout mlittlegreembikecomeinall;
    private String token1;
    private String token2;
    private String xdtcardNo;
    private String volleygetidcardNo;
    private int cardStatus;
    private int userid;
    private int real;

    private LinearLayout mlittlegreembikecomeinallitem;
    private TextView mlittlegreembikecomeinallitemtext;
    private LinearLayout mlittlegreembikecomeinallitemimgcanle;
    private ImageView mlittlegreenbikecomeinsaoyisao;
    private boolean loadingstates;
    private String greenStatus;
    private Handler showPopWindowHandler;
    private boolean personssecretstates;
    private LinearLayout mLlProblem;
    private TextView mTvXdt;
    private TextView mTvGlcenter;
    private TextView mTvProplem;

    @Override
    public void initView() {
        loadingstates = SpUtils.getParam(getApplicationContext(), "loadingstate", false);
        final Intent intent = getIntent();
        personssecretstates = intent.getBooleanExtra("personssecretstates", false);
        token1 = intent.getStringExtra("token1");
        token2 = intent.getStringExtra("token2");
        xdtcardNo = intent.getStringExtra("xdtcardNo");
        volleygetidcardNo = intent.getStringExtra("volleygetidcardNo");
        cardStatus = intent.getIntExtra("cardStatus", 0);
        userid = intent.getIntExtra("userid", 0);
        real = intent.getIntExtra("real", 5);
        greenStatus = intent.getStringExtra("greenStatus");
        mLlProblem = (LinearLayout) findViewById(R.id.ll_problem);
        mTvXdt = (TextView) findViewById(R.id.tv_xudoutong);
        mTvGlcenter = (TextView) findViewById(R.id.tv_guanlicenter);
        mTvGlcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0374-3332929"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        mTvXdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0374-7021111"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mLlProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(LittlegreenbikeComein.this,ProblemsActivity.class);
                startActivity(intent);
            }
        });
        mTvProplem = (TextView) findViewById(R.id.tv_problem);
        mTvProplem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(LittlegreenbikeComein.this,ProblemsActivity.class);
                startActivity(intent);
            }
        });

        mlittlegreenbikecomeinsaoyisao = (ImageView) findViewById(R.id.littlegreenbikecomeinsaoyisao);
        //实名认证与否的上部条目
        mlittlegreembikecomeinallitem = (LinearLayout) findViewById(R.id.littlegreembikecomeinallitem);
        mlittlegreembikecomeinallitemtext = (TextView) findViewById(R.id.littlegreembikecomeinallitemtext);
        mlittlegreembikecomeinallitemimgcanle = (LinearLayout) findViewById(R.id.littlegreembikecomeinallitemimgcanle);
        mlittlegreembikecomeinall = (LinearLayout) findViewById(R.id.littlegreembikecomeinall);
        LinearLayout mlittlegreembikecomeinback = (LinearLayout) findViewById(R.id.littlegreembikecomeinback);
        LinearLayout mlittlegreenbikecomeinlinear1 = (LinearLayout) findViewById(R.id.littlegreenbikecomeinlinear1);
        LinearLayout mlittlegreenbikecomeinlinear2 = (LinearLayout) findViewById(R.id.littlegreenbikecomeinlinear2);
        LinearLayout mlittlegreenbikecomeinlinear3 = (LinearLayout) findViewById(R.id.littlegreenbikecomeinlinear3);
        showPopWindowHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        //登录弹窗
                        Showdialogforcomein();
                        break;
                    case 1:
                        //是否有小绿功能
                        Showdialogfornocardstates();
                        break;
                    case 2:
                        //绑卡弹窗
                        showpopwindowaddcard();
                        break;
                    case 3:
                        //认证弹窗
                        showpopwindowtruename(0);
                        break;
                    case 4:
                        //正在骑行弹窗
                        ShowVolleyRequestforxiaolv(0);
                        break;
                    case 5:
                        //正在认证
                        showpopwindowtruename(1);
                        break;
                    case 6:
                        //认证失败弹窗
                        showpopwindowtruename(2);
                        break;
                    case 7:
                        //点击扫码按钮
                        ShowVolleyRequestforxiaolv(1);
                        break;
                    case 8:
                        //卡故障的弹窗
                        showpopwindowcardproblem();
                        break;
                }
            }
        };
        //请求是否有未支付的订单
        //该逻辑，当该卡余额不足时，有缺陷，会发生   未支付和余额不足  弹窗同时出现
        if (loadingstates == true) {
            showloadingnext();
        } else {
            //登录弹窗
            showPopWindowHandler.sendEmptyMessageDelayed(0, 10);
        }

        mlittlegreenbikecomeinsaoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadingstates == true) {

                    if (real == -1) {
                        //未认证
                        showPopWindowHandler.sendEmptyMessageDelayed(3, 10);
                    } else if (real == 0) {
                        //正在认证
                        showPopWindowHandler.sendEmptyMessageDelayed(5, 10);
                    } else if (real == 1) {
                        //已认证
                        if (cardStatus == 0) {
                            //无卡
                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                        } else if (cardStatus == 1) {
                            if (!TextUtils.isEmpty(greenStatus) && greenStatus.equals("1")) {
                                //正常
                                showPopWindowHandler.sendEmptyMessageDelayed(7, 10);
                            } else {
                                showPopWindowHandler.sendEmptyMessageDelayed(1, 10);
                            }

                        } else if (cardStatus == 3) {
                            //挂失
                            showPopWindowHandler.sendEmptyMessageDelayed(8, 10);
                        } else if (cardStatus == 11) {
                            //解绑
                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                        }

                    } else {
                        //认证失败
                        showPopWindowHandler.sendEmptyMessageDelayed(6, 10);
                    }

                } else {
                    showPopWindowHandler.sendEmptyMessageDelayed(0, 10);
                }


            }
        });


        mlittlegreembikecomeinback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mlittlegreenbikecomeinlinear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LittlegreenbikeComein.this, Homecardgroupbuttonsevenxiaolv.class);
                startActivity(intent);
            }
        });
        mlittlegreenbikecomeinlinear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.getInstance(LittlegreenbikeComein.this).showMessage("此功能敬请期待");
                if (!"".equals(xdtcardNo) && xdtcardNo != null){
                    Intent intent = new Intent(LittlegreenbikeComein.this, XIaoLvExceptionOrderActivity.class);
                    intent.putExtra("xdtcardNo",xdtcardNo);
                    startActivity(intent);
                }else {
                    showpopwindowaddcard();
                }
            }
        });
        mlittlegreenbikecomeinlinear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadingstates == true) {
                    if (real == -1) {
                        //未认证
                        showPopWindowHandler.sendEmptyMessageDelayed(3, 10);
                    } else if (real == 0) {
                        //正在认证
                        showPopWindowHandler.sendEmptyMessageDelayed(5, 10);
                    } else if (real == 1) {
                        //已认证
                        if (cardStatus == 0) {
                            //无卡
                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                        } else if (cardStatus == 1) {
                            if (!TextUtils.isEmpty(greenStatus) && greenStatus.equals("1")) {
                                //正常
                                //如果开启免密状态下
                                if (personssecretstates == true) {
                                    Intent intent = new Intent(LittlegreenbikeComein.this, LittlegreenbikeRecord.class);
                                    intent.putExtra("xdtcardnumber", xdtcardNo);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(LittlegreenbikeComein.this, Littlegreenrecordcomein.class);
                                    intent.putExtra("userid", userid);
                                    startActivity(intent);
                                }
                            } else {
                                showPopWindowHandler.sendEmptyMessageDelayed(1, 10);
                            }

                        } else if (cardStatus == 3) {
                            //挂失
                            showPopWindowHandler.sendEmptyMessageDelayed(8, 10);
                        } else if (cardStatus == 11) {
                            //解绑
                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                        }

                    } else {
                        //认证失败
                        showPopWindowHandler.sendEmptyMessageDelayed(6, 10);
                    }
                } else {
                    showPopWindowHandler.sendEmptyMessageDelayed(0, 10);
                }
            }
        });
        mlittlegreembikecomeinallitemimgcanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlittlegreembikecomeinallitem.setVisibility(View.GONE);
            }
        });


    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.TRUENAMEFLAG:
                Object data5 = event.data;
                Boolean aBoolean = Boolean.valueOf(data5 + "");
                if (aBoolean == true) {
                    mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                }
                break;


        }
    }
    //余额不足
    private void Showdialogfornomoney() {
        View inflate2 = LayoutInflater.from(LittlegreenbikeComein.this).inflate(R.layout.littlegreenbikenomoneydialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
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
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);

    }

    //未支付
    private void Showdialogforwaitpaymoney() {
        View inflate2 = LayoutInflater.from(LittlegreenbikeComein.this).inflate(R.layout.littlegreenbikewaitpaymoneydialog, null);
        PopupWindow popupWindow2 = new PopupWindow(inflate2, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        getWindow().setAttributes(attributes);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);
    }

    //登录弹窗
    private void Showdialogforcomein() {
        View inflate = LayoutInflater.from(LittlegreenbikeComein.this).inflate(R.layout.littlegreenbikecomeindialog, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
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
                startActivity(new Intent(LittlegreenbikeComein.this, Personuser_comein.class));
            }
        });
        mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow1.dismiss();
            }
        });
    }

    //是否有小绿功能的判断
    private void Showdialogfornocardstates() {
        View inflate = LayoutInflater.from(LittlegreenbikeComein.this).inflate(R.layout.littlegreenbikenocardstatesdialog, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
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
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow1.dismiss();
            }
        });
    }


    //请绑卡的弹窗
    public void showpopwindowaddcard() {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbikeaddcarddialog, null);
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
                SpUtils.putParam(getContext(), PERSONCENTERTRUENAMEREALSTATES2, true);
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbikeaddcarddialogcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未认证
                Intent intent = new Intent(LittlegreenbikeComein.this, PersoncenterAddCard.class);
                startActivity(intent);
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);

    }   //卡故障的弹窗----卡挂失使用

    public void showpopwindowcardproblem() {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbikeaddcardproblemdialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbicardproblemdialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbicardproblemdialogimg1);
        ImageView mlittlegreenbicardproblemdialogcalle = (ImageView) inflate2.findViewById(R.id.littlegreenbicardproblemdialogcalle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(false);
        mlittlegreenbicardproblemdialogcalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });

        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);

    }

    //请实名认证的弹窗
    public void showpopwindowtruename(final int truenamestatesdialog) {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbiketruenamedialog, null);
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
                SpUtils.putParam(getContext(), PERSONCENTERTRUENAMEREALSTATES2, true);
                popupWindow2.dismiss();
            }
        });
        mlittlegreenbiketruenamedialogimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (truenamestatesdialog == 0) {
                    //未认证
                    Intent intent = new Intent(LittlegreenbikeComein.this, Personitemthree.class);
                    startActivity(intent);
                } else if (truenamestatesdialog == 1) {
                    //正在认证
                    Intent intent = new Intent(LittlegreenbikeComein.this, Personitemthreetwo.class);
                    startActivity(intent);
                } else {
                    //认证失败
                    Intent intent = new Intent(LittlegreenbikeComein.this, Personitemthreethreefail.class);
                    startActivity(intent);
                }

            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);

    }

    //有未归还小绿的弹窗
    public void showpopwindowkeepbike() {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbikekeebikedialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbikekeebikedialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbikekeebikedialogimg1);
        ImageView mlittlegreenbikekeebikedialogacncle = (ImageView) inflate2.findViewById(R.id.littlegreenbikekeebikedialogacncle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(false);
        mlittlegreenbikekeebikedialogacncle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户点击消失，进行记录。
                SpUtils.putParam(getContext(), PERSONCENTERTRUENAMEREALSTATES2, true);
                popupWindow2.dismiss();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 1f;
                getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mlittlegreembikecomeinall, Gravity.CENTER, 0, 0);

    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.littlegreenbikecomein);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void showloadingnext() {
        //如果登陆成功，则开始判断实名认证状态进行显示
        if (loadingstates == true) {
            switch (real) {
                case -1:
                    //未认证
                    showPopWindowHandler.sendEmptyMessageDelayed(3, 10);
                    mlittlegreembikecomeinallitemtext.setText("您还没有实名认证与绑卡，暂时不能用车，点击了解详情！");
                    mlittlegreembikecomeinallitem.setVisibility(View.VISIBLE);
                    mlittlegreembikecomeinallitemimgcanle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                        }
                    });
                    mlittlegreembikecomeinallitemtext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(LittlegreenbikeComein.this, PersonMessageInfoUserhelp.class));
                        }
                    });
                    break;
                case 0:
                    //正在认证
                    showPopWindowHandler.sendEmptyMessageDelayed(5, 10);
                    mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                    break;
                case 1:
                    //已认证
                    //0,无卡，1.正常，3.挂失，11.解绑
                    mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                    if (cardStatus == 0) {
                        //无卡
                        showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                        mlittlegreembikecomeinallitemtext.setText("您还没有实名认证与绑卡，暂时不能用车，点击了解详情！");
                        mlittlegreembikecomeinallitem.setVisibility(View.VISIBLE);
                        mlittlegreembikecomeinallitemtext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(LittlegreenbikeComein.this, PersonMessageInfoUserhelp.class));
                            }
                        });
                        mlittlegreembikecomeinallitemimgcanle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                            }
                        });
                    } else if (cardStatus == 1) {
                        if (!TextUtils.isEmpty(greenStatus) && greenStatus.equals("1")) {
                            //正常
                            showPopWindowHandler.sendEmptyMessageDelayed(4, 10);
                        } else {
                            showPopWindowHandler.sendEmptyMessageDelayed(1, 10);
                        }
                    } else if (cardStatus == 3) {
                        //挂失
                        showPopWindowHandler.sendEmptyMessageDelayed(8, 10);
                    } else if (cardStatus == 11) {
                        //解绑
                        showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
                    }
                    break;
                default:
                    //认证失败
                    LogUtil.d("认证失败", "认证失败");
                    mlittlegreembikecomeinallitem.setVisibility(View.GONE);
                    showPopWindowHandler.sendEmptyMessageDelayed(6, 10);
                    break;
            }

        }
    }

    //请求当前账户余额
    private void ShowVolleyrequestforgetBalance(String cityCardno, String substring2) {
        String url = ApiUrls.GETBALANCE;
        //Volley请求网络进行判断is);
        Map<String, String> params = new HashMap<String, String>();
        params.put("citycardno", cityCardno);
        params.put("searchPwd", substring2);
        LogUtil.d("citycardno=", cityCardno + "");
        LogUtil.d("searchPwd=", substring2 + "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        CitygetBalance citygetbalance = gson.fromJson(response.toString(), CitygetBalance.class);
                        int flag = citygetbalance.getFlag();
                        if (flag == 1) {
                            double getyue = citygetbalance.getContent().getData();
                            if (getyue <= 0) {
                                Showdialogfornomoney();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的当前账户余额失败数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }

    //请求小绿正在骑行的数据
    private void ShowVolleyRequestforxiaolv(final int states) {
        String urltruename = ApiUrls.WSBIKEAPPSCANCODECHECKCREDITRECORD;
        Map<String, String> params = new HashMap<>();
        params.put("cardfaceno", xdtcardNo);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("code");
                            if (code.equals("R00001")) {
                                if (states == 0) {
                                    //正常，啥都不显示
                                } else {
                                    //无不良记录
                                    Intent intent = new Intent(LittlegreenbikeComein.this, SecondActivity.class);
                                    intent.putExtra("flag","1");
                                    intent.putExtra("saomaxdt", 2);
                                    intent.putExtra("littlegreencardfaceno", xdtcardNo);
                                    intent.putExtra("userid", userid);
                                    intent.putExtra("cardStatus", cardStatus);
                                    startActivity(intent);
                                }

                            } else if (code.equals("R00002")) {
                                //用户正在骑行
                          /*      Intent intent = new Intent(LittlegreenbikeComein.this, HomeLittlegreentakinging.class);
                                intent.putExtra("xdtcardNo", xdtcardNo);
                                startActivity(intent);*/
                                showpopwindowkeepbike();
                            } else if (code.equals("R00003")) {
                                //用户有未付款项
                                Showdialogforwaitpaymoney();
                            } else if (code.equals("E00000")) {
                                //参数不能为空

                            } else if (code.equals("E00001")) {
                                //请求失败

                            } else if (code.equals("E00002")) {
                                //数据不存在
                                LogUtil.d("数据不存在=", "数据不存在");
                                // Showdialogfornocardstates//showpopwindowaddcard//showpopwindowtruename// showpopwindowkeepbike();
                                // showpopwindowtruename();
                            }
                        } catch (JSONException e) {
                            LogUtil.d("捕捉到了请求小绿正在骑行的异常=", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的数据为=", "请求小绿失败");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(getContext()).getRequestQueue().add(jsonRequest);
    }

}
