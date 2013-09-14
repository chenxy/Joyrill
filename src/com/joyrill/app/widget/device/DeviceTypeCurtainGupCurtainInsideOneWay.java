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

public class DeviceTypeCurtainGupCurtainInsideOneWay extends LinearLayout {
	private View v;
	private TextView title;
	private ImageView image;
	private Button turnOn, turnClose;
	private DeviceController controller;
	private Device device;

	public DeviceTypeCurtainGupCurtainInsideOneWay(Context context) {
		this(context, null);
	}

	public DeviceTypeCurtainGupCurtainInsideOneWay(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		v = LayoutInflater.from(context).inflate(
				R.layout.devicetype_curtaingup_curtaininsideoneway, this, true);
		title = (TextView) v.findViewById(R.id.curtaininsideonewayTitle);
		image = (ImageView) v.findViewById(R.id.curtaininsideonewayImage);
		turnOn = (Button) v.findViewById(R.id.curtaininsideonewayButtonOpen);
		turnClose = (Button) v
				.findViewById(R.id.curtaininsideonewayButtonClose);
	}

	public void setText(String text) {
		title.setText(text);
	}

	public void setBackgroundResource(String resource) {
		image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}

	public void setOnClickListener(Device d, DeviceController c) {
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
