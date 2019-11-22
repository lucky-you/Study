package com.zhowin.study.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zho.recorder.View.CameraRecordView;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.filter.FilterPreviewDialog;
import com.zhowin.study.utils.RecorderUtils;
import com.zhowin.study.utils.ToastUtils;
import com.zhowin.study.view.RecorderVideoView;

import java.io.IOException;

/**
 * 视频录制：---》增加了滤镜功能 依赖 OpenGLlibrary model
 */
public class CameraOpenGLVideoActivity extends BaseActivity {

    private CameraRecordView mRecordView;
    private boolean mRecording;
    private String mCurrPath;
    private FilterPreviewDialog mDialog;
    private RecorderVideoView mRecordBtn;
    private RelativeLayout rlMore;
    private TextView mTvTime;
    private int mCurrTime;
    private LinearLayout mWaitDialog;
    private TextView mTvLoding;
    private static final int RECORD_TIME = 4541;
    FilterPreviewDialog.OnUpdateFilterListener listener = new FilterPreviewDialog.OnUpdateFilterListener() {
        @Override
        public void select(int type) {
            mRecordView.switchFilter(type);
        }

        @Override
        public void dismiss() {
            rlMore.setVisibility(View.VISIBLE);
            mRecordBtn.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == RECORD_TIME) {
                mCurrTime++;
                mTvTime.setText("00:" + String.format("%0" + 2 + "d", mCurrTime));
                if (mRecording) {
                    handler.sendEmptyMessageDelayed(RECORD_TIME, 1000);
                }
            } else if (RecorderUtils.COMPRESS_SUCCESS == msg.what) {
                Log.e("xy", "压缩完成" + mCurrPath);
                ToastUtils.showLong("压缩完成" + mCurrPath);
            } else {
                mTvLoding.setText("压缩中" + msg.what + "%");
            }

            return false;
        }
    });


    @Override
    public int loadViewLayout() {
        return R.layout.activity_camera_open_glvideo;
    }

    @Override
    public void bindViews(View contentView) {
        mRecordView = findViewById(R.id.surfaceView);
        mRecordBtn = findViewById(R.id.btn_rec);

        mWaitDialog = findViewById(R.id.ll_loding);
        mTvLoding = findViewById(R.id.tv_loading);

        mTvTime = findViewById(R.id.tv_time);
        rlMore = findViewById(R.id.rl_more);

        mWaitDialog.setOnClickListener(this);
        findViewById(R.id.btn_rec).setOnClickListener(this);
        findViewById(R.id.iv_filter).setOnClickListener(this);
        findViewById(R.id.iv_flash).setOnClickListener(this);
        findViewById(R.id.iv_swith).setOnClickListener(this);
        findViewById(R.id.iv_close).setOnClickListener(this);

        mDialog = new FilterPreviewDialog(this, listener);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    /**
     * 开始录制
     *
     * @return
     */
    private boolean startRecord(String path) {
        try {
            mRecordView.startRecord(path);
            Log.e("xy", "开始录制：" + path);
            mCurrPath = path;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 结束录制
     */
    private boolean stopRecord() {
        try {
            handler.removeMessages(RECORD_TIME);
            mRecordView.stopRecord();
            mRecording = false;
            Log.e("xy", "停止录制：" + mCurrPath);
            compteRecord(mCurrPath);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 完成录制
     */
    public void compteRecord(String path) {
        final String out = RecorderUtils.getVideoFileByTime();
        String[] cmd = RecorderUtils.ffmengComprerssCmd(path, out);
        long duration = RecorderUtils.getDuration(path);
        Log.e("xy", "路径：" + out + "<--时长:" + duration);
    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.iv_filter:
                mDialog.show();
                rlMore.setVisibility(View.GONE);
                mRecordBtn.setVisibility(View.GONE);
                break;
            case R.id.btn_rec:
                if (!mRecording) {
                    //开始录制视频
                    if (startRecord(RecorderUtils.getVideoFileByTime())) {
                        mCurrTime = 0;
                        mRecording = true;
                        mRecordBtn.record();
                        handler.sendEmptyMessageDelayed(RECORD_TIME, 1000);
                    }
                } else {
                    //停止视频录制
                    if (mCurrTime > 1) {
                        mRecordBtn.pause();
                        stopRecord();
                    }
                }
                break;
            case R.id.iv_swith:
                //切换摄像头
                mRecordView.switchCamera();
                break;
            case R.id.iv_close: // 退出录制
                ToastUtils.showLong("退出录制");
                finish();
                break;
        }
    }
}
