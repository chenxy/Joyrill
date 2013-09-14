package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;
import com.joyrill.app.model.User;

public class LoginController extends BaseController{

	public LoginController(){
	}
	public void login(int commandId , User user){
		String cmd = "*JOYRILL*ACCESS*" + user.getUserName()
				+ "*" + user.getPassword() + "**CHECK*CRC#";
		super.sentMsg(cmd);
	};
	
	public void connect(int commandId){
		String cmd = "*JOYRILL*CONNECT#";
		super.sentMsg(cmd);
	}
	public void login(User user){
		String cmd = "*JOYRILL*ACCESS*" + user.getUserName()
				+ "*" + user.getPassword() + "**CHECK*CRC#";
		super.sentMsg(cmd);
	};
	
	public void connect(){
		String cmd = "*JOYRILL*CONNECT#";
		super.sentMsg(cmd);
	}
}
