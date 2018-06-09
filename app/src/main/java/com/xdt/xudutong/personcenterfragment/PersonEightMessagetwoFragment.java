package com.xdt.xudutong.personcenterfragment;

/**
 * Created by Administrator on 2017\8\9 0009.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.xdt.xudutong.adapder.PersoneightlittlegreenpushAdapter2;
import com.xdt.xudutong.bean.Littlegreenorderpay;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.ReturnBikeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xdt.xudutong.utils.EventMsg.MESSAGEINFOREADED;

public class PersonEightMessagetwoFragment extends Fragment {
    private View view;
    private JSONArray datas1;
    private int pageNum = 1;
    private XRecyclerView mperson_eight_messagetwo_fragmentrecycleveiw1;
    private PersoneightlittlegreenpushAdapter2 personeightlittlegreenpushAdapter2;
    private int appAlias;
    private Littlegreenorderpay mLittlegreenorderpay;
    private LinearLayout memptystates_layout2;
    private String token1;
    private String token2;
    private List datasflag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.person_eight_messagetwo_fragment, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        appAlias = SpUtils.getParam(getContext(), "appAlias", 0);
        mperson_eight_messagetwo_fragmentrecycleveiw1 = (XRecyclerView) view.findViewById(R.id.person_eight_messagetwo_fragmentrecycleveiw1);
        memptystates_layout2 = (LinearLayout) view.findViewById(R.id.emptystates_layout2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mperson_eight_messagetwo_fragmentrecycleveiw1.setLayoutManager(layoutManager);
        mperson_eight_messagetwo_fragmentrecycleveiw1.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mperson_eight_messagetwo_fragmentrecycleveiw1.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mperson_eight_messagetwo_fragmentrecycleveiw1.setArrowImageView(R.drawable.ic_loading_rotate);
        personeightlittlegreenpushAdapter2 = new PersoneightlittlegreenpushAdapter2();
        mperson_eight_messagetwo_fragmentrecycleveiw1.setAdapter(personeightlittlegreenpushAdapter2);
        datas1 = new JSONArray();
        datasflag = new ArrayList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.MESSAGEINFOREADED:
                if (null != personeightlittlegreenpushAdapter2) {
                    personeightlittlegreenpushAdapter2.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initData() {
        int pagersize = 10;
        ShowVolleyRequestfor(10, 1);
        mperson_eight_messagetwo_fragmentrecycleveiw1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                if (null != datas1) {
                    datas1.clear();
                    datasflag.clear();
                    personeightlittlegreenpushAdapter2.notifyDataSetChanged();
                }
                ShowVolleyRequestfor(10, 1);
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShowVolleyRequestfor(7, 2);
                    }
                }, 2000);
            }
        });

        personeightlittlegreenpushAdapter2.setOnClickListener(new PersoneightlittlegreenpushAdapter2.OnItemClickListener() {


            @Override
            public void onItemClick(String id, int position) {
                Log.e("little==========",datas1.getJSONObject(position-1).get("extra").toString());
                 ShowVolleyRequestforlittleorderpay(getContext(),datas1.getJSONObject(position-1).get("extra").toString());
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
                                personeightlittlegreenpushAdapter2.notifyDataSetChanged();
                            } else {
                                Object desc = response.get("desc");
                                ToastUtils.getInstance(getContext()).showMessage(desc + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(getContext()).showMessage("系统繁忙");
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
                                    LogUtil.d("发送了已读","发送了已读");
                                    EventBus.getDefault().post(new EventMsg(MESSAGEINFOREADED, 1));
                                }
                                datasflag.set(positions - 1, "1");
                                personeightlittlegreenpushAdapter2.notifyDataSetChanged();
                            } else {
                                Object desc = response.get("desc");
                                ToastUtils.getInstance(getContext()).showMessage(desc + "");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(getContext()).showMessage("系统繁忙");
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


    private void ShowVolleyRequestfor(int pagercount, final int myflag) {
        String urltruename = ApiUrls.PUSHMESSAGEMANAGEMENT;
        Map<String, String> params = new HashMap<>();
        params.put("pushUser",appAlias+"");
        params.put("pageNum", pageNum + "");
        params.put("pageCount", pagercount + "");
        params.put("appAlias", "yonganPush");
        params.put("pushObj", "1");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String desc;
                    private String code;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            code = response.getString("code");
                            desc = response.getString("desc");
                            JSONObject content = response.getJSONObject("content");
                            LogUtil.d("content",content+"");
                            if (null!=code&&code.equals("R00001")) {
                                JSONArray pushlittlegreencontent = JSON.parseArray(content.getString("data"));
                                if (myflag == 1) {
                                    //下拉刷新
                                    if (!TextUtils.isEmpty(content+"")||pushlittlegreencontent.size() > 0) {
                                        LogUtil.d("111111111",1111111+"");
                                        memptystates_layout2.setVisibility(View.GONE);
                                        datas1.addAll(pushlittlegreencontent);
                                        for (int i = 0; i < pushlittlegreencontent.size(); i++) {
                                            String s = pushlittlegreencontent.getJSONObject(i).get("readStatus").toString();
                                            datasflag.add(s);
                                        }
                                        personeightlittlegreenpushAdapter2.setDatas(datas1, datasflag);

                                    } else {
                                        LogUtil.d("2222222222",222222222+"");
                                        memptystates_layout2.setVisibility(View.VISIBLE);
                                        ToastUtils.getInstance(getContext()).showMessage("暂无查询结果");
                                    }
                                    mperson_eight_messagetwo_fragmentrecycleveiw1.refreshComplete();
                                } else if (myflag == 2) {
                                    //滚动加载
                                    if (pushlittlegreencontent.size() > 0) {
                                        memptystates_layout2.setVisibility(View.GONE);
                                        datas1.addAll(pushlittlegreencontent);
                                        personeightlittlegreenpushAdapter2.setDatas(datas1, datasflag);
                                    } else {
                                        memptystates_layout2.setVisibility(View.GONE);
                                        ToastUtils.getInstance(getContext()).showMessage("没有更多数据了");
                                    }
                                    mperson_eight_messagetwo_fragmentrecycleveiw1.loadMoreComplete();
                                }
                            }
                            else{
                                memptystates_layout2.setVisibility(View.VISIBLE);
                                mperson_eight_messagetwo_fragmentrecycleveiw1.refreshComplete();
                                mperson_eight_messagetwo_fragmentrecycleveiw1.loadMoreComplete();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                memptystates_layout2.setVisibility(View.VISIBLE);
                LogUtil.d("请求的数据为=", error.toString());
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

    //请求计费接口
    private void ShowVolleyRequestforlittleorderpay(final Context context, String littlegreenopenkey) {
        String url = ApiUrls.WSBIKEAPPSCANCODEPAYMENT;
        //Volley请求网络进行判断  push/messageManagement接口取messageId的值放进pushId里
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderno", littlegreenopenkey);
        params.put("cardfaceno",SpUtils.getParam(context,"cardNo",""));
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        mLittlegreenorderpay = gson.fromJson(response.toString(), Littlegreenorderpay.class);
                        String code = mLittlegreenorderpay.getCode();
                        String desc = mLittlegreenorderpay.getDesc();
                        if (code.equals("R00001")) {
                            Littlegreenorderpay.ContentEntity content = mLittlegreenorderpay.getContent();
                            //consume 消耗时间（秒）
                            String consume = content.getConsume();
                            //expense	金额（元）
                            String expense = content.getExpense();
                            String returnTime = content.getReturnTime();
//                            if (expense > 0) {
//                                Intent intent6 = new Intent(context, Littlegreeenbikeorderpay.class);
//                                intent6.putExtra("consume", consume);
//                                intent6.putExtra("expense", expense);
//                                intent6.putExtra("returnTime", returnTime);
//                                intent6.putExtra("data",mLittlegreenorderpay);
//                                context.startActivity(intent6);
//                            } else {
                                Intent intent7 = new Intent(context, ReturnBikeActivity.class);
//                                intent7.putExtra("consume", consume);
//                                intent7.putExtra("expense", expense);
//                                intent7.putExtra("returnTime", returnTime);
                                intent7.putExtra("data",mLittlegreenorderpay);
                                intent7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                context.startActivity(intent7);
//                            }

                        } else {
                            ToastUtils.getInstance(context).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(context).showMessage("系统繁忙");

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }
}
