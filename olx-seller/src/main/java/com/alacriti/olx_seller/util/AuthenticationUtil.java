package com.alacriti.olx_seller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.alacriti.olx_seller.model.vo.UserLoginVO;

public class AuthenticationUtil {
	private static final Logger log  = Logger.getLogger(AuthenticationUtil.class);
	public void createSession(HttpServletRequest request,UserLoginVO userLoginVo){
		try{
			HttpSession session = request.getSession(true);
			session.setAttribute("email", userLoginVo.getEmail());
			session.setAttribute("seller_id", userLoginVo.getSeller_id());
			session.setAttribute("seller_name", userLoginVo.getSeller_name());
			log.debug("**seller_id: "+session.getAttribute("seller_id"));
			log.debug("JSession ID: "+session.getId());
		}catch(Exception e){
			log.error("Exception in creating session: "+e);
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
			log.info("Logged out successfully...");
			return true;
		}catch(Exception e){
			log.error("Exception in destroying the session: "+e);
			return false;
		}
		
	}
	
}
