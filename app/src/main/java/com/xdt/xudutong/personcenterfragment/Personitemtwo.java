package com.xdt.xudutong.personcenterfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.InvokeParam;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.bean.VipuploadHeadImg;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToRoundCorner;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.ActionSheetDialog;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.xdt.xudutong.utils.Bitmapyasuoutils.getimage;


/**
 * Created by Administrator on 2017/5/10.
 */

public class Personitemtwo extends BaseActivity {
    private final int CAMERA = 1;
    private final int PICTURE = 2;
    private ImageView id_person_head_icom;
    private LinearLayout person_center_customheadicom;
    private LinearLayout person_person_infor_usename2;
    private LinearLayout person_person_infor_sex;
    private LinearLayout person_person_infor_home;
    private LinearLayout person_person_infor_birthday;
    private Handler mhandler;
    /*    private Dialog mCameraDialog;*/
    private TextView item_usename2;
    private TextView item_idcard1;
    private TextView item_gender1;
    private TextView item_birthday1;
    private TextView item_usename1;
    private String token1;
    private String token2;
    //点击查看大图
    private Dialog bigimgdialog;
    private ImageView mImageView;
    private String usernamestring;
    // 返回码
    private final int CODE = 1;
    private final int CODEBIG = 4;

    // 记录文件保存位置
    private String mFilePath;
    private Bitmap getRoundCornerbitmap;
    private Bitmap defultbitmap;
    private TextView mItem_home;
    private String mAddre;
    private String mAddressCode;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_inforone);
    }

    @Override
    public void initView() {
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
        String FilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath = FilePath + "/" + name;
        LinearLayout person_infooneback1 = (LinearLayout) findViewById(R.id.person_infooneback);
        person_person_infor_home = (LinearLayout) findViewById(R.id.person_person_infor_home);
        person_center_customheadicom = (LinearLayout) findViewById(R.id.person_center_customheadicom);
        LinearLayout mperson_person_infor_usename20 = (LinearLayout) findViewById(R.id.person_person_infor_usename20);
        LinearLayout mperson_person_infor_usename22 = (LinearLayout) findViewById(R.id.person_person_infor_usename22);
        id_person_head_icom = (ImageView) findViewById(R.id.id_person_head_icom);
        person_infooneback1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mperson_person_infor_usename20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("此信息不可修改");
            }
        });
        mperson_person_infor_usename22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("此信息不可修改");
            }
        });
        defultbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.morentouxiang);
        mItem_home = (TextView) findViewById(R.id.item_home);
        person_person_infor_usename2 = (LinearLayout) findViewById(R.id.person_person_infor_usename21);

        item_idcard1 = (TextView) findViewById(R.id.item_idcard);
        person_person_infor_sex = (LinearLayout) findViewById(R.id.person_person_infor_sex);
        item_gender1 = (TextView) findViewById(R.id.item_gender);
        person_person_infor_birthday = (LinearLayout) findViewById(R.id.person_person_infor_birthday);
        item_birthday1 = (TextView) findViewById(R.id.item_birthday);
        item_usename1 = (TextView) findViewById(R.id.item_usename);
        item_usename2 = (TextView) findViewById(R.id.item_usename2);
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 2) {
                    Object obj = msg.obj;
                    EventBus.getDefault().post(new EventMsg(EventMsg.PERSON_TICKNAMEEDITTEXTSTRING, obj));
                }
            }
        };
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        if (token1 != null && !token1.isEmpty() && !token2.isEmpty() && token2 != null) {
            ShowVolleyRequest();
        } else {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("请重新登录");
            startActivity(new Intent(Personitemtwo.this, Personuser_comein.class));
            finish();
        }
//        选择地址
        person_person_infor_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwo.this, Personwritedetails.class);
                    startActivityForResult(intent, 15);
                }
            }
        });

        //头像点击的按钮
        person_center_customheadicom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                   showDialog();
                }
            }
        });
        //昵称的点击
        person_person_infor_usename2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwo.this, PersoncenterUpdatetickname.class);
                    startActivityForResult(intent, 11);
                }
            }
        });
        //性别选择按钮
        person_person_infor_sex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwo.this, Personsetsex1.class);
                    startActivityForResult(intent, 12);
                }
            }
        });
        //设置选择生日按钮
        person_person_infor_birthday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    Intent intent = new Intent(Personitemtwo.this, Personsetbirthday.class);
                    startActivityForResult(intent, 13);
                }
            }
        });
    }

    private void ShowVolleyRequestHome() {
        LogUtil.e("地址=========","标记00000000000");
        String url = ApiUrls.UPDATEUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("huji_addr", mAddre);
        params.put("huji_code",mAddressCode);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;
                    @Override
                    public void onResponse(JSONObject response) {
                        LogUtil.e("response======",response+"");
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            ToastUtils.getInstance(Personitemtwo.this).showMessage("更新用户信息成功");
                            mItem_home.setText(mAddre);
                        } else {
                            ToastUtils.getInstance(Personitemtwo.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(Personitemtwo.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
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



    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (null != getRoundCornerbitmap) {
                id_person_head_icom.setImageBitmap(getRoundCornerbitmap);
            } else {
                id_person_head_icom.setImageBitmap(defultbitmap);
            }
        } catch (OutOfMemoryError e) {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
        }


    }

    private void ShowVolleyRequest() {
        String url = ApiUrls.LOADUSERINFO;
        //Volley请求网络进行判断;
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
                            Object code1 = response.get("code");
                            code1string = code1.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (code1string.equals("R00001")) {
                            ViploadUserInfo viploaduserinfo = gson.fromJson(response.toString(), ViploadUserInfo.class);
                            ViploadUserInfo.ContentBean.DataBean viploaduserinfodata = viploaduserinfo.getContent().getData();
                            item_usename1.setText(viploaduserinfodata.getUsername());
                            item_usename2.setText(viploaduserinfodata.getNickname());
                            //获取头像信息，进行判断填充
                            Object headImg = viploaduserinfodata.getHeadImg();
                            if (null != headImg && !(headImg + "").isEmpty()) {
                                String headImgstring = String.valueOf(headImg);
                                getRoundCornerbitmap = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(headImgstring), 5);
                                id_person_head_icom.setImageBitmap(getRoundCornerbitmap);
                            } else {
                                id_person_head_icom.setImageBitmap(defultbitmap);
                            }
                            //获取用户名信息进行赋值
                            usernamestring = viploaduserinfodata.getUsername();
                            item_usename1.setText(usernamestring);
                            //获取身份证号信息，进行判断填充
                            Object certId = viploaduserinfodata.getCertId();
                            if (certId != null) {
                                String certIdstring = String.valueOf(certId);
                                item_idcard1.setText(certIdstring);
                            } else {
                                item_idcard1.setText("");
                            }
                            //获取性别信息，进行填充
                            Object gender = viploaduserinfodata.getGender();
                            if (gender != null) {
                                String genderstring = String.valueOf(gender);
                                item_gender1.setText(genderstring);
                            } else {
                                item_gender1.setText("");
                            }
                            //获取生日，进行填充
                            Object birthday = viploaduserinfodata.getBirthday();
                            if (birthday != null) {
                                String birthdaystring = String.valueOf(birthday);
                                LogUtil.e("生日==========",birthdaystring);
                                item_birthday1.setText(birthdaystring);
                            } else {
                                item_birthday1.setText("");
                            }
                            LogUtil.e("现居地===========",viploaduserinfodata.getHuji_addr());
                            if(viploaduserinfodata.getHuji_addr() != null && !"".equals(viploaduserinfodata.getHuji_addr())){
                                mItem_home.setText(viploaduserinfodata.getHuji_addr());
                            }else {
                                mItem_home.setText("");
                            }
                            //initimgdialog();
                            id_person_head_icom.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (fastClick()) {
                                        initimgdialog();
                                        bigimgdialog.show();
                                    }
                                }
                            });
                        } else {
                            ToastUtils.getInstance(Personitemtwo.this).showMessage("会话已过期，请重新登录");
                            SpUtils.putParam(Personitemtwo.this, "loadingstate", false);
                            Intent intent = new Intent(Personitemtwo.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
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


    public void showDialog() {
        new ActionSheetDialog(Personitemtwo.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                             /*   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);*/
                                // 指定拍照
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 加载路径
                                Uri uri = Uri.fromFile(new File(mFilePath));
                                // 指定存储路径，这样就可以保存原图了
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                // 拍照返回图片
                                startActivityForResult(intent, CODEBIG);
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

    //判断返回值，获取从相机或者相册选取的图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (resultCode == 14) {
                final String person_ticknameedittextstring = data.getStringExtra("person_ticknameedittextstring");
                item_usename2.setText(person_ticknameedittextstring);

                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 2;
                        message.obj = person_ticknameedittextstring;
                        mhandler.sendMessage(message);
                    }
                });


            }
        }else if (requestCode == 12) {
            if (resultCode == 15) {
                String person_ticknameedittextstring = data.getStringExtra("sexupdate");
                item_gender1.setText(person_ticknameedittextstring);
            }
        }else if (requestCode==15){
            if (resultCode == 15) {
                mAddre = data.getStringExtra("address");
                mAddressCode = data.getStringExtra("address_code");
//                mItem_home.setText(address);
                ShowVolleyRequestHome();
            }
        }else if (requestCode == 13) {
            if (resultCode == 16) {
                String person_ticknameedittextstring = data.getStringExtra("sexupbirthday");
                item_birthday1.setText(person_ticknameedittextstring);
            }
        }
        if (resultCode == RESULT_OK && requestCode == CODEBIG) {
            //FileInputStream is = null;

            // 获取输入流
            //  is = new FileInputStream(mFilePath);
            Log.e("mFilePath=======",mFilePath);
            Bitmap bitmap = getimage(mFilePath);
            // Bitmap bitmap = getimage(mFilePath);
               /* // 把流解析成bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(is);*/
           /*     FileOutputStream fout = new FileOutputStream(mFilePath);
                String substring = mFilePath.substring(mFilePath.length() - 3);
                if (substring.equals("jpg") || substring.equals("jpeg")) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, fout);
                } else if (substring.equals("png")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 40, fout);
                }*/
            Bitmap bitmap2 = ToRoundCorner.toRoundCorner(bitmap, 20);
            // 设置图片
            id_person_head_icom.setImageBitmap(bitmap2);
            mImageView = getImageView();
            EventBus.getDefault().post(new EventMsg(EventMsg.CAMERA11, bitmap2));
            if (bitmap != null) {
                if (token1 != null && !token1.isEmpty() && !token2.isEmpty() && token2 != null) {
                    String bitmapsreingcamera = DataAnalysetwo.bitmap64toString(bitmap);
                    //Log.e("获取的图片为----", bitmapsreingcamera);
                    ShowVolleyRequest2(bitmapsreingcamera);
                } else {
                    ToastUtils.getInstance(Personitemtwo.this).showMessage("会话已过期，请重新登录");
                    Intent intent = new Intent(Personitemtwo.this, Personuser_comein.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
            }


        }

        //从系统相册
        if (requestCode == PICTURE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Log.e("imagePath=======",imagePath);
            String substring = imagePath.substring(imagePath.length() - 3);
            if (substring.equals("png")) {
                showImage(imagePath);
            } else if (substring.equals("jpg")) {
                showImagejpg(imagePath);
            } else if (substring.equals("jpeg")) {
                showImagejpg(imagePath);
            }
            c.close();
        }
    }


    private void showImage(String imagePath) {
        Bitmap bm = null;
        try {
            bm = BitmapFactory.decodeFile(imagePath);
        } catch (OutOfMemoryError e) {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
        }
        Bitmap bitmap = ToRoundCorner.toRoundCorner(bm, 20);
        id_person_head_icom.setImageBitmap(bitmap);
        EventBus.getDefault().post(new EventMsg(EventMsg.PICTUREPNG, bitmap));
        if (bm != null) {
            if (token1 != null && !token1.isEmpty() && !token2.isEmpty() && token2 != null) {
                String bitmapsreingpicture = DataAnalysetwo.bitmap64toString(bm);
                ShowVolleyRequest2(bitmapsreingpicture);
            } else {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("请重新登录");
            }
        } else {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
        }
    }

    private void showImagejpg(String imagePath) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inSampleSize = 2;   //width，hight设为原来的十分一
        Bitmap bm = null;
        try {
            bm = BitmapFactory.decodeFile(imagePath, bfOptions);
        } catch (OutOfMemoryError e) {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
        }
        if (bm != null) {
            if (token1 != null && !token1.isEmpty() && !token2.isEmpty() && token2 != null) {
                Bitmap bitmap = ToRoundCorner.toRoundCorner(bm, 20);
                id_person_head_icom.setImageBitmap(bitmap);
                String bitmapsreingpicture = DataAnalysetwo.bitmap64toString2(bm);
                EventBus.getDefault().post(new EventMsg(EventMsg.PICTUREJPG, bitmap));
                ShowVolleyRequest2(bitmapsreingpicture);
            } else {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("请重新登录");
            }
        } else {
            ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
        }
    }

    private void ShowVolleyRequest2(final String bitmapsreingpicture) {
        String url = ApiUrls.UPLOADHEADIMG;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("headImg", bitmapsreingpicture);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            VipuploadHeadImg vipuploadheadimg = gson.fromJson(response.toString(), VipuploadHeadImg.class);
                            int flag = vipuploadheadimg.getFlag();
                            String desc = vipuploadheadimg.getDesc();
                            Bitmap bm = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(bitmapsreingpicture), 5);
                            id_person_head_icom.setImageBitmap(bm);
                        } else {
                            ToastUtils.getInstance(Personitemtwo.this).showMessage("会话已过期，请重新登录");
                            Intent intent = new Intent(Personitemtwo.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(Personitemtwo.this).showMessage("系统繁忙");
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

    private void initimgdialog() {
        //大图所依附的dialog
        bigimgdialog = new Dialog(Personitemtwo.this, R.style.AlertDialog_AppCompat_Light);
        mImageView = getImageView();
        bigimgdialog.setContentView(mImageView);

        //大图的点击事件（点击让他消失）
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigimgdialog.dismiss();
            }
        });

        //大图的长按监听
        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //弹出的“保存图片”的Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Personitemtwo.this);
                builder.setItems(new String[]{getResources().getString(R.string.save_picture)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveCroppedImage(((BitmapDrawable) mImageView.getDrawable()).getBitmap());
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    //保存图片
    private void saveCroppedImage(Bitmap bmp) {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/WeiXin/";
        File file = new File(dir);
        if (!file.exists())
            file.mkdir();
        file = new File("/sdcard/temp.jpg".trim());
        String fileName = file.getName();
        String mName = fileName.substring(0, fileName.lastIndexOf("."));
        String sName = fileName.substring(fileName.lastIndexOf("."));

        // /sdcard/myFolder/temp_cropped.jpg
        String newFilePath = "/sdcard/myFolder" + "/" + mName + "_cropped" + sName;
        ToastUtils.getInstance(Personitemtwo.this).showMessage("已保存到" + newFilePath + "/文件夹");
        file = new File(newFilePath);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        Personitemtwo.this.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
    }

    //动态的ImageView
    private ImageView getImageView() {
        ImageView iv = new ImageView(this);
        //宽高
        iv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //设置Padding
        iv.setPadding(20, 20, 20, 20);
        //imageView设置图片
        Drawable drawable1 = id_person_head_icom.getDrawable();
       /* int ic_launcher = R.mipmap.ic_launcher;
        InputStream is = getResources().openRawResource(ic_launcher);
        Drawable drawable = BitmapDrawable.createFromStream(is, null);*/
        iv.setImageDrawable(drawable1);
        iv.setBackgroundColor(getResources().getColor(R.color.blacktext));
        return iv;
    }
/*    private  Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }*/

}



