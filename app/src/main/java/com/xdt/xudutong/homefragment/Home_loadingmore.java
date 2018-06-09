package com.xdt.xudutong.homefragment;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.HomeLoadingmoreAdapter;
import com.xdt.xudutong.adapder.HomeLoadingmoretopAdapter;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.personcenterfragment.Personwritedetails;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.LittlegreenbikeComein;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\11 0011.
 */

public class Home_loadingmore extends BaseActivity {
    private String token1;
    private String token2;
    private RecyclerView mhome_loading_moreingrecycle0;
    private RecyclerView mhome_loading_moreingrecycle1;
    private RecyclerView mhome_loading_moreingrecycle2;
    private RecyclerView mhome_loading_moreingrecycle3;
    private RecyclerView mhome_loading_moreingrecycle4;
    private RecyclerView mhome_loading_moreingrecycle5;
    private LinearLayout home_loading_moreback1;
    private String volleygetidcardNo;
    private String volleygetphoneNo;
    private String volleygetname;
    private String huji_code;
    private String huji_addr;
    private Handler mhandlerdoctor;
    private Handler mhandlerpeople;
    private Boolean loadingstate;
    private int real = -1;
    private Dialog dialog;
    private RecyclerView home_healthyxuchangrecycleview1;
    private EditText home_healthyxuchangtopeduittext1;
    private NotificationManager manager;
    private int homenextselect;
    private boolean personssecretstates;
    private String mXdtcardNo;
    private String mGreenStatus;
    private int mCardStatus;
    private int mUserid;

    @Override
    public void setMyContentView() {
        Intent intent99 = getIntent();
        homenextselect = intent99.getIntExtra("homenextselect", 1);
        loadingstate = intent99.getBooleanExtra("loadingstate", false);
        real = intent99.getIntExtra("real", -1);
        token1 = intent99.getStringExtra("token1");
        token2 = intent99.getStringExtra("token2");
        mXdtcardNo = intent99.getStringExtra("xdtcardNo");
        volleygetidcardNo = intent99.getStringExtra("volleygetidcardNo");
        mCardStatus = intent99.getIntExtra("cardStatus", 0);
        mUserid = intent99.getIntExtra("userid", 0);
        real = intent99.getIntExtra("real", 5);
        mGreenStatus = intent99.getStringExtra("greenStatus");
        volleygetphoneNo = intent99.getStringExtra("volleygetphoneNo");
        volleygetname = intent99.getStringExtra("volleygetname");
        huji_code = intent99.getStringExtra("huji_code");
        huji_addr = intent99.getStringExtra("huji_addr");
        personssecretstates = intent99.getBooleanExtra("personssecretstates", false);
        switch (homenextselect) {
            case 0:
                break;
            case 1:
                setContentView(R.layout.home_loading_moreing);
                AppManager.getInstance().addActivity(this);
                break;
            case 2:
                break;
            case 3:
                setContentView(R.layout.home_loading_moreinglifemoney);
                AppManager.getInstance().addActivity(this);
                break;
            default:
                setContentView(R.layout.home_loading_moreing);
                AppManager.getInstance().addActivity(this);
                break;
        }
    }


    @Override
    public void initView() {
        home_loading_moreback1 = (LinearLayout) findViewById(R.id.home_loading_moreback);
        mhome_loading_moreingrecycle0 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle0);
        mhome_loading_moreingrecycle1 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle1);
        mhome_loading_moreingrecycle2 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle2);
        mhome_loading_moreingrecycle3 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle3);
        mhome_loading_moreingrecycle4 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle4);
        mhome_loading_moreingrecycle5 = (RecyclerView) findViewById(R.id.home_loading_moreingrecycle5);
        mhome_loading_moreingrecycle0.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 7));
        mhome_loading_moreingrecycle1.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 4));
        mhome_loading_moreingrecycle2.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 4));
        mhome_loading_moreingrecycle3.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 4));
        mhome_loading_moreingrecycle4.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 4));
        mhome_loading_moreingrecycle5.setLayoutManager(new GridLayoutManager(Home_loadingmore.this, 4));
        initData();
    }

    private void initData() {
        dialog = new Dialog(Home_loadingmore.this, R.style.my_dialog);
        manager = (NotificationManager) Home_loadingmore.this.getSystemService
                (NOTIFICATION_SERVICE);
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");


        home_loading_moreback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int[] imgs0 = {R.drawable.home_bus, R.drawable.home_bike, R.drawable.home_weizhang,
                R.drawable.home_yanglao, R.drawable.home_gji_jin, R.drawable.home_doctore2, R.drawable.home_phone};
        int[] imgs1 = {R.drawable.home_bus, R.drawable.home_bike, R.drawable.home_weizhang,
                R.drawable.home_train};
        String[] strings1 = {"公交出行", "小绿骑行", "违章查询", "列车查询"};
        int[] imgs2 = {R.drawable.home_gji_jin, R.drawable.home_yanglao, R.drawable.home_yiliao,
                R.drawable.home_kuaidi, R.drawable.home_gongshangimg, R.drawable.home_shiyeimg, R.drawable.home_shengyuimg,
                R.drawable.home_qiye, R.drawable.home_jiguandanweidetailsimg, R.drawable.home_shiyedanweidetailsimg, R.drawable.home_geti,
                R.drawable.home_zhongkao};
        String[] strings2 = {
                "公积金", "养老保险", "医疗保险", "快递查询", "工伤保险", "失业保险",
                "生育保险", "企业信息", "机关单位", "事业单位", "个体工商", "中考成绩"};
        int[] imgs3 = {R.drawable.zhihuiyiliao, R.drawable.home_guahao,
                R.drawable.home_jiuzhen};
        String[] strings3 = {
                "智慧医疗",
                "挂号就诊", "就诊记录"};
        int[] imgs4 = {R.drawable.home_wuyewuye, R.drawable.home_phone, R.drawable.home_woter, R.drawable.home_power_rate,
                R.drawable.home_ranqiimg, R.drawable.home_nuanqiimg, R.drawable.home_youxianimg,};
        String[] strings4 = {
                "物业费", "手机充值", "水费", "电费", "燃气费",
                "暖气费", "有线电视"};
        int[] imgs5 = {R.drawable.home_huiminbaoimg};
        String[] strings5 = {
                "惠民宝"};
        HomeLoadingmoretopAdapter homeLoadingmoreAdapter0 = new HomeLoadingmoretopAdapter(Home_loadingmore.this, imgs0);
        mhome_loading_moreingrecycle0.setAdapter(homeLoadingmoreAdapter0);
        HomeLoadingmoreAdapter homeLoadingmoreAdapter = new HomeLoadingmoreAdapter(Home_loadingmore.this, imgs1, strings1);
        mhome_loading_moreingrecycle1.setAdapter(homeLoadingmoreAdapter);
        HomeLoadingmoreAdapter homeLoadingmoreAdapter2 = new HomeLoadingmoreAdapter(Home_loadingmore.this, imgs2, strings2);
        mhome_loading_moreingrecycle2.setAdapter(homeLoadingmoreAdapter2);
        HomeLoadingmoreAdapter homeLoadingmoreAdapter3 = new HomeLoadingmoreAdapter(Home_loadingmore.this, imgs3, strings3);
        mhome_loading_moreingrecycle3.setAdapter(homeLoadingmoreAdapter3);
        HomeLoadingmoreAdapter homeLoadingmoreAdapter4 = new HomeLoadingmoreAdapter(Home_loadingmore.this, imgs4, strings4);
        mhome_loading_moreingrecycle4.setAdapter(homeLoadingmoreAdapter4);
        HomeLoadingmoreAdapter homeLoadingmoreAdapter5 = new HomeLoadingmoreAdapter(Home_loadingmore.this, imgs5, strings5);
        mhome_loading_moreingrecycle5.setAdapter(homeLoadingmoreAdapter5);
        //条目点击事件
        homeLoadingmoreAdapter.setOnItemClickListener(new HomeLoadingmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        if (fastClick()) {
                            Intent intent = new Intent(Home_loadingmore.this, Homebuttongroupbuttonsix.class);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        if (fastClick()) {
                            Intent intent = new Intent(Home_loadingmore.this, LittlegreenbikeComein.class);
                            intent.putExtra("real", real);
                            intent.putExtra("token1", token1);
                            intent.putExtra("token2", token2);
                            intent.putExtra("xdtcardNo", mXdtcardNo);
                            intent.putExtra("userid", mUserid);
                            LogUtil.d("useriduserid2", mUserid + "");
                            intent.putExtra("cardStatus", mCardStatus);
                            intent.putExtra("volleygetidcardNo", volleygetidcardNo);
                            intent.putExtra("greenStatus", mGreenStatus);
                            intent.putExtra("personssecretstates", personssecretstates);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (fastClick()) {
                            Intent intent2 = new Intent(Home_loadingmore.this, Homecardgroupbuttonthree.class);
                            startActivity(intent2);
                        }
                        break;
                    case 3:
                        if (fastClick()) {
                            Intent intent3 = new Intent(Home_loadingmore.this, Homebuttongroupbuttonfive.class);
                            startActivity(intent3);
                        }
                        break;

                }
            }
        });
        homeLoadingmoreAdapter2.setOnItemClickListener(new HomeLoadingmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent0 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent0.putExtra("yanglaobaoxian", "公积金");
                                intent0.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent0);
                            } else {
                                Intent intent00 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent00.putExtra("yanglaobaoxian", "公积金");
                                startActivity(intent00);
                            }
                        }
                        break;
                    case 1:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent1 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent1.putExtra("yanglaobaoxian", "养老保险");
                                intent1.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent1);
                            } else {
                                Intent intent11 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent11.putExtra("yanglaobaoxian", "养老保险");
                                startActivity(intent11);
                            }
                        }
                        break;
                    case 2:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent2 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent2.putExtra("yanglaobaoxian", "医疗保险");
                                intent2.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent2);
                            } else {
                                Intent intent22 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent22.putExtra("yanglaobaoxian", "医疗保险");
                                startActivity(intent22);
                            }
                        }
                        break;
                    case 3:
                        if (fastClick()) {
                            //快递查询
                            Intent intent3 = new Intent(Home_loadingmore.this, Homecardgroupbuttonfive.class);
                            startActivity(intent3);
                        }
                        break;
                    case 4:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent4 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent4.putExtra("yanglaobaoxian", "工伤保险");
                                intent4.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent4);
                            } else {
                                Intent intent44 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent44.putExtra("yanglaobaoxian", "工伤保险");
                                startActivity(intent44);
                            }
                        }
                        break;
                    case 5:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent5 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent5.putExtra("yanglaobaoxian", "失业保险");
                                intent5.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent5);
                            } else {
                                Intent intent55 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent55.putExtra("yanglaobaoxian", "失业保险");
                                startActivity(intent55);
                            }
                        }
                        break;
                    case 6:
                        if (fastClick()) {
                            if (personssecretstates == true) {
                                Intent intent6 = new Intent(Home_loadingmore.this, Home_yanglaodetails.class);
                                //*         intent.putExtra("gongjijinidcardnumber", volleygetidcardNo);*//*
                                intent6.putExtra("yanglaobaoxian", "生育保险");
                                intent6.putExtra("yanglaoidcardnumber", volleygetidcardNo);
                                startActivity(intent6);
                            } else {
                                Intent intent66 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                                intent66.putExtra("yanglaobaoxian", "生育保险");
                                startActivity(intent66);
                            }
                        }
                        break;
                    case 7:
                        //企业
                        if (fastClick()) {
                            Intent intent7 = new Intent(Home_loadingmore.this, Homecardgroupbuttonqiyeinfo.class);
                            startActivity(intent7);
                        }
                        break;
                    case 8:
                        if (fastClick()) {
                            //机关单位事业
                            Intent intent8 = new Intent(Home_loadingmore.this, Homecardgroupbuttondanweiinfo.class);
                            startActivity(intent8);
                        }
                        break;
                    case 9:
                        if (fastClick()) {
                            //事业单位
                            Intent intent9 = new Intent(Home_loadingmore.this, Homecardgroupbuttondanweiinfo.class);
                            startActivity(intent9);
                        }
                        break;
                    case 10:
                        //个体工商
                        if (fastClick()) {
                            Intent intent10 = new Intent(Home_loadingmore.this, Homecardgroupbuttongetiinfo.class);
                            startActivity(intent10);
                        }
                        break;
                    case 11:
                        //中考
                        if (fastClick()) {
                            Intent intent11 = new Intent(Home_loadingmore.this, Homecardgroupbuttonzhongkaoinfo.class);
                            startActivity(intent11);
                        }
                        break;
                    case 12:
                        //参保
                     /*   Intent intent12 = new Intent(Home_loadingmore.this, Homeyanglaoquery.class);
                        intent12.putExtra("yanglaobaoxian", "参保信息查询");
                        startActivity(intent12);*/
                        if (fastClick()) {
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                        }
                        break;
                }
            }
        });
        //健康医疗
        homeLoadingmoreAdapter3.setOnItemClickListener(new HomeLoadingmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    switch (position) {
                        case 0:
                            //  签约医生--医生端
                            //getusername就是电话号码
                        /* String volleygetidcardNo;
                             String volleygetphoneNo;
                            String volleygetname;*/
                            if (loadingstate == true) {
                                switch (real) {
                                    case -1:
                                        ToastUtils.getInstance(Home_loadingmore.this).showMessage("请先进行实名认证");
                                        Intent intent1 = new Intent(Home_loadingmore.this, Personitemthree.class);
                                        startActivity(intent1);
                                        break;
                                    case 0:
                                        ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证正在认证");
                                        break;
                                    case 1:
                                        if (!TextUtils.isEmpty(huji_code)) {
                                            switch (real) {
                                                case -1:
                                                    ToastUtils.getInstance(Home_loadingmore.this).showMessage("请先进行实名认证");
                                                    Intent intent2 = new Intent(Home_loadingmore.this, Personitemthree.class);
                                                    startActivity(intent2);
                                                    break;
                                                case 0:
                                                    Intent intent3 = new Intent(Home_loadingmore.this, Personitemthreetwo.class);
                                                    startActivity(intent3);
                                                    ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证正在认证");
                                                    break;
                                                case 1:
                                                    if (!TextUtils.isEmpty(huji_code)) {
                                                        Intent intent4 = new Intent(Home_loadingmore.this, Homezhihuiyiuliao.class);
                                                        intent4.putExtra("homevolleygetidcardNo1", volleygetidcardNo);
                                                        intent4.putExtra("homevolleygetphoneNo1", volleygetphoneNo);
                                                        intent4.putExtra("homevolleygetname1", volleygetname);
                                                        intent4.putExtra("homehuji_code1", huji_code);
                                                        startActivity(intent4);
                                                    } else {
                                                        //如果没有填写现在家庭住址，现在进入app填写
                                                        Intent intent5 = new Intent(Home_loadingmore.this, Personwritedetails.class);
                                                        intent5.putExtra("jiatingzhuzhi", "填写现居住地址");
                                                        //1表示第一次进入这个页面，即从注册页面进入，2表示从主页面补充个人信息
                                                        intent5.putExtra("homeactivityaddhomeid", 2);
                                                        if (!TextUtils.isEmpty(volleygetname) && !TextUtils.isEmpty(volleygetidcardNo) && !TextUtils.isEmpty(volleygetphoneNo)) {
                                                            intent5.putExtra("requestusername", volleygetphoneNo);
                                                            intent5.putExtra("realIdnumber", volleygetidcardNo);
                                                            intent5.putExtra("realName", volleygetname);
                                                        } else {
                                                            intent5.putExtra("requestusername", "");
                                                            intent5.putExtra("realIdnumber", "");
                                                            intent5.putExtra("realName", "");
                                                        }
                                                        startActivity(intent5);
                                                    }
                                                    break;
                                                case 2:
                                                    Intent intent6 = new Intent(Home_loadingmore.this, Personitemthreethreefail.class);
                                                    startActivity(intent6);
                                                    ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证认证失败");
                                                    break;
                                                case 3:
                                                    Intent intent7 = new Intent(Home_loadingmore.this, Personitemthreethreefail.class);
                                                    startActivity(intent7);
                                                    ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证认证失败");
                                                    break;
                                                default:
                                                    Intent intent8 = new Intent(Home_loadingmore.this, Personuser_comein.class);
                                                    startActivity(intent8);
                                                    ToastUtils.getInstance(Home_loadingmore.this).showMessage("请先登录");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证认证失败");
                                        break;
                                    case 3:
                                        ToastUtils.getInstance(Home_loadingmore.this).showMessage("实名认证认证失败");
                                        break;
                                    default:
                                        ToastUtils.getInstance(Home_loadingmore.this).showMessage("系统繁忙");
                                }
                            } else {
                                Intent intent = new Intent(Home_loadingmore.this, Personuser_comein.class);
                                startActivity(intent);
                                ToastUtils.getInstance(Home_loadingmore.this).showMessage("请先登录");
                            }

                            break;
                        case 1:
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            //挂号就诊
                            break;
                        case 2:
                            //就诊记录
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                        default:
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;

                    }
                }
            }
        });
        //生活缴费recycleview
        homeLoadingmoreAdapter4.setOnItemClickListener(new HomeLoadingmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    switch (position) {
                        case 0:
                            //物业
                            Intent intent = new Intent(Home_loadingmore.this, Homecardgroupbuttonfour.class);
                            startActivity(intent);
                            break;
                        case 1:
                            //手机充值
                 /*       Intent intent1 = new Intent(Home_loadingmore.this, Homebuttonphonemoneyinfo2.class);
                        startActivity(intent1);*/
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                        case 2:
                            //水费
                    /*    Intent intent1 = new Intent(Home_loadingmore.this, Homebuttonwatermoneyinfo.class);
                        startActivity(intent1);*/
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                        case 3:
                            //电费
                      /* Intent intent2 = new Intent(Home_loadingmore.this, HomebuttonEnergymoneyinfo.class);
                        startActivity(intent2);*/
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                        case 4:
                       /* Intent intent3 = new Intent(Home_loadingmore.this, HomebuttonRanqimoneyinfo.class);
                        startActivity(intent3);*/
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            //燃气费
                            break;
                        case 5:
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            //暖气费

                            break;
                        case 6:
                            //有线电视
                /*        Intent intent5 = new Intent(Home_loadingmore.this, HomebuttonyouxianTVinfo.class);
                        startActivity(intent5);*/
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                    }
                }
            }
        });
        homeLoadingmoreAdapter5.setOnItemClickListener(new HomeLoadingmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    switch (position) {
                        case 0:
                            //惠民宝
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("此功能敬请期待");
                            break;
                    }
                }
            }
        });
    }

    private void ShowVolleyRequestforVipupdateUserInfo(String huji_code) {

        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("huji_code", huji_code);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            //更新用户信息成功
                            ToastUtils.getInstance(Home_loadingmore.this).showMessage("请稍候..");
                        } else {
                            //更新用户信息成功
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Home_loadingmore.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mhandlerdoctor != null) {
            mhandlerdoctor.removeCallbacksAndMessages(null);
        }
        if (mhandlerpeople != null) {
            mhandlerpeople.removeCallbacksAndMessages(null);
        }
        AppManager.getInstance().removeActivity(this);
        System.gc();  //提醒系统及时回收
        System.runFinalization();
    }
}
