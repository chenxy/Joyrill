package com.joyrill.app.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.joyrill.app.R;
import com.joyrill.app.cache.Cache;
import com.joyrill.app.config.Constants;
import com.joyrill.app.controller.LoginController;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Url;
import com.joyrill.app.model.User;
import com.joyrill.app.operation.ParameterOperation;
import com.joyrill.app.service.MessageService;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.util.NetState;
import com.joyrill.app.util.ReceiveMsg;
import com.joyrill.app.util.SocketClient;

public class LaunchActivity extends BaseActivity implements Handler.Callback {
	private Looper looper = null;
	private Handler uiHandler = null;
	private NetState netState = null;
	private Cache cache = Cache.getInstance();
	private LoginController loginController;
	private LaunchReceiver receiver = null;
	IntentFilter intentFilter;
	Intent intent = new Intent();
	private boolean isRegister = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		loginController = new LoginController();
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.joyrill.app.command");
		receiver = new LaunchReceiver();
		netState = new NetState(this);
		View view = View.inflate(this, R.layout.launch, null);
		setContentView(view);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
				try {
					if (netState.isNetworkConnected()) {

						HttpClient httpclient = new DefaultHttpClient();
						// 创建Get方法实例
						HttpGet httpgets = new HttpGet(
								"http://42.121.118.102/api/?action=getip&imei="
										+ ParameterOperation.findImei());
						HttpResponse response = httpclient.execute(httpgets);
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							InputStream instreams = entity.getContent();
							String str = convertStreamToString(instreams).replace("\n", "");
							Logger.d("getIp:"+str);
							if (null != str) {
								String s[] = str.split(";");
								Url intranet = new Url(s[2], s[4]);
								Url internet = new Url(s[3], s[4]);
								ParameterOperation.updateIntranetUrl(intranet);
								ParameterOperation.updateInternetUrl(internet);
								httpgets.abort();
							}
						}
					}
				} catch (Exception e) {

				}
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
			}
		});
		uiHandler.sendEmptyMessage(1);
	}

	@Override
	protected void onStart() {
		this.registerReceiver(receiver, intentFilter);
		isRegister = true;
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 1: {
			if (netState.isNetworkConnected()) {
				if (ParameterOperation.findIsAutoLogin()) {
					if (ParameterOperation.findDefaultLoginType().equals("1")) {
						Url url1 = ParameterOperation.findIntranetUrl();
						// Url url1 = new Url("119.130.142.64", "8001");
						cache.putValue(Cache.URL_CACHE, url1);
					} else {
						Url url2 = ParameterOperation.findInternetUrl();
						// Url url2 = new Url("119.130.142.64", "8001");
						cache.putValue(Cache.URL_CACHE, url2);
					}
					//SocketClient.getInstance();
					this.startService(new Intent(this, MessageService.class));
					loginController.login((User) cache
							.getValue(Cache.USER_CACHE));
				} else {
					this.startActivity(LoginActivity.class);
					unregisterReceiver();
					isRegister = false;
				}
			} else {
				this.startActivity(LoginActivity.class);
				unregisterReceiver();
				isRegister = false;
			}
			break;
		}
		default: {
			break;
		}
		}
		return true;
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();
		super.onDestroy();
	}
	
	private void unregisterReceiver(){
		if(isRegister){
			this.unregisterReceiver(receiver);
		}
	}

	public String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	class LaunchReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Logger.d("launch receive broadcast");
			String s = bundle.getString("message");
			//if (s.equals("JOYRILL*SOCKETERROR#")) {
			//	LaunchActivity.this.startActivity(LoginActivity.class);
			/*} else */if (s.equals(Constants.STATUS.LOGIN_SUCCESS)) {
				LaunchActivity.this.startActivity(JoyrillActivity.class);
			} else if (s.equals(Constants.STATUS.ERROR_NO_USER)) {
				LaunchActivity.this.startActivity(LoginActivity.class);
			} else if (s.equals(Constants.STATUS.ERROR_PASS_ERROR)) {
				LaunchActivity.this.startActivity(LoginActivity.class);
			}else{
				
			}
		}

	}

}
