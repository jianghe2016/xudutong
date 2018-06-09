package com.xdt.xudutong.tianjian;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/4/12.
 */

public class PackageUtils {
    /**
     * 检测是否有权限
     *
     * @param mContext
     * @param //pkgName
     *            应用包名
     * @param permName
     *            权限名称
     * @return
     */
    public static boolean hasPermission(Context mContext, String permName) {
        try {
            return hasPermission(mContext, permName, mContext.getPackageName());
        }
        catch (Exception e) {
            return false;
        }

    }
    public static boolean hasPermission(Context mContext, String permName, String pkgName) {
        if (null == mContext || TextUtils.isEmpty(pkgName) || TextUtils.isEmpty(permName)) {
            return false;
        }
        try {
            PackageManager pm = mContext.getPackageManager();
            boolean permission = PackageManager.PERMISSION_GRANTED == pm.checkPermission(permName, pkgName);
            return permission;
        }
        catch (Exception e) {
            return false;
        }

    }
}
