package com.zhowin.study.manager;

import android.view.View;


/**
 * author Z_B
 * date :2019/12/2 20:02
 * description:
 */
public class ScaleTransformer implements GalleryLayoutManager.ItemTransformer  {
    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        item.setPivotX(item.getWidth() / 2.f);
        item.setPivotY(item.getHeight()/2.0f);
        float scale = 1 - 0.2f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }
}
