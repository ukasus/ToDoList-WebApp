package com.ujjawal.project.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ujjawal.project.entity.UserCredentials;
import com.ujjawal.project.repo.UserRepository;
import org.springframework.security.core.userdetails.User;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentials> userop=userRepository.findById(username);
		UserCredentials userc=userop.get();
		return new User(userc.getEmail(),userc.getPassword(),new ArrayList<>());
		
		
		
	}
	
	public String getUserNamefromPrincipal(String email)
	{
		Optional<UserCredentials> userop=userRepository.findById(email);
		UserCredentials userc=userop.get();
		return (userc.getFirstName()+" "+userc.getLastName());
	}

	

}
