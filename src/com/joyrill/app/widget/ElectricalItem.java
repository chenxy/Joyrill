package com.joyrill.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.util.ImageScale;

public class ElectricalItem extends LinearLayout {
	private View v;
	private TextView textView;
	private ImageView imageView;

	public ElectricalItem(Context context) {
		this(context, null);
	}

	public ElectricalItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(
				R.layout.devicetype_curtaingup_curtaininsidetwoway, this, true);
		textView = (TextView) v.findViewById(R.id.sceneName);
		imageView = (ImageView) v.findViewById(R.id.sceneimgIco);
	}

	public void setText(String text) {
		textView.setText(text);
	}

	public void setBackgroundResource(String resource) {
		imageView.setImageBitmap(ImageScale
				.getImageFromAssetsFile(resource));
	}

}
