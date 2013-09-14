package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.Timer;

public class TimerDAO extends DAOHelper {

	public List<Timer> findTimer() {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		String sql = "select ID,Name,Type,FunctionID,ExecStyle,ExecDate,ExecTime,State,Enabled,'Index',ImgIco from Timers";
		List<Timer> timers = new ArrayList<Timer>();

		try {
			db = getReadableDatabase();
			cursor = db.rawQuery(sql, new String[] {});
			while (cursor.moveToNext()) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					Timer timer = new Timer();
					timer.setId(cursor.getInt(cursor.getColumnIndex("ID")));
					timer.setName(cursor.getString(cursor
							.getColumnIndex("Name")));
					timer.setType(cursor.getInt(cursor.getColumnIndex("Type")));
					timer.setFunctionId(cursor.getInt(cursor
							.getColumnIndex("FunctionID")));
					timer.setExecStyle(cursor.getInt(cursor
							.getColumnIndex("ExecStyle")));
					timer.setExecDate(cursor.getString(cursor
							.getColumnIndex("ExecDate")));
					timer.setExecTime(cursor.getString(cursor
							.getColumnIndex("ExecTime")));
					timer.setState(cursor.getInt(cursor.getColumnIndex("State")));
					timer.setEnable(cursor.getInt(cursor
							.getColumnIndex("Enabled")));
					timer.setIndex(cursor.getInt(cursor
							.getColumnIndex("'Index'")));
					timer.setImgIco(cursor.getString(cursor
							.getColumnIndex("ImgIco")));
					timers.add(timer);
				}
			}
		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return timers;
	}
}
