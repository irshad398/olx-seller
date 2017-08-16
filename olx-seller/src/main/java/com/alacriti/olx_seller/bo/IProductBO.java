package com.alacriti.olx_seller.bo;

import java.util.ArrayList;

import com.alacriti.olx_seller.bo.impl.BOException;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public interface IProductBO {
	ArrayList<ProductVO> getProducts() throws DAOException, BOException;

	void getProductById(ProductVO productVO) throws DAOException, BOException;

	ArrayList<ProductVO> getProductByCategory(int category_id)
			throws DAOException, BOException;

	ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException, BOException;

	ArrayList<ProductVO> getSellerProducts(int seller_id) throws DAOException,
			BOException;

	boolean addProduct(ProductVO productVO) throws DAOException, BOException;

	boolean deleteProduct(int seller_id, int product_id) throws DAOException,
			BOException;

	boolean updateProduct(ProductVO productVO, int seller_id, int product_id)
			throws DAOException, BOException;
	ArrayList<ProductVO> getRecentProducts() throws DAOException, BOException;
}
