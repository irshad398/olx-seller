package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.dao.IUserDAO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public class UserDAO extends BaseDAO implements IUserDAO {
	private static final Logger log = Logger.getLogger(UserDAO.class);

	public UserDAO(Connection conn) {
		super(conn);
	}

	public UserDAO() {

	}

	public boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isValidUser = false;

		try {

			String sqlCmd = "sqlCmd";
			stmt = getPreparedStatementCheckUserLogin(getConnection(), sqlCmd);
			log.debug("reached here********");
			stmt.setString(1, userLoginVO.getEmail());
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (userLoginVO.getPassword().equals(rs.getString("password"))) {
					userLoginVO.setSeller_id(rs.getInt("seller_id"));
					userLoginVO.setSeller_name(rs.getString("seller_name"));
					System.out.println(userLoginVO.getSeller_name());

					isValidUser = true;
				} else {
					isValidUser = false;
				}

			}
			log.debug("Logged in***" + userLoginVO.getEmail());

		} catch (SQLException e) {
			log.error("SQLException in checkUser " + e.getMessage(), e);
			throw new DAOException("SQLException in getUserRole():", e);
		} finally {
			close(stmt);
		}
		return isValidUser;
	}

	public PreparedStatement getPreparedStatementCheckUserLogin(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());

		log.info("getStatement: " + sqlCmd);
		try {

			return connection
					.prepareStatement("select seller_id, seller_name, email, password from irshadk_olx_seller_details where email = ? ");
		} catch (SQLException e) {
			log.error("Exception in getStatementCheckUser " + e.getMessage(), e);
			throw e;
		}
	}

	public boolean registerUser(UserRegisterVO userRegisterVO)
			throws DAOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlCmd = "sql command";

			stmt = getPreparedStatementRegisterUser(getConnection(), sqlCmd);
			stmt.setString(1, userRegisterVO.getName());
			stmt.setString(2, userRegisterVO.getEmail());
			stmt.setLong(3, userRegisterVO.getPhoneNumber());
			stmt.setString(4, userRegisterVO.getPassword());
			stmt.setString(5, userRegisterVO.getQuestion());
			stmt.setString(6, userRegisterVO.getAnswer());
			log.debug("reached here********");
			int count = stmt.executeUpdate();
			if (count > 0) {
				log.info("User inserted!");
				return true;
			} else {
				log.info("User not inserted...");
				return false;
			}
		} catch (SQLException e) {
			log.error("SQLException in createUserRole():" + e.getMessage(), e);
			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}

	}

	private PreparedStatement getPreparedStatementRegisterUser(
			Connection connection, String sqlCmd) throws SQLException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		try {
			return connection
					.prepareStatement("INSERT INTO irshadk_olx_seller_details(seller_name,email,phone_no,password,security_question,answer) values(?,?,?,?,?,?)");
		} catch (SQLException e) {
			log.error(
					"Exception in getPreparedStatementRegisterUser "
							+ e.getMessage(), e);
			throw e;
		}
	}

}
