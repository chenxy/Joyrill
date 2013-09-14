package com.joyrill.app.model;

import java.util.List;

public class Weather {
	/**
	 * String(0) 到 String(4)：省份，城市，城市代码，城市图片名称，最后更新时间。
	 * String(5) 到 String(11)：当天的 气温，概况，风向和风力，天气趋势开始图片名称(以下称：图标一)，天气趋势结束图片名称(以下称：图标二)，
	 * 现在的天气实况，天气和生活指数。
	 * String(12) 到 String(16):第二天的 气温，概况，风向和风力，图标一，图标二。
	 * String(17) 到 String(21):第三天的 气温，概况，风向和风力，图标一，图标二。
	 * String(22) 被查询的城市或地区的介绍 
	 */
	private String province;
	private String city;
	private String cityCode;
	private String cityImage;
	private String lastUpdatTime;
	private String todayTemperature;
	private String todayDescribe;
	private String todayWindDirectionAndWindForce;
	private String todayStartImage;
	private String todayEndImage;
	private String todayNowWeather;
	private String todayLiveingIndex;
	private String tomorrowTemperature;
	private String tomorrowWindDirectionAndWindForce;
	private String tomorrowDescribe;
	private String tomorrowStartImage;
	private String tomorrowEndImage;
	private String theDayAfterTomorrowTemperature;
	private String theDayAfterTomorrowWindDirectionAndWindForce;
	private String theDayAfterTomorrowDescribe;
	private String theDayAfterTomorrowStartImage;
	private String theDayAfterTomorrowEndImage;
	private String cityDesc;
	
	public Weather() {
		super();
	}
	public Weather(List<String> weather){
		this(weather.get(0),weather.get(1),weather.get(2),weather.get(3),weather.get(4),weather.get(5),
				weather.get(6),weather.get(7),weather.get(8),weather.get(9),weather.get(10),weather.get(11),
				weather.get(12),weather.get(13),weather.get(14),weather.get(15),weather.get(16),weather.get(17),
				weather.get(18),weather.get(19),weather.get(20),weather.get(21),weather.get(22));
	}
	
	public Weather(String province, String city, String cityCode,
			String cityImage, String lastUpdatTime, String todayTemperature,
			String todayDescribe, String todayWindDirectionAndWindForce,
			String todayStartImage, String todayEndImage,
			String todayNowWeather, String todayLiveingIndex,
			String tomorrowTemperature,
			String tomorrowWindDirectionAndWindForce, String tomorrowDescribe,
			String tomorrowStartImage, String tomorrowEndImage,
			String theDayAfterTomorrowTemperature,
			String theDayAfterTomorrowWindDirectionAndWindForce,
			String theDayAfterTomorrowDescribe,
			String theDayAfterTomorrowStartImage,
			String theDayAfterTomorrowEndImage, String cityDesc) {
		super();
		this.province = province;
		this.city = city;
		this.cityCode = cityCode;
		this.cityImage = cityImage;
		this.lastUpdatTime = lastUpdatTime;
		this.todayTemperature = todayTemperature;
		this.todayDescribe = todayDescribe;
		this.todayWindDirectionAndWindForce = todayWindDirectionAndWindForce;
		this.todayStartImage = todayStartImage;
		this.todayEndImage = todayEndImage;
		this.todayNowWeather = todayNowWeather;
		this.todayLiveingIndex = todayLiveingIndex;
		this.tomorrowTemperature = tomorrowTemperature;
		this.tomorrowWindDirectionAndWindForce = tomorrowWindDirectionAndWindForce;
		this.tomorrowDescribe = tomorrowDescribe;
		this.tomorrowStartImage = tomorrowStartImage;
		this.tomorrowEndImage = tomorrowEndImage;
		this.theDayAfterTomorrowTemperature = theDayAfterTomorrowTemperature;
		this.theDayAfterTomorrowWindDirectionAndWindForce = theDayAfterTomorrowWindDirectionAndWindForce;
		this.theDayAfterTomorrowDescribe = theDayAfterTomorrowDescribe;
		this.theDayAfterTomorrowStartImage = theDayAfterTomorrowStartImage;
		this.theDayAfterTomorrowEndImage = theDayAfterTomorrowEndImage;
		this.cityDesc = cityDesc;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityImage() {
		return cityImage;
	}
	public void setCityImage(String cityImage) {
		this.cityImage = cityImage;
	}
	public String getLastUpdatTime() {
		return lastUpdatTime;
	}
	public void setLastUpdatTime(String lastUpdatTime) {
		this.lastUpdatTime = lastUpdatTime;
	}
	public String getTodayTemperature() {
		return todayTemperature;
	}
	public void setTodayTemperature(String todayTemperature) {
		this.todayTemperature = todayTemperature;
	}
	public String getTodayDescribe() {
		return todayDescribe;
	}
	public void setTodayDescribe(String todayDescribe) {
		this.todayDescribe = todayDescribe;
	}
	public String getTodayWindDirectionAndWindForce() {
		return todayWindDirectionAndWindForce;
	}
	public void setTodayWindDirectionAndWindForce(
			String todayWindDirectionAndWindForce) {
		this.todayWindDirectionAndWindForce = todayWindDirectionAndWindForce;
	}
	public String getTodayStartImage() {
		return todayStartImage;
	}
	public void setTodayStartImage(String todayStartImage) {
		this.todayStartImage = todayStartImage;
	}
	public String getTodayEndImage() {
		return todayEndImage;
	}
	public void setTodayEndImage(String todayEndImage) {
		this.todayEndImage = todayEndImage;
	}
	public String getTodayNowWeather() {
		return todayNowWeather;
	}
	public void setTodayNowWeather(String todayNowWeather) {
		this.todayNowWeather = todayNowWeather;
	}
	public String getTodayLiveingIndex() {
		return todayLiveingIndex;
	}
	public void setTodayLiveingIndex(String todayLiveingIndex) {
		this.todayLiveingIndex = todayLiveingIndex;
	}
	public String getTomorrowTemperature() {
		return tomorrowTemperature;
	}
	public void setTomorrowTemperature(String tomorrowTemperature) {
		this.tomorrowTemperature = tomorrowTemperature;
	}
	public String getTomorrowWindDirectionAndWindForce() {
		return tomorrowWindDirectionAndWindForce;
	}
	public void setTomorrowWindDirectionAndWindForce(
			String tomorrowWindDirectionAndWindForce) {
		this.tomorrowWindDirectionAndWindForce = tomorrowWindDirectionAndWindForce;
	}
	public String getTomorrowDescribe() {
		return tomorrowDescribe;
	}
	public void setTomorrowDescribe(String tomorrowDescribe) {
		this.tomorrowDescribe = tomorrowDescribe;
	}
	public String getTomorrowStartImage() {
		return tomorrowStartImage;
	}
	public void setTomorrowStartImage(String tomorrowStartImage) {
		this.tomorrowStartImage = tomorrowStartImage;
	}
	public String getTomorrowEndImage() {
		return tomorrowEndImage;
	}
	public void setTomorrowEndImage(String tomorrowEndImage) {
		this.tomorrowEndImage = tomorrowEndImage;
	}
	public String getTheDayAfterTomorrowTemperature() {
		return theDayAfterTomorrowTemperature;
	}
	public void setTheDayAfterTomorrowTemperature(
			String theDayAfterTomorrowTemperature) {
		this.theDayAfterTomorrowTemperature = theDayAfterTomorrowTemperature;
	}
	public String getTheDayAfterTomorrowWindDirectionAndWindForce() {
		return theDayAfterTomorrowWindDirectionAndWindForce;
	}
	public void setTheDayAfterTomorrowWindDirectionAndWindForce(
			String theDayAfterTomorrowWindDirectionAndWindForce) {
		this.theDayAfterTomorrowWindDirectionAndWindForce = theDayAfterTomorrowWindDirectionAndWindForce;
	}
	public String getTheDayAfterTomorrowDescribe() {
		return theDayAfterTomorrowDescribe;
	}
	public void setTheDayAfterTomorrowDescribe(String theDayAfterTomorrowDescribe) {
		this.theDayAfterTomorrowDescribe = theDayAfterTomorrowDescribe;
	}
	public String getTheDayAfterTomorrowStartImage() {
		return theDayAfterTomorrowStartImage;
	}
	public void setTheDayAfterTomorrowStartImage(
			String theDayAfterTomorrowStartImage) {
		this.theDayAfterTomorrowStartImage = theDayAfterTomorrowStartImage;
	}
	public String getTheDayAfterTomorrowEndImage() {
		return theDayAfterTomorrowEndImage;
	}
	public void setTheDayAfterTomorrowEndImage(String theDayAfterTomorrowEndImage) {
		this.theDayAfterTomorrowEndImage = theDayAfterTomorrowEndImage;
	}
	public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}	
	
}
