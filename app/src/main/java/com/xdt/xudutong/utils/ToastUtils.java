package com.xdt.xudutong.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class ToastUtils {
    protected static Toast toast = null;

    private static volatile ToastUtils mToastUtils;

    private ToastUtils(Context context) {
        toast = Toast.makeText(context.getApplicationContext(), null, Toast.LENGTH_SHORT);
    }

    public static ToastUtils getInstance(Context context) {
        if (null == mToastUtils) {
            synchronized (ToastUtils.class) {
                if (null == mToastUtils) {
                    mToastUtils = new ToastUtils(context);
                }
            }
        }
        return mToastUtils;
    }

    public void showMessage(int toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void showMessage(String toastMsg) {
        toast.setText(toastMsg);
        toast.show();
    }

    public void toastCancel() {
        if (null != toast) {
            toast.cancel();
            toast = null;
        }
        mToastUtils = null;
    }

}

