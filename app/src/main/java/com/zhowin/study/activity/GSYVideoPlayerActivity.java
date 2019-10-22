package com.zhowin.study.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhowin.study.R;
import com.zhowin.study.utils.ConstantValues;
import com.zhowin.study.utils.GlideUtils;

/**
 * 视频播放--》GSYVideoPlayer
 */
public class GSYVideoPlayerActivity extends AppCompatActivity {

    private StandardGSYVideoPlayer gsyVideoPlayer;
    private Context mContext;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsyvideo_player);
        mContext = this;
        gsyVideoPlayer = findViewById(R.id.gsyVideoPlayer);
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
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        gsyVideoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        gsyVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        gsyVideoPlayer.startPlayLogic();
    }

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
}
