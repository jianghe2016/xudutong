package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.xdt.xudutong.R;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.huiminbao.FundsBalance;
import com.xdt.xudutong.huiminbao.Fundsdetails;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.utils.X5WebView;
import com.xdt.xudutong.view.LogUtil;

/**
 * Created by Administrator on 2018\2\22 0022.
 */

public class Fundsfirstactivitytwo extends BaseActivity {

    private ProgressBar mPageLoadingProgressBar;
    private X5WebView mWebView;
//    private WebView hmbwebview;
    private ViewGroup mViewParent;
    private boolean loadingstate;
    private int real;
    private int userId ;

    @Override
    public void initView() {
        init();
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.jsx5layout1);
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
        mViewParent = (ViewGroup) findViewById(R.id.webView2);


    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(4);
    }

    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.jsandandroidprogressbar1);
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
    }

    private void init() {

        mWebView = new X5WebView(this, null);

        //加载html文件
        mWebView.loadUrl(ApiUrls.PAGEINFOFUNDPAGE);
//        mWebView.loadUrl("http://192.168.10.124:8020/xudutongWeb/pageFund/fundIndex.html");
       // mWebView.loadUrl("file:///android_asset/androiddddd.html");//加载本地asset下面的js_java_interaction.html文件
        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));

        initProgressBar();
        LinearLayout mfundsfirstactivitytwoback = (LinearLayout) findViewById(R.id.fundsfirstactivitytwoback);
        mfundsfirstactivitytwoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebView.setWebViewClient(new WebViewClient() { //设置Web视图
            //    显示加载失败时的逻辑
            @Override
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                ToastUtils.getInstance(Fundsfirstactivitytwo.this).showMessage("加载失败，请检查网络 :" + failingUrl);
                setContentView(R.layout.jsx5layout1);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("appBuy")){
                    LogUtil.e("第一种：============","立即买入");
                }else if(url.startsWith("appBuyparam")){
                    LogUtil.e("第二种：*************","传参");
                }else if(url.startsWith("appRisk")){
                    LogUtil.e("第三种：============","答题");
                }else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPageLoadingProgressBar.setVisibility(View.GONE);
         /*       mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e("111111111111", "onReceiveValue value=" + value);
                    }
                });*/
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
            IX5WebChromeClient.CustomViewCallback callback;

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         IX5WebChromeClient.CustomViewCallback customViewCallback) {
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
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
      //  webSetting.setSupportZoom(true);//允许缩放
     //   webSetting.setBuiltInZoomControls(true); //原网页基础上缩放
      //  webSetting.setUseWideViewPort(true);//任意比例缩放
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(new h5api(), "h5api");
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

        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }
    @Override
    protected void onDestroy() {
        if (mWebView != null){
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }

        super.onDestroy();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    public class h5api {
        @JavascriptInterface
        public void appBuy() {
            System.out.println("--------进入买入交互1");
//            loaduser();
            Intent intent = new Intent(Fundsfirstactivitytwo.this,FundsBalance.class);
            startActivity(intent);
        }
        @JavascriptInterface
        public void appBuyparam_android() {
            Intent intent = getIntent();
            userId = intent.getIntExtra("userid",0);
            System.out.println("--------进入买入交互5"+userId);
//            hmbwebview.loadUrl("javascript:appBuyparam_js('" + userId+ "')");// 调用js函数
            mWebView.loadUrl("javascript:appBuyparam_js('" + userId+ "')");// 调用js函数

//            Intent intent = new Intent(Fundsfirstactivitytwo.this,FundsBalance.class);
//            startActivity(intent);
        }
        @JavascriptInterface
        public void appDetail() {
            Intent intent = new Intent(Fundsfirstactivitytwo.this,Fundsdetails.class);
            startActivity(intent);
        }

    }
    public void loaduser() {
        Intent intent = getIntent();
        loadingstate = intent.getBooleanExtra("loadingstate",false);
        real = intent.getIntExtra("real",5);


            System.out.println("--------进入买入交互2"+real);
        if (loadingstate == true) {
            switch (real) {
                case -1:
                    ToastUtils.getInstance(getApplicationContext()).showMessage("请先进行实名认证");
                    Intent intent1 = new Intent(getApplicationContext(), Personitemthree.class);
                    startActivity(intent1);
                    break;
                case 0:
                    Intent intent2 = new Intent(getApplicationContext(), Personitemthreetwo.class);
                    startActivity(intent2);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证正在认证");
                    break;
                case 1:
                    //实名认证成功输入userid,进行走逻辑
                    System.out.println("--------进入买入交互3");
                    mWebView.loadUrl("javascript:appBuyparam('" + userId+ "')");// 调用js函数
                        /*Intent intent_success = new Intent();
                        intent.setAction("android.intent.action.userId");
                        intent.setData(Uri.parse("userId:" + userId));*/
//                        Intent intent_success = new Intent(FundsfirstH5activity.this,FundsBalance.class);
//                        intent.putExtra("userid", userid);
//                        startActivity(intent_success);
                    break;
                case 2:
                    Intent intent3 = new Intent(getApplicationContext(), Personitemthreethreefail.class);
                    startActivity(intent3);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证认证失败");
                    break;
                case 3:
                    Intent intent4 = new Intent(getApplicationContext(), Personitemthreethreefail.class);
                    startActivity(intent4);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证认证失败");
                    break;
                default:
                    Intent intent5 = new Intent(getApplicationContext(), Personuser_comein.class);
                    startActivity(intent5);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("请先登录");
                    break;
            }
        } else {
            Intent intent2 = new Intent(getApplicationContext(), Personuser_comein.class);
            startActivity(intent2);
            ToastUtils.getInstance(getApplicationContext()).showMessage("请先登录");
        }
    }
}
