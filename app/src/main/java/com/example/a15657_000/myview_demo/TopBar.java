package com.example.a15657_000.myview_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by 15657_000 on 2017/9/11 0011.
 */

public class TopBar extends RelativeLayout {


    private int mTitleColor;
    private String mTitleText;
    private float mTitleSize;

    private int mLeftColor;
    private String mLeftText;
    private Drawable mLeftBackground;

    private int mRightColor;
    private String mRightText;
    private Drawable mRightBackground;

    private LayoutParams mLeftLayoutParams,mRightLayoutParams,mTitleLayoutParams;

    public topbarClickListener mListener;

    private Button mLeftButton,mRightButton;
    private TextView mTextView;


    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        mTitleSize = ta.getDimension(R.styleable.TopBar_titleTextSize,10);
        mTitleText = ta.getString(R.styleable.TopBar_title);
        mTitleColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);

        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTextView = new TextView(context);

        mLeftButton.setText(mLeftText);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setTextColor(mLeftColor);

        mRightButton.setText(mRightText);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setTextColor(mRightColor);

        mTextView.setText(mTitleText);
        mTextView.setTextColor(mTitleColor);
        mTextView.setTextSize(mTitleSize);
        mTextView.setGravity(Gravity.CENTER);

        mLeftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftLayoutParams);

        mRightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightLayoutParams);

        mTitleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTextView,mTitleLayoutParams);


        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });


    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnTopBarClickListener(topbarClickListener listener){
        this.mListener = listener;
    }

    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }

    public void setVisable(int id,boolean flag){
        if(flag){
            if(id == 0){
                mLeftButton.setVisibility(VISIBLE);
            }
            else if(id == 1){
                mRightButton.setVisibility(VISIBLE);
            }
        }
        else{
            if(id == 0){
                mLeftButton.setVisibility(GONE);
            }
            else if(id == 1){
                mRightButton.setVisibility(GONE);
            }
        }
    }

}
