package com.alacriti.olx_seller.model.vo;

public class UserLoginVO {
	private int seller_id;
	private String seller_name;
	private String email;
	private String password;
	

	public UserLoginVO() {

	}

	
	public UserLoginVO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public int getSeller_id() {
		return seller_id;
	}


	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}


	public String getSeller_name() {
		return seller_name;
	}


	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
}
