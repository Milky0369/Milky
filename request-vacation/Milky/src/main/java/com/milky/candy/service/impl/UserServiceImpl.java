package com.milky.candy.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.milky.candy.domain.User;
import com.milky.candy.domain.request.ReqLoginDto;
import com.milky.candy.repository.UserDbRepository;
import com.milky.candy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
//public class UserServiceImpl implements UserService {
	
	private final UserDbRepository userDbRepository;
	
	@Override
	public User loginUser(ReqLoginDto user) {
		return userDbRepository.findByUserIdAndUserPwd(user.getUserId(), user.getUserPwd());
	}

	@Override
	public List<User> selectUser() {
		return userDbRepository.findAll();
	}

	@Override
	public User readUser(String username) {
		return userDbRepository.findByUserId(username);
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDbRepository.findByUserId(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPwd(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public User save(User user) {
		return userDbRepository.save(user);
	}
	
}
