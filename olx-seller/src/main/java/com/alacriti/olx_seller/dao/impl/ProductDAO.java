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
		try {
			/*String sqlCmd = 
					"SELECT pd.title,c.category_name,pd.price,pd.model,"
					+ "pd.old_or_new,pd.description,pd.ts,s.seller_name,s.phone_no,"
					+ "s.email from irshadk_olx_products as p,"
					+ "irshadk_olx_product_details as pd,"
					+ "irshadk_olx_seller_details as s,"
					+ "irshadk_olx_product_categories as c"
					+ "where p.product_id = pd.product_id"
					+ "and p.category_id = c.category_id"
					+ "and p.seller_id = s.seller_id";*/
			String sqlCmd = "SELECT pt.product_id,pt.title,ct.category_name,pt.price,"
					+ "pt.model,pt.old_or_new,pt.description,"
					+ "pt.posted_on,st.seller_name,st.phone_no,st.email "
					+ "FROM irshadk_olx_product_details as pt,"
					+ "irshadk_olx_seller_details as st,"
					+ "irshadk_olx_product_categories as ct "
					+ "WHERE pt.category_id = ct.category_id "
					+ "AND pt.seller_id = st.seller_id";
			stmt = getPreparedStatementGetProducts(getConnection(), sqlCmd);
			System.out.println("reached here********");
			rs = stmt.executeQuery();
			while(rs.next()){
				products.add(new ProductVO(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getFloat(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getLong(10),rs.getString(11)));
				
			}
			/*while (rs.next()) {
				userVO.setRoleTypeId(rs.getInt(1));
				userVO.setRoleName(rs.getString(2));
			}
			log.logDebug("userVO.UserAcctId********" + userVO.getRoleTypeId());
			log.logDebug("userVO.RoleName********" + userVO.getRoleName());*/
		} catch (SQLException e) {
			throw new DAOException("SQLException in getProducts():", e);
		} finally {
			close(stmt, rs);
		}
		return products;
	}
	public PreparedStatement getPreparedStatementGetProducts(Connection connection, String sqlCmd) throws SQLException{
//		log.debugPrintCurrentMethodName();

		System.out.println("getPreparedStatement: " + sqlCmd);
		try {
			
			return connection.prepareStatement(sqlCmd);
		} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementGetProducts " + e.getMessage());
			throw e;
		}
	}

}
