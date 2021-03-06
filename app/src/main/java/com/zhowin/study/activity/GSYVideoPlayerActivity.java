package com.zhowin.study.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhowin.study.R;
import com.zhowin.study.base.BaseActivity;
import com.zhowin.study.utils.ConstantValues;
import com.zhowin.study.utils.GlideUtils;

/**
 * 视频播放--》GSYVideoPlayer
 */
public class GSYVideoPlayerActivity extends BaseActivity {

    private StandardGSYVideoPlayer gsyVideoPlayer;
    private OrientationUtils orientationUtils;


    @Override
    protected void onPause() {
        super.onPause();
        gsyVideoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gsyVideoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            gsyVideoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        gsyVideoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_gsyvideo_player;
    }

    @Override
    public void bindViews(View contentView) {
        gsyVideoPlayer = findViewById(R.id.gsyVideoPlayer);

    }

    @Override
    public void processLogic(Bundle savedInstanceState) {
        initViews();
    }

    private void initViews() {
        gsyVideoPlayer.setUp(ConstantValues.BIG_VIDEO_URL, true, "B型车");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideUtils.loadImage(mContext, ConstantValues.imageList[0], imageView);
        gsyVideoPlayer.setThumbImageView(imageView);

//        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_FULL);
        //增加title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, gsyVideoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(v -> orientationUtils.resolveByClick());
        //是否可以滑动调整
        gsyVideoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        gsyVideoPlayer.getBackButton().setOnClickListener(v -> onBackPressed());
//        gsyVideoPlayer.startPlayLogic();
    }

    @Override
    public void setClickListener(View view) {

    }
}
