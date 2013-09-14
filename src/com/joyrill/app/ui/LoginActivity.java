package com.joyrill.app.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.joyrill.app.util.ReceiveMsg;
import com.joyrill.app.util.UIHelper;

public class LoginActivity extends BaseActivity implements OnClickListener,
		Handler.Callback {

	private Button loginBtn, demoBtn, setUrlBtn;
	private ImageButton layoutType;
	private GridLayout loginLayout, settingLayout;
	private CheckBox savePassword, autoLogin;
	private EditText internetText, intranetText, portText, userName, password;
	private ProgressDialog loginDialog;
	private LoginController loginController = null;
	private Looper looper = null;
	private Handler uiHandler = null;
	private User user = null;
	private Url intranet, internet;
	private Spinner typeText = null;
	private ArrayAdapter<CharSequence> adapter=null;
	private Cache cache = Cache.getInstance();
	private LoginReceiver receiver = new LoginReceiver();
	private IntentFilter intentFilter;
	private boolean isRegister = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.login);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() 
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		loginController = new LoginController();
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.joyrill.app.command");
		this.registerReceiver(receiver, intentFilter);
		isRegister = true;
		this.initComponent();
		this.initListener();
		uiHandler.sendEmptyMessage(0);
	}

	private void initComponent() {
		layoutType = (ImageButton) this.findViewById(R.id.layoutType);
		loginLayout = (GridLayout) this.findViewById(R.id.loginLayout);
		settingLayout = (GridLayout) this.findViewById(R.id.settingLayout);
		setUrlBtn = (Button) this.findViewById(R.id.setUrlBtn);
		loginBtn = (Button) this.findViewById(R.id.loginBtn);
		demoBtn = (Button) this.findViewById(R.id.demoBtn);
		userName = (EditText) this.findViewById(R.id.userName);
		password = (EditText) this.findViewById(R.id.password);
		internetText = (EditText) this.findViewById(R.id.internetText);
		intranetText = (EditText) this.findViewById(R.id.interantText);
		portText = (EditText) this.findViewById(R.id.portText);
		savePassword = (CheckBox) this.findViewById(R.id.savepassword);
		autoLogin = (CheckBox) this.findViewById(R.id.autologin);
		loginDialog = this.buildLoginProgressDialog(this,
				getString(R.string.login_ui_login_btn));
		typeText = (Spinner)this.findViewById(R.id.typeText);
		
	}

	private void initListener() {
		this.layoutType.setOnClickListener(this);
		this.loginBtn.setOnClickListener(this);
		this.demoBtn.setOnClickListener(this);
		this.setUrlBtn.setOnClickListener(this);
		this.savePassword
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						if(ischecked){
							ParameterOperation.savePassword(ischecked);
						}else{
							autoLogin.setChecked(ischecked);
							ParameterOperation.autoLogin(ischecked);
							ParameterOperation.savePassword(ischecked);
						}
					}
				});
		this.autoLogin
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean ischecked) {
						if(ischecked){
							savePassword.setChecked(ischecked);
							ParameterOperation.autoLogin(ischecked);
							ParameterOperation.savePassword(ischecked);
						}else{
							ParameterOperation.autoLogin(ischecked);
						}
					}
				});
		typeText.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int id, long arg3) {
				if(0==id){
					ParameterOperation.saveLoginType("1");
					Url url1 = ParameterOperation.findIntranetUrl();
					cache.putValue(Cache.URL_CACHE, url1);
				}else{
					ParameterOperation.saveLoginType("2");
					Url url2 = ParameterOperation.findInternetUrl();
					cache.putValue(Cache.URL_CACHE, url2);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 4) {
			UIHelper.Exit(this);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private ProgressDialog buildLoginProgressDialog(Context context,
			String title) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle(title);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(true);
		return progressDialog;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layoutType: {
			changeLayoutType();
			intranet = ParameterOperation.findIntranetUrl();
			internet = ParameterOperation.findInternetUrl();
			internetText.setText(internet.getAddress());
			intranetText.setText(intranet.getAddress());
			portText.setText(internet.getPort());
			break;
		}
		case R.id.loginBtn: {
			ParameterOperation.saveLoginType("2");
			loginDialog.show();
			user = new User(userName.getText().toString(), password.getText()
					.toString());
			ParameterOperation.saveUserNameAndPassword(user);
			if(ParameterOperation.findDefaultLoginType().equals("1")){
				Url url1 = ParameterOperation.findIntranetUrl();
				cache.putValue(Cache.URL_CACHE, url1);
			}else{
				Url url2 = ParameterOperation.findInternetUrl();
				cache.putValue(Cache.URL_CACHE, url2);
			}
			this.startService(new Intent(this, MessageService.class));
			loginController.login(user);
			break;
		}
		case R.id.demoBtn: {
			break;
		}
		case R.id.setUrlBtn: {
			updateUrl();
			break;
		}
		default: {
			break;
		}
		}
	}

	private void updateUrl() {
		String internet = internetText.getText().toString();
		String intranet = intranetText.getText().toString();
		String port = portText.getText().toString();
		ParameterOperation.updateInternetUrl(new Url(internet, port));
		ParameterOperation.updateIntranetUrl(new Url(intranet, port));
		changeLayoutType();
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0: {
			if (ParameterOperation.findIsSavePassword()) {
				savePassword.setChecked(true);
				user = ParameterOperation.findDefaultUser();
				userName.setText(user.getUserName());
				password.setText(user.getPassword());
			}
			if (ParameterOperation.findIsAutoLogin()) {
				autoLogin.setChecked(true);
			}
			adapter = ArrayAdapter.createFromResource(this, R.array.net_list, android.R.layout.simple_spinner_item);  
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  	  
	        typeText.setAdapter(adapter);
	        if(ParameterOperation.findDefaultLoginType().equals("1")){
	        	typeText.setSelection(0);
	        }else{
	        	typeText.setSelection(1);
	        }
			break;
		}
		case 1: {
			Bundle bundle = msg.getData();
			String returnStr = bundle.getString(Constants.RETURN);
			if (returnStr != null && "*JOYRILL*ADMIN#".equals(returnStr)) {
				if (savePassword.isChecked()) {
					ParameterOperation.saveUserNameAndPassword(user);
				}

				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, JoyrillActivity.class);
				this.startActivity(intent);
				loginDialog.dismiss();
				this.finish();
			} else {
				loginDialog.dismiss();
				Toast.makeText(this, R.string.nouser, Toast.LENGTH_LONG).show();
			}
		}
		default:
			break;
		}
		return false;
	}

	private void changeLayoutType() {
		if (loginLayout.getVisibility() == LinearLayout.VISIBLE) {
			settingLayout.setVisibility(LinearLayout.VISIBLE);
			loginLayout.setVisibility(LinearLayout.GONE);
		} else {
			settingLayout.setVisibility(LinearLayout.GONE);
			loginLayout.setVisibility(LinearLayout.VISIBLE);
		}
	}
	
	
	@Override
	protected void onStop() {
		this.finish();
		super.onStop();
	}

	@Override
	protected void onStart() {
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
	protected void onDestroy() {
		loginDialog.dismiss();
		if(isRegister){
			this.unregisterReceiver(receiver);
			isRegister = false;
		}
		super.onDestroy();
	}
	
	class LoginReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Logger.d("login receive broadcast");
			String s = bundle.getString("message");
			if(s.equals(Constants.STATUS.SOCKET_ERROR)){
				Toast.makeText(LoginActivity.this, "链接服务器异常!", Toast.LENGTH_LONG).show();
				loginDialog.dismiss();
			}
			if (s.equals(Constants.STATUS.LOGIN_SUCCESS)) {
				LoginActivity.this.startActivity(JoyrillActivity.class);
				if(isRegister){
					LoginActivity.this.unregisterReceiver(receiver);
					isRegister =false;
				}
			} else {
			}
		}

	}

}