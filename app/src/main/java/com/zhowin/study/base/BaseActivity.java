package com.zhowin.study.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.zhowin.study.R;
import com.zhowin.study.utils.ToastUtils;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected Context mContext;
    protected View mContentView;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理栈
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(loadViewLayout());
        bindViews(mContentView);
        processLogic(savedInstanceState);
    }


    @SuppressLint("ResourceType")
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }


    @Override
    public void onClick(View view) {
        if (!isFastClick()) setClickListener(view);
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 300) {
            lastClick = now;
            return false;
        }
        return true;
    }

    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) findViewById(id);
    }

    /**
     * 界面跳转
     *
     * @param tarActivity
     */
    protected void intentToActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mContext, tarActivity);
        startActivity(intent);
    }

    /**
     * 显示Toast
     */
    protected void showToast(String msg) {
        ToastUtils.showLong(msg);
    }


    @Override
    protected void onDestroy() {
        ActivityManager.getAppInstance().removeActivity(this);//将当前activity移除管理栈
        super.onDestroy();
    }

}
