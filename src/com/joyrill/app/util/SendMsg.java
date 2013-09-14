package com.joyrill.app.util;
import java.io.PrintWriter;

import com.joyrill.app.logger.Logger;

public class SendMsg extends Task {
	SocketClient socket = null;
	private String str;

	public SendMsg(String str){
		socket = SocketClient.getInstance();
		this.str = str;
	}
	
	public SendMsg(SocketClient socket, String str) {
		this.socket = socket;
		this.str = str;
	}

	@Override
	public void run() {
		try {
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println(str);
			Logger.d("send message : "+str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Task[] taskCore() throws Exception {
		return null;
	}

}
