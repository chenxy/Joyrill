package com.joyrill.app.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

public class MyImageButton extends LinearLayout {

	private View v;
	private TextView textView;
	private ImageView imageView;
	private Handler uiHandler;
	private int id;
	public MyImageButton(Context context) {
		this(context, null);
	}

	public MyImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(R.layout.image_item, this,
				true);
		textView = (TextView) v.findViewById(R.id.imageButtonDesc);
		imageView = (ImageView) v.findViewById(R.id.imageButton);
	}

	public void setText(String text) {
		textView.setText(text);
	}

	public void setBackgroundResource(String resource) {
		imageView.setImageBitmap(ImageScale
				.getImageFromAssetsFile(resource));
	}

	public void setOnClickListener(Handler handler,int ids) {
		uiHandler = handler;
		this.id = ids;
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message message = uiHandler.obtainMessage();
				message.arg1 = id;
				message.what = 1;
				uiHandler.sendMessage(message);		
			}
		});

	}

}
