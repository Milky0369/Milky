package com.milky.candy.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.milky.candy.domain.User;
import com.milky.candy.domain.Vacation;
import com.milky.candy.domain.VacationHistory;
import com.milky.candy.domain.request.ReqVacationDto;
import com.milky.candy.domain.response.ResVacationDto;
import com.milky.candy.repository.UserDbRepository;
import com.milky.candy.repository.VacationDbRepository;
import com.milky.candy.repository.VacationHistoryDbRepository;
import com.milky.candy.service.VacationService;
import com.milky.candy.util.Holiday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

	final UserDbRepository userDbRepository;
	final VacationDbRepository vacationDbRepository;
	final VacationHistoryDbRepository vacationHistoryDbRepository;
	
	@Override
	public ResVacationDto useVacation(ReqVacationDto request) {
		ResVacationDto res = new ResVacationDto();
		
		String startDate = null;
		String endDate = null;
		
		User user = new User();
		Vacation selectVacation = new Vacation();
		VacationHistory checkVacation = new VacationHistory();
		
		double useDay = 0;
		
		try {
			startDate = calcDate(request.getStartDate());
			endDate = calcDate(request.getEndDate());
			
			user = userDbRepository.findByUserId(request.getUserId());
			selectVacation = vacationDbRepository.findByEmpNum(user.getEmpNum());
			checkVacation =  vacationHistoryDbRepository.selectHistory(user.getEmpNum(), startDate, endDate);
	
			useDay = Holiday.calcVacation(request.getStartDate(), request.getEndDate(), request.getVacationType());
		} catch (Exception e) {
			log.error("Exception : {}", e);
			
			res.setResultCode("500");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
			
			return res;
		}
		
		if(user != null && checkVacation == null && useDay > 0 && 
							(selectVacation.getVacation() > 0 || selectVacation.getVacation() - useDay > 0)) {
			Vacation updateVacation = new Vacation();
			
			updateVacation.setEmpNum(user.getEmpNum());
			updateVacation.setVacation(selectVacation.getVacation() - useDay);
			updateVacation.setRegDate(selectVacation.getRegDate());
			updateVacation.setUptDate(LocalDateTime.now());
			
			vacationDbRepository.save(updateVacation);
			
			VacationHistory saveVacationHistory = new VacationHistory();
			
			saveVacationHistory.setEmpNum(user.getEmpNum());
			saveVacationHistory.setUseFlag("N");
			saveVacationHistory.setCancelFlag("N");
			saveVacationHistory.setStartDate(startDate);
			saveVacationHistory.setEndDate(endDate);
			saveVacationHistory.setTimeType(request.getVacationType());
			saveVacationHistory.setRegDate(LocalDateTime.now());
			
			vacationHistoryDbRepository.save(saveVacationHistory);
		
			res.setResultCode("200");
			res.setUseVacation(useDay);
			res.setRemainingVacation(updateVacation.getVacation());
		} else if(useDay == 0) {
			log.error("Invalid date. startDate : {}, endDate : {}, vacationType : {}", startDate, endDate, request.getVacationType());
			
			res.setResultCode("409");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		} else if(checkVacation != null) {
			log.error("Already regist vacation. Vacation : {}", checkVacation);
			
			res.setResultCode("409");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		} else if(selectVacation.getVacation() <= 0 || selectVacation.getVacation() - useDay <= 0) {
			log.error("Can not use Vacation. Vacation : {}", selectVacation);
			
			res.setResultCode("409");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		} else {
			log.error("unknown user. userId : {}", request.getUserId());
			
			res.setResultCode("404");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		}
		
		return res;
	}
	
	@Override
	public ResVacationDto cancelVacation(ReqVacationDto request) {
		ResVacationDto res = new ResVacationDto();
		
		String startDate = null;
		String endDate = null;
		
		User user = new User();
		Vacation selectVacation = new Vacation();
		VacationHistory checkVacation = new VacationHistory();
		
		double useDay = 0;
		
		try {
			startDate = calcDate(request.getStartDate());
			endDate = calcDate(request.getEndDate());

			user = userDbRepository.findByUserId(request.getUserId());
			selectVacation = vacationDbRepository.findByEmpNum(user.getEmpNum());
			checkVacation =  vacationHistoryDbRepository.selectHistory(user.getEmpNum(), startDate, endDate);
	
			useDay = Holiday.calcVacation(request.getStartDate(), request.getEndDate(), request.getVacationType());
		} catch (Exception e) {
			log.error("Exception : {}", e);
			
			res.setResultCode("500");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
			
			return res;
		}
		
		if(user != null && checkVacation != null && useDay > 0) {
			Vacation updateVacation = new Vacation();
			
			updateVacation.setEmpNum(user.getEmpNum());
			updateVacation.setVacation(selectVacation.getVacation() + useDay);
			updateVacation.setRegDate(selectVacation.getRegDate());
			updateVacation.setUptDate(LocalDateTime.now());
			
			vacationDbRepository.save(updateVacation);
			
			checkVacation.setCancelFlag("Y");
			
			vacationHistoryDbRepository.save(checkVacation);
		
			res.setResultCode("200");
			res.setUseVacation(useDay);
			res.setRemainingVacation(updateVacation.getVacation());
		} else if(useDay == 0) {
			log.error("Invalid date. startDate : {}, endDate : {}", startDate, endDate);
			
			res.setResultCode("405");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		} else if(checkVacation != null) {
			log.error("Vacation Data not found. Vacation : {}", checkVacation);
			
			res.setResultCode("401");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		} else {
			log.error("unknown user. userId : {}", request.getUserId());
			
			res.setResultCode("401");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		}
		
		return res;
	}
	
	public String calcDate(LocalDateTime param) {
		
		int year = param.getYear();
		int month = param.getMonthValue();
		int day = param.getDayOfMonth();
		
		String result = null;
		
		if(month < 10) {
			result = "" + year + "0" + month + day;
		} else {
			result = "" + year + month + day; 
		}
		
		return result;
	}
	
}
