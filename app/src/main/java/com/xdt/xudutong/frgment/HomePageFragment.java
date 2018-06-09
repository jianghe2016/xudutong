package com.xdt.xudutong.frgment;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.activitys.DynamicPaymentActivity;
import com.xdt.xudutong.bean.PressselectPress;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.bean.WeathergetWeatherList;
import com.xdt.xudutong.homefragment.Home_loadingmore;
import com.xdt.xudutong.homefragment.Home_yanglaodetails;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttonfive;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttonsixbefore;
import com.xdt.xudutong.homefragment.Homebuttonphonemoneyinfo2;
import com.xdt.xudutong.homefragment.Homecardgroupbuttondanweiinfo;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonfive;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonfour;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonqiyeinfo;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonthree;
import com.xdt.xudutong.homefragment.Homecardgroupbuttonzhongkaoinfo;
import com.xdt.xudutong.homefragment.Homeweather;
import com.xdt.xudutong.homefragment.Homeyanglaoquery;
import com.xdt.xudutong.homefragment.Homezhihuiyiuliao;
import com.xdt.xudutong.locallifefragment.LocalNewsmore;
import com.xdt.xudutong.locallifefragment.LocalNewsmoreDetails;
import com.xdt.xudutong.personcenterfragment.PersoncenterAddCard;
import com.xdt.xudutong.personcenterfragment.Personitemfour;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.personcenterfragment.Personwritedetails;
import com.xdt.xudutong.tianjian.zxingg.SecondActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.ExampleUtil;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.utils.GlideImageLoader;
import com.xdt.xudutong.utils.GlideImageLoader2;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.LittlegreenbikeComein;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.xdt.xudutong.utils.Finaltext.LITTLEGREENTRUENAMEREALSTATES;
import static com.xdt.xudutong.utils.Finaltext.PERSONCENTERTRUENAMEREALSTATES2;
import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017/5/8.
 */

public class HomePageFragment extends BaseFragment {
    private View mview;
    private Banner mvp;
    private TextView textView4;


    private LinearLayout homebutton5;
    private LinearLayout homebutton6;
    private LinearLayout cardbutton1;
    private LinearLayout cardbutton2;
    private LinearLayout cardbutton3;
    private LinearLayout cardbutton5;
    private LinearLayout homeloadingmore;
    private TextView home_headview4right1;
    private NotificationManager manager;
    private int real = 5;
    private RecyclerView home_healthyxuchangrecycleview1;
    private String huji_code;
    private EditText home_healthyxuchangtopeduittext1;
    private Dialog dialog;
    private LinearLayout button7xiaplv;
    private LinearLayout home_button_qiyeinfo1;
    private LinearLayout home_button_zhongkaoinfo1;
    private LinearLayout home_button_danweiinfo1;
    private LinearLayout mhome_cardgroup_button3;
    private LinearLayout home_button_wuyequery1;

    private List<PressselectPress.ContentBean.DataBean> pressselectpressdata;
    private List<String> newsinfo = new ArrayList<>();
    private TextView mhome_headview4right2;
    private LinearLayout mhome_queryweather1;
    private LinearLayout home_saoma1;
    private LinearLayout home_fukuan1;
    private LinearLayout home_kabao1;
    private LinearLayout home_button_watermoneyinfo1;
    private LinearLayout home_button_phonemoneyinfo1;
    private LinearLayout home_button_lifejiaofeimoreinfo1;
    private String token1;
    private String token2;
    private String token3;
    private LinearLayout mhome_button_guahaojiuzhen;
    private boolean loadingstate = false;
    private LinearLayout mhome_button_guahaojiuzhenjilu;
    private LinearLayout mhome_huiminbao;
    private boolean personssecretstates = false;
    private String homeyuetext;
    private LinearLayout mhome_cardgroup_button6test0;
    private ImageView home_news_morenext1;
    private int cardStatus;
    private String requestusername;
    private String realName;
    private String realIdnumber;
    private String xdtcardNo;
    private int id;
    private boolean hasGotToken = false;
    private FrameLayout mhomepager;
    private String greenStatus;
    private Handler showPopWindowHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_homepager1, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setHasOptionsMenu(true);
        manager = (NotificationManager) getContext().getSystemService
                (NOTIFICATION_SERVICE);
        LogUtil.e("*****************************");
        initMyView();
        initMyData();
        LogUtil.e("++++++++++++++++++++++++++");
        return mview;
    }
//小绿弹窗开始
private void Showdialogforcomein() {
    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.littlegreenbikecomeindialog, null);
    final PopupWindow mpopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
    ImageView mlittlegreeenbikestartcomein = (ImageView) inflate.findViewById(R.id.littlegreeenbikestartcomein);
    LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
    // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
/*        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
    mpopupWindow1.setTouchable(true);
    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
    lp.alpha = 0.7f;
    //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    getActivity().getWindow().setAttributes(lp);
    mpopupWindow1.setOutsideTouchable(false);
    mlittlegreeenbikestartcomein.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), Personuser_comein.class));
        }
    });
    mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

        @Override
        public void onDismiss() {
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.alpha = 1f;
            getActivity().getWindow().setAttributes(lp);
        }
    });
    //显示PopupWindow
    mpopupWindow1.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);
    mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mpopupWindow1.dismiss();
        }
    });

}

    //是否有小绿功能的判断
    private void Showdialogfornocardstates() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.littlegreenbikenocardstatesdialog, null);
        final PopupWindow mpopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ImageView mlittlegreenbikesearchresultnoxdtcard = (ImageView) inflate.findViewById(R.id.littlegreenbikesearchresultnoxdtcard);
        LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        // 在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
/*        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
        mpopupWindow1.setTouchable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        mpopupWindow1.setFocusable(false);
        //  mpopupWindow1.setOutsideTouchable(true);
        mpopupWindow1.setOutsideTouchable(false);
        mpopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        //显示PopupWindow
        mpopupWindow1.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);
        mlittlegreenbikecomeindialog1dissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpopupWindow1.dismiss();
            }
        });
    }

    //请绑卡的弹窗
    public void showpopwindowaddcard() {
        View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.littlegreenbikeaddcarddialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbikeaddcarddialogcomein = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcomein);
        ImageView mlittlegreenbikeaddcarddialogcancle = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcancle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(attributes);
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
                Intent intent = new Intent(getActivity(), PersoncenterAddCard.class);
                startActivity(intent);
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);

    }
//小绿认证
    private void showpopwindowXiaoLvRenzheng(final int truenamestatesdialog) {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbiketruenamedialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbiketruenamedialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbiketruenamedialogimg1);
        ImageView mlittlegreenbikeaddcarddialogcancle = (ImageView) inflate2.findViewById(R.id.littlegreenbikeaddcarddialogcancle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(attributes);
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
                    Intent intent = new Intent(getActivity(), Personitemthree.class);
                    startActivity(intent);
                } else if (truenamestatesdialog == 1) {
                    //正在认证
                    Intent intent = new Intent(getActivity(), Personitemthreetwo.class);
                    startActivity(intent);
                } else {
                    //认证失败
                    Intent intent = new Intent(getActivity(), Personitemthreethreefail.class);
                    startActivity(intent);
                }

            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);
    }

    //有未归还小绿的弹窗
    public void showpopwindowkeepbike() {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbikekeebikedialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbikekeebikedialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbikekeebikedialogimg1);
        ImageView mlittlegreenbikekeebikedialogacncle = (ImageView) inflate2.findViewById(R.id.littlegreenbikekeebikedialogacncle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(attributes);
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
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);

    }


    public void showpopwindowcardproblem() {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.littlegreenbikeaddcardproblemdialog, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        ImageView mlittlegreenbicardproblemdialogimg1 = (ImageView) inflate2.findViewById(R.id.littlegreenbicardproblemdialogimg1);
        ImageView mlittlegreenbicardproblemdialogcalle = (ImageView) inflate2.findViewById(R.id.littlegreenbicardproblemdialogcalle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(attributes);
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
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);

    }

    //未支付
    private void Showdialogforwaitpaymoney() {
        View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.littlegreenbikewaitpaymoneydialog, null);
        PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.7f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        popupWindow2.setFocusable(false);
        popupWindow2.setOutsideTouchable(false);
        getActivity().getWindow().setAttributes(attributes);
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);
    }



    private void initMyView() {
        mhomepager = (FrameLayout) mview.findViewById(R.id.homepager);
        mvp = (Banner) mview.findViewById(R.id.home_vp);
        home_news_morenext1 = (ImageView) mview.findViewById(R.id.home_news_morenext);
        home_headview4right1 = (TextView) mview.findViewById(R.id.home_headview4right);
        textView4 = (TextView) mview.findViewById(R.id.home_headview4);
        homebutton5 = (LinearLayout) mview.findViewById(R.id.home_buttom5);
        homebutton6 = (LinearLayout) mview.findViewById(R.id.home_buttom6);
        mhome_headview4right2 = (TextView) mview.findViewById(R.id.home_headview4right2);
        mhome_queryweather1 = (LinearLayout) mview.findViewById(R.id.home_queryweather1);
        home_saoma1 = (LinearLayout) mview.findViewById(R.id.home_saoma);
        //付款
        home_fukuan1 = (LinearLayout) mview.findViewById(R.id.home_fukuan);
        home_kabao1 = (LinearLayout) mview.findViewById(R.id.home_kabao);

        homeloadingmore = (LinearLayout) mview.findViewById(R.id.home_loading_more);
        cardbutton1 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button1);
        cardbutton2 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button2);
        cardbutton3 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button3);
        cardbutton5 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button5);
        home_button_qiyeinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_qiyeinfo);
        home_button_zhongkaoinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_zhongkaoinfo);
        home_button_danweiinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_danweiinfo);
        mhome_cardgroup_button3 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button3yiliao);
        home_button_wuyequery1 = (LinearLayout) mview.findViewById(R.id.home_button_wuyequery);

        //生活缴费
        home_button_watermoneyinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_watermoneyinfo);
        home_button_phonemoneyinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_phonemoneyinfo);
        home_button_lifejiaofeimoreinfo1 = (LinearLayout) mview.findViewById(R.id.home_button_lifejiaofeimoreinfo);
        //健康医疗
        mhome_button_guahaojiuzhen = (LinearLayout) mview.findViewById(R.id.home_button_guahaojiuzhen);
        mhome_cardgroup_button6test0 = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button6test0);
        //小绿骑行
        button7xiaplv = (LinearLayout) mview.findViewById(R.id.home_cardgroup_button7xiaplv);
        //就诊记录
        mhome_button_guahaojiuzhenjilu = (LinearLayout) mview.findViewById(R.id.home_button_guahaojiuzhenjilu);
        //惠民宝
        mhome_huiminbao = (LinearLayout) mview.findViewById(R.id.home_huiminbao);
        home_news_morenext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LocalNewsmore.class);
                startActivity(intent);
            }
        });
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
                        showpopwindowXiaoLvRenzheng(0);
                        break;
                    case 4:
                        //正在骑行弹窗
                        ShowVolleyRequestforxiaolv(0);
                        break;
                    case 5:
                        //正在认证
                        showpopwindowXiaoLvRenzheng(1);
                        break;
                    case 6:
                        //认证失败弹窗
                        showpopwindowXiaoLvRenzheng(2);
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

        //InstallUtils.clearUpateFile(getContext());
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
                                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                                    intent.putExtra("saomaxdt", 2);
                                    intent.putExtra("littlegreencardfaceno", xdtcardNo);
                                    intent.putExtra("userid", id);
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

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("======================");
        ShowVolleyRequestforuserinfo();
    }

    private void initMyData() {
        dialog = new Dialog(getContext(), R.style.my_dialog);
        token1 = SpUtils.getParam(getContext(), "access_token", "");
        token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
        token3 = SpUtils.getParam(getContext(), "refresh_token", "");
        LogUtil.d("主页面得access_token1====", token1);
        LogUtil.d("主页面得x_auth_token2====", token2);
        LogUtil.d("主页面得refresh_token3====", token3);
        LogUtil.d("主页面得到的登录状态为============", loadingstate + "");
        //请求下面展示的三条新闻
        ShowVolleyRequestfornews();
        //请求天气
        ShowVolleyRequest();
        //给小点设置形状
        //给小点设置监听器
        //天气按钮监听器
        //获取登录状态
        loadingstate = SpUtils.getParam(getContext(), "loadingstate", false);
        //百度OCR直接初始化，没有用到assest文件夹中License
        //initAccessTokenWithAkSk();
        //天健
        mhome_cardgroup_button6test0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ShowVolleyRequestforuserinfo();
                if (loadingstate == true) {
                    switch (real) {
                        case -1:
                            ToastUtils.getInstance(getContext()).showMessage("请先进行实名认证");
                            Intent intent1 = new Intent(getContext(), Personitemthree.class);
                            startActivity(intent1);
                            break;
                        case 0:
                            Intent intent2 = new Intent(getContext(), Personitemthreetwo.class);
                            startActivity(intent2);
                            ToastUtils.getInstance(getContext()).showMessage("实名认证正在认证");
                            break;
                        case 1:
                            if (!TextUtils.isEmpty(huji_code)) {
                                Intent intent = new Intent(getContext(), Homezhihuiyiuliao.class);
                                intent.putExtra("homevolleygetidcardNo1", realIdnumber);
                                intent.putExtra("homevolleygetphoneNo1", requestusername);
                                intent.putExtra("homevolleygetname1", realName);
                                intent.putExtra("homehuji_code1", huji_code);
                                startActivity(intent);
                            } else {
                                //如果没有填写现在家庭住址，现在进入app填写
                                Intent intent = new Intent(getContext(), Personwritedetails.class);
                                intent.putExtra("jiatingzhuzhi", "填写现居住地址");
                                //1表示第一次进入这个页面，即从注册页面进入，2表示从主页面补充个人信息
                                intent.putExtra("homeactivityaddhomeid", 2);
                                if (!TextUtils.isEmpty(requestusername) && !TextUtils.isEmpty(realIdnumber) && !TextUtils.isEmpty(realName)) {
                                    intent.putExtra("requestusername", requestusername);
                                    intent.putExtra("realIdnumber", realIdnumber);
                                    intent.putExtra("realName", realName);
                                } else {
                                    intent.putExtra("requestusername", "");
                                    intent.putExtra("realIdnumber", "");
                                    intent.putExtra("realName", "");
                                }
                                startActivity(intent);
                            }
                            break;
                        case 2:
                            Intent intent3 = new Intent(getContext(), Personitemthreethreefail.class);
                            startActivity(intent3);
                            ToastUtils.getInstance(getContext()).showMessage("实名认证认证失败");
                            break;
                        case 3:
                            Intent intent4 = new Intent(getContext(), Personitemthreethreefail.class);
                            startActivity(intent4);
                            ToastUtils.getInstance(getContext()).showMessage("实名认证认证失败");
                            break;
                        default:
                            Intent intent5 = new Intent(getContext(), Personuser_comein.class);
                            startActivity(intent5);
                            ToastUtils.getInstance(getContext()).showMessage("请先登录");
                            break;
                    }
                } else {
                    Intent intent2 = new Intent(getContext(), Personuser_comein.class);
                    startActivity(intent2);
                    ToastUtils.getInstance(getContext()).showMessage("请先登录");
                }
            }
        });


        //天气查询
        mhome_queryweather1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homeweather.class);
                startActivity(intent);
            }
        });
        //扫码
        home_saoma1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getContext(), Homesaomaactivity.class);
                // startActivity(intent);
                Intent intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("flag","0");
                intent.putExtra("saomaxdt",2);
                intent.putExtra("cardStatus",cardStatus);
                intent.putExtra("loadingstate",loadingstate);
                intent.putExtra("greenStatus",greenStatus);
                intent.putExtra("real",real);
                intent.putExtra("userid",id);
                startActivity(intent);
//                if (loadingstate == true) {
//                    if (real == -1) {
//                        //未认证
//                        showPopWindowHandler.sendEmptyMessageDelayed(3, 10);
//                    } else if (real == 0) {
//                        //正在认证
//                        showPopWindowHandler.sendEmptyMessageDelayed(5, 10);
//                    } else if (real == 1) {
//                        //已认证
//                        if (cardStatus == 0) {
//                            //无卡
//                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
//                        } else if (cardStatus == 1) {
//                            if (!TextUtils.isEmpty(greenStatus) && greenStatus.equals("1")) {
//                                //正常
//                                showPopWindowHandler.sendEmptyMessageDelayed(7, 10);
//                            } else {
//                                showPopWindowHandler.sendEmptyMessageDelayed(1, 10);
//                            }
//                        } else if (cardStatus == 3) {
//                            //挂失
//                            showPopWindowHandler.sendEmptyMessageDelayed(8, 10);
//                        } else if (cardStatus == 11) {
//                            //解绑
//                            showPopWindowHandler.sendEmptyMessageDelayed(2, 10);
//                        }
//                    } else {
//                        //认证失败
//                        showPopWindowHandler.sendEmptyMessageDelayed(6, 10);
//                    }
//                } else {
//                    showPopWindowHandler.sendEmptyMessageDelayed(0, 10);
//                }
            }
        });
        //付款
        home_fukuan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent().setClass(getActivity(), DynamicPaymentActivity.class);
                getActivity().startActivity(mIntent);
//                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
            }
        });
        //卡包
        home_kabao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadingstate == true) {
                    Intent intent = new Intent(getContext(), Personitemfour.class);
                    intent.putExtra("xdtguashi", "卡管理");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), Personuser_comein.class);
                    startActivity(intent);

                }

            }
        });
        //列车查询
        homebutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homebuttongroupbuttonfive.class);
                startActivity(intent);
            }
        });
        //公交查询
        homebutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homebuttongroupbuttonsixbefore.class);
                intent.putExtra("xdtcardstates", cardStatus);
                intent.putExtra("personssecretstates", personssecretstates);
                intent.putExtra("realrestates", real);
                intent.putExtra("realIdnumber", realIdnumber);
                intent.putExtra("xdtcardNo", xdtcardNo);
                startActivity(intent);
            }
        });
        //公共服务加载更多按钮的逻辑
        homeloadingmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Home_loadingmore.class);
                intent.putExtra("loadingstate", loadingstate);
                intent.putExtra("homenextselect", 1);
                intent.putExtra("real", real);
                intent.putExtra("token1", token1);
                intent.putExtra("token2", token2);
                intent.putExtra("xdtcardNo", xdtcardNo);
                intent.putExtra("userid", id);
                intent.putExtra("cardStatus", cardStatus);
                intent.putExtra("greenStatus", greenStatus);
                if (!TextUtils.isEmpty(requestusername) && !TextUtils.isEmpty(realIdnumber) && !TextUtils.isEmpty(realName)) {
                    intent.putExtra("volleygetphoneNo", requestusername);
                    intent.putExtra("volleygetidcardNo", realIdnumber);
                    intent.putExtra("volleygetname", realName);
                }
                intent.putExtra("personssecretstates", personssecretstates);
                if (!TextUtils.isEmpty(huji_code)) {
                    intent.putExtra("huji_code", huji_code);
                } else {
                    intent.putExtra("huji_code", "");
                }

                startActivity(intent);
            }
        });
        //养老保险
        cardbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personssecretstates == true) {
                    Intent intent = new Intent(getContext(), Home_yanglaodetails.class);
                    //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                    intent.putExtra("yanglaobaoxian", "养老保险");
                    intent.putExtra("yanglaoidcardnumber", realIdnumber);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), Homeyanglaoquery.class);
                    intent.putExtra("yanglaobaoxian", "养老保险");
                    startActivity(intent);
                }


            }
        });
        //公积金查询
        cardbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personssecretstates == true) {
                    Intent intent2 = new Intent(getContext(), Home_yanglaodetails.class);
                    //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                    intent2.putExtra("yanglaobaoxian", "公积金");
                    intent2.putExtra("yanglaoidcardnumber", realIdnumber);
                    startActivity(intent2);
                } else {
                    Intent intent1 = new Intent(getContext(), Homeyanglaoquery.class);
                    intent1.putExtra("yanglaobaoxian", "公积金");
                    startActivity(intent1);
                }

            }
        });
        //医疗保险查询
        mhome_cardgroup_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personssecretstates == true) {
                    Intent intent2 = new Intent(getContext(), Home_yanglaodetails.class);
                    //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                    intent2.putExtra("yanglaobaoxian", "医疗保险");
                    intent2.putExtra("yanglaoidcardnumber", realIdnumber);
                    startActivity(intent2);
                } else {
                    Intent intent1 = new Intent(getContext(), Homeyanglaoquery.class);
                    intent1.putExtra("yanglaobaoxian", "医疗保险");
                    startActivity(intent1);
                }
            }
        });
        //违章查询
        cardbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttonthree.class);
                startActivity(intent);
            }
        });
        //快递查询
        cardbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttonfive.class);
                startActivity(intent);
            }
        });
        //小绿查询
        button7xiaplv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Homecardgroupbuttonsevenxiaolv.class);
                Intent intent = new Intent(getContext(), LittlegreenbikeComein.class);
                intent.putExtra("real", real);
                intent.putExtra("token1", token1);
                intent.putExtra("token2", token2);
                intent.putExtra("xdtcardNo", xdtcardNo);
                intent.putExtra("userid", id);
                LogUtil.d("useriduserid2", id + "");
                intent.putExtra("cardStatus", cardStatus);
                intent.putExtra("volleygetidcardNo", realIdnumber);
                intent.putExtra("greenStatus", greenStatus);
                intent.putExtra("personssecretstates", personssecretstates);
                startActivity(intent);
            }
        });
        //企业信息查询
        home_button_qiyeinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttonqiyeinfo.class);
                startActivity(intent);
            }
        });
        //中考成绩查询
        home_button_zhongkaoinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttonzhongkaoinfo.class);
                startActivity(intent);
            }
        });
        //单位信息查询
        home_button_danweiinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttondanweiinfo.class);
                startActivity(intent);
            }
        });
        //物业查询
        home_button_wuyequery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homecardgroupbuttonfour.class);
                startActivity(intent);
            }
        });
        //水费充值
        home_button_watermoneyinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
              /*  Intent intent = new Intent(getContext(), Homebuttonwatermoneyinfo.class);
                startActivity(intent);*/
                //H5对接的
               /* Intent intent = new Intent(getContext(), Fundsfirstactivitytwo.class);
                startActivity(intent);*/
            }
        });
        //手机充值
        home_button_phonemoneyinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Homebuttonphonemoneyinfo2.class);
                startActivity(intent);
//                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
            }
        });
        //生活缴费的更多
        home_button_lifejiaofeimoreinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Home_loadingmore.class);
                intent.putExtra("real", real);
                intent.putExtra("token1", token1);
                intent.putExtra("token2", token2);
                intent.putExtra("xdtcardNo", xdtcardNo);
                intent.putExtra("userid", id);
                intent.putExtra("cardStatus", cardStatus);
                intent.putExtra("greenStatus", greenStatus);
                intent.putExtra("loadingstate", loadingstate);
                intent.putExtra("personssecretstates", personssecretstates);
                intent.putExtra("homenextselect", 3);
                if (!TextUtils.isEmpty(requestusername) && !TextUtils.isEmpty(realIdnumber) && !TextUtils.isEmpty(realName)) {
                    intent.putExtra("volleygetphoneNo", requestusername);
                    intent.putExtra("volleygetidcardNo", realIdnumber);
                    intent.putExtra("volleygetname", realName);
                } else {
                    intent.putExtra("volleygetphoneNo", "");
                    intent.putExtra("volleygetidcardNo", "");
                    intent.putExtra("volleygetname", "");
                }
                if (!TextUtils.isEmpty(huji_code)) {
                    intent.putExtra("huji_code", huji_code);
                } else {
                    intent.putExtra("huji_code", "");
                }
                startActivity(intent);
            }
        });
        //挂号就诊
        mhome_button_guahaojiuzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getContext(), Homeguahaojiuzhen.class);
                startActivity(intent);*/
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");

            }
        });
        //就诊记录
        mhome_button_guahaojiuzhenjilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
            }
        });
//        //惠民宝
//        mhome_huiminbao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //gaoya---H5链接入口
//                LogUtil.d("H5链接入口", "");
////                Intent intent = new Intent(getContext(), Fundsfirstactivitytwo.class);
//                Intent intent = new Intent(getContext(), FundsBalance.class);
//                intent.putExtra("loadingstate", loadingstate);
//                intent.putExtra("real", real);
//                intent.putExtra("userid", id);
//                LogUtil.d("下个页面传参：" + real + loadingstate + id);
//                startActivity(intent);
//            }
//        });
        mhome_huiminbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
                //gaoya---H5首页链接入口
//                LogUtil.d("H5链接入口", "");
////                Intent intent = new Intent(getContext(), Fundsfirstactivitytwo.class);
////                Intent intent = new Intent(getContext(), FundWebViewTest.class);
//                Intent intent = new Intent(getContext(), FundsBalance.class);
//                intent.putExtra("loadingstate", loadingstate);
//                intent.putExtra("real", real);
//                intent.putExtra("userid", id);
//                LogUtil.d("下个页面传参：" + real + loadingstate + id);
//                startActivity(intent);
            }
        });

    }
    //请求新闻
    public void ShowVolleyRequestfornews() {
        String url = ApiUrls.SELECTPRESS;
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageNum", "1");
        params.put("pageCount", "3");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PressselectPress pressselectpress = gson.fromJson(response.toString(), PressselectPress.class);
                        pressselectpressdata = pressselectpress.getContent().getData();
                        int flag = pressselectpress.getFlag();
                        if (flag == 1) {
                            newsinfo.add(pressselectpressdata.get(0).getTITLE());
                            newsinfo.add(pressselectpressdata.get(1).getTITLE());
                            newsinfo.add(pressselectpressdata.get(2).getTITLE());
                            //如果没有请求到。则给空集合
                            List newsinfolist = new ArrayList<>();
                            newsinfolist.add("");
                            newsinfolist.add("");
                            newsinfolist.add("");
                            mvp.setImageLoader(new GlideImageLoader());
                            mvp.isAutoPlay(true);
                            //设置轮播时间
                            mvp.setDelayTime(1500);
                            mvp.setIndicatorGravity(BannerConfig.RIGHT);
                            mvp.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            if (newsinfo.size() == 3) {
                                mvp.setBannerTitles(newsinfo);
                            } else {
                                mvp.setBannerTitles(newsinfolist);
                            }
                            final List list = new ArrayList<>();
                            list.add(R.mipmap.home_news1);
                            list.add(R.mipmap.home_news2);
                            list.add(R.drawable.home_news3);
                            mvp.setImageLoader(new GlideImageLoader2());
                            mvp.setImages(list);
                            mvp.isAutoPlay(true);
                            //设置轮播时间
                            mvp.setDelayTime(1500);
                            mvp.setIndicatorGravity(BannerConfig.RIGHT);
                            mvp.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            if (newsinfo.size() == 3) {
                                mvp.setBannerTitles(newsinfo);
                            } else {
                                mvp.setBannerTitles(newsinfolist);
                            }
                            mvp.start();
                            //主页面轮播图的点击
                            mvp.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    Intent intent = new Intent(getContext(), LocalNewsmoreDetails.class);
                                    int id = pressselectpressdata.get(position).getID();
                                    String s = String.valueOf(id);
                                    intent.putExtra("newsdetailsid", s);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            home_news_morenext1.setVisibility(View.GONE);
                            List list = new ArrayList<>();
                            list.add(R.mipmap.home_news1);
                            list.add(R.mipmap.home_news2);
                            list.add(R.drawable.home_news3);
                            mvp.setImageLoader(new GlideImageLoader2());
                            mvp.setBannerStyle(BannerConfig.NOT_INDICATOR);
                            mvp.setImages(list);
                            mvp.start();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                home_news_morenext1.setVisibility(View.GONE);
                List list = new ArrayList<>();
                list.add(R.mipmap.home_news1);
                list.add(R.mipmap.home_news2);
                list.add(R.drawable.home_news3);
                mvp.setImageLoader(new GlideImageLoader2());
                mvp.setImages(list);
                mvp.setBannerStyle(BannerConfig.NOT_INDICATOR);
                mvp.start();
                LogUtil.d("请求的主页面新闻失败数据为", error + "");

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


    private void ShowVolleyRequestforuserinfo() {
        String urltruename = ApiUrls.LOADUSERINFO;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(""+response);
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            Gson gson = new Gson();
                            if (code1string.equals("R00001")) {
                                ViploadUserInfo viploaduserinfo = gson.fromJson(response.toString(), ViploadUserInfo.class);
                                int flag = viploaduserinfo.getFlag();
                                if (flag == 1) {
                                    ViploadUserInfo.ContentBean.DataBean viploaduserinfodata = viploaduserinfo.getContent().getData();
                                    requestusername = viploaduserinfodata.getUsername();
                                    cardStatus = viploaduserinfodata.getCardStatus();
                                    LogUtil.e("cardStatus==========",cardStatus+"");
                                    realName = viploaduserinfodata.getRealName();
                                    realIdnumber = viploaduserinfodata.getCertId();
                                    xdtcardNo = viploaduserinfodata.getCardNo();
                                    SpUtils.putParam(getActivity(),"cardNo",xdtcardNo);
                                    id = viploaduserinfodata.getId();
                                    real = viploaduserinfodata.getReal();
                                    Log.d("ididid", id + "");

                                    String mobile = viploaduserinfodata.getMobile();
                                    greenStatus = viploaduserinfodata.getGreenStatus();
                                    if (real == -1 && "-1".equals(ApplicationController.mPopWindow)) {
                                        showpopwindowtruename(mobile);
                                        ApplicationController.mPopWindow = "1";
                                    }
                                    if (id != 0) {
                                        //设置别名
                                        setAlias(id + "");
                                        SpUtils.putParam(getContext(),"appAlias",id);
                                    }
                                    //获取是否是免密码查询状态
                                    if (real == 1) {
                                        personssecretstates = SpUtils.getParam(getContext(), Personssecretstates, true);
                                        SpUtils.putParam(getContext(), Personssecretstates, true);
                                    } else {
                                        personssecretstates = SpUtils.getParam(getContext(), Personssecretstates, false);
                                        SpUtils.putParam(getContext(), Personssecretstates, false);
                                    }
                                    huji_code = viploaduserinfodata.getHuji_code();
                                    loadingstate = true;
                                    SpUtils.putParam(getContext(), "requestusername", requestusername);
                                    SpUtils.putParam(getContext(), "loadingstate", true);

                                } else {
                                    SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                                    SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                                    loadingstate = false;
                                    SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                                    SpUtils.putParam(getContext(), "loadingstate", false);
                                    SpUtils.removeParam(getContext(), "personmobile");
                                    SpUtils.removeParam(getContext(),"appAlias");
                                }
                            } else {
                                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                                SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                                SpUtils.removeParam(getContext(), "personmobile");
                                SpUtils.removeParam(getContext(),"appAlias");
                            }
                        } catch (JSONException e) {
                            SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                            SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                            SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                            SpUtils.putParam(getContext(), "loadingstate", false);
                            SpUtils.removeParam(getContext(), "personmobile");
                            SpUtils.removeParam(getContext(),"appAlias");
                            loadingstate = false;
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingstate = false;
                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                SpUtils.removeParam(getContext(), "personmobile");
                SpUtils.putParam(getContext(), "loadingstate", false);
                SpUtils.removeParam(getContext(),"appAlias");
                LogUtil.d("请求的数据为", error + "");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    //请求天气
    private void ShowVolleyRequest() {
        String url = ApiUrls.GETWEATHERLIST;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("citykey", "101180401");
        params.put("cityPinyin", "xuchang");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        WeathergetWeatherList weathergetweatherlist = gson.fromJson(response.toString(), WeathergetWeatherList.class);
                        int flag = weathergetweatherlist.getFlag();
                        if (flag == 1) {
                            WeathergetWeatherList.ContentBean.DataBean weathergetweatherlistdata = weathergetweatherlist.getContent().getData();
                            //得到温度
                            String wendu = weathergetweatherlistdata.getWendu();
                            home_headview4right1.setText(wendu + "°");
                            //得到空气质量数字
                            String qualityNumber = weathergetweatherlistdata.getQualityNumber();
                            if (!TextUtils.isEmpty(qualityNumber)) {
                                String[] split = qualityNumber.split(" ");
                                String qualityNumberone = split[1];
                                mhome_headview4right2.setText("空气质量 : " + qualityNumberone);
                            }

                            List<WeathergetWeatherList.ContentBean.DataBean.ForecastBean> forecast = weathergetweatherlistdata.getForecast();
                            WeathergetWeatherList.ContentBean.DataBean.ForecastBean forecastBean = forecast.get(0);
                            //得到天气类型
                            String type = forecastBean.getType();
                            textView4.setText(type);
                        } else {
                            String desc = weathergetweatherlist.getDesc();
                            ToastUtils.getInstance(getContext()).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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


    @Override
    public View initView() {
        return null;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.TUICHU:
                loadingstate = false;
                SpUtils.putParam(getContext(), "loadingstate", false);
                break;
            case EventMsg.PERSON_YU_E:
                //余额
                Object data22 = event.data;
                homeyuetext = data22.toString();
                break;
            case EventMsg.PERSONSSECRETSTATES:
                Object data = event.data;
                String s = data.toString();
                Boolean data1 = (Boolean) data;
                personssecretstates = data1;
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        System.gc();  //提醒系统及时回收
        System.runFinalization();
    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
            LogUtil.d("alias为空", "alias为空");
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            LogUtil.d("alias正则失败", "alias为空");
            return;
        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        // 校验Tag Alias 只能是数字,英文字母和中文
        LogUtil.d("aliasalias", alias);
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 30);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags((getContext()), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    LogUtil.d("55555555", "Unhandled msg - " + msg.what);
            }
        }
    };

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            ToastUtils.getInstance(getContext()).showMessage("token还未成功获取");
        }
        return hasGotToken;
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                ToastUtils.getInstance(getContext()).showMessage("百度AK，SK方式获取token失败");
            }
        }, getContext(), "eOH7ySjfr9lUeaVoqLNgyeid", "2NLcsjCd4adRHTLdbBj5LdHgf7BpeYpr");
    }

    public void showpopwindowtruename(final String mobile) {
        View inflate2 = LayoutInflater.from(getContext()).inflate(R.layout.home_popwindowtruename, null);
        final PopupWindow popupWindow2 = new PopupWindow(inflate2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // final LinearLayout mlittlegreenbikecomeindialog1dissmiss = (LinearLayout) inflate2.findViewById(R.id.littlegreenbikecomeindialog1dissmiss);
        Button mhome_popwindowbutton = (Button) inflate2.findViewById(R.id.home_popwindowbutton);
        ImageView mhome_popwindowbuttoncancle = (ImageView) inflate2.findViewById(R.id.home_popwindowbuttoncancle);
        popupWindow2.setTouchable(true);
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha = 0.6f;
        //多加这一句，问题就解决了！这句的官方文档解释是：让窗口背景后面的任何东西变暗
        //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(attributes);
        popupWindow2.setFocusable(false);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(false);
        mhome_popwindowbuttoncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户点击消失，进行记录。
                SpUtils.putParam(getContext(), PERSONCENTERTRUENAMEREALSTATES2, true);
                popupWindow2.dismiss();
            }
        });
        mhome_popwindowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //未认证
                Intent intent = new Intent(getActivity(), Personitemthree.class);

                if (!TextUtils.isEmpty(mobile)) {
                    intent.putExtra("loadusername", mobile);
                }
                startActivity(intent);
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1f;
                getActivity().getWindow().setAttributes(attributes);
            }
        });
        popupWindow2.showAtLocation(mhomepager, Gravity.CENTER, 0, 0);

    }
}