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
			destroySession(request);
			HttpSession session = request.getSession(true);
			session.setAttribute("email", userLoginVo.getEmail());
			session.setAttribute("seller_id", userLoginVo.getSeller_id());
			session.setAttribute("seller_name", userLoginVo.getSeller_name());
			System.out.println("seller_id: "+session.getAttribute("seller_id"));
			System.out.println("JSession ID: "+session.getId());
		}catch(Exception e){
			System.out.println("Exception in creating session: "+e);
		}
	}
	public HttpSession getSession( @Context HttpServletRequest request){
		HttpSession session = request.getSession(false);
		return session;
	}
	public boolean destroySession(HttpServletRequest request) {
		HttpSession existingSession = request.getSession(false);
		try{
			existingSession.invalidate();
			System.out.println("Logged out successfully...");
			return true;
		}catch(Exception e){
			System.out.println("Exception in destroying the session: "+e);
			return false;
		}
		
	}
	
}
