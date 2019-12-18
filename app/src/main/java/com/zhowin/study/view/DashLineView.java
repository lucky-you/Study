package com.zhowin.study.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhowin.study.R;

/**
 * author Z_B
 * date :2019/12/18 8:42
 * description: 画一条虚线
 */
public class DashLineView extends View {
    private Paint mPaint;
    private Path mPath;
    // 线的颜色
    private int lineColor;
    // 线的宽度
    private float lineWidth;

    public DashLineView(Context context) {
        this(context, null);
    }

    public DashLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DashLineView, 0, 0);
        lineWidth = typeArray.getDimension(R.styleable.DashLineView_lineWidth, 4);
        lineColor = typeArray.getColor(R.styleable.DashLineView_lineColor, Color.GRAY);
        typeArray.recycle();
    }

    private void initVariable() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setColor(lineColor);
        mPaint.setPathEffect(new DashPathEffect(new float[]{15, 5}, 0));
        mPath = new Path();
        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        mPath.reset();
        mPath.moveTo(0, centerY);
        mPath.lineTo(getWidth(), centerY);
        canvas.drawPath(mPath, mPaint);
    }


}
