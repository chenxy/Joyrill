package com.joyrill.app.ui;

import java.util.List;
import java.util.regex.Pattern;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.controller.DeviceController;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Device;
import com.joyrill.app.model.DeviceTypeCfg;
import com.joyrill.app.operation.DeviceOperation;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.widget.MyImageButton;
import com.joyrill.app.widget.device.BaseAdjust;
import com.joyrill.app.widget.device.BaseTwoWay;
import com.joyrill.app.widget.device.DeviceTypeCurtainGupCurtainInsideOneWay;
import com.joyrill.app.widget.device.DeviceTypeCurtainGupCurtainOutsideOneWay;
import com.joyrill.app.widget.device.DeviceTypeElectricalGupAir;
import com.joyrill.app.widget.device.DeviceTypeElectricalGupDVD;
import com.joyrill.app.widget.device.DeviceTypeElectricalGupTV;
import com.joyrill.app.widget.device.DeviceTypeLightGupLightOneWay;
import com.joyrill.app.widget.device.DeviceTypeOtherGupManipulator;
import com.joyrill.app.widget.device.DeviceTypeOtherGupRollerGas;
import com.joyrill.app.widget.device.DeviceTypeOtherGupRollerShutter;
import com.joyrill.app.widget.device.DeviceTypeOtherGupWatering;
import com.joyrill.app.widget.device.DeviceTypeSocketGupSocketOneWay;

public class TypeActivity extends BaseActivity implements OnClickListener,
		Handler.Callback {

	private Looper looper = null;
	private Handler uiHandler = null;
	private List<Device> devices = null;
	private DeviceController areaController = null;
	private LinearLayout deviceViewLeft, deviceViewRight, typeView;
	private Button airLeftAndRightWind, airUpAndDownWind, airBrute, airSleep,
			airAuto, airOpen, airClose;
	private TextView airTemperature;
	private SeekBar airTemperatureSeekbar;
	private ImageButton airModel, airCold, airHot, airWind;
	private Button tvMenu, tvBack, tvStandBy, tvSignal, tvOk, tvOpen, tvClose;
	private ImageButton tvVolumePlus, tvVolumeMinus, tvChannelPlus,
			tvChannelMiuns;
	private Device device = null;
	private SparseArray<BaseTwoWay> component;
	private SparseArray<BaseAdjust> baseAdjust;
	private TypeReceiver receiver = null;
	private IntentFilter intentFilter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		areaController = new DeviceController();
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.joyrill.app.command");
		receiver = new TypeReceiver();
		component = new SparseArray<BaseTwoWay>();
		baseAdjust = new SparseArray<BaseAdjust>();
		this.registerReceiver(receiver, intentFilter);
		this.initComponent();
		this.initListener();
		uiHandler.sendEmptyMessage(0);
	}

	private void initTypeData(int parentId) {
		this.setContentView(R.layout.type);
		typeView = (LinearLayout) this.findViewById(R.id.typeLayout);
		List<DeviceTypeCfg> devicetypes = DeviceOperation
				.findDeviceTypeByParentId(parentId);
		for (DeviceTypeCfg devicetype : devicetypes) {
			MyImageButton button = new MyImageButton(this);
			button.setBackgroundResource(devicetype.getImgIco());
			button.setText(devicetype.getDeviceTypeDisplayName());
			button.setOnClickListener(uiHandler, devicetype.getDeviceTypeId());
			/*
			 * TextView textView = new TextView(this);
			 * textView.setText(devicetype.getDeviceTypeDisplayName()); Drawable
			 * drawable = ImageScale.getImageFromAssetsFile(devicetype
			 * .getImgIco()); drawable.setBounds(0, 0, 80, 60);
			 * textView.setGravity(Gravity.CENTER); textView.setPadding(10, 0,
			 * 10, 0); textView.setCompoundDrawables(null, drawable, null,
			 * null); textView.setId(devicetype.getDeviceTypeId());
			 * textView.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View v) { Message message =
			 * uiHandler.obtainMessage(); message.arg1 = v.getId(); message.what
			 * = 1; uiHandler.sendMessage(message); } });
			 */
			typeView.addView(button);
		}
	}

	private void initComponent() {
	}

	private void initListener() {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.air_open: {
			areaController.airOpen(device);
			break;
		}
		case R.id.air_close: {
			areaController.airClose(device);
			break;
		}
		case R.id.air_upanddownwind: {
			areaController.airUpAndDownWind(device);
			break;
		}
		case R.id.air_leftandrightwind: {
			areaController.airLeftAndRightWind(device);
			break;
		}
		case R.id.air_brute: {
			areaController.airBrute(device);
			break;
		}
		case R.id.air_sleep: {
			areaController.airSleep(device);
			break;
		}
		case R.id.air_cold: {
			areaController.airCold(device);
			break;
		}
		case R.id.air_hot: {
			areaController.airHot(device);
			break;
		}
		case R.id.air_auto: {
			areaController.airAuto(device);
			break;
		}
		case R.id.tv_open: {
			areaController.tvOpen(device);
			break;
		}
		case R.id.tv_close: {
			areaController.tvClose(device);
			break;
		}
		case R.id.tv_channnel_plus: {
			areaController.tvChannelPlus(device);
			break;
		}
		case R.id.tv_channel_minus: {
			areaController.tvChannelMinus(device);
			break;
		}
		case R.id.tv_volume_plus: {
			areaController.tvVolumePlus(device);
			break;
		}
		case R.id.tv_volume_minus: {
			areaController.tvVolumeMinus(device);
			break;
		}
		case R.id.tv_menu: {
			areaController.tvMenu(device);
			break;
		}
		case R.id.tv_mute: {
			areaController.tvMute(device);
			break;
		}
		case R.id.tv_ok: {
			areaController.tvOk(device);
			break;
		}
		case R.id.tv_signal: {
			areaController.tvSignal(device);
			break;
		}
		case R.id.tv_standby: {
			areaController.tvStandBy(device);
			break;
		}
		case R.id.tv_back: {
			areaController.tvBack(device);
			break;
		}
		default: {
			break;
		}
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0: {
			this.initTypeData(1);
			break;
		}
		case 1: {
			addDevices(msg);
			break;
		}
		case 2: {
			break;
		}
		case 3: {
			break;
		}
		case 41: {
			device = (Device) msg.obj;
			setTV();
			break;
		}
		case 42: {
			device = (Device) msg.obj;
			setAir();
			break;
		}
		case 43: {
			device = (Device) msg.obj;
			setDVD();
			break;
		}
		}
		return false;
	}

	private void setTV() {
		this.setContentView(R.layout.devicetype_electricalgup_tv_more);
		initTVComponent();
		initTVListener();
	};

	private void setAir() {
		this.setContentView(R.layout.devicetype_electricalgup_air_more);
		initAirComponent();
		initAirListener();
		airTemperature.setText(airTemperatureSeekbar.getProgress() * 100
				/ airTemperatureSeekbar.getMax() + "%");
	}

	private void setDVD() {
		this.setContentView(R.layout.devicetype_electricalgup_dvd_more);
	}

	private void initAirComponent() {
		airLeftAndRightWind = (Button) this
				.findViewById(R.id.air_leftandrightwind);
		airUpAndDownWind = (Button) this.findViewById(R.id.air_upanddownwind);
		airOpen = (Button) this.findViewById(R.id.air_open);
		airClose = (Button) this.findViewById(R.id.air_close);
		airBrute = (Button) this.findViewById(R.id.air_brute);
		airSleep = (Button) this.findViewById(R.id.air_sleep);
		airAuto = (Button) this.findViewById(R.id.air_auto);
		airTemperature = (TextView) this.findViewById(R.id.air_temperature);
		airTemperatureSeekbar = (SeekBar) this
				.findViewById(R.id.air_temperature_seekbar);
		airModel = (ImageButton) this.findViewById(R.id.air_model);
		airCold = (ImageButton) this.findViewById(R.id.air_cold);
		airHot = (ImageButton) this.findViewById(R.id.air_hot);
		airWind = (ImageButton) this.findViewById(R.id.air_wind);
	}

	private void initAirListener() {
		airLeftAndRightWind.setOnClickListener(this);
		airUpAndDownWind.setOnClickListener(this);
		airOpen.setOnClickListener(this);
		airClose.setOnClickListener(this);
		airBrute.setOnClickListener(this);
		airSleep.setOnClickListener(this);
		airAuto.setOnClickListener(this);
		airModel.setOnClickListener(this);
		airCold.setOnClickListener(this);
		airHot.setOnClickListener(this);
		airWind.setOnClickListener(this);
		airTemperatureSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						airTemperature.setText(airTemperatureSeekbar
								.getProgress()
								* 100
								/ airTemperatureSeekbar.getMax() + "%");
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
					}
				});
	}

	private void initTVComponent() {
		tvOk = (Button) this.findViewById(R.id.tv_ok);
		tvBack = (Button) this.findViewById(R.id.tv_back);
		tvClose = (Button) this.findViewById(R.id.tv_close);
		tvMenu = (Button) this.findViewById(R.id.tv_menu);
		tvOpen = (Button) this.findViewById(R.id.tv_open);
		tvSignal = (Button) this.findViewById(R.id.tv_signal);
		tvStandBy = (Button) this.findViewById(R.id.tv_standby);
		tvChannelPlus = (ImageButton) this.findViewById(R.id.tv_channnel_plus);
		tvChannelMiuns = (ImageButton) this.findViewById(R.id.tv_channel_minus);
		tvVolumePlus = (ImageButton) this.findViewById(R.id.tv_volume_plus);
		tvVolumeMinus = (ImageButton) this.findViewById(R.id.tv_volume_minus);
	}

	private void initTVListener() {
		tvOk.setOnClickListener(this);
		tvBack.setOnClickListener(this);
		tvClose.setOnClickListener(this);
		tvMenu.setOnClickListener(this);
		tvOpen.setOnClickListener(this);
		tvSignal.setOnClickListener(this);
		tvStandBy.setOnClickListener(this);
		tvChannelPlus.setOnClickListener(this);
		tvChannelMiuns.setOnClickListener(this);
		tvVolumePlus.setOnClickListener(this);
		tvVolumeMinus.setOnClickListener(this);
	}

	private void addDevices(Message msg) {
		this.setContentView(R.layout.device);
		deviceViewLeft = (LinearLayout) this.findViewById(R.id.deviceViewLeft);
		deviceViewRight = (LinearLayout) this
				.findViewById(R.id.deviceViewRight);
		int deviceId = msg.arg1;
		devices = DeviceOperation.findDeviceByDeviceTypeCfgId(deviceId);
		areaController.findDeviceStateByTypeId(deviceId);
		int i = 1;
		for (; i <= devices.size(); i++) {
			Device device = devices.get(i - 1);
			addDeviceItem(device, i);
		}
	}

	private void addDeviceItem(Device device, int i) {
		switch (device.getDeviceTypeId()) {
		case 10: {
			DeviceTypeLightGupLightOneWay deviceTypeOne = new DeviceTypeLightGupLightOneWay(
					this);
			deviceTypeOne.setText(device.getDeviceName());
			deviceTypeOne.setBackgroundResource(device.getImgIco());
			deviceTypeOne.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(deviceTypeOne);
			} else {
				deviceViewRight.addView(deviceTypeOne);
			}
			break;
		}
		case 11: {
			// DeviceTypeLightGupLightTwoWay lightTwoWay = new
			// DeviceTypeLightGupLightTwoWay(
			// this);
			// lightTwoWay.setText(device.getDeviceName());
			// lightTwoWay.setBackgroundResource(device.getImgIco());
			// lightTwoWay.setStatusText(device);
			BaseTwoWay twoWay = new BaseTwoWay(this);
			twoWay.setText(device.getDeviceName());
			twoWay.setBackgroundResource(device.getImgIco());
			twoWay.setOnclickListener(device, areaController);
			twoWay.setStatusText(device);
			component.put(device.getDeviceId(), twoWay);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(twoWay);
			} else {
				deviceViewRight.addView(twoWay);
			}
			break;
		}
		case 13: {
			/*
			 * DeviceTypeDimmerGupDimmerOneWay dimmerOneWay = new
			 * DeviceTypeDimmerGupDimmerOneWay( this);
			 * dimmerOneWay.setText(device.getDeviceName());
			 * dimmerOneWay.setBackgroundResource(device.getImgIco());
			 * dimmerOneWay.setOnClickListener(device, areaController);
			 */
			BaseAdjust dimmerOneWay = new BaseAdjust(this);
			dimmerOneWay.setText(device.getDeviceName());
			dimmerOneWay.setBackgroundResource(device.getImgIco());
			dimmerOneWay.setStatus(device.getState());
			dimmerOneWay.setOnClickListener(device, areaController);
			baseAdjust.put(device.getDeviceId(), dimmerOneWay);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(dimmerOneWay);
			} else {
				deviceViewRight.addView(dimmerOneWay);
			}
			break;
		}
		case 14: {
			/*
			 * DeviceTypeDimmerGupDimmerTwoWay dimmerTwoWay = new
			 * DeviceTypeDimmerGupDimmerTwoWay( this);
			 * dimmerTwoWay.setText(device.getDeviceName());
			 * dimmerTwoWay.setBackgroundResource(device.getImgIco());
			 * dimmerTwoWay.setOnClickListener(device, areaController);
			 */
			BaseAdjust dimmerTwoWay = new BaseAdjust(this);
			dimmerTwoWay.setText(device.getDeviceName());
			dimmerTwoWay.setBackgroundResource(device.getImgIco());
			dimmerTwoWay.setStatus(device.getState());
			dimmerTwoWay.setOnClickListener(device, areaController);
			baseAdjust.put(device.getDeviceId(), dimmerTwoWay);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(dimmerTwoWay);
			} else {
				deviceViewRight.addView(dimmerTwoWay);
			}
			break;
		}
		case 15: {
			/*
			 * DeviceTypeDimmerGupDimmerColorTwoWay dimmerColorTwoWay = new
			 * DeviceTypeDimmerGupDimmerColorTwoWay( this);
			 * dimmerColorTwoWay.setText(device.getDeviceName());
			 * dimmerColorTwoWay.setBackgroundResource(device.getImgIco());
			 * dimmerColorTwoWay.setOnClickListener(device, areaController);
			 */
			BaseAdjust dimmerColorTwoWay = new BaseAdjust(this);
			dimmerColorTwoWay.setText(device.getDeviceName());
			dimmerColorTwoWay.setBackgroundResource(device.getImgIco());
			dimmerColorTwoWay.setStatus(device.getState());
			dimmerColorTwoWay.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(dimmerColorTwoWay);
			} else {
				deviceViewRight.addView(dimmerColorTwoWay);
			}
			break;
		}
		case 21: {
			DeviceTypeSocketGupSocketOneWay socketOneWay = new DeviceTypeSocketGupSocketOneWay(
					this);
			socketOneWay.setText(device.getDeviceName());
			socketOneWay.setBackgroundResource(device.getImgIco());
			socketOneWay.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(socketOneWay);
			} else {
				deviceViewRight.addView(socketOneWay);
			}
			break;
		}
		case 22: {
			/*
			 * DeviceTypeSocketGupSocketTwoWay socketTwoWay = new
			 * DeviceTypeSocketGupSocketTwoWay( this);
			 * socketTwoWay.setText(device.getDeviceName());
			 * socketTwoWay.setBackgroundResource(device.getImgIco());
			 * socketTwoWay.setStatusText(device);
			 */
			BaseTwoWay twoWay = new BaseTwoWay(this);
			twoWay.setText(device.getDeviceName());
			twoWay.setBackgroundResource(device.getImgIco());
			twoWay.setOnclickListener(device, areaController);
			twoWay.setStatusText(device);
			component.put(device.getDeviceId(), twoWay);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(twoWay);
			} else {
				deviceViewRight.addView(twoWay);
			}
			break;
		}
		case 31: {
			/*
			 * DeviceTypeCurtainGupCurtainInsideTwoWay insideTwoWay = new
			 * DeviceTypeCurtainGupCurtainInsideTwoWay( this);
			 * insideTwoWay.setText(device.getDeviceName());
			 * insideTwoWay.setBackgroundResource(device.getImgIco());
			 * insideTwoWay.setStatusText(device);
			 */
			BaseTwoWay twoWay = new BaseTwoWay(this);
			twoWay.setText(device.getDeviceName());
			twoWay.setBackgroundResource(device.getImgIco());
			twoWay.setOnclickListener(device, areaController);
			twoWay.setStatusText(device);
			component.put(device.getDeviceId(), twoWay);
			Logger.d("put a device = " + device.getDeviceId());
			if ((i & 1) != 0) {
				deviceViewLeft.addView(twoWay);
			} else {
				deviceViewRight.addView(twoWay);
			}
			break;
		}
		case 32: {
			/*
			 * DeviceTypeCurtainGupCurtainOutsideTwoWay outsideTwoWay = new
			 * DeviceTypeCurtainGupCurtainOutsideTwoWay( this);
			 * outsideTwoWay.setText(device.getDeviceName());
			 * outsideTwoWay.setBackgroundResource(device.getImgIco());
			 * outsideTwoWay.setStatusText(device);
			 */
			BaseTwoWay twoWay = new BaseTwoWay(this);
			twoWay.setText(device.getDeviceName());
			twoWay.setBackgroundResource(device.getImgIco());
			twoWay.setOnclickListener(device, areaController);
			twoWay.setStatusText(device);
			component.put(device.getDeviceId(), twoWay);
			Logger.d("put a device = " + device.getDeviceId());
			if ((i & 1) != 0) {
				deviceViewLeft.addView(twoWay);
			} else {
				deviceViewRight.addView(twoWay);
			}
			break;
		}
		case 33: {
			DeviceTypeCurtainGupCurtainInsideOneWay insideOneWay = new DeviceTypeCurtainGupCurtainInsideOneWay(
					this);
			insideOneWay.setText(device.getDeviceName());
			insideOneWay.setBackgroundResource(device.getImgIco());
			insideOneWay.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(insideOneWay);
			} else {
				deviceViewRight.addView(insideOneWay);
			}
			break;
		}
		case 34: {
			DeviceTypeCurtainGupCurtainOutsideOneWay outsideOneWay = new DeviceTypeCurtainGupCurtainOutsideOneWay(
					this);
			outsideOneWay.setText(device.getDeviceName());
			outsideOneWay.setBackgroundResource(device.getImgIco());
			outsideOneWay.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(outsideOneWay);
			} else {
				deviceViewRight.addView(outsideOneWay);
			}
			break;
		}
		case 41: {
			DeviceTypeElectricalGupTV tv = new DeviceTypeElectricalGupTV(this);
			tv.setText(device.getDeviceName());
			tv.setBackgroundResource(device.getImgIco());
			tv.setOnClickListener(device, areaController, uiHandler);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(tv);
			} else {
				deviceViewRight.addView(tv);
			}
			break;
		}
		case 42: {
			DeviceTypeElectricalGupAir air = new DeviceTypeElectricalGupAir(
					this);
			air.setText(device.getDeviceName());
			air.setBackgroundResource(device.getImgIco());
			air.setOnClickListener(device, areaController, uiHandler);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(air);
			} else {
				deviceViewRight.addView(air);
			}
			break;
		}
		case 43: {
			DeviceTypeElectricalGupDVD dvd = new DeviceTypeElectricalGupDVD(
					this);
			dvd.setText(device.getDeviceName());
			dvd.setBackgroundResource(device.getImgIco());
			dvd.setOnClickListener(device, areaController, uiHandler);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(dvd);
			} else {
				deviceViewRight.addView(dvd);
			}
			break;
		}
		case 51: {
			DeviceTypeOtherGupManipulator manipulator = new DeviceTypeOtherGupManipulator(
					this);
			manipulator.setText(device.getDeviceName());
			manipulator.setBackgroundResource(device.getImgIco());
			manipulator.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(manipulator);
			} else {
				deviceViewRight.addView(manipulator);
			}
			break;
		}
		case 52: {
			DeviceTypeOtherGupRollerShutter rs = new DeviceTypeOtherGupRollerShutter(
					this);
			rs.setText(device.getDeviceName());
			rs.setBackgroundResource(device.getImgIco());
			rs.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(rs);
			} else {
				deviceViewRight.addView(rs);
			}
			break;
		}
		case 53: {
			DeviceTypeOtherGupWatering watering = new DeviceTypeOtherGupWatering(
					this);
			watering.setText(device.getDeviceName());
			watering.setBackgroundResource(device.getImgIco());
			watering.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(watering);
			} else {
				deviceViewRight.addView(watering);
			}
			break;
		}
		case 54: {
			DeviceTypeOtherGupRollerGas gas = new DeviceTypeOtherGupRollerGas(
					this);
			gas.setText(device.getDeviceName());
			gas.setBackgroundResource(device.getImgIco());
			gas.setOnClickListener(device, areaController);
			if ((i & 1) != 0) {
				deviceViewLeft.addView(gas);
			} else {
				deviceViewRight.addView(gas);
			}
			break;
		}
		default:
			break;
		}
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
		this.uiHandler.sendEmptyMessage(0);
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

	class TypeReceiver extends BroadcastReceiver {

		public static final String FORMATE = "*JOYRILL*STATE*";

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Logger.d("type receive broadcast");
			String s = bundle.getString("message");
			String a[] = s.split("#");
			try {
				for (int i = 0; i < a.length; i++) {
					String str = a[i];
					boolean b = Pattern.matches(
							"\\*JOYRILL\\*STATE\\*[\\s\\S]*\\:[\\s\\S]*", str);
					boolean c = Pattern.matches(
							"\\*STATE\\*[\\s\\S]*\\:[\\s\\S]*", str);
					if ((null != str && b) || (null != str && c)) {
						String subData = str.substring(
								str.lastIndexOf("*") + 1, str.length());
						String[] splitData = subData.split(";");
						for (int j = 0; j < splitData.length; j++) {
							String[] ss = splitData[j].split(":");
							if (ss.length == 2) {
								Integer deviceId = Integer.parseInt(ss[0]);

								BaseTwoWay baseTwoWay = component.get(deviceId);
								if (null != baseTwoWay) {
									baseTwoWay.setStatusText(ss[1], true);
								}
								BaseAdjust adjust = baseAdjust.get(deviceId);
								if (null != adjust) {
									adjust.setStatus(ss[1]);
								}
							} else {
								Logger.d("不处理数据：" + splitData[j]);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
