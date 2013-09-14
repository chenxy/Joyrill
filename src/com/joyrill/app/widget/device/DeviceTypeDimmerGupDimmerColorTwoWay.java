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

public class DeviceTypeDimmerGupDimmerColorTwoWay extends LinearLayout {
	private View v;
	private TextView title,status;
	private ImageView image;
	private SeekBar statusBar;
	
	private Device device = null;
	private DeviceController controller = null;
	public DeviceTypeDimmerGupDimmerColorTwoWay(Context context){
		this(context , null);
	}
	
	public DeviceTypeDimmerGupDimmerColorTwoWay(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.devicetype_dimmergup_dimmercolortwoway, this, true);
		title = (TextView)v.findViewById(R.id.dimmercolortwowayTitle);
		image = (ImageView)v.findViewById(R.id.dimmercolortwowayImage);
		statusBar = (SeekBar)v.findViewById(R.id.dimmercolortwowayStatusBar);
		status = (TextView)v.findViewById(R.id.dimmercolortwowayStatus);
	
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
			controller.changeDeviceStatus(device, seekBar.getProgress());
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				status.setText(statusBar.getProgress()*100/statusBar.getMax()+"%");
			}
		});
	}
}
