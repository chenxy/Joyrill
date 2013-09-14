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

public class BaseAdjust extends LinearLayout {
	private View v;
	private TextView title,status;
	private ImageView image;
	private SeekBar statusBar;
	private Device device = null;
	private DeviceController controller = null;
	public BaseAdjust(Context context){
		this(context , null);
	}
	
	public BaseAdjust(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.base_adjust, this, true);
		title = (TextView)v.findViewById(R.id.baseAdjustTitle);
		image = (ImageView)v.findViewById(R.id.baseAdjustImage);
		statusBar = (SeekBar)v.findViewById(R.id.baseAdjustStatusBar);
		status = (TextView)v.findViewById(R.id.baseAdjustStatus);
	}
	
	public void setText(String text){
		title.setText(text);
	}
	
	public void setBackgroundResource(String resource){
		if(null != resource){
			image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
		}
	}
	
	public void setStatus(String s){
		if(null != s&&!"".equals(s)){
			statusBar.setProgress(Integer.parseInt(s));
			status.setText(statusBar.getProgress()*100/statusBar.getMax()+"%");
		}
		else{
			statusBar.setProgress(0);
			status.setText(statusBar.getProgress()*100/statusBar.getMax()+"%");
		}
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
