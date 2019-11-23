package com.zhowin.study.voide.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zhowin.study.R;

/**
 * 语音通话--》接收界面
 */
public class VoiceAudioRingActivity extends BaseLoginActivity {

    private RoundedImageView mInviteAvatar;
    private TextView mInviteName;
    private TextView mInviteInfo;
    private ImageButton mAnswer; // 接听
    private ImageButton mHangUp; // 挂断
    private String mLoginUserId;
    private String mLoginUserName;
    private int mCallType;
    private String call_fromUser;
    private String call_toUser;
    private String call_Name;
    private String meetUrl;

    @Override
    public void initData(@Nullable Bundle bundle) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);// 锁屏也可显示 | Activity启动时点亮屏幕
    }

    @Override
    public int loadViewLayout() {
        return R.layout.activity_voice_audio_ring;
    }

    @Override
    public void bindViews(View contentView) {
        mInviteAvatar = findViewById(R.id.call_avatar);
        mInviteName = findViewById(R.id.call_name);
        mInviteInfo = findViewById(R.id.call_invite_type);
        mAnswer = findViewById(R.id.call_answer);
        mHangUp = findViewById(R.id.call_hang_up);
        mAnswer.setOnClickListener(this::setClickListener);
        mHangUp.setOnClickListener(this::setClickListener);
    }

    @Override
    public void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void setClickListener(View view) {
        switch (view.getId()) {
            case R.id.call_answer:
                //接听
                break;
            case R.id.call_hang_up:
                //挂断
                break;
        }

    }
}
