package com.xdt.xudutong.waituse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.PersoneightlittlegreenpushAdapter;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.bean.Littlegreenorderpay;
import com.xdt.xudutong.personcenterfragment.Personitemthreethree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personpushinfomanager2;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.RecyclerViewDivider;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.MESSAGEINFOREADED;
import static com.xdt.xudutong.utils.EventMsg.XIOALV;

/**
 * Created by Administrator on 2017\12\29 0029.
 */

public class Personpushinfomanager1 extends Base2Activity {
    private int pageNum = 1;
    private JSONArray datas1;
    private PersoneightlittlegreenpushAdapter littlegreenbikerecordtwoXrecycleveiwAdapter;
    private XRecyclerView person_push_infomanageronerecycleview;
    private int appAlias;
    private int personturenamestates;
    private ImageView monlyProgress;
    private TextView mperson_push_infomanageroneselectbutton3text,mperson_push_infomanageroneselectbutton2text;
    private String token1;
    private String token2;
    private List datasflag,xiaolv;
    private LinearLayout memptystates_layout2;
    private Littlegreenorderpay.ContentEntity mContent;
    private Littlegreenorderpay mLittlegreenorderpay;
    private String mLittlegreenbike;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_push_infomanager_one);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initView();
    }

    public void initView() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        Intent intent = getIntent();
        personturenamestates = intent.getIntExtra("personturenamestates", 5);
        appAlias = SpUtils.getParam(getApplicationContext(), "appAlias", 0);
        LinearLayout mperson_eight_messageactivity_back = (LinearLayout) findViewById(R.id.person_eight_messageactivity_back);
        LinearLayout mperson_push_infomanageroneselectbutton1 = (LinearLayout) findViewById(R.id.person_push_infomanageroneselectbutton1);
        LinearLayout mperson_push_infomanageroneselectbutton2 = (LinearLayout) findViewById(R.id.person_push_infomanageroneselectbutton2);
        LinearLayout mperson_push_infomanageroneselectbutton3 = (LinearLayout) findViewById(R.id.person_push_infomanageroneselectbutton3);
        LinearLayout mperson_push_infomanageroneselectbutton4 = (LinearLayout) findViewById(R.id.person_push_infomanageroneselectbutton4);
        memptystates_layout2 = (LinearLayout) findViewById(R.id.emptystates_layout2);
        mperson_push_infomanageroneselectbutton3text = (TextView) findViewById(R.id.person_push_infomanageroneselectbutton3text);
        mperson_push_infomanageroneselectbutton2text = (TextView) findViewById(R.id.person_push_infomanageroneselectbutton2text);
        person_push_infomanageronerecycleview = (XRecyclerView) findViewById(R.id.person_push_infomanageronerecycleview);

        person_push_infomanageronerecycleview.addItemDecoration(new RecyclerViewDivider(Personpushinfomanager1.this, LinearLayoutManager.VERTICAL));
        monlyProgress = (ImageView) findViewById(R.id.onlyProgress);
        datas1 = new JSONArray();
        datasflag = new ArrayList<>();
        xiaolv = new ArrayList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Personpushinfomanager1.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        person_push_infomanageronerecycleview.setLayoutManager(linearLayoutManager);
        person_push_infomanageronerecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        person_push_infomanageronerecycleview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        person_push_infomanageronerecycleview.setArrowImageView(R.drawable.ic_loading_rotate);
        littlegreenbikerecordtwoXrecycleveiwAdapter = new PersoneightlittlegreenpushAdapter();
        person_push_infomanageronerecycleview.setAdapter(littlegreenbikerecordtwoXrecycleveiwAdapter);
        Animation animation = AnimationUtils.loadAnimation(Personpushinfomanager1.this, R.anim.dialog_progress_anim);
        monlyProgress.startAnimation(animation);
        mperson_eight_messageactivity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //顶部四个按钮的点击
        mperson_push_infomanageroneselectbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Personpushinfomanager1.this, Personpushinfomanager2.class);
                intent.putExtra("personpushinfomanager1selectbutton", 0);
                startActivity(intent);*/
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("敬请期待");
            }
        });
        mperson_push_infomanageroneselectbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personpushinfomanager1.this, Personpushinfomanager2.class);
                intent.putExtra("personpushinfomanager1selectbutton", 1);
                startActivity(intent);
//                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("敬请期待");
            }
        });

        mperson_push_infomanageroneselectbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personpushinfomanager1.this, Personpushinfomanager2.class);
                intent.putExtra("personpushinfomanager1selectbutton", 2);
                intent.putExtra("personturenamestates", personturenamestates);
                startActivity(intent);
            }
        });
        mperson_push_infomanageroneselectbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(Personpushinfomanager1.this, Personpushinfomanager2.class);
                intent.putExtra("personpushinfomanager1selectbutton", 3);
                startActivity(intent);*/
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("敬请期待");
            }
        });
//        initData();
        littlegreenbikerecordtwoXrecycleveiwAdapter.setOnClickListener(new PersoneightlittlegreenpushAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id, int position) {
                ShowVolleyRequestforinfodetails(id, position);
                ShowVolleyRequestforreaded(id, position);
            }

            @Override
            public void onDelClick(String id, int position) {
                if (datas1.size()<=1){
                    memptystates_layout2.setVisibility(View.VISIBLE);
                }
                ShowVolleyRequestfordelect(id, position);
            }

            @Override
            public void onFlagcircle(String id, int position) {
                ShowVolleyRequestforreaded(id, position);

            }
        });
    }


    private void initData() {
//        int pagersize = 10;
        requestforlittlegreenrecordtwo(10, 1);
        person_push_infomanageronerecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                if (null != datas1) {
                    datas1.clear();
                    littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
                }
                requestforlittlegreenrecordtwo(10, 1);
                person_push_infomanageronerecycleview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestforlittlegreenrecordtwo(10, 2);
                    }
                }, 2000);

            }
        });
    }

//    @Override
//    public void setMyContentView() {
//        setContentView(R.layout.person_push_infomanager_one);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case MESSAGEINFOREADED:
                if (null != mperson_push_infomanageroneselectbutton3text) {
                    if (null!=datasflag){
                        int count = Collections.frequency(datasflag, "0");
                        if (count>0){
                            mperson_push_infomanageroneselectbutton3text.setVisibility(View.VISIBLE);
                            mperson_push_infomanageroneselectbutton3text.setText(count-1+"");
                        }else{
                            mperson_push_infomanageroneselectbutton3text.setVisibility(View.GONE);
                        }
                    }
                }
            case XIOALV:
                if (null != mperson_push_infomanageroneselectbutton2text) {
                    if (null!=xiaolv){
                        int count = Collections.frequency(xiaolv, "0");
                        if (count>0){
                            mperson_push_infomanageroneselectbutton2text.setVisibility(View.VISIBLE);
                            mperson_push_infomanageroneselectbutton2text.setText(count-1+"");
                        }else{
                            mperson_push_infomanageroneselectbutton2text.setVisibility(View.GONE);
                        }
                    }
                }
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void requestforlittlegreenrecordtwo(int pagersize, final int myflag) {
        //查询全部推送记录
        String url = ApiUrls.PUSHMESSAGEMANAGEMENT;
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("pushUser",appAlias+"");
        stringMap.put("pageNum", pageNum + "");
        stringMap.put("pageCount", pagersize + "");
        stringMap.put("appAlias", "");
        stringMap.put("pushObj", "");

        JSONObject jsonObject = new JSONObject(stringMap);
        JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            private String desc;
            private String code;

            @Override
            public void onResponse(JSONObject response) {
                Log.e("^^^^^^^^^^^^^^",response+"");
                try {
                    code = response.getString("code");
                    desc = response.getString("desc");
                    String certcheckUnreads = response.getString("certcheckUnread");
                    String yonganUnread = response.getString("yonganUnread");
                    int i = Integer.parseInt(certcheckUnreads);
                    int a = Integer.parseInt(yonganUnread);

                    if (a > 0) {
                        mperson_push_infomanageroneselectbutton2text.setVisibility(View.VISIBLE);
                        mperson_push_infomanageroneselectbutton2text.setText(yonganUnread);
                    } else {
                        mperson_push_infomanageroneselectbutton2text.setVisibility(View.GONE);
                    }
                    if (i > 0) {
                        mperson_push_infomanageroneselectbutton3text.setVisibility(View.VISIBLE);
                        mperson_push_infomanageroneselectbutton3text.setText(certcheckUnreads);
                    } else {
                        mperson_push_infomanageroneselectbutton3text.setVisibility(View.GONE);
                    }
                    JSONObject content = response.getJSONObject("content");
                        if (null!=code&&code.equals("R00001")) {
                            JSONArray pushlittlegreencontent = JSON.parseArray(content.getString("data"));
                            if (myflag == 1) {
                                //下拉刷新
                                if (pushlittlegreencontent.size() > 0) {
                                    memptystates_layout2.setVisibility(View.GONE);
                                    datas1.addAll(pushlittlegreencontent);
                                    for (int j = 0; j < pushlittlegreencontent.size(); j++) {
                                        String s = pushlittlegreencontent.getJSONObject(j).get("readStatus").toString();
                                        datasflag.add(s);
                                    }
                                    littlegreenbikerecordtwoXrecycleveiwAdapter.setDatas(datas1, datasflag);
                                } else {
                                    memptystates_layout2.setVisibility(View.VISIBLE);
                                    ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("暂无查询结果");
                                }
                                monlyProgress.clearAnimation();
                                monlyProgress.setVisibility(View.INVISIBLE);
                                person_push_infomanageronerecycleview.refreshComplete();
                            } else if (myflag == 2) {
                                //滚动加载
                                if (pushlittlegreencontent.size() > 0) {
                                    datas1.addAll(pushlittlegreencontent);
                                    littlegreenbikerecordtwoXrecycleveiwAdapter.setDatas(datas1, datasflag);
                                    person_push_infomanageronerecycleview.loadMoreComplete();
                                } else {
                                    ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("没有更多数据了");
                                    person_push_infomanageronerecycleview.loadMoreComplete();
                                }
                                monlyProgress.clearAnimation();
                                monlyProgress.setVisibility(View.INVISIBLE);
                                person_push_infomanageronerecycleview.loadMoreComplete();
                                memptystates_layout2.setVisibility(View.GONE);
                            }

                        } else {
                            memptystates_layout2.setVisibility(View.VISIBLE);
                            monlyProgress.clearAnimation();
                            monlyProgress.setVisibility(View.INVISIBLE);
                            if (!"没有相关数据".equals(desc)){
                                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage(desc);
                            }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                monlyProgress.clearAnimation();
                monlyProgress.setVisibility(View.INVISIBLE);
                person_push_infomanageronerecycleview.loadMoreComplete();
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("系统繁忙");
                LogUtil.d("请求小绿error数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(Personpushinfomanager1.this).getRequestQueue().add(jsonRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("111111111", "111111111111");
        datas1.clear();
        littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
        initData();
    }

    private void ShowVolleyRequestfordelect(String collectid, final int positions) {
        String url3 = ApiUrls.DELMESSAGEMANAGEMENT;
        //删除消息
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", collectid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                            if (codestring.equals("R00001")) {
                                if (datasflag.get(positions-1).equals("0")){
                                    EventBus.getDefault().post(new EventMsg(MESSAGEINFOREADED, 1));
                                }
                                datas1.remove(positions-1);
                                littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
                            } else {
                                Object desc = response.get("desc");
                                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage(desc + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("系统繁忙");
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

    private void ShowVolleyRequestforreaded(String collectid, final int positions) {
        String url3 = ApiUrls.PUSHREAD;
        //标记已读
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", collectid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                            if (codestring.equals("R00001")) {
                                if (datasflag.get(positions-1).equals("0")){
                                    EventBus.getDefault().post(new EventMsg(MESSAGEINFOREADED, 1));
                                }
                                datasflag.set(positions - 1, "1");
                                littlegreenbikerecordtwoXrecycleveiwAdapter.notifyDataSetChanged();
                            } else {
                                Object desc = response.get("desc");
                                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage(desc + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("系统繁忙");
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

    private void ShowVolleyRequestforinfodetails(String collectid, final int positions) {
        String url3 = ApiUrls.DETAILEDINFORMATION;
        //详情
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", collectid);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String desc;
                    private String code;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            code = response.getString("code");
                            desc = response.getString("desc");
                            JSONObject content = response.getJSONObject("content");
                            if (!TextUtils.isEmpty(code) && code.equals("R00001")) {
                                JSONObject data = content.getJSONObject("data");
                                String appAlias = data.getString("appAlias");
                                LogUtil.d("appAlias2222",appAlias);
                                switch (appAlias) {
                                    case "certCheckPush":
                                        //实名认证模块
                                        //认证成功
                                        Intent intent1 = new Intent(Personpushinfomanager1.this, Personitemthreethree.class);
                                        startActivity(intent1);
                                        break;
                                    case "certfalsePush":
                                        //实名认证模块
                                        //认证失败
                                        Intent intent2 = new Intent(Personpushinfomanager1.this, Personitemthreethreefail.class);
                                        startActivity(intent2);
                                        break;
                                    case "activityPush":
                                        //公告
                                       /* String marqueeviewid = extrasJson.optString("param");
                                        Intent intent3 = new Intent(Personpushinfomanager1.this, MarqueeViewone.class);
                                        intent3.putExtra("marqueeviewid", marqueeviewid);
                                        startActivity(intent3);*/
                                        break;
                                    case "citynewsPush":
                                        //新闻
                               /*         String paramnewsid = extrasJson.optString("param");
                                        Intent intent4 = new Intent(Personpushinfomanager1.this, LocalNewsmoreDetails.class);
                                        intent4.putExtra("newsdetailsid", paramnewsid);
                                        startActivity(intent4);*/
                                        break;
                                    case "tjpush":
                                        //天健推送
                             /*           String paramtianjian = pushlittlegreencontent.getJSONObject(positions).getString("appAlias");
                                        Intent intent5 = new Intent(Personpushinfomanager1.this, ASKWebViewUtilsActivity.class);
                                        String stringtitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                                        intent5.putExtra("jpush_topviewtext", stringtitle);
                                        intent5.putExtra("url", paramtianjian);
                                        startActivity(intent5);*/
                                        break;
                                    case "yonganPush":
                                        mLittlegreenbike = data.getString("extra");
                                        Log.e("yonganPush=======",mLittlegreenbike);
                                        ShowVolleyRequestforlittleorderpay(mLittlegreenbike);
                                        break;
                                    default:
                                        System.out.println("other");
                                }

                            }else{
                                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage(desc);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("系统繁忙");
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
    //请求计费接口
    private void ShowVolleyRequestforlittleorderpay(String littlegreenopenkey) {
        String url = ApiUrls.WSBIKEAPPSCANCODEPAYMENT;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderno", littlegreenopenkey);
        params.put("cardfaceno",SpUtils.getParam(Personpushinfomanager1.this,"cardNo",""));
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("小绿计费========",""+response);
                        Gson gson = new Gson();
                        mLittlegreenorderpay = gson.fromJson(response.toString(), Littlegreenorderpay.class);
                        String code = mLittlegreenorderpay.getCode();
                        String desc = mLittlegreenorderpay.getDesc();
                        if (code.equals("R00001")) {
                            mContent = mLittlegreenorderpay.getContent();
                            //consume 消耗时间（秒）
                            String consume = mContent.getConsume();
                            //expense	金额（元）
                            String expense = mContent.getExpense();
                            String returnTime = mContent.getReturnTime();
//                            if (expense>0){
//                                Intent intent6 = new Intent().setClass(Personpushinfomanager1.this, Littlegreeenbikeorderpay.class);
//                                intent6.putExtra("consume",consume);
//                                intent6.putExtra("expense",expense);
//                                intent6.putExtra("returnTime",returnTime);
//                                intent6.putExtra("data",mLittlegreenorderpay);
//                                startActivity(intent6);
//                            }else{
                                Intent intent7 = new Intent().setClass(Personpushinfomanager1.this, ReturnBikeActivity.class);
//                                intent7.putExtra("consume",consume);
//                                intent7.putExtra("expense",expense);
//                                intent7.putExtra("returnTime",returnTime);
                                intent7.putExtra("data",mLittlegreenorderpay);
                                startActivity(intent7);
//                            }

                        } else {
                            ToastUtils.getInstance(Personpushinfomanager1.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personpushinfomanager1.this).showMessage("系统繁忙");

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
}
