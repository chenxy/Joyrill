package com.joyrill.app.service;

import com.joyrill.app.logger.Logger;
import com.joyrill.app.util.ReceiveMsg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MessageService extends Service {

	public MessageService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		Logger.d("MessageService onBind");
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Logger.d("MessageService onCreate");
		new ReceiveMsg().start();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Logger.d("MessageService onStart");
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		Logger.d("MessageService onDestory");
		new ReceiveMsg().stop();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

}
