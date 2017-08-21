package com.alacriti.olx_seller.bo.impl;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.exception.BaseException;


public class BOException extends BaseException {
	private static final Logger log = Logger.getLogger(BOException.class);

	public BOException() {
	}
	public BOException(String msg, Throwable th) {

		log.error(msg+" :"+th);
	}
/*
	public BOException(String msg, Throwable th, String errorCode) {
		super(msg, th, errorCode);
	}

	public BOException(Throwable th, String errorCode) {
		this(null, th, errorCode);
	}

	public BOException(String msg, Throwable th) {

		this(msg, th, ErrorConstants.ERR_GENERIC_DAO);
	}

	public BOException(Throwable th) {
		this(null, th, ErrorConstants.ERR_GENERIC_DAO);
	}

	public BOException(String msg, String errorCode) {
		this(msg, null, errorCode);
	}

	public BOException(String msg) {
		this(msg, null, ErrorConstants.ERR_GENERIC_DAO);
	}*/
}