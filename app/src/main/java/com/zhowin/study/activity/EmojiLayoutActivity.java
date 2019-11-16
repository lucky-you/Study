package com.zhowin.study.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhowin.study.R;
import com.zhowin.study.emoticon.EmotionView;
import com.zhowin.study.utils.InputMethodUtils;

public class EmojiLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editMessage;
    private EmotionView EmojiLayout;
    private ImageView ivImage, ivCamera, ivEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_layout);
        editMessage = findViewById(R.id.editMessage);
        ivImage = findViewById(R.id.ivImage);
        ivCamera = findViewById(R.id.ivCamera);
        ivEmotion = findViewById(R.id.ivEmotion);
        EmojiLayout = findViewById(R.id.EmojiLayout);
        ivImage.setOnClickListener(this::onClick);
        ivCamera.setOnClickListener(this::onClick);
        ivEmotion.setOnClickListener(this::onClick);
        findViewById(R.id.btnSend).setOnClickListener(this::onClick);
        EmojiLayout.setEditText(editMessage);

        editMessage.setOnTouchListener((view, motionEvent) -> {
            EmojiLayout.hide();
            return false;
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                break;
            case R.id.ivImage:
                break;
            case R.id.ivCamera:
                break;
            case R.id.ivEmotion:
                if (EmojiLayout.isVisible()) {
                    InputMethodUtils.showKeyboard(this, editMessage);
                    EmojiLayout.hide();
                } else {
                    InputMethodUtils.hideKeyboard(this);
                    new Handler().postDelayed(() -> EmojiLayout.show(), 200);
                }
                break;
        }



    }
}
