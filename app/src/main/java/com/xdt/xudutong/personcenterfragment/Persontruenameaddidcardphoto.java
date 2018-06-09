package com.xdt.xudutong.personcenterfragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Idcardcertification;
import com.xdt.xudutong.crashexception.AppManager;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.NoDoubleClickListener;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ActionSheetDialog;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.frgment.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.xdt.xudutong.utils.Bitmapyasuoutils.getimage;
import static com.xdt.xudutong.utils.EventMsg.TRUENAMEFLAG;

/**
 * Created by Administrator on 2017/5/13.
 */
//此类下方有个小问题，照相机未判断转换Png格式的图片，故不能传送用户png格式的图片
public class Persontruenameaddidcardphoto extends BaseActivity {
    private final int CAMERA1 = 5;
    private final int PICTURE = 6;
    private final int CAMERA2 = 7;
    private final int PICTURE2 = 8;
    private ImageView addphoto1;
    private ImageView addphoto2;
    private Bitmap bm1;
    private Bitmap bm2;
    private String bm1Stringcamera;
    private String bm1Stringpicture;
    private String bm2Stringcamera;
    private String bm2Stringpicture;
    private String access_token;
    private String x_auth_token;
    private LinearLayout person_true__nameaddcardphotoback1;
    private ProgressBar person_truename_addcardphotoprogress1;
    private ImageView add1getimg1;
    private ImageView add2getimg1;
    // 记录文件保存位置
    private String mFilePath1;
    private String mFilePath2;
    private FileInputStream is = null;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_true_name_addidcardphoto);
    }

    @Override
    public void initView() {
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        String FilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath1 = FilePath + "/" + name;
        mFilePath2 = FilePath + "/" + name;
        person_true__nameaddcardphotoback1 = (LinearLayout) findViewById(R.id.person_true__nameaddcardphotoback);
        person_truename_addcardphotoprogress1 = (ProgressBar) findViewById(R.id.person_truename_addcardphotoprogress);
        final String truename1textstring1 = SpUtils.getParam(getApplicationContext(), "truename1textstring", "");
        final String trueidnumber1textstring1 = SpUtils.getParam(getApplicationContext(), "trueidnumber1textstring", "");
        final String truephonenumber1textstring1 = SpUtils.getParam(getApplicationContext(), "truephonenumber1textstring", "");


        addphoto1 = (ImageView) findViewById(R.id.add1);
        addphoto2 = (ImageView) findViewById(R.id.add2);
        //backaround1 = (backaround1) findViewById(R.id.backaroundadd1);
        //backaround2 = (backaround1) findViewById(R.id.backaroundadd2);
        TextView thenext = (TextView) findViewById(R.id.person_true_next);
        //添加要显示的图片
        add1getimg1 = (ImageView) findViewById(R.id.add1getimg);
        add2getimg1 = (ImageView) findViewById(R.id.add2getimg);
        person_true__nameaddcardphotoback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //添加正面图片
        addphoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    showDialog();
                }
            }
        });
        //添加反面图片
        addphoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    showDialog2();
                }
            }
        });
        //下一步
        access_token = SpUtils.getParam(getApplicationContext(), "access_token", "");
        x_auth_token = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        Log.i("认证11access_token==", access_token);
        Log.i("认证22x_auth_token===", x_auth_token);
        //自定义=====设置点击延长时间五秒，五秒内不可重复点击
        thenext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if (fastClick()) {
                    if (bm1 == null || bm2 == null) {
                        ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("请添加身份证照片");
                    } else {
                        person_truename_addcardphotoprogress1.setVisibility(View.VISIBLE);
                        if (bm1Stringcamera != null && !bm1Stringcamera.isEmpty() && bm2Stringcamera != null && !bm2Stringcamera.isEmpty()) {
                            ShowVolleyRequest(truename1textstring1, trueidnumber1textstring1, truephonenumber1textstring1, bm1Stringcamera, bm2Stringcamera);
                            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("正在上传请稍候...");
                        }
                        if (bm1Stringcamera != null && !bm1Stringcamera.isEmpty() && bm2Stringpicture != null && !bm2Stringpicture.isEmpty()) {
                            ShowVolleyRequest(truename1textstring1, trueidnumber1textstring1, truephonenumber1textstring1, bm1Stringcamera, bm2Stringpicture);
                            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("正在上传请稍候...");
                        }
                        if (bm1Stringpicture != null && !bm1Stringpicture.isEmpty() && bm2Stringcamera != null && !bm2Stringcamera.isEmpty()) {
                            ShowVolleyRequest(truename1textstring1, trueidnumber1textstring1, truephonenumber1textstring1, bm1Stringpicture, bm2Stringcamera);
                            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("正在上传请稍候...");
                        }
                        if (bm1Stringpicture != null && !bm1Stringpicture.isEmpty() && bm2Stringpicture != null && !bm2Stringpicture.isEmpty()) {
                            ShowVolleyRequest(truename1textstring1, trueidnumber1textstring1, truephonenumber1textstring1, bm1Stringpicture, bm2Stringpicture);
                            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("正在上传请稍候...");
                        }
                    }
                }
            }
        });
    }

    private void ShowVolleyRequest(final String truename1textstring1, String trueidnumber1textstring1, String truephonenumber1textstring1, String bm1Stringcamera, String bm2Stringcamera) {
        String url = ApiUrls.CERTIFICATIONS;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", truename1textstring1);
        params.put("idcardNo", trueidnumber1textstring1);
        params.put("phoneNo", truephonenumber1textstring1);
        params.put("myFile_z", bm1Stringcamera);
        params.put("myFile_f", bm2Stringcamera);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String code1string;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            code1string = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            Idcardcertification idcardcertification = gson.fromJson(response.toString(), Idcardcertification.class);
                            person_truename_addcardphotoprogress1.setVisibility(View.GONE);
                            //发送实名认证成功
                            EventBus.getDefault().post(new EventMsg(TRUENAMEFLAG,true));
                            Intent intent = new Intent(Persontruenameaddidcardphoto.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Idcardcertification idcardcertification = gson.fromJson(response.toString(), Idcardcertification.class);
                            person_truename_addcardphotoprogress1.setVisibility(View.GONE);
                            String code1 = idcardcertification.getCode();
                            String desc = idcardcertification.getDesc();
                            LogUtil.d("请求的数据为=", code1.toString());
                            LogUtil.d("请求的数据为=", desc.toString());
                            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_truename_addcardphotoprogress1.setVisibility(View.GONE);
                ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", access_token);
                headers.put("x_auth_token", x_auth_token);
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    public void showDialog() {
        new ActionSheetDialog(Persontruenameaddidcardphoto.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 指定拍照
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 加载路径
                                Uri uri = Uri.fromFile(new File(mFilePath1));
                                // 指定存储路径，这样就可以保存原图了
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                // 拍照返回图片
                                startActivityForResult(intent, CAMERA1);
                            }
                        })
                .addSheetItem("从相册中选", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(picture, PICTURE);
                            }
                        }).show();

    }

    public void showDialog2() {
        new ActionSheetDialog(Persontruenameaddidcardphoto.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                // 指定拍照
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 加载路径
                                Uri uri = Uri.fromFile(new File(mFilePath2));
                                // 指定存储路径，这样就可以保存原图了
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                // 拍照返回图片
                                startActivityForResult(intent, CAMERA2);
                            }
                        })
                .addSheetItem("从相册中选", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(picture, PICTURE2);
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA1) {
            // 设置图片
            bm1 = getimage(mFilePath2);
            add1getimg1.setImageBitmap(bm1);
            bm1Stringcamera = DataAnalysetwo.bitmap64toString2(bm1);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA2) {
            // 设置图片
            bm2 = getimage(mFilePath2);
            add2getimg1.setImageBitmap(bm2);
            bm2Stringcamera = DataAnalysetwo.bitmap64toString2(bm2);
        }
        //从系统相册
        if (requestCode == PICTURE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            String substring = imagePath.substring(imagePath.length() - 3);
            if (substring.equals("png")) {
                LogUtil.d("文件的路径为11====", substring.toString());
                showImage(imagePath);
            } else if (substring.equals("jpg")) {
                LogUtil.d("文件的路径为111111====", substring.toString());
                showImagejpg(imagePath);
            } else if (substring.equals("jpeg")) {
                LogUtil.d("文件的路径为1111111====", substring.toString());
                showImagejpg(imagePath);
            }

            c.close();
        }
        if (requestCode == PICTURE2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            String substring = imagePath.substring(imagePath.length() - 3);
            if (substring.equals("png")) {
                LogUtil.d("文件的路径为22====", substring.toString());
                showImage2(imagePath);
            } else if (substring.equals("jpg")) {
                LogUtil.d("文件的路径为22222====", substring.toString());
                showImage2jpg(imagePath);
            } else if (substring.equals("jpeg")) {
                LogUtil.d("文件的路径为222222222222====", substring.toString());
                showImage2jpg(imagePath);
            }
            c.close();
        }
    }

    private void showImage(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm1 = BitmapFactory.decodeFile(imagePath, bfOptions);
        add1getimg1.setImageBitmap(bm1);
        if (bm1 != null) {
            //此处有个小问题，未判断转换jpg格式的图片，故不能传送用户jpg格式的图片
            //获取路径，截取后三位得到是png还是jpg
            bm1Stringpicture = DataAnalysetwo.bitmap64toString(bm1);
        } else {
            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("系统繁忙");
        }
    }

    private void showImagejpg(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm1 = BitmapFactory.decodeFile(imagePath, bfOptions);
        add1getimg1.setImageBitmap(bm1);
        if (bm1 != null) {
            //此处有个小问题，未判断转换jpg格式的图片，故不能传送用户jpg格式的图片
            //获取路径，截取后三位得到是png还是jpg
            bm1Stringpicture = DataAnalysetwo.bitmap64toString2(bm1);
            LogUtil.d("转换成功jpg====", bm1Stringpicture.toString());
        } else {
            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("系统繁忙");
        }
    }

    private void showImage2(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm2 = BitmapFactory.decodeFile(imagePath, bfOptions);
        add2getimg1.setImageBitmap(bm2);
        if (bm2 != null) {
            bm2Stringpicture = DataAnalysetwo.bitmap64toString(bm2);
        } else {
            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("系统繁忙");
        }
    }

    private void showImage2jpg(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm2 = BitmapFactory.decodeFile(imagePath, bfOptions);
        add2getimg1.setImageBitmap(bm2);
        if (bm2 != null) {
            bm2Stringpicture = DataAnalysetwo.bitmap64toString2(bm2);
        } else {
            ToastUtils.getInstance(Persontruenameaddidcardphoto.this).showMessage("系统繁忙");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        distoryBitmap();
        AppManager.getInstance().removeActivity(this);
    }

    public void distoryBitmap() {
        if (null != bm1 && !bm1.isRecycled()) {
            bm1.recycle();
        }
        if (null != bm2 && !bm2.isRecycled()) {
            bm2.recycle();
        }

    }
}