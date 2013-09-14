package com.joyrill.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joyrill.app.dao.DAOHelper;
import com.joyrill.app.model.Scene;

public class SceneDAO extends DAOHelper {

	public List<Scene> findAllScene() {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		List<Scene> scenes = new ArrayList<Scene>();
		try {
			db = getReadableDatabase();
			cursor = db
					.rawQuery(
							"select SceneID,SceneName,SceneDisplayName,SceneDelay,IsSecvice,'Index',ImgIco,Enabled from Scene",
							new String[] {});
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Scene scene = new Scene();
				scene.setSceneId(cursor.getInt(cursor.getColumnIndex("SceneID")));
				scene.setSceneName(cursor.getString(cursor
						.getColumnIndex("SceneName")));
				scene.setSceneDisplayName(cursor.getString(cursor
						.getColumnIndex("SceneDisplayName")));
				scene.setSceneDelay(cursor.getString(cursor
						.getColumnIndex("SceneDelay")));
				scene.setIsService(cursor.getInt(cursor
						.getColumnIndex("IsSecvice")));
				scene.setIndex(cursor.getInt(cursor.getColumnIndex("'Index'")));
				scene.setImgIco(cursor.getString(cursor
						.getColumnIndex("ImgIco")));
				scene.setEnable(cursor.getInt(cursor.getColumnIndex("Enabled")));
				scenes.add(scene);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCursor(cursor);
			closeSQLiteDatabase(db);
		}
		Log.d("scene", "success loading " + scenes.size() + " scenes");
		return scenes;
	}

}
