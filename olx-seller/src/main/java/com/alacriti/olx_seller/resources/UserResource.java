package com.alacriti.olx_seller.resources;

import java.util.ArrayList;

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

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.delegate.ProductDelegate;
import com.alacriti.olx_seller.delegate.UserDelegate;
import com.alacriti.olx_seller.model.vo.ProductVO;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;
import com.alacriti.olx_seller.util.AuthenticationUtil;

@Path("/user")
public class UserResource {
	private static final Logger log = Logger.getLogger(UserResource.class);
	boolean isValidUser = false;

	@POST
	@Path("login")
	@Produces("application/json")
	public Response checkUser(UserLoginVO userLoginVo,
			@Context HttpServletRequest request) {
		log.debug("userLoginVo.email: "+userLoginVo.getEmail());
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
	public boolean logOut(@Context HttpServletRequest request) {
		log.debug("Logging..out  ");
		AuthenticationUtil auth = new AuthenticationUtil();
		return auth.destroySession(request);

	}

	@GET
	@Path("check")
	@Produces("application/json")
	public Response checkSession(@Context HttpServletRequest request) {
		AuthenticationUtil auth = new AuthenticationUtil();
		HttpSession session = auth.getSession(request);
		log.debug("Checking session...");
		if (session != null) {
			UserLoginVO userLoginVo = new UserLoginVO();
			log.info("Session exists...for seller_id: "+session.getAttribute("seller_id"));
			userLoginVo.setEmail((String) session.getAttribute("email"));
			userLoginVo.setSeller_id((Integer) session
					.getAttribute("seller_id"));
			userLoginVo.setSeller_name((String) session
					.getAttribute("seller_name"));
			return Response.status(200).entity(userLoginVo).build();
		}
		return Response.status(401).build();

	}

	@POST
	@Path("register")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean registerUser(UserRegisterVO userRegisterVO) {
		log.debug("Registering user..."+userRegisterVO.getEmail());
		UserDelegate userDelegate = new UserDelegate();
		return userDelegate.registerUser(userRegisterVO);
	}

	@GET
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSellerProducts(@Context HttpServletRequest request) {
		log.info("Getting user products..");
		int seller_id;
		ProductDelegate productDelegate = new ProductDelegate();
		ArrayList<ProductVO> products = null;
		AuthenticationUtil auth = new AuthenticationUtil();
		HttpSession session = auth.getSession(request);
		if (session != null) {
			log.debug("Products of seller_id:"+session.getAttribute("seller_id"));
			seller_id = (Integer) session.getAttribute("seller_id");
			products = productDelegate.getSellerProducts(seller_id);
			return Response.status(200).entity(products).build();
		}
		return Response.status(401).entity(products).build();
	}

	@POST
	@Path("products")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addProduct(ProductVO productVO,
			@Context HttpServletRequest request) {
		log.debug("***Adding product.. product_title: "+productVO.getTitle());
		AuthenticationUtil auth = new AuthenticationUtil();
		ProductDelegate productDelegate = new ProductDelegate();
		int seller_id;
		HttpSession session = auth.getSession(request);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			productVO.setSeller_id(seller_id);
			return productDelegate.addProduct(productVO);

		}
		return false;
	}

	@DELETE
	@Path("products/{product_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteProduct(@PathParam("product_id") int product_id,
			@Context HttpServletRequest request) {
		log.debug("***Deleting product..product_id: "+product_id);
		System.out.println("Delete Product method called with product_id: "
				+ product_id);
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
		log.debug("***Updating product..product_id: "+productVO.getProduct_id());
		AuthenticationUtil auth = new AuthenticationUtil();
		ProductDelegate productDelegate = new ProductDelegate();
		int seller_id;
		int product_id;
		product_id = productVO.getProduct_id();
		HttpSession session = auth.getSession(request);
		if (session != null) {
			seller_id = (Integer) session.getAttribute("seller_id");
			productVO.setSeller_id(seller_id);
			productVO.setProduct_id(product_id);
			return productDelegate.updateProduct(productVO, seller_id,
					product_id);
		}
		return false;
	}
}
