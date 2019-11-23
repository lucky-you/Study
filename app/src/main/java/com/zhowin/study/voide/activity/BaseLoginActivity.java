package com.zhowin.study.voide.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.voide.config.AppConfig;
import com.zhowin.study.voide.listener.CoreStatusListener;
import com.zhowin.study.voide.manager.CoreManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author Z_B
 * date :2019/11/23 11:40
 * description:
 */
public abstract class BaseLoginActivity extends BaseActivity implements CoreStatusListener {

    private static final String TAG = "CoreManager";
    public CoreManager coreManager;
    private List<CoreStatusListener> coreStatusListeners;
    private boolean loginRequired = true;
    private boolean configRequired = true;

    protected void noLoginRequired() {
        Log.d(TAG, "noLoginRequired() called");
        loginRequired = false;
    }

    protected void noConfigRequired() {
        Log.d(TAG, "noConfigRequired() called");
        configRequired = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBar();
        initCore();
    }


    private void setTitleBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    public AppConfig getAppconfig() {
        return coreManager.getConfig();
    }

    private void initCore() {
        Log.d(TAG, "initCore() called");
        if (coreManager == null) {
            coreManager = new CoreManager(this, this);
        }
        if (coreStatusListeners == null) {
            coreStatusListeners = new ArrayList<>();
        }
        coreManager.init(loginRequired, configRequired);
    }

    @Override
    public void onCoreReady() {
        Log.d(TAG, "onCoreReady() called");
        for (CoreStatusListener listener : coreStatusListeners) {
            listener.onCoreReady();
        }
    }

    // 注册CoreManager初始化状态的监听，比如fragment可以调用，
    public void addCoreStatusListener(CoreStatusListener listener) {
        coreStatusListeners.add(listener);
    }

    @Override
    protected void onDestroy() {
        coreManager.destroy();
        super.onDestroy();
    }
}
