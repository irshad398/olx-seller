package com.alacriti.olx_seller.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.olx_seller.delegate.ProductDelegate;
import com.alacriti.olx_seller.model.vo.ProductVO;

@Path("products")
public class ProductResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(){
		ArrayList<ProductVO> products = new ArrayList<ProductVO>();
		ProductDelegate productDelegate = new ProductDelegate();
		products=productDelegate.getProducts();
		return Response.status(200).entity(products).build();
		
	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") int id){
		ProductVO productVO = new ProductVO();
		productVO.setProduct_id(id);
		ProductDelegate productDelegate = new ProductDelegate();
		productDelegate.getProductById(productVO);
		return Response.status(200).entity(productVO).build();
		
	}
	
}
