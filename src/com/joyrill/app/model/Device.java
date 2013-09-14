package com.joyrill.app.model;

public class Device {

	private int deviceId;
	private int areaId;
	private String deviceName;
	private int deviceTypeId;
	private String deviceDes;
	private int index;
	private int Enabled;
	private String imgIco;
	private String state;

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceDes() {
		return deviceDes;
	}

	public void setDeviceDes(String deviceDes) {
		this.deviceDes = deviceDes;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getEnabled() {
		return Enabled;
	}

	public void setEnabled(int enabled) {
		Enabled = enabled;
	}

	public String getImgIco() {
		return imgIco;
	}

	public void setImgIco(String imgIco) {
		this.imgIco = imgIco;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
