package com.joyrill.app.model;


public class Scene {

	private int sceneId;
	private String sceneName;
	private String sceneDisplayName;
	private String sceneDelay;
	private int isService;
	private int enable;
	private int index;
	private String imgIco;
	public int getSceneId() {
		return sceneId;
	}
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public String getSceneDisplayName() {
		return sceneDisplayName;
	}
	public void setSceneDisplayName(String sceneDisplayName) {
		this.sceneDisplayName = sceneDisplayName;
	}
	public String getSceneDelay() {
		return sceneDelay;
	}
	public void setSceneDelay(String sceneDelay) {
		this.sceneDelay = sceneDelay;
	}

	public int getIsService() {
		return isService;
	}
	public void setIsService(int isService) {
		this.isService = isService;
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
