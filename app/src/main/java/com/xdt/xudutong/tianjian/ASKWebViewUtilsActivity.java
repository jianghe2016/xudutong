package com.xdt.xudutong.tianjian;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xdt.xudutong.R;
import com.xdt.xudutong.tianjian.zxing.ZxingEvent;
import com.xdt.xudutong.tianjian.zxingg.SecondActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;


public class ASKWebViewUtilsActivity extends Activity implements Handler.Callback {
    private static final int BAIDU_READ_PHONE_STATE = 1;
    private TextView title;
    private ImageView backimg;
    private WebView webview;
    private ProgressBar progressBar;
    private TextView function_textview;
    private List<String> titles = new ArrayList<String>();
    private boolean isback = false;
    private Handler handler;
    //录音方法需要
    protected ImageView speechdel_indicator;
    private HtmlChatInputView chatinputview;
    private boolean isSendSpeecher = false;
    private MP3Recorder mMp3Recorder;
    //图片
    public static final String TAG = "MainActivity";
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    public static final int FILECHOOSER_RESULTCODE = 1;
    private static final int REQ_CAMERA = FILECHOOSER_RESULTCODE + 1;
    private static final int REQ_CHOOSE = REQ_CAMERA + 1;
    private String compressPath = "";
    private String imagePaths;
    private Uri cameraUri;
    private int closeflag = 1;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.askwebviewutilsactivity);
        EventBus.getDefault().register(this);
        initView();
        initListener();
        loadUrl();
        getPermission();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {//点击的是返回键
            if (closeflag == 2) {
                finish();
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private class MyWebChromeClient extends WebChromeClient {

        public boolean onShowFileChooser(
                WebView webView, ValueCallback<Uri[]> filePathCallback,
                FileChooserParams fileChooserParams) {
            if (mFilePathCallback != null) return true;
            mFilePathCallback = filePathCallback;
            selectImage();
            return true;
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null) return;
            mUploadMessage = uploadMsg;
            selectImage();
        }

        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        // For Android  > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileChooser(uploadMsg, acceptType);
        }

    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public final boolean checkSDcard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(this, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    /**
     * 打开照相机
     */
    private void openCarcme() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagePaths = Environment.getExternalStorageDirectory().getPath()
                + "/fuiou_wmp/temp/"
                + (System.currentTimeMillis() + ".jpg");
        // 必须确保文件夹路径存在，否则拍照后无法完成回调
        File vFile = new File(imagePaths);
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        cameraUri = Uri.fromFile(vFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
     /*获取当前系统的android版本号*/
            int currentapiVersion = Build.VERSION.SDK_INT;
            Log.e("currentapiVersion", "currentapiVersion====>" + currentapiVersion);
            if (Build.VERSION.SDK_INT >= 23) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 23);
                    if (mFilePathCallback != null) {
                        Uri[] uris = new Uri[1];
                        uris[0] = Uri.parse("");
                        mFilePathCallback.onReceiveValue(uris);
                        mFilePathCallback = null;
                    } else {
                        mUploadMessage.onReceiveValue(Uri.parse(""));
                        mUploadMessage = null;
                    }
                    return;
                } else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, vFile.getAbsolutePath());
                    Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, REQ_CAMERA);
                }
            } else {
                //上面已经写好的拨号方法
                intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
                startActivityForResult(intent, REQ_CAMERA);
       /*         ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, vFile.getAbsolutePath());
                Uri uri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQ_CAMERA);*/
            }


        }
    }


    /**
     * 拍照结束后
     */
    private void afterOpenCamera() {
        File f = new File(imagePaths);
        addImageGallery(f);
    }

    /**
     * 解决拍照后在相册中找不到的问题
     */
    private void addImageGallery(File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    /**
     * 本地相册选择图片
     */
    private void chosePic() {
//        FileUtils.delFile(compressPath);
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
        String IMAGE_UNSPECIFIED = "image/*";
        innerIntent.setType(IMAGE_UNSPECIFIED); // 查看类型
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, REQ_CHOOSE);
    }

    /**
     * 选择照片后结束
     *
     * @param data
     */
    private Uri afterChosePic(Intent data) {
        if (data == null) {
            return null;
        }
        // 获取图片的路径：
        String[] proj = {MediaStore.Images.Media.DATA};
        // 好像是android多媒体数据库的封装接口，具体的看Android文档
        Uri originalUri = getUri(this, data);
        Cursor cursor = managedQuery(originalUri, proj, null, null, null);
        if (cursor == null) {
            Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
            return null;
        }
        // 按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        // 最后根据索引值获取图片路径
        String path = cursor.getString(column_index);
        if (path != null && (path.endsWith(".png") || path.endsWith(".PNG") || path.endsWith(".jpg") || path.endsWith(".JPG"))) {
            File newFile = FileUtils.compressFile(path, compressPath);
            return Uri.fromFile(newFile);
        } else {
            Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
        }
//        if (path != null ) {
//            File newFile = FileUtils.compressFile(path, compressPath);
//            return Uri.fromFile(newFile);
//        } else {
//            Toast.makeText(this, "图片格式错误", Toast.LENGTH_SHORT).show();
//        }
        return null;
    }

    //小米5拼接uri
    public static Uri getUri(Context context, Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file")) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                        Log.i("urishi", uri.toString());
                    }
                }
            }
        }
        return uri;
    }


    /**
     * 返回文件选择
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        Uri uri = null;
        if (requestCode == REQ_CAMERA) {
            if (resultCode == RESULT_OK) {
                afterOpenCamera();
                uri = cameraUri;
            }
        } else if (requestCode == REQ_CHOOSE) {
            uri = afterChosePic(intent);
        }
        if (resultCode != RESULT_OK) {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            } else {
                mUploadMessage.onReceiveValue(Uri.parse(""));
            }
            mFilePathCallback = null;
            mUploadMessage = null;
            return;
        }
        if (mFilePathCallback != null) {
            Uri[] uris = new Uri[1];
            uris[0] = uri;
            mFilePathCallback.onReceiveValue(uris);
        } else {
            mUploadMessage.onReceiveValue(uri);
        }
        mFilePathCallback = null;
        mUploadMessage = null;
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(RecoderEvent event) {
        chatinputview.setVisibility(View.GONE);
        webview.loadUrl("javascript:cancelAudio()");// 取消录音
    }

    private void initView() {
        handler = new Handler(this);
        function_textview = (TextView) findViewById(R.id.function_textview);
        function_textview.setText("关闭");
        backimg = (ImageView) findViewById(R.id.backImg);
        title = (TextView) findViewById(R.id.title);
        webview = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        chatinputview = (HtmlChatInputView) findViewById(R.id.chatinputview);
        chatinputview.setVisibility(View.GONE);
        speechdel_indicator = (ImageView) findViewById(R.id.speechdel_indicator);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ClipDrawable d = new ClipDrawable(new ColorDrawable(Color.RED),
                Gravity.LEFT, ClipDrawable.HORIZONTAL);
        progressBar.setProgressDrawable(d);
        webview.setWebChromeClient(new MyWebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }


            @Override
            public void onReceivedTitle(WebView view, String t) {
                super.onReceivedTitle(view, t);
                Log.e("TAG", "标题==-" + view.getTitle() + ";;" + view.getUrl());
                if (isback == false) {
                    if (title.getText().toString().length() > 2 && title.getText().toString().substring(0, 1).equals("h")) {
//                        Log.e("TAG", "title截取==" + title.getText().toString().substring(0, 1));
                        title.setText(t);
                    } else {
                        title.setText(view.getTitle());
                    }
                }
                isback = false;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ASKWebViewUtilsActivity.this);
                builder.setTitle("提示");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                result.confirm();
                            }
                        });
                builder.setCancelable(false);
                builder.create();
                builder.show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ASKWebViewUtilsActivity.this);
                builder.setTitle("提示");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                result.confirm();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                result.cancel();
                            }
                        });
                builder.setCancelable(false);
                builder.create();
                builder.show();
                return true;
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber,
                                         String sourceID) {
            }

        });
    }

    private void initListener() {
        backimg.setOnClickListener(listOnClickListener);
        function_textview.setOnClickListener(listOnClickListener);
        chatinputview.setStatusListener(chatInputStatusListener);
        chatinputview.setSendListener(chatInputSendListener);
    }

    /**
     * 加载url
     *
     * @return void
     * @throws
     * @Title: loadUrl
     * @author dongling
     */
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void loadUrl() {
        webview.setWebViewClient(new MyWebViewClient());
        webview.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        webview.setFocusable(true);
        webview.setClickable(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setHapticFeedbackEnabled(true);
        webview.setFocusableInTouchMode(true);
        // 设置可以访问文件
        webview.getSettings().setAllowFileAccess(true);
        webview.setDownloadListener(new MyWebViewDownloadListener());
        webview.addJavascriptInterface(new h5api(), "h5api");
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setDefaultFontSize((int) 15);
//		webview.loadUrl("file:///android_asset/test.html");
//      webview.loadUrl("http://app.medvision.com.cn:18081/SignDocWeb/resident/login.html?name=邹雪曼&idNo=653001198211124422&mobileTel=18711000008&deviceType=android");
//        webview.loadUrl("http://app.medvision.com.cn:18081/SignDocWeb/doctor/index.html?name=%E8%82%96%E4%BC%9F&idNo=210204197008045252&mobileTel=6420832&deviceType=android");
        webview.loadUrl(this.getIntent().getStringExtra("url") + "&deviceType=android");
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                chatinputview.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        return false;
    }

    public class h5api {
        @JavascriptInterface
        public void audioRecord() {
            Log.e("TAG", "调用");
            handler.sendEmptyMessage(1);
        }

        @JavascriptInterface
        public void closeHtml() {
            Log.e("TAG", "调用");
            finish();
        }

        //扫码
        @JavascriptInterface
        public void scan() {
            Intent intent = new Intent(ASKWebViewUtilsActivity.this, SecondActivity.class);
            intent.putExtra("saomaxdt", 1);
            startActivity(intent);
        }

        @JavascriptInterface
        public void shareUrl(String title, String imgurl, String newurl) {
            Log.e("TAG", "调用分享==标题=" + title + ";imgurl==" + imgurl + ";newurl=" + newurl);
            //    AppSecretMobSDK.init(context, "你的AppKey", "你的AppSecret");
            showShare(title,imgurl,newurl);
        }

        @JavascriptInterface
        public void getPhone(String phone) {
            Log.e("TAG", "调用拨打电话==" + phone);
            onCall(phone);
        }
    }

    public void onCall(String mobile) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                return;
            } else {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + mobile));
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.CALL");
            intent.setData(Uri.parse("tel:" + mobile));
            startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(ZxingEvent event) {
        Log.e("TAG", "扫描结果==" + event.result);
        webview.loadUrl("javascript:scanResult('" + event.result + "')");// 调用js函数
}

    class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            title.setText(view.getTitle());
            Log.e("TAG", "webUrl标题===" + view.getTitle() + ";;;" + view.getUrl());
            if (view.getUrl().contains("index.html")) {
                closeflag = 2;
            } else {
                closeflag = 1;
            }
        }

    }

    class MyWebViewDownloadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }

    private final OnClickListener listOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int vID = v.getId();
            if (vID == R.id.backImg) {
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
                isback = true;
            } else if (vID == R.id.function_textview) {
                finish();
            }
        }
    };


    //录音方法需要


    private final ChatInputStatusListener chatInputStatusListener = new ChatInputStatusListener() {

        @Override
        public void onDeleteViewStart(long totalTimeValue) {
//			speechdel_indicator.setVisibility(View.VISIBLE);
            animStep1();

        }

        @Override
        public void onDeleteViewFinish() {
            // TODO Auto-generated method stub
        }
    };

    private void animStep1() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        animationSet.addAnimation(rotateAnimation);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.1f, 0.1f, 0.1f, 0);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animStep2();

            }
        });
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);
        speechdel_indicator.startAnimation(animationSet);
    }

    private void animStep2() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.1f, 0.1f, 0, 0);
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                speechdel_indicator.setVisibility(View.INVISIBLE);
                chatinputview.setVisibility(View.GONE);
                webview.loadUrl("javascript:cancelAudio()");// 取消录音
            }
        });
        speechdel_indicator.startAnimation(animationSet);
    }

    /* 二、录制的流程
     1.Mp3Recorder调用startRecording()开始录制并初始化DataEncoderThread线程，并定期将录制的PCM数据，传入DataEncoderThread中。
     2.在DataEncoderThread里，SimpleLame将Mp3Recorder传入的PCM数据转换成MP3格式并写入文件，其中SimpleLame通过jni对Lame库进行调用
     3.Mp3Recorder调用stopRecording()停止录制，并通知DataEncoderThread线程录制结束，DataEncoderThread将剩余的数据转换完毕。*/
    private final ChatInputSendListener chatInputSendListener = new ChatInputSendListener() {

        @Override
        public void onChatSendVoice(View v, int sendStatus) {
            Log.e("TAG", "录音");
            if (sendStatus == ChatInputConfig.VOICE_Start_OK) {
                isSendSpeecher = false;
                if (null != mMp3Recorder) {
                    Toast.makeText(ASKWebViewUtilsActivity.this, "上次录音还没有完成，请稍后操作", Toast.LENGTH_LONG).show();
                    chatinputview.stopSpeecher(false);
                    webview.loadUrl("javascript:cancelAudio()");// 取消录音
                    chatinputview.setVisibility(View.GONE);
                }
                String fileName = FileUtils.getFilenameByTime("voice_", ".mp3");
                File fileRecorder = FileUtils.createFileInContext(ASKWebViewUtilsActivity.this, "tianjian" + File.separator + "xmpp" + File.separator + "sound"
                        + File.separator + fileName);
                if (null == fileRecorder) {
                    chatinputview.setVisibility(View.GONE);
                    Toast.makeText(ASKWebViewUtilsActivity.this, "没有存储卡，无法进行录音", Toast.LENGTH_LONG).show();
                    chatinputview.stopSpeecher(false);
                    webview.loadUrl("javascript:cancelAudio()");// 取消录音
                }
                mMp3Recorder = new MP3Recorder(fileRecorder);
                mMp3Recorder.setRecorderListener(mp3RecorderListener);
                Log.e("TAG", "录音1");
                mMp3Recorder.start();
                Log.e("TAG", "录音2");
            }
            if (sendStatus == ChatInputConfig.VOICE_End_Cancle) {
                isSendSpeecher = false;
                if (null != mMp3Recorder) {
                    mMp3Recorder.stop();
                    mMp3Recorder = null;
                }
            }
            if (sendStatus == ChatInputConfig.VOICE_End_OK) {
                isSendSpeecher = true;
                if (null != mMp3Recorder) {
                    mMp3Recorder.stop();
                    mMp3Recorder = null;
                }

            }

        }

        @Override
        public void onChatSendText(View v, String msgString) {
        }

        @Override
        public void onChatSendImage(View v) {

        }
    };
    private final MP3RecorderListener mp3RecorderListener = new MP3RecorderListener() {

        @Override
        public void onMp3RecoderStart(boolean isTrueRecorder) {

        }

        @Override
        public void onMp3RecoderSotp(String mp3FilePath, final long length, boolean isTrueRecorder) {
            chatinputview.setVisibility(View.GONE);
            webview.loadUrl("javascript:cancelAudio()");// 取消录音
            if (isSendSpeecher && !TextUtils.isEmpty(mp3FilePath) && isTrueRecorder) {
                if (length >= 1000) {
                    Log.e("TAG", "录音文件地址==" + mp3FilePath);
                    try {
                        Log.e("TAG", "录音文件内容==" + encodeBase64File(mp3FilePath));
                        webview.loadUrl("javascript:sendAudio('" + encodeBase64File(mp3FilePath) + "','" + (length / 1000) + "','" + ".mp3" + "')");// 调用js函数
                    } catch (Exception e) {
                        Log.e("TAG", "录音被catch了");
                        e.printStackTrace();
                    }
//					sendMsgVoice(mp3FilePath, length);
                } else {
                    Toast.makeText(ASKWebViewUtilsActivity.this, "录音失败", Toast.LENGTH_LONG).show();
                }
            }

        }
    };

    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }


    /**
     * 选择图片弹框
     */
    private void initPopupWindow() {

        compressPath = Environment
                .getExternalStorageDirectory()
                .getPath()
                + "/fuiou_wmp/temp";
        new File(compressPath).mkdirs();
        compressPath = compressPath + File.separator
                + "compress.jpg";

        View popupWindow_view = LayoutInflater.from(this).inflate(
                R.layout.photo_item, null);
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ActionBar.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(popupWindow_view);
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(R.style.AnimBottom1);
        ColorDrawable cr = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(cr);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchable(true);
//        popupWindow.setAnimationStyle(R.style.mystyle);
        popupWindow.showAtLocation(popupWindow_view,
                Gravity.BOTTOM, 0, 0);

        Button cancel = (Button) popupWindow_view.findViewById(R.id.to_cancel);
        TextView toPhoto = (TextView) popupWindow_view
                .findViewById(R.id.to_photo);
        TextView toChoose = (TextView) popupWindow_view
                .findViewById(R.id.to_choose);
        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                if (mFilePathCallback != null) {
                    Uri[] uris = new Uri[1];
                    uris[0] = Uri.parse("");
                    mFilePathCallback.onReceiveValue(uris);
                    mFilePathCallback = null;
                } else {
                    mUploadMessage.onReceiveValue(Uri.parse(""));
                    mUploadMessage = null;
                }
            }
        });

        toPhoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                openCarcme();
            }

        });

        toChoose.setOnClickListener(new OnClickListener() {

            @SuppressLint("InlinedApi")
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                chosePic();

            }
        });
    }

    protected final void selectImage() {
        if (!checkSDcard())
            return;
        initPopupWindow();
    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(ASKWebViewUtilsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, BAIDU_READ_PHONE_STATE);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取读取文件权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void showShare(String title, String imgurl, String newurl) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        //oks.setTitle("标题");
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(title);
        // text是分享文本，所有平台都需要这个字段
        //oks.setText("我是分享文本");
        oks.setText(newurl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(title);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        oks.setComment(title);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(newurl);

        // 启动分享GUI
        oks.show(this);
    }
}
