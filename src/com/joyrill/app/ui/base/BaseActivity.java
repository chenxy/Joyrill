package com.joyrill.app.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.joyrill.app.JoyrillActivityManager;
import com.joyrill.app.logger.Logger;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Logger.d(this.getClass().getSimpleName() + " onCreate() invoked");
		super.onCreate(savedInstanceState);
		JoyrillActivityManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onStart() {
		Logger.d(this.getClass().getSimpleName() + " onStart() invoked");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Logger.d(this.getClass().getSimpleName() + " onRestart() invoked");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Logger.d(this.getClass().getSimpleName() + " onResume() invoked");
		super.onResume();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		Logger.d(this.getClass().getSimpleName() + " onNewIntent() invoked");
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		Logger.d(this.getClass().getSimpleName() + " onPause() invoked");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Logger.d(this.getClass().getSimpleName() + " onStop() invoked");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Logger.d(this.getClass().getSimpleName() + " onDestroy() invoked");
		super.onDestroy();
		JoyrillActivityManager.getAppManager().finishActivity(this);
	}

	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Logger.d(this.getClass().getSimpleName() + " onRestoreInstanceState() invoked");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Logger.d(this.getClass().getSimpleName() + " onSaveInstanceState() invoked");
		super.onSaveInstanceState(outState);
	}

	public void startActivity(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		Logger.d("From Class:" + this.getClass().getSimpleName() + " To Class:"
				+ cls.getSimpleName());
		this.startActivity(intent);
	}

	public void startActivity(Class<?> cls,Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		Logger.d("From Class:" + this.getClass().getSimpleName() + " To Class:"
				+ cls.getSimpleName());
		this.startActivity(intent);
	}
	
}
