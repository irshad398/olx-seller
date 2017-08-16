package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import com.alacriti.olx_seller.bo.impl.CategoryBO;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public class CategoryDelegate extends BaseDelegate {
	public CategoryDelegate() {

	}

	public ArrayList<CategoryVO> getCategories() {
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<CategoryVO> categories = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			CategoryBO categoryBO = new CategoryBO(getConnection());
			categories = categoryBO.getCategories();
		} catch (Exception e) {
			System.out.println("Exception in getCategories " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return categories;

	}

}
