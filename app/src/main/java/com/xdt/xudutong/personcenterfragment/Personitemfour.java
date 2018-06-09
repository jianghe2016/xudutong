package com.xdt.xudutong.personcenterfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.PersonIemfouradapter;
import com.xdt.xudutong.bean.CitygetUserCards;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Personitemfour extends BaseActivity {
    private RecyclerView person_cardmanagerrecycle1;
    private String token1;
    private String token2;
    private TextView person_cardmanager_canle1;
    private TextView person_cardmanager_submit1;
    private LinearLayout person_cardmanager_selectbutton;
    private String realName;
    private LinearLayout person_cardmanagerrecyclenoinfo1;
    private String username;
    private String xdtguashi;
    private LinearLayout person_cardamanager_dialog1;
    private LinearLayout mperson_card_managerallview;
    private String personmobile;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_card_manage);
    }

    @Override
    public void initView() {
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        personmobile = SpUtils.getParam(getApplicationContext(),"personmobile","");
        Intent intent = getIntent();
        xdtguashi = intent.getStringExtra("xdtguashi");
        LinearLayout person_card_managerback1 = (LinearLayout) findViewById(R.id.person_card_managerback);
        mperson_card_managerallview = (LinearLayout) findViewById(R.id.person_card_managerallview);
        TextView person_card_managertopview1 = (TextView) findViewById(R.id.person_card_managertopview);
        LinearLayout person_cardmanageradd1 = (LinearLayout) findViewById(R.id.person_cardmanageradd);
        person_cardmanagerrecyclenoinfo1 = (LinearLayout) findViewById(R.id.person_cardmanagerrecyclenoinfo);
        //确定取消按钮
        person_cardmanager_selectbutton = (LinearLayout) findViewById(R.id.person_cardmanager_selectbutton);
        person_cardmanager_canle1 = (TextView) findViewById(R.id.person_cardmanager_canle);
        person_cardmanager_submit1 = (TextView) findViewById(R.id.person_cardmanager_submit);
        person_cardmanagerrecycle1 = (RecyclerView) findViewById(R.id.person_cardmanagerrecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Personitemfour.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        person_cardmanagerrecycle1.setLayoutManager(layoutManager);
        person_card_managerback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        //判断是卡挂失还是卡环里页面
        person_cardmanagerrecycle1.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(xdtguashi)) {
            if (xdtguashi.equals("卡挂失")) {
                person_cardmanageradd1.setVisibility(View.INVISIBLE);
                LogUtil.d("卡挂失", "卡挂失");
                ShowVolleyRequestforuserinfo(1);
                ShowVolleyRequest(1);
                person_cardmanager_submit1.setText("确认挂失");
                person_card_managertopview1.setText("卡挂失");
            } else {
                LogUtil.d("卡管理", "卡管理");
                //卡管理
                //请求许都通卡片内容
                ShowVolleyRequest(2);
                //请求个人信息接口，1代表第一个请求，2代表第二次请求
                ShowVolleyRequestforuserinfo(1);
                person_card_managertopview1.setText("卡管理");
                person_cardmanager_submit1.setText("确认解绑");
                person_cardmanageradd1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (fastClick()) {
                            ShowVolleyRequestforuserinfo(2);
                        }
                    }
                });
            }
        } else {
            LogUtil.d("空空空", "空空空");
        }
    }

    private void ShowVolleyRequestforuserinfo(final int ownflag) {
        String urltruename = ApiUrls.LOADUSERINFO;
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, urltruename, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            Gson gson = new Gson();
                            if (code1string.equals("R00001")) {
                                ViploadUserInfo viploaduserinfo = gson.fromJson(response.toString(), ViploadUserInfo.class);
                                ViploadUserInfo.ContentBean.DataBean viploaduserinfodata = viploaduserinfo.getContent().getData();
                                int real = viploaduserinfodata.getReal();
                                realName = viploaduserinfodata.getRealName();
                                username = viploaduserinfodata.getUsername();
                                if (ownflag == 2) {
                                    if (real == 1) {
                                        //请绑卡
                                        Intent intent = new Intent(Personitemfour.this, PersoncenterAddCard.class);
                                        startActivityForResult(intent, 32);
                                    } else if (real == 0) {
                                        //正在认证
                                        ToastUtils.getInstance(Personitemfour.this).showMessage("正在进行实名认证");
                                    } else if (real == -1) {
                                        //未认证
                                        Intent intent = new Intent(Personitemfour.this, Personitemthree.class);
                                        if (!TextUtils.isEmpty(username)) {
                                            intent.putExtra("loadusername", username);
                                        }
                                        startActivity(intent);
                                        ToastUtils.getInstance(Personitemfour.this).showMessage("请先进行实名认证");
                                    } else {
                                        //认证失败
                                        startActivity(new Intent(Personitemfour.this, Personitemthreethreefail.class));
                                        ToastUtils.getInstance(Personitemfour.this).showMessage("请重新进行实名认证");
                                    }
                                }

                            } else {
                                ToastUtils.getInstance(Personitemfour.this).showMessage("会话已过期，请重新登录");
                                Intent intent = new Intent(Personitemfour.this, Personuser_comein.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    //因为卡的种类没有确定，所以条目展示内容暂时无法确定，故recycleview，无法展示内容
    private void ShowVolleyRequest(final int guashiflag) {
        String url = ApiUrls.GETUSERCARDS;
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
                            //如果由内容，集合有数据则，无卡片的背景隐藏，展示数据
                            person_cardmanagerrecyclenoinfo1.setVisibility(View.GONE);
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody = citygetusercards.getResponse().getBody();
                            int status = citygetusercardsbody.get(0).getStatus();
                            LogUtil.d("卡的statusstatus", status + "");
                            Showdata(citygetusercardsbody, guashiflag);
                            person_cardmanager_selectbutton.setVisibility(View.GONE);
                        } else {
                            person_cardmanager_selectbutton.setVisibility(View.GONE);
                            CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                            List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody = citygetusercards.getResponse().getBody();
                            //如果集合为空或者没有内容，则隐藏确定取消按钮，显示无卡片的背景
                            final int i = citygetusercardsbody == null ? 0 : citygetusercardsbody.size();
                            if (i == 0) {
                                person_cardmanager_selectbutton.setVisibility(View.GONE);
                                person_cardmanagerrecyclenoinfo1.setVisibility(View.VISIBLE);
                            }
                            Showdata(citygetusercardsbody, guashiflag);
                            // person_cardmanagerrecycle1.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                person_cardmanager_selectbutton.setVisibility(View.GONE);
                failactivity();
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

    private void Showdata(final List<CitygetUserCards.ResponseBean.BodyBean> citygetusercardsbody, final int guashiflag) {
        PersonIemfouradapter personIemfouradapter = new PersonIemfouradapter(Personitemfour.this, citygetusercardsbody, guashiflag);
        personIemfouradapter.notifyDataSetChanged();
        person_cardmanagerrecycle1.setAdapter(personIemfouradapter);
        personIemfouradapter.setOnItemClickListener(new PersonIemfouradapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final int cardstatus = citygetusercardsbody.get(position).getStatus();
                if (guashiflag == 1) {
                    //对话框的卡挂失，个个人信息
                    TextView person_cardamanager_dialogtext11 = (TextView) view.findViewById(R.id.person_cardamanager_guashidialogtext1);
                    TextView person_cardamanager_dialogtext22 = (TextView) view.findViewById(R.id.person_cardamanager_guashidialogtext2);
                    TextView person_cardamanager_dialogtext44 = (TextView) view.findViewById(R.id.person_cardamanager_guashidialogtext4);
                    //右上角的对号图片
                    final ImageView mperson_cardmanager_selectcancleguashiimg2 = (ImageView) view.findViewById(R.id.person_cardmanager_selectcancleguashiimg2);
                    //卡挂失跳出的个人信息layout
                    person_cardamanager_dialog1 = (LinearLayout) view.findViewById(R.id.person_cardamanagerguashi_dialog);
                    person_cardamanager_dialog1.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(realName)) {
                        person_cardamanager_dialogtext11.setText("姓名 : " + realName);
                    }
                    final String cityCardno = citygetusercardsbody.get(position).getCityCardno();
                    if (!TextUtils.isEmpty(cityCardno)) {
                        person_cardamanager_dialogtext22.setText("卡号 : " + cityCardno);
                    }

                    person_cardamanager_dialogtext44.setText("手机号 : " + personmobile);

                    //下面的选择确定取消
                    if (cardstatus == 1) {
                        mperson_cardmanager_selectcancleguashiimg2.setVisibility(View.GONE);
                        person_cardmanager_selectbutton.setVisibility(View.VISIBLE);
                        person_cardmanager_submit1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (fastClick()) {
                                    //点击了确定按钮
                                    Intent intent = new Intent(Personitemfour.this, Personcardmanagercheck.class);
                                    intent.putExtra("username", username);
                                    intent.putExtra("cityCardno", cityCardno);
                                    startActivityForResult(intent, 101);
                                }
                            }
                        });
                        person_cardmanager_canle1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //点击了取消按钮
                                person_cardamanager_dialog1.setVisibility(View.GONE);
                                person_cardmanager_selectbutton.setVisibility(View.GONE);
                            }
                        });
                    } else if (cardstatus == 3) {
                        mperson_cardmanager_selectcancleguashiimg2.setVisibility(View.VISIBLE);
                        person_cardmanager_selectbutton.setVisibility(View.GONE);
                        ToastUtils.getInstance(Personitemfour.this).showMessage("该账户处于挂失状态");
                        mperson_card_managerallview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (xdtguashi.equals("卡挂失")) {
                                    if (person_cardamanager_dialog1.getVisibility() == View.VISIBLE) {
                                        person_cardamanager_dialog1.setVisibility(View.GONE);
                                    }
                                }
                            }
                        });
                    } else if (cardstatus == 11) {
                        mperson_cardmanager_selectcancleguashiimg2.setVisibility(View.VISIBLE);
                        person_cardmanager_selectbutton.setVisibility(View.GONE);
                        ToastUtils.getInstance(Personitemfour.this).showMessage("该账户处于解绑状态");
                    }

                } else {
                    //对话框的卡管理，个个人信息
                    TextView person_cardamanager_dialogtext11 = (TextView) view.findViewById(R.id.person_cardamanager_dialogtext1);
                    TextView person_cardamanager_dialogtext22 = (TextView) view.findViewById(R.id.person_cardamanager_dialogtext2);
                    //右上角的对号图片
                    final ImageView mperson_cardmanager_selectcancleguashiimg2 = (ImageView) view.findViewById(R.id.person_cardmanager_selectcancleguashiimg2);
                    //卡管理跳出的个人信息layout
                    final LinearLayout person_cardamanager_dialog2 = (LinearLayout) view.findViewById(R.id.person_cardamanager_dialog);
                    person_cardamanager_dialog2.setVisibility(View.VISIBLE);
                    //条目一。二
                    final String cityCardno = citygetusercardsbody.get(position).getCityCardno();
                    if (!TextUtils.isEmpty(realName)) {
                        person_cardamanager_dialogtext11.setText("姓名 : " + realName);
                    }
                    if (!TextUtils.isEmpty(cityCardno)) {
                        person_cardamanager_dialogtext22.setText("卡号 : " + cityCardno);
                    }
                    //确定和取消按钮
                    person_cardmanager_selectbutton.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(cityCardno)) {
                        person_cardmanager_submit1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (fastClick()) {
                                    if (cardstatus == 1) {
                                        //卡状态为1，正常卡可以解绑和挂失
                                        //点击了确定按钮
                                        //请求解绑
                                        Intent intent = new Intent(Personitemfour.this, Personcardmanagerjiebangcheck.class);
                                        intent.putExtra("username", username);
                                        intent.putExtra("cityCardno", cityCardno);
                                        Log.i("发送了解绑", "发送了解绑");
                                        intent.putExtra("cardactivityflag", "jiebang");
                                        startActivityForResult(intent, 101);
                                    } else if (cardstatus == 3) {
                                        mperson_cardmanager_selectcancleguashiimg2.setVisibility(View.VISIBLE);
                                        ToastUtils.getInstance(Personitemfour.this).showMessage("该账户处于挂失状态");
                                    } else if (cardstatus == 11) {
                                        ToastUtils.getInstance(Personitemfour.this).showMessage("该账户处于解绑状态");
                                    }
                                }
                            }
                        });
                    }
                    person_cardmanager_canle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //点击了取消按钮
                            person_cardamanager_dialog2.setVisibility(View.GONE);
                            person_cardmanager_selectbutton.setVisibility(View.GONE);
                        }
                    });


                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 32) {
            if (resultCode == 33) {
                LogUtil.d("收到了返回绑卡状态", "收到了返回绑卡状态");
                ShowVolleyRequest(2);
            }
        }
        if (requestCode == 101) {
            if (resultCode == 103) {
                //卡管理短信页面返回的
                LogUtil.d("收到了解绑，绑卡状态", "收到了解绑，返回绑卡状态");
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Personitemfour.this, R.style.my_dialog);
                final android.app.AlertDialog dialog = builder.create();
                View view = LayoutInflater.from(Personitemfour.this).inflate(R.layout.person_cardmanagercheckbackdialog, null);  //通过LayoutInflater获取布局
                TextView person_fankui_dialog_cancel1 = (TextView) view.findViewById(R.id.person_cardmanagerbackbutton);
                dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;
                int layout_width = (int) ((widthPixels) * 0.8);// 宽度
                int layout_heigth = (int) ((heightPixels) * 0.3);// 宽度
                // int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
                dialog.getWindow().setLayout(layout_width, layout_heigth); //对话框大小应根据屏幕大小调整

                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.x = 0; // 新位置X坐标
                lp.y = -100; // 新位置Y坐标
                dialogWindow.setAttributes(lp);
                dialog.setCanceledOnTouchOutside(false);
                person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                ShowVolleyRequest(2);
            } else if (resultCode == 104) {
                //卡挂失短信页面返回的
                Log.i("卡挂失短信页面返回的", "卡挂失短信页面返回的");
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Personitemfour.this, R.style.my_dialog);
                final android.app.AlertDialog dialog = builder.create();
                View view = LayoutInflater.from(Personitemfour.this).inflate(R.layout.person_cardmanagercheckguashibackdialog, null);  //通过LayoutInflater获取布局
                TextView person_fankui_dialog_cancel1 = (TextView) view.findViewById(R.id.person_cardmanagerguashibackbutton);
                dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                int heightPixels = displayMetrics.heightPixels;
                int layout_width = (int) ((widthPixels) * 0.8);// 宽度
                int layout_heigth = (int) ((heightPixels) * 0.3);// 宽度
                // int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
                dialog.getWindow().setLayout(layout_width, layout_heigth); //对话框大小应根据屏幕大小调整

                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.x = 0; // 新位置X坐标
                lp.y = -100; // 新位置Y坐标
                dialogWindow.setAttributes(lp);
                dialog.setCanceledOnTouchOutside(false);
                person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                ShowVolleyRequest(1);
            }

        }


    }


    //请求失败加载页面
    private void failactivity() {
        setContentView(R.layout.search_empty);
        LinearLayout search_empty_back1 = (LinearLayout) findViewById(R.id.search_empty_back);
        search_empty_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView search_empty_toptext1 = (TextView) findViewById(R.id.search_empty_toptext);
        search_empty_toptext1.setText("卡管理");
    }
}
