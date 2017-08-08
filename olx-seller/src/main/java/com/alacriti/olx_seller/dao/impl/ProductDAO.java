package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alacriti.olx_seller.model.vo.ProductVO;

public class ProductDAO extends BaseDAO {

	public ProductDAO(Connection connection) {
		super(connection);
	}

	public ProductDAO() {

	}

	public ArrayList<ProductVO> getProducts() throws DAOException {
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
				+ "pt.model,pt.old_or_new,pt.description,"
				+ "pt.posted_on,st.seller_name,st.phone_no,st.email "
				+ "FROM irshadk_olx_product_details as pt,"
				+ "irshadk_olx_seller_details as st,"
				+ "irshadk_olx_product_categories as ct "
				+ "WHERE pt.category_id = ct.category_id "
				+ "AND pt.seller_id = st.seller_id";
		try {
			stmt = getPreparedStatementGetProducts(getConnection(), sqlCmd);
			System.out.println("reached here********");
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	public PreparedStatement getPreparedStatementGetProducts(
			Connection connection, String sqlCmd) throws SQLException {
		// log.debugPrintCurrentMethodName();

		System.out.println("getPreparedStatementGetProducts: " + sqlCmd);
		try {

			return connection.prepareStatement(sqlCmd);
		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementGetProducts "
					+ e.getMessage());
			throw e;
		}
	}

	public void getProductById(ProductVO productVO) throws DAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "SELECT pt.title,ct.category_name,pt.price,"
				+ "pt.model,pt.old_or_new,pt.description,"
				+ "pt.posted_on,st.seller_name,st.phone_no,st.email "
				+ "FROM irshadk_olx_product_details as pt,"
				+ "irshadk_olx_seller_details as st,"
				+ "irshadk_olx_product_categories as ct "
				+ "WHERE pt.product_id = '" + productVO.getProduct_id()
				+ "' and pt.category_id = ct.category_id "
				+ "AND pt.seller_id = st.seller_id";
		try {
			stmt = getPreparedStatementGetProductById(getConnection(), sqlCmd);
			System.out.println("reached here********");
			rs = stmt.executeQuery();

			while (rs.next()) {
				productVO.setTitle(rs.getString(1));
				productVO.setCategory_name(rs.getString(2));
				productVO.setPrice(rs.getFloat(3));
				productVO.setModel(rs.getString(4));
				productVO.setOld_or_new(rs.getString(5));
				productVO.setDescription(rs.getString(6));
				productVO.setPostedOn(rs.getString(7));
				productVO.setSeller_name(rs.getString(8));
				productVO.setPhone_no(rs.getLong(9));
				productVO.setEmail(rs.getString(10));
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in getProductById(): ", e);
		} finally {
			close(stmt, rs);
		}

	}

	private PreparedStatement getPreparedStatementGetProductById(
			Connection connection, String sqlCmd) throws SQLException {
		System.out.println("getPreparedStatementGetProductById: " + sqlCmd);
		try {

			return connection.prepareStatement(sqlCmd);
		} catch (SQLException e) {
			System.out
					.println("Exception in getPreparedStatementGetProductById "
							+ e.getMessage());
			throw e;
		}
	}

}
