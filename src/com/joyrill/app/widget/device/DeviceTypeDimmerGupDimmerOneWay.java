package com.joyrill.app.widget.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.controller.DeviceController;
import com.joyrill.app.model.Device;
import com.joyrill.app.util.ImageScale;

public class DeviceTypeDimmerGupDimmerOneWay extends LinearLayout {
	private View v;
	private TextView title,status;
	private ImageView image;
	private SeekBar statusBar;
	private Device device = null;
	private DeviceController controller = null;
	public DeviceTypeDimmerGupDimmerOneWay(Context context){
		this(context , null);
	}
	
	public DeviceTypeDimmerGupDimmerOneWay(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.devicetype_dimmergup_dimmeroneway, this, true);
		title = (TextView)v.findViewById(R.id.dimmeronewayTitle);
		image = (ImageView)v.findViewById(R.id.dimmeronewayImage);
		statusBar = (SeekBar)v.findViewById(R.id.dimmeronewayStatusBar);
		status = (TextView)v.findViewById(R.id.dimmeronewayStatus);
	}
	
	public void setText(String text){
		title.setText(text);
		status.setText(statusBar.getProgress()*100/statusBar.getMax()+"%");
	}
	
	public void setBackgroundResource(String resource){
		image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public void setOnClickListener(Device d,DeviceController c){
		this.device = d;
		this.controller = c;
		this.statusBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				status.setText(statusBar.getProgress()*100/statusBar.getMax()+"%");
				controller.changeDeviceStatus(device, statusBar.getProgress());
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});
	}
}
