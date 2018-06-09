package com.xdt.xudutong.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.XdtpartnerimgsRecycleAdapter;
import com.xdt.xudutong.bean.ActivitygetActivityTitle;
import com.xdt.xudutong.bean.CitygetBalance;
import com.xdt.xudutong.bean.CitygetUserCards;
import com.xdt.xudutong.bean.IdcardgetCert;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttonone;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttononenext;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttontwo;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttontwonext;
import com.xdt.xudutong.homefragment.MarqueeViewone;
import com.xdt.xudutong.homefragment.Yingyewangdiandingwei;
import com.xdt.xudutong.personcenterfragment.Personitemfour;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.tianjian.zxingg.SecondActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.GlideImageLoader;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.xudutong.Xdtchongbuttonloadmore;
import com.xdt.xudutong.xudutong.Xdtyue;
import com.xdt.xudutong.xudutong.Xdtyuequery;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.PERSON_YU_E;
import static com.xdt.xudutong.utils.Finaltext.Personssecretstates;

/**
 * Created by Administrator on 2017\8\6 0006.
 */

public class XudutongFragment extends BaseFragment {
    int currentImg = 0;
    private View view;
    private Banner xdt_vp1;
    private TextView xdt_yu_etext1;
    private LinearLayout mxdt_buttongroupone1;
    private LinearLayout mxdt_buttongroupone2;
    private LinearLayout mxdt_buttongroupone3;
    private LinearLayout mxdt_buttongroupone4;
    private LinearLayout mxdt_buttongroupone5;
    private LinearLayout mxdt_buttongroupone6;
    private LinearLayout mxdt_buttongroupone7;
    private LinearLayout mxdt_buttongroupone8;
    private LinearLayout mxdt_chongzhi;
    private LinearLayout xdt_saoyisao;
    private LinearLayout mxdt_loadcomein;
    private LinearLayout mxdt_bangkacomein;
    private LinearLayout mxdt_loadedcomein;
    private String volleygetidcardNo;
    private String token1;
    private String token2;
    private LinearLayout mxdt_chongzhieye;
    private int realstates;

    private boolean loadingstate;
    private ImageView mxdt_chongzhieyeimg;
    private boolean xdtyueopenorclose;
    //免密状态
    private boolean personssecretstates;
    private int cardStatus;
    private RecyclerView xdt_partnerrecycleview1;
    //是否创建页面
    protected boolean isCreate = false;
    private boolean myloadflagforgaushi = false;
    private String yue1;
    private String mMobile;
    private Intent mIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xudutong, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        isCreate = true;
        Myinitview();
        MyinitData();
        return view;

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.TRUENAMESTATES:
                //收到实名认证状态
                Object data1 = event.data;
                int i = Integer.parseInt(data1.toString());
                //收到个人中心退出消息
            case EventMsg.TUICHU:
                LogUtil.d("许都通页面收到了退出========", "收到了退出");
                loadingstate = false;
                mxdt_buttongroupone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Xdtyuequery.class);
                        startActivity(intent);
                    }
                });

                //分别代表，未登录，请绑卡，已绑卡
                mxdt_loadcomein.setVisibility(View.VISIBLE);
                mxdt_bangkacomein.setVisibility(View.GONE);
                mxdt_loadedcomein.setVisibility(View.GONE);
                break;
            case EventMsg.PERSONSSECRETSTATES:
                LogUtil.d("许都通收到了免密状态========", "主页面收到了免密状态");
                Object data2 = event.data;
                String s = data2.toString();
                LogUtil.d("许都通页面收到了免密状态string===", s);
                Boolean data3 = (Boolean) data2;
                LogUtil.d("许都通页面收到了免密状态data1===", data3 + "");
                personssecretstates = data3;
                break;
            case EventMsg.ADDCARDSTATES:
                Object data4 = event.data;
                Boolean data44 = (Boolean) data4;
                LogUtil.d("许都通页面收到了绑卡成功========", data44 + "");
                ShowVolleyRequestforuserinfo();
                break;
            case EventMsg.JIEBANGCARD:
                LogUtil.d("许都通页面收到了解绑成功========", "许都通页面收到了解绑成功");
                //分别代表，未登录，请绑卡，已绑卡
                mxdt_loadcomein.setVisibility(View.GONE);
                mxdt_bangkacomein.setVisibility(View.VISIBLE);
                mxdt_loadedcomein.setVisibility(View.GONE);
                mxdt_chongzhieye.setVisibility(View.INVISIBLE);
                ShowVolleyRequestforuserinfo();
                break;
        }
    }

    private void Myinitview() {
        xdt_vp1 = (Banner) view.findViewById(R.id.xdt_vp);
        xdt_yu_etext1 = (TextView) view.findViewById(R.id.xdt_yu_etext);
        mxdt_buttongroupone1 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone1);
        mxdt_buttongroupone2 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone2);
        mxdt_buttongroupone3 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone3);
        mxdt_buttongroupone4 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone4);
        mxdt_buttongroupone5 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone5);
        mxdt_buttongroupone6 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone6);
        mxdt_buttongroupone7 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone7);
        //更多按钮
        mxdt_buttongroupone8 = (LinearLayout) view.findViewById(R.id.xdt_buttongroupone8);
        //许都通充值
        mxdt_chongzhi = (LinearLayout) view.findViewById(R.id.xdt_chongzhi);
        //扫一扫
        xdt_saoyisao = (LinearLayout) view.findViewById(R.id.xdt_saoyisao);
        //许都通状态显示
        //未登录
        mxdt_loadcomein = (LinearLayout) view.findViewById(R.id.xdt_loadcomein);
        //请绑卡
        mxdt_bangkacomein = (LinearLayout) view.findViewById(R.id.xdt_bangkacomein);
        //已绑卡，显示余额
        mxdt_loadedcomein = (LinearLayout) view.findViewById(R.id.xdt_loadedcomein);
        //许都通眼
        mxdt_chongzhieye = (LinearLayout) view.findViewById(R.id.xdt_chongzhieye);
        mxdt_chongzhieyeimg = (ImageView) view.findViewById(R.id.xdt_chongzhieyeimg);
        //合作伙伴
        xdt_partnerrecycleview1 = (RecyclerView) view.findViewById(R.id.xdt_partnerrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        xdt_partnerrecycleview1.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void MyinitData() {
        //请求登录是否成功
        ShowVolleyRequestforuserinfo();
        token1 = SpUtils.getParam(getContext(), "access_token", "");
        token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
        loadingstate = SpUtils.getParam(getContext(), "loadingstate", false);
        //请求许都通页面的轮播图
        ShowVolleyRequestpaomadengnews();

        //查找控件
        int[] xdt_partnerimgs = {R.drawable.xdt_xinkaipu, R.drawable.sdt_huasan, R.drawable.xdt_gonghang,
                R.drawable.xdt_xinpu, R.drawable.xdt_guangdian, R.drawable.xdt_tianjian};
        XdtpartnerimgsRecycleAdapter xdtpartnerimgsRecycleAdapter = new XdtpartnerimgsRecycleAdapter(getContext(), xdt_partnerimgs);
        xdt_partnerrecycleview1.setAdapter(xdtpartnerimgsRecycleAdapter);
        //许都通充值
        mxdt_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(getContext(), Xdtchongzhi.class);
                startActivity(intent);*/
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
            }
        });
        //许都通 扫码
        xdt_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Intent intent = new Intent(getContext(), Homesaomaactivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        mxdt_loadcomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        mxdt_bangkacomein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Personitemfour.class);
                intent.putExtra("xdtguashi", "卡管理");
                startActivity(intent);
            }
        });
        //许都通信息组
        //余额查询
        mxdt_buttongroupone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personssecretstates == true) {
                    switch (cardStatus) {
                        case 0://没卡
                            Intent intent0 = new Intent(getContext(), Xdtyuequery.class);
                            startActivity(intent0);
                            break;
                        case 1://正常
                            Intent intent = new Intent(getContext(), Xdtyue.class);
                            intent.putExtra("yuequeryresult", yue1);
                            startActivity(intent);
                            break;
                        case 3://挂失
                            Intent intent2 = new Intent(getContext(), Xdtyue.class);
                            intent2.putExtra("yuequeryresult", yue1);
                            startActivity(intent2);
                            break;
                        case 11://解绑
                            Intent intent3 = new Intent(getContext(), Xdtyuequery.class);
                            startActivity(intent3);
                            break;
                        default:
                            LogUtil.d("挂失状态。。。。", "default");
                            break;
                    }

                } else {
                    Intent intent = new Intent(getContext(), Xdtyuequery.class);
                    startActivity(intent);
                }
            }
        });
        //电子钱包
        mxdt_buttongroupone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Logger.e("开户的逻辑========="+ cardStatus);
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
//                if (cardStatus == 0 ){
//                    //没有开户的逻辑，进行开户
//                    mIntent = new Intent(getContext(), Benefitthepeoplenewclient.class);
//                    LogUtil.e("cardStatus===========",cardStatus+"");
//                    mIntent.putExtra("cardStatus",cardStatus);
//                    startActivity(mIntent);
//                }else {
//                    LogUtil.e("许都通账号开户的===========",cardStatus+"");
//                    //有许都通账号开户的
//                    mIntent = new Intent(getContext(), Benefitthepeopleformyaccount.class);
//                    startActivity(mIntent);

//                }
            }
        });
        //消费记录
        mxdt_buttongroupone3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogUtil.d("许都通页面登录状态为", loadingstate + "");
                if (personssecretstates == true) {
                    if (realstates == 1) {
                        if (cardStatus == 1) {
                            //消费记录
                            ShowVolleyRequestforcard();
                        } else if (cardStatus == 3) {
                            ToastUtils.getInstance(getContext()).showMessage("该账户处于挂失状态");
                        } else if (cardStatus == 11) {
                            Intent intent4 = new Intent(getContext(), Homebuttongroupbuttonone.class);
                            startActivity(intent4);
                        }else if(cardStatus==0){
                            Intent intent4 = new Intent(getContext(), Homebuttongroupbuttonone.class);
                            startActivity(intent4);
                        }

                    } else {
                        Intent intent4 = new Intent(getContext(), Homebuttongroupbuttonone.class);
                        startActivity(intent4);
                    }
                } else {
                    Intent intent4 = new Intent(getContext(), Homebuttongroupbuttonone.class);
                    startActivity(intent4);
                }
            }
        });
        //充值记录
        mxdt_buttongroupone4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personssecretstates == true) {
                    if (realstates == 1) {
                        if (cardStatus == 1) {
                            //充值记录
                            ShowVolleyRequestforcard2();
                        } else if (cardStatus == 3) {
                            ToastUtils.getInstance(getContext()).showMessage("该账户处于挂失状态");
                        } else if (cardStatus == 11) {
                            Intent intent4 = new Intent(getContext(), Homebuttongroupbuttontwo.class);
                            startActivity(intent4);
                        }else if(cardStatus==0){
                            Intent intent5 = new Intent(getContext(), Homebuttongroupbuttontwo.class);
                            startActivity(intent5);
                        }
                    } else {
                        Intent intent4 = new Intent(getContext(), Homebuttongroupbuttontwo.class);
                        startActivity(intent4);
                    }
                } else {
                    Intent intent4 = new Intent(getContext(), Homebuttongroupbuttontwo.class);
                    startActivity(intent4);
                }
            }
        });
        //付款
        mxdt_buttongroupone5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (loadingstate == true) {
                    Intent intent = new Intent(getContext(), Xdtchongfukuan.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), Personuser_comein.class);
                    startActivity(intent);
                };*/
                ToastUtils.getInstance(getContext()).showMessage("此功能敬请期待");
            }
        });
        //挂失
        mxdt_buttongroupone6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myloadflagforgaushi == true) {
                    switch (cardStatus) {
                        case 0://无卡
                            Intent intent0 = new Intent(getContext(), Personitemfour.class);
                            intent0.putExtra("xdtguashi", "卡挂失");
                            startActivity(intent0);
                            break;
                        case 1://正常
                            Intent intent = new Intent(getContext(), Personitemfour.class);
                            intent.putExtra("xdtguashi", "卡挂失");
                            startActivity(intent);
                            break;
                        case 3://挂失
                            Intent intent2 = new Intent(getContext(), Personitemfour.class);
                            intent2.putExtra("xdtguashi", "卡挂失");
                            startActivity(intent2);
                            break;
                        case 11://解绑
                            ToastUtils.getInstance(getContext()).showMessage("该账户处于解绑状态");
                            break;
                        default:
                            break;
                    }
                } else {
                    Intent intent5 = new Intent(getContext(), Personuser_comein.class);
                    startActivity(intent5);
                }

            }
        });
        //业务网点
        mxdt_buttongroupone7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Yingyewangdiandingwei.class);
                startActivity(intent);
            }
        });
        //更多页面
        mxdt_buttongroupone8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Xdtchongbuttonloadmore.class);
                intent.putExtra("realstates", realstates);
                intent.putExtra("loadingstate", loadingstate);
                intent.putExtra("volleygetidcardNo", volleygetidcardNo);
                intent.putExtra("personssecretstates", personssecretstates);
                intent.putExtra("personcardstates", cardStatus);
                intent.putExtra("myloadflagforgaushi", myloadflagforgaushi);
                intent.putExtra("yuetext", yue1);
                startActivity(intent);

            }
        });


    }

    private void ShowVolleyRequestpaomadengnews() {
        final String url = ApiUrls.GETACTIVITYTITLE;
        LogUtil.d("请求跑马灯数据=", "请求跑马灯数据");
        //Volley请求网络进行判断
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        ActivitygetActivityTitle activitygetactivitytitle = gson.fromJson(response.toString(), ActivitygetActivityTitle.class);
                        int flag = activitygetactivitytitle.getFlag();
                        if (flag == 1) {
                            final List<ActivitygetActivityTitle.ContentBean.DataBean> activitygetactivitytitledata = activitygetactivitytitle.getContent().getData();
                            //建立一个空集合放置崩溃
                            List newsinfolist = new ArrayList<>();
                            newsinfolist.add("");
                            newsinfolist.add("");
                            newsinfolist.add("");
                            List<String> info = new ArrayList<>();
                            for (int i = 0; i < activitygetactivitytitledata.size(); i++) {
                                info.add(activitygetactivitytitledata.get(i).getTitle());
                            }
                            ArrayList<String> imglist = new ArrayList<>();
                            for (int i = 0; i < activitygetactivitytitledata.size(); i++) {
                                String icon = activitygetactivitytitledata.get(i).getIcon();
                                imglist.add(icon);
                            }
                            xdt_vp1.setImageLoader(new GlideImageLoader());
                            xdt_vp1.setImages(imglist);
                            xdt_vp1.isAutoPlay(true);
                            //设置轮播时间
                            xdt_vp1.setDelayTime(1500);
                            xdt_vp1.setIndicatorGravity(BannerConfig.RIGHT);
                            xdt_vp1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            //设置轮播时间
                            xdt_vp1.setDelayTime(1500);
                            xdt_vp1.setIndicatorGravity(BannerConfig.CENTER);

                            if (info.size() == 3) {
                                //如果等于3，则展示内容
                                xdt_vp1.setBannerTitles(info);
                            } else {
                                //如果不等于3，则展示为防止崩溃，用空集合
                                xdt_vp1.setBannerTitles(newsinfolist);
                            }
                            xdt_vp1.start();
                            xdt_vp1.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    ActivitygetActivityTitle.ContentBean.DataBean dataBean = activitygetactivitytitledata.get(position);
                                    int id = dataBean.getId();
                                    String s = String.valueOf(id);
                                    Intent intent1 = new Intent(getContext(), MarqueeViewone.class);
                                    intent1.putExtra("marqueeviewid", s);
                                    startActivity(intent1);
                                }
                            });
                        } else {
                            List list2 = new ArrayList<>();
                            list2.add(R.drawable.home_xdt_banner_defultimg);
                            xdt_vp1.setImageLoader(new GlideImageLoader());
                            xdt_vp1.setImages(list2);
                            xdt_vp1.start();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                List list2 = new ArrayList<>();
                list2.add(R.drawable.home_xdt_banner_defultimg);
                xdt_vp1.setImageLoader(new GlideImageLoader());
                xdt_vp1.setImages(list2);
                xdt_vp1.start();
                LogUtil.d("请求的请求跑马灯数据数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    /*    @Override
        public void onResume() {
            super.onResume();
            LogUtil.d("onResumeonResume","onResumeonResume");
            token1 = SpUtils.getParam(getContext(), "access_token", "");
            token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
            LogUtil.d("token1token1token1token1",token1);
            LogUtil.d("token2token2token2token2",token2);
            loadingstate = SpUtils.getParam(getContext(), "loadingstate", false);
            ShowVolleyRequestforuserinfo();
        }*/
/*    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&& isCreate) {
            //相当于Fragment的onResume
            //在这里处理加载数据等操作
            LogUtil.d("onResumeonResume","onResumeonResume");
            token1 = SpUtils.getParam(getContext(), "access_token", "");
            token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
            LogUtil.d("token1token1token1token1",token1);
            LogUtil.d("token2token2token2token2",token2);
            loadingstate = SpUtils.getParam(getContext(), "loadingstate", false);
            ShowVolleyRequestforuserinfo();
        } else {
            //相当于Fragment的onPause
        }
    }*/

    @Override
    public boolean getUserVisibleHint() {
        LogUtil.d("onResumeonResume","onResumeonResume");
        return super.getUserVisibleHint();

    }
/*    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            LogUtil.d("onResumeonResume", "onResumeonResume");
            token1 = SpUtils.getParam(getContext(), "access_token", "");
            token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
            LogUtil.d("token1token1token1token1", token1);
            LogUtil.d("token2token2token2token2", token2);
            loadingstate = SpUtils.getParam(getContext(), "loadingstate", false);
            ShowVolleyRequestforuserinfo();
            //pause
        } else {
            //resume

        }
    }*/

    //请求是否登录成功
    private void ShowVolleyRequestforuserinfo() {
        String urltruename = ApiUrls.LOADUSERINFO;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json("加载个人信息======"+response);
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            LogUtil.d("2222222222222222222222222","22222222222222222222222");
                            Gson gson = new Gson();
                            if (code1string.equals("R00001")) {
                                ViploadUserInfo viploaduserinfo = gson.fromJson(response.toString(), ViploadUserInfo.class);
                                mMobile = viploaduserinfo.getContent().getData().getMobile();
                                int flag = viploaduserinfo.getFlag();
                                if (flag == 1) {
                                    LogUtil.d("111111111111111111","1111111111111111111");
                                    //登陆成功
                                    realstates = viploaduserinfo.getContent().getData().getReal();
                                    int realId = viploaduserinfo.getContent().getData().getRealId();
                                    cardStatus = viploaduserinfo.getContent().getData().getCardStatus();
                                    if (realstates == 1) {
                                        personssecretstates = SpUtils.getParam(getContext(), Personssecretstates, true);
                                    } else {
                                        personssecretstates = SpUtils.getParam(getContext(), Personssecretstates, false);
                                    }
                                    String realIdstring = String.valueOf(realId);
                                    mxdt_chongzhieye.setVisibility(View.INVISIBLE);
                                    mxdt_loadcomein.setVisibility(View.GONE);
                                    mxdt_bangkacomein.setVisibility(View.VISIBLE);
                                    mxdt_loadedcomein.setVisibility(View.GONE);
                                    //请求绑卡信息
                                    SpUtils.putParam(getContext(), "loadingstate", true);
                                    ShowVolleyRequestforturename(realIdstring);
                                    myloadflagforgaushi = true;
                                } else {
                                    LogUtil.d("请求许都通页面失败00的数据为=", "请求许都通页面失败11的数据为=");
                                    mxdt_chongzhieye.setVisibility(View.VISIBLE);
                                    mxdt_loadcomein.setVisibility(View.VISIBLE);
                                    mxdt_bangkacomein.setVisibility(View.GONE);
                                    mxdt_loadedcomein.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            LogUtil.d("请求许都通页面失败11的数据为=", "请求许都通页面失败11的数据为=");
                            e.printStackTrace();
                            mxdt_chongzhieye.setVisibility(View.VISIBLE);
                            mxdt_loadcomein.setVisibility(View.VISIBLE);
                            mxdt_bangkacomein.setVisibility(View.GONE);
                            mxdt_loadedcomein.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求许都通页面失败22的数据为=", error.toString());
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

    //消费记录查询，请求个人绑定的卡
    private void ShowVolleyRequestforcard() {
        String url = ApiUrls.GETUSERCARDS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        //    params.put("train_date", trainstartdata);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json("请求个人绑定的卡"+response+"");
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            CitygetUserCards.ResponseBean response1 = citygetusercards.getResponse();
                            if (!response1.equals("")) {
                                CitygetUserCards.ResponseBean.BodyBean bodyBean = response1.getBody().get(0);
                                String cityCardno = bodyBean.getCityCardno();
                                int status = bodyBean.getStatus();
                                String substring = volleygetidcardNo.substring(volleygetidcardNo.length() - 6, volleygetidcardNo.length());
                                //选出当月一号和当前日期
                                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
                                String startdate = sDateFormat.format(new java.util.Date());
                                SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String enddate = sDateFormat2.format(new java.util.Date());
                                Intent intent = new Intent(getContext(), Homebuttongroupbuttononenext.class);
                                intent.putExtra("zhanghao", cityCardno);
                                intent.putExtra("startdate11", startdate);
                                intent.putExtra("endedata11", enddate);
                                startActivity(intent);
                            } else {
                                LogUtil.d("许都通页面请求个人绑定卡为空=", "许都通页面请求个人绑定卡为空");
                            }
                        } else {
                            Intent intent = new Intent(getContext(), Personuser_comein.class);
                            startActivity(intent);
                            LogUtil.d("许都通页面请求个人绑定卡为空=", "许都通页面请求个人绑定卡为空");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
    //请求实名认证身份证号
    private void ShowVolleyRequestforturename(String realIdstring) {
        String urltruename = ApiUrls.GETCERT;
        Map<String, String> params = new HashMap<>();
        params.put("certId", realIdstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            IdcardgetCert idcardgetcert = gson.fromJson(response.toString(), IdcardgetCert.class);
                            IdcardgetCert.ResponseBean.BodyBean body = idcardgetcert.getResponse().getBody();
                            volleygetidcardNo = body.getIdcardNo();
                            ShowVolleyRequest();
                        } else {
                            LogUtil.d("许都通页面请求R0002数据为=", "许都通页面请求的失败11数据为");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("许都通页面请求的失败11数据为=", error.toString());

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


    //请求卡号
    private void ShowVolleyRequest() {
        String url = ApiUrls.GETUSERCARDS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        //    params.put("train_date", trainstartdata);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            CitygetUserCards.ResponseBean getResponse = citygetusercards.getResponse();
                            CitygetUserCards.ResponseBean.BodyBean bodyBean = getResponse.getBody().get(0);
                            String cityCardno = bodyBean.getCityCardno();
                            String substring2 = volleygetidcardNo.substring(volleygetidcardNo.length() - 6, volleygetidcardNo.length());
                            ShowVolleyrequestforgetBalance(cityCardno, substring2);
                            //未登录，请绑卡，已绑卡，请求成功，有数据
                            mxdt_loadcomein.setVisibility(View.GONE);
                            mxdt_bangkacomein.setVisibility(View.GONE);
                            mxdt_loadedcomein.setVisibility(View.VISIBLE);
                            mxdt_chongzhieye.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    //查询绑定个人账户充值记录
    private void ShowVolleyRequestforcard2() {
        String url = ApiUrls.GETUSERCARDS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            CitygetUserCards.ResponseBean response1 = citygetusercards.getResponse();
                            if (!response1.equals("")) {
                                CitygetUserCards.ResponseBean.BodyBean bodyBean = response1.getBody().get(0);
                                String cityCardno = bodyBean.getCityCardno();
                                String substring = volleygetidcardNo.substring(volleygetidcardNo.length() - 6, volleygetidcardNo.length());
                                //选出当月一号和当前日期
                                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
                                String startdate = sDateFormat.format(new java.util.Date());
                                SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String enddate = sDateFormat2.format(new java.util.Date());
                                Intent intent = new Intent(getContext(), Homebuttongroupbuttontwonext.class);
                                intent.putExtra("zhanghao", cityCardno);
                                intent.putExtra("startdate21", startdate);
                                intent.putExtra("endedata21", enddate);
                                intent.putExtra("home_buttongroupbuttononenextheadview1", "充值记录");
                                startActivity(intent);
                            }
                        } else {
                            Intent intent = new Intent(getContext(), Personuser_comein.class);
                            startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
                            yue1 = String.format("%.2f", getyue);
                            xdtyueopenorclose = SpUtils.getParam(getContext(), "xdtyueopenorclose", false);
                            LogUtil.i("获取账户余额显示否", xdtyueopenorclose + "");
                            if (xdtyueopenorclose == true) {
                                xdt_yu_etext1.setText(yue1);
                                mxdt_chongzhieyeimg.setImageResource(R.drawable.xut_open_eye);
                            } else {
                                xdt_yu_etext1.setText("***.**");
                                mxdt_chongzhieyeimg.setImageResource(R.drawable.xdt_close_eye);
                            }
                            //许都通眼的切换
                            mxdt_chongzhieye.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int[] image = new int[]{
                                            R.drawable.xut_open_eye,
                                            R.drawable.xdt_close_eye,
                                    };
                                    if (currentImg >= 1) {
                                        currentImg = -1;
                                    }
                                    mxdt_chongzhieyeimg.setImageResource(image[++currentImg]);
                                    if (currentImg == 0) {
                                        xdt_yu_etext1.setText(yue1);
                                        SpUtils.putParam(getContext(), "xdtyueopenorclose", true);
                                    } else {
                                        xdt_yu_etext1.setText("***.**");
                                        SpUtils.putParam(getContext(), "xdtyueopenorclose", false);
                                    }
                                }
                            });
                            EventBus.getDefault().post(new EventMsg(PERSON_YU_E, yue1));
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

    @Override
    public View initView() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        System.gc();  //提醒系统及时回收
        System.runFinalization();
    }
}
