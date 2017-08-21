package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.ICategoryBO;
import com.alacriti.olx_seller.dao.impl.CategoryDAO;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public class CategoryBO extends BaseBO implements ICategoryBO {
	private static final Logger log = Logger.getLogger(ProductBO.class);

	public CategoryBO(Connection connection) {
		super(connection);
	}

	public ArrayList<CategoryVO> getCategories() throws DAOException,
			BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<CategoryVO> categories = null;
		try {
			CategoryDAO categoryDAO = new CategoryDAO(getConnection());
			categories = categoryDAO.getCategories();
		} catch (Exception e) {
			log.error("Exception in retrieveCategories "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return categories;
	}
}
