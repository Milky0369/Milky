package com.milky.candy.service;

import java.util.List;

import com.milky.candy.domain.User;
import com.milky.candy.domain.request.ReqLoginDto;

public interface UserService {
	User loginUser(ReqLoginDto user);
	List<User> selectUser();
}
