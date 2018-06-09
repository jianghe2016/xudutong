package com.xdt.xudutong.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2017\11\17 0017.
 */

public class PersonmerchantAnimationUtil {


    /**
     * push_up_out 隐藏
     *
     * @return
     */
    public static Animation moveToViewBottom(Context context) {
        // mHiddenAction = AnimationUtils.loadAnimation(context, R.anim.push_up_out);
        Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -0.5f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * push_up_in
     *
     * @return
     */
    public static Animation moveToViewLocation(Context context) {
     /*   TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1500);
        return mHiddenAction;*/
       // mShowAction = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
        Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        return mShowAction;
    }
}
