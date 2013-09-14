package com.joyrill.app.operation;

import java.util.List;

import com.joyrill.app.dao.DAOFactory;
import com.joyrill.app.dao.impl.DeviceDAO;
import com.joyrill.app.dao.impl.DeviceTypeDAO;
import com.joyrill.app.model.Device;
import com.joyrill.app.model.DeviceTypeCfg;

public class DeviceOperation {

	public DeviceOperation() {
	}

	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	public static List<Device> findDeviceByAreaId(int id){
		DeviceDAO dao = null;
		try{
			dao = daoFactory.getDeviceDAO();
			return dao.getDeviceByAreaId(id);
		}finally{
			if(dao != null){
				dao.close();
			}
		}
		
	}
	
	public static List<Device> findDeviceByDeviceTypeCfgId(int id){
		DeviceDAO dao = null;
		try{
			dao = daoFactory.getDeviceDAO();
			return dao.getDeviceByDeviceTypeCfgId(id);
		}finally{
			if(dao != null){
				dao.close();
			}
		}
		
	}
	
	public static boolean updateDeviceByDeviceId(String id,String value){
		DeviceDAO dao = null;
		try{
			dao = daoFactory.getDeviceDAO();
			return dao.updateDeviceByDeviceId(id, value);
		}finally{
			if(dao != null){
				dao.close();
			}
		}
		
	}
	
	public static List<DeviceTypeCfg> findDeviceTypeByParentId(int id){
		DeviceTypeDAO dao = null;
		try{
			dao = daoFactory.getDeviceTypeDAO();
			return dao.findDeviceTypeByParentId(id);
			
		}finally{
			if(dao != null){
				dao.close();
			}
		}
	}
	
	public static DeviceTypeCfg findRootDeviceTypeByParentId(int id){
		DeviceTypeDAO dao = null;
		try{
			dao = daoFactory.getDeviceTypeDAO();
			return dao.findRootDeviceTypeByParentId(id);
			
		}finally{
			if(dao != null){
				dao.close();
			}
		}
	}
}
