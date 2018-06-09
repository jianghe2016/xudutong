package com.xdt.xudutong.view;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.GetLatestVersion;
import com.xdt.xudutong.bean.Tokenrefreshtoken;
import com.xdt.xudutong.frgment.MainActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.Jpushreceivedatenumber;
import com.xdt.xudutong.utils.SpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by Administrator on 2017/5/8.
 */

public class SplashActivity extends AppCompatActivity {

    public String APK_NAME = "update";
    private String TAG = "InstallUtils";
    private Context context;
    private String token1;
    private String token2;
    private String token3;
    private Runnable mrun;
    private Handler handler1;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private String loaclappversion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        context = this;
        if (Jpushreceivedatenumber.data.size() > 0) {
            ShortcutBadger.removeCount(context);
        }
        token1 = SpUtils.getParam(SplashActivity.this, "access_token", "");
        token2 = SpUtils.getParam(SplashActivity.this, "x_auth_token", "");
        token3 = SpUtils.getParam(SplashActivity.this, "refresh_token", "");
        ShowVolleyRequestforVipupdateUserInfo();
        handler1 = new Handler();
        loaclappversion = SpUtils.getParam(SplashActivity.this, "appversion", "");
        if (loaclappversion.isEmpty()) {
            loaclappversion = getVersionName();
        }
        String s1 = loaclappversion.replaceAll("\\.", "");
        final int intapkversionname = Integer.parseInt(s1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ShowVolleyReques(intapkversionname);
            }
        }, 2000);   //2秒


    }

    private void ShowVolleyReques(final int getappversion) {
        String url = ApiUrls.GETLATESTVERSION;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        GetLatestVersion getlatestversion = gson.fromJson(response.toString(), GetLatestVersion.class);
                        int flag = getlatestversion.getFlag();
                        if (flag == 1) {
                            GetLatestVersion.ContentBean.DataBean getlatestversiondata = getlatestversion.getContent().getData();
                            String version = getlatestversiondata.getVersion();
                            String s2 = version.replaceAll("\\.", "");
                            int internetversion = Integer.parseInt(s2);
                            String url1 = getlatestversiondata.getUrl();
                            //如果请求的大于当前本地的
                            if (internetversion <= getappversion) {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    //展示对话框
                                    Showupdateuii(url1, version);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

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


    //展示提示更新的对话框
    private void Showupdateuii(final String url1, final String netverson) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("新版本更新？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
                downloadApk(url1);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SpUtils.putParam(SplashActivity.this, "appversion", netverson);
                dialog.dismiss();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }


    //下载更新的apk
    private void downloadApk(String url1) {
        Toast.makeText(SplashActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
        Homedownloadapking(url1);
    }

    private void Homedownloadapking(final String url1) {
        new InstallUtils(context, url1, APK_NAME, new InstallUtils.DownloadCallBack() {

            private Notification notification;
            private NotificationManager motificationManager;

            @Override
            public void onStart() {
                Log.i(TAG, "InstallUtils---onStart");

            }

            @Override
            public void onComplete(String path) {

               /* //第一个参数为Notification的id
                builder.setContentText("下载完成");
                // Removes the progress bar
                builder.setProgress(0, 0, false);
                notificationManager.notify(4, builder.build());*/
                notificationManager.cancel(4);
                Log.i(TAG, "InstallUtils---onComplete:" + path);
                InstallUtils.installAPK(context, path, getPackageName() + ".fileProvider", new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onLoading(long total, long current) {
                Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                final int progressnumber2 = (int) (current * 100 / total);
                sendNotification(progressnumber2);
               /* Notification.Builder builder = new Notification.Builder(ApplicationController.getContext());
                builder.setTicker("有新消息");
                builder.setSmallIcon(R.drawable.appliation);//设置图标
                builder.setContentTitle("我城许昌");//消息标题
                builder.setContentText("正在下载我城许昌");//消息内容
                builder.setProgress(100, progressnumber2, false);
                PendingIntent pendingIntent = PendingIntent.getActivity
                        //通过Notification来创建通知
                                (ApplicationController.getContext(), 1, new Intent(ApplicationController.getContext(), SplashActivity.class), 0);
                builder.setContentIntent(pendingIntent);
                NotificationManager manager = (NotificationManager) ApplicationController.getContext().getSystemService
                        (NOTIFICATION_SERVICE);
                manager.notify(3, builder.build());//通过管理器发起通知
                System.out.println("使用for来遍历请求跑马灯数据的结果:" + progressnumber2);
                builder.setAutoCancel(true);
                if (progressnumber2 >= 95) {
                    manager.cancel(3);
                }*/

               /* motificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                notification = new Notification();
                Intent intent1 = new Intent(SplashActivity.this,MainActivity.class);
                PendingIntent pd = PendingIntent.getActivity(SplashActivity.this, 0, intent1, 0);
                notification.tickerText = "正在下载我城许昌";
                notification.icon=R.drawable.appliation;
                final RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.splashtopview);
                remoteViews.setImageViewResource(R.id.splashtopviwnoimg1, R.drawable.appliation);
                remoteViews.setTextViewText(R.id.splashtopviwnotext1,"我城许昌...");
                notification.contentView = remoteViews;
                notification.contentIntent = pd;
                //这个Runnable 用来根据下载进度来刷新进度条
                mrun = new Runnable() { //这个Runnable 用来根据下载进度来刷新进度条
                    @Override
                    public void run() {
                        if (progressnumber2<98){
                            remoteViews.setProgressBar(R.id.splashtopviwnoprogressbar1,99,progressnumber2,false);
                            remoteViews.setTextViewText(R.id.splashtopviwnoprogressbartext1,progressnumber2+"%");
                            motificationManager.notify(8888, notification);
                            handler1.postDelayed(mrun, 300);
                        }else{
                            remoteViews.setProgressBar(R.id.splashtopviwnoprogressbar1,100,100,false);
                            remoteViews.setTextViewText(R.id.splashtopviwnoprogressbartext1,100+"%");
                            motificationManager.notify(8888, notification);
                            motificationManager.cancel(8888);
                            Toast.makeText(SplashActivity.this, "download over", Toast.LENGTH_SHORT);//提示用户下载成功
                        }
                    }
                };
                handler1.postDelayed(mrun, 300);
*/

            }

            @Override
            public void onFail(Exception e) {
                Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
            }

        }).downloadAPK();
    }

    private void update() {
/*        //安装应用
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), DOWNLOAD_NAME)),
                "application/vnd.android.package-archive");
        startActivity(intent);*/
    }

    private String getVersionName() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    private void sendNotification(int progressnumber2) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //设置小图标
        //点击后自动清除
        //设置通知标题
        //设置通知内容
        //设置通知的动作
        //设置通知时间，默认为系统发出通知的时间
        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                //设置小图标
                .setTicker("开始下载我城许昌")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.appliation)
                //点击后自动清除
                .setAutoCancel(true)
                //设置通知标题
                .setContentTitle("我城许昌")
                //设置通知内容
                .setContentText(progressnumber2 + "%")
                .setProgress(99, progressnumber2, false)
                //设置通知的动作
                .setContentIntent(mPendingIntent)
                //设置通知时间，默认为系统发出通知的时间
                .setWhen(System.currentTimeMillis());
        notificationManager.notify(4, builder.build());


/*        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题4");

        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher);

        // Start a lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress;
                for (progress = 0; progress <= 100; progress++) {
                    // Sets the progress indicator to a max value, the current completion percentage,
                    // and "determinate" state
                    mBuilder.setProgress(100, progress, false);

                    //不明确进度的进度条
//                    mBuilder.setProgress(0, 0, true);

                    nm.notify(4, mBuilder.build());
                    // 模拟延时
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // When the loop is finished, updates the notification
                mBuilder.setContentText("Download complete");
                // Removes the progress bar
                mBuilder.setProgress(0, 0, false);
                nm.notify(4, mBuilder.build());
            }
        }
        ).start();*/

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
   /*     mHandler.removeCallbacksAndMessages(null);*/
    }

    private void ShowVolleyRequestforVipupdateUserInfo() {

        String url = ApiUrls.REFRESHTOKEN;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Tokenrefreshtoken pressselectpress = gson.fromJson(response.toString(), Tokenrefreshtoken.class);
                        String code = pressselectpress.getCode();
                        if (code.equals("R00001")) {
                            //更新用户信息成功
                            Tokenrefreshtoken.ResponseBean.BodyBean body = pressselectpress.getResponse().getBody();
                            String access_token = body.getAccess_token();
                            String x_auth_token = body.getX_auth_token();
                            String refresh_token = body.getRefresh_token();
                            SpUtils.putParam(getApplicationContext(), "access_token", access_token);
                            SpUtils.putParam(getApplicationContext(), "x_auth_token", x_auth_token);
                            SpUtils.putParam(getApplicationContext(), "refresh_token", refresh_token);
                            LogUtil.d("请求的数据为111access_token=", access_token.toString());
                            LogUtil.d("请求的数据为222x_auth_token=", x_auth_token.toString());
                            LogUtil.d("请求的数据为333refresh_token=", refresh_token.toString());
                            LogUtil.d("请求的调用成功数据为44444desc=", "调用成功".toString());
                        } else {
                            String desc = pressselectpress.getDesc();
                            LogUtil.d("请求的调用失败数据为44444desc=", desc.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("请求的刷新token失败数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("refresh_token", token3);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.i("内存泄漏检测", "====LeakActivity has been recycled!");
    }
}

