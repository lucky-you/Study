package com.zhowin.study.voide.manager;

import android.content.ServiceConnection;
import android.util.Log;

import com.zhowin.study.voide.config.AppConfig;
import com.zhowin.study.voide.activity.BaseLoginActivity;
import com.zhowin.study.voide.bean.UserStatus;
import com.zhowin.study.voide.listener.CoreStatusListener;

/**
 * author Z_B
 * date :2019/11/23 11:54
 * description:
 */
public class CoreManager {
    private static final String TAG = "CoreManager";
    private UserStatus selfStatus = null;
    private AppConfig config = null;
    private static AppConfig staticConfig = null;
    private BaseLoginActivity ctx;
    private CoreStatusListener coreStatusListener;
    // 绑定Service成功的回调，
    // 当前绑定服务的连接，
    private ServiceConnection mCoreServiceConnection;


    public CoreManager(BaseLoginActivity ctx, CoreStatusListener coreStatusListener) {
        this.ctx = ctx;
        this.coreStatusListener = coreStatusListener;
    }

    public AppConfig getConfig() {
//        if (config == null) {
//            config = requireConfig(ctx);
//        }
        return config;
    }

    public void init(boolean loginRequired, boolean configRequired) {
        Log.d(TAG, "init() called");
    }

    public void destroy() {
    }


}
