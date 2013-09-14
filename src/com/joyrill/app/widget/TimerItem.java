package com.joyrill.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.util.ImageScale;

public class TimerItem extends LinearLayout{

	private View v;
	private TextView textView;
	private ImageView imageView;
	private Button timerOpen,timerClose;
	public TimerItem(Context context){
		this(context , null);
	}
	
	public TimerItem(Context context, AttributeSet attrs){
		super(context , attrs);
		 v = LayoutInflater.from(context).inflate(R.layout.timer_item, this, true);
		 textView = (TextView)v.findViewById(R.id.timerName);
		 imageView = (ImageView)v.findViewById(R.id.timerimgIco);
		 timerOpen = (Button)v.findViewById(R.id.timerOpen);
		 timerClose = (Button)v.findViewById(R.id.timerClose);
		 
	}
	
	public void setText(String text){
		textView.setText(text);
	}
	
	public void setBackgroundResource(String resource){
		imageView.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public Button getTimerOpenButtonOpen(){
		return this.timerOpen;
	}
	
	public Button getTimerOpenButtonClose(){
		return this.timerClose;
	}
}
