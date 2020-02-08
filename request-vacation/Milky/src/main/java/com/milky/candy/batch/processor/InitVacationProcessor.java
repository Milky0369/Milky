package com.milky.candy.batch.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.milky.candy.domain.Vacation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
public class InitVacationProcessor implements ItemProcessor<Vacation, Vacation>{
	
	@Override
	public Vacation process(Vacation item) throws Exception {
		
		item.setVacation(15);
		
		return item;
	}

}
