package com.xdt.xudutong.personcenterfragment;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.GetLatestVersion;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.InstallUtils;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.view.SplashActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Personitemsix extends BaseActivity {
    public String APK_NAME = "update";
    private String TAG = "InstallUtils";
    private Context context;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_about);
    }

    @Override
    public void initView() {
        context = this;
        LinearLayout personaboutback1 = (LinearLayout) findViewById(R.id.personaboutback);
        TextView person_version_update1 = (TextView) findViewById(R.id.person_version_update);
        String versionName = getVersionName();
        String s = versionName.replaceAll("\\.", "");
        final int intapkversionname = Integer.parseInt(s);
        LogUtil.d("当前版本号为=intapkversionname", intapkversionname+"");
        TextView person_about_version = (TextView) findViewById(R.id.person_about_version);
        person_about_version.setText("V  " + versionName);

        LinearLayout kefucall = (LinearLayout) findViewById(R.id.kefucall);
        personaboutback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });

        person_version_update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //版本更新的点击
                if (fastClick()) {
                    ShowVolleyRequesforupdate(intapkversionname);
                }
            }
        });

        kefucall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Personitemsix.this);
                builder.setMessage("许都通-让您的生活更轻松");
                builder.setTitle("确定拨打电话？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "03747021111");
                        intent.setData(data);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });
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

    private void ShowVolleyRequesforupdate(final int intapkversionname) {
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
                            String url1 = getlatestversiondata.getUrl();
                            try {
                                ShowData(intapkversionname,version, url1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtils.getInstance(Personitemsix.this).showMessage("获取更新信息失败");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemsix.this).showMessage("系统繁忙");
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

    //请求网络获取版本号进行对比
    private void ShowData(int intapkversionname, String internetversion, final String url1) throws Exception {
        String s2 = internetversion.replaceAll("\\.", "");
        int intinternetversion = Integer.parseInt(s2);
        LogUtil.d("intinternetversion",intinternetversion+"");
        if (intinternetversion<=(intapkversionname)) {
            //展示对话框
            Toast toast = Toast.makeText(getApplicationContext(), "当前已是最新版本", Toast.LENGTH_SHORT);
            Display display = getWindowManager().getDefaultDisplay();
            // 获取屏幕高度
            int height = display.getHeight();
            //设置吐司的位置
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout linearLayout = (LinearLayout) toast.getView();
            TextView messageTextView = (TextView) linearLayout.getChildAt(0);
            messageTextView.setTextSize(20);
            toast.show();
        } else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Personitemsix.this, R.style.my_dialogupdateversion);
            final android.app.AlertDialog dialog = builder.create();
            View view = LayoutInflater.from(Personitemsix.this).inflate(R.layout.person_updateversiondialog, null);  //通过LayoutInflater获取布局
            LinearLayout person_versionname_update_dialogcancle1 = (LinearLayout) view.findViewById(R.id.person_versionname_update_dialogcancle);
            TextView person_versionname_update_dialogtext = (TextView) view.findViewById(R.id.person_versionname_update_dialogtext);
            TextView person_versionname_update_dialog1 = (TextView) view.findViewById(R.id.person_versionname_update_dialogsubmit);
            dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int widthPixelss = displayMetrics.widthPixels;
            int heightPixelss = displayMetrics.heightPixels;
            int layout_width = (int) ((widthPixelss) * 0.6);// 宽度
            int layout_heigth = (int) ((heightPixelss) * 0.4);// 高度
            dialog.getWindow().setLayout(layout_width, layout_heigth); //对话框大小应根据屏幕大小调整
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0; // 新位置X坐标
            lp.y = -100; // 新位置Y坐标
            dialogWindow.setAttributes(lp);
            person_versionname_update_dialogtext.setText("V" + internetversion);
            person_versionname_update_dialogcancle1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            person_versionname_update_dialog1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    ToastUtils.getInstance(Personitemsix.this).showMessage("开始下载新版本");
                    downloadApk(url1);
                }
            });
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            LogUtil.d("展示更新版本对话框=", "展示更新版本对话框");

        }
    }

    private void downloadApk(String url1) {
        new InstallUtils(context, url1, APK_NAME, new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "InstallUtils---onStart");

            }

            @Override
            public void onComplete(String path) {
                Log.i(TAG, "InstallUtils---onComplete:" + path);
                InstallUtils.installAPK(context, path, getPackageName() + ".fileProvider", new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        ToastUtils.getInstance(Personitemsix.this).showMessage("正在安装程序");
                    }

                    @Override
                    public void onFail(Exception e) {
                        ToastUtils.getInstance(Personitemsix.this).showMessage("安装失败");
                    }
                });

            }

            @Override
            public void onLoading(long total, long current) {
                Log.i(TAG, "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                final int progressnumber2 = (int) (current * 100 / total);
                Notification.Builder builder = new Notification.Builder(ApplicationController.getContext());
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
                }
            }

            @Override
            public void onFail(Exception e) {
                Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
            }

        }).downloadAPK();
    }
}
