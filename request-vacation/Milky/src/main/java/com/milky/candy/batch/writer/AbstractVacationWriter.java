package com.milky.candy.batch.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;

import com.milky.candy.batch.service.VacationBatchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
public abstract class AbstractVacationWriter<T> implements ItemWriter<T> {
	
	final VacationBatchService<T> vacationBatchService;
	
	public AbstractVacationWriter(VacationBatchService<T> vacationBatchService) {
		this.vacationBatchService = vacationBatchService;
	}
	
	@Override
	public void write(List<? extends T> items) throws Exception {
		
		List<T> vacationList = (List<T>) items;
		
		int successCnt = 0;
		
		try {
			List<T> saveVacationList = vacationBatchService.bulkVacationList(vacationList);
			successCnt = saveVacationList.size();
		} catch (Exception e) {
			
			log.error("Vacation batch update fail. trying one to one update. Exception : {}", e);
			
			for(T vacation : items) {
				T result = vacationBatchService.insertVacationList(vacation);
				
				if(result != null)
					successCnt++;
			}
		}
		
		log.info("Vacation update End. successCnt : {}", successCnt);
	}
	
}
