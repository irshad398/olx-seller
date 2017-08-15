package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

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
		String sqlCmd = "sql cmd";
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

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id ORDER BY pt.posted_on DESC" );
		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementGetProducts "
					+ e.getMessage());
			throw e;
		}
	}

	public void getProductById(ProductVO productVO) throws DAOException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProductById(getConnection(), sqlCmd);
			System.out.println("reached here********");
			stmt.setInt(1, productVO.getProduct_id());
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

			return connection
					.prepareStatement("SELECT pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email "
							+ "FROM irshadk_olx_product_details as pt,"
							+ "irshadk_olx_seller_details as st,"
							+ "irshadk_olx_product_categories as ct "
							+ "WHERE pt.product_id = ? and pt.category_id = ct.category_id "
							+ "AND pt.seller_id = st.seller_id");
		} catch (SQLException e) {
			System.out
					.println("Exception in getPreparedStatementGetProductById "
							+ e.getMessage());
			throw e;
		}
	}

	public ArrayList<ProductVO> getProductByCategory(int category_id)throws DAOException {
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProductByCategory(getConnection(), sqlCmd);
			System.out.println("reached here********");
			stmt.setInt(1, category_id);
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
	
	private PreparedStatement getPreparedStatementGetProductByCategory(
			Connection connection, String sqlCmd) throws SQLException{
		// log.debugPrintCurrentMethodName();

		System.out.println("getPreparedStatementGetProducts: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id AND pt.category_id = ? ORDER BY pt.posted_on DESC");
		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementGetProductByCategory "
					+ e.getMessage());
			throw e;
		}
	}

	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException {

		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProductsBySearch(getConnection(),
					sqlCmd);
			System.out.println("reached here********");
			stmt.setInt(1, searchProdutVO.getCatId());
			stmt.setString(2, "%" + searchProdutVO.getTitle() + "%");
			System.out.println(searchProdutVO.getTitle() + ":I am here");
			rs = stmt.executeQuery();
			System.out.println(searchProdutVO.getCatId() + ":Hey...I am here");
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}
			// System.out.println(products.iterator().next().getTitle());

		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	public PreparedStatement getPreparedStatementGetProductsBySearch(
			Connection connection, String sqlCmd) throws SQLException {
		// log.debugPrintCurrentMethodName();

		System.out
				.println("getPreparedStatementGetProductsBySearch: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id"
							+ " WHERE ct.category_id = ? AND pt.title LIKE ? ORDER BY pt.posted_on DESC");

		} catch (SQLException e) {
			System.out
					.println("Exception in getPreparedStatementGetProductsBySearch "
							+ e.getMessage());
			throw e;
		}
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id)
			throws DAOException {
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetSellerProducts(getConnection(),
					sqlCmd);
			System.out.println("reached here********");
			stmt.setInt(1, seller_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}
			// System.out.println(products.iterator().next().getTitle());

		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	private PreparedStatement getPreparedStatementGetSellerProducts(
			Connection connection, String sqlCmd) throws SQLException {
		// log.debugPrintCurrentMethodName();

		System.out
				.println("getPreparedStatementGetSellerProducts: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id"
							+ " WHERE st.seller_id = ? ORDER BY pt.posted_on DESC");

		} catch (SQLException e) {
			System.out
					.println("Exception in getPreparedStatementGetProductsBySearch "
							+ e.getMessage());
			throw e;
		}
	}

	public boolean addProduct(ProductVO productVO) throws DAOException {
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementAddProduct(getConnection(), sqlCmd);
			System.out.println("reached here********");
			System.out.println(productVO.getTitle());
			stmt.setString(1, productVO.getTitle());
			stmt.setInt(2, productVO.getCategory_id());
			stmt.setString(3, productVO.getDescription());
			stmt.setString(4, productVO.getModel());
			stmt.setString(5, productVO.getOld_or_new());
			stmt.setFloat(6, productVO.getPrice());
			stmt.setInt(7, productVO.getSeller_id());
			count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Product Added successfully");
				return true;
			}
			else{
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt);
		}

	}

	private PreparedStatement getPreparedStatementAddProduct(
			Connection connection, String sqlCmd) throws SQLException {

		System.out.println("getPreparedStatementAddProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("INSERT INTO irshadk_olx_product_details("
							+ "title,category_id,description,model,old_or_new,price,seller_id)"
							+ " VALUES(?,?,?,?,?,?,?)");

		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementAddProduct "
					+ e.getMessage());
			throw e;
		}

	}

	public boolean deleteProduct(int seller_id, int product_id)
			throws DAOException {
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementDeleteProduct(getConnection(), sqlCmd);
			System.out.println("reached here********");
			stmt.setInt(1, product_id);
			stmt.setInt(2, seller_id);
			count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Product Deleted successfully");
				return true;
			} else {
				System.out.println("Unable to delete the product");
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in deleteProduct():", e);
		} finally {
			close(stmt);
		}
	}

	private PreparedStatement getPreparedStatementDeleteProduct(
			Connection connection, String sqlCmd) throws SQLException {

		System.out.println("getPreparedStatementDeleteProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("DELETE FROM irshadk_olx_product_details"
							+ " WHERE product_id = ? AND seller_id = ?");

		} catch (SQLException e) {
			System.out
					.println("Exception in getPreparedStatementDeleteProduct "
							+ e.getMessage());
			throw e;
		}

	}

	public boolean updateProduct(ProductVO productVO, int seller_id,
			int product_id) throws DAOException {
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementUpdateProduct(getConnection(), sqlCmd);
			System.out.println("reached here********");
			stmt.setString(1, productVO.getTitle());
			// stmt.setInt(2,seller_id); SET title = ?,category_id = ?, description = ? , model = ?, price = ?"
//			+ " where product_id = ? AND seller_id = ?");
			stmt.setString(2, productVO.getDescription());
			stmt.setString(3, productVO.getModel());
			stmt.setFloat(4, productVO.getPrice());
			stmt.setString(5, productVO.getOld_or_new());
			stmt.setInt(6, product_id);
			stmt.setInt(7, seller_id);
			count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Product updated successfully");
				return true;
			} else {
				System.out.println("Unable to update the product");
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in updateProduct():", e);
		} finally {
			close(stmt);
		}
	}

	private PreparedStatement getPreparedStatementUpdateProduct(
			Connection connection, String sqlCmd) throws SQLException{


		System.out.println("getPreparedStatementUpdateProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("UPDATE irshadk_olx_product_details"
							+ " SET title = ?, description = ? , model = ?, price = ?, old_or_new = ?"
							+ " where product_id = ? AND seller_id = ?");

		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementDeleteProduct "
					+ e.getMessage());
			throw e;
		}

	
	}

	

}
