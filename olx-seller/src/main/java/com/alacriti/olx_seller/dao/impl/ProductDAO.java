package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.dao.IProductDAO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public class ProductDAO extends BaseDAO implements IProductDAO {
	private static final Logger log = Logger.getLogger(ProductDAO.class);

	public ProductDAO(Connection connection) {
		super(connection);
	}

	public ProductDAO() {

	}

	public ArrayList<ProductVO> getProducts() throws DAOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProducts(getConnection(), sqlCmd);
			log.debug("reached here********");
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			log.error("SQLException in getProducts(): " + e.getMessage(), e);
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	public PreparedStatement getPreparedStatementGetProducts(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		log.info("getPreparedStatementGetProducts: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id ORDER BY pt.posted_on DESC");
		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementGetProducts "
					+ e.getMessage(),e);
			throw e;
		}
	}

	public void getProductById(ProductVO productVO) throws DAOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProductById(getConnection(), sqlCmd);
			log.debug("reached here********");
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
			log.error("SQLException in getProductById(): "+e.getMessage(), e);
			throw new DAOException("SQLException in getProductById(): ", e);
		} finally {
			close(stmt, rs);
		}

	}

	private PreparedStatement getPreparedStatementGetProductById(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		log.info("getPreparedStatementGetProductById: " + sqlCmd);
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
			log.error("Exception in getPreparedStatementGetProductById "
							+ e.getMessage(),e);
			throw e;
		}
	}

	public ArrayList<ProductVO> getProductByCategory(int category_id)
			throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetProductByCategory(getConnection(),
					sqlCmd);
			log.debug("reached here********");
			stmt.setInt(1, category_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			log.error("SQLException in getProducts():"+e.getMessage(), e);
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	private PreparedStatement getPreparedStatementGetProductByCategory(
			Connection connection, String sqlCmd) throws SQLException {
		
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		log.info("getPreparedStatementGetProducts: " + sqlCmd);
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
			log.error("Exception in getPreparedStatementGetProductByCategory "
							+ e.getMessage(),e);
			throw e;
		}
	}

	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
				+ "pt.model,pt.old_or_new,pt.description,"
				+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
				+ " FROM irshadk_olx_seller_details as st "
				+ " INNER JOIN  irshadk_olx_product_details as pt"
				+ " ON pt.seller_id = st.seller_id"
				+ " INNER JOIN irshadk_olx_product_categories as ct"
				+ " ON pt.category_id = ct.category_id" + " WHERE ";
		try {
			if (searchProdutVO.getCatId() == 0) {
				sqlCmd += "pt.title LIKE ? OR pt.description LIKE ? ORDER BY pt.posted_on DESC";
				stmt = getPreparedStatementGetProductsBySearch(getConnection(),
						sqlCmd);
				stmt.setString(1, "%" + searchProdutVO.getTitle() + "%");
				stmt.setString(2, "%" + searchProdutVO.getTitle() + "%");
			} else {
				sqlCmd += "ct.category_id = ? AND pt.title LIKE ? OR pt.description LIKE ? ORDER BY pt.posted_on DESC";
				stmt = getPreparedStatementGetProductsBySearch(getConnection(),
						sqlCmd);
				stmt.setInt(1, searchProdutVO.getCatId());
				stmt.setString(2, "%" + searchProdutVO.getTitle() + "%");
				stmt.setString(3, "%" + searchProdutVO.getTitle() + "%");
			}
			log.debug("reached here********");
			log.info("searchProdutVO.getTitle(): "+searchProdutVO.getTitle());
			rs = stmt.executeQuery();
			log.info("searchProdutVO.getCatId(): "+searchProdutVO.getCatId());
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			log.error("SQLException in getProducts():"+e.getMessage(), e);
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	public PreparedStatement getPreparedStatementGetProductsBySearch(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		log.info("getPreparedStatementGetProductsBySearch: " + sqlCmd);
		try {
			return connection.prepareStatement(sqlCmd);
		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementGetProductsBySearch "
							+ e.getMessage(),e);
			throw e;
		}
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id)
			throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetSellerProducts(getConnection(),
					sqlCmd);
			log.debug("reached here********");
			stmt.setInt(1, seller_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			log.error("SQLException in getProducts():"+e.getMessage(), e);
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	private PreparedStatement getPreparedStatementGetSellerProducts(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		log.info("getPreparedStatementGetSellerProducts: " + sqlCmd);
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
			log.error("Exception in getPreparedStatementGetProductsBySearch "
					+ e.getMessage(),e);
			System.out
					.println("Exception in getPreparedStatementGetProductsBySearch "
							+ e.getMessage());
			throw e;
		}
	}

	public boolean addProduct(ProductVO productVO) throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementAddProduct(getConnection(), sqlCmd);
			log.debug("reached here********");
			log.debug("***productVO.getTitle(): "+productVO.getTitle());
			stmt.setString(1, productVO.getTitle());
			stmt.setInt(2, productVO.getCategory_id());
			stmt.setString(3, productVO.getDescription());
			stmt.setString(4, productVO.getModel());
			stmt.setString(5, productVO.getOld_or_new());
			stmt.setFloat(6, productVO.getPrice());
			stmt.setInt(7, productVO.getSeller_id());
			count = stmt.executeUpdate();
			if (count > 0) {
				log.info("Product Added successfully");
				return true;
			} else {
				log.info("Unable to add product");
				return false;
			}

		} catch (SQLException e) {
			log.error("SQLException in getProducts():"+e.getMessage(), e);
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt);
		}

	}

	private PreparedStatement getPreparedStatementAddProduct(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());

		log.info("getPreparedStatementAddProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("INSERT INTO irshadk_olx_product_details("
							+ "title,category_id,description,model,old_or_new,price,seller_id)"
							+ " VALUES(?,?,?,?,?,?,?)");

		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementAddProduct "
					+ e.getMessage(),e);
			throw e;
		}

	}

	public boolean deleteProduct(int seller_id, int product_id)
			throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementDeleteProduct(getConnection(), sqlCmd);
			log.debug("reached here********");
			stmt.setInt(1, product_id);
			stmt.setInt(2, seller_id);
			count = stmt.executeUpdate();
			if (count > 0) {
				log.info("Product Deleted successfully");
				return true;
			} else {
				log.info("Unable to delete the product");
				return false;
			}

		} catch (SQLException e) {
			log.error("SQLException in deleteProduct():"+e.getMessage(), e);
			throw new DAOException("SQLException in deleteProduct():", e);
		} finally {
			close(stmt);
		}
	}

	private PreparedStatement getPreparedStatementDeleteProduct(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());

		log.info("getPreparedStatementDeleteProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("DELETE FROM irshadk_olx_product_details"
							+ " WHERE product_id = ? AND seller_id = ?");

		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementDeleteProduct "
							+ e.getMessage(),e);
			throw e;
		}

	}

	public boolean updateProduct(ProductVO productVO, int seller_id,
			int product_id) throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		String sqlCmd = "sql cmd";
		int count;
		try {
			stmt = getPreparedStatementUpdateProduct(getConnection(), sqlCmd);
			log.debug("reached here********");
			stmt.setString(1, productVO.getTitle());
			stmt.setString(2, productVO.getDescription());
			stmt.setString(3, productVO.getModel());
			stmt.setFloat(4, productVO.getPrice());
			stmt.setString(5, productVO.getOld_or_new());
			stmt.setInt(6, product_id);
			stmt.setInt(7, seller_id);
			count = stmt.executeUpdate();
			if (count > 0) {
				log.info("Product updated successfully");
				return true;
			} else {
				log.info("Unable to update the product");
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException("SQLException in updateProduct():", e);
		} finally {
			close(stmt);
		}
	}

	private PreparedStatement getPreparedStatementUpdateProduct(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());

		log.info("getPreparedStatementUpdateProduct: " + sqlCmd);
		try {

			return connection
					.prepareStatement("UPDATE irshadk_olx_product_details"
							+ " SET title = ?, description = ? , model = ?, price = ?, old_or_new = ?"
							+ " where product_id = ? AND seller_id = ?");

		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementDeleteProduct "
							+ e.getMessage(),e);
			throw e;
		}

	}

	public ArrayList<ProductVO> getRecentProducts() throws DAOException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCmd = "sql cmd";
		try {
			stmt = getPreparedStatementGetRecentProducts(getConnection(),
					sqlCmd);
			log.debug("reached here********");
			rs = stmt.executeQuery();
			while (rs.next()) {
				products.add(new ProductVO(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getFloat(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getLong(10), rs.getString(11)));

			}

		} catch (SQLException e) {
			log.error("SQLException in getRecentProducts():"+e.getMessage(),e);
			throw new DAOException("SQLException in getRecentProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}

	private PreparedStatement getPreparedStatementGetRecentProducts(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());

		log.info("getPreparedStatementRecentGetProducts: " + sqlCmd);
		try {

			return connection
					.prepareStatement("SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
							+ "pt.model,pt.old_or_new,pt.description,"
							+ "pt.posted_on,st.seller_name,st.phone_no,st.email"
							+ " FROM irshadk_olx_seller_details as st "
							+ " INNER JOIN  irshadk_olx_product_details as pt"
							+ " ON pt.seller_id = st.seller_id"
							+ " INNER JOIN irshadk_olx_product_categories as ct"
							+ " ON pt.category_id = ct.category_id ORDER BY pt.posted_on DESC LIMIT 4");
		} catch (SQLException e) {
			log.error("Exception in getPreparedStatementGetProducts "
					+ e.getMessage(),e);
			throw e;
		}
	}

}
