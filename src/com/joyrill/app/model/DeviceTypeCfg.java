package com.joyrill.app.model;

public class DeviceTypeCfg {

	private int deviceTypeId;
	private int parentId;
	private String deviecTypeName;
	private String deviceTypeDisplayName;
	private String deviceTypeDesc;
	private int index;
	private int enable;
	private String imgIco;

	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getDeviecTypeName() {
		return deviecTypeName;
	}

	public void setDeviecTypeName(String deviecTypeName) {
		this.deviecTypeName = deviecTypeName;
	}

	public String getDeviceTypeDisplayName() {
		return deviceTypeDisplayName;
	}

	public void setDeviceTypeDisplayName(String deviceTypeDisplayName) {
		this.deviceTypeDisplayName = deviceTypeDisplayName;
	}

	public String getDeviceTypeDesc() {
		return deviceTypeDesc;
	}

	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getImgIco() {
		return imgIco;
	}

	public void setImgIco(String imgIco) {
		this.imgIco = imgIco;
	}

}
