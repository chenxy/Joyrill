package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.Area;

public class AreaDAO extends DAOHelper {

	public List<Area> findAllArea() {
		Cursor cursor = null;
		List<Area> areas = new ArrayList<Area>();
		try {
			SQLiteDatabase db = getReadableDatabase();
			cursor = db
					.rawQuery(
							"select AreaID,AreaName,ParentAreaID,'Index',ImgIco from Area",
							new String[] {});
			while (cursor.moveToNext()) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					Area area = new Area();
					area.setAreaId(cursor.getInt(cursor
							.getColumnIndex("AreaID")));
					area.setAreaName(cursor.getString(cursor
							.getColumnIndex("AreaName")));
					area.setParentAreaId(cursor.getInt(cursor
							.getColumnIndex("ParentAreaID")));
					area.setIndex(cursor.getInt(cursor
							.getColumnIndex("'Index'")));
					area.setImgIco(cursor.getString(cursor
							.getColumnIndex("ImgIco")));
					areas.add(area);
				}
			}
		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
		}
		return areas;
	}

	public List<Area> findViewDevices(int areaId) {
		Cursor cursor = null;
		List<Area> areas = new ArrayList<Area>();
		try {
			SQLiteDatabase db = getReadableDatabase();
			cursor = db.rawQuery("SELECT Devices.ID," + "Devices.AreaID,  "
					+ "Devices.DeviceName, " + " Devices.DeviceTypeID, "
					+ " Devices.DeviceDes, " + "Devices.[Index], "
					+ "Devices.Enabled, " + "Devices.ImgIco,"
					+ "devices.[State]," + "DeviceTypeCfg.DeviceTypeName,"
					+ "DeviceTypeCfg.DeviceTypeDisplayName,"
					+ "DeviceTypeCfg.DeviceTypeDes," + "area.[AreaName] "
					+ "FROM " + "Devices " + "INNER JOIN " + "DeviceTypeCfg "
					+ "ON "
					+ "Devices.DeviceTypeID = DeviceTypeCfg.DeviceTypeID "
					+ "inner join " + "area " + "ON "
					+ "devices.[AreaID]=area.[AreaID]"
					+ "and devices.[AreaID]='" + areaId + "'", new String[] {});
			while (cursor.moveToNext()) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
				}
			}
		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
		}
		return areas;
	}

}
