package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;

public class SettingController extends BaseController {

	public SettingController() {
	}

	public void getTime(int commandId) {
		String cmd = "*JOYRILL*CONFIG*TIME*GET**CRC#";
		super.sentMsg(cmd);
	}

	public void saveDateTime(int commandId, String dateTime) {
		String cmd = "*JOYRILL*CONFIG*TIME*SET*" + dateTime + "*CRC#";
		super.sentMsg(cmd);
	}
	
	public void getDataBaseVersion(){
		String cmd = "*JOYRILL*DATABASE*CRC#";
		super.sentMsg(cmd);
	}
}
