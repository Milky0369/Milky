package com.milky.candy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milky.candy.domain.User;
import com.milky.candy.domain.request.ReqLoginDto;
import com.milky.candy.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {
	
	final UserService userService;
	
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }
    
	// 로그인 세션처리 필요. -> Oauth2 토큰발급
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public Map<String, Object> requestLogin(@RequestBody ReqLoginDto param) {
    	
    	Map<String, Object> resMap = new HashMap<String, Object>();
    	
    	User user = userService.loginUser(param);
    	
    	if(user != null) {
    		resMap.put("result", "login Success.");
    	} else {
    		resMap.put("result", "login Fail.");
    	}
    	
    	return resMap;
    }
    
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public List<User> users() {
		
		List<User> resList = userService.selectUser();
		
		return resList;
	}
	
}
