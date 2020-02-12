package com.milky.candy.batch.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.milky.candy.batch.service.VacationBatchService;
import com.milky.candy.domain.VacationHistory;
import com.milky.candy.repository.VacationHistoryDbRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacationHistoryBatchServiceImpl implements VacationBatchService<VacationHistory> {
	
	final VacationHistoryDbRepository vacationHistoryDbRepository;
	
	@Override
	public List<VacationHistory> bulkVacationList(List<VacationHistory> list) {
		return vacationHistoryDbRepository.saveAll(list);
	}

	@Override
	public VacationHistory insertVacationList(VacationHistory vacation) {
		return vacationHistoryDbRepository.save(vacation);
	}
	
}
