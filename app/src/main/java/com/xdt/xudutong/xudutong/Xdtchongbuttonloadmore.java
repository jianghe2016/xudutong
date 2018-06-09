package com.xdt.xudutong.xudutong;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.XdtbuttongrouploadmoreRecyclerAdapter;
import com.xdt.xudutong.bean.CheckSearchPwd;
import com.xdt.xudutong.bean.CitygetUserCards;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttonone;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttononenext;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttontwo;
import com.xdt.xudutong.homefragment.Homebuttongroupbuttontwonext;
import com.xdt.xudutong.homefragment.Yingyewangdiandingwei;
import com.xdt.xudutong.personcenterfragment.Personitemfour;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017\8\18 0018.
 */

public class Xdtchongbuttonloadmore extends BaseActivity {


    private LinearLayout mxdt_buttongrouplodamoreback;
    private RecyclerView xdt_buttongrouplodamorerecycle1;
    private int realstates;
    private Boolean loadingstate;
    private String volleygetidcardNo;
    private String token1;
    private String token2;
    private String yue;
    private boolean personssecretstates;
    private boolean myloadflagforgaushi;
    private int cardStatus;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.xdt_buttongrouploadmore);
    }

/*    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {

        switch (event.what) {
            case EventMsg.PERSON_YU_E:
                Object data = event.data;

                break;
            case EventMsg.TRUENAMESTATES:
                //收到实名认证状态
                Object data1 = event.data;
                int i = Integer.parseInt(data1.toString());
                //收到个人中心退出消息
            case EventMsg.TUICHU:
                //未登录，已绑卡，请绑卡

                break;
        }
    }*/

    @Override
    public void initView() {
        mxdt_buttongrouplodamoreback = (LinearLayout) findViewById(R.id.xdt_buttongrouplodamoreback);
        xdt_buttongrouplodamorerecycle1 = (RecyclerView) findViewById(R.id.xdt_buttongrouplodamorerecycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        xdt_buttongrouplodamorerecycle1.setLayoutManager(llm);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        realstates = intent.getIntExtra("realstates", 0);
        personssecretstates = intent.getBooleanExtra("personssecretstates", false);
        loadingstate = intent.getBooleanExtra("loadingstate", false);
        volleygetidcardNo = intent.getStringExtra("volleygetidcardNo");
        myloadflagforgaushi = intent.getBooleanExtra("myloadflagforgaushi", false);
        LogUtil.d("myloadflagforgaushi", myloadflagforgaushi + "");
        LogUtil.d("personssecretstates", personssecretstates + "");
        yue = intent.getStringExtra("yuetext");
        cardStatus = intent.getIntExtra("personcardstates", 0);
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
        int[] xdt_ints = {R.drawable.xdt_yu_e, R.drawable.xdt_kabao, R.drawable.xdt_xiaofei,
                R.drawable.xdt__jilu, R.drawable.xdt_fukuan, R.drawable.xdt_guashi,
                R.drawable.xdt_wangdian};
        String[] xdt_strings = {"许都通余额", "电子钱包", "消费记录", "充值记录", "付款", "挂失", "业务网点"};
        mxdt_buttongrouplodamoreback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });

        XdtbuttongrouploadmoreRecyclerAdapter xdtbuttongrouploadmoreRecyclerAdapter = new XdtbuttongrouploadmoreRecyclerAdapter(Xdtchongbuttonloadmore.this, xdt_ints, xdt_strings);
        xdt_buttongrouplodamorerecycle1.setAdapter(xdtbuttongrouploadmoreRecyclerAdapter);
        xdtbuttongrouploadmoreRecyclerAdapter.setOnItemClickListener(new XdtbuttongrouploadmoreRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (fastClick()) {
                    if (personssecretstates == true) {
                        if (realstates == 1) {
                            //实名认证成功
                            switch (position) {
                                case 0:
                                    switch (cardStatus) {
                                        case 0:
                                            Intent intent0 = new Intent(Xdtchongbuttonloadmore.this, Xdtyuequery.class);
                                            startActivity(intent0);
                                            break;
                                        case 1:
                                            Intent intent = new Intent(Xdtchongbuttonloadmore.this, Xdtyue.class);
                                            intent.putExtra("yuequeryresult", yue + "");
                                            startActivity(intent);
                                            break;
                                        case 3:
                                            Intent intent3 = new Intent(Xdtchongbuttonloadmore.this, Xdtyue.class);
                                            intent3.putExtra("yuequeryresult", yue + "");
                                            startActivity(intent3);
                                            break;
                                        case 11:
                                            Intent intent11 = new Intent(Xdtchongbuttonloadmore.this, Xdtyue.class);
                                            intent11.putExtra("yuequeryresult", yue + "");
                                            startActivity(intent11);
                                            break;
                                        default:
                                            Intent intentdefult = new Intent(Xdtchongbuttonloadmore.this, Xdtyuequery.class);
                                            startActivity(intentdefult);
                                            break;
                                    }

                                    break;
                                case 1:
                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
                                    break;
                                case 2:
                                    //消费记录
                                    switch (cardStatus) {
                                        case 0:
                                            Intent intent4 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttonone.class);
                                            startActivity(intent4);
                                            break;
                                        case 1:
                                            ShowVolleyRequestforcard();
                                            break;
                                        case 3:
                                            ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("该账户处于挂失状态");
                                            break;
                                        case 11:
                                            Intent intent22 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttonone.class);
                                            startActivity(intent22);
                                            break;
                                    }
                                    break;
                                case 3:
                                    //充值记录
                                    switch (cardStatus) {
                                        case 0:
                                            Intent intent0 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttontwo.class);
                                            startActivity(intent0);
                                            break;
                                        case 1:
                                            ShowVolleyRequestforcard2();
                                            break;
                                        case 3:
                                            ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("该账户处于挂失状态");
                                            break;
                                        case 11:
                                            Intent intent33 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttontwo.class);
                                            startActivity(intent33);
                                            break;
                                    }


                                    break;
                                case 4:
                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
                            /*        Intent intent4 = new Intent(Xdtchongbuttonloadmore.this, Xdtchongfukuan.class);
                                    startActivity(intent4);*/
                                    break;
                                case 5:
                                    //挂失页面
                                    if (myloadflagforgaushi == true) {
                                        switch (cardStatus) {
                                            case 0:
                                                Intent intent0 = new Intent(Xdtchongbuttonloadmore.this, Personitemfour.class);
                                                intent0.putExtra("xdtguashi", "卡挂失");
                                                startActivity(intent0);
                                                break;
                                            case 1:
                                                Intent intent = new Intent(Xdtchongbuttonloadmore.this, Personitemfour.class);
                                                intent.putExtra("xdtguashi", "卡挂失");
                                                startActivity(intent);
                                                break;
                                            case 3:
                                                ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("该账户处于挂失状态!");
                                                Intent intent2 = new Intent(Xdtchongbuttonloadmore.this, Personitemfour.class);
                                                intent2.putExtra("xdtguashi", "卡挂失");
                                                startActivity(intent2);
                                                break;
                                            case 11:
                                                ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("该账户处于解绑状态!");
                                                break;
                                        }
                                    } else {
                                        Intent intent = new Intent(Xdtchongbuttonloadmore.this, Personuser_comein.class);
                                        startActivity(intent);
                                    }

                                    break;
                                case 6:
                                    Intent intent6 = new Intent(Xdtchongbuttonloadmore.this, Yingyewangdiandingwei.class);
                                    startActivity(intent6);
                                    break;
                                default:
                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
                            }

                        } else {
                            //不免密的情况下
                            searchdetails(position);
                        }

                    } else {
                    /*Intent intent = new Intent(Xdtchongbuttonloadmore.this, Personuser_comein.class);
                    startActivity(intent);*/
                        searchdetails(position);
                    }
                }
            }

            private void ShowVolleyRequestforcard2() {

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
                                    CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                                    String code = citygetusercards.getCode();
                                    CitygetUserCards.ResponseBean response1 = citygetusercards.getResponse();
                                    if (!response1.equals("")) {
                                        CitygetUserCards.ResponseBean.BodyBean bodyBean = response1.getBody().get(0);
                                        String cityCardno = bodyBean.getCityCardno();
                                        String substring = volleygetidcardNo.substring(volleygetidcardNo.length() - 6, volleygetidcardNo.length());
                                        ShowVolleyRequestforquerysale(cityCardno, substring);
                                    }
                                } else {
                                    CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                                    String desc = citygetusercards.getDesc();
                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage(desc);
                                    Intent intent = new Intent(Xdtchongbuttonloadmore.this, Personuser_comein.class);
                                    startActivity(intent);
                                }
                            }

                            private void ShowVolleyRequestforquerysale(final String cityCardno, String substring) {
                                String url = ApiUrls.VERIFICATION;
                                //Volley请求网络进行判断
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("cardNo", cityCardno);
                                params.put("searchPwd", substring);
                                JSONObject jsonObject = new JSONObject(params);
                                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Gson gson = new Gson();
                                                CheckSearchPwd verificationSearchPwd = gson.fromJson(response.toString(), CheckSearchPwd.class);
                                                String desc = verificationSearchPwd.getDesc().toString();
                                                int flag = verificationSearchPwd.getFlag();
                                                if (flag == 1) {
                                                    //选出当月一号和当前日期
                                                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
                                                    String startdate = sDateFormat.format(new java.util.Date());
                                                    SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                                    String enddate = sDateFormat2.format(new java.util.Date());
                                                    Intent intent = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttontwonext.class);
                                                    intent.putExtra("zhanghao", cityCardno);
                                                    intent.putExtra("startdate21", startdate);
                                                    intent.putExtra("endedata21", enddate);
                                                    intent.putExtra("home_buttongroupbuttononenextheadview1", "充值记录");
                                                    startActivity(intent);
                                                    LogUtil.d("登录成功=", desc);
                                                } else {
                                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("卡号或密码不正确");
                                                }


                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        LogUtil.d("请求的数据为=", error.toString());
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
                                        return headers;
                                    }
                                };
                                ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("系统繁忙");
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

            private void ShowVolleyRequestforcard() {
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
                                    CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                                    String code = citygetusercards.getCode();
                                    CitygetUserCards.ResponseBean response1 = citygetusercards.getResponse();
                                    if (!response1.equals("")) {
                                        CitygetUserCards.ResponseBean.BodyBean bodyBean = response1.getBody().get(0);
                                        String cityCardno = bodyBean.getCityCardno();
                                        String substring = volleygetidcardNo.substring(volleygetidcardNo.length() - 6, volleygetidcardNo.length());
                                        ShowVolleyRequestforquerycardnumber(cityCardno, substring);
                                    }
                                } else {

                                    CitygetUserCards citygetusercards = gson.fromJson(response.toString(), CitygetUserCards.class);
                                    String desc= citygetusercards.getDesc();
                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage(desc);
                                    Intent intent = new Intent(Xdtchongbuttonloadmore.this, Personuser_comein.class);
                                    startActivity(intent);
                                }
                            }

                            private void ShowVolleyRequestforquerycardnumber(final String cityCardno, String substring) {
                                String url = ApiUrls.VERIFICATION;
                                //Volley请求网络进行判断
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("cardNo", cityCardno);
                                params.put("searchPwd", substring);
                                JSONObject jsonObject = new JSONObject(params);
                                JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                Gson gson = new Gson();
                                                CheckSearchPwd verificationSearchPwd = gson.fromJson(response.toString(), CheckSearchPwd.class);
                                                String desc = verificationSearchPwd.getDesc().toString();
                                                int flag = verificationSearchPwd.getFlag();
                                                if (flag == 1) {
                                                    //选出当月一号和当前日期
                                                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-01", Locale.getDefault());
                                                    String startdate = sDateFormat.format(new java.util.Date());
                                                    SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                                    String enddate = sDateFormat2.format(new java.util.Date());
                                                    Intent intent = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttononenext.class);
                                                    intent.putExtra("zhanghao", cityCardno);
                                                    intent.putExtra("startdate11", startdate);
                                                    intent.putExtra("endedata11", enddate);
                                                    startActivity(intent);
                                                    LogUtil.d("登录成功=", desc);
                                                } else {
                                                    ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage(desc);
                                                }


                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("系统繁忙");
                                        LogUtil.d("请求的数据为=", error.toString());
                                    }
                                }) {

                                    @Override
                                    public Map<String, String> getHeaders() {
                                        HashMap<String, String> headers = new HashMap<String, String>();
                                        headers.put("Content-Type", "application/json");
                                        return headers;
                                    }
                                };
                                ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("系统繁忙");
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


        });
    }

    private void searchdetails(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(Xdtchongbuttonloadmore.this, Xdtyuequery.class);
                startActivity(intent);
                break;
            case 1:
                ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
                break;
            case 2:
                Intent intent2 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttonone.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(Xdtchongbuttonloadmore.this, Homebuttongroupbuttontwo.class);
                startActivity(intent3);
                break;
            case 4:
                ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
                       /* Intent intent4 = new Intent(Xdtchongbuttonloadmore.this, Xdtchongfukuan.class);
                        startActivity(intent4);
                        Toast.makeText(Xdtchongbuttonloadmore.this, "实实名认证暂未通过", Toast.LENGTH_SHORT).show();*/
                break;
            case 5:
                //挂失
                if (myloadflagforgaushi == true) {
                    Intent intent5 = new Intent(Xdtchongbuttonloadmore.this, Personitemfour.class);
                    intent5.putExtra("xdtguashi", "卡挂失");
                    startActivity(intent5);
                } else {
                    Intent intent1 = new Intent(Xdtchongbuttonloadmore.this, Personuser_comein.class);
                    startActivity(intent1);
                }
                break;
            case 6:
                Intent intent6 = new Intent(Xdtchongbuttonloadmore.this, Yingyewangdiandingwei.class);
                startActivity(intent6);
                break;
            default:
                ToastUtils.getInstance(Xdtchongbuttonloadmore.this).showMessage("此功能敬请期待");
        }
    }
}
