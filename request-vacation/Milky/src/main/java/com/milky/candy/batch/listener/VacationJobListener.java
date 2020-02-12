package com.milky.candy.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacationJobListener implements JobExecutionListener {
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Start jobName: {} ,param:[{}]", jobExecution.getJobInstance().getJobName(), 
				jobExecution.getJobParameters().toProperties());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("End jobName: {} ,param:[{}], elapseTime:{}", jobExecution.getJobInstance().getJobName(), 
				jobExecution.getJobParameters().toProperties());
	}

}
