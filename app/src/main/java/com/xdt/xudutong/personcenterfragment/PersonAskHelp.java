package com.xdt.xudutong.personcenterfragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.xdt.xudutong.R;
import com.xdt.xudutong.bean.Feedbackaddfeedback;
import com.xdt.xudutong.frgment.BaseActivity;
import com.xdt.xudutong.utils.ApiUrls;
import com.xdt.xudutong.utils.ApplicationController;
import com.xdt.xudutong.utils.ToastUtils;
import com.xdt.xudutong.view.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017\9\21 0021.
 */

public class PersonAskHelp extends BaseActivity {
    private String itemtwoPersonAskHelpusername;
    private EditText mperson_askhelptext1;
    private EditText mperson_askhelptext2;

    @Override
    public void setMyContentView() {
        setContentView(R.layout.person_askhelp);
    }
    @Override
    public void initView() {
        Intent intent = getIntent();
        itemtwoPersonAskHelpusername = intent.getStringExtra("itemtwoPersonAskHelpusername");
        TextView mperson_askhelpsubmit = (TextView) findViewById(R.id.person_askhelpsubmit);
        LinearLayout mperson_askhelpback = (LinearLayout) findViewById(R.id.person_askhelpback);
        mperson_askhelpback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                    finish();
                }
            }
        });
        mperson_askhelptext1 = (EditText) findViewById(R.id.person_askhelptext1);
        mperson_askhelptext2 = (EditText) findViewById(R.id.person_askhelptext2);
        mperson_askhelpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fastClick()) {
                String mperson_askhelptext11 = mperson_askhelptext1.getText().toString();
                String mperson_askhelptext22 = mperson_askhelptext2.getText().toString();
                if (!TextUtils.isEmpty(mperson_askhelptext11)) {
                    if (!TextUtils.isEmpty(mperson_askhelptext22)) {
                        if (checkPhone3(mperson_askhelptext22)) {
                            if (!TextUtils.isEmpty(itemtwoPersonAskHelpusername)) {
                                //提交逻辑
                                ShowVolleyRequestforpersonaskhelp(mperson_askhelptext11, mperson_askhelptext22, itemtwoPersonAskHelpusername);
                            } else {
                                ShowVolleyRequestforpersonaskhelp(mperson_askhelptext11, mperson_askhelptext22, "");
                            }
                        } else {
                            ToastUtils.getInstance(PersonAskHelp.this).showMessage("请输入正确的手机号");
                        }
                    } else {
                        ToastUtils.getInstance(PersonAskHelp.this).showMessage("请输入您的手机号");
                    }
                } else {
                    ToastUtils.getInstance(PersonAskHelp.this).showMessage("请输入您的意见或者建议");
                }
            }
        }
        });
    }

    private void ShowVolleyRequestforpersonaskhelp(String mpersonAskhelptext11, String mperson_askhelptext11, String mperson_askhelptext22) {

        String url = ApiUrls.ADDFEEDBACK;
        //Volley请求网络进行判断
        Map<String, String> params = new HashMap<String, String>();
        params.put("questionContent", mperson_askhelptext11);
        params.put("phone", mperson_askhelptext22);
        params.put("userName", mpersonAskhelptext11);
        params.put("platform", "0");
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        final Gson gson = new Gson();
                        Feedbackaddfeedback feedbackaddfeedback = gson.fromJson(response.toString(), Feedbackaddfeedback.class);
                        String code = feedbackaddfeedback.getCode();
                        String desc = feedbackaddfeedback.getDesc();
                        if (code.equals("R00001")) {
                            //提交成功
                            AlertDialog.Builder builder = new AlertDialog.Builder(PersonAskHelp.this, R.style.my_dialog);
                            final AlertDialog dialog = builder.create();
                            View view = LayoutInflater.from(PersonAskHelp.this).inflate(R.layout.person_fankuidialog, null);  //通过LayoutInflater获取布局
                            LinearLayout person_fankui_dialog_cancel1 = (LinearLayout) view.findViewById(R.id.person_fankui_dialog_cancel);
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
                            person_fankui_dialog_cancel1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();


                        } else {
                            ToastUtils.getInstance(PersonAskHelp.this).showMessage(desc);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.getInstance(PersonAskHelp.this).showMessage("系统繁忙");
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

    //手机号码的正则
    public boolean checkPhone3(String truephonenumber1textstring) {
       /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][34578]\\d{9}";
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(truephonenumber1textstring)) return false;
        else return truephonenumber1textstring.matches(telRegex);
    }
}
