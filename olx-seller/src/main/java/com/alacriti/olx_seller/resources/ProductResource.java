package com.alacriti.olx_seller.resources;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.olx_seller.delegate.CategoryDelegate;
import com.alacriti.olx_seller.delegate.ProductDelegate;
import com.alacriti.olx_seller.model.vo.CategoryVO;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.SearchProdutVO;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
	@GET
	public Response getProducts(@Context HttpServletRequest request){
			ArrayList<ProductVO> products;
			ProductDelegate productDelegate = new ProductDelegate();
			products=productDelegate.getProducts();
			return Response.status(200).entity(products).build();
		
	}
	@GET
	@Path("{id}")
	public Response getProductById(@PathParam("id") int id, @Context HttpServletRequest request){
		ProductVO productVO = new ProductVO();
		productVO.setProduct_id(id);
		ProductDelegate productDelegate = new ProductDelegate();
		productDelegate.getProductById(productVO);
		return Response.status(200).entity(productVO).build();
		
	}
	@GET
	@Path("cat/{category_id}")
	public Response getProductByCategory(@PathParam("category_id") int category_id, @Context HttpServletRequest request){
		ArrayList<ProductVO> products;
		ProductDelegate productDelegate = new ProductDelegate();
		products=productDelegate.getProductByCategory(category_id);
		return Response.status(200).entity(products).build();
		
	}
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProdctsBySearch(SearchProdutVO searchProdutVO){
		System.out.println(searchProdutVO.getTitle()+": "+searchProdutVO.getCatId());
		ArrayList<ProductVO> products=null;
		ProductDelegate productDelegate = new ProductDelegate();
		products=productDelegate.getProducts(searchProdutVO);
		return Response.status(200).entity(products).build();
	}
	@GET
	@Path("/categories")
	public Response getCategories(){
		ArrayList<CategoryVO> categories;
		CategoryDelegate categoryDelegate = new CategoryDelegate();
		categories=categoryDelegate.getCategories();
		return Response.status(200).entity(categories).build();
	}
	@GET
	@Path("/recent")
	public Response getRecentProducts(@Context HttpServletRequest request){
			ArrayList<ProductVO> products;
			ProductDelegate productDelegate = new ProductDelegate();
			products=productDelegate.getRecentProducts();
			return Response.status(200).entity(products).build();
		
	}
	
}
