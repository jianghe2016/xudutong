package com.xdt.xudutong.utils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.bean.Littlegreenorderpay;
import com.xdt.xudutong.frgment.MainActivity;
import com.xdt.xudutong.homefragment.MarqueeViewone;
import com.xdt.xudutong.locallifefragment.LocalNewsmoreDetails;
import com.xdt.xudutong.personcenterfragment.Personitemthreethree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.tianjian.ASKWebViewUtilsActivity;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.waituse.ReturnBikeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";
    private Littlegreenorderpay.ContentEntity mContent;
    private Littlegreenorderpay mLittlegreenorderpay;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Bundle bundle = intent.getExtras();
       /*     *//* bundle.get("url");
            Log.i("收到的++++", url.toString());*//*
            int badgeCount = 1;
            ShortcutBadger.applyCount(context, badgeCount); //for 1.1.4+*/
            // ShortcutBadger.with(getApplicationContext()).count(badgeCount); //for 1.1.3

            LogUtil.d(TAG, "[MyReceiver] onReceiveee - " + intent.getAction() + ", extras: " + printBundle(bundle));
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                LogUtil.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                //测试
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                LogUtil.d("收到了自定义消息。消息内容是：" + message);
                String extraceshi = bundle.getString(JPushInterface.EXTRA_EXTRA);
                LogUtil.d("收到了自定义消息。extraceshi是：" + extraceshi);
                boolean topActivity = isTopActivity(context);
                if (topActivity == true) {
                    if (Jpushreceivedatenumber.data.size() > 0) {
                        ShortcutBadger.removeCount(context);
                    }
                } else {
                    /**
                     * 通过输出日志我们知道extra是JSON格式的字符串
                     * 解析Json
                     */
                    Map<String, Object> map = new HashMap<String, Object>();
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(extraceshi);
                        String key = jsonObject.getString("key");
                        map.put("key", key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    map.put("message", message);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date = format.format(calendar.getTime());
                    map.put("date", date);
                    Jpushreceivedatenumber.data.add(map);
                    ShortcutBadger.applyCount(context, Jpushreceivedatenumber.data.size()); //for 1.1.4+
                }

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver]2 接收到推送下来的通知3333");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                LogUtil.d(TAG, "[MyReceiver]3 接收到推送下来的通知的ID: " + notifactionId);
                String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                LogUtil.d(TAG, " 接收到推送下来的通知title : " + title);
                String message = bundle.getString(JPushInterface.EXTRA_ALERT);
                LogUtil.d(TAG, "接收到推送下来的通知message : " + message);
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                LogUtil.d(TAG, "接收到推送下来的通知extras : " + extras);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver]4 用户点击打开了通知");
                boolean topActivity = isTopActivity(context);
                LogUtil.d("topActivitytopActivity", topActivity + "");
                if (topActivity == false) {
                    Intent intent1 = new Intent();
                    String mypackge = "com.xdt.xudutong";
                    String firstpage = "com.xdt.xudutong.frgment.MainActivity";
                    ComponentName cn = new ComponentName(mypackge, firstpage);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setComponent(cn);
                    context.startActivity(intent1);
                }
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                JSONObject extrasJson = new JSONObject(extras);
                String s1 = extrasJson.optString("pushid");
                //接收到推送下来的通知extras : {"pushid":"yonganPush","param":"50505134561313131313"}
                //params为内容字段的key
                switch (s1) {
                    case "certCheckPush":
                        //实名认证模块
                        //认证成功
                        Intent intent1 = new Intent(context, Personitemthreethree.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent1);
                        break;
                    case "certfalsePush":
                        //实名认证模块
                        //认证失败
                        Intent intent2 = new Intent(context, Personitemthreethreefail.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent2);
                        break;
                    case "activityPush":
                        //公告
                        String marqueeviewid = extrasJson.optString("param");
                        Intent intent3 = new Intent(context, MarqueeViewone.class);
                        intent3.putExtra("marqueeviewid", marqueeviewid);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent3);
                        break;
                    case "citynewsPush":
                        //新闻
                        String paramnewsid = extrasJson.optString("param");
                        Intent intent4 = new Intent(context, LocalNewsmoreDetails.class);
                        intent4.putExtra("newsdetailsid", paramnewsid);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent4);
                        break;
                    case "tjpush":
                        //天健推送
                        String paramtianjian = extrasJson.optString("param");
                        Log.i("extrasextras", extras.toString());
                        Log.i("sssssss", paramtianjian.toString());
                        Intent intent5 = new Intent(context, ASKWebViewUtilsActivity.class);
                        String stringtitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                        intent5.putExtra("jpush_topviewtext", stringtitle);
                        intent5.putExtra("url", paramtianjian);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent5);
                        break;
                    case "yonganPush":
                        String littlegreenbike = extrasJson.optString("param");
                        Log.i("extrasextras", extras.toString());
                        Log.i("sssssss", littlegreenbike.toString());
                        ShowVolleyRequestforlittleorderpay(context,littlegreenbike);

                        break;
                    default:
                        System.out.println("other");
                }


            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver]5 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));

                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                LogUtil.d(TAG, "[MyReceiver]6" + intent.getAction() + " connected state change to " + connected);
            } else {
                LogUtil.d(TAG, "[MyReceiver]7 Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
            LogUtil.d(TAG, e.toString());
        }

    }


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        if (MainActivity.isForeground) {
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
            if (!ExampleUtil.isEmpty(extras)) {
                try {
                    JSONObject extraJson = new JSONObject(extras);
                    if (extraJson.length() > 0) {
                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
                    }
                } catch (JSONException e) {
                }
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
        }
    }

    private boolean isTopActivity(Context context) {
        String packageName = "com.xdt.xudutong";
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            //应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }
    //请求计费接口
    private void ShowVolleyRequestforlittleorderpay(final Context context, String littlegreenopenkey) {
        String url = ApiUrls.WSBIKEAPPSCANCODEPAYMENT;
        //Volley请求网络进行判断
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
                            mContent = mLittlegreenorderpay.getContent();
                            //consume 消耗时间（秒）
                            String consume = mContent.getConsume();
                            //expense	金额（元）
                            String expense = mContent.getExpense();
                            String returnTime = mContent.getReturnTime();
//                            if (expense>0){
//                                Intent intent6 = new Intent().setClass(context, Littlegreeenbikeorderpay.class);
//                                intent6.putExtra("consume",consume);
//                                intent6.putExtra("expense",expense);
//                                intent6.putExtra("returnTime",returnTime);
//                                intent6.putExtra("data",mLittlegreenorderpay);
//                                context.startActivity(intent6);
//                            }else{
//                                Intent intent7 = new Intent(context, Littlegreenbikereturnbike.class);
                                Intent intent7 = new Intent().setClass(context, ReturnBikeActivity.class);
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
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

    }
}
