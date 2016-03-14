package com.gno.sample.security.social;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialService implements SocialUserDetailsService{
	
	
	private UserDetailsService userDetailsService;
	 
    public SocialService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
 

	@Override
	public SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return (SocialUserDetails) userDetails;
	}
	

}
