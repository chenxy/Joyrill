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

public class DeviceTypeCurtainGupCurtainOutsideTwoWay extends LinearLayout {
	private View v;
	private TextView title,status;
	private ImageView image;
	public DeviceTypeCurtainGupCurtainOutsideTwoWay(Context context){
		this(context , null);
	}
	
	public DeviceTypeCurtainGupCurtainOutsideTwoWay(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.devicetype_curtaingup_curtainoutsidetwoway, this, true);
		title = (TextView)v.findViewById(R.id.curtainoutsidetwowayTitle);
		image = (ImageView)v.findViewById(R.id.curtainoutsidetwowayImage);
		status = (TextView)v.findViewById(R.id.curtainoutsidetwowayStatus);
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
