package com.joyrill.app.util;
import java.io.DataInputStream;

import android.content.Intent;

import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.receiver.base.BaseReceiver;


public class ReceiveMsg extends Thread{
	
	private SocketClient socket;
	DataInputStream input;
	private boolean isTrue = true;
	byte[] length;
	public ReceiveMsg(){
		socket = SocketClient.getInstance();
		length = new byte[1024];
	}
	public ReceiveMsg(SocketClient socket){
		this.socket = socket;
		length = new byte[1024];
	}
	@Override
	public void run() {
		Logger.d("start receiver message");
		while (isTrue) {
			String callBackStr = null;
			try {
					Thread.sleep(1000);
					input = new DataInputStream(socket.getInputStream());
	                byte[] sb = new byte[1024];
	                int backleng = input.read(sb);
	                if (backleng > 0) {
	                	callBackStr = new String(sb, 0, backleng);
	                }
			} catch (Exception e) {
				//e.printStackTrace();
			}
			if(callBackStr != null){
				Logger.d("receiver message : "+callBackStr);
				String[] s = callBackStr.split("#");
				for(int i=0 ; i< s.length ; i++){
					Intent intent = new Intent(BaseReceiver.action); 
					intent.putExtra("message",callBackStr); 
					JoyrillApplication.getInstance().sendBroadcast(intent);
				}
			}
		}
	}
}
