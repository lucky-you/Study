package com.zhowin.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhowin.study.model.ThatGiftList;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿照弹幕样式的额View
 */
public class ZhoWinBarrageView extends FrameLayout {
    private Context mContext;
    private int mScreenWidth;
    private List<View> viewList;
    private List<ThatGiftList> theGiftList = new ArrayList<>();

    public ZhoWinBarrageView(@NonNull Context context) {
        this(context, null);
    }

    public ZhoWinBarrageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZhoWinBarrageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    private void initViews() {

    }

    public void setTheGiftList(List<ThatGiftList> theGiftList) {
        this.theGiftList = theGiftList;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    //获取屏幕宽度
    private int getScreenWidth() {
        WindowManager mWm = (WindowManager) this.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        mWm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
