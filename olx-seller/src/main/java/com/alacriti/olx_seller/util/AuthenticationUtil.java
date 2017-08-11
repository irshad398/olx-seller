package com.alacriti.olx_seller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import com.alacriti.olx_seller.model.vo.UserLoginVO;

public class AuthenticationUtil {
	public AuthenticationUtil(){
		
	}
	public void createSession(HttpServletRequest request,UserLoginVO userLoginVo){
		try{
			HttpSession session = request.getSession(true);
			session.setAttribute("email", userLoginVo.getEmail());
			System.out.println(session.getAttribute("email"));
			System.out.println("JSession ID: "+session.getId());
		}catch(Exception e){
			System.out.println("Exception in creating session: "+e);
		}
	}
	public boolean checkSession( @Context HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			System.out.println("Session: "+session.getId());
			return true;
		}
		return false;
	}
	public void destroySession(HttpServletRequest request) {
		HttpSession existingSession = request.getSession(false);
		try{
			existingSession.invalidate();
			System.out.println("Logged out successfully...");
		}catch(Exception e){
			System.out.println("Exception in destroying the session: "+e);
		}
		
	}
	
}
