package com.joyrill.app.operation;

import java.util.List;

import com.joyrill.app.dao.DAOFactory;
import com.joyrill.app.dao.impl.ParameterDAO;
import com.joyrill.app.dao.impl.TimerDAO;
import com.joyrill.app.model.Timer;
import com.joyrill.app.model.Url;
import com.joyrill.app.model.User;

public class ParameterOperation {
	private static DAOFactory daoFactory = DAOFactory.getInstance();

	public static boolean savePassword(boolean isSavePassword) {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.savePassword(isSavePassword);
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static boolean autoLogin(boolean autoLogin) {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.autoLogin(autoLogin);
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static boolean findIsSavePassword() {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return Boolean.parseBoolean(parameterDao.findParaValueByParaName("IsSavePassword"));
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static boolean findIsAutoLogin() {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return Boolean.parseBoolean(parameterDao
					.findParaValueByParaName("IsAutoLogin"));
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static boolean saveUserNameAndPassword(User user) {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.saveUserNameAndPassword(user);
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static String findDataBaseVersion(){
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.findParaValueByParaName("DataVersion");
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static String findParaValueByParaName(String paraValue) {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.findParaValueByParaName(paraValue);
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static boolean saveLoginType(String id) {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.setParametersByParaValue("NetMode", id);
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static String findImei() {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.findParaValueByParaName("IMEI");
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}
	}
	
	public static List<Timer> findTimer(){
		TimerDAO dao = null;
		try{
			dao = daoFactory.getTimerDAO();
			return dao.findTimer();
		}finally{
			if(dao != null){
				dao.close();
			}
		}
	}
	
	public static String findDefaultLoginType() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.findDefaultLoginType();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	public static Url findInternetUrl() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.findInternetUrl();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
	
	public static Url findIntranetUrl() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.findIntranetUrl();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}


	public static Url findAutoLoginUrl() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			Integer netMode = Integer.parseInt(dao.findDefaultLoginType());
			if (netMode == 2) {
				return dao.findInternetUrl();
			} else {
				return dao.findIntranetUrl();
			}
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	public static Url findFtpIntranetUrl() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.findFtpIntranetUrl();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	public static Url findFtpInternetUrl() {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.findFtpInternetUrl();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	public static boolean updateIntranetUrl(Url url) {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.updateIntranetUrl(url);
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	public static boolean updateInternetUrl(Url url) {
		ParameterDAO dao = null;
		try {
			dao = daoFactory.getParameterDAO();
			return dao.updateInternetUrl(url);
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
	
	public static User findDefaultUser() {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.findDefaultUser();
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}

	}
	
	public static User findFtpDefaultUser() {
		ParameterDAO parameterDao = null;
		try {
			parameterDao = daoFactory.getParameterDAO();
			return parameterDao.findFtpDefaultUser();
		} finally {
			if (parameterDao != null) {
				parameterDao.close();
			}
		}

	}
	
}
