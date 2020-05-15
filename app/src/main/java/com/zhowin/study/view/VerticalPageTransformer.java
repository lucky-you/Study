package com.zhowin.study.view;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * author Z_B
 * date :2020/5/15 8:44
 * description:
 */
public class VerticalPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View view, float position) {
        /**
         *  0 当前界面
         *  -1 前一页
         *  1 后一页
         */
        if (position >= -1 && position <= 1) {
            view.setTranslationX(view.getWidth() * -position);
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
        }
    }
}
