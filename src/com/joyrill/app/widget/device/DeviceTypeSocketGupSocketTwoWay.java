package com.joyrill.app.widget.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.model.Device;
import com.joyrill.app.util.ImageScale;

public class DeviceTypeSocketGupSocketTwoWay extends LinearLayout {
	private View v;
	private TextView title,status;
	private ImageView image;
	public DeviceTypeSocketGupSocketTwoWay(Context context){
		this(context , null);
	}
	
	public DeviceTypeSocketGupSocketTwoWay(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.devicetype_socketgup_sockettwoway, this, true);
		title = (TextView)v.findViewById(R.id.sockettwowayTitle);
		image = (ImageView)v.findViewById(R.id.sockettwowayImage);
		status = (TextView)v.findViewById(R.id.sockettwowayStatus);
	}
	
	public void setText(String text){
		title.setText(text);
	}
	
	public void setBackgroundResource(String resource){
		image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public void setStatusText(Device device){
		if(!"".equals(device.getState())&&Integer.parseInt(device.getState())==255){
			status.setText(R.string.status_on);
		}else if(!"".equals(device.getState())&&Integer.parseInt(device.getState())==0){
			status.setText(R.string.status_off);
		}else{
			status.setText(R.string.status_error);
		}
	}
}
