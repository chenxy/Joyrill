package com.joyrill.app.controller;

import com.joyrill.app.controller.base.BaseController;
import com.joyrill.app.model.Device;

public class DeviceController extends BaseController{

	public DeviceController(){
	}
	
	public void openDevice(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*255*CRC#";
		super.sentMsg(cmd);
	}
	
	public void closeDevice(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*0*CRC#";
		super.sentMsg(cmd);
	}

	public void changeDeviceStatus(Device device , int progress){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*"+progress+"*CRC#";
		super.sentMsg(cmd);
	}
	
	public void tvOpen(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*13*CRC#";
		super.sentMsg(cmd);
	}
	public void tvClose(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*13*CRC#";
		super.sentMsg(cmd);
	}
	public void tvVolumePlus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*1*CRC#";
		super.sentMsg(cmd);
	}
	
	public void tvVolumeMinus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*2*CRC#";
		super.sentMsg(cmd);
	}
	public void tvChannelPlus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*3*CRC#";
		super.sentMsg(cmd);
	}
	public void tvChannelMinus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*4*CRC#";
		super.sentMsg(cmd);
	}
	public void tvOk(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*5*CRC#";
		super.sentMsg(cmd);
	}
	public void tvMenu(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*6*CRC#";
		super.sentMsg(cmd);
	}
	public void tvBack(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*7*CRC#";
		super.sentMsg(cmd);
	}
	public void tvMute(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*8*CRC#";
		super.sentMsg(cmd);
	}
	public void tvStandBy(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*9*CRC#";
		super.sentMsg(cmd);
	}
	public void tvSignal(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*10*CRC#";
		super.sentMsg(cmd);
	}
	public void airOpen(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*1*CRC#";
		super.sentMsg(cmd);
	}
	public void airClose(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*2*CRC#";
		super.sentMsg(cmd);
	}
	public void airLeftAndRightWind(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*3*CRC#";
		super.sentMsg(cmd);
	}
	public void airUpAndDownWind(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*4*CRC#";
		super.sentMsg(cmd);
	}
	public void airBrute(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*5*CRC#";
		super.sentMsg(cmd);
	}
	public void airSleep(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*6*CRC#";
		super.sentMsg(cmd);
	}
	public void airCold(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*7*CRC#";
		super.sentMsg(cmd);
	}
	public void airHot(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*8*CRC#";
		super.sentMsg(cmd);
	}
	public void airAuto(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*9*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdMenu(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*1*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdOk(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*2*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdMute(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*3*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdStandBy(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*4*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdPrevious(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*5*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdNext(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*6*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdVolumePlus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*7*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdVolumeMinus(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*8*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdModal(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*9*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdPlay(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*10*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdPause(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*11*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdFF(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*12*CRC#";
		super.sentMsg(cmd);
	}
	public void dvdRew(Device device){
		String cmd = "*JOYRILL*COMMAND*"+device.getDeviceTypeId()+"*"+device.getDeviceId()+"*13*CRC#";
		super.sentMsg(cmd);
	}
	
	public void findDeviceState(Device device){
		String cmd = "*JOYRILL*STATE*1*"+device.getDeviceId()+"*CRC#";
		super.sentMsg(cmd);
	}
	
	public void findAllDeviceState(Device device){
		String cmd = "*JOYRILL*STATE*4**CRC#";
		super.sentMsg(cmd);
	}
	
	public void findDeviceStateByAreaId(int id){
		String cmd = "*JOYRILL*STATE*2*"+id+"*CRC#";
		super.sentMsg(cmd);
	}
	
	public void findDeviceStateByTypeId(int id){
		String cmd = "*JOYRILL*STATE*3*"+id+"*CRC#";
		super.sentMsg(cmd);
	}
	
}
