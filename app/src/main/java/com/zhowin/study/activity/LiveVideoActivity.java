package com.zhowin.study.activity;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zhowin.sdk.video.CameraHelper;
import com.zhowin.sdk.video.CameraOrientationDetector;
import com.zhowin.sdk.video.CameraPreviewView;
import com.zhowin.sdk.video.Recorder;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.utils.ToastUtils;
import com.zhowin.study.utils.VideoFileUtil;
import com.zhowin.study.view.RecorderCircleView;

import org.bytedeco.javacv.FrameFilter;
import org.bytedeco.javacv.FrameRecorder;

import java.lang.ref.WeakReference;

/**
 * 视频录制:依赖 video model
 */
public class LiveVideoActivity extends BaseActivity implements View.OnTouchListener {

    private Camera mCamera;
    private int mCameraFace = Camera.CameraInfo.CAMERA_FACING_BACK;     //相机默认后置摄像头
    private Recorder mRecorder;
    private CameraPreviewView mCameraPreviewView;       //预览view
    private RecorderCircleView mRecorderCircleView;     //录像按钮
    private ImageView mImgCamera;       //翻转摄像头
    private VideoHandler mVideoHandler;
    private CameraOrientationDetector mCameraOrientationDetector;
    private int RECORDER_WIDTH;     //视频录制宽度
    private int RECORDER_HEIGHT;    //视频录制高度
    private static final int MIN_RECORDER_TIME = 5000;       //最小录制时长 单位ms
    private static final int MAX_RECORDER_TIME = 20000;      //最大录制时长 单位ms
    private static final int MSG_STOP = 1;      //录制结束


    private static class VideoHandler extends Handler {
        private WeakReference<LiveVideoActivity> mWeakRecorderActivity;
        private LiveVideoActivity mRecorderActivity;

        VideoHandler(LiveVideoActivity recorderActivity) {
            mWeakRecorderActivity = new WeakReference<LiveVideoActivity>(recorderActivity);
            mRecorderActivity = mWeakRecorderActivity.get();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_STOP:
                    mRecorderActivity.stopRecorder();
                    break;
            }
        }
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_live_video;
    }

    @Override
    public void bindViews(View contentView) {
        mRecorderCircleView = findViewById(R.id.recorder_circle_view);
        mRecorderCircleView.setTotalTime(MAX_RECORDER_TIME);
        mImgCamera = findViewById(R.id.img_camera);

        mVideoHandler = new VideoHandler(this);
        //录制尺寸为全屏比例的一半
        DisplayMetrics dm = getResources().getDisplayMetrics();
        RECORDER_WIDTH = dm.widthPixels / 2;
        RECORDER_HEIGHT = dm.heightPixels / 2;
        mCameraOrientationDetector = new CameraOrientationDetector(this);

    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkPermissionBelowM()) {
            ToastUtils.showLong("请前往设置开启摄像头和录音权限");
        } else {
            initRecorder();
        }
    }

    //初始化
    private void initRecorder() {
        //检查sd卡
        VideoFileUtil.init(this);
        if (VideoFileUtil.getSDPath() == null) return;
        //存储路径
        String path = VideoFileUtil.getSDPath() + VideoFileUtil.getVideoName();
        //初始化摄像头
        mCamera = CameraHelper.getCamera(this, mCameraFace);       //默认打开后置摄像头
        //初始化摄像预览界面
        mCameraPreviewView = findViewById(R.id.camera_preview);
        mCameraPreviewView.init(mCamera, RECORDER_WIDTH, RECORDER_HEIGHT);
        //初始化recorder
        mRecorder = new Recorder.Builder()
                .context(getApplicationContext())
                .camera(mCamera)
                .cameraFace(mCameraFace)
                .outputSize(RECORDER_WIDTH, RECORDER_HEIGHT)
                .outputFilePath(path)
                .orientationDetector(mCameraOrientationDetector)
                .build();

        mRecorderCircleView.setOnTouchListener(this);
        mImgCamera.setOnClickListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startRecorder();
                break;
            case MotionEvent.ACTION_UP:
                stopRecorder();
                break;
        }
        return true;
    }


    //开始录制
    private void startRecorder() {
        if (mRecorder == null || mRecorder.isRecording()) {
            return;
        }
        try {
            mRecorder.start();
            mRecorderCircleView.start();
            mVideoHandler.sendEmptyMessageDelayed(MSG_STOP, MAX_RECORDER_TIME);        //MAX_RECORDER_TIME后自动停止
        } catch (FrameRecorder.Exception | FrameFilter.Exception e) {
            e.printStackTrace();
            ToastUtils.showLong("录制失败");
        }
    }

    //结束录制
    private void stopRecorder() {
        if (mRecorder == null || !mRecorder.isRecording()) {
            return;
        }
        if (mVideoHandler.hasMessages(MSG_STOP)) {
            mVideoHandler.removeMessages(MSG_STOP);
        }
        try {
            mRecorderCircleView.stop();
            long recordingTime = mRecorder.getRecordingTime();
            mRecorder.stop();
            if (recordingTime < MIN_RECORDER_TIME) {         //少于最少录制时间
                ToastUtils.showLong("录制时间不能少于" + MIN_RECORDER_TIME / 1000 + "s");
            } else {
                ToastUtils.showLong("录制完成:" + mRecorder.getOutputPath());
//                startActivity(RecoderPlayerActivity.createIntent(this, mRecorder.getOutputPath()));
//                finish();
            }
        } catch (FrameRecorder.Exception | FrameFilter.Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.img_camera:
                if (mRecorder.isRecording()) {       //录制的时候禁止旋转摄像头
                    return;
                }
                switchCamera();
                break;
        }
    }

    //切换前后摄像头
    private void switchCamera() {
        if (mCameraFace == Camera.CameraInfo.CAMERA_FACING_BACK) {       //切换前置
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
                ToastUtils.showLong("不支持前置摄像头");
                return;
            }
        }
        mImgCamera.setOnClickListener(null);
        releaseCamera();
        if (mCameraFace == Camera.CameraInfo.CAMERA_FACING_BACK) {
            mCameraFace = Camera.CameraInfo.CAMERA_FACING_FRONT;
        } else {
            mCameraFace = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        mCamera = CameraHelper.getCamera(this, mCameraFace);
        mCameraPreviewView.switchCamera(mCamera);
        mRecorder.switchCamera(mCamera, mCameraFace);
        mImgCamera.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        releaseHandler();
        releaseRecorder();
        releaseCamera();
        super.onStop();
    }

    //释放handler
    private void releaseHandler() {
        if (mVideoHandler != null) {
            if (mVideoHandler.hasMessages(MSG_STOP)) {
                mVideoHandler.removeMessages(MSG_STOP);
            }
        }
    }

    //释放recorder
    private void releaseRecorder() {
        if (mRecorder != null) {
            if (mRecorder.isRecording()) {
                try {
                    mRecorder.stop();
                } catch (FrameRecorder.Exception | FrameFilter.Exception e) {
                    e.printStackTrace();
                }
            }
            mRecorder = null;
        }
    }

    //释放camera
    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    //检查Android M系统以下的国产定制系统权限处理 （模拟开启 捕获异常来检查权限）
    private static boolean checkPermissionBelowM() {
        boolean canUse = true;
        Camera camera = null;
        AudioRecord audioRecord = null;
        try {
            camera = Camera.open();
            Camera.Parameters mParameters = camera.getParameters();
            camera.setParameters(mParameters);
            int bufferSize = AudioRecord.getMinBufferSize(44100,
                    AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
                    AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
            audioRecord.startRecording();
            if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
                throw new Exception();
            }
            audioRecord.stop();
        } catch (Exception e) {
            canUse = false;
        } finally {
            if (camera != null) {
                camera.release();
            }
            if (audioRecord != null) {
                audioRecord.release();
            }
        }
        return canUse;
    }
}
