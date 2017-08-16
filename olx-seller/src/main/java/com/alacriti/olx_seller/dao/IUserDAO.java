package com.alacriti.olx_seller.dao;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public interface IUserDAO {
	boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException;
	boolean registerUser(UserRegisterVO userRegisterVO) throws DAOException;
	
}
