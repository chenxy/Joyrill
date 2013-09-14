package com.joyrill.app.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.Url;
import com.joyrill.app.model.User;

public class ParameterDAO extends DAOHelper {

	public boolean savePassword(boolean isSavePassword) {
		String sql = "update Parameters set ParaValue='" + isSavePassword
				+ "' where ParaName='IsSavePassword'";
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			db.execSQL(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSQLiteDatabase(db);
		}
		return false;
	}

	public boolean autoLogin(boolean autoLogin) {
		String sql = "update Parameters set ParaValue='" + autoLogin
				+ "' where ParaName='IsAutoLogin'";
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			db.execSQL(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSQLiteDatabase(db);
		}
		return false;
	}

	public boolean saveUserNameAndPassword(User user) {
		String sqlAddress = "update Parameters set ParaValue='"
				+ user.getUserName() + "' where ParaName='LastUserName'";
		String sqlPort = "update Parameters set ParaValue='"
				+ user.getPassword() + "' where ParaName='LastPassword'";
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			db.execSQL(sqlAddress);
			db.execSQL(sqlPort);
		} catch (Exception e) {
		} finally {
			closeSQLiteDatabase(db);
		}
		return true;
	}

	public User findDefaultUser() {
		Cursor cursor = null;
		User user = new User();
		SQLiteDatabase db = null;
		String sql = "select * from Parameters where ParaName=?";
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery(sql, new String[] { "LastUserName" });
			if (cursor.moveToNext()) {
				String username = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				user.setUserName(username);
			}

		} finally {
			closeCursor(cursor);
		}
		try {
			cursor = db.rawQuery(sql, new String[] { "LastPassword" });
			if (cursor.moveToNext()) {
				String password = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				user.setPassword(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return user;
	}

	public User findFtpDefaultUser() {
		Cursor cursor = null;
		User user = new User();
		SQLiteDatabase db = null;
		String sql = "select * from Parameters where ParaName=?";
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery(sql, new String[] { "FtpUserName" });
			if (cursor.moveToNext()) {
				String username = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				user.setUserName(username);
			}
		} finally {
			closeCursor(cursor);
		}

		try {
			cursor = db.rawQuery(sql, new String[] { "FtpPassword" });
			if (cursor.moveToNext()) {
				String password = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				user.setPassword(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return user;
	}

	public Url findInternetUrl() {
		Cursor cursor = null;
		Url url = new Url();
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "OuterNetIP" });
			if (cursor.moveToFirst()) {
				String address = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setAddress(address);

			}
		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
		}
		try {
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "OuterNetPort" });
			if (cursor.moveToFirst()) {
				String port = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setPort(port);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}

/*		url.setAddress("192.168.77.39");
		url.setPort("8001");*/
		return url;
	}

	public Url findIntranetUrl() {
		Cursor cursor = null;
		Url url = new Url();
		SQLiteDatabase db = null;
		try {

			db = getReadableDatabase();
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "IntranetIP" });
			if (cursor.moveToNext()) {
				String address = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setAddress(address);
			}

		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);

		}
		try {
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "IntranetPort" });
			if (cursor.moveToNext()) {
				String port = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setPort(port);

			}
		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
/*		url.setAddress("192.168.77.39");
		url.setPort("8001");*/
		return url;
	}

	public Url findFtpInternetUrl() {
		Cursor cursor = null;
		Url url = new Url();
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "OuterNetIP" });
			if (cursor.moveToNext()) {
				String address = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setAddress(address);
			}

		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		try {
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "FtpServicePort" });
			if (cursor.moveToNext()) {
				String port = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setPort(port);
			}
		} catch (Exception e) {

		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		return url;
	}

	public Url findFtpIntranetUrl() {
		Cursor cursor = null;
		Url url = new Url();
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "IntranetIP" });
			if (cursor.moveToNext()) {
				String address = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setAddress(address);
			}
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "FtpServicePort" });
			if (cursor.moveToNext()) {
				String port = cursor.getString(cursor
						.getColumnIndex("ParaValue"));
				url.setPort(port);
			}
		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);

		}
		return url;
	}

	public String findDefaultLoginType() {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		String type = "";
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select * from Parameters where ParaName=?",
					new String[] { "NetMode" });
			if (cursor.moveToNext()) {
				type = cursor.getString(cursor.getColumnIndex("ParaValue"));
			}
		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
			db.close();
		}
		return type;
	}

	public String findParaValueByParaName(String paraName) {
		Cursor cursor = null;
		String type = "";
		String sql = "select * from [Parameters] where paraName = '" + paraName
				+ "'";
		try {
			SQLiteDatabase db = getReadableDatabase();
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()) {
				type = cursor.getString(cursor.getColumnIndex("ParaValue"));
			}
		} catch (Exception e) {
		} finally {
			closeCursor(cursor);
		}
		return type;
	}

	public boolean setParametersByParaValue(String paraName, String paraValue) {
		String sql = "update Parameters set ParaValue='" + paraValue
				+ "' where ParaName='" + paraName + "'";
		try {
			SQLiteDatabase db = getReadableDatabase();
			db.execSQL(sql);
		} catch (Exception e) {
		} finally {

		}
		return true;
	}

	public boolean updateIntranetUrl(Url url) {
		String sqlAddress = "update Parameters set ParaValue='"
				+ url.getAddress() + "' where ParaName='IntranetIP'";
		String sqlPort = "update Parameters set ParaValue='" + url.getPort()
				+ "' where ParaName='IntranetPort'";
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			db.execSQL(sqlAddress);
			db.execSQL(sqlPort);
		} catch (Exception e) {
		} finally {
			closeSQLiteDatabase(db);
		}
		return true;
	}

	public boolean updateInternetUrl(Url url) {
		String sqlAddress = "update Parameters set ParaValue='"
				+ url.getAddress() + "' where ParaName='OuterNetIP'";
		String sqlPort = "update Parameters set ParaValue='" + url.getPort()
				+ "' where ParaName='OuterNetPort'";
		SQLiteDatabase db = null;
		try {
			db = getReadableDatabase();
			db.execSQL(sqlAddress);
			db.execSQL(sqlPort);
		} catch (Exception e) {
		} finally {
			closeSQLiteDatabase(db);
		}
		return true;
	}

}
