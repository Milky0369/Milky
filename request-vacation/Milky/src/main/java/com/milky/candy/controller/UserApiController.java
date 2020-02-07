package com.milky.candy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milky.candy.domain.User;
import com.milky.candy.domain.request.ReqLoginDto;
import com.milky.candy.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;
	
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public Map<String, Object> requestLogin(@RequestBody ReqLoginDto param) { // user 객체로 RequestBody 매핑
    	
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
