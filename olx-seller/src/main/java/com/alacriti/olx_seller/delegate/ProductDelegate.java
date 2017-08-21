package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.bo.impl.ProductBO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public class ProductDelegate extends BaseDelegate {
	private static final Logger log = Logger.getLogger(ProductDelegate.class);
	
	public ArrayList<ProductVO> getProducts(){
		log.info("In Product delegate*******getProducts");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getProducts();
		} catch (Exception e) {
			log.error("Exception in getProducts " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
		
	}

	public void getProductById(ProductVO productVO) {
		log.info("In Product delegate*******getProductById");
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			productBO.getProductById(productVO);
		}
		catch(Exception e){
			log.error("Exception in getProductById " + e.getMessage(),e);
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public ArrayList<ProductVO> getProductByCategory(int category_id) {
		log.info("In Product delegate*******getProductByCategory");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getProductByCategory(category_id);
		} catch (Exception e) {
			log.error("Exception in getProductByCategory " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
		
	}

	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO) {
		log.info("In Product delegate*******getProducts");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getProducts(searchProdutVO);
		} catch (Exception e) {
			log.error("Exception in getProducts " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id) {
		log.info("In Product delegate*******getSellerProducts");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getSellerProducts(seller_id);
		} catch (Exception e) {
			log.error("Exception in getSellerProducts " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
	}

	public boolean addProduct(ProductVO productVO) {
		log.info("In Product delegate*******addProduct");
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.addProduct(productVO);
		}
		catch(Exception e){
			log.error("Exception in addProduct " + e.getMessage(),e);
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}

	public boolean deleteProduct(int seller_id, int product_id) {
		log.info("In Product delegate*******deleteProduct");
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.deleteProduct(seller_id,product_id);
		}
		catch(Exception e){
			log.error("Exception in deleteProduct " + e.getMessage(),e);
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}

	public boolean updateProduct(ProductVO productVO, int seller_id, int product_id) {
		log.info("In Product delegate*******updateProduct");
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.updateProduct(productVO, seller_id,product_id);
		}
		catch(Exception e){
			log.error("Exception in deleteProduct " + e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}

	public ArrayList<ProductVO> getRecentProducts() {
		log.info("In Product delegate*******getRecentProducts");
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getRecentProducts();
		} catch (Exception e) {
			log.error("Exception in getRecentProducts " + e.getMessage(),e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
		
	}

}
