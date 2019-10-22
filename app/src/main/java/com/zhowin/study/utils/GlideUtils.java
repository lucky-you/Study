package com.zhowin.study.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhowin.study.R;


/**
 * author      : Z_B
 * date       : 2018/11/21
 * function  : Glide图片加载的工具类
 */
public class GlideUtils {


    /**
     * 加载视频封面--->正常加载封面
     */
    public static void loadVideoCover(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);
    }


    /**
     * 加载普通图片
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_default_image_view)
                .error(R.drawable.ic_default_image_view)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(url)
                .apply(options)
                .into(imageView);
    }


}
