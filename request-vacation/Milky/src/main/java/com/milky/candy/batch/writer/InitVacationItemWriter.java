package com.milky.candy.batch.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import com.milky.candy.batch.service.VacationBatchService;
import com.milky.candy.domain.Vacation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
public class InitVacationItemWriter extends AbstractVacationWriter<Vacation> {

	public InitVacationItemWriter(VacationBatchService<Vacation> vacationBatchService) {
		super(vacationBatchService);
	}

}
