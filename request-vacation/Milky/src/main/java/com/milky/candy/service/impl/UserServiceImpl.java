package com.milky.candy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milky.candy.domain.User;
import com.milky.candy.domain.request.ReqLoginDto;
import com.milky.candy.repository.UserDbRepository;
import com.milky.candy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	final UserDbRepository userDbRepository;
	
	@Override
	public User loginUser(ReqLoginDto user) {
		return userDbRepository.findByUserIdAndUserPwd(user.getUserId(), user.getUserPwd());
	}

	@Override
	public List<User> selectUser() {
		return userDbRepository.findAll();
	}

}
