package com.zhowin.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhowin.study.R;
import com.zhowin.study.adapter.SkinStoreAdapter;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.skin.Skin;
import com.zhowin.study.skin.SkinUtils;

import java.util.List;

/**
 * 主题商店
 */
public class SkinStoreActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView skinRecyclerView;
    private SkinStoreAdapter skinStoreAdapter;
    private List<Skin> skinList; //皮肤集合
    private Skin currentSkin; //当前皮肤

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_skin_store;
    }

    @Override
    public void bindViews(View contentView) {
        skinRecyclerView = get(R.id.skinRecyclerView);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        // 当前皮肤
        currentSkin = SkinUtils.getSkin(this);
        // 初始化皮肤
        skinList = SkinUtils.defaultSkins;
        skinStoreAdapter = new SkinStoreAdapter(skinList);
        skinStoreAdapter.setCurrentSkin(currentSkin);
        skinRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        skinRecyclerView.setAdapter(skinStoreAdapter);
        skinStoreAdapter.setOnItemClickListener(this::onItemClick);
        skinStoreAdapter.setCurrentSkin(currentSkin);
    }

    @Override
    public void setClickListener(View view) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        currentSkin = skinList.get(position);
        SkinUtils.setSkin(mContext, currentSkin);
        skinStoreAdapter.setCurrentSkin(currentSkin);
        MainActivity.isInitView = true;
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
