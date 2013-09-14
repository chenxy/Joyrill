package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.logger.Logger;
import com.joyrill.app.model.Device;

public class DeviceDAO extends DAOHelper {

	public List<Device> getDeviceByAreaId(int id) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		List<Device> devices = new ArrayList<Device>();
		try {
			String sql = "select ID,AreaID,DeviceName,DeviceTypeID,DeviceDes,'Index',Enabled,ImgIco,State from Devices where AreaID='"
					+ id + "'";
			db = getReadableDatabase();
			cursor = db.rawQuery(sql, new String[] {});
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Device device = new Device();
				device.setDeviceId(cursor.getInt(cursor.getColumnIndex("ID")));
				device.setAreaId(cursor.getInt(cursor.getColumnIndex("AreaID")));
				device.setDeviceName(cursor.getString(cursor
						.getColumnIndex("DeviceName")));
				device.setDeviceTypeId(cursor.getInt(cursor
						.getColumnIndex("DeviceTypeID")));
				device.setDeviceDes(cursor.getString(cursor
						.getColumnIndex("DeviceDes")));
				device.setIndex(cursor.getInt(cursor.getColumnIndex("'Index'")));
				device.setEnabled(cursor.getInt(cursor
						.getColumnIndex("Enabled")));
				device.setImgIco(cursor.getString(cursor
						.getColumnIndex("ImgIco")));
				device.setState(cursor.getString(cursor.getColumnIndex("State")));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.e(e.getMessage());
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return devices;
	}

	public List<Device> getDeviceByDeviceTypeCfgId(int id) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		List<Device> devices = new ArrayList<Device>();
		try {
			String sql = "select "
					+ "d1.ID as ID,d1.AreaID as AreaID,d1.DeviceName as DeviceName, "
					+ "d1.DeviceTypeID as DeviceTypeID,d1.DeviceDes as DeviceDes, "
					+ "d1.'Index' as 'Index',d1.Enabled as Enabled,d1.ImgIco as ImgIco,d1.State as State "
					+ "from Devices d1,(select c1.DeviceTypeID from DeviceTypeCfg c1 , DeviceTypeCfg c2 "
					+ "where c1.ParentId=c2.DeviceTypeId "
					+ "and c2.DeviceTypeID='" + id
					+ "') d2 where d1.DeviceTypeID=d2.DeviceTypeID";
			db = getReadableDatabase();
			cursor = db.rawQuery(sql, new String[] {});
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Device device = new Device();
				device.setDeviceId(cursor.getInt(cursor.getColumnIndex("ID")));
				device.setAreaId(cursor.getInt(cursor.getColumnIndex("AreaID")));
				device.setDeviceName(cursor.getString(cursor
						.getColumnIndex("DeviceName")));
				device.setDeviceTypeId(cursor.getInt(cursor
						.getColumnIndex("DeviceTypeID")));
				device.setDeviceDes(cursor.getString(cursor
						.getColumnIndex("DeviceDes")));
				device.setIndex(cursor.getInt(cursor.getColumnIndex("Index")));
				device.setEnabled(cursor.getInt(cursor
						.getColumnIndex("Enabled")));
				device.setImgIco(cursor.getString(cursor
						.getColumnIndex("ImgIco")));
				device.setState(cursor.getString(cursor.getColumnIndex("State")));
				devices.add(device);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.e(e.getMessage());
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return devices;
	}

	public boolean updateDeviceByDeviceId(String id, String value) {
		SQLiteDatabase db = null;
		try {
			String sql = "update [Devices] set State='" + value
					+ "' where ID='" + id + "';";
			db = getReadableDatabase();
			db.execSQL(sql);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.e(e.getMessage());
		} finally {
			closeSQLiteDatabase(db);
		}
		return true;
	}
}
