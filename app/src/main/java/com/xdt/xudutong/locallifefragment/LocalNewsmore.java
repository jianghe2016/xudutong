package com.xdt.xudutong.locallifefragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xdt.xudutong.R;
import com.xdt.xudutong.adapder.LocalNewsmoreAdapter;
import com.xdt.xudutong.bean.PresscollectPress;
import com.xdt.xudutong.bean.PressselectPress;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017\6\30 0030.
 */

public class LocalNewsmore extends BaseActivity {
    private XRecyclerView locallifenewsmoreXRecyclerView1;
    private int pageNum = 2;
    private LocalNewsmoreAdapter localnewsmoreadapter;
    private ProgressBar locallife_newsmoreprogressbar1;
    private LinearLayout locallifenewsmoreback1;
    private LinearLayout mloacl_life_newsmoreview;
    private String token1;
    private String token2;
    private List<PressselectPress.ContentBean.DataBean> contentlis1;
    private List<PressselectPress.ContentBean.DataBean> contentlist2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.locallife_newsmore);
    }


    @Override
    public void initView() {
        locallifenewsmoreback1 = (LinearLayout) findViewById(R.id.locallifenewsmoreback);
        mloacl_life_newsmoreview = (LinearLayout) findViewById(R.id.loacl_life_newsmoreview);
        locallife_newsmoreprogressbar1 = (ProgressBar) findViewById(R.id.locallife_newsmoreprogressbar);
        locallifenewsmoreXRecyclerView1 = (XRecyclerView) findViewById(R.id.locallifenewsmoreXRecyclerView);
        setxrecycleview();
        LinearLayoutManager layoutManager = new LinearLayoutManager(LocalNewsmore.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        locallifenewsmoreXRecyclerView1.setLayoutManager(layoutManager);
        locallifenewsmoreXRecyclerView1.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        locallifenewsmoreXRecyclerView1.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        locallifenewsmoreXRecyclerView1.setArrowImageView(R.drawable.ic_loading_rotate);
        initData();
    }

    private void setxrecycleview() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        locallifenewsmoreXRecyclerView1.setLayoutManager(layoutManager);
        contentlis1 = new ArrayList<PressselectPress.ContentBean.DataBean>();
        contentlist2 = new ArrayList<PressselectPress.ContentBean.DataBean>();

        locallifenewsmoreXRecyclerView1.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        locallifenewsmoreXRecyclerView1.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        locallifenewsmoreXRecyclerView1.setArrowImageView(R.drawable.ic_loading_rotate);
        //homeMerchantrecordrecycleveiwAdapter1 = new HomeMerchantrecordrecycleveiwAdapter1(getmerchantslecturl);
        localnewsmoreadapter = new LocalNewsmoreAdapter();
        locallifenewsmoreXRecyclerView1.setAdapter(localnewsmoreadapter);
        locallifenewsmoreXRecyclerView1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        pageNum = 1;
                        if (null != contentlis1) {
                            contentlis1.clear();
                        }
                        ShowVolleyRequest2(1, 15, 1);
                        localnewsmoreadapter.notifyDataSetChanged();
                        locallifenewsmoreXRecyclerView1.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                LogUtil.d("触发了滚动加载", "触发了滚动加载");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        ShowVolleyRequest2(pageNum, 7, 2);
                        locallifenewsmoreXRecyclerView1.loadMoreComplete();
                    }
                }, 2000);
                pageNum++;
            }
        });
        // 点击进入下一页面按钮的实现，其中有实现自己adapter里边的接口
        localnewsmoreadapter.setOnItemClickLitener(new LocalNewsmoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TextView textView, int position) {
                Intent intent = new Intent(LocalNewsmore.this, LocalNewsmoreDetails.class);
                int id = contentlis1.get(position - 1).getID();
                String s = String.valueOf(id);
                intent.putExtra("newsdetailsid", s);
                startActivity(intent);
            }
        });
        //收藏按钮的实现，其中有实现自己adapter里边的接口
        localnewsmoreadapter.setthreeClicklistener(new LocalNewsmoreAdapter.OnitemthreeListener() {
            @Override
            public void mitemthreeClick(ImageView itemthree, String isCollect, int position) {
                int id = contentlis1.get(position - 1).getID();
                String s = String.valueOf(id);
                Drawable.ConstantState constantState = itemthree.getDrawable().getConstantState();
                Drawable drawable1 = LocalNewsmore.this.getResources().getDrawable(R.drawable.locallifenewsmorecollect2);
                Drawable.ConstantState constantState1 = drawable1.getConstantState();
                Drawable drawable2 = LocalNewsmore.this.getResources().getDrawable(R.drawable.locallifenewsmorecollect);
                Drawable.ConstantState constantState2 = drawable2.getConstantState();
                if (!token1.isEmpty()) {
                    if (constantState.equals(constantState1)) {
                        //设置上去取消按钮
                        itemthree.setImageDrawable(drawable2);
                        ShowVolleyRequestforcollect2(s, itemthree);
                    } else {
                        //设置上去成功按钮
                        itemthree.setImageDrawable(drawable1);
                        ShowVolleyRequestforcollect(s, itemthree);
                    }
                } else {
                    Intent intent = new Intent(LocalNewsmore.this, Personuser_comein.class);
                    startActivity(intent);
                    finish();
                    ToastUtils.getInstance(LocalNewsmore.this).showMessage("会话已过期，请重新登录");
                }

            }
        });
    }

    private void initData() {
        locallifenewsmoreback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        locallife_newsmoreprogressbar1.setVisibility(View.VISIBLE);
        ShowVolleyRequest2(1, 15, 1);
        token1 = SpUtils.getParam(getApplicationContext(), "access_token", "");
        token2 = SpUtils.getParam(getApplicationContext(), "x_auth_token", "");
    }

    private void ShowVolleyRequestforcollect(String s, final ImageView itemthree) {
        String url2 = ApiUrls.COLLECTPRESS;
        //收藏成功
        Map<String, String> params = new HashMap<String, String>();
        params.put("collectid", s);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url2, jsonObject,
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
                            PresscollectPress presscollectpress = gson.fromJson(response.toString(), PresscollectPress.class);
                            String desc = presscollectpress.getDesc();

                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LocalNewsmore.this, R.style.my_dialog);
                            final android.app.AlertDialog dialog = builder.create();
                            View view = LayoutInflater.from(LocalNewsmore.this).inflate(R.layout.home_collectnewssuccess, null);  //通过LayoutInflater获取布局
                            LinearLayout person_fankui_dialog_cancel1 = (LinearLayout) view.findViewById(R.id.home_collectsuccess_dialog_cancel);
                            dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
                            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                            int widthPixels = displayMetrics.widthPixels;
                            int layout_width = (int) ((widthPixels) * 0.8);// 宽度
                            //  lp.height = (int) ((widthPixels) * 0.6);
                            int layout_height = WindowManager.LayoutParams.WRAP_CONTENT;// 高度
                            dialog.getWindow().setLayout(layout_width, layout_height); //对话框大小应根据屏幕大小调整

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
                        } else {
                            Intent intent = new Intent(LocalNewsmore.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                            ToastUtils.getInstance(LocalNewsmore.this).showMessage("会话已过期，请重新登录");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(LocalNewsmore.this).showMessage("系统繁忙");
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

    private void ShowVolleyRequestforcollect2(String s, final ImageView itemthree) {
        String url3 = ApiUrls.DELETECOLLECTPRESS;
        //取消收藏
        Map<String, String> params = new HashMap<String, String>();
        params.put("collectid", s);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject,
                new Response.Listener<JSONObject>() {
                    private String codestring;

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Object code = response.get("code");
                            codestring = code.toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (codestring.equals("R00001")) {
                            ToastUtils.getInstance(LocalNewsmore.this).showMessage("取消成功");
                        } else {
                            Intent intent = new Intent(LocalNewsmore.this, Personuser_comein.class);
                            startActivity(intent);
                            finish();
                            ToastUtils.getInstance(LocalNewsmore.this).showMessage("会话已过期，请重新登录");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(LocalNewsmore.this).showMessage("系统繁忙");
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

    private void ShowVolleyRequest2(int pageNum, int pageCount, final int myflag) {
        String url = ApiUrls.SELECTPRESS;
        String s = String.valueOf(pageNum);
        String s1 = String.valueOf(pageCount);
        //加载更多传参
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageNum", s);
        params.put("pageCount", s1);
        params.put("userid", "");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        PressselectPress pressselectpress = gson.fromJson(response.toString(), PressselectPress.class);
                        List<PressselectPress.ContentBean.DataBean> pressselectpressdata = pressselectpress.getContent().getData();
                        String desc = pressselectpress.getDesc().toString();
                        int flag = pressselectpress.getFlag();
                        if (flag == 1) {
                            locallife_newsmoreprogressbar1.setVisibility(View.INVISIBLE);
                            contentlis1.addAll(pressselectpressdata);
                            if (pressselectpressdata.size() > 0) {
                                localnewsmoreadapter.notifyDataSetChanged();
                                localnewsmoreadapter.setDatas(contentlis1);
                            } else {
                                if (myflag == 2) {
                                    //说明没有下一页的数据，显示没有更多数据了
                                    LogUtil.d("没没有更多数据=", "没没有更多数据");
                                    locallifenewsmoreXRecyclerView1.loadMoreComplete();
                                    ToastUtils.getInstance(LocalNewsmore.this).showMessage("没有更多数据了");
                                    // mhome_merchantrecordseearchrecycleview.setLoadingMoreEnabled(false);
                                } else {
                                    //没有数据，显示没有数据的背景图
                                    mloacl_life_newsmoreview.setVisibility(View.VISIBLE);
                                }
                            }


                            LogUtil.d("登录成功=", desc);
                        } else {
                            locallife_newsmoreprogressbar1.setVisibility(View.INVISIBLE);
                            ToastUtils.getInstance(LocalNewsmore.this).showMessage(desc);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                locallife_newsmoreprogressbar1.setVisibility(View.INVISIBLE);
                ToastUtils.getInstance(LocalNewsmore.this).showMessage("系统繁忙");
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
}
