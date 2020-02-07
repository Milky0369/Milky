package com.milky.candy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommonApiController {
	
	@RequestMapping(value="/ping", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public Map<String, Object> ping() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("time", new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date()));

		try {
			map.put("msg", "Milky is ready.");
			log.info(String.format("[ping] [resultMsg: %s]", map.get("msg")));
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			log.error(String.format("[ping] [resultMsg: %s]", map.get("msg")));
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Access-Control-Allow-Origin", "*");
		
		return map;
	}
	
}
