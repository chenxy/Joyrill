package com.joyrill.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.joyrill.app.cache.Cache;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.User;
import com.joyrill.app.operation.ParameterOperation;
import com.joyrill.app.util.SharedPreferencesHelper;

public class JoyrillApplicationInitialize {

	@SuppressLint("SdCardPath")
	public static String dbname = "zjdata";
	private Context context;
	private Cache cache = Cache.getInstance();
	private File dbfile = null, dir = null;
	private SharedPreferencesHelper helper = null;

	public JoyrillApplicationInitialize(Context context) {
		this.context = context;
		this.helper = new SharedPreferencesHelper(context);
	}

	public synchronized void initDatabase() {

		dbfile = context.getDatabasePath("zjdata.db");
		dir = dbfile.getParentFile();
		while (!dir.exists()) {
			dir.mkdirs();
		}
		if (!dbfile.exists()) {
			InputStream input = null;
			OutputStream output = null;

			try {
				Logger.d("start copy database from assert!");
				input = context.getResources().getAssets().open(dbname);
				output = new FileOutputStream(dbfile);
				byte[] buffer = new byte[2048];
				int length;
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
				Logger.d("copy database success !");
			} catch (Exception e) {
				Logger.e(e.getMessage());
			}
		}

	}

	public void initLanguage() {
		Configuration config = context.getResources().getConfiguration();
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		String able = context.getResources().getConfiguration().locale
				.getCountry();
		String language = helper.getValue("language", able);
		if ("en".equalsIgnoreCase(language)) {
			config.locale = Locale.ENGLISH;
		} else if ("CN".equalsIgnoreCase(language)) {
			config.locale = Locale.SIMPLIFIED_CHINESE;
		} else {

		}
		context.getResources().updateConfiguration(config, dm);
	}

	public void initCache() {
		// cache.putValue(Cache.USER_CACHE, User.findDefaultUser());
		cache.putValue(Cache.USER_CACHE, new User("admin", "123456"));

	}

	public void initData() {
		if (null == helper.getValue("FirstUse")) {
			helper.putValue("FirstUse", "0");
			ParameterOperation.autoLogin(false);
		}
		ParameterOperation.saveLoginType("2");
	}
}
