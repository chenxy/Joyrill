package com.joyrill.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import cn.jpush.android.api.JPushInterface;

import com.joyrill.app.exception.JoyrillException;

import dalvik.system.VMRuntime;

public class JoyrillApplication extends Application {

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	private static Context context;

	JoyrillApplicationInitialize initialize;

	static {

	}

	@Override
	public void onCreate() {
		super.onCreate();

		Thread.setDefaultUncaughtExceptionHandler(JoyrillException
				.getAppExceptionHandler());

		final int HEAP_SIZE = 64 * 1024 * 1024;
		VMRuntime.getRuntime().setMinimumHeapSize(HEAP_SIZE);

		context = this;
		this.setEnviroment();
		initialize.initDatabase();
		initialize.initLanguage();
		initialize.initCache();
		initialize.initData();

		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
	}

	public static Context getInstance() {
		return context;
	}

	private void setEnviroment() {
		initialize = new JoyrillApplicationInitialize(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	public String getAppId() {
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
}
