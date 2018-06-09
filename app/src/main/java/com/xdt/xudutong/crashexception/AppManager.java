package com.xdt.xudutong.crashexception;

/**
 * Created by Administrator on 2017\7\20 0020.
 */

import android.app.Activity;

import com.xdt.xudutong.view.LogUtil;

import java.util.LinkedList;
import java.util.List;

public class AppManager {
    private
    List<Activity> activitys = null;
    private static AppManager instance;

    private AppManager() {
        activitys = new LinkedList<Activity>();
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static AppManager getInstance() {
        if (null == instance) {
            instance = new AppManager();
        }
        return instance;
    }

    //添加Activity到容器中

    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }


    public void removeActivity(Activity a) {
        activitys.remove(a);
    }
    //遍历所有Activity并finishe
    public void exit() {
        LogUtil.e("程序正常退出", "程序正常退出");

        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);


      /*  ActivityManager manager = (ActivityManager) ApplicationController.getContext().getSystemService(ACTIVITY_SERVICE); //获取应用程序管理器
        manager.killBackgroundProcesses(ApplicationController.getContext().getPackageName());*/

    }    //遍历所有Activity并finish
}