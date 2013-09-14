package com.joyrill.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.joyrill.app.model.Url;

public class ServerState {
	public boolean isConnect(Url url) {
		boolean isSuccess = false;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("ping " + url.getAddress());
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();

			if (null != sb && !sb.toString().equals("")) {
				if (sb.toString().indexOf("TTL") > 0) {
					isSuccess = true;
				} else {
					isSuccess = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}
}
