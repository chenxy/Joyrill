package com.joyrill.app.util;

public class MonitorList {
	public  String deviceName;
	public  String devicePass;
	public  String deviceId;
	
	public MonitorList(String deviceName, String devicePass, String deviceId) {
		super();
		this.deviceName = deviceName;
		this.devicePass = devicePass;
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDevicePass() {
		return devicePass;
	}
	public void setDevicePass(String devicePass) {
		this.devicePass = devicePass;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
