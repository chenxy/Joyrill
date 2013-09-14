package com.joyrill.app.model;

public class Timer {

	private int Id;
	private String name;
	private int type;
	private int functionId;
	private int execStyle;
	private String execDate;
	private String execTime;
	private int state;
	private int enable;
	private int index;
	private String imgIco;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public int getExecStyle() {
		return execStyle;
	}

	public void setExecStyle(int execStyle) {
		this.execStyle = execStyle;
	}

	public String getExecDate() {
		return execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getExecTime() {
		return execTime;
	}

	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
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

}
