package com.xdt.xudutong.benefitthepeople;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.sdk.model.Word;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.SettlementaccountOpen;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.ocr.RecognizeService;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.FileUtilCamera;
import com.xdt.xudutong.utils.RSAutilsmy;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.TimerCountshape;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ActionSheetDialog;
import com.xdt.xudutong.view.CameraActivity;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xdt.xudutong.R.id.benefitthepeopleaboutopenanaccountsubmit;

/**
 * Created by Administrator on 2018\1\30 0030.
 */
//惠民宝————开户----此页有测试百度ocr获取照片的demo
public class Benefitthepeopleaboutopenanaccount extends BaseActivity {
    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private boolean hasGotToken = false;
    private EditText mbenefitthepeopleaboutopenanaccounteduittext2;
    private EditText mbenefitthepeopleaboutopenanaccounteduittext3;
    private EditText mbenefitthepeopleaboutopenanaccounteduittext4;
    private EditText mbenefitthepeopleaboutopenanaccounteduittext5;
    private EditText mbenefitthepeopleaboutopenanaccounteduittext1;
    private ImageView mbenefitthepeopleaboutopenanaccountimgtext;
    private Animation myanimation;
    private ImageView monlyProgress;
    private String corpSerno = "";
    private String smsSendNo = "";
    private String rsaopenkey = RSAutilsmy.RSAOPENKEY;
    private String requestusername;
    private Button mBenefitthepeopleaboutopenanaccountsubmit;
    private boolean isChecked = false;

    @Override
    public void initView() {
        final Intent intent = getIntent();
        int cardStatus = intent.getIntExtra("cardStatus", 0);
        //如果无卡，则不用Item3
        LinearLayout mbenefitthepeopleaboutopenanaccountitem3 = (LinearLayout) findViewById(R.id.benefitthepeopleaboutopenanaccountitem3);
        requestusername = SpUtils.getParam(getApplicationContext(), "requestusername", "");
        //如果是0，则说明无卡
        if (cardStatus == 0) {

        }
        final CheckBox mCheckBox = (CheckBox) findViewById(R.id.benefitthepeopleopenanaccountcheckbox);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked = true){
                    isChecked = false;
                }else {
                    isChecked = true;
                }
            }
        });
        ImageView mbenefitthepeopleaboutopenanaccountcamera = (ImageView) findViewById(R.id.benefitthepeopleaboutopenanaccountcamera);
        ImageView mbenefitthepeopleaboutopenanaccountcamera2 = (ImageView) findViewById(R.id.benefitthepeopleaboutopenanaccountcamera2);
        mBenefitthepeopleaboutopenanaccountsubmit = (Button) findViewById(benefitthepeopleaboutopenanaccountsubmit);
        LinearLayout mbenefitthepeopleaboutopenanaccountback = (LinearLayout) findViewById(R.id.benefitthepeopleaboutopenanaccountback);
        mbenefitthepeopleaboutopenanaccounteduittext1 = (EditText) findViewById(R.id.benefitthepeopleaboutopenanaccounteduittext1);
        mbenefitthepeopleaboutopenanaccounteduittext2 = (EditText) findViewById(R.id.benefitthepeopleaboutopenanaccounteduittext2);
        mbenefitthepeopleaboutopenanaccounteduittext3 = (EditText) findViewById(R.id.benefitthepeopleaboutopenanaccounteduittext3);
        mbenefitthepeopleaboutopenanaccounteduittext4 = (EditText) findViewById(R.id.benefitthepeopleaboutopenanaccounteduittext4);
        mbenefitthepeopleaboutopenanaccounteduittext5 = (EditText) findViewById(R.id.benefitthepeopleaboutopenanaccounteduittext5);
        mbenefitthepeopleaboutopenanaccountimgtext = (ImageView) findViewById(R.id.benefitthepeopleaboutopenanaccountimgtext);
        final Button mbenefitthepeoplesenmessagelayoutsendmessage = (Button) findViewById(R.id.benefitthepeoplesenmessagelayoutsendmessage);
        final PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(rsaopenkey);
        monlyProgress = (ImageView) findViewById(R.id.benefitthepeopleaboutopenanaccountprogress);
        myanimation = AnimationUtils.loadAnimation(Benefitthepeopleaboutopenanaccount.this, R.anim.dialog_progress_anim);
        SpannableString ss = new SpannableString("请输入银行卡号");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        mbenefitthepeopleaboutopenanaccounteduittext3.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        //初始化百度OCR
        initAccessTokenWithAkSk();
        mbenefitthepeopleaboutopenanaccountback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //相机OCR
        mbenefitthepeopleaboutopenanaccountcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                showdilog();


            }
        });
        mbenefitthepeopleaboutopenanaccountcamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(Benefitthepeopleaboutopenanaccount.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtilCamera.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_BANKCARD);
            }
        });
        //发送短信按钮
        mbenefitthepeoplesenmessagelayoutsendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1 = mbenefitthepeopleaboutopenanaccounteduittext1.getText().toString();
                String a2 = mbenefitthepeopleaboutopenanaccounteduittext2.getText().toString();
                String a3 = mbenefitthepeopleaboutopenanaccounteduittext3.getText().toString();
                String a4 = mbenefitthepeopleaboutopenanaccounteduittext4.getText().toString();
                String a5 = mbenefitthepeopleaboutopenanaccounteduittext5.getText().toString();
                LogUtil.e("a1============="+a1+"*********a2============="+a2+"###########a3============"+a3+"^^^^^^^^a4========"+a4);
                String phonenumber = mbenefitthepeopleaboutopenanaccounteduittext4.getText().toString();
                Pattern idNumPattern = Pattern.compile("[1][34578]\\d{9}");
                Matcher b2 = idNumPattern.matcher(phonenumber);
                if (!TextUtils.isEmpty(a1) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4) /*&& !TextUtils.isEmpty(a5)*/) {
                    if (b2.matches() == true) {
                        TimerCountshape timer = new TimerCountshape(60000, 1000, mbenefitthepeoplesenmessagelayoutsendmessage, Benefitthepeopleaboutopenanaccount.this);
                        timer.start();
                        String RSAa1 = RSAutilsmy.encryptDataByPublicKey(a1.getBytes(), publicKey);
                        String RSAa2 = RSAutilsmy.encryptDataByPublicKey(a2.getBytes(), publicKey);
                        String RSAa3 = RSAutilsmy.encryptDataByPublicKey(a3.getBytes(), publicKey);
                        String RSAa4 = RSAutilsmy.encryptDataByPublicKey(a4.getBytes(), publicKey);
                        if (!requestusername.isEmpty()){
                            String RSAa5 = RSAutilsmy.encryptDataByPublicKey(requestusername.getBytes(), publicKey);
                            resquestICBCopenusersendmessage(RSAa1,RSAa2,RSAa3,RSAa4,RSAa5);
                        }else{
                            ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("请先登录");
                        }
                    } else {
                        ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("请输入正确的手机号");
                    }
                } else {
                    ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("输入信息不能为空");
                }



            }
        });
        //确定按钮
        //获取公钥


        mBenefitthepeopleaboutopenanaccountsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1 = mbenefitthepeopleaboutopenanaccounteduittext1.getText().toString();
                String a2 = mbenefitthepeopleaboutopenanaccounteduittext2.getText().toString();
                String a3 = mbenefitthepeopleaboutopenanaccounteduittext3.getText().toString();
                String a4 = mbenefitthepeopleaboutopenanaccounteduittext4.getText().toString();
                String a5 = mbenefitthepeopleaboutopenanaccounteduittext5.getText().toString();
                monlyProgress.startAnimation(myanimation);
               /* String name = "城天";
                String idcard = "540102198506020114";
                String mobileNo = "18827212721";
                String bindMedium = "6222020200123701464";
                String corpMediumId = "13271280590";

                String aa = RSAutilsmy.encryptDataByPublicKey(name.getBytes(), publicKey);
                String bb = RSAutilsmy.encryptDataByPublicKey(idcard.getBytes(), publicKey);
                String cc = RSAutilsmy.encryptDataByPublicKey(mobileNo.getBytes(), publicKey);
                String dd = RSAutilsmy.encryptDataByPublicKey(bindMedium.getBytes(), publicKey);
                String ee = RSAutilsmy.encryptDataByPublicKey(corpMediumId.getBytes(), publicKey);
                LogUtil.d("name---------", aa);
                LogUtil.d("idcard-------", bb);
                LogUtil.d("mobileNo-----", cc);
                LogUtil.d("bindMedium---", dd);
                LogUtil.d("xdtcard------", ee);*/

                if (!TextUtils.isEmpty(a1) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4) && !TextUtils.isEmpty(a5)) {
                    String RSAreceivesms = RSAutilsmy.encryptDataByPublicKey(a5.getBytes(), publicKey);
                    String RSAcorpSerno = RSAutilsmy.encryptDataByPublicKey(corpSerno.getBytes(), publicKey);
                    String RSAsmsSendNo = RSAutilsmy.encryptDataByPublicKey(smsSendNo.getBytes(), publicKey);
                    if (mCheckBox.isChecked()){
                        resquestICBCopenusersubmit(RSAreceivesms,RSAcorpSerno, RSAsmsSendNo);
                    }else {
                        ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("请阅读并同意协议");
                        return;
                    }

                } else {
                    ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("输入信息不能为空");
                }



                Intent intent1 = new Intent(Benefitthepeopleaboutopenanaccount.this, Benfitthepeopleopeopenanaccountsuccessful.class);
                startActivity(intent1);


     /*           //字符串公钥，可以直接保存在客户端
                final String PUBLIC_KEY_STR = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArDq+LD0E5aRw9O6oElL2jvb7OGxOACxdcZZnvwN4L+Pv3aM4KSGl4Q7zDSAj/ViaQDC6Y0f3GXiAPIoGPcUnIcm/mpiNZ85NHgoYtgwpP4o0nAEarEUu/YPfdzYAVF7ku+azVJPxelbgxQV0tlamKk0H1COHi3nIdgbusaAvEarMZfFMk25MKB03LrWBjJ9ydDFOjvfokigdxvBDmFhyTsgU1QlEsDPKNFqRS+nrDx6z6j5Xpfeq3P59sQJLE3Hd6YGbUxJB4eVDua5KWS6Fw/5mFWfGBQmdMqm4dUEXlCAYr1U6GVtJJ+amSfzwP1U2D5KD7xCy8N3MJRlgsN2iFwIDAQAB";
                //字符串密钥，通常保存在服务器，这里为了方便演示，直接保存在客户端
                final String PRIVATE_KEY_STR = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsOr4sPQTlpHD07qgSUvaO9vs4bE4ALF1xlme/A3gv4+/dozgpIaXhDvMNICP9WJpAMLpjR/cZeIA8igY9xSchyb+amI1nzk0eChi2DCk/ijScARqsRS79g993NgBUXuS75rNUk/F6VuDFBXS2VqYqTQfUI4eLech2Bu6xoC8Rqsxl8UyTbkwoHTcutYGMn3J0MU6O9+iSKB3G8EOYWHJOyBTVCUSwM8o0WpFL6esPHrPqPlel96rc/n2xAksTcd3pgZtTEkHh5UO5rkpZLoXD/mYVZ8YFCZ0yqbh1QReUIBivVToZW0kn5qZJ/PA/VTYPkoPvELLw3cwlGWCw3aIXAgMBAAECggEABrWPHPgPjcaXI+N8JqKWukECzlLhwv33cepTBkzjTLJLcM3f7TJDXP4RF8zNuhvOfnundyChjpt0G2ehEJzyhk1uql4Q/B88P9RS3ByjKrd+jyk32cgkKXoOpX00DBVaQbud9siAmqxxuxsYTdYYSQORL4Fm0VcgKQDiIYdE7iIx0G+CTO8ClWKNwQsY82GdEd1DizGVz7p747k5doSiSi6Bu7YHXk9d5kiFeGhBRXO2KQt2ZfyVsRbDuKdyWvpMeRKFE8dsSvgEC1Cli8ThGjPM1PLJYmkWRGwFu+Rorua04u6ss6zqEam08pOm0qzfoKJ7ZvaiIhbecjadRC9qeQKBgQDYBeDxPQu1IwA92KtcazSCGXCk4cf3IqlDnlT/kVTdy5RsVa93mq2KAYSlTOq+6b58qPP5RlNx0kbWZUo4eyqy3s7GHcDI9kkSgljUKUboFNvtD4ROMgJ8f8xauEsKb1MOkS940JTJ4OfdzHfzOLj/DTfyxFl58AJGfUyi7hfJQwKBgQDMGiPvzJPFzvOL+jQPbF3B+ttlJLOAmHpgzlkqlWTD3EQC7EW9AZiuIlk0mgxXMWkULvpn2sem3/RwTbUp6omaz2/vWZE9UXUvLXAMWy44zNNaXUP/rROxvpFXuvD63N2BevHzL4t2GDCO54yrXq5vNkjqRBTee8sfqxpOLP68nQKBgQCqIh8h/6Eb3OAQ1XdIh0pIeH7F7OhPVGYY0jdBPJWpRO+1TtquCQ1KFp4Ajg6Ho5IZnfrgRSntB94wdn+48hAT5fTWBZLS811jjXMmTQgCOoNnNgROjYZ1xTUN8f1vz3OLkn7f2O6F/HLAtYt27CKPBTseINQTfBpep8pWu8vR/wKBgBWB46uPSTsc9bkYYogFiVO5lYjw9yFj7/FnjSnZmEazXU9ZinfCRU6EPBY47Xf6svH3iVeMTGGfU+jJp3+FQX7YwRjdvVpSzSBtj1MeAJ7nppXtIg89M8gVJsex4VbuE0FjrT9NEUsefW9xovckAQmjFMfq6LARJ3Rs2VbHkwhZAoGBANOj4V5tJzcZmoav14WfNTs5EPq+W8ZR73NDaffTq5oWytqYaQoG/haISANySHL5mF+PIZ5lKBRHnzO6u2tk+ir/LjmMqJT5WzhtQqAv8jkogkxzD8nyXtiIyRyf8s/oI2UQwdNWIxqQKLIrqGQ2HCuSC1QquZD1EmuIYjE6/w5j";


                //获取公钥
                PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(PUBLIC_KEY_STR);
                //获取私钥
                PrivateKey privateKey = RSAutilsmy.keyStrToPrivate(PRIVATE_KEY_STR);

                //需要加密的数据
                String clearText01 = "大家好，我是朱志强！";

                //公钥加密结果
                String publicEncryptedResult = RSAutilsmy.encryptDataByPublicKey(clearText01.getBytes(), publicKey);
                //私钥解密结果
                String privateDecryptedResult = RSAutilsmy.decryptedToStrByPrivate(publicEncryptedResult, privateKey);

                LogUtil.d("公钥加密，私钥解密测试：\n"
                        + "原文:\n" + clearText01 + "\n"
                        + "公钥加密结果:\n" + publicEncryptedResult + "\n"
                        + "私钥解密结果:\n" + privateDecryptedResult);*/


            }


        });
    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("token还未成功获取");
        }
        return hasGotToken;
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("百度AK，SK方式获取token失败");
            }
        }, Benefitthepeopleaboutopenanaccount.this, "eOH7ySjfr9lUeaVoqLNgyeid", "2NLcsjCd4adRHTLdbBj5LdHgf7BpeYpr");
    }

    private void showdilog() {
        new ActionSheetDialog(Benefitthepeopleaboutopenanaccount.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("身份证正面", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(Benefitthepeopleaboutopenanaccount.this, CameraActivity.class);
                                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                                        FileUtilCamera.getSaveFile(getApplication()).getAbsolutePath());
                                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                            }
                        })
                .addSheetItem("身份证反面", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(Benefitthepeopleaboutopenanaccount.this, CameraActivity.class);
                                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                                        FileUtilCamera.getSaveFile(getApplication()).getAbsolutePath());
                                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                            }
                        }).show();
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benefitthepeopleaboutopenanaccountlayout);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);

    }

    @Override
    public void setSteepStatusBar(boolean isSetStatusBar) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(Benefitthepeopleaboutopenanaccount.this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Benefitthepeopleaboutopenanaccount.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }

    // 调用身份证识别服务请求百度服务器，内含
    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        //百度并没有说明此参数，下面一行是百度解释：
        // 图像数据，支持本地图像文件路径，图像文件二进制数据，要求base64编码后大小不超过1M，最短边至少15px，最长边最大2048px,支持jpg/png/bmp格式
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        //以下代码获取ocr拍照的照片文件，经测试可用，和实名认证用的一个base64转码工具，网络在线测试不能显示，原因未知，但是手机上显示正常
/*        Bitmap bitmap = BitmapFactory.decodeFile(filePath.toString());
        mbenefitthepeopleaboutopenanaccountimgtext.setImageBitmap(bitmap);
        boolean containsjpg = filePath.contains(".jpg");
        boolean containspng = filePath.contains(".png");
        boolean containsjpeg = filePath.contains(".jpeg");
        if (containsjpg==true||containsjpeg==true){
            String s = DataAnalysetwo.bitmap64toString2(bitmap);
            LogUtil.d("containsjpgcontainsjpg",s);
        }
        if (containspng==true){
            String s1 = DataAnalysetwo.bitmap64toString(bitmap);
            LogUtil.d("containsjpgcontainspng",s1);
        }*/


        OCR.getInstance().recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    LogUtil.d("身份证信息", result.toString());
                    Word idNumber = result.getIdNumber();
                    mbenefitthepeopleaboutopenanaccounteduittext1.setText(result.getName() + "");
                    mbenefitthepeopleaboutopenanaccounteduittext2.setText(idNumber + "");
                }
            }

            @Override
            public void onError(OCRError error) {
                LogUtil.d("身份证信息ocr读取失败", error.toString());
                ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("请正确放置证件位置");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE_FRONT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE_BACK && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtilCamera.getSaveFile(getApplicationContext()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }
        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBankCard(FileUtilCamera.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            //infoPopText(result);
                            LogUtil.d("银行卡识别", result);
                            if (!TextUtils.isEmpty(result)) {
                                int n = result.indexOf("类型");
                                if (n != -1) {
                                    String[] n1 = result.split("类型");
                                    String[] n2 = n1[0].split("：");
                                    mbenefitthepeopleaboutopenanaccounteduittext3.setText(n2[1]);
                                } else {
                                    ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("识别失败，请重试");
                                }
                            } else {
                                ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("识别失败，请重试");
                            }

                        }
                    });
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    //请求发送短信接口
    private void resquestICBCopenusersendmessage(String RSAa1, String RSAa2, String RSAa3, String RSAa4, String RSAa5) {
        String url = ApiUrls.ACCOUNTOPEN;
        HashMap<String, String> params = new HashMap<>();
        params.put("custName",RSAa1);
        params.put("certNo",RSAa2);
        params.put("mobileNo",RSAa4);
        params.put("bindMedium",RSAa3);
        params.put("corpMediumId",RSAa5);
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                SettlementaccountOpen settlementaccountOpen = gson.fromJson(response.toString(), SettlementaccountOpen.class);
                String code = settlementaccountOpen.getCode();
                if (code.equals("R00001")) {
                    ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage("正在发送，请注意查收短信");
                    myanimation.cancel();
                    monlyProgress.setVisibility(View.GONE);
                    SettlementaccountOpen.ContentBean.DataBean data = settlementaccountOpen.getContent().getData();
                    //合作方交易单号   短信验证码发送编号，只有启用短信验证码模式时返回
                    corpSerno = data.getCorpSerno();
                    smsSendNo = data.getSmsSendNo();
                } else {
                    String desc = settlementaccountOpen.getDesc();
                    myanimation.cancel();
                    monlyProgress.setVisibility(View.GONE);
                    ToastUtils.getInstance(Benefitthepeopleaboutopenanaccount.this).showMessage(desc);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myanimation.cancel();
                monlyProgress.setVisibility(View.GONE);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return super.getHeaders();
            }
        };
        ApplicationController.getInstance(Benefitthepeopleaboutopenanaccount.this).getRequestQueue().add(jsonRequest);
    }

    //信息确认提交接口
    private void resquestICBCopenusersubmit(String RSAreceivesms,String RSAcorpSerno, String RSAsmsSendNo) {
        String url = ApiUrls.ACCOUNTSCODEVERIFY;
        HashMap<String, String> params = new HashMap<>();
        params.put("corpSernoOriginal",RSAcorpSerno);
        params.put("smsSendNo",RSAsmsSendNo);
        params.put("smsSCode",RSAreceivesms);
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String code = response.get("code").toString();
                    if (code.equals("R00001")) {
                        Intent intent1 = new Intent(Benefitthepeopleaboutopenanaccount.this, Benfitthepeopleopeopenanaccountsuccessful.class);
                        startActivity(intent1);
                    } else {

                    }
                } catch (JSONException e) {


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return super.getHeaders();
            }
        };
        ApplicationController.getInstance(Benefitthepeopleaboutopenanaccount.this).getRequestQueue().add(jsonRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myanimation.cancel();
    }
}
