package com.gno.sample.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gno.sample.dto.Person;
import com.gno.sample.repository.PersonRepository;
import com.gno.sample.security.social.CustomSocialUserDetail;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private PersonRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String id)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		ArrayList<Person> person = (ArrayList<Person>) repo.findById(id);
		
		if(person.size() == 0){
			throw new UsernameNotFoundException(id);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority(person.get(0).getAuth()));
        
        CustomUserDetail customUserDetail = new CustomUserDetail(person.get(0));
		
		//userDetail을 재정의 없이 Spring security default로 사용할때
		return new CustomSocialUserDetail(customUserDetail.getUsername(), customUserDetail.getPassword(), customUserDetail.getAuthorities());
		
//		return new CustomUserDetail(person.get(0));
	}
	
	
	
	

}
