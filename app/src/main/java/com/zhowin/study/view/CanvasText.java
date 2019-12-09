package com.zhowin.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author Z_B
 * date :2019/12/9 17:26
 * description:
 */
public class CanvasText extends View {

    private int viewWidth;
    private int viewHeight;
    private Paint paint;
    private TextPaint textPaint;
    private float ascent;
    private float descent;
    private float textOffset;
    private float startX = 0;
    private float startY = 0;
    private float endX = 0;
    private float endY = 0;
    private RectF rectF;

    public CanvasText(Context context) {
        this(context, null);
    }

    public CanvasText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CanvasText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        /*
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(0xFFEE0000);
        //初始化文字画笔
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(50);
        textPaint.setColor(0xFF000000);
        textPaint.setTextAlign(Paint.Align.CENTER);
//        //文字的上坡度和下坡度。用于计算偏移量
        ascent = textPaint.ascent();
        descent = textPaint.descent();
        //偏移量，用于辅助文字居中
        textOffset = (ascent + descent) / 2;
        startX = 10;
        startY = 10;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        endX = viewWidth - 10;
        endY = viewHeight - 10;
        rectF = new RectF(startX, startY, endX, endY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        // 平移和旋转画布
        canvas.translate(viewWidth / 2, viewHeight / 2 - textOffset);
        canvas.rotate(-30);
        //绘制文字。因为这个时候，画布已经移动到控件中心点了，如果在中心位置写字，字的中心坐标，就是(0,0)
        canvas.drawText("￥3000元", 0, 0, textPaint);
        // 释放画布
        canvas.restore();
        canvas.drawRoundRect(rectF, 50, 50, paint);

    }
}
