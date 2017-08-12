package com.alacriti.olx_seller.resources;

import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.alacriti.olx_seller.delegate.ProductDelegate;
import com.alacriti.olx_seller.delegate.UserDelegate;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;
import com.alacriti.olx_seller.util.AuthenticationUtil;

@Path("/user")
public class UserResource {
	boolean isValidUser = false;

	@POST
	@Path("login")
	@Produces("application/json")
	public Response checkUser(UserLoginVO userLoginVo,
			@Context HttpServletRequest request) {
		UserDelegate userDelegate = new UserDelegate();
		AuthenticationUtil auth = new AuthenticationUtil();
		
		isValidUser = userDelegate.checkUserLogin(userLoginVo);
		if (isValidUser) {
			auth.createSession(request, userLoginVo);
			return Response.status(200).entity(userLoginVo).build();
		} else
			return Response.status(401).build();

	}

	@GET
	@Path("logout")
	@Produces("application/json")
	public Response logOut(@Context HttpServletRequest request) {
		AuthenticationUtil auth = new AuthenticationUtil();
		auth.destroySession(request);
		return Response.status(200).entity(true).build();

	}

	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public Response registerUser(UserRegisterVO userRegisterVO) {
		UserDelegate userDelegate = new UserDelegate();
		userDelegate.registerUser(userRegisterVO);
		return Response.status(200).entity(userRegisterVO).build();
	}

	@GET
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSellerProducts(@Context HttpServletRequest request) {
		System.out.println("/user/products hitted");
		int seller_id;
		ProductDelegate productDelegate = new ProductDelegate();
		ArrayList<ProductVO> products=null;
		HttpSession session = request.getSession(false);
		System.out.println(session);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			System.out.println("123 " + seller_id);
			products = productDelegate.getSellerProducts(seller_id);
			return Response.status(200).entity(products).build();
		}
		return Response.status(401).entity(products).build();
	}

	@POST
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(ProductVO productVO,
			@Context HttpServletRequest request) {
		AuthenticationUtil auth = new AuthenticationUtil();
		ProductDelegate productDelegate = new ProductDelegate();
		int seller_id;
		HttpSession session = auth.getSession(request);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			productVO.setSeller_id(seller_id);
			productDelegate.addProduct(productVO);
			return Response.status(200).entity(productVO).build();
		}
		return Response.status(200).entity(null).build();
	}

	@DELETE
	@Path("products/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteProduct(@PathParam("product_id") int product_id,
			@Context HttpServletRequest request) {
		AuthenticationUtil auth = new AuthenticationUtil();
		ProductDelegate productDelegate = new ProductDelegate();
		int seller_id;
		HttpSession session = auth.getSession(request);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			return productDelegate.deleteProduct(seller_id, product_id);

		}
		return false;
	}

	@PUT
	@Path("products")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateProduct(ProductVO productVO,
			@Context HttpServletRequest request) {
		AuthenticationUtil auth = new AuthenticationUtil();
		ProductDelegate productDelegate = new ProductDelegate();
		int seller_id;
		int product_id;
		product_id=productVO.getProduct_id();
		HttpSession session = auth.getSession(request);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			productVO.setSeller_id(seller_id);
			productVO.setProduct_id(product_id);
			return productDelegate.updateProduct(productVO, seller_id, product_id);
		}
		// return Response.status(200).entity(product_id).build();
		return false;
	}
}
