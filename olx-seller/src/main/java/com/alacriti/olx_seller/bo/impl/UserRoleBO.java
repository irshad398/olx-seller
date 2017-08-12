package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.dao.impl.UserDAO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;


public class UserRoleBO extends BaseBO {
	
	public UserRoleBO(Connection connection) {
		super(connection);
	}
	
	
	public boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException, BOException{
		boolean isValidUser;
		try {
			UserDAO userDAO =   new UserDAO(getConnection());
			isValidUser = userDAO.checkUserLogin(userLoginVO);
			
		} catch (Exception e) {
			System.out.println("Exception in retrieveMessage " + e.getMessage());
			throw new BOException();
		}
		return isValidUser;
	}


	public void registerUser(UserRegisterVO userRegisterVO) throws DAOException, BOException {
	   
		try{
			UserDAO userDAO = new UserDAO(getConnection());
			userDAO.registerUser(userRegisterVO);
			
		}catch(Exception e){
			System.out.println("Exception in retrieveMessage " + e.getMessage());
			throw new BOException();
			
		}
		
		
	}
	
	
	/*public void createUserRole(UserRoleVO userVO) throws DAOException, BOException{
		
		try {
			UserDAO userDAO =   new UserDAO(getConnection());
			userDAO.createUserRole(userVO);
			
		} catch (Exception e) {
			System.out.println("Exception in retrieveMessage " + e.getMessage());
			throw new BOException();
		}
	}*/

}
