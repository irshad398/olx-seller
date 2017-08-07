package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.dao.impl.ProductDAO;
import com.alacriti.olx_seller.model.vo.ProductVO;


public class ProductBO extends BaseBO{

	public ProductBO(Connection connection){
		super(connection);
	}

	public ArrayList<ProductVO> getProducts() throws DAOException, BOException{
		ArrayList<ProductVO> products=null;
		try {
			ProductDAO productDAO =   new ProductDAO(getConnection());
			products= productDAO.getProducts();
			
		} catch (Exception e) {
			System.out.println("Exception in retrieveProducts " + e.getMessage());
			throw new BOException();
		}
		return products;
	}
}
