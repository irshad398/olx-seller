package com.alacriti.olx_seller.dao;

import java.util.ArrayList;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public interface ICategoryDAO {
	ArrayList<CategoryVO> getCategories() throws DAOException;

	
}
