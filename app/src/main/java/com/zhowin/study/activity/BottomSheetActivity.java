package com.zhowin.study.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.dialog.ZhoBottomSheetDialog;

public class BottomSheetActivity extends BaseActivity {


    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_bottom_sheet;
    }

    @Override
    public void bindViews(View contentView) {
        get(R.id.btnDialogStyle).setOnClickListener(this::setClickListener);
        get(R.id.btnListStyle).setOnClickListener(this::setClickListener);
        get(R.id.btnFragmentStyle).setOnClickListener(this::setClickListener);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.btnDialogStyle:
                break;
            case R.id.btnListStyle:
                break;
            case R.id.btnFragmentStyle:
                break;
        }
    }

    private void showDialog() {

//        final BottomSheetDialog dialog = new BottomSheetDialog(this);
//        dialog.setContentView(recyclerView);
//        dialog.show();

    }
}
