package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alacriti.olx_seller.dao.IUserDAO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;

public class UserDAO extends BaseDAO implements IUserDAO{
//	private static final AppLogger log = LogUtil.getLogger(UserDAO.class);
	public UserDAO(Connection conn) {
		super(conn);
	}
	public UserDAO(){
		
	}
	public boolean checkUserLogin(UserLoginVO userLoginVO) throws DAOException {
//		log.debugPrintCurrentMethodName();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isValidUser=false;
		
		try {
			
			String sqlCmd = "sqlCmd";
			stmt =getPreparedStatementCheckUserLogin(getConnection(), sqlCmd);
			System.out.println("reached here********");
			stmt.setString(1,userLoginVO.getEmail());
			rs= stmt.executeQuery();
			if(rs.next()){
				if(userLoginVO.getPassword().equals(rs.getString("password"))){
					System.out.println("Successfully Logged in***");
					userLoginVO.setSeller_id(rs.getInt("seller_id"));
					userLoginVO.setSeller_name(rs.getString("seller_name"));
					System.out.println(userLoginVO.getSeller_name());
					//UserResource.validUser=true;
					isValidUser = true;
				}
				else
				{
					isValidUser = false;
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println(
					"SQLException in checkUser " + e.getMessage());
			//throw new DAOException("SQLException in getUserRole():"+ e);
		} finally {
			close(stmt);
		}
		return isValidUser;
	}
	
	public PreparedStatement getPreparedStatementCheckUserLogin(Connection connection, String sqlCmd) throws SQLException{
//		log.debugPrintCurrentMethodName();

		System.out.println("getStatement: " + sqlCmd);
		try {
			
			return connection.prepareStatement("select seller_id, seller_name, email, password from irshadk_olx_seller_details where email = ? ");
		} catch (SQLException e) {
			System.out.println("Exception in getStatementCheckUser " + e.getMessage());
			throw e;
		}
	}
	public boolean registerUser(UserRegisterVO userRegisterVO) throws DAOException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sqlCmd = "sql command";
			
			stmt =getPreparedStatementRegisterUser(getConnection(), sqlCmd);
			stmt.setString(1,userRegisterVO.getName());
			stmt.setString(2, userRegisterVO.getEmail());
			stmt.setLong(3, userRegisterVO.getPhoneNumber());
			stmt.setString(4,userRegisterVO.getPassword());
			stmt.setString(5, userRegisterVO.getQuestion());
			stmt.setString(6, userRegisterVO.getAnswer());
//			log.logDebug("reached here********");
			int count= stmt.executeUpdate();
			if(count>0){
				//userRegisterVO.setRoleCreate(true);
				System.out.println("User inserted!");
				return true;
			}
			else{
				System.out.println("User not inserted...");
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	private PreparedStatement getPreparedStatementRegisterUser (
			Connection connection, String sqlCmd)throws SQLException {
		try {
				return connection.prepareStatement("INSERT INTO irshadk_olx_seller_details(seller_name,email,phone_no,password,security_question,answer) values(?,?,?,?,?,?)");
			} catch (SQLException e) {
			System.out.println("Exception in getPreparedStatementRegisterUser " + e.getMessage());
			throw e;
		}
	}

}
