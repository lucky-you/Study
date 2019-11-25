package com.zhowin.study.activity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.study.R;
import com.zhowin.study.utils.ToastUtils;
import com.zhowin.study.voide.activity.BaseLoginActivity;

import java.io.IOException;
import java.util.Timer;

/**
 * 语音通话——》拨打界面
 */
public class VoiceAudioActivity extends BaseLoginActivity {
    private AssetFileDescriptor mAssetFileDescriptor;
    private MediaPlayer mediaPlayer;
    private RoundedImageView mCallAvatar;
    private TextView mCallName;
    private ImageButton mHangUp;
    private boolean isAllowBack = false;


    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_voice_audio;
    }

    @Override
    public void bindViews(View contentView) {
        mCallAvatar = findViewById(R.id.call_avatar);
        mCallName = findViewById(R.id.call_name);
        mHangUp = findViewById(R.id.call_hang_up);
        TextView wait_tv = findViewById(R.id.call_wait);
        TextView hang_up_tv = findViewById(R.id.call_hang_up_tv);
        mHangUp.setOnClickListener(this::setClickListener);
        callBell();// 响铃
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    /**
     * 拨号响铃
     */
    private void callBell() {
        try {
            mAssetFileDescriptor = getAssets().openFd("dial.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mAssetFileDescriptor.getFileDescriptor(), mAssetFileDescriptor.getStartOffset(), mAssetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消响铃
     */
    private void cancelBell() {
        try {
            mediaPlayer.stop();
        } catch (Exception e) {
            // 在华为手机上疯狂点击挂断按钮会出现崩溃的情况
        }
        mediaPlayer.release();
    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.call_hang_up:
                cancelBell();
                ToastUtils.showLong("挂断");
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        if (isAllowBack) {
//            super.onBackPressed();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mAssetFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
