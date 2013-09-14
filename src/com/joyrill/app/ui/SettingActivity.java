package com.joyrill.app.ui;

import java.util.Locale;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.joyrill.app.JoyrillApplication;
import com.joyrill.app.R;
import com.joyrill.app.adapter.setting.LocalAdapter;
import com.joyrill.app.config.Constants;
import com.joyrill.app.controller.SettingController;
import com.joyrill.app.operation.ParameterOperation;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.util.SharedPreferencesHelper;

public class SettingActivity extends BaseActivity implements OnClickListener,
		Handler.Callback {

	private Looper looper = null;
	private Handler uiHandler = null;
	private GridLayout host = null, about = null;
	private LinearLayout language = null;
	private ListView settingList = null, languageList = null;
	private SharedPreferencesHelper helper = null;
	private SettingController controller = null;
	private String time, date;
	private EditText dateText, timeText, userId;
	private CheckBox savePassword, autoLogin;
	private Button settingSaveLanguage,settingEdit,databaseSync;
	private int currentChecked = -1;
	private LocalAdapter localAdapter = null;
	private SettingReceiver receiver = null;
	private IntentFilter intentFilter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.setting);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		controller = new SettingController();
		helper = new SharedPreferencesHelper(this);
		uiHandler.sendEmptyMessage(0);
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.joyrill.app.command");
		receiver = new SettingReceiver();
		this.registerReceiver(receiver, intentFilter);
		this.initComponent();
		this.initListener();
		String[] setting = this.getResources().getStringArray(
				R.array.setting_list);
		ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, setting);
		this.settingList.setAdapter(addressAdapter);
		String[] language = this.getResources().getStringArray(
				R.array.language_list);
    	String able= JoyrillApplication.getInstance().getResources().getConfiguration().locale.getCountry(); 
		String check = helper.getValue("language",able);
		localAdapter = new LocalAdapter(this, language, check);
		this.languageList.setAdapter(localAdapter);
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
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		this.unregisterReceiver(receiver);
		super.onDestroy();
	}

	private void initComponent() {
		settingList = (ListView) this.findViewById(R.id.settingList);
		languageList = (ListView) this.findViewById(R.id.languageList);
		host = (GridLayout) this.findViewById(R.id.host);
		about = (GridLayout) this.findViewById(R.id.about);
		language = (LinearLayout) this.findViewById(R.id.language);
		dateText = (EditText) this.findViewById(R.id.dateText);
		timeText = (EditText) this.findViewById(R.id.timeText);
		savePassword = (CheckBox) this.findViewById(R.id.settingSavePassword);
		autoLogin = (CheckBox) this.findViewById(R.id.settingAutoLogin);
		userId = (EditText) this.findViewById(R.id.userId);
		settingSaveLanguage = (Button) this
				.findViewById(R.id.settingSaveLanguage);
		settingEdit = (Button)this.findViewById(R.id.setting_edit);
		databaseSync=(Button)this.findViewById(R.id.databaseSync);
		
	}

	private void initListener() {
		settingEdit.setOnClickListener(this);
		databaseSync.setOnClickListener(this);
		settingSaveLanguage.setOnClickListener(this);
		settingList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int id,
					long arg3) {
				change(id);
			}
		});
		languageList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0: {
					currentChecked = 0;
					localAdapter.setSelectItem(0);
					break;
				}
				case 1: {
					currentChecked = 1;
					localAdapter.setSelectItem(1);
					break;
				}
				default:
					break;
				}
			}
		});
		savePassword
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if(isChecked){
							ParameterOperation.savePassword(isChecked);
						}else{
							autoLogin.setChecked(isChecked);
							ParameterOperation.autoLogin(isChecked);
							ParameterOperation.savePassword(isChecked);
						}
					}
				});
		autoLogin
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							savePassword.setChecked(isChecked);
							ParameterOperation.savePassword(isChecked);
							ParameterOperation.autoLogin(isChecked);
						} else {
							ParameterOperation.autoLogin(isChecked);
						}
					}
				});
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0: {
			controller.getTime(1);
			boolean bool = ParameterOperation.findIsAutoLogin();
			autoLogin.setChecked(bool);
			String id = ParameterOperation.findParaValueByParaName("UserID");
			userId.setText(id);
			break;
		}
		case 1: {
			Bundle bundle = msg.getData();
			String returnStr = bundle.getString(Constants.RETURN);
			if (null != returnStr) {
				String temp = returnStr.replace("*JOYRILL*", "").replace("#",
						"");
				String[] s = temp.split(":");
				date = s[0];
				time = s[1];
			} else {
				date = "Error";
				time = "Error";
			}
			dateText.setText(date);
			timeText.setText(time);
			break;
		}
		case 2:{
			break;
		}
		default: {
			break;
		}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.settingSaveLanguage: {
			Configuration config = JoyrillApplication.getInstance()
					.getResources().getConfiguration();
			DisplayMetrics dm = JoyrillApplication.getInstance().getResources()
					.getDisplayMetrics();
			if (currentChecked == 0) {
				config.locale = Locale.ENGLISH;
				helper.putValue("language", "en");
			} else if (currentChecked == 1) {
				config.locale = Locale.SIMPLIFIED_CHINESE;
				helper.putValue("language", "CN");
			} else {

			}
			JoyrillApplication.getInstance().getResources()
					.updateConfiguration(config, dm);
			JoyrillActivity.getInstance().getResources()
					.updateConfiguration(config, dm);
			JoyrillActivity.getInstance().recreate();
			break;
		}
		case R.id.setting_edit:{
			String date = dateText.getText().toString();
			String time = timeText.getText().toString();
			controller.saveDateTime(2, date+":"+time);
			break;
		}
		case R.id.databaseSync:{
			controller.getDataBaseVersion();
			break;
		}
		default:
			break;
		}
	}

	private void change(int id) {
		switch (id) {
		case 0: {
			host.setVisibility(View.VISIBLE);
			language.setVisibility(View.GONE);
			about.setVisibility(View.GONE);
			break;
		}
		case 1: {
			host.setVisibility(View.GONE);
			language.setVisibility(View.VISIBLE);
			about.setVisibility(View.GONE);
			break;
		}
		case 2: {
			host.setVisibility(View.GONE);
			language.setVisibility(View.GONE);
			about.setVisibility(View.VISIBLE);
			break;
		}
		}
	}
	
	class SettingReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			
			Bundle bundle = intent.getExtras();
			String message = bundle.getString("message");
			setDateTime(message);
		}
		private void setDateTime(String message){
			boolean b = Pattern.matches("\\*JOYRILL\\*[\\s\\S]*\\:[\\s\\S]*", message);
			if (b) {
				String temp = message.replace("*JOYRILL*", "").replace("#",
						"");
				String[] s = temp.split(":");
				date = s[0];
				time = s[1];
			} else {
				date = "Error";
				time = "Error";
			}
			dateText.setText(date);
			timeText.setText(time);
		}
		
	}
}
