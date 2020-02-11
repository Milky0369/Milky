package com.milky.candy.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.milky.candy.constant.VacationType;
import com.milky.candy.domain.Holiday;
import com.milky.candy.domain.User;
import com.milky.candy.domain.Vacation;
import com.milky.candy.domain.VacationHistory;
import com.milky.candy.domain.request.ReqVacationDto;
import com.milky.candy.domain.response.ResVacationDto;
import com.milky.candy.repository.UserDbRepository;
import com.milky.candy.repository.VacationDbRepository;
import com.milky.candy.repository.VacationHistoryDbRepository;
import com.milky.candy.service.VacationService;

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
		Vacation updateVacation = new Vacation();
		
		User user = userDbRepository.findByUserId(request.getUserId());
		Vacation selectVacation = vacationDbRepository.findByEmpNum(user.getEmpNum());
		VacationHistory checkVacation =  vacationHistoryDbRepository.findByEmpNumAndCancelFlag(user.getEmpNum(), "N");

		if(user != null && checkVacation == null) {
			double useDay = calcVacation(request.getStartDate(), request.getEndDate(), request.getVacationType());
		
			if(useDay == 0) {
				res.setResultCode("405");
				res.setUseVacation(0);
				res.setRemainingVacation(selectVacation.getVacation());
				return res;
			}
			
			updateVacation.setEmpNum(user.getEmpNum());
			updateVacation.setVacation(selectVacation.getVacation() - useDay);
			updateVacation.setRegDate(selectVacation.getRegDate());
			updateVacation.setUptDate(LocalDateTime.now());
			
			vacationDbRepository.save(updateVacation);
			
			VacationHistory saveVacationHistory = new VacationHistory();
			saveVacationHistory.setEmpNum(user.getEmpNum());
			saveVacationHistory.setCancelFlag("N");
			saveVacationHistory.setStartDate(request.getStartDate());
			saveVacationHistory.setEndDate(request.getEndDate());
			saveVacationHistory.setTimeType(request.getVacationType());
			saveVacationHistory.setRegDate(LocalDateTime.now());
			
			vacationHistoryDbRepository.save(saveVacationHistory);
			
			res.setUseVacation(useDay);
			res.setRemainingVacation(updateVacation.getVacation());
		} else {
			res.setResultCode("401");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		}
		
		return res;
	}
	
	@Override
	public ResVacationDto cancelVacation(ReqVacationDto request) {
		ResVacationDto res = new ResVacationDto();
		Vacation updateVacation = new Vacation();
		
		User user = userDbRepository.findByUserId(request.getUserId());
		Vacation selectVacation = vacationDbRepository.findByEmpNum(user.getEmpNum());
		VacationHistory checkVacation =  vacationHistoryDbRepository.findByEmpNumAndCancelFlag(user.getEmpNum(), "N");
		
		if(user != null && checkVacation != null) {
			double useDay = calcVacation(request.getStartDate(), request.getEndDate(), request.getVacationType());
		
			if(useDay == 0) {
				res.setResultCode("405");
				res.setUseVacation(0);
				res.setRemainingVacation(selectVacation.getVacation());
				return res;
			}
			
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
		} else {
			res.setResultCode("401");
			res.setUseVacation(0);
			res.setRemainingVacation(selectVacation.getVacation());
		}
		
		return res;
	}
	
	public double calcVacation(LocalDateTime startDate, LocalDateTime endDate, String type) {
		
		int startDay = startDate.getDayOfYear();
		int endDay = endDate.getDayOfYear();
		
		LocalDateTime compareDay = null;
		int i = 0;
		double useDay = 0;

		if(endDay > startDay && VacationType.DAY_OFF.getVacationType().equals(type)) {
			while (true) {
				compareDay = endDate.minusDays(i);
				i++;
				
				if(Holiday.isHoliday(compareDay.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) {
					useDay++;
				}
				
				if(compareDay.getDayOfYear() == startDate.getDayOfYear()) {
					break;
				}
			}
		} else if (endDay == startDay && (VacationType.HALF_DAY_OFF_AM.getVacationType().equals(type) || VacationType.HALF_DAY_OFF_PM.getVacationType().equals(type))) {
			if(Holiday.isHoliday(endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) {
				return 0;
			}
			
			useDay = VacationType.HALF_DAY_OFF_AM.getVacationDay();
		} else if (endDay == startDay && (VacationType.HALF_HALF_DAY_OFF_AM.getVacationType().equals(type) || VacationType.HALF_HALF_DAY_OFF_PM.getVacationType().equals(type))) {
			if(Holiday.isHoliday(endDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())) {
				return 0;
			}
			
			useDay = VacationType.HALF_HALF_DAY_OFF_AM.getVacationDay();
		} else {
			return 0;
		}
		
		return useDay;
	}
	
}
