package com.milky.candy.batch.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.milky.candy.domain.VacationHistory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
public class UseVacationProcessor implements ItemProcessor<VacationHistory, VacationHistory>{

	@Override
	public VacationHistory process(VacationHistory item) throws Exception {
		
		item.setUseFlag("Y");
		
		return item;
	}
	
}
