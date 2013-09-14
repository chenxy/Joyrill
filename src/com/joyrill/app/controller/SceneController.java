package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;
import com.joyrill.app.model.Scene;

public class SceneController extends BaseController{
	public SceneController(){
	}
	
	public void excuteScene(int command , Scene scene){
		String cmd = "*JOYRILL*SCENE*"+scene.getSceneId()+"**CRC#";
		super.sentMsg(cmd);
	}
	
}
