package com.alacriti.olx_seller.delegate;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.alacriti.olx_seller.bo.impl.ProductBO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

public class ProductDelegate extends BaseDelegate {
	public ProductDelegate(){
		
	}
	
	public ArrayList<ProductVO> getProducts(){
		
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
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

	public void getProductById(ProductVO productVO) {
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			productBO.getProductById(productVO);
		}
		catch(Exception e){
			System.out.println("Exception in getProductById " + e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
	}


	public ArrayList<ProductVO> getProducts(SearchProdutVO searchProdutVO) {
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getProducts(searchProdutVO);
		} catch (Exception e) {
			System.out.println("Exception in getProducts " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
	}

	public ArrayList<ProductVO> getSellerProducts(int seller_id) {
		boolean rollBack = false;
		Connection connection = null;
		ArrayList<ProductVO> products=null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			products=productBO.getSellerProducts(seller_id);
		} catch (Exception e) {
			System.out.println("Exception in getProducts " + e.getMessage());
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return products;
	}

	public boolean addProduct(ProductVO productVO) {
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.addProduct(productVO);
		}
		catch(Exception e){
			System.out.println("Exception in addProduct " + e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}

	public boolean deleteProduct(int seller_id, int product_id) {
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.deleteProduct(seller_id,product_id);
		}
		catch(Exception e){
			System.out.println("Exception in deleteProduct " + e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}

	public boolean updateProduct(ProductVO productVO, int seller_id, int product_id) {
		boolean rollBack=false;
		Connection connection = null;
		try{
			connection = startDBTransaction();
			setConnection(connection);
			ProductBO productBO = new ProductBO(getConnection());
			return productBO.updateProduct(productVO, seller_id,product_id);
		}
		catch(Exception e){
			System.out.println("Exception in deleteProduct " + e.getMessage());
			rollBack = true;
		}
		finally {
			endDBTransaction(connection, rollBack);
		}
		return false;
	}
}
