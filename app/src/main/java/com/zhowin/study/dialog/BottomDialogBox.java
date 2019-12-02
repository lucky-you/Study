package com.zhowin.study.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.zhowin.study.R;
import com.zhowin.study.base.BaseStyleDialog;

/**
 * author Z_B
 * date :2019/12/2 17:17
 * description: 从底部弹出的dialog
 */
public class BottomDialogBox extends BaseStyleDialog {
    public BottomDialogBox(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.include_bottom_dialog_box_layout;
    }

    @Override
    public void initView() {

    }
}
