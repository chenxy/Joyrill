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
import com.joyrill.app.controller.SceneController;
import com.joyrill.app.model.Scene;
import com.joyrill.app.util.ImageScale;

public class SceneItem extends LinearLayout{

	private View v;
	private TextView textView;
	private ImageView imageView;
	private Button start;
	private SceneController sceneConteroller;
	private Scene scene;
	public SceneItem(Context context){
		this(context , null);
	}
	
	public SceneItem(Context context, AttributeSet attrs){
		super(context , attrs);
		 v = LayoutInflater.from(context).inflate(R.layout.scene_item, this, true);
		 textView = (TextView)v.findViewById(R.id.sceneName);
		 imageView = (ImageView)v.findViewById(R.id.sceneimgIco);
		 start = (Button)v.findViewById(R.id.sceneStart);
	}
	
	public void setText(String text){
		textView.setText(text);
	}
	
	public void setBackgroundResource(String resource){
		imageView.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public Button getButton(){
		return start;
	}
	
	public void setOnClickListener(Scene s,SceneController sceneController){
		this.sceneConteroller  = sceneController;
		this.scene = s;
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sceneConteroller.excuteScene(1, scene);
			}
			
		});
	}
	
}
