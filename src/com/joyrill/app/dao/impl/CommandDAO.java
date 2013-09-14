package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.Command;

public class CommandDAO extends DAOHelper {

	public CommandDAO() {
	}

	public List<Command> findAllArea(String deviceId) {
		Cursor cursor = null;
		List<Command> commands = new ArrayList<Command>();
		try {
			SQLiteDatabase db = getReadableDatabase();
			cursor = db.rawQuery("select " + "c.ID,c.DeviceId,c.CommandName,"
					+ "c.CommandDisplayName,c.CommandValue,"
					+ "c.Enabled,c.'Index',c.CommandTypeID,"
					+ "c.ImgIco,c.DisplayType " + "from " + "Commands c "
					+ "where " + "c.DeviceID='" + deviceId + "'",
					new String[] {});
			while (cursor.moveToNext()) {
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					Command command = new Command();
					command.setCommandId(cursor.getInt(cursor
							.getColumnIndex("ID")));
					command.setDeviceId(cursor.getInt(cursor
							.getColumnIndex("DeviceId")));
					command.setCommandName(cursor.getString(cursor
							.getColumnIndex("CommandName")));
					command.setCommandValue(cursor.getString(cursor
							.getColumnIndex("CommandValue")));
					command.setEnable(cursor.getInt(cursor
							.getColumnIndex("Enabled")));
					command.setIndex(cursor.getInt(cursor
							.getColumnIndex("'Index'")));
					command.setCommandTypeId(cursor.getInt(cursor
							.getColumnIndex("CommandTypeID")));
					command.setImgIco(cursor.getString(cursor
							.getColumnIndex("ImgIco")));
					command.setDisplayType(cursor.getInt(cursor
							.getColumnIndex("DisplayType")));
					commands.add(command);
				}
			}
		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
		}
		return commands;
	}
}
