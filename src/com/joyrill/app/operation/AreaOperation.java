package com.joyrill.app.operation;

import java.util.List;

import com.joyrill.app.dao.DAOFactory;
import com.joyrill.app.dao.impl.AreaDAO;
import com.joyrill.app.model.Area;

public class AreaOperation {

	public AreaOperation() {
	}
	
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	public static List<Area> findAllArea(){
		AreaDAO dao = null;
		try{
			dao = daoFactory.getAreaDAO();
			return dao.findAllArea();
		}finally{
			if(dao != null){
				dao.close();
			}
		}
	}
}
