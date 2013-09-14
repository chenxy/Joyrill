package com.joyrill.app.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.joyrill.app.cache.Cache;
import com.joyrill.app.exception.JoyrillException;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Url;

public class CheckSocket {
	public boolean isConnection = false;
	private InetAddress serverAddr = null;
	private Socket socket = null;
	private SocketAddress socketAddress = null;
	private Cache cache = Cache.getInstance();
	public CheckSocket() {
	}

	public  boolean startConnection() throws JoyrillException {
		try {
			Url url = (Url) cache.getValue(Cache.URL_CACHE);
			Logger.d("start connection : " + url.toString());
			serverAddr = InetAddress.getByName(url.getAddress());
			socketAddress = new InetSocketAddress(serverAddr,
					Integer.parseInt(url.getPort()));
			socket = new Socket();
			socket.setSoTimeout(1000);
			socket.connect(socketAddress, 3000);
			isConnection = true;
		} catch (Exception e) {
			isConnection = false;
			e.printStackTrace();
			if (e instanceof JoyrillException)
				throw (JoyrillException) e;
			throw JoyrillException.socket(e);
		}
		return false;
	}
}
