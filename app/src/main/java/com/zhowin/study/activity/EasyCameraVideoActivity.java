package com.zhowin.study.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.zho.camera.JCameraView;
import com.zho.camera.listener.ClickListener;
import com.zho.camera.listener.ErrorListener;
import com.zho.camera.listener.JCameraListener;
import com.zho.camera.util.FileUtil;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.utils.BitmapUtil;
import com.zhowin.study.utils.ToastUtils;

import java.io.File;

/**
 * 视频录制  依赖 camera  model
 */
public class EasyCameraVideoActivity extends BaseActivity {

    private JCameraView jCameraView;

    @Override
    public void initData(@Nullable Bundle bundle) {
        // 全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_easy_camera_video;
    }

    @Override
    public void bindViews(View contentView) {
        jCameraView = findViewById(R.id.jcameraView);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        // 设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        // 设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "a.JCamera");
        // 设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        // JCameraView监听
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                // 打开Camera失败回调
                Log.i("xy", "open camera error");
            }

            @Override
            public void AudioPermissionError() {
                // 没有录取权限回调
                Log.i("xy", "AudioPermissionError");
            }
        });

        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                // 获取图片bitmap
                String path = BitmapUtil.saveBitmap(bitmap);
                Log.i("xy", "path = " + path);
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                // 获取视频路径
                Log.i("xy", "url = " + url);
            }
        });
        // 左边按钮点击事件
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showLong("点击了左侧返回按钮");
                finish();
            }
        });
        // 右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                ToastUtils.showLong("点击了右侧完成按钮");
            }
        });
    }

    @Override
    public void setClickListener(View view) {
    }
}
