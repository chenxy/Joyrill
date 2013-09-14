package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.DeviceTypeCfg;

public class DeviceTypeDAO extends DAOHelper {

	public List<DeviceTypeCfg> findDeviceTypeByParentId(int id){
		String sql = "select DeviceTypeID,ParentID,DeviceTypeName,DeviceTypeDisplayName,DeviceTypeDes,'Index',Enabled,ImgIco from DeviceTypeCfg where ParentID='"+id+"'";
		Cursor cursor = null;
		List<DeviceTypeCfg> devicetypes = new ArrayList<DeviceTypeCfg>();
		try{
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.rawQuery(sql,new String[]{});
            while (cursor.moveToNext()) {
        		for(int i=0 ; i<cursor.getCount() ; i++){
        			cursor.moveToPosition(i);
        			DeviceTypeCfg deviceType = new DeviceTypeCfg();
        			deviceType.setDeviceTypeId(cursor.getInt(cursor.getColumnIndex("DeviceTypeID")));
        			deviceType.setParentId(cursor.getInt(cursor.getColumnIndex("ParentID")));
        			deviceType.setDeviecTypeName(cursor.getString(cursor.getColumnIndex("DeviceTypeName")));
        			deviceType.setDeviceTypeDisplayName(cursor.getString(cursor.getColumnIndex("DeviceTypeDisplayName")));
        			deviceType.setDeviceTypeDesc(cursor.getString(cursor.getColumnIndex("DeviceTypeDes")));
        			deviceType.setIndex(cursor.getInt(cursor.getColumnIndex("'Index'")));
        			deviceType.setEnable(cursor.getInt(cursor.getColumnIndex("Enabled")));
        			deviceType.setImgIco(cursor.getString(cursor.getColumnIndex("ImgIco")));
        			devicetypes.add(deviceType);
        		}
            }
		}finally{
			closeCursor(cursor);
		}
		return devicetypes;
	}
	
	public DeviceTypeCfg findRootDeviceTypeByParentId(int id){
		String sql = "select dt2.DeviceTypeID,dt2.ParentID,dt2.DeviceTypeName,dt2.DeviceTypeDisplayName,dt2.DeviceTypeDes,dt2.'Index',dt2.Enabled,dt2.ImgIco from DeviceTypeCfg dt1 , DeviceTypeCfg dt2 where dt1.parentId=dt2.DeviceTypeID and dt1.DeviceTypeID="+id;
		Cursor cursor = null;
		DeviceTypeCfg deviceType = new DeviceTypeCfg();
		try{
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.rawQuery(sql,new String[]{});
            cursor.moveToFirst();
			deviceType.setDeviceTypeId(cursor.getInt(cursor.getColumnIndex("DeviceTypeID")));
			deviceType.setParentId(cursor.getInt(cursor.getColumnIndex("ParentID")));
			deviceType.setDeviecTypeName(cursor.getString(cursor.getColumnIndex("DeviceTypeName")));
			deviceType.setDeviceTypeDisplayName(cursor.getString(cursor.getColumnIndex("DeviceTypeDisplayName")));
			deviceType.setDeviceTypeDesc(cursor.getString(cursor.getColumnIndex("DeviceTypeDes")));
			deviceType.setIndex(cursor.getInt(cursor.getColumnIndex("Index")));
			deviceType.setEnable(cursor.getInt(cursor.getColumnIndex("Enabled")));
			deviceType.setImgIco(cursor.getString(cursor.getColumnIndex("ImgIco")));
    
		}finally{
			closeCursor(cursor);
		}
		return deviceType;
	}
}
