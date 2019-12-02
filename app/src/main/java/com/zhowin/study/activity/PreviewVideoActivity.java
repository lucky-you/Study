package com.zhowin.study.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.utils.ConstantValues;
import com.zhowin.study.utils.ToastUtils;
import com.zhowin.study.utils.VideoPlayUtils;
import com.zhowin.video.CustomerVideoView;

import java.io.File;

/**
 * 预览视频的activity
 */
public class PreviewVideoActivity extends BaseActivity {
    private String mVideoPath;
    private CustomerVideoView mVideoView;
    private ImageView ivVideoSuspend;

    public static void start(Context context, String videoPath) {
        Intent intent = new Intent(context, PreviewVideoActivity.class);
        intent.putExtra(ConstantValues.VIDEO_PATH, videoPath);
        context.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mVideoPath = bundle.getString(ConstantValues.VIDEO_PATH);
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_preview_video;
    }

    @Override
    public void bindViews(View contentView) {
        mVideoView = get(R.id.mVideoView);
        ivVideoSuspend = get(R.id.iv_video_suspend);
        ivVideoSuspend.setOnClickListener(this::setClickListener);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(mVideoPath)) {
            VideoPlayUtils.playLocalVideo(this, mVideoView, mVideoPath, mediaPlayer -> {
                mVideoView.seekTo(0);
                mVideoView.start();
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.pause();
    }


    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.iv_video_suspend:
                if (mVideoView.isPlaying()) {
                    ivVideoSuspend.setImageResource(R.drawable.ic_video_play);
                    mVideoView.pause();
                } else {
                    ivVideoSuspend.setImageResource(R.drawable.ic_video_suspend);
                    mVideoView.start();
                }
                break;
        }
    }
}
