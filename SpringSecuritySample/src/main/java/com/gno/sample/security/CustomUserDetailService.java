package com.gno.sample.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gno.sample.dto.Person;
import com.gno.sample.repository.PersonRepository;

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
		//userDetail을 default로 사용할때
//		return new User(person.get(0).getId(), person.get(0).getPassword(), "ROLE_USER");
		
		return new CustomUserDetail(person.get(0));
	}
	
	
	
	

}
