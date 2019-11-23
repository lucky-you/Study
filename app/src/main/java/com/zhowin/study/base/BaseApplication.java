package com.zhowin.study.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * application的基类
 */
public class BaseApplication extends Application {

    protected static BaseApplication instance;
    public static boolean IS_OPEN_RECEIPT = true;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    public static BaseApplication getInstance() {
        return instance;
    }


    /**
     * 在程序内部关闭时，调用此方法
     */
    public void destory() {
        // 释放数据库
        // SQLiteHelper.release();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取当前进程的名称
     */
    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


}
