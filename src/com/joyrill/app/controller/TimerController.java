package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;
import com.joyrill.app.model.Timer;

public class TimerController extends BaseController {

	public TimerController() {
	}

	public void openTimer(int command, Timer timer) {
		String cmd = "*JOYRILL*TIMER*" + timer.getId() + "*255*CRC#";
		super.sentMsg(cmd);
	}

	public void closeTimer(int command, Timer timer) {
		String cmd = "*JOYRILL*TIMER*" + timer.getId() + "*0*CRC#";
		super.sentMsg(cmd);
	}
}
