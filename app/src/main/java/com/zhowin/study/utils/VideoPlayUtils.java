package com.zhowin.study.utils;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.io.File;

/**
 * author Z_B
 * date :2019/12/2 16:41
 * description:
 */
public class VideoPlayUtils {

    //播放本地视频
    public static void playLocalVideo(Activity activity, VideoView mVideoView, String mVideoPath, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (mVideoView == null || TextUtils.isEmpty(mVideoPath)) return;
        //获取屏幕宽度
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        //获取视频宽高
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(mVideoPath);
        float mediaWidth = dm.widthPixels;
        float mediaHeight = dm.heightPixels;
        try {
            int rotation = Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION));
            if (rotation == 90 || rotation == 270) {
                mediaWidth = Float.parseFloat(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                mediaHeight = Float.parseFloat(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
            } else {
                mediaWidth = Float.parseFloat(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                mediaHeight = Float.parseFloat(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
            }
        } catch (NullPointerException e) {
            ToastUtils.showLong("视频播放错误，请重试");
            File file = new File(mVideoPath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            activity.finish();
        }
        //根据比例占全屏
        int width = 0;
        int height = 0;
        if (mediaWidth / mediaHeight > dm.widthPixels / dm.heightPixels) {
            width = dm.widthPixels;
            height = (int) (mediaHeight / (mediaWidth / dm.widthPixels));
        } else {
            height = dm.heightPixels;
            width = (int) (mediaWidth / (mediaHeight / dm.heightPixels));
        }
        ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mVideoView.setLayoutParams(layoutParams);
        //视频路径
        mVideoView.setVideoPath(mVideoPath);
        //循环播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion(mp);
                }

            }
        });
        mVideoView.setVisibility(View.VISIBLE);
        mVideoView.start();
    }

}
