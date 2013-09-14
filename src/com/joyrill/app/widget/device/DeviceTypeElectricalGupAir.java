package com.joyrill.app.widget.device;

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
import com.joyrill.app.controller.DeviceController;
import com.joyrill.app.model.Device;
import com.joyrill.app.util.ImageScale;

public class DeviceTypeElectricalGupAir extends LinearLayout {
	private View v;
	private TextView title;
	private ImageView image;
	private Button open,close,to;
	private Device device = null;
	private DeviceController controller = null;
	private Handler handler = null;
	public DeviceTypeElectricalGupAir(Context context){
		this(context , null);
	}
	
	public DeviceTypeElectricalGupAir(Context context, AttributeSet attrs){
		super(context , attrs);
		v = LayoutInflater.from(context).inflate(R.layout.devicetype_electricalgup_air, this, true);
		title = (TextView)v.findViewById(R.id.airTitle);
		image = (ImageView)v.findViewById(R.id.airImage);
		open = (Button)v.findViewById(R.id.airButtonOpen);
		close = (Button)v.findViewById(R.id.airButtonClose);
		to = (Button)v.findViewById(R.id.airButtonTo);
	}
	
	public void setText(String text){
		title.setText(text);
	}
	
	public void setBackgroundResource(String resource){
		image.setImageBitmap(ImageScale.getImageFromAssetsFile(resource));
	}
	
	public void setOnClickListener(Device d,DeviceController c,Handler h){
		this.device = d;
		this.controller = c;
		this.handler = h;
		this.open.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.openDevice(device);
			}
		});
		this.close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				controller.closeDevice(device);
			}
		});
		this.to.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message message = handler.obtainMessage();
				message.what = 42;
				message.obj = device;
				handler.sendMessage(message);
			}
		});
	}
}
