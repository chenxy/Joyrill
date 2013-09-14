package com.joyrill.app.dao;

import com.joyrill.app.dao.impl.AreaDAO;
import com.joyrill.app.dao.impl.CommandDAO;
import com.joyrill.app.dao.impl.DeviceDAO;
import com.joyrill.app.dao.impl.DeviceTypeDAO;
import com.joyrill.app.dao.impl.ParameterDAO;
import com.joyrill.app.dao.impl.SceneDAO;
import com.joyrill.app.dao.impl.TimerDAO;

public class DAOFactory {

	private static DAOFactory instance = null;

	private boolean cacheDAOInstances = false;

	private ParameterDAO cacheParameterDao = null;
	private AreaDAO cacheAreaDao = null;
	private DeviceDAO cacheDeviceDao = null;
	private DeviceTypeDAO cacheDeviceTypeDao = null;
	private SceneDAO cacheSceneDao = null;
	private TimerDAO cacheTimerDao = null;
	private CommandDAO cacheCommandDao = null;

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}

	public ParameterDAO getParameterDAO() {
		if (cacheDAOInstances) {
			if (cacheParameterDao == null) {
				cacheParameterDao = new ParameterDAO();
			}
			return cacheParameterDao;
		} else {
			return new ParameterDAO();
		}
	}

	public AreaDAO getAreaDAO() {
		if (cacheDAOInstances) {
			if (cacheAreaDao == null) {
				cacheAreaDao = new AreaDAO();
			}
			return cacheAreaDao;
		} else {
			return new AreaDAO();
		}
	}

	public DeviceDAO getDeviceDAO() {
		if (cacheDAOInstances) {
			if (cacheDeviceDao == null) {
				cacheDeviceDao = new DeviceDAO();
			}
			return cacheDeviceDao;
		} else {
			return new DeviceDAO();
		}
	}

	public SceneDAO getSceneDAO() {
		if (cacheDAOInstances) {
			if (cacheSceneDao == null) {
				cacheSceneDao = new SceneDAO();
			}
			return cacheSceneDao;
		} else {
			return new SceneDAO();
		}
	}

	public TimerDAO getTimerDAO() {
		if (cacheDAOInstances) {
			if (cacheTimerDao == null) {
				cacheTimerDao = new TimerDAO();
			}
			return cacheTimerDao;
		} else {
			return new TimerDAO();
		}
	}

	public DeviceTypeDAO getDeviceTypeDAO() {
		if (cacheDAOInstances) {
			if (cacheDeviceTypeDao == null) {
				cacheDeviceTypeDao = new DeviceTypeDAO();
			}
			return cacheDeviceTypeDao;
		} else {
			return new DeviceTypeDAO();
		}
	}

	public CommandDAO getCommandDAO() {
		if (cacheDAOInstances) {
			if (cacheDeviceTypeDao == null) {
				cacheDeviceTypeDao = new DeviceTypeDAO();
			}
			return cacheCommandDao;
		} else {
			return new CommandDAO();
		}
	}

	public void setCacheDAOInstances(boolean cacheDAOInstances) {
		this.cacheDAOInstances = cacheDAOInstances;
	}

}
