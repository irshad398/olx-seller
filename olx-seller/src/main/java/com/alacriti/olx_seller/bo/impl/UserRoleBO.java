package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.IUserRoleBO;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.dao.impl.UserDAO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;


public class UserRoleBO extends BaseBO implements IUserRoleBO{
	private static final Logger log = Logger.getLogger(UserRoleBO.class);
	
	public UserRoleBO(Connection connection) {
		super(connection);
	}
	
	public boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException, BOException{
		log.debug( "In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		boolean isValidUser;
		try {
			UserDAO userDAO =   new UserDAO(getConnection());
			isValidUser = userDAO.checkUserLogin(userLoginVO);
			
		} catch (Exception e) {
			log.error("Exception in checkUserLogin " + e.getMessage(),e);
			throw new BOException();
		}
		return isValidUser;
	}


	public boolean registerUser(UserRegisterVO userRegisterVO) throws DAOException, BOException {
		log.debug( "In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		try{
			UserDAO userDAO = new UserDAO(getConnection());
			return userDAO.registerUser(userRegisterVO);
			
		}catch(Exception e){
			log.error("Exception in registerUser " + e.getMessage(),e);
			throw new BOException("Exception in registerUser ",e);
			
		}
		
		
	}
	
}
