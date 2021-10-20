package com.example.demo.service.impl;

import java.util.Arrays;
import java.util.Collection;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private static List<String> roleList = Arrays.asList(new String[] { "ROLE_DOCTOR", "ROLE_USER" });
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		User user = userService.findByEmail(username);
		User user = createUser(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

		String[] userRoles = user.getRoles().stream().toArray(String[]::new);

		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);

		return authorities;

	}

	private User createUser(String userName) {
		User user = null;
		if ("admin@gmail.com".equalsIgnoreCase(userName)) {
			user = new User();
			user.setEmail(userName);
			user.setPassword("admin");
			user.setRoles(roleList);
			user.setAddress("addressLine1");
		}
		return user;
	}
	
}