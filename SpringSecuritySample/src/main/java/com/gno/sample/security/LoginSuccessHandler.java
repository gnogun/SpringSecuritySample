package com.gno.sample.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println(auth.getAuthorities().getClass().getName());
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
		String strAuth = authorities.get(0).getAuthority();
		
		Cookie cookie = new Cookie("auth", strAuth);
		cookie.setPath("/");
		// ���߿� ������Ƽ�� ����
//		cookie.setDomain("test.com");
		response.addCookie(cookie);
		
		if(strAuth.equals("ROLE_ADMIN")){
			response.sendRedirect(request.getContextPath() +  "/admin.do");
		}else{
			response.sendRedirect(request.getContextPath() +  "/user.do");
		}
		
		
	}

}
