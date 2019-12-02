package com.zhowin.study.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.zhowin.study.R;
import com.zhowin.study.utils.DisplayUtils;

/**
 * author Z_B
 * date :2019/12/2 17:45
 * description: Dialog的基类
 */
public abstract class BaseStyleDialog extends Dialog {
    protected Context mContext;
    protected View rootView;

    public BaseStyleDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (rootView == null) {
            rootView = View.inflate(mContext, getLayoutResId(), null);
            Window window = this.getWindow();
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setContentView(rootView);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = DisplayUtils.dp2px(mContext, 320);
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            window.setAttributes(params);
            window.setWindowAnimations(R.style.BottomDialogStyle);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            initView();
        }
    }

    public abstract int getLayoutResId();

    public abstract void initView();

}
