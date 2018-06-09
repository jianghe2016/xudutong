package com.xdt.xudutong.huiminbao;

/**
 * Created by Administrator on 2018/4/19.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xdt.xudutong.R;
import com.xdt.xudutong.base.Base2Activity;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ToastUtils;

public class FundWebViewTest extends Base2Activity {
    private ProgressBar mPageLoadingProgressBar;
    private WebView mWebView;
    private TextView logTextView;
    private boolean loadingstate;
    private  int real;
    private int userId ;
    private Intent mIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundwebview_activity);
        mWebView = (WebView) findViewById(R.id.hmbwebview);
        WebSettings settings =mWebView.getSettings();
        mWebView.getSettings().setDomStorageEnabled(true);
        // 设置与Js交互的权限
        settings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 要WebView插入视频
        settings.setJavaScriptEnabled(true);
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(
                    WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("onPageStarted", url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e("onReceivedError", description + ":" + failingUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPageLoadingProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.e("onLoadResource", url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

        });
        mWebView.loadUrl(ApiUrls.PAGEINFOFUNDPAGE);
        initProgressBar();
        mWebView.addJavascriptInterface(new JsInterface(this), "h5api");
        LinearLayout mfundsfirstactivitytwoback = (LinearLayout) findViewById(R.id.fundsfirstactivity);
        mfundsfirstactivitytwoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void appBuy() {
            //直接跳转购买页面
        }
        @JavascriptInterface
        public void appBuyparam() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loaduser();
                }
            });
        }

        @JavascriptInterface
        public void appRisk() {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("http://192.168.10.124:8020/xudutongWeb/nativeInteraction/riskAbility.html");
                }
            });
        }
        @JavascriptInterface
        public void appAnswer() {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("http://192.168.10.124:8020/xudutongWeb/nativeInteraction/riskEvaluation.html");
                }
            });
        }

        @JavascriptInterface
        public void appBalance() {
            Intent intent = new Intent().setClass(FundWebViewTest.this,FundsBalance.class);
            startActivity(intent);
        }
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(4);
    }

    private void initProgressBar() {
        mPageLoadingProgressBar = (ProgressBar) findViewById(R.id.jsandandroidprogressbar);
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
    }

    public void loaduser() {
        loadingstate = getIntent().getExtras().getBoolean("loadingstate");
        real = getIntent().getExtras().getInt("real");
        userId = getIntent().getExtras().getInt("userid");
        if (loadingstate == true) {
            switch (real) {
                case -1:
                    ToastUtils.getInstance(getApplicationContext()).showMessage("请先进行实名认证");
                    mIntent = new Intent(getApplicationContext(), Personitemthree.class);
                    startActivity(mIntent);
                    break;
                case 0:
                    mIntent = new Intent(getApplicationContext(), Personitemthreetwo.class);
                    startActivity(mIntent);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证正在认证");
                    break;
                case 1:
                    //实名认证成功输入userid,进行走逻辑
                    mWebView.loadUrl("javascript:appBuyparam_js('" + userId+ "')");// 调用js函数
                    break;
                case 2:
                    mIntent = new Intent(getApplicationContext(), Personitemthreethreefail.class);
                    startActivity(mIntent);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证认证失败");
                    break;
                case 3:
                    mIntent = new Intent(getApplicationContext(), Personitemthreethreefail.class);
                    startActivity(mIntent);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("实名认证认证失败");
                    break;
                default:
                    mIntent = new Intent(getApplicationContext(), Personuser_comein.class);
                    startActivity(mIntent);
                    ToastUtils.getInstance(getApplicationContext()).showMessage("请先登录");
                    break;
            }
        } else {
            mIntent = new Intent(getApplicationContext(), Personuser_comein.class);
            startActivity(mIntent);
            ToastUtils.getInstance(getApplicationContext()).showMessage("请先登录");
        }
    }
}


