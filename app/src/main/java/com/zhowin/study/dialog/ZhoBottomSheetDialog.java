package com.zhowin.study.dialog;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zhowin.study.R;
import com.zhowin.study.adapter.ItemOneListAdapter;
import com.zhowin.study.model.ThatMessageList;
import com.zhowin.study.view.BaseBottomSheetFrag;

import java.util.ArrayList;
import java.util.List;

/**
 * author Z_B
 * date :2019/11/25 8:52
 * description: 底部弹出的BottomSheetDialog
 */
public class ZhoBottomSheetDialog extends BaseBottomSheetFrag {

    private RecyclerView recyclerView;


    @Override
    public int getLayoutResId() {
        return R.layout.include_bottom_sheet_layout;
    }

    @Override
    public void initView() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        showDialog();
    }


    private void showDialog() {
        List<ThatMessageList> messageLists = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            messageLists.add(new ThatMessageList(i, "我是测试的item" + i));
        }
        ItemOneListAdapter itemOneListAdapter = new ItemOneListAdapter(messageLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(itemOneListAdapter);

    }
}
