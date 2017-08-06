package com.alacriti.olx_seller.delegate;

import java.sql.Connection;

import com.alacriti.olx_seller.bo.impl.UserRoleBO;
import com.alacriti.olx_seller.delegate.BaseDelegate;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public class UserDelegate extends BaseDelegate {

	public void checkUserLogin(UserLoginVO userLoginVO) {
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			UserRoleBO userRoleBO = new UserRoleBO(getConnection());
			userRoleBO.checkUserLogin(userLoginVO);
		} catch (Exception e) {
			System.out.println("Exception in getMessage " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}

	}
	public void registerUser(UserRegisterVO userRegisterVO){
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			UserRoleBO userRoleBO = new UserRoleBO(getConnection());
			userRoleBO.registerUser(userRegisterVO);
			
		} catch (Exception e) {
			System.out.println("Exception in getMessage " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}
}
