package com.zhowin.study.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhowin.study.R;
import com.zhowin.study.emoticon.EmotionView;

public class EmojiLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editMessage;
    private EmotionView  EmojiLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_layout);
        editMessage = findViewById(R.id.editMessage);
        EmojiLayout = findViewById(R.id.EmojiLayout);
        findViewById(R.id.btnSend).setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

    }
}
