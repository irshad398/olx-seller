package com.alacriti.olx_seller.dao.impl;

import com.alacriti.olx_seller.exception.BaseException;



public class DAOException extends BaseException {

	public DAOException() {
	}

	public DAOException(String msg, Throwable th) {
		super(msg, th);
	}
	/*
	public DAOException(Throwable th, String errorCode) {
		this(null, th, errorCode);
	}
/*
	public DAOException(String msg, Throwable th) {
		
		this(msg, th);
	}

	public DAOException(Throwable th) {
		this(null, th, ErrorConstants.ERR_GENERIC_DAO);
	}

	public DAOException(String msg, String errorCode) {
		this(msg, null, errorCode);
	}

	public DAOException(String msg) {
		this(msg, null, ErrorConstants.ERR_GENERIC_DAO);
	}*/
}