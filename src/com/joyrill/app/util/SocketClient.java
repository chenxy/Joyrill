package com.joyrill.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.content.Intent;

import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.cache.Cache;
import com.joyrill.app.exception.JoyrillException;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Url;
import com.joyrill.app.receiver.base.BaseReceiver;

public class SocketClient {

	private static boolean isConnection = false;
	private InetAddress serverAddr = null;
	private Socket socket = null;
	private SocketAddress socketAddress = null;
	private static SocketClient socketClient = new SocketClient();
	private DeamonThread deamonThread = null;
	private Cache cache = Cache.getInstance();
	
	public static synchronized SocketClient getInstance() {
		return socketClient;
	}
	
	public synchronized void chean(){
		socketClient = null;
	} 

	private SocketClient() {
		deamonThread = new DeamonThread();
		new Thread(deamonThread).start();
		try {
			Logger.d("isConnection=" + isConnection);
			this.startConnection();
		} catch (JoyrillException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnection() {
		return isConnection;
	}

	private boolean startConnection() throws JoyrillException {
		Logger.d("start Connection");
		Url url = (Url) cache.getValue(Cache.URL_CACHE);
		Logger.d("start connection : " + url.toString());
		try {
			serverAddr = InetAddress.getByName(url.getAddress());
			socketAddress = new InetSocketAddress(serverAddr,
					Integer.parseInt(url.getPort()));
			socket = new Socket();
			socket.setSoTimeout(1000);
			socket.setKeepAlive(true);
			socket.connect(socketAddress, 2000);
			isConnection = true;
		} catch (Exception e) {
			isConnection = false;
			Intent intent = new Intent(BaseReceiver.action);
			Logger.d("send socket error");
			intent.putExtra("message", "*JOYRILL*SOCKETERROR#");
			JoyrillApplication.getInstance().sendBroadcast(intent);
			this.startConnection();
			e.printStackTrace();
			if (e instanceof JoyrillException)
				throw (JoyrillException) e;
			throw JoyrillException.socket(e);
		}
		return false;
	}

	public InputStream getInputStream() {
		try {
			if (null != socket && isConnection) {
				return socket.getInputStream();
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public OutputStream getOutputStream() {
		try {
			if (null != socket && isConnection) {
				return socket.getOutputStream();
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
