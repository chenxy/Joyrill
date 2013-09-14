package com.joyrill.app.ui;

import java.util.Stack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.ui.base.BaseActivityGroup;
import com.joyrill.app.util.UIHelper;
import com.joyrill.app.util.UpdateManager;

public class JoyrillActivity extends BaseActivityGroup implements
		OnClickListener, Handler.Callback {

	private ImageButton areaBtn, typeBtn, sceneBtn, timerBtn, musicBtn,
			monitorBtn, securityBtn, weatherBtn, settingBtn, toolbarBack,
			toobarHome;
	private LinearLayout contentBody;
	private TextView toobarText;
	private Looper looper = null;
	private Handler uiHandler = null;
	private Stack<Module> history = null;
	private static JoyrillActivity activity = null;

	enum Module {
		AREA, TYPE, SCENE, TIMER, MUSIC, MONITOR, SECURITY, WEATHER, SETTING;
	}

	public static JoyrillActivity getInstance() {
		return activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		history = new Stack<Module>();
		uiHandler.sendEmptyMessage(0);
		activity = this;
		this.initComponent();
		this.initListener();
		UpdateManager.getUpdateManager().checkAppUpdate(this, false);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 4) {
			UIHelper.Exit(this);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			break;

		default:
			break;
		}
		return false;
	}

	private void initComponent() {
		this.areaBtn = (ImageButton) this.findViewById(R.id.areaBtn);
		this.typeBtn = (ImageButton) this.findViewById(R.id.typeBtn);
		this.sceneBtn = (ImageButton) this.findViewById(R.id.sceneBtn);
		this.timerBtn = (ImageButton) this.findViewById(R.id.timerBtn);
		this.musicBtn = (ImageButton) this.findViewById(R.id.musicBtn);
		this.monitorBtn = (ImageButton) this.findViewById(R.id.monitorBtn);
		this.securityBtn = (ImageButton) this.findViewById(R.id.securityBtn);
		this.weatherBtn = (ImageButton) this.findViewById(R.id.weatherBtn);
		this.settingBtn = (ImageButton) this.findViewById(R.id.settingBtn);
		this.contentBody = (LinearLayout) this.findViewById(R.id.containerBody);
		this.toobarText = (TextView) this.findViewById(R.id.toobarText);
		this.toolbarBack = (ImageButton) this.findViewById(R.id.toolbarBack);
		this.toobarHome = (ImageButton) this.findViewById(R.id.toolbarHome);
	}

	private void initListener() {
		this.areaBtn.setOnClickListener(this);
		this.typeBtn.setOnClickListener(this);
		this.sceneBtn.setOnClickListener(this);
		this.timerBtn.setOnClickListener(this);
		this.musicBtn.setOnClickListener(this);
		this.monitorBtn.setOnClickListener(this);
		this.securityBtn.setOnClickListener(this);
		this.weatherBtn.setOnClickListener(this);
		this.settingBtn.setOnClickListener(this);
		this.typeBtn.setOnClickListener(this);
		this.toolbarBack.setOnClickListener(this);
		this.toobarHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				contentBody.removeAllViews();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		contentBody.removeAllViews();
		switch (v.getId()) {
		case R.id.areaBtn: {
			changeModule(Module.AREA,AreaActivity.class);
			setToolBarText(R.string.menu_ui_area);
			push(Module.AREA);
			break;
		}
		case R.id.typeBtn: {
			changeModule(Module.TYPE,TypeActivity.class);
			setToolBarText(R.string.menu_ui_type);
			push(Module.TYPE);
			break;
		}
		case R.id.sceneBtn: {
			changeModule(Module.SCENE,SceneActivity.class);
			setToolBarText(R.string.menu_ui_area);
			push(Module.SCENE);
			break;
		}
		case R.id.timerBtn: {
			changeModule(Module.TIMER,TimerActivity.class);
			setToolBarText(R.string.menu_ui_clock);
			push(Module.TIMER);
			break;
		}
		case R.id.musicBtn: {
			changeModule(Module.MUSIC,MusicActivity.class);
			setToolBarText(R.string.menu_ui_music);
			push(Module.MUSIC);
			break;
		}
		case R.id.monitorBtn: {
			changeModule(Module.MONITOR,MonitorActivity.class);
			setToolBarText(R.string.menu_ui_monitor);
			push(Module.MONITOR);
			break;
		}
		case R.id.securityBtn: {
			changeModule(Module.SECURITY,SecurityActivity.class);
			setToolBarText(R.string.menu_ui_security);
			push(Module.SECURITY);
			break;
		}
		case R.id.weatherBtn: {
			changeModule(Module.WEATHER,WeatherActivity.class);
			setToolBarText(R.string.menu_ui_weather);
			push(Module.WEATHER);
			break;
		}
		case R.id.settingBtn: {
			changeModule(Module.SETTING,SettingActivity.class);
			setToolBarText(R.string.menu_ui_setting);
			push(Module.SETTING);
			break;
		}
		case R.id.toolbarBack: {
			changeHistory();
		}
		default: {

		}
		}

	}

	private void changeHistory() {
		switch (pop()) {
		case AREA: {
			changeModule(Module.AREA,AreaActivity.class);
			setToolBarText(R.string.menu_ui_area);
			break;
		}
		case TYPE: {
			changeModule(Module.TYPE, TypeActivity.class);
			setToolBarText(R.string.menu_ui_type);
			break;

		}
		case SCENE: {
			changeModule(Module.SCENE,SceneActivity.class);
			setToolBarText(R.string.menu_ui_scene);
			break;

		}
		case TIMER: {
			changeModule(Module.TIMER,TimerActivity.class);
			setToolBarText(R.string.menu_ui_clock);
			break;

		}
		case MONITOR: {
			changeModule(Module.MONITOR,MonitorActivity.class);
			setToolBarText(R.string.menu_ui_monitor);
			break;
		}
		case SECURITY: {
			changeModule(Module.SECURITY,SecurityActivity.class);
			setToolBarText(R.string.menu_ui_security);
			break;
		}
		case WEATHER: {
			changeModule(Module.WEATHER,WeatherActivity.class);
			setToolBarText(R.string.menu_ui_weather);
			break;
		}
		case SETTING: {
			changeModule(Module.SETTING,SettingActivity.class);
			setToolBarText(R.string.menu_ui_setting);
			break;
		}
		default:
			break;
		}
	}

	private Module pop() {
		if (history.size() == 1) {
			return history.peek();
		} else {
			history.pop();
			return history.pop();
		}
	}

	private void push(Module module) {
		if (history.size() == 0) {
			history.push(module);
		} else {
			if (!history.peek().equals(module)) {
				history.push(module);
			} else {

			}
		}
	}

	private void setToolBarText(int textId) {
		toobarText.setText(getString(textId));
	}

	private void changeModule(Module module,Class<?> className) {
		Intent intent = new Intent(this, className);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		Logger.d("changeModule : "+module.toString());
		@SuppressWarnings("deprecation")
		Window subActivity = getLocalActivityManager().startActivity(module.toString(), intent);
		contentBody.addView(subActivity.getDecorView(),
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	}


}
