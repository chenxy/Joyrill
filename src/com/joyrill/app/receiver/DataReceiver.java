package com.joyrill.app.receiver;

import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.config.Constants;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Url;
import com.joyrill.app.model.User;
import com.joyrill.app.operation.DeviceOperation;
import com.joyrill.app.operation.ParameterOperation;
import com.joyrill.app.util.FileHelper;

public class DataReceiver extends BroadcastReceiver {

	public DataReceiver() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String s = bundle.getString("message");
		String a[] = s.split("#");
		for (int i = 0; i < a.length; i++) {
			boolean database = Pattern.matches(
					"\\*JOYRILL\\*DATABASE\\*[\\s\\S]*", a[i]);
			
			boolean stateList = Pattern.matches(
					"\\*JOYRILL\\*STATE\\*[\\s\\S]*\\:[\\s\\S]*", a[i]);
			boolean state = Pattern.matches(
					"\\*STATE\\*[\\s\\S]*\\:[\\s\\S]*", a[i]);
			if (database) {
				downloadDatabase(a[i]);
			}
			if (state || stateList) {
				updateState(a[i]);
			}
		}

	}

	public void updateState(String str) {
		try {
			String subData = str.substring(str.lastIndexOf("*") + 1,
					str.length());
			String[] splitData = subData.split(";");
			for (int j = 0; j < splitData.length; j++) {
				String[] ss = splitData[j].split(":");
				if (ss.length == 2) {
					DeviceOperation.updateDeviceByDeviceId(ss[0], ss[1]);
				} else {
					Logger.d("不处理数据：" + splitData[j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void downloadDatabase(String s) {
		String databaseVersion = ParameterOperation.findDataBaseVersion();
		;
		Url ftpIntranet = ParameterOperation.findFtpIntranetUrl();
		Url ftpInternet = ParameterOperation.findFtpInternetUrl();
		FileHelper helper = new FileHelper(JoyrillApplication.getInstance());
		User user = ParameterOperation.findFtpDefaultUser();
		if (s != null) {
			String p = s.replace("*JOYRILL*DATABASE*", "").replace("#", "");
			if (!databaseVersion.equals(p)) {
				if (!helper.downFile(
						ftpIntranet.getAddress(),
						Integer.parseInt(ftpIntranet.getPort()),
						user.getUserName(),
						user.getPassword(),
						"/home/joyrill",
						"zjdata",
						JoyrillApplication.getInstance()
								.getDatabasePath(Constants.DATABASE_NAME)
								.getParent())) {
					helper.downFile(
							ftpInternet.getAddress(),
							Integer.parseInt(ftpInternet.getPort()),
							user.getUserName(),
							user.getPassword(),
							"/home/joyrill",
							"zjdata",
							JoyrillApplication.getInstance()
									.getDatabasePath(Constants.DATABASE_NAME)
									.getParent());
				}
			}
		}
	}

}
