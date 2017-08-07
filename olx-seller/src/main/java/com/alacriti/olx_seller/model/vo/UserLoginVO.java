package com.alacriti.olx_seller.model.vo;

public class UserLoginVO {
	private String email;
	private String password;

	public UserLoginVO() {

	}

	public UserLoginVO(String email, String password) {

		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return email;
	}

	public void setName(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
