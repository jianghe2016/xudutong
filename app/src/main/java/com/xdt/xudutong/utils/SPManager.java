package com.xdt.xudutong.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存储用户本地信息
 * Created by Administrator on 16/11/5.
 */

public class SPManager {

    private static final String SP_TAG = "pinche_sp";
    public static final String SP_WELCOME = "sp_welcome";
    public static final String SP_SESSION = "sp_session";
    public static final String SP_USER_INFO = "sp_user_info";
    public static final String SP_MANNED_CACHE = "sp_manned_cache";
    public static final String SP_HOME_AD = "sp_home_ad";
    public static final String SP_ALIAS = "alias";
    public static final String SP_PHONE = "sp_phone";
    public static final String SP_USERID = "user_id";
    public static final String SP_INFO = "user_info";
    public static final String SP_PSDVALUE = "user_value";
    public static final String SP_PERMIT = "sp_permit";
    public static final String SP_LEVEL = "level";
    public static final String SP_CODE = "code";
    public static final String SP_ID = "id_num";
    public static final String SP_PAY_PSD= "sp_pay_psd";

    private static SPManager INSTANCE;
    private final Context mContext;
    private final SharedPreferences mSharedPreferences;

    private SPManager(Context context) {
        this.mContext = context;
        mSharedPreferences = context.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE);
    }

    public static SPManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SPManager(context);
        }
        return INSTANCE;
    }

    public void saveSession(String session) {
        mSharedPreferences.edit().putString(SP_SESSION, session).commit();
    }

    public String getSession() {
        String session = mSharedPreferences.getString(SP_SESSION, "missing");
        return session;
    }

    public void savePermit(String permit) {
        mSharedPreferences.edit().putString(SP_PERMIT, permit).commit();
    }

    public String getPermit() {
        String permit = mSharedPreferences.getString(SP_PERMIT, "unKnow");
        return permit;
    }


    public void saveUserId(String userId) {
        mSharedPreferences.edit().putString(SP_USERID, userId).commit();
    }

    public String getUserId() {
        String session = mSharedPreferences.getString(SP_USERID, "null");
        return session;
    }

    public void savePsdValue(String psdValue) {
        mSharedPreferences.edit().putString(SP_PSDVALUE, psdValue).commit();
    }

    public String getPsdValue() {
        String psdValue = mSharedPreferences.getString(SP_PSDVALUE, "0");
        return psdValue;
    }

    public void savePhone(String phone) {
        mSharedPreferences.edit().putString(SP_PHONE, phone).commit();
    }

    public String getPhone() {
        String phone = mSharedPreferences.getString(SP_PHONE, "null");
        return phone;
    }

    public void saveInfo(String info) {
        mSharedPreferences.edit().putString(SP_INFO, info).commit();
    }

    public String getInfo() {
        String info = mSharedPreferences.getString(SP_INFO, "info_null");
        return info;
    }


    public boolean getWelcome() {
        boolean aBoolean = mSharedPreferences.getBoolean(SP_WELCOME, true);
        return aBoolean;
    }

    public void setWelcome() {
        mSharedPreferences.edit().putBoolean(SP_WELCOME, false).commit();
    }

    public void saveUserInfo(String userInfo) {
        mSharedPreferences.edit().putString(SP_USER_INFO, userInfo).commit();
    }

    public String getUserInfo() {
        return mSharedPreferences.getString(SP_USER_INFO, "");
    }

    public void saveHomeAd(boolean show) {
        mSharedPreferences.edit().putBoolean(SP_HOME_AD, show).commit();
    }

    public boolean getHomeAd() {
        return mSharedPreferences.getBoolean(SP_HOME_AD, true);
    }

    public void saveAlias(boolean have) {
        mSharedPreferences.edit().putBoolean(SP_ALIAS, have).commit();
    }

    public boolean getAlias() {
        return mSharedPreferences.getBoolean(SP_ALIAS, false);
    }

    public void saveLevel(String level) {
        mSharedPreferences.edit().putString(SP_LEVEL, level).commit();
    }

    public String getLevel() {
        String level = mSharedPreferences.getString(SP_LEVEL, "level");
        return level;
    }

    public void saveCode(String code) {
        mSharedPreferences.edit().putString(SP_CODE, code).commit();
    }

    public String getCode() {
        String code = mSharedPreferences.getString(SP_CODE, "unKnow");
        return code;
    }

    public void saveID_Num(String id_num) {
        mSharedPreferences.edit().putString(SP_ID, id_num).commit();
    }

    public String getID_Num() {
        String id_num = mSharedPreferences.getString(SP_ID, "null");
        return id_num;
    }

    public void savePayPsdStatus(String s) {
        mSharedPreferences.edit().putString(SP_PAY_PSD, s).commit();
    }

    public String getPayPsdStatus() {
        String id_num = mSharedPreferences.getString(SP_PAY_PSD, "null");
        return id_num;
    }

}
