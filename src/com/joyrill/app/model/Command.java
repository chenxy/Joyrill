package com.joyrill.app.model;

public class Command {

	private int commandId;
	private int deviceId;
	private String commandName;
	private String commandDesplayName;
	private String commandValue;
	private int enable;
	private int index;
	private int commandTypeId;
	private String imgIco;
	private int displayType;

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getCommandDesplayName() {
		return commandDesplayName;
	}

	public void setCommandDesplayName(String commandDesplayName) {
		this.commandDesplayName = commandDesplayName;
	}

	public String getCommandValue() {
		return commandValue;
	}

	public void setCommandValue(String commandValue) {
		this.commandValue = commandValue;
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

	public int getCommandTypeId() {
		return commandTypeId;
	}

	public void setCommandTypeId(int commandTypeId) {
		this.commandTypeId = commandTypeId;
	}

	public String getImgIco() {
		return imgIco;
	}

	public void setImgIco(String imgIco) {
		this.imgIco = imgIco;
	}

	public int getDisplayType() {
		return displayType;
	}

	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}

}
