package com.example.a15657_000.myview_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by 15657_000 on 2017/9/12 0012.
 */

public class MyAudioBar extends View {

    private Paint mPaint;
    private int offset = 5;
    private int mWidth,mRectHight,mRectWidth;
    private double randomNum;
    private int mRectCount = 12;
    private float mCurrentHeight;
    private LinearGradient linearGradient;


    public MyAudioBar(Context context) {
        super(context);
        init();
    }

    public MyAudioBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyAudioBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredSpectWidth(widthMeasureSpec),getMeasuredSpectHigth(heightMeasureSpec));
    }

    public int getMeasuredSpectWidth(int width){
        int result = 0;
        int mSpectMode = MeasureSpec.getMode(width);
        int mSpectSize = MeasureSpec.getSize(width);
        if(mSpectMode == MeasureSpec.EXACTLY){
            result = mSpectSize;
        }else{
            result = 200;
            if(mSpectMode == MeasureSpec.AT_MOST){
                result = Math.min(result,mSpectSize);
            }
        }
        return result;
    }

    public int getMeasuredSpectHigth(int hight){
        int result = 0;
        int mSpectMode = MeasureSpec.getMode(hight);
        int mSpectSize = MeasureSpec.getSize(hight);
        if(mSpectMode == MeasureSpec.EXACTLY){
            result = mSpectSize;
        }else{
            result = 200;
            if(mSpectMode == MeasureSpec.AT_MOST){
                result = Math.min(result,mSpectSize);
            }
        }
        return result;
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(android.R.color.black));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHight = getHeight();
        mRectWidth = (int) (mWidth*0.6/mRectCount);
        linearGradient = new LinearGradient(0,0,mRectWidth,mRectHight,
                                            Color.BLUE,Color.YELLOW,                                                    Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0;i < mRectCount;i++){
            randomNum = Math.random();
            mCurrentHeight = (float) (randomNum*mRectHight);
            canvas.drawRect((float)(mWidth*0.4/2 + mRectWidth*i + offset),
                            mCurrentHeight,
                            (float)(mWidth*0.4/2 + mRectWidth*(i+1)),
                            mRectHight,
                            mPaint);
        }
        postInvalidateDelayed(300);
    }
}
