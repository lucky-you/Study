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
 * date :2019/12/2 19:34
 * description:
 */
public class ThatSnapHelperItemAdapter extends BaseQuickAdapter<ThatMessageList, BaseViewHolder> {
    public ThatSnapHelperItemAdapter(@Nullable List<ThatMessageList> data) {
        super(R.layout.include_snaphelper_item_view, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ThatMessageList item) {
        helper.setText(R.id.tvItemName, item.getTitle());

    }
}
