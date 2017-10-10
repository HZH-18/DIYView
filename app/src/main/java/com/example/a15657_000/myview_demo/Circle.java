package com.example.a15657_000.myview_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 15657_000 on 2017/9/12 0012.
 */

public class Circle extends View {

    private long length;
    private int mMeasureWidth,mMeasureHight;

    private float mCircleXY;
    private float mRadius;

    private RectF mArcRectf;

    private Paint mCirclePaint,mArcPaint,mTextPaint;

    private float mSweepValue = 66;
    private float mSweepAngle;

    private float mShowTextSize;
    private String mShowText;

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureHight = MeasureSpec.getSize(heightMeasureSpec);
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mMeasureWidth,mMeasureHight);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        //所在矩形，起始度数，扫过的角度，时候与圆心相连，画笔
        canvas.drawArc(mArcRectf,270,mSweepAngle,false,mArcPaint);
        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY,mCircleXY+(mShowTextSize/4),mTextPaint);
    }

    private void init(){
        if(mMeasureHight > mMeasureWidth){
            length = mMeasureHight;
        }
        else{
            length = mMeasureWidth;
        }
        mCircleXY = length/2;
        mRadius = (float) (length*0.5/2);
        //指定圆弧所在的矩形大小
        mArcRectf = new RectF((float)(length*0.1),
                (float)(length*0.1),
                (float)(length*0.9),
                (float)(length*0.9));
        mSweepAngle = mSweepValue/100f*360f;
        mShowTextSize = 50;
        mShowText = "android skill";

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(R.color.colorAccent));

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);//设置空心
        mArcPaint.setStrokeWidth((float)(length*0.1));
        mArcPaint.setColor(getResources().getColor(R.color.colorAccent));

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }
}
