package com.milky.candy.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VacationApiController {

    @RequestMapping(value="/vacation", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String requestVacation(@RequestBody String param) {
    	
    	return null;
    }
    
}
