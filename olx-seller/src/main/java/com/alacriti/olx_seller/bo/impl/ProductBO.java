package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.dao.impl.ProductDAO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public class ProductBO extends BaseBO {

	public ProductBO(Connection connection) {
		super(connection);
	}

	public ArrayList<ProductVO> getProducts() throws DAOException, BOException {
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getProducts();

		} catch (Exception e) {
			System.out.println("Exception in retrieveProducts "
					+ e.getMessage());
			throw new BOException();
		}
		return products;
	}

	public void getProductById(ProductVO productVO) throws DAOException,
			BOException {
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			productDAO.getProductById(productVO);
		} catch (Exception e) {
			System.out.println("Exception in retrieveProductById "
					+ e.getMessage());
			throw new BOException();
		}
	}

	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException, BOException {

		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getProducts(searchProdutVO);

		} catch (Exception e) {
			System.out.println("Exception in retrieveProducts "
					+ e.getMessage());
			throw new BOException();
		}
		return products;
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id)
			throws DAOException, BOException {
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getSellerProducts(seller_id);

		} catch (Exception e) {
			System.out.println("Exception in retrieveProducts "
					+ e.getMessage());
			throw new BOException();
		}
		return products;
	}

	public void addProduct(ProductVO productVO) throws DAOException,
			BOException {
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			productDAO.addProduct(productVO);
		} catch (Exception e) {
			System.out.println("Exception in retrieveAddProduct "
					+ e.getMessage());
			throw new BOException();
		}
	}
}
