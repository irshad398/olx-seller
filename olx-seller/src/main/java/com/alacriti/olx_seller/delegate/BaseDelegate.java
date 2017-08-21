package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.datasource.MySqlDataSource;

public class BaseDelegate {
	private static final Logger log = Logger.getLogger(BaseDelegate.class);
	private Connection connection;

	public void setConnection(Connection _connection) {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		this.connection = _connection;
	}

	public Connection getConnection() {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		return connection;
	}

	protected void endDBTransaction(Connection connection) {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		try {
			connection.commit();

		} catch (SQLException e) {
			log.error("SQLException in endDBTransaction " + e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error("SQLException in endDBTransaction" + e1.getMessage());
			}
		} catch (Exception e) {
			log.error("Exception in endDBTransaction" + e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				log.error("SQLException in endDBTransaction" + e.getMessage());
			}
		}

	}

	protected void endDBTransaction(Connection connection, boolean isRollback) {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		if (isRollback) {
			try {
				connection.rollback();
				log.info("Rolled Back on some exception....!!!");
			} catch (SQLException e) {
				log.error("SQLException in endDBTransaction " + e.getMessage());
			}

			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					log.error("SQLException in endDBTransaction " + e.getMessage());
				}
			}
		} else {
			endDBTransaction(connection);
		}

	}

	protected Connection startDBTransaction() {
		log.debug("In " + Thread.currentThread().getStackTrace()[2].getMethodName());
		Connection conn = null;
		try {
			if (conn == null || conn.isClosed())
				conn = MySqlDataSource.getInstance().getConnection();

			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("SQLException in startDBTransaction " + e.getMessage());
		}
		return conn;

	}
}
