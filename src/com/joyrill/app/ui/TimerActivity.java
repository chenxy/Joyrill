package com.joyrill.app.ui;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joyrill.app.R;
import com.joyrill.app.config.Constants;
import com.joyrill.app.controller.TimerController;
import com.joyrill.app.model.Timer;
import com.joyrill.app.operation.ParameterOperation;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.widget.TimerItem;

public class TimerActivity extends BaseActivity implements OnClickListener,
		Handler.Callback {

	private LinearLayout timerLeft, timerRight;
	private List<Timer> timers;
	private Looper looper = null;
	private Handler uiHandler = null;
	public TimerController timerController = null;

	@Override
	protected void onStart() {
		super.onStart();
		this.setContentView(R.layout.timer);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		timerController = new TimerController();
		this.initComponent();
		uiHandler.sendEmptyMessage(0);
	}

	private void setTime() {
		int i = 1;
		for (; i <= timers.size(); i++) {
			TimerItem timerItem = new TimerItem(this);
			timerItem.setText(timers.get(i - 1).getName());
			timerItem.setBackgroundResource(timers.get(i - 1).getImgIco());
			timerItem.getTimerOpenButtonOpen().setOnClickListener(
					new TimerItemOnClickListener(timers.get(i - 1)));
			timerItem.getTimerOpenButtonClose().setOnClickListener(
					new TimerItemCloseClickListener(timers.get(i - 1)));
			if ((i & 1) != 0) {
				timerLeft.addView(timerItem);
			} else {
				timerRight.addView(timerItem);
			}
		}
	}

	class TimerItemOnClickListener implements OnClickListener {
		private Timer timer = null;

		public TimerItemOnClickListener(Timer timer) {
			this.timer = timer;
		}

		@Override
		public void onClick(View v) {
			timerController.openTimer(1, timer);
		}

	}

	class TimerItemCloseClickListener implements OnClickListener {
		private Timer timer = null;

		public TimerItemCloseClickListener(Timer timer) {
			this.timer = timer;
		}

		@Override
		public void onClick(View v) {
			timerController.openTimer(2, timer);
		}

	}

	private void initComponent() {
		this.timerLeft = (LinearLayout) this.findViewById(R.id.timerLeft);
		this.timerRight = (LinearLayout) this.findViewById(R.id.timerRight);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0: {
			timers = ParameterOperation.findTimer();
			setTime();
			break;
		}
		case 1: {
			Bundle bundle = msg.getData();
			String str = bundle.getString(Constants.RETURN);
			if (str != null && "*JOYRILL*OK#".equals(str)) {
				Toast.makeText(this, "������ʱ�ɹ�", Toast.LENGTH_LONG).show();
			}
			break;
		}
		case 2: {
			Bundle bundle = msg.getData();
			String str = bundle.getString(Constants.RETURN);
			if (str != null && "*JOYRILL*OK#".equals(str)) {
				Toast.makeText(this, "�رն�ʱ�ɹ�", Toast.LENGTH_LONG).show();
			}
			break;
		}
		default:
			break;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
