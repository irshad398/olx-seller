package com.alacriti.olx_seller.model.vo;

public class ProductVO {
	private int product_id;
	private String title;
	private String category_name;
	private float price;
	private String model;
	private String old_or_new;
	private String description;
	private String postedOn;
	private String seller_name;
	private long phone_no;
	private String email;
	
	public ProductVO() {
		super();
	}


	public ProductVO(int product_id, String title, String category_name,
			float price, String model, String old_or_new, String description,
			String postedOn, String seller_name, long phone_no, String email) {
		super();
		this.product_id = product_id;
		this.title = title;
		this.category_name = category_name;
		this.price = price;
		this.model = model;
		this.old_or_new = old_or_new;
		this.description = description;
		this.postedOn = postedOn;
		this.seller_name = seller_name;
		this.phone_no = phone_no;
		this.email = email;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getOld_or_new() {
		return old_or_new;
	}


	public void setOld_or_new(String old_or_new) {
		this.old_or_new = old_or_new;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPostedOn() {
		return postedOn;
	}


	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}


	public String getSeller_name() {
		return seller_name;
	}


	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}


	public long getPhone_no() {
		return phone_no;
	}


	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
