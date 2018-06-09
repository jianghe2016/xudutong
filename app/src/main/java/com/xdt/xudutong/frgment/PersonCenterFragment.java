package com.xdt.xudutong.frgment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xdt.xudutong.R;
import com.xdt.xudutong.activitys.QCActivity;
import com.xdt.xudutong.bean.ViploadUserInfo;
import com.xdt.xudutong.personcenterfragment.PersonAskHelp;
import com.xdt.xudutong.personcenterfragment.PersonMerchantloadin;
import com.xdt.xudutong.personcenterfragment.Personietmfive;
import com.xdt.xudutong.personcenterfragment.Personitemeight;
import com.xdt.xudutong.personcenterfragment.Personitemfour;
import com.xdt.xudutong.personcenterfragment.Personitemsix;
import com.xdt.xudutong.personcenterfragment.Personitemthree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethree;
import com.xdt.xudutong.personcenterfragment.Personitemthreethreefail;
import com.xdt.xudutong.personcenterfragment.Personitemthreetwo;
import com.xdt.xudutong.personcenterfragment.Personitemtwo;
import com.xdt.xudutong.personcenterfragment.Personuser_comein;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.DataAnalysetwo;
import com.xdt.xudutong.utils.EventMsg;
import com.xdt.xudutong.utils.Finaltext;
import com.xdt.xudutong.utils.SpUtils;
import com.xdt.xudutong.utils.ToRoundCorner;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.xdt.xudutong.utils.Finaltext.LITTLEGREENTRUENAMEREALSTATES;
import static com.xdt.xudutong.utils.Finaltext.PERSONCENTERTRUENAMEREALSTATES;


/**
 * Created by Administrator on 2017/5/8.
 */

public class PersonCenterFragment extends BaseFragment {
    private View view;
    private LinearLayout person_center_item2;
    private LinearLayout person_center_item3;
    private LinearLayout person_center_item_merchantrecord;
    private LinearLayout person_center_item4;
    private LinearLayout person_center_item5;
    private LinearLayout person_center_item6;
    private LinearLayout person_center_item8;
    private TextView loadingtext;
    private TextView personcenter3right1;

    private ImageView person_center_head_icom1;
    private Bitmap bitmap;
    private TextView fragment_loadingtext1;
    private LinearLayout personcenter_loadingstatesfalse1;
    private LinearLayout personcenter_loadingstatesture1;
    private String token1;
    private String token2;
    private boolean loadingstate;
    private TextView mperson_center_itempersonstatesselect;
    private LinearLayout mperson_center_itempersonstatesitem;
    private LinearLayout mperson_center_itempersonstatescancle;
    private boolean personcentertruenamerealstatescanleorno;
    private String mRealName;
    private String mHeadImgstring;
    private LinearLayout mLl_user_code;
    private String mAddress;
    private Intent mIntent3;
    private ViploadUserInfo mViploaduserinfo;
    private LinearLayout mPerson_center_help;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_personcenter, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mLl_user_code = (LinearLayout) view.findViewById(R.id.ll_user_code);
        mPerson_center_help = (LinearLayout) view.findViewById(R.id.person_center_help);
        personcenter_loadingstatesfalse1 = (LinearLayout) view.findViewById(R.id.personcenter_loadingstatesfalse);
        personcenter_loadingstatesture1 = (LinearLayout) view.findViewById(R.id.personcenter_loadingstatesture);
        person_center_head_icom1 = (ImageView) view.findViewById(R.id.person_center_head_icom);
        //个人信息黄色状态栏的ID,字体和cancle布局
        mperson_center_itempersonstatesselect = (TextView) view.findViewById(R.id.person_center_itempersonstatesselect);
        mperson_center_itempersonstatesitem = (LinearLayout) view.findViewById(R.id.person_center_itempersonstatesitem);
        mperson_center_itempersonstatescancle = (LinearLayout) view.findViewById(R.id.person_center_itempersonstatescancle);
        person_center_item2 = (LinearLayout) view.findViewById(R.id.person_center_item2);
        person_center_item3 = (LinearLayout) view.findViewById(R.id.person_center_item3);
        person_center_item4 = (LinearLayout) view.findViewById(R.id.person_center_item4);
        person_center_item_merchantrecord = (LinearLayout) view.findViewById(R.id.person_center_item_merchantrecord);
        person_center_item5 = (LinearLayout) view.findViewById(R.id.person_center_item5);
        person_center_item6 = (LinearLayout) view.findViewById(R.id.person_center_item6);
        person_center_item8 = (LinearLayout) view.findViewById(R.id.person_center_item8);
        fragment_loadingtext1 = (TextView) view.findViewById(R.id.fragment_loadingtext);
        personcenter3right1 = (TextView) view.findViewById(R.id.personcenter3right);
        loadingtext = (TextView) view.findViewById(R.id.Person_center_noloading);
        token1 = SpUtils.getParam(getContext(), "access_token", "");
        token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
        //默认没记录点击取消的提示
        personcentertruenamerealstatescanleorno = SpUtils.getParam(getContext(), PERSONCENTERTRUENAMEREALSTATES, false);
        LogUtil.d("个人中心获得的access_token====", token1);
        LogUtil.d("个人中心获得的x_auth_token====", token2);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ShowVolleyrequest();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(EventMsg event) {
        switch (event.what) {
            case EventMsg.TUICHU:
                LogUtil.d("主页面收到了退出===", "收到了退出");
                SpUtils.putParam(getContext(), "loadingstate", false);
                loadingstate = false;
                break;
            case EventMsg.CAMERA11:
                Object data1 = event.data;
                Bitmap data11 = (Bitmap) data1;
                person_center_head_icom1.setImageBitmap(data11);
                break;
            case EventMsg.PICTUREPNG:
                Object data2 = event.data;
                Bitmap data22 = (Bitmap) data2;
                person_center_head_icom1.setImageBitmap(data22);
                break;
            case EventMsg.PICTUREJPG:
                Object data3 = event.data;
                Bitmap data33 = (Bitmap) data3;
                person_center_head_icom1.setImageBitmap(data33);
                break;
            case EventMsg.PERSON_TICKNAMEEDITTEXTSTRING:
                Object data4 = event.data;
                fragment_loadingtext1.setText(data4.toString());
                break;
            case EventMsg.TRUENAMEFLAG:
                Object data5 = event.data;
                Boolean aBoolean = Boolean.valueOf(data5 + "");
                if (aBoolean == true) {
                    mperson_center_itempersonstatesitem.setVisibility(View.GONE);
                }
                break;


        }
    }

    private void ShowVolleyrequest() {
        String url = ApiUrls.LOADUSERINFO;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.json(response+"");
                        try {
                            Object code1 = response.get("code");
                            String code1string = code1.toString();
                            if (code1string.equals("R00001")) {
                                loadingstate = true;
                                SpUtils.putParam(getContext(), "loadingstate", true);
                                Gson gson = new Gson();
                                mViploaduserinfo = gson.fromJson(String.valueOf(response), ViploadUserInfo.class);
                                int real = mViploaduserinfo.getContent().getData().getReal();
                                LogUtil.d("打印个人信息页面real===", real + "");
                                SpUtils.putParam(getContext(), "realstate", real);
                                String id_num = mViploaduserinfo.getContent().getData().getCertId();
                                String paypsd = mViploaduserinfo.getContent().getData().getPayPwdStatus();
                                SpUtils.putParam(getContext(),"id_num",id_num);
                                SpUtils.putParam(getContext(),"paypsd",paypsd);
                                LogUtil.e("paypsd===========",SpUtils.getParam(getContext(),"paypsd",""));
                                //请求成功展示的顶部页面
                                ViploadUserInfo.ContentBean.DataBean viploaduserinfodata = mViploaduserinfo.getContent().getData();
                                String nickname = viploaduserinfodata.getNickname();
                                String username = viploaduserinfodata.getUsername();
                                String mobile = viploaduserinfodata.getMobile();
                                mAddress = viploaduserinfodata.getHuji_addr();
                                LogUtil.e("address=====", mAddress);
                                mRealName = viploaduserinfodata.getRealName();
                                int cardStatus = viploaduserinfodata.getCardStatus();
                                int realId = viploaduserinfodata.getRealId();
                                String realIdstring = String.valueOf(realId);
                                LogUtil.d("打印realIdstring===", realIdstring + "");
                                Showdataforsuccess(loadingstate, real, realIdstring, username, mobile, cardStatus);
                                fragment_loadingtext1.setText(nickname);
                                Object headImg = viploaduserinfodata.getHeadImg();
                                if (null != headImg && !(headImg + "").isEmpty()) {
                                    mHeadImgstring = String.valueOf(headImg);
                                    LogUtil.e("mHeadImgstring=======",mHeadImgstring);
                                    bitmap = ToRoundCorner.toRoundCorner(DataAnalysetwo.base64ToBitmap(mHeadImgstring), 5);
                                    ApplicationController.mBitmap = bitmap;
                                    person_center_head_icom1.setImageBitmap(bitmap);
                                } else {
                                    mHeadImgstring = "null";
                                    LogUtil.e("mHeadImgstring=======","null");
                                    person_center_head_icom1.setImageResource(R.drawable.personcenter1);
                                }
                            } else {
                                Showdataforfail();
                                LogUtil.d("请求个人信息失败===", false + "");
                                loadingstate = false;
                                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                                SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                                SpUtils.putParam(getContext(), "loadingstate", false);
                                SpUtils.removeParam(getContext(), "personmobile");
                                SpUtils.removeParam(getContext(), "appAlias");
                            }
                        } catch (JSONException e) {
                            Showdataforfail();
                            LogUtil.d("请求个人信息===", false + "");
                            loadingstate = false;
                            SpUtils.putParam(getContext(), "loadingstate", false);
                            SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                            SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES2);
                            SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                            SpUtils.removeParam(getContext(), "personmobile");
                            SpUtils.removeParam(getContext(), "appAlias");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN-ERROR", error.getMessage(), error);
                Showdataforfail();
                SpUtils.removeParam(getContext(), Finaltext.PERSONCENTERTRUENAMEREALSTATES);
                SpUtils.removeParam(getContext(), LITTLEGREENTRUENAMEREALSTATES);
                SpUtils.removeParam(getContext(), "personmobile");
                SpUtils.removeParam(getContext(), "appAlias");
                LogUtil.d("请求个人信息失败", "请求个人信息失败");
                loadingstate = false;
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
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        ApplicationController.getInstance(ApplicationController.getContext()).getRequestQueue().add(jsonRequest);
    }

    private void Showdataforfail() {
        personcenter_loadingstatesture1.setVisibility(View.GONE);
        personcenter_loadingstatesfalse1.setVisibility(View.VISIBLE);
        mLl_user_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        mPerson_center_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });

        personcenter_loadingstatesfalse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        person_center_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        person_center_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        person_center_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        //商户
        person_center_item_merchantrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        person_center_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
        //条目6逻辑,关于我们
        person_center_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Personitemsix.class);
                //Intent intent = new Intent(getContext(), Personpushinfomanager1.class);
                startActivity(intent);
            }
        });
        //条目8逻辑
        person_center_item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personuser_comein.class);
                startActivity(intent);
            }
        });
    }

    private void Showdataforsuccess(boolean loadingstate, final int real, String realIdstring, final String username, final String mobile, int cardStatus) {
        personcenter_loadingstatesture1.setVisibility(View.VISIBLE);
        personcenter_loadingstatesfalse1.setVisibility(View.GONE);
        //如果用户没有点击过消失，则默认是显示的，点击了则保存为true
        if (personcentertruenamerealstatescanleorno != true) {
            switch (real) {
                case -1:
                    loadingtext.setText("未认证");
                    mperson_center_itempersonstatesitem.setVisibility(View.VISIBLE);
                    mperson_center_itempersonstatesselect.setText("您还没有实名认证，部分功能不能使用。");
                    canclick(-1, cardStatus);
                    break;
                case 0:
                    loadingtext.setText("正在认证");
                    mperson_center_itempersonstatesitem.setVisibility(View.GONE);
                    // mperson_center_itempersonstatesselect.setText("您正在实名认证，部分功能不能使用。");
                    canclick(0, cardStatus);
                    break;
                case 1:
                    //此条能显示无卡绑卡的条幅
                    if (cardStatus == 0 || cardStatus == 11) {
                        mperson_center_itempersonstatesitem.setVisibility(View.VISIBLE);
                        mperson_center_itempersonstatesselect.setText("您还未绑定许都通卡，部分功能不能使用。");
                    }
                   /* if (realIdstring != null) {
                        ShowVolleyRequestforturename(realIdstring);
                    }*/
                    loadingtext.setText("已认证");
                    canclick(1, cardStatus);
                    break;
                case 2:
                    mperson_center_itempersonstatesitem.setVisibility(View.VISIBLE);
                    mperson_center_itempersonstatesselect.setText("您的实名认证失败，部分功能不能使用。");
                    loadingtext.setText("认证失败");
                    canclick(2, cardStatus);
                    break;
                case 3:
                    mperson_center_itempersonstatesitem.setVisibility(View.VISIBLE);
                    mperson_center_itempersonstatesselect.setText("您的实名认证失败，部分功能不能使用。");
                    loadingtext.setText("认证失败");
                    canclick(3, cardStatus);
                    break;
                default:
                    mperson_center_itempersonstatesitem.setVisibility(View.GONE);
                    loadingtext.setText("***");
                    personcenter3right1.setText("***");
            }
        }


        //以下为一大部分，请求成功，token没有过期，有数据，进入有数据页面，一共七个按钮
        //条目一逻辑
        person_center_head_icom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personitemtwo.class);
                startActivity(intent);
            }
        });

        mLl_user_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (real) {
                    case -1:
                        //未认证
                        Intent intent = new Intent(getActivity(), Personitemthree.class);
                        if (!TextUtils.isEmpty(mobile)) {
                            intent.putExtra("loadusername", username);
                        }
                        startActivity(intent);
                        break;
                    case 0:
                        //正在认证
                        Intent intent2 = new Intent(getActivity(), Personitemthreetwo.class);
                        startActivity(intent2);
                        break;
                    case 1:
                        LogUtil.e("Address=========",mAddress);
                        //认证成功
                        if (mAddress != null && !"".equals(mAddress)){
                            mIntent3 = new Intent(getActivity(), QCActivity.class);
                            mIntent3.putExtra("realName",mViploaduserinfo.getContent().getData().getRealName());
                            mIntent3.putExtra("sex",mViploaduserinfo.getContent().getData().getGender());
                        }else {
                            ToastUtils.getInstance(getActivity()).showMessage("请完善您的住址信息");
                            mIntent3 = new Intent(getActivity(), Personitemtwo.class);
                        }
                        startActivity(mIntent3);
                        break;
                    case 2:
                        loadingtext.setText("认证失败");
                        Intent intent4 = new Intent(getActivity(), Personitemthreethreefail.class);
                        startActivity(intent4);
                        break;
                    case 3:
                        loadingtext.setText("认证失败");
                        Intent intent5 = new Intent(getActivity(), Personitemthreethreefail.class);
                        startActivity(intent5);
                        break;
                    default:
                        loadingtext.setText("***");
                        personcenter3right1.setText("***");
                }
            }});
        mPerson_center_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent55 = new Intent(getActivity(), PersonAskHelp.class);
                if (!TextUtils.isEmpty(username)) {
                    intent55.putExtra("itemtwoPersonAskHelpusername", username);
                }
                startActivity(intent55);
            }
        });

        //条目2逻辑,个人信息
        person_center_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Personitemtwo.class);
                startActivity(intent);
            }
        });
        //条目3逻辑,实名认证
        person_center_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (real) {
                    case -1:
                        //未认证
                        Intent intent = new Intent(getActivity(), Personitemthree.class);
                        if (!TextUtils.isEmpty(mobile)) {
                            intent.putExtra("loadusername", username);
                        }
                        startActivity(intent);
                        break;
                    case 0:
                        //正在认证
                        Intent intent2 = new Intent(getActivity(), Personitemthreetwo.class);
                        startActivity(intent2);
                        break;
                    case 1:
                        //认证成功
                        Intent intent3 = new Intent(getActivity(), Personitemthreethree.class);
                        startActivity(intent3);
                        break;
                    case 2:
                        loadingtext.setText("认证失败");
                        Intent intent4 = new Intent(getActivity(), Personitemthreethreefail.class);
                        startActivity(intent4);
                        break;
                    case 3:
                        loadingtext.setText("认证失败");
                        Intent intent5 = new Intent(getActivity(), Personitemthreethreefail.class);
                        startActivity(intent5);
                        break;
                    default:
                        loadingtext.setText("***");
                        personcenter3right1.setText("***");
                }

            }
        });
        //商户这版不上架，故选择隐藏
        person_center_item_merchantrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonMerchantloadin.class);
                startActivity(intent);
            }
        });
        //条目4逻辑，卡管理
        person_center_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果未登录则
                Intent intent = new Intent(getActivity(), Personitemfour.class);
                intent.putExtra("xdtguashi", "卡管理");
                startActivity(intent);
            }
        });

        //条目5逻辑，新闻收藏
        person_center_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录则
                Intent intent = new Intent(getContext(), Personietmfive.class);
                startActivity(intent);
            }
        });
        //条目6逻辑
        person_center_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Personitemsix.class);
                startActivity(intent);
            }
        });
        //设置按钮逻辑
        person_center_item8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e(mViploaduserinfo.getContent().getData().getMobile());
                Intent intent = new Intent(getContext(), Personitemeight.class);
                intent.putExtra("personturenamestates", real);
                intent.putExtra("personusername", username);
                SpUtils.putParam(getContext(), "personmobile", mobile);
                intent.putExtra("mobile",mViploaduserinfo.getContent().getData().getMobile());
                startActivity(intent);
            }
        });


    }

    private void canclick(final int turenamestates, final int cardStatus) {

        //-1未认证，0正在认证，1认证成功，2,3认证失败
        mperson_center_itempersonstatesselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turenamestates == -1) {
                    //未认证
                    Intent intent = new Intent(getContext(), Personitemthree.class);
                    startActivity(intent);
                } else if (turenamestates == 2 || turenamestates == 3) {
                    //认证失败
                    Intent intent3 = new Intent(getContext(), Personitemthreethreefail.class);
                    startActivity(intent3);
                } else if (turenamestates == 1) {
                    //实名，认证成功
                    if (cardStatus == 0 || cardStatus == 11) {
                        //无卡
                        mperson_center_itempersonstatesselect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getContext(), Personitemfour.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        //解绑和挂失状态不显示
                        mperson_center_itempersonstatesitem.setVisibility(View.GONE);
                    }
                } else {
                    mperson_center_itempersonstatesitem.setVisibility(View.GONE);
                }
            }
        });
        mperson_center_itempersonstatescancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户点击消失，进行记录。
                SpUtils.putParam(getContext(), PERSONCENTERTRUENAMEREALSTATES, true);
                mperson_center_itempersonstatesitem.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View initView() {
        return null;
    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 21) {
            switch (resultCode) {
                case 22:

                    break;
                case 42:
                    //退出账号
                    loadingstate=false;
                    ShowVolleyrequest();
                    Log.i("从itemeight页面返回的", "从itemeight页面返回的");
                    break;
            }
        }
        if(requestCode==40){
            if(resultCode==22){
                loadingstate=true;
                Log.i("从登录页面返回的", "从登录页面返回的");
                token1 = SpUtils.getParam(getContext(), "access_token", "");
                token2 = SpUtils.getParam(getContext(), "x_auth_token", "");
                ShowVolleyrequest();
            }

        }


    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle();   //回收图片所占的内存
                System.gc();  //提醒系统及时回收
                System.runFinalization();
            }
        }

    }

}
