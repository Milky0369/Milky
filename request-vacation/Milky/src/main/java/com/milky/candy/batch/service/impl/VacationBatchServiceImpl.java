package com.milky.candy.batch.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.milky.candy.batch.service.VacationBatchService;
import com.milky.candy.domain.Vacation;
import com.milky.candy.repository.VacationDbRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacationBatchServiceImpl implements VacationBatchService<Vacation> {

	final VacationDbRepository vacationDbRepository;
	
	@Override
	public List<Vacation> bulkVacationList(List<Vacation> list) {
		return vacationDbRepository.saveAll(list);
	}

	@Override
	public Vacation insertVacationList(Vacation vacation) {
		return vacationDbRepository.save(vacation);
	}

}
