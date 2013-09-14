package com.joyrill.app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
	private static final String NAME = "config";
	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;
	
	Context context;
	
	public SharedPreferencesHelper(Context context){
		this.context = context;
		this.sharedPreferences = this.context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
	}
	
	public void putValue(String key, String value){
		editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void putValue(String key, boolean value){
		editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public String getValue(String key){
		return sharedPreferences.getString(key, null);
	}
	
	public String getValue(String key,String defValue){
		return sharedPreferences.getString(key, defValue);
	}
	
	public boolean getBoolean(String key){
		return sharedPreferences.getBoolean(key, false);
	}
	
}
