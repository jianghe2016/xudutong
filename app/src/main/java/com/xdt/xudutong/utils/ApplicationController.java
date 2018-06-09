package com.xdt.xudutong.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mobstat.StatService;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.orhanobut.logger.LogLevel;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.xdt.xudutong.crashexception.CrashHandler;
import com.xdt.xudutong.view.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Stack;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/5/25.
 */

public class ApplicationController extends MultiDexApplication {

    private static Context mContext;
    private SPManager mSPManager;
    public static Bitmap mBitmap = null;
    public static String mPopWindow = "-1";
    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyPatterns";
    /**
     * Global request queue for Volley
     */
    private RequestQueue mRequestQueue;
    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static ApplicationController mInstance;
    private static Stack<Activity> activityStack;
    private HttpsUtils.SSLParams sslParams3;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        //百度统计
        StatService.start(this);
        mSPManager = SPManager.getInstance(this);
        handleSSLHandshake();
        initLogger();
        ZXingLibrary.initDisplayOpinion(this);
        // Normal app init code...
        //极光推送初始化
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        //初始化全局异常管理
       CrashHandler.getInstance().init(this);
        //初始化地图
        //x5内核初始化回调
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean b) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtil.d("app", " onViewInitFinished is " + b);
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(this, cb);
        // setupLeakCanary();
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        try {
//            sslParams3 = HttpsUtils.getSslSocketFactory(getApplicationContext().getAssets().open("www.xudutong.com.cer"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.HEADERS);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
//        builder.sslSocketFactory(sslParams3.sSLSocketFactory);
        OkGo.getInstance().init(this)                       //必须调用初始化
                //.setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                        //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0


    }

 /*   private void setupLeakCanary() {
        LeakCanary.install(this);
    }*/

    public ApplicationController() {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(ApplicationController.this);
    }


    public static Context getContext() {
        return mContext;
    }


    /**
     * @return ApplicationController singleton instance
     */
    // 用于返回一个VolleyController单例
    public static ApplicationController getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ApplicationController.class) {
                mInstance = new ApplicationController();
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            synchronized (ApplicationController.class) {
                mRequestQueue = Volley.newRequestQueue(mContext);
            }
        }
        return mRequestQueue;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * Activity关闭时，删除Activity列表中的Activity对象
     */
    public void removeActivity(Activity a) {
        activityStack.remove(a);
    }

    public void init() {
        //设置该CrashHandler为程序的默认处理器
        CrashHandler catchExcep = new CrashHandler();
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    public void saveCode(String code) {
        mSPManager.saveCode(code);
    }

    public String getCode() {
        return mSPManager.getCode();
    }

    public void saveID_Num(String id_num) {
        mSPManager.saveCode(id_num);
    }

    public String getID_Num() {
        return mSPManager.getID_Num();
    }
    /**
     * 初始化Logger日志
     */
    private void initLogger(){
        com.orhanobut.logger.Logger.init("catroom")
                .logLevel(LogLevel.FULL);
    }
}




