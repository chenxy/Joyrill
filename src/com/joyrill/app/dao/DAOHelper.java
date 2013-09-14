package com.joyrill.app.dao;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOHelper{
	
	private SQLiteDatabase db;
	
	protected void closeCursor(Cursor cursor){
		if(cursor != null && !cursor.isClosed()){
			cursor.close();
		}
	}
	
	
	protected void closeSQLiteDatabase(SQLiteDatabase db){
		if(db != null && db.isOpen()){
			db.close();
		}
	}
	
	@SuppressLint("SdCardPath")
	protected SQLiteDatabase getReadableDatabase(){
		db = SQLiteDatabase.openDatabase("/data/data/com.joyrill.app/databases/zjdata.db", null, SQLiteDatabase.OPEN_READWRITE);
		return db;
	}

	public void close(){
		if(db != null && db.isOpen()){
			db.close();
		}
	}
	
}
	