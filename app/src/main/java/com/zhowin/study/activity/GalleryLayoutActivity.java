package com.zhowin.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhowin.study.R;
import com.zhowin.study.adapter.GalleryListAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.gallery.CardScaleHelper;
import com.zhowin.study.gallery.GalleryLayoutManager;
import com.zhowin.study.gallery.ScaleTransformer;
import com.zhowin.study.gallery.SpeedRecyclerView;
import com.zhowin.study.model.ThatMessageList;
import com.zhowin.study.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


public class GalleryLayoutActivity extends BaseActivity {

    private RecyclerView GalleryLayoutView;
    private SpeedRecyclerView speedRecyclerView;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_gallery_layout;
    }

    @Override
    public void bindViews(View contentView) {
        GalleryLayoutView = get(R.id.GalleryLayoutView);
        speedRecyclerView = get(R.id.speedRecyclerView);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        setRecyclerViewData();
        setSpeedRecyclerViewData();
    }

    @Override
    public void setClickListener(View view) {

    }


    private void setRecyclerViewData() {
        List<ThatMessageList> thatMessageList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            thatMessageList.add(new ThatMessageList(1, "测试的第" + i + "条"));
        }
        GalleryLayoutManager layoutManager1 = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        layoutManager1.attach(GalleryLayoutView);
        layoutManager1.canScrollHorizontally();
        layoutManager1.setItemTransformer(new ScaleTransformer());
        int position = layoutManager1.getCurSelectedPosition();
        Log.e("GalleryLayoutManager", "position:" + position);
        GalleryListAdapter galleryListAdapter = new GalleryListAdapter(mContext, thatMessageList);
        galleryListAdapter.setOnItemClickListener(new GalleryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GalleryLayoutView.smoothScrollToPosition(position);
                ToastUtils.showLong("click:" + position);
            }
        });
        GalleryLayoutView.setAdapter(galleryListAdapter);
    }


    private void setSpeedRecyclerViewData() {
        List<ThatMessageList> thatMessageList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            thatMessageList.add(new ThatMessageList(1, "测试的第" + i + "条"));
        }
        GalleryListAdapter galleryListAdapter = new GalleryListAdapter(mContext, thatMessageList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        speedRecyclerView.setLayoutManager(linearLayoutManager);
        speedRecyclerView.setAdapter(galleryListAdapter);

        // mRecyclerView绑定scale效果
        CardScaleHelper mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(speedRecyclerView);
    }
}
