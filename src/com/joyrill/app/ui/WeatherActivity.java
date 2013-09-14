package com.joyrill.app.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.joyrill.app.R;
import com.joyrill.app.cache.Cache;
import com.joyrill.app.model.Weather;
import com.joyrill.app.thread.WeatherThread;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.util.ImageScale;

public class WeatherActivity extends BaseActivity implements OnClickListener,
		Handler.Callback ,Runnable{
	//http://maps.googleapis.com/maps/api/geocode/json?latlng=22.523,114.050&sensor=true&language=zh-CN
	private String urlStyle = "http://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&sensor=true&language=zh-CN";
	private Looper looper = null;
	private Handler uiHandler = null;
	private Cache cache = Cache.getInstance();
	private List<String> list = null;
	private TextView todayTemperature, todayWinddirection, lastUpdateTime,
			todayTemp, todayLiveingIndex,cityName;
	private ImageView todayStartImage;
	private Weather weather = null;
	Double Lat;
	Double Lon;
	private String o;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.weather);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		initComponent();
		initListener();
		uiHandler.sendEmptyMessage(0);
	}

	private void initComponent() {
		todayTemperature = (TextView) this.findViewById(R.id.todayTemperature);
		todayWinddirection = (TextView) this.findViewById(R.id.todayWinddirection);
		lastUpdateTime = (TextView) this.findViewById(R.id.lastUpdateTime);
		todayTemp = (TextView) this.findViewById(R.id.todayTemp);
		todayLiveingIndex = (TextView) this.findViewById(R.id.todayLiveingIndex);
		todayStartImage = (ImageView) this.findViewById(R.id.todayStartImage);
		cityName=(TextView)this.findViewById(R.id.cityName);
	}

	private void initListener() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0: {
			getLoaction();
			if (null == cache.getValue(Cache.WEATHER_CACHE)) {
				WeatherThread weatherThread = new WeatherThread(uiHandler, 1,
						o);
				new Thread(weatherThread).start();
			}
			break;
		}
		case 1: {
			if (null == cache.getValue(Cache.WEATHER_CACHE)) {
				list = (List<String>) msg.obj;
				cache.putValue(Cache.WEATHER_CACHE, msg.obj);
			} else {
				list = (List<String>) cache.getValue(Cache.WEATHER_CACHE);
			}
			handlerWeather();
			break;
		}
		default:
			break;
		}
		return false;
	}

	private void getLoaction(){
		Location loc;

		LocationManager locMan;

		LocationProvider locPro;

		List<String> proList;

		locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

		proList = locMan.getAllProviders();

		locPro = locMan.getProvider(proList.get(0));

		loc = locMan.getLastKnownLocation(proList.get(0));

		Lat = loc.getLatitude();

		Lon = loc.getLongitude();
		Thread t = new Thread(this);
		t.start();
	}
	private void handlerWeather() {
		weather = new Weather(list);
		cityName.setText(weather.getCity());
		todayTemperature.setText(weather.getTodayTemperature()
				.replace("/", "~"));
		todayWinddirection.setText(weather.getTodayWindDirectionAndWindForce());
		lastUpdateTime.setText("更新时间" + weather.getLastUpdatTime());
		todayStartImage.setImageBitmap(ImageScale.getImageFromAssetsFile("a_"
				+ weather.getTodayStartImage()));
		todayLiveingIndex.setText(weather.getTodayLiveingIndex());
	}

	private String geocodeAddr(String latitude, String longitude) {
		StringBuffer sb = new StringBuffer();
		String url = String.format(urlStyle,latitude, longitude);
		URL myURL = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		HttpURLConnection urlConnection;
		try {
			urlConnection = (HttpURLConnection) myURL.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			byte[] b = new byte[12048];
			int a = in.read(b);
			if (a > 0) {
				String c = new String(b, 0, a);
				sb.append(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public void onClick(View v) {

	}

	private void setWeatherImage(String id) {
	}

	@Override
	public void run() {
		String json = geocodeAddr(String.valueOf(Lat),String.valueOf(Lon));
		try {
			o = JSON.parseObject(json.toString()).getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(3).getString("short_name");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
