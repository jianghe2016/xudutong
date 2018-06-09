package com.xdt.xudutong.personcenterfragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.IdcardgetCert;
import com.xdt.xudutong.bean.IdcardupdateCert;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ActionSheetDialog;
import com.xdt.xudutong.view.LogUtil;
import com.xdt.xudutong.frgment.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.xdt.xudutong.utils.Bitmapyasuoutils.getimage;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Personitemthreethreefail extends BaseActivity {
    private final int CAMERA1 = 1;
    private final int PICTURE = 2;
    private final int CAMERA2 = 3;
    private final int PICTURE2 = 4;
    private Bitmap bm1;
    private Bitmap bm2;
    private String bm1Stringcamera;
    private String bm2Stringcamera;
    private String bm1Stringpicture;
    private String bm2Stringpicture;
    private ImageView mperson_true_namefailimg1;
    private ImageView mperson_true_namefailimg2;

    private TextView mpersoncenterturename5_next;
    private Boolean userinfo = false;
    private int realId;
    private EditText person_ture_name4_turenametwice1;
    private EditText person_ture_name4_tureidcardtwice1;
    private EditText person_ture_name4_turephonenumbertwice1;
    private ProgressBar person_true_namefailprogressbar1;
    private TextView person_true_namefailmessage1;
    private String token1;
    private String token2;
    private Bitmap turenamebitmap;
    private Bitmap turenamebitmap2;
    // 记录文件保存位置
    private String mFilePath1;
    private String mFilePath2;
    private FileInputStream is = null;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_true_namefail);
    }

    @Override
    public void initView() {
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        String FilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath1 = FilePath + "/" + name;
        mFilePath2 = FilePath + "/" + name;
        person_true_namefailprogressbar1 = (ProgressBar) findViewById(R.id.person_true_namefailprogressbar);
        person_true_namefailmessage1 = (TextView) findViewById(R.id.person_true_namefailmessage);
        LinearLayout mperson_ture_name5_turenameback = (LinearLayout) findViewById(R.id.person_ture_name5_turenameback);
        mperson_true_namefailimg1 = (ImageView) findViewById(R.id.person_true_namefailimg1);
        mperson_true_namefailimg2 = (ImageView) findViewById(R.id.person_true_namefailimg2);


        person_ture_name4_turenametwice1 = (EditText) findViewById(R.id.person_ture_name4_turenametwice);
        person_ture_name4_tureidcardtwice1 = (EditText) findViewById(R.id.person_ture_name4_tureidcardtwice);
        person_ture_name4_turephonenumbertwice1 = (EditText) findViewById(R.id.person_ture_name4_turephonenumbertwice);

        mpersoncenterturename5_next = (TextView) findViewById(R.id.personcenterturename5_next);
        mperson_ture_name5_turenameback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        initData();
    }

    private void initData() {
        person_true_namefailprogressbar1.setVisibility(View.VISIBLE);
        Boolean userinfo = false;
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");

        mpersoncenterturename5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Drawable drawable1 = mperson_true_namefailimg1.getDrawable();
                    Drawable drawable2 = mperson_true_namefailimg2.getDrawable();

                    BitmapDrawable bd = (BitmapDrawable) drawable1;
                    if (bd != null) {
                        bm1 = bd.getBitmap();
                        bm1Stringcamera = DataAnalysetwo.bitmap64toString2(bm1);
                    }
                    BitmapDrawable bd2 = (BitmapDrawable) drawable2;
                    if (bd2 != null) {
                        bm2 = bd2.getBitmap();
                        bm2Stringcamera = DataAnalysetwo.bitmap64toString2(bm2);
                    }


                    //获取用户输入的名字，身份证号,电话号
                    String s = person_ture_name4_turenametwice1.getText().toString();
                    String s1 = person_ture_name4_tureidcardtwice1.getText().toString();
                    String s2 = person_ture_name4_turephonenumbertwice1.getText().toString();
                    if (!s.isEmpty() && !s1.isEmpty() && !s2.isEmpty()) {
                        if (bm1 != null && bm2 != null) {
                            LogUtil.d("更新实名认证realIdstring=", realId + "");
                            //    person_truename_addcardphotoprogress1.setVisibility(View.VISIBLE);
                            if (bm1Stringcamera != null && !bm1Stringcamera.isEmpty() && bm2Stringcamera != null && !bm2Stringcamera.isEmpty()) {
                                person_true_namefailprogressbar1.setVisibility(View.VISIBLE);
                                ShowVolleyRequestforupdate(realId, s, s1, s2, bm1Stringcamera, bm2Stringcamera);
                                ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("正在上传请稍候...");
                            }
                     /*   if (bm1Stringcamera != null && !bm1Stringcamera.isEmpty() && bm2Stringpicture != null && !bm2Stringpicture.isEmpty()) {
                            person_true_namefailprogressbar1.setVisibility(View.VISIBLE);
                            ShowVolleyRequestforupdate(realId, s, s1, s2, bm1Stringcamera, bm2Stringpicture);
                            Toast.makeText(Personitemthreethreefail.this, "正在上传请稍候...", Toast.LENGTH_SHORT).show();
                        }
                        if (bm1Stringpicture != null && !bm1Stringpicture.isEmpty() && bm2Stringcamera != null && !bm2Stringcamera.isEmpty()) {
                            person_true_namefailprogressbar1.setVisibility(View.VISIBLE);
                            ShowVolleyRequestforupdate(realId, s, s1, s2, bm1Stringpicture, bm2Stringcamera);
                            Toast.makeText(Personitemthreethreefail.this, "正在上传请稍候...", Toast.LENGTH_SHORT).show();
                        }
                        if (bm1Stringpicture != null && !bm1Stringpicture.isEmpty() && bm2Stringpicture != null && !bm2Stringpicture.isEmpty()) {
                            person_true_namefailprogressbar1.setVisibility(View.VISIBLE);
                            ShowVolleyRequestforupdate(realId, s, s1, s2, bm1Stringpicture, bm2Stringpicture);
                            Toast.makeText(Personitemthreethreefail.this, "正在上传请稍候...", Toast.LENGTH_SHORT).show();
                        }*/

                        } else {
                            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("请重新添加正反面身份证照片");
                        }
                    } else {
                        ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("输入信息不能为空");
                    }
                }
            }
        });
        ShowVolleyRequestforcard();
     /*   if (userinfo == true) {
            ShowVolleyRequestforturename(token1, token2, realId);
        }*/

        //点击正反面图片的效果
        mperson_true_namefailimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    showDialog1();
                }
            }
        });
        mperson_true_namefailimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    showDialog2();
                }
            }
        });
    }

    private void ShowVolleyRequestforupdate(int realId, String s, String s1, String s2, String bm1Stringpicture, String bm2Stringpicture) {
        String realIdstring = String.valueOf(realId);
        String url = ApiUrls.UPDATECERT;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", s);
        params.put("idcardNo", s1);
        params.put("phoneNo", s2);
        params.put("certId", realIdstring);
        params.put("myFile_z", bm1Stringpicture);
        params.put("myFile_f", bm2Stringpicture);
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
                            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("重新提交成功");
                            person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Personitemthreethreefail.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            IdcardupdateCert idcardupdatecert = gson.fromJson(response.toString(), IdcardupdateCert.class);
                            String desc = idcardupdatecert.getDesc();
                            person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    public void showDialog1() {
        new ActionSheetDialog(Personitemthreethreefail.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);*/
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
        new ActionSheetDialog(Personitemthreethreefail.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                    /*            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA2);*/
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

    //请求个人信息
    private void ShowVolleyRequestforcard() {
        String url = ApiUrls.LOADUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        //    params.put("train_date", trainstartdata);
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
                            userinfo = true;
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            realId = viploaduserinfo.getContent().getData().getRealId();
                            LogUtil.d("请求个人信息成功realId=", realId + "");
                            String certMessage = viploaduserinfo.getContent().getData().getCertMessage();
                            person_true_namefailmessage1.setText(certMessage);
                            LogUtil.d("请求个人信息成功=", "请求个人信息成功");
                            ShowVolleyRequestforturename(realId);
                        } else {
                            ViploadUserInfo viploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                            String desc = viploaduserinfo.getDesc();
                            Toast.makeText(Personitemthreethreefail.this, desc, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
                LogUtil.d("请求的数据为=", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    //请求实名认证信息
    private void ShowVolleyRequestforturename(int realIdstring1) {
        String realIdstring = String.valueOf(realIdstring1);
        String urltruename = ApiUrls.GETCERT;
        Map<String, String> params = new HashMap<>();
        params.put("certId", realIdstring);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
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
                            person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                            IdcardgetCert idcardgetcert = gson.fromJson(response.toString(), IdcardgetCert.class);
                            IdcardgetCert.ResponseBean.BodyBean body = idcardgetcert.getResponse().getBody();

                            String idcardNo = body.getIdcardNo();
                            String name = body.getName();
                            String phoneNo = body.getPhoneNo();
                       /*     //截取身份证号
                            String substring1 = idcardNo.substring(0, 6);
                            String substring2 = idcardNo.substring(idcardNo.length() - 3, idcardNo.length());
                            //截取电话号码
                            String substring3 = phoneNo.substring(0, 3);
                            String substring4 = phoneNo.substring(phoneNo.length() - 4, phoneNo.length());*/
                            person_ture_name4_turenametwice1.setText(name);
                            person_ture_name4_tureidcardtwice1.setText(idcardNo);
                            person_ture_name4_turephonenumbertwice1.setText(phoneNo);
                            //获取正反面照片
                            String obverseImg = body.getObverseImg();
                            String reverseImg = body.getReverseImg();
                            Log.i("实名认证正正面图片", obverseImg);
                            Log.i("实名认证反反面图片", reverseImg);
                            //   try {
                            turenamebitmap = DataAnalysetwo.base64ToBitmap(obverseImg);
                            turenamebitmap2 = DataAnalysetwo.base64ToBitmap(reverseImg);
                            mperson_true_namefailimg1.setImageBitmap(turenamebitmap);
                            mperson_true_namefailimg2.setImageBitmap(turenamebitmap2);
                            LogUtil.d("请求实名认证成功=", "请求实名认证信息成功");
                          /*  } catch (Exception e) {
                                Toast.makeText(Personitemthreethreefail.this, "系统繁忙", Toast.LENGTH_SHORT).show();
                            }*/

                        } else {
                            person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_true_namefailprogressbar1.setVisibility(View.INVISIBLE);
                LogUtil.d("请求的数据为=", error.toString());
                ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
            }
        }) {
            //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            //所以这个方法在此处是不需要的
            //    @Override
            //    protected Map<String, String> getParams() {
            //          Map<String, String> map = new HashMap<String, String>();
            //            map.put("name1", "value1");
            //            map.put("name2", "value2");

            //        return params;
            //    }
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("access_token", token1);
                headers.put("x_auth_token", token2);
                return headers;
            }
        };
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA1) {
            bm1 = getimage(mFilePath1);
            // 设置图片
            mperson_true_namefailimg1.setImageBitmap(bm1);
            bm1Stringcamera = DataAnalysetwo.bitmap64toString2(bm1);

        }
        if (requestCode == CAMERA2 && resultCode == Activity.RESULT_OK) {
            bm2 = getimage(mFilePath2);
            mperson_true_namefailimg2.setImageBitmap(bm2);
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
        mperson_true_namefailimg1.setImageBitmap(bm1);
        //   idcardtrue.setVisibility(View.INVISIBLE);
        if (bm1 != null) {
            //此处有个小问题，未判断转换jpg格式的图片，故不能传送用户jpg格式的图片
            //获取路径，截取后三位得到是png还是jpg
            bm1Stringpicture = DataAnalysetwo.bitmap64toString(bm1);
        } else {
            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
        }
    }

    private void showImagejpg(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm1 = BitmapFactory.decodeFile(imagePath, bfOptions);
        mperson_true_namefailimg1.setImageBitmap(bm1);
        //  idcardtrue.setVisibility(View.INVISIBLE);
        if (bm1 != null) {
            //此处有个小问题，未判断转换jpg格式的图片，故不能传送用户jpg格式的图片
            //获取路径，截取后三位得到是png还是jpg
            bm1Stringpicture = DataAnalysetwo.bitmap64toString2(bm1);
        } else {
            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
        }
    }

    private void showImage2(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm2 = BitmapFactory.decodeFile(imagePath, bfOptions);
        mperson_true_namefailimg2.setImageBitmap(bm2);
        //   idcardfalse.setVisibility(View.INVISIBLE);
        if (bm2 != null) {
            bm2Stringpicture = DataAnalysetwo.bitmap64toString(bm2);
        } else {
            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
        }
    }

    private void showImage2jpg(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        bm2 = BitmapFactory.decodeFile(imagePath, bfOptions);
        mperson_true_namefailimg2.setImageBitmap(bm2);
        //    idcardfalse.setVisibility(View.INVISIBLE);
        if (bm2 != null) {
            bm2Stringpicture = DataAnalysetwo.bitmap64toString2(bm2);
        } else {
            ToastUtils.getInstance(Personitemthreethreefail.this).showMessage("系统繁忙");
        }
    }

}
