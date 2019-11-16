package com.zhowin.study.utils;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author      : Z_B
 * date       : 2019/1/10
 * function  : 视频存放
 */
public class VideoFileUtil {


    private static final String ROOT_DIR = "AhoWinVideo";
    private static String sRootPath = "";
    private static boolean hasInitialize = false;

    public static void init(Context context) {
        if (hasInitialize) return;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + ROOT_DIR;
        } else {
            sRootPath = context.getCacheDir().getAbsolutePath() + "/" + ROOT_DIR;
        }
        File file = new File(sRootPath);
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) {
                throw new RuntimeException("create file failed");
            }
        }
        hasInitialize = true;
    }

    public static File newMp4File() {
        SimpleDateFormat format = new SimpleDateFormat("MM_dd_HH_mm_ss", Locale.CHINA);
        return new File(sRootPath, "mp4_" + format.format(new Date()) + ".mp4");
    }


    public static String getSDPath() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ToastUtils.showLong("SD卡不可用!");
            return null;
        }
        File sdDir = Environment.getExternalStorageDirectory();
        File eis = new File(sdDir.toString() + "/" + ROOT_DIR + "/");
        if (!eis.exists()) {
            eis.mkdir();
        }
        return sdDir.toString() + "/" + ROOT_DIR + "/";
    }

    public static String getVideoName() {
        return "VID_" + new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(new Date()) + ".mp4";
    }
}
