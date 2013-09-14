package com.joyrill.app.widget.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.controller.DeviceController;
import com.joyrill.app.model.Device;
import com.joyrill.app.util.ImageScale;

public class DeviceTypeLightGupLightOneWay extends LinearLayout {
	private View v;
	private TextView textView;
	private ImageView imageView;
	private Button turnOn,turnClose;
	private DeviceController controller;	
	private Device device;
	public DeviceTypeLightGupLightOneWay(Context context) {
		this(context, null);
	}

	public DeviceTypeLightGupLightOneWay(Context context, AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(
				R.layout.devicetype_lightgup_lightoneway, this, true);
		textView = (TextView) v.findViewById(R.id.lightonewayType);
		imageView = (ImageView) v.findViewById(R.id.lightonewayImage);
		turnOn = (Button)v.findViewById(R.id.lightonewayButtonOpen);
		turnClose = (Button)v.findViewById(R.id.lightonewayButtonClose);
	}

	public void setText(String text) {
		textView.setText(text);
	}

	public void setBackgroundResource(String resource) {
		imageView.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public void setOnClickListener(Device d,DeviceController c){
		this.device = d;
		this.controller = c;
		turnOn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				controller.openDevice(device);
			}
		});
		turnClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				controller.closeDevice(device);
			}
		});
	}
}
