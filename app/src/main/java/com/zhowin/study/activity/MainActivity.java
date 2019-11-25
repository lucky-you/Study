package com.zhowin.study.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yanzhenjie.permission.runtime.Permission;
import com.zhowin.study.R;
import com.zhowin.study.permission.AndPermissionListener;
import com.zhowin.study.permission.AndPermissionUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViewById(R.id.btnVideoPlay).setOnClickListener(this::onClick);
        findViewById(R.id.btnPlaying).setOnClickListener(this::onClick);
        findViewById(R.id.btnScrollableLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnEmojiLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnRecorderVideoLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnOpenGLVideoLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnEasyCameraVideoLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnVoicePlayer).setOnClickListener(this::onClick);
        findViewById(R.id.btnBottomSheet).setOnClickListener(this::onClick);
        initPermission();

    }


    private void initPermission() {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
                    @Override
                    public void PermissionSuccess(List<String> permissions) {
//                        Log.e("xy", "权限获取成功");
                    }

                    @Override
                    public void PermissionFailure(List<String> permissions) {

                    }

                    @Override
                    public void OpenSystemSettings() {

                    }
                }, Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.CAMERA,
                Permission.RECORD_AUDIO);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVideoPlay:
                mContext.startActivity(new Intent(mContext, GSYVideoPlayerActivity.class));
                break;
            case R.id.btnPlaying:
                mContext.startActivity(new Intent(mContext, CoordinatorLayoutActivity.class));
                break;
            case R.id.btnScrollableLayout:
                mContext.startActivity(new Intent(mContext, ScrollableLayoutActivity.class));
                break;
            case R.id.btnEmojiLayout:
                mContext.startActivity(new Intent(mContext, EmojiLayoutActivity.class));
                break;
            case R.id.btnRecorderVideoLayout:
                mContext.startActivity(new Intent(mContext, LiveVideoActivity.class));
                break;
            case R.id.btnOpenGLVideoLayout:
                mContext.startActivity(new Intent(mContext, CameraOpenGLVideoActivity.class));
                break;
            case R.id.btnEasyCameraVideoLayout:
                mContext.startActivity(new Intent(mContext, EasyCameraVideoActivity.class));
                break;
            case R.id.btnVoicePlayer:
                mContext.startActivity(new Intent(mContext, VoiceAudioActivity.class));
                break;
            case R.id.btnBottomSheet:
                mContext.startActivity(new Intent(mContext, BottomSheetActivity.class));
                break;

        }
    }


}
