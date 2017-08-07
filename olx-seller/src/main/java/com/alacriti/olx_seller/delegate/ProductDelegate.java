package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import com.alacriti.olx_seller.bo.impl.ProductBO;
import com.alacriti.olx_seller.model.vo.ProductVO;

public class ProductDelegate extends BaseDelegate {
	public ProductDelegate(){
		
	}
	
	public ArrayList<ProductVO> getProducts(){
		
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=new ArrayList<ProductVO>();
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getProducts();
		} catch (Exception e) {
			System.out.println("Exception in getProducts " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
		
	}
}
