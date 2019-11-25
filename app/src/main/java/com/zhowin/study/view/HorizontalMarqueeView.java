package com.zhowin.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.zhowin.study.R;


/**
 * Created by：Z_B on 2018/5/11 14:50
 * Effect： 水平跑马灯效果
 */
public class HorizontalMarqueeView extends HorizontalScrollView implements Runnable {

    private Context context;
    private LinearLayout mainLayout;//跑马灯滚动部分
    private int scrollSpeed = 5;//滚动速度
    private int scrollDirection = RIGHT_TO_LEFT;//滚动方向
    private int currentX;//当前x坐标
    private int viewMargin = 20;//View间距
    private int viewWidth;//View总宽度
    private int screenWidth;//屏幕宽度
    private int totalScrollX;//计算滚动的总距离
    public static final int LEFT_TO_RIGHT = 1;
    public static final int RIGHT_TO_LEFT = 2;

    private OnSopScrollListener onSopScrollListener;

    public HorizontalMarqueeView(Context context) {
        this(context, null);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    void initView() {
        screenWidth = getScreenWidth();
        mainLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.include_scroll_content, null);
        this.addView(mainLayout);
    }

    public void addViewInQueue(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getScreenWidth(), dp2px(44));
        layoutParams.setMargins(viewMargin, 0, 0, 0);
        view.setLayoutParams(layoutParams);
        mainLayout.addView(view);
        view.measure(0, 0);//测量view
//        viewWidth = viewWidth + view.getMeasuredWidth() + viewMargin;
        viewWidth = view.getMeasuredWidth();

    }

    //开始滚动
    public void startScroll() {
        removeCallbacks(this);
        currentX = (scrollDirection == LEFT_TO_RIGHT ? viewWidth : -screenWidth);
        Log.e("xy", "currentX=" + currentX + "<---viewWidth=" + viewWidth + "<---screenWidth=" + screenWidth);
        post(this);
    }

    //停止滚动
    public void stopScroll() {
        removeCallbacks(this);
    }

    //设置View间距
    public void setViewMargin(int viewMargin) {
        this.viewMargin = viewMargin;
    }

    //设置滚动速度
    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    //设置滚动方向 默认从右向左
    public void setScrollDirection(int scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    /**
     * 移除 view
     */
    public void removeViewInQueue(View view) {
        mainLayout.removeView(view);
    }


    @Override
    public void run() {
        switch (scrollDirection) {
            case LEFT_TO_RIGHT:
                mainLayout.scrollTo(currentX, 0);
                currentX--;
                if (-currentX >= screenWidth) {
                    mainLayout.scrollTo(viewWidth, 0);
                    currentX = viewWidth;
                }
                break;
            case RIGHT_TO_LEFT:
                mainLayout.scrollTo(currentX, 0);
                currentX++;
                if (currentX >= viewWidth) {
                    mainLayout.scrollTo(-screenWidth, 0);
                    currentX = -screenWidth;
                }
                totalScrollX++;
                if (totalScrollX == (3 * getScreenWidth() - viewWidth)) {
                    Log.e("xy", "totalScrollX=" + totalScrollX);
                    totalScrollX = 0;
                    mainLayout.removeAllViews();
                    if (onSopScrollListener != null) {
                        Log.e("xy", "停止滚动");
                        onSopScrollListener.onStopScroll();
                    }
                    return;
                }
                break;
            default:
                break;
        }
        postDelayed(this, 50 / scrollSpeed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    /**
     * 停止滚动的监听
     */
    public interface OnSopScrollListener {
        void onStopScroll();
    }

    public void setOnSopScrollListener(OnSopScrollListener onSopScrollListener) {
        this.onSopScrollListener = onSopScrollListener;
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

    public int dp2px(final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
