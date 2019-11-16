package com.zhowin.study.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/1/15.
 */
public class InputMethodUtils {


    /**
     * 显示软键盘
     *
     * @param activity
     * @param et
     */
    public static void showKeyboard(Activity activity, EditText et) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null) {
                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
