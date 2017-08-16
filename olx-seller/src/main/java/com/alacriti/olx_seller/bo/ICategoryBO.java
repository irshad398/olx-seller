package com.alacriti.olx_seller.bo;

import java.util.ArrayList;

import com.alacriti.olx_seller.bo.impl.BOException;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public interface ICategoryBO {
	ArrayList<CategoryVO> getCategories() throws DAOException, BOException;
}
