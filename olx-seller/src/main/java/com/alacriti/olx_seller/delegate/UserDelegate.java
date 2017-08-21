package com.alacriti.olx_seller.delegate;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.impl.UserRoleBO;
import com.alacriti.olx_seller.delegate.BaseDelegate;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public class UserDelegate extends BaseDelegate {
	private static final Logger log = Logger.getLogger(UserDelegate.class);

	public boolean checkUserLogin(UserLoginVO userLoginVO) {
		log.info("In User delegate*******checkUserLogin");
		log.info("In User delegate***userLoginVO.getEmail()****"+userLoginVO.getEmail());
		boolean isValidUser = false;
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			UserRoleBO userRoleBO = new UserRoleBO(getConnection());
			isValidUser = userRoleBO.checkUserLogin(userLoginVO);
		} catch (Exception e) {
			log.error("Exception in checkUserLogin " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return isValidUser;
	}
	public boolean registerUser(UserRegisterVO userRegisterVO){
		log.info("In User delegate*********registerUser");
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			UserRoleBO userRoleBO = new UserRoleBO(getConnection());
			return userRoleBO.registerUser(userRegisterVO);
			
		} catch (Exception e) {
			log.error("Exception in registerUser " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}
}
