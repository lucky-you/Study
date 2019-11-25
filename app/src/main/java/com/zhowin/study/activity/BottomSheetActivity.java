package com.zhowin.study.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zhowin.study.R;
import com.zhowin.study.adapter.ItemOneListAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.dialog.ZhoBottomSheetDialog;
import com.zhowin.study.model.ThatMessageList;

import java.util.ArrayList;
import java.util.List;

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
        get(R.id.btnListStyle).setOnClickListener(this::setClickListener);
        get(R.id.btnFragmentStyle).setOnClickListener(this::setClickListener);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.btnListStyle:
                showDialog();
                break;
            case R.id.btnFragmentStyle:
                ZhoBottomSheetDialog zhoBottomSheetDialog = new ZhoBottomSheetDialog();
                zhoBottomSheetDialog.show(getSupportFragmentManager(), "XXX");
                break;
        }
    }

    private void showDialog() {
        List<ThatMessageList> messageLists = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            messageLists.add(new ThatMessageList(i, "我是测试的item" + i));
        }
        RecyclerView recyclerView = new RecyclerView(mContext);
        ItemOneListAdapter itemOneListAdapter = new ItemOneListAdapter(messageLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(itemOneListAdapter);
        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.TransBottomSheetDialogStyle);
        dialog.setContentView(recyclerView);
        dialog.show();

    }
}
