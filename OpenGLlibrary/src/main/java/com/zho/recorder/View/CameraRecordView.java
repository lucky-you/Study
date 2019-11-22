package com.zho.recorder.View;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;

import androidx.annotation.RequiresApi;

import com.zho.recorder.OpenGL.CameraRecorder;
import com.zho.recorder.OpenGL.Filter.Mp4EditFilter;
import com.zho.recorder.OpenGL.Renderer;
import com.zho.recorder.Utils.MatrixUtils;

import java.io.IOException;


public class CameraRecordView extends TextureView implements SurfaceTextureListener, Renderer {

    private static final int STATE_INIT = 0;
    private static final int STATE_RECORDING = 1;
    private static final int STATE_PAUSE = 2;
    private int mCameraWidth;
    private int mCameraHeight;
    private Camera.Parameters parameters;
    // 滤镜系统
    private int mCurrentFilterIndex; // 当前滤镜
    private Mp4EditFilter mFilter; //基础滤镜
    // 录制系统
    private CameraRecorder mCameraRecord; //GP录像类
    private int mRecorderState;
    // 摄像头
    private Camera mCamera;
    /**
     * 延时发送通知开始自动对焦
     */
    Handler mCameraAutoFocusCallbackHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mCamera != null) {
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            camera.cancelAutoFocus();
                            doAutoFocus();
                        }
                    }
                });
            }
        }
    };
    private int mCurrentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private int mCameraCount = 0;

    public CameraRecordView(Context context) {
        this(context, null);
    }

    public CameraRecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // 获取摄像头个数
        mCameraCount = Camera.getNumberOfCameras();
        mFilter = new Mp4EditFilter(getResources());
        // 初始化录制
        mCameraRecord = new CameraRecorder();
        // 设置监听
        setSurfaceTextureListener(this);
    }

    /**
     * 切换摄像头
     */
    public void switchCamera() {
        if (mCameraCount > 1) {
            mCurrentCameraId = mCurrentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;
            stopPreview();
            mCamera = Camera.open(mCurrentCameraId);
            mCameraRecord.startPreview();
            requestCameraFocus();
        }
    }

    /**
     * 切换滤镜
     *
     * @param index
     */
    public void switchFilter(int index) {
        if (mCurrentFilterIndex != index) {
            mCurrentFilterIndex = index;
            mFilter.getChooseFilter().setChangeType(mCurrentFilterIndex);
        }
    }

    /**
     * 开始录制
     */
    public void startRecord(String filePath) throws IOException {
        mCameraRecord.setOutputPath(filePath);
        mCameraRecord.startRecord();
        requestCameraFocus();
        mRecorderState = STATE_RECORDING;
    }

    /**
     * 停止录制
     */
    public void stopRecord() throws InterruptedException {
        mCameraRecord.stopRecord();
        mRecorderState = STATE_INIT;
    }

    public void stopPreview() {
        try {
            mCameraRecord.stopPreview();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCamera = Camera.open(mCurrentCameraId);
        mCameraRecord.setOutputSurface(new Surface(surface));
        mCameraRecord.setOutputSize(new Size(width, height));
        mCameraRecord.setRenderer(this);
        mCameraRecord.setPreviewSize(width, height);
        mCameraRecord.startPreview();
//        mCamera.cancelAutoFocus();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        mCameraRecord.setPreviewSize(width, height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        // 停止录制
        if (mRecorderState == STATE_RECORDING) {
            try {
                stopRecord();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopPreview();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void create() {
        try {
            mCamera.setPreviewTexture(mCameraRecord.createInputSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Camera.Size mSize = mCamera.getParameters().getPreviewSize();
        mCameraWidth = mSize.height;
        mCameraHeight = mSize.width;
        mCamera.startPreview();
        requestCameraFocus();
        mFilter.create();
    }

    @Override
    public void sizeChanged(int width, int height) {
        mFilter.sizeChanged(width, height);
        MatrixUtils.getMatrix(mFilter.getVertexMatrix(), MatrixUtils.TYPE_CENTERCROP, mCameraWidth, mCameraHeight, width, height);
        MatrixUtils.flip(mFilter.getVertexMatrix(), false, true);
    }

    @Override
    public void draw(int texture) {
        mFilter.draw(texture);
    }

    @Override
    public void destroy() {
        mFilter.destroy();
    }

    /**
     * 请求相机对焦
     */
    private void requestCameraFocus() {
        if (mCamera != null && mCurrentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            mCameraAutoFocusCallbackHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    /**
     * 设置相机自动对焦
     */
    private void doAutoFocus() {
        parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(parameters);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                        camera.setParameters(parameters);
                    } else {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        camera.setParameters(parameters);
                    }
                }
            }
        });
    }

}
