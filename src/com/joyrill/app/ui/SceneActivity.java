package com.joyrill.app.ui;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.joyrill.app.R;
import com.joyrill.app.controller.SceneController;
import com.joyrill.app.model.Scene;
import com.joyrill.app.operation.SceneOperation;
import com.joyrill.app.ui.base.BaseActivity;
import com.joyrill.app.widget.SceneItem;

public class SceneActivity extends BaseActivity implements OnClickListener,
		Handler.Callback {

	private LinearLayout sceneLeft, sceneRight;
	List<Scene> scenes;
	private Looper looper = null;
	private Handler uiHandler = null;
	Scene scene;
	private SceneController sceneController = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.scene);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		sceneController = new SceneController();
		this.initComponent();
		this.initListener();
		scenes = SceneOperation.findAllScene();
		initSceneData();
	}

	private void initSceneData() {
		int i = 1;
		for (; i <= scenes.size(); i++) {
			scene = scenes.get(i - 1);
			SceneItem sceneItem = new SceneItem(this);
			sceneItem.setText(scenes.get(i - 1).getSceneDisplayName());
			sceneItem.setBackgroundResource(scenes.get(i - 1).getImgIco());
			sceneItem.setOnClickListener(scene, sceneController);
			if ((i & 1) != 0) {
				sceneLeft.addView(sceneItem);
			} else {
				sceneRight.addView(sceneItem);
			}
		}
	}

	private void initComponent() {
		this.sceneLeft = (LinearLayout) this.findViewById(R.id.sceneLeft);
		this.sceneRight = (LinearLayout) this.findViewById(R.id.sceneRight);
	}

	private void initListener() {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			break;
		default:
			break;
		}
		return false;
	}

}
