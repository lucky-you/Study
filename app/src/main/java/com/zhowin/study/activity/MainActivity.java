package com.zhowin.study.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.permission.runtime.Permission;
import com.zhowin.study.R;
import com.zhowin.study.permission.AndPermissionListener;
import com.zhowin.study.permission.AndPermissionUtils;
import com.zhowin.study.view.HorizontalMarqueeView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HorizontalMarqueeView.OnSopScrollListener {

    private HorizontalMarqueeView tvDynamicText;
    View marqueeViewOne, marqueeViewTwo;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDynamicText = findViewById(R.id.tvDynamicText);
        mContext = this;
        findViewById(R.id.btnVideoPlay).setOnClickListener(this::onClick);
        findViewById(R.id.btnPlaying).setOnClickListener(this::onClick);
        findViewById(R.id.btnScrollableLayout).setOnClickListener(this::onClick);
        findViewById(R.id.btnEmojiLayout).setOnClickListener(this::onClick);
        initPermission();
        addViewToMarquee();

    }


    private void initPermission() {
        AndPermissionUtils.requestPermission(mContext, new AndPermissionListener() {
                    @Override
                    public void PermissionSuccess(List<String> permissions) {
                        Log.e("xy", "权限获取成功");
                    }

                    @Override
                    public void PermissionFailure(List<String> permissions) {

                    }

                    @Override
                    public void OpenSystemSettings() {

                    }
                }, Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE);

    }


    private void addViewToMarquee() {
        marqueeViewOne = View.inflate(this, R.layout.include_dynamic_marquee_item_view, null);
        marqueeViewTwo = View.inflate(this, R.layout.include_home_marquee_item_view, null);
        marqueeViewOne.findViewById(R.id.tvSendGiftName).setOnClickListener(this);
        marqueeViewOne.findViewById(R.id.tvReceiveGiftName).setOnClickListener(this);
        tvDynamicText.addViewInQueue(marqueeViewOne);
        tvDynamicText.setOnSopScrollListener(this);
        tvDynamicText.startScroll();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSendGiftName:
                Log.e("xy", "点击了送礼物的人");
                break;
            case R.id.tvReceiveGiftName:
                Log.e("xy", "点击了收礼物的人");
                break;
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

        }
    }


    @Override
    public void onStopScroll() {
        tvDynamicText.removeViewInQueue(marqueeViewOne);
        tvDynamicText.stopScroll();
//        tvDynamicText.addViewInQueue(marqueeViewTwo);
//        tvDynamicText.startScroll();
    }
}
