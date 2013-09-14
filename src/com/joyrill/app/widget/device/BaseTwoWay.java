package com.joyrill.app.widget.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.controller.DeviceController;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Device;
import com.joyrill.app.util.ImageScale;

public class BaseTwoWay extends LinearLayout {
	private Context mContext;
	private View v;
	private TextView title, status;
	private ImageView image;
	private Switch s;

	private Device device = null;
	private DeviceController controller = null;
	
	private boolean isBroadCast = false;
	
	public BaseTwoWay(Context context) {
		this(context, null);
	}

	public BaseTwoWay(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		v = LayoutInflater.from(context).inflate(R.layout.base_twoway, this,
				true);
		title = (TextView) v.findViewById(R.id.basetwowayTitle);
		image = (ImageView) v.findViewById(R.id.basetwowayImage);
		status = (TextView) v.findViewById(R.id.basetwowayStatus);
		s = (Switch) v.findViewById(R.id.basetwowaySwitch);
	}

	public void setText(String text) {
		title.setText(text);
	}

	public void setBackgroundResource(String resource) {
		image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}

	public void setStatusText(Device device) {
		if (!"".equals(device.getState())
				&& Integer.parseInt(device.getState()) == 255) {
			status.setText(R.string.status_on);
		} else if (!"".equals(device.getState())
				&& Integer.parseInt(device.getState()) == 0) {
			status.setText(R.string.status_off);
		} else {
			status.setText(R.string.status_off);
		}
	}

	public void setOnclickListener(Device d, DeviceController c) {
		this.device = d;
		this.controller = c;
		s.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Logger.d("isBroadCast"+isBroadCast);
				if(!isBroadCast){
					if(isChecked){
						controller.openDevice(device);
					}else{
						controller.closeDevice(device);
					}
				}
			}
		});
	}

	public void setStatusText(String id,boolean isBroadCast) {
		Logger.d("baseTwoWay State : " + id);
		this.isBroadCast = isBroadCast;
		if (Integer.parseInt(id) == 255) {
			status.setText(R.string.status_on);
			s.setChecked(true);
		} else if (Integer.parseInt(id) == 0) {
			status.setText(R.string.status_off);
			s.setChecked(false);
		} else {
			status.setText(R.string.status_off);
			s.setChecked(false);
		}
		this.isBroadCast = false;
	}
}
