package com.gno.sample.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	 
   private final Log logger = LogFactory.getLog(this.getClass());
 
   @Override
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return super.authenticate(arg0);
	}
}