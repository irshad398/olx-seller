package com.alacriti.olx_seller.bo;

import com.alacriti.olx_seller.bo.impl.BOException;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public interface IUserRoleBO {
	boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException,
			BOException;

	boolean registerUser(UserRegisterVO userRegisterVO) throws DAOException,
			BOException;
}
