package com.zhowin.study.voide.player;

import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * author Z_B
 * date :2019/11/23 15:47
 * description:
 */
public class AudioPlayer {
    private int duration;
    private boolean isPlaying = false;
    private File targetFile;
    private MediaPlayer mMediaPlayer = null;
    private Consumer<AudioPlayer> mOnCompletionListener;

    public AudioPlayer() {

    }

    public void setTarget(File file) {
        this.targetFile = file;
    }

    public void setOnCompletionListener(Consumer<AudioPlayer> consumer) {
        this.mOnCompletionListener = consumer;
    }

    public boolean start() {
        if (targetFile == null) {
            return false;
        }

        mMediaPlayer = new MediaPlayer();

        try {
            mMediaPlayer.setDataSource(targetFile.getAbsolutePath());
            mMediaPlayer.prepare();
            mMediaPlayer.setOnCompletionListener(this::onCompletion);
            mMediaPlayer.setOnPreparedListener(mp -> mMediaPlayer.start());

            this.duration = mMediaPlayer.getDuration();
        } catch (IOException e) {
            e.printStackTrace();

            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        boolean result = (mMediaPlayer != null);
        this.isPlaying = result;

        return result;
    }

    public void stop() {
        this.isPlaying = false;
        this.duration = 0;

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void resume() {
        if (mMediaPlayer != null) {
            this.isPlaying = true;

            mMediaPlayer.start();
        }
    }

    public void pause() {
        this.isPlaying = false;

        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    public boolean isRunning() {
        return (mMediaPlayer != null);
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public int getDuration() {
        if (mMediaPlayer == null) {
            return this.duration;
        }

        return mMediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        if (mMediaPlayer == null) {
            return 0;
        }

        return mMediaPlayer.getCurrentPosition();
    }

    public MediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCompletion(MediaPlayer mp) {
        this.stop();
        if (mOnCompletionListener != null) {
            mOnCompletionListener.accept(this);
        }
    }
}
