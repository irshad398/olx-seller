package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.alacriti.olx_seller.bo.ICategoryBO;
import com.alacriti.olx_seller.dao.impl.CategoryDAO;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public class CategoryBO extends BaseBO implements ICategoryBO{
	public CategoryBO(Connection connection) {
		super(connection);
	}
	public ArrayList<CategoryVO> getCategories() throws DAOException, BOException {
		ArrayList<CategoryVO> categories = null;
		try {
			CategoryDAO categoryDAO = new CategoryDAO(getConnection());
			categories = categoryDAO.getCategories();
		} catch (Exception e) {
			System.out.println("Exception in retrieveCategories "
					+ e.getMessage());
			throw new BOException();
		}
		return categories;
	}
}
