package com.milky.candy.batch.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.milky.candy.batch.launcher.VacationJobLauncher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitVacationScheduler {
	
	private final VacationJobLauncher vacationJobLauncher;
	
	@Scheduled(cron = "0 10 0 1 1 ?")
	public void scheduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException {
		
        if (log.isDebugEnabled()) {
            log.debug("schedule check. InitVacationScheduler.");
        }
        
        try {
        	log.info("schedule check. InitVacationScheduler.");
        	
        	vacationJobLauncher.run(new JobParametersBuilder()
					.addString("currentTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
					.toJobParameters());
		} catch (Exception e) {
			log.error("initVacationScheduler Exception : {}", e);
		}
	}

}
