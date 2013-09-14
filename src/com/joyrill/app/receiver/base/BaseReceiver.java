package com.joyrill.app.receiver.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BaseReceiver extends BroadcastReceiver {
	public static final String action= "com.joyrill.app.command";
	public static final String KEY="MESSAGE";
	@Override
	public void onReceive(Context context, Intent intent) {
		//Logger.d("recevie broadcaset");
	}

}
