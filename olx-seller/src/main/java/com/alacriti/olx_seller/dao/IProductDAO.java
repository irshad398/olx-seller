package com.alacriti.olx_seller.dao;

import java.util.ArrayList;

import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public interface IProductDAO {
	ArrayList<ProductVO> getProducts() throws DAOException;
	void getProductById(ProductVO productVO) throws DAOException;
	ArrayList<ProductVO> getProductByCategory(int category_id)
			throws DAOException;
	ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException;
	ArrayList<ProductVO> getSellerProducts(int seller_id)
			throws DAOException;
	boolean addProduct(ProductVO productVO) throws DAOException;
	boolean deleteProduct(int seller_id, int product_id)
			throws DAOException;
	boolean updateProduct(ProductVO productVO, int seller_id,
			int product_id) throws DAOException;
	ArrayList<ProductVO> getRecentProducts() throws DAOException;
	
}
