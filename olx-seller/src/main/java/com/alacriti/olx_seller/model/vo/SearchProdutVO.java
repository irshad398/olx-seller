package com.alacriti.olx_seller.model.vo;

public class SearchProdutVO {
	private String title;
	private int catId;
	
	public SearchProdutVO() {
		super();
	}

	public SearchProdutVO(String title, int catId) {
		super();
		this.title = title;
		this.catId = catId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}
	
	
	
}
