package com.joyrill.app.controller.base;

import com.joyrill.app.util.SendMsg;
import com.joyrill.app.util.ThreadPool;

public class BaseController {
	private ThreadPool pool = ThreadPool.getInstance();
	public void sentMsg(String cmd){
		SendMsg msg = new SendMsg(cmd);
		pool.addTask(msg);
	}
}
