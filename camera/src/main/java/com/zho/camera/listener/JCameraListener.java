package com.zho.camera.listener;

import android.graphics.Bitmap;

public interface JCameraListener {

    void captureSuccess(Bitmap bitmap);

    void recordSuccess(String url, Bitmap firstFrame);

}
