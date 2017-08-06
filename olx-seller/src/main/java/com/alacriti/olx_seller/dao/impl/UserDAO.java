package com.alacriti.olx_seller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;
import com.alacriti.olx_seller.resources.UserResource;

public class UserDAO extends BaseDAO{
//	private static final AppLogger log = LogUtil.getLogger(UserDAO.class);
	public UserDAO(Connection conn) {
		super(conn);
	}
	public UserDAO(){
		
	}
	public void checkUserLogin(UserLoginVO userLoginVO) throws DAOException {
//		log.debugPrintCurrentMethodName();
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			String userName=userLoginVO.getUserName();
			String password=userLoginVO.getPassword();
			String sqlCmd = "select password from irshadk_olx_seller_details where email='"+userName+"'";
			stmt =getStatementCheckUserLogin(getConnection(), sqlCmd);
			//System.out.println("reached here********");
			rs= stmt.executeQuery(sqlCmd);
			if(rs.next()){
				if(password.equals(rs.getString("password"))){
					System.out.println("Successfully Logged in***"+userName);
					UserResource.validUser=true;
				}
				else
				{
					System.out.println("Invalid Password");
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println(
					"SQLException in checkUser " + e.getMessage());
			//throw new DAOException("SQLException in getUserRole():"+ e);
		} finally {
			close(stmt);
		}
	}
	
	public Statement getStatementCheckUserLogin(Connection connection, String sqlCmd) throws SQLException{
//		log.debugPrintCurrentMethodName();

		System.out.println("getStatement: " + sqlCmd);
		try {

			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Exception in getStatementCheckUser " + e.getMessage());
			throw e;
		}
	}
	public void registerUser(UserRegisterVO userRegisterVO) throws DAOException{
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
			}
		} catch (SQLException e) {
			System.out.println(
					"SQLException in registerUser " + e.getMessage());
			//throw new DAOException("SQLException in createUserRole():", e);
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
	
	
	/*public void createUserRole(UserRoleVO userRoleVO) throws DAOException{
		log.debugPrintCurrentMethodName();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sqlCmd = "sql command";
			
			stmt =getPreparedStatementCreateUserRole(getConnection(), sqlCmd);
			stmt.setInt(1,userRoleVO.getRoleTypeId());
			stmt.setString(2, userRoleVO.getRoleName());
			stmt.setString(3, userRoleVO.getRoleDesc());
			log.logDebug("reached here********");
			int count= stmt.executeUpdate();
			if(count>0){
				userRoleVO.setRoleCreate(true);
			}
		} catch (SQLException e) {
			log.logError(
					"SQLException in createUserRole " + e.getMessage(), e);
			throw new DAOException("SQLException in createUserRole():", e);
		} finally {
			close(stmt, rs);
		}
	}
	
	public PreparedStatement getPreparedStatementCreateUserRole(Connection connection, String sqlCmd) throws SQLException{
		try {
			return connection.prepareStatement("insert into role_type_tbl values(?,?,?)");
		} catch (SQLException e) {
			log.logError("Exception in getPreparedStatementCreateUser " + e.getMessage(), e);
			throw e;
		}
	}*/

}
