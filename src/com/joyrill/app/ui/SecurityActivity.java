package com.joyrill.app.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.joyrill.app.R;
import com.joyrill.app.config.Constants;
import com.joyrill.app.controller.SecurityController;
import com.joyrill.app.ui.base.BaseActivity;

public class SecurityActivity extends BaseActivity implements OnClickListener ,Handler.Callback{

	private Button outdoorArming,outdoorDisarming,indoorArming,indoorDisarming;
	private SecurityController securityController = null; 
	private Looper looper = null;
	private Handler uiHandler = null;
	private RadioGroup armingGroup = null;
	private int checkId = 0;;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.security);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		securityController = new SecurityController();
		this.initComponent();
		this.initListener();
		uiHandler.sendEmptyMessage(0);
	}

	private void initComponent(){
		outdoorArming = (Button)this.findViewById(R.id.outdoorArming);
		outdoorDisarming = (Button)this.findViewById(R.id.outdoorDisarming);
		indoorArming = (Button)this.findViewById(R.id.indoorArming);
		indoorDisarming = (Button)this.findViewById(R.id.indoorDisarming);
		armingGroup = (RadioGroup)this.findViewById(R.id.armingGroup);
		
	}
	
	private void initListener(){
		outdoorArming.setOnClickListener(this);
		outdoorDisarming.setOnClickListener(this);
		indoorArming.setOnClickListener(this);
		indoorDisarming.setOnClickListener(this);
		armingGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				checkId =group.getCheckedRadioButtonId();
			}
		});
	}

	private int getCheckedType(){
		int temp = 0;
		switch (checkId) {
		case R.id.armingImmediate:{
			temp = 0;
			break;
		}
		case R.id.armingDelay:{
			temp = 1;
			break;
		}
		default:
			break;
		}
		return temp;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.indoorArming:
		{
			securityController.indoorArming("INDOOR", 1,getCheckedType(), null, 1);
			break;
		}
		case R.id.indoorDisarming:
		{
			securityController.indoorArming("INDOOR", 0,getCheckedType(), null, 1);
			break;
		}
		case R.id.outdoorArming:
		{
			securityController.indoorArming("OURDOOR", 1,getCheckedType(), null, 1);
			break;
		}
		case R.id.outdoorDisarming:
		{
			securityController.indoorArming("OUTDOOR", 0,getCheckedType(), null, 1);
			break;
		}

		default:
			break;
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0:{
			checkId = R.id.armingImmediate;
			break;
		}
		case 1:{
			Bundle bundle = msg.getData();
			String str = bundle.getString(Constants.RETURN);
			Toast.makeText(this, str, Toast.LENGTH_LONG).show();
			break;
		}

		default:
			break;
		}
		return false;
	}
}
