package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;
import com.joyrill.app.model.Music;

public class MusicController extends BaseController{
	public MusicController(){
	}
	public void getMusicList(int commandId){
		String cmd = "*JOYRILL*MP3*LIST**CRC#";
		super.sentMsg(cmd);
	}
	public void getMusicCount(){
		String cmd = "*JOYRILL*MP3*LIST*0**CRC#";
		super.sentMsg(cmd);
	}
	public void getMusicListByPage(int page){
		String cmd = "*JOYRILL*MP3*LIST*"+page+"**CRC#";
		super.sentMsg(cmd);
	}
	public void play(Music music,int commandId){
		String cmd = "*JOYRILL*MP3*PLAY*"+music.getName()+"*CRC#";
		super.sentMsg(cmd);
	}
	public void play(String name){
		String cmd = "*JOYRILL*MP3*PLAY*"+name+"*CRC#";
		super.sentMsg(cmd);
	}
	public void stop(int commandId){
		String cmd = "*JOYRILL*MP3*STOP**CRC#";
		super.sentMsg(cmd);
	}
	public void previous(int commandId){
		String cmd = "*JOYRILL*MP3*PREV**CRC#";
		super.sentMsg(cmd);
	}
	public void next(int commandId){
		String cmd = "*JOYRILL*MP3*NEXT**CRC#";
		super.sentMsg(cmd);
	}
	public void pause(int commandId){
		String cmd = "*JOYRILL*MP3*PAUSE**CRC#";
		super.sentMsg(cmd);
	}
	public void mute(int value , int commandId){
		String cmd = "*JOYRILL*MP3*MUTE*"+value+"*CRC#";
		super.sentMsg(cmd);
	}
	public void setAbsVolume(int value , int commandId){
		String cmd = "*JOYRILL*MP3*VOLUME*"+value+" 1*CRC#";
		super.sentMsg(cmd);
	}
	public void setSingle(int value , int commandId){
		String cmd = "*JOYRILL*MP3*MODE*"+value+"**CRC#";
		super.sentMsg(cmd);
	}
	public void setSingleloop(int value ,int commandId){
		String cmd = "*JOYRILL*MP3*MODE*"+value+"**CRC#";
		super.sentMsg(cmd);
	}
	public void setLoop(int value , int commandId){
		String cmd = "*JOYRILL*MP3*MODE*"+value+"**CRC#";
		super.sentMsg(cmd);
	}
	public void setOrder(int value , int commandId){
		String cmd = "*JOYRILL*MP3*MODE*"+value+"**CRC#";
		super.sentMsg(cmd);
	}
}
