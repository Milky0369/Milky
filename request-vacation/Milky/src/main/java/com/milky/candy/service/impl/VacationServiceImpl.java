package com.milky.candy.service.impl;

import org.springframework.stereotype.Service;

import com.milky.candy.domain.Vacation;
import com.milky.candy.domain.request.ReqVacationDto;
import com.milky.candy.repository.UserDbRepository;
import com.milky.candy.repository.VacationDbRepository;
import com.milky.candy.service.VacationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

	final VacationDbRepository vacationDbRepository;
	
	@Override
	public Vacation cunsumeVacation(ReqVacationDto request) {
		
		return null;
	}

}
