package com.joyrill.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;

import com.joyrill.app.R;

public class ButtonEditText extends EditText {

	private ImageButton mButton;
	private int mButtonHeightPadding = 20;
	private int mButtonWeightPadding = 20;
	
	public ButtonEditText(Context context) {
		super(context);
		init();
	}

	public ButtonEditText(Context context , AttributeSet attrs) {
		super(context , attrs);
		init();
	}

	public ButtonEditText(Context context , AttributeSet attrs , int defStyle) {
		super(context , attrs , defStyle);
		init();
	}

	private void init(){
		mButton = new ImageButton(getContext());
		mButton.setBackgroundResource(R.drawable.edittext_ui);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if(event.getY() > mButtonHeightPadding && event.getY() < getHeight() - mButtonHeightPadding
				&& event.getX() >getWidth() - mButtonWeightPadding - mButton.getMeasuredWidth()
				&& event.getX() <getWidth() - mButtonWeightPadding){
			return mButton.dispatchTouchEvent(event);
		}
		return super.dispatchTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.translate(getMeasuredWidth() - (mButton.getMeasuredWidth() + mButtonWeightPadding), 
				mButtonHeightPadding);
		mButton.draw(canvas);
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mButton.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(getMeasuredHeight() - mButtonHeightPadding *2, MeasureSpec.EXACTLY));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mButton.layout(0, 0, mButton.getMeasuredWidth(), mButton.getMeasuredHeight());
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		canvas.save();
		canvas.translate(getMeasuredWidth() - (mButton.getMeasuredWidth() + mButtonWeightPadding), 
				mButtonHeightPadding);
		mButton.draw(canvas);
		canvas.restore();
	}
	
	
	
}
