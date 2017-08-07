package com.alacriti.olx_seller.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.alacriti.olx_seller.delegate.UserDelegate;
import com.alacriti.olx_seller.model.vo.UserLoginVO;
import com.alacriti.olx_seller.model.vo.UserRegisterVO;


@Path("/user")
public class UserResource {
	public static boolean validUser=false;
	@POST
	@Path("login")
	@Produces("application/json")
	
	public Response checkUser(UserLoginVO userLoginVo){
		UserDelegate userDelegate= new UserDelegate();
		userDelegate.checkUserLogin(userLoginVo);
		if(validUser)
			return Response.status(200).entity(userLoginVo).build();
		else 
			return Response.status(401).entity(userLoginVo).build();
			
	}
	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public Response registerUser(UserRegisterVO userRegisterVO){
		UserDelegate userDelegate= new UserDelegate();
		userDelegate.registerUser(userRegisterVO);
		return Response.status(200).entity(userRegisterVO).build();
	}
}
