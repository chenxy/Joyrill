package com.joyrill.app.ui.base;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;

import com.joyrill.app.JoyrillActivityManager;
import com.joyrill.app.logger.Logger;

@SuppressWarnings("deprecation")
public class BaseActivityGroup extends ActivityGroup {

	public BaseActivityGroup() {
	}

	@Override
	public Activity getCurrentActivity() {
		Logger.d(this.getClass().getSimpleName()
				+ " getCurrentActivity() invoked");
		return super.getCurrentActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Logger.d(this.getClass().getSimpleName() + " onCreate() invoked");
		super.onCreate(savedInstanceState);
		JoyrillActivityManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		Logger.d(this.getClass().getSimpleName() + " onDestroy() invoked");
		super.onDestroy();
		JoyrillActivityManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onPause() {
		Logger.d(this.getClass().getSimpleName() + " onPause() invoked");
		super.onPause();
	}

	@Override
	protected void onResume() {
		Logger.d(this.getClass().getSimpleName() + " onResume() invoked");
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Logger.d(this.getClass().getSimpleName()
				+ " onSaveInstanceState() invoked");
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		Logger.d(this.getClass().getSimpleName() + " onStop() invoked");
		super.onStop();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		Logger.d(this.getClass().getSimpleName() + " onNewIntent() invoked");
		super.onNewIntent(intent);
	}

	@Override
	protected void onRestart() {
		Logger.d(this.getClass().getSimpleName() + " onRestart() invoked");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		Logger.d(this.getClass().getSimpleName() + " onStart() invoked");
		super.onStart();
	}

	
}
