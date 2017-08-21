package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.impl.CategoryBO;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public class CategoryDelegate extends BaseDelegate {
	private static final Logger log = Logger.getLogger(CategoryDelegate.class);

	public ArrayList<CategoryVO> getCategories() {
		log.info("In Category Delegate****getCategories");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<CategoryVO> categories = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			CategoryBO categoryBO = new CategoryBO(getConnection());
			categories = categoryBO.getCategories();
		} catch (Exception e) {
			log.error("Exception in getCategories " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return categories;

	}
}
