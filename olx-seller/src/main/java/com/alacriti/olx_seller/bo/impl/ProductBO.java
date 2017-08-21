package com.alacriti.olx_seller.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.IProductBO;
import com.alacriti.olx_seller.dao.impl.DAOException;
import com.alacriti.olx_seller.dao.impl.ProductDAO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public class ProductBO extends BaseBO implements IProductBO {
	private static final Logger log = Logger.getLogger(ProductBO.class);

	public ProductBO(Connection connection) {
		super(connection);
	}

	public ArrayList<ProductVO> getProducts() throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getProducts();

		} catch (Exception e) {
			log.error("Exception in retrieveProducts "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return products;
	}

	public void getProductById(ProductVO productVO) throws DAOException,
			BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			productDAO.getProductById(productVO);
		} catch (Exception e) {
			log.error("Exception in retrieveProductById "
					+ e.getMessage(),e);
			throw new BOException();
		}
	}

	public ArrayList<ProductVO> getProductByCategory(int category_id)
			throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = null;

		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getProductByCategory(category_id);

		} catch (Exception e) {
			log.error("Exception in getProductByCategory in BO "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return products;
	}

	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO)
			throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getProducts(searchProdutVO);

		} catch (Exception e) {
			log.error("Exception in retrieveProducts "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return products;
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id)
			throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getSellerProducts(seller_id);

		} catch (Exception e) {
			log.error("Exception in retrieve getSellerProducts "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return products;
	}

	public boolean addProduct(ProductVO productVO) throws DAOException,
			BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			return productDAO.addProduct(productVO);
		} catch (Exception e) {
			log.error("Exception in retrieveAddProduct "
					+ e.getMessage(),e);
			throw new BOException();
		}
	}

	public boolean deleteProduct(int seller_id, int product_id)
			throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			return productDAO.deleteProduct(seller_id, product_id);
		} catch (Exception e) {
			log.error("Exception in retrieveDeleteProduct "
					+ e.getMessage(),e);
			throw new BOException();
		}
	}

	public boolean updateProduct(ProductVO productVO, int seller_id,
			int product_id) throws DAOException, BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			return productDAO.updateProduct(productVO, seller_id, product_id);
		} catch (Exception e) {
			log.error("Exception in retrieveDeleteProduct "
					+ e.getMessage(),e);
			throw new BOException();
		}
	}

	public ArrayList<ProductVO> getRecentProducts() throws DAOException,
			BOException {
		log.debug("In "
				+ Thread.currentThread().getStackTrace()[2].getMethodName());
		ArrayList<ProductVO> products = null;
		try {
			ProductDAO productDAO = new ProductDAO(getConnection());
			products = productDAO.getRecentProducts();

		} catch (Exception e) {
			log.error("Exception in retrieveRecentProducts "
					+ e.getMessage(),e);
			throw new BOException();
		}
		return products;
	}

}
