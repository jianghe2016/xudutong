package com.xdt.xudutong.homefragment;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.xdt.xudutong.R;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.utils.X5WebView;
import com.xdt.xudutong.view.InstallUtils;
import com.xdt.xudutong.view.SplashActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserActivityy extends Activity {
    /**
     * 作为一个浏览器的示例展示出来，采用android+web的模式
     */
    private X5WebView mWebView;
    private ViewGroup mViewParent;
    private static final String TAG = "SdkDemo";
    public String APK_NAME = "update";
    private boolean mNeedTestPage = false;
    private ProgressBar mPageLoadingProgressBar = null;
    private Handler mhandler;
    private ValueCallback<Uri> uploadFile;
    private Context context;
    private URL mIntentUrl;
    private String urll;
    private URL urlreguest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.x5surfintent);
        context = this;
        Intent intent = getIntent();
        urll = intent.getStringExtra("urll");

        if (intent != null) {
            try {
                mIntentUrl = new URL(intent.getData().toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            } catch (Exception e) {
            }
        }
        //
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }

		/*
         * getWindow().addFlags(
		 * android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */

        mViewParent = (ViewGroup) findViewById(R.id.webView1);
        String jpush_topviewtext = intent.getStringExtra("jpush_topviewtext");
        if (jpush_topviewtext != null && !jpush_topviewtext.isEmpty()) {
            TextView mjpush_topviewtext = (TextView) findViewById(R.id.jpush_topviewtext);
            mjpush_topviewtext.setText(jpush_topviewtext);
        }

        LinearLayout home_erweimasaomiaodetailsback1 = (LinearLayout) findViewById(R.id.home_erweimasaomiaodetailsback);
        home_erweimasaomiaodetailsback1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initBtnListenser();
        if (Patterns.WEB_URL.matcher(urll).matches()) {
            //符合标准
            Log.d("WEB_URLWEB_URL",true+"");
            mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
        } else{
            Log.d("WEB_URLWEB_URL22",false+"");
            //不符合标准
            ToastUtils.getInstance(BrowserActivityy.this).showMessage(urll);
        }


    }

    private void changGoForwardButton(WebView view) {

    }

    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);// new
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
    }

    private void init() {

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));

        initProgressBar();

        mWebView.setWebViewClient(new WebViewClient() { //设置Web视图
            //    显示加载失败时的逻辑
            @Override
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                ToastUtils.getInstance(BrowserActivityy.this).showMessage("加载失败，请检查网络 :" + failingUrl);
                String data = "Page NO FOUND！";
                mWebView.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                // mTestHandler.sendEmptyMessage(MSG_OPEN_TEST_URL);
                mTestHandler.sendEmptyMessageDelayed(MSG_OPEN_TEST_URL, 5000);// 5s?
                if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16)
                    changGoForwardButton(view);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            View myVideoView;
            View myNormalView;
            CustomViewCallback callback;

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         CustomViewCallback customViewCallback) {
                FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2,
                                     JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(null, arg1, arg2, arg3);
            }
        });
        mWebView.setDownloadListener(new MyWebViewDownLoadListener());
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);//允许缩放
        webSetting.setBuiltInZoomControls(true); //原网页基础上缩放
        webSetting.setUseWideViewPort(true);//任意比例缩放
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true); //允许加载javascript
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        if (mIntentUrl == null) {
            mWebView.loadUrl(urll);
        } else {
            mWebView.loadUrl(mIntentUrl.toString());
        }
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }


    private void initBtnListenser() {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16) {

        }
    }

    boolean[] m_selected = new boolean[]{true, true, true, true, false,
            false, true};

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 16)
                    changGoForwardButton(mWebView);
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TbsLog.d(TAG, "onActivityResult, requestCode:" + requestCode
                + ",resultCode:" + resultCode);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent == null || mWebView == null || intent.getData() == null)
            return;
        mWebView.loadUrl(intent.getData().toString());
    }

    @Override
    protected void onDestroy() {
        if (mTestHandler != null)
            mTestHandler.removeCallbacksAndMessages(null);
        if (mWebView != null){
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }

        super.onDestroy();
    }

    public static final int MSG_OPEN_TEST_URL = 0;
    public static final int MSG_INIT_UI = 1;
    private final int mUrlStartNum = 0;
    private int mCurrentUrl = mUrlStartNum;
    private Handler mTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_OPEN_TEST_URL:
                    if (!mNeedTestPage) {
                        return;
                    }

                    String testUrl = "file:///sdcard/outputHtml/html/"
                            + Integer.toString(mCurrentUrl) + ".html";
                    if (mWebView != null) {
                        mWebView.loadUrl(testUrl);
                    }

                    mCurrentUrl++;
                    break;
                case MSG_INIT_UI:
                    init();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private class MyWebViewDownLoadListener implements DownloadListener {

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            new InstallUtils(context, url, APK_NAME, new InstallUtils.DownloadCallBack() {
                @Override
                public void onStart() {
                    Log.i(TAG, "InstallUtils---onStart");
                    finish();
                }

                @Override
                public void onComplete(String path) {
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
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = 2;
                            message.arg1 = progressnumber2;
                            mhandler.sendMessage(message);
                        }
                    });

                }

                @Override
                public void onFail(Exception e) {
                    Log.i(TAG, "InstallUtils---onFail:" + e.getMessage());
                }

            }).downloadAPK();
            mhandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 2) {
                        Notification.Builder builder = new Notification.Builder(BrowserActivityy.this);

                        builder.setTicker("开始下载,请稍候..");
                        builder.setSmallIcon(R.drawable.appliation);//设置图标
                        builder.setContentTitle("我城许昌");//消息标题
                        builder.setContentText("正在下载我城许昌");//消息内容
                        builder.setProgress(100, msg.arg1, false);
                        PendingIntent pendingIntent = PendingIntent.getActivity
                                //通过Notification来创建通知
                                        (BrowserActivityy.this, 1, new Intent(BrowserActivityy.this, SplashActivity.class), 0);
                        builder.setContentIntent(pendingIntent);
                        NotificationManager manager = (NotificationManager) getSystemService
                                (NOTIFICATION_SERVICE);
                        manager.notify(3, builder.build());//通过管理器发起通知
                        System.out.println("使用for来遍历请求跑马灯数据的结果:" + msg.arg1);
                        builder.setAutoCancel(true);
                        if (msg.arg1 >= 95) {
                            manager.cancel(3);

                        }
                    }

                }
            };
        }


    }
}
