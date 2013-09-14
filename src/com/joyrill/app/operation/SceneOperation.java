package com.joyrill.app.operation;

import java.util.List;

import com.joyrill.app.dao.DAOFactory;
import com.joyrill.app.dao.impl.SceneDAO;
import com.joyrill.app.model.Scene;

public class SceneOperation {

	public SceneOperation() {
	}

	private static DAOFactory daoFactory = DAOFactory.getInstance();

	public static List<Scene> findAllScene() {
		SceneDAO dao = null;
		try {
			dao = daoFactory.getSceneDAO();
			return dao.findAllScene();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
