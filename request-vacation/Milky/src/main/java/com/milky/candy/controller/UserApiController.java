package com.milky.candy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserApiController {
	
    @RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String requestLogin(@RequestBody String param) { // user 객체로 RequestBody 매핑
    	
    	// 유저 찾기 서비스 로직
    	
    	return null;
    }
    
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Map<String, Object> users() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// user select service logic

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		
		return map;
	}
	
}
