package com.zhowin.study.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhowin.study.R;
import com.zhowin.study.model.ThatMessageList;

import java.util.List;

/**
 * author Z_B
 * date :2019/11/25 9:11
 * description:
 */
public class ItemOneListAdapter extends BaseQuickAdapter<ThatMessageList, BaseViewHolder> {
    public ItemOneListAdapter(@Nullable List<ThatMessageList> data) {
        super(R.layout.include_one_list_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ThatMessageList item) {
        helper.setText(R.id.tvContent, item.getTitle());

    }
}
