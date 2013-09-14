package com.joyrill.app.model;


public class Area {

	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(int parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getImgIco() {
		return imgIco;
	}
	public void setImgIco(String imgIco) {
		this.imgIco = imgIco;
	}
	
	private int areaId;
	private String areaName;
	private int parentAreaId;
	private int index;
	private String imgIco;
	
}
