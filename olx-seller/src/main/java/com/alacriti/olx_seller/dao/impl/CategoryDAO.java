package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alacriti.olx_seller.dao.ICategoryDAO;
import com.alacriti.olx_seller.model.vo.CategoryVO;

public class CategoryDAO extends BaseDAO implements ICategoryDAO{

	public CategoryDAO(Connection connection) {
		super(connection);
	}

	public CategoryDAO() {

	}

	public ArrayList<CategoryVO> getCategories() throws DAOException {
		ArrayList<CategoryVO> categories = new ArrayList<CategoryVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetCategories(getConnection(), sqlCmd);
			System.out.println("reached here********");
			rs = stmt.executeQuery();
			while (rs.next()) {
				categories.add(new CategoryVO(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return categories;
	}

	private PreparedStatement getPreparedStatementGetCategories(
			Connection connection, String sqlCmd) throws SQLException {
		// log.debugPrintCurrentMethodName();

		System.out.println("getPreparedStatementGetCategories: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT category_id,category_name from irshadk_olx_product_categories ");
		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementGetProducts "
					+ e.getMessage());
			throw e;
		}
	}
}
