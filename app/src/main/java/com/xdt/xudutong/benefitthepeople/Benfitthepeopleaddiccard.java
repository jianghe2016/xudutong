package com.xdt.xudutong.benefitthepeople;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.SettlementaccountBinding;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.RSAutilsmy;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\2\1 0001.
 */

public class Benfitthepeopleaddiccard extends BaseActivity {

    private LinearLayout mbenfitthepeopleaddiccardlayout;
    private LinearLayout mbenfitthepeopleaddiccarditem1;
    private String rsaopenkey = RSAutilsmy.RSAOPENKEY;

    @Override
    public void initView() {
        final PublicKey publicKey = RSAutilsmy.keyStrToPublicKey(rsaopenkey);
        mbenfitthepeopleaddiccardlayout = (LinearLayout) findViewById(R.id.benfitthepeopleaddiccardlayout);
        ImageView mbenfitthepeopleaddiccardaddcardbutton = (ImageView) findViewById(R.id.benfitthepeopleaddiccardaddcardbutton);
        LinearLayout mbenfitthepeopleaddiccardback = (LinearLayout) findViewById(R.id.benfitthepeopleaddiccardback);
        mbenfitthepeopleaddiccarditem1 = (LinearLayout) findViewById(R.id.benfitthepeopleaddiccarditem1);
        TextView mbenfitthepeopleaddiccarddelect = (TextView) findViewById(R.id.benfitthepeopleaddiccarddelect);
        mbenfitthepeopleaddiccardback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mbenfitthepeopleaddiccarddelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmydiaog(publicKey);
            }
        });
        mbenfitthepeopleaddiccardaddcardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Benfitthepeopleaddiccard.this, Benefitthepeopleaddbankcard.class);
                startActivity(intent);

            }
        });
    }

    private void showmydiaog(final PublicKey publicKey) {
        View inflate = LinearLayout.inflate(Benfitthepeopleaddiccard.this, R.layout.benfitthepeopleaddiccarddialog1, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        final Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.7f;
        getWindow().setAttributes(attributes);
        popupWindow.showAtLocation(mbenfitthepeopleaddiccardlayout, Gravity.CENTER, 0, 0);
        Button mbenfitthepeopleaddiccarddialog1_canle = (Button) inflate.findViewById(R.id.benfitthepeopleaddiccarddialog1_canle);
        Button mbenfitthepeopleaddiccarddialog1_delect = (Button) inflate.findViewById(R.id.benfitthepeopleaddiccarddialog1_delect);
        mbenfitthepeopleaddiccarddialog1_canle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams attributes1 = window.getAttributes();
                attributes1.alpha = 1f;
                window.setAttributes(attributes1);
                popupWindow.dismiss();
            }
        });
        mbenfitthepeopleaddiccarddialog1_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNo="18827212721";
                String bindMedium="6212560200123701123";
                String mediumId="6214760200600605312";
                String a = RSAutilsmy.encryptDataByPublicKey(mobileNo.getBytes(), publicKey).replaceAll("\r|\n", "");
                String b = RSAutilsmy.encryptDataByPublicKey(bindMedium.getBytes(), publicKey).replaceAll("\r|\n", "");
                String c = RSAutilsmy.encryptDataByPublicKey(mediumId.getBytes(), publicKey).replaceAll("\r|\n", "");
                LogUtil.d("mobileNo==",a+"");
                LogUtil.d("bindMedium==",b+"");
                LogUtil.d("mediumId==",c+"");
                ShowVolleyRequestforaddiccarddelectIBCcard(a,b,c);

                WindowManager.LayoutParams attributes1 = window.getAttributes();
                attributes1.alpha = 1f;
                window.setAttributes(attributes1);
                mbenfitthepeopleaddiccarditem1.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void setMyContentView() {
        setContentView(R.layout.benfitthepeopleaddiccard);
    }

    @Override
    public void setSteepStatusBarwhiteorblack(int steepstatesflag) {
        super.setSteepStatusBarwhiteorblack(2);
    }

    //请求解绑
    public void ShowVolleyRequestforaddiccarddelectIBCcard(String a, String b, String c) {
        String url = ApiUrls.ACCOUNTUNBINDING;
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobileNo", a);
        params.put("bindMedium", b);
        params.put("mediumId", c);
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        SettlementaccountBinding sttlementaccountBinding = gson.fromJson(response.toString(), SettlementaccountBinding.class);
                        String code = sttlementaccountBinding.getCode();
                        LogUtil.d("codecode", code);
                        if (code.equals("R00001")) {
                            SettlementaccountBinding.ContentBean.DataBean data = sttlementaccountBinding.getContent().getData();
                            //工行交易订单号
                            //短信验证码发送编号，只有启用短信验证码模式时返回

                        } else {
                            String desc = sttlementaccountBinding.getDesc();
                            ToastUtils.getInstance(Benfitthepeopleaddiccard.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.d("VolleyError", error + "");
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

}
