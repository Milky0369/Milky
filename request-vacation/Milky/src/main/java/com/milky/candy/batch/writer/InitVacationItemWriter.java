package com.milky.candy.batch.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.milky.candy.domain.Vacation;
import com.milky.candy.repository.VacationDbRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StepScope
@Component
@RequiredArgsConstructor
public class InitVacationItemWriter implements ItemWriter<Vacation> {
	
	final VacationDbRepository vacationDbRepository;

	@Override
	public void write(List<? extends Vacation> items) throws Exception {
		List<Vacation> vacationList = (List<Vacation>) items;
		
		int successCnt = 0;
		
		try {
			List<Vacation> saveVacationList = vacationDbRepository.saveAll(vacationList);
			successCnt = saveVacationList.size();
		} catch (Exception e) {
			
			log.error("Vacation batch update fail. trying one to one update. Exception : {}", e);
			
			for(Vacation vacation : items) {
				Vacation result = vacationDbRepository.save(vacation);
				
				if(result != null)
					successCnt++;
			}
		}
		
		log.info("Vacation update End. successCnt : {}", successCnt);
	}
	

}
