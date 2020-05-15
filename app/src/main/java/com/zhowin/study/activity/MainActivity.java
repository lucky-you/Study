package com.zhowin.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.yanzhenjie.permission.runtime.Permission;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.permission.AndPermissionListener;
import com.zhowin.study.permission.AndPermissionUtils;
import com.zhowin.study.skin.SkinUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    private Button btnVideoPlay, btnPlaying, btnScrollableLayout, btnEmojiLayout, btnRecorderVideoLayout,
            btnOpenGLVideoLayout, btnEasyCameraVideoLayout, btnVoicePlayer, btnBottomSheet, btnSnapHelper, btnSkinStore;

    public static boolean isInitView = false;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindViews(View contentView) {
        btnVideoPlay = findViewById(R.id.btnVideoPlay);
        btnVideoPlay.setOnClickListener(this::onClick);
        btnPlaying = findViewById(R.id.btnPlaying);
        btnPlaying.setOnClickListener(this::onClick);
        btnScrollableLayout = findViewById(R.id.btnScrollableLayout);
        btnScrollableLayout.setOnClickListener(this::onClick);
        btnEmojiLayout = findViewById(R.id.btnEmojiLayout);
        btnEmojiLayout.setOnClickListener(this::onClick);
        btnRecorderVideoLayout = findViewById(R.id.btnRecorderVideoLayout);
        btnRecorderVideoLayout.setOnClickListener(this::onClick);
        btnOpenGLVideoLayout = findViewById(R.id.btnOpenGLVideoLayout);
        btnOpenGLVideoLayout.setOnClickListener(this::onClick);
        btnEasyCameraVideoLayout = findViewById(R.id.btnEasyCameraVideoLayout);
        btnEasyCameraVideoLayout.setOnClickListener(this::onClick);
        btnVoicePlayer = findViewById(R.id.btnVoicePlayer);
        btnVoicePlayer.setOnClickListener(this::onClick);
        btnBottomSheet = findViewById(R.id.btnBottomSheet);
        btnBottomSheet.setOnClickListener(this::onClick);
        btnSnapHelper = findViewById(R.id.btnSnapHelper);
        btnSnapHelper.setOnClickListener(this::onClick);
        btnSkinStore = findViewById(R.id.btnSkinStore);
        btnSkinStore.setOnClickListener(this::onClick);
        findViewById(R.id.btnViewPager).setOnClickListener(this::onClick);
        findViewById(R.id.btnGalleryLayout).setOnClickListener(this::onClick);
        initPermission();
        initViews();
    }

    private void initViews() {
        int defaultColorResId = SkinUtils.getSkin(this).getAccentColor();
        int defaultColorNameResId = SkinUtils.getSkin(this).getColorName();
        Log.e("xy", "color:" + defaultColorResId + "--colorName:" + mContext.getString(defaultColorNameResId));
        btnVideoPlay.setBackgroundColor(defaultColorResId);
        btnPlaying.setBackgroundColor(defaultColorResId);
        btnScrollableLayout.setBackgroundColor(defaultColorResId);
        btnEmojiLayout.setBackgroundColor(defaultColorResId);
        btnRecorderVideoLayout.setBackgroundColor(defaultColorResId);
        btnOpenGLVideoLayout.setBackgroundColor(defaultColorResId);
        btnEasyCameraVideoLayout.setBackgroundColor(defaultColorResId);
        btnVoicePlayer.setBackgroundColor(defaultColorResId);
        btnBottomSheet.setBackgroundColor(defaultColorResId);
        btnSnapHelper.setBackgroundColor(defaultColorResId);
        btnSkinStore.setBackgroundColor(defaultColorResId);

    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (isInitView) {
            initViews();
        }
        MainActivity.isInitView = false;
    }

    @Override
    public void setClickListener(View view) {
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
            case R.id.btnSnapHelper:
                mContext.startActivity(new Intent(mContext, SnapHelperRecyclerViewActivity.class));
                break;
            case R.id.btnSkinStore:
                mContext.startActivity(new Intent(mContext, SkinStoreActivity.class));
                break;
            case R.id.btnViewPager:
                mContext.startActivity(new Intent(mContext, ViewPagerActivity.class));
                break;
            case R.id.btnGalleryLayout:
                mContext.startActivity(new Intent(mContext, GalleryLayoutActivity.class));
                break;

        }
    }

    private void initPermission() {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
                    @Override
                    public void PermissionSuccess(List<String> permissions) {
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
}
