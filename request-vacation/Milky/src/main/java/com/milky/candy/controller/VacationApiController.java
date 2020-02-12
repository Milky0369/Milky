package com.milky.candy.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milky.candy.domain.request.ReqVacationDto;
import com.milky.candy.domain.response.ResVacationDto;
import com.milky.candy.service.VacationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VacationApiController {

	final VacationService vacationService;
	
    @RequestMapping(value="/vacation", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    public ResVacationDto requestVacation(@RequestBody ReqVacationDto param) {
    	
    	ResVacationDto res = vacationService.useVacation(param);
    	
    	return res;
    }
    
    @RequestMapping(value="/vacation", method=RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public ResVacationDto requestCancelVacation(@RequestBody ReqVacationDto param) {
    	
    	ResVacationDto res = vacationService.cancelVacation(param);
    	
    	return res;
    }
    
}
