package com.basavaLearing.jornalApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.UserRepository;

@Component 
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.basavaLearing.jornalApp.Entity.User user =userRepository.findByUserName(username);
		//UserDetails userDetails=null;
		if(user ==null) {
			throw new UsernameNotFoundException("user not found : "+username);
		}
		System.out.println("✅ User found: " + user.getUserName());
        System.out.println("🔐 Encoded password: " + user.getPassword());
        System.out.println("👥 Roles in DB: " + user.getRoles());
		
		 UserDetails userDetails =User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles(user.getRoles().toArray(new String[0]))
				.build();
		 System.out.println("userDetails : " + userDetails.toString());
		 return userDetails;
		
	}

}
