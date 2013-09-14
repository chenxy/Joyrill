package com.joyrill.app.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	public static final String USER_CACHE = "UserCache";
	public static final String URL_CACHE = "UrlCache";
	public static final String WEATHER_CACHE="weatherCache";
	
	private static Cache instance = new Cache();
	private Map<String,Object> map = new HashMap<String,Object>();
	private Cache(){
	}
	public static Cache getInstance(){
		return instance;
	}
	public void putValue(String key , Object object){
		map.put(key, object);
	}
	public Object getValue(String key){
		return map.get(key);
	}
}
