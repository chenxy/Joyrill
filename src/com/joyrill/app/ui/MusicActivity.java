package com.joyrill.app.ui;

import java.util.ArrayList;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.adapter.music.MusicItemAdapter;
import com.joyrill.app.adapter.music.MusicViewHolder;
import com.joyrill.app.controller.MusicController;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Music;
import com.joyrill.app.ui.base.BaseActivity;

public class MusicActivity extends BaseActivity implements OnClickListener,OnItemClickListener,Handler.Callback{

	private Looper looper = null;
	private Handler uiHandler = null;
	private ListView musicListView = null;

	private ImageButton musicStop,musicPrevious,musicPlay,musicNext,musicPause,musicVolumeType;
	private TextView musicVolumeProgress;
	private SeekBar musicVolumeBar;
	private MusicController musicController = null;
	private List<Music> musiclist = new ArrayList<Music>();;

	private MusicItemAdapter musicItemAdapter = null;
	private Music playing = null;
	private int currentPlaying = -1 , currentVolume = -1;
	private boolean isSound = true;
	private MusicReceiver receiver = null;
	private IntentFilter intentFilter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.music);
		looper = this.getMainLooper();
		uiHandler = new Handler(looper, this);
		musicController = new MusicController();
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.joyrill.app.command");
		receiver = new MusicReceiver();
		this.registerReceiver(receiver, intentFilter); 
		musicController.getMusicCount();
		this.initComponent();
		this.initListener();
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		
		case 0:{
			currentVolume = musicVolumeBar.getProgress();
			if(currentVolume != 0){
				isSound = true;
			}
			musicController.getMusicCount();
			musicVolumeProgress.setText(currentVolume*100/musicVolumeBar.getMax()+"%");
			break;
		}
		
		default:
			break;
			
		}
		return false;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.musicStop:{
			this.musicController.stop(1);
			break;
		}
		case R.id.musicPrevious:{
			this.musicController.previous(2);
			currentPlaying = currentPlaying-1;
			musicItemAdapter.setSelectItem(currentPlaying);
			break;
		}
		case R.id.musicPlay:{
			this.musicController.pause(3);
			break;
		}
		case R.id.musicNext:{
			this.musicController.next(4);
			currentPlaying = currentPlaying+1;
			musicItemAdapter.setSelectItem(currentPlaying);
			break;
		}
		case R.id.musicPause:{
			this.musicController.pause(3);
			break;
		}
		case R.id.musicSingle:{
			this.musicController.setSingleloop(1, 6);
			break;
		}
		case R.id.musicSingleLoop:{
			this.musicController.setSingleloop(2, 6);
			break;
		}
		case R.id.musicOrder:{
			this.musicController.setOrder(3, 6);
			break;
		}
		case R.id.musicLoop:{
			this.musicController.setLoop(4, 6);
			break;
		}
		case R.id.musicVolumeType:{
			changeVolumeType();
		}
		default :{
			
		}
		}
	}
	
	private void changeVolumeType(){
		if(isSound){
			isSound = false;
			currentVolume = musicVolumeBar.getProgress();
			musicVolumeBar.setProgress(0);
			musicVolumeProgress.setText(musicVolumeBar.getProgress()*100/musicVolumeBar.getMax());
			this.musicVolumeType.setImageResource(R.drawable.music_mute);
			musicController.mute(0, 7);
		}else{
			isSound = true;
			musicVolumeBar.setProgress(currentVolume);
			musicVolumeProgress.setText(currentVolume*100/musicVolumeBar.getMax()+"%");
			this.musicVolumeType.setImageResource(R.drawable.music_voice);
			musicController.mute(1, 7);
			musicController.setAbsVolume(currentVolume*100/musicVolumeBar.getMax(), 6);
		}
	}

	private void initComponent(){
		this.musicListView = (ListView)this.findViewById(R.id.musicListView);
		this.musicStop = (ImageButton)this.findViewById(R.id.musicStop);
		this.musicPrevious = (ImageButton)this.findViewById(R.id.musicPrevious);
		this.musicPlay = (ImageButton)this.findViewById(R.id.musicPlay);
		this.musicNext = (ImageButton)this.findViewById(R.id.musicNext);
		this.musicPause = (ImageButton)this.findViewById(R.id.musicPause);
		this.musicVolumeType = (ImageButton)this.findViewById(R.id.musicVolumeType);
		this.musicVolumeBar = (SeekBar)this.findViewById(R.id.musicVolumeBar);
		this.musicVolumeProgress = (TextView)this.findViewById(R.id.musicVolumeProgress);
	}

	private void initListener(){
		this.musicStop.setOnClickListener(this);
		this.musicPrevious.setOnClickListener(this);
		this.musicPlay.setOnClickListener(this);
		this.musicStop.setOnClickListener(this);
		this.musicPause.setOnClickListener(this);
		this.musicNext.setOnClickListener(this);
		this.musicVolumeType.setOnClickListener(this);
		this.musicListView.setOnItemClickListener(this);
		this.musicVolumeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if(seekBar.getProgress() == 0){
					
				}else{
					currentVolume = seekBar.getProgress();
					musicVolumeProgress.setText(seekBar.getProgress()*100/musicVolumeBar.getMax()+"%");
					musicController.setAbsVolume(seekBar.getProgress(), 6);
				}
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MusicViewHolder holder = (MusicViewHolder)view.getTag();
		playing = musiclist.get(Integer.parseInt(holder.id.getText().toString())-1);
		musicController.play(playing,6);
		currentPlaying = position;
		musicItemAdapter.setSelectItem(currentPlaying);
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



	class MusicReceiver extends BroadcastReceiver{
		public static final String PSGE_COUNT= "*JOYRILL*MP3*LIST*3*CRC#";
		/**
		 * DemoString:JOYRILL*MP3*LIST*1*\n手机铃声-讨好女朋友必备(男声版本).mp3\n水木年华 - 一生有你.mp3\n苏打绿-小情歌.mp3\n苏仨 - 八连发.mp3\n汪峰 - 存在.mp3\n汪峰 - 当我想你的时候.mp3\n汪峰 - 青春.mp3\na.mp3\n
		 **JOYRILL*MP3*LIST*2*\nb.mp3\nc.mp3\nd.mp3\ne.mp3\nf.mp3\n庾澄庆 - 春泥.mp3\n张惠妹 - 我可以抱你吗.mp3\n张嘉杰 - 永远是兄弟.mp3\n#
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Logger.d("music receive broadcast");
			String s = bundle.getString("message");
			if(s!=null){
				String[] l = s.split("#");
				for(int i=0 ; i<l.length ; i++){
					analyse(l[i]);
				}
			}
		}
		
		public void analyse(String s){
			boolean s1 = Pattern.matches("\\*JOYRILL\\*MP3\\*LIST\\*[0-9]*\\*CRC", s);
			boolean s2 = Pattern.matches("\\*JOYRILL\\*MP3\\*LIST\\*[0-9]*\\*[\\s\\S\\u4E00-\u9FFF]*", s);
			if(s2){
				if(s1){
					int page = Integer.parseInt(s.replace("*JOYRILL*MP3*LIST*", "").replace("*CRC", ""));
					if(page != 0){
						if(page>5){
							for(int i=1;i<=5;i++){
								try {
									new MyMusicThread(i).start();
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}else{
							for(int i=1;i<=page;i++){
								try {
									new MyMusicThread(i).start();
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}else{
					String list = s.substring(s.lastIndexOf("*")+1, s.length());
					String[] musicList = list.split("\n");
					System.out.println(musicList.length);
					for(int i=1 ;i<musicList.length ; i++){
						Music music = new Music();
						music.setId(musiclist.size()+1);
						String a = musicList[i];
						music.setName(a);
						musiclist.add(music);
					}
					musicItemAdapter = new MusicItemAdapter(MusicActivity.this, musiclist);
					musicListView.setAdapter(musicItemAdapter);
					musicItemAdapter.notifyDataSetInvalidated();;
				}
			}else{
			}
		}
		
	}
	class MyMusicThread extends Thread{
		private int i;
		MyMusicThread(int i){
			this.i = i;
		}
		@Override
		public void run() {
			musicController.getMusicListByPage(i);
			super.run();
		}
		
	}

}
