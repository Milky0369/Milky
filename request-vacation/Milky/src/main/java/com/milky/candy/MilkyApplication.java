package com.milky.candy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EntityScan
@ComponentScan
@EnableScheduling
@EnableBatchProcessing
@EnableJpaRepositories
@EnableAuthorizationServer
@SpringBootApplication
public class MilkyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkyApplication.class, args);
	}
	
    @Bean(name = "vacationJobLauncher")
    public JobLauncher vacationJobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setThreadNamePrefix("vacation-task");
        launcher.setTaskExecutor(simpleAsyncTaskExecutor);
        return launcher;
    }
    
    @Bean(name = "vacationBatchThreadPool")
    public TaskExecutor vacationBatchThreadPool(
            @Value("${milky.db.core.task.pool.size}") int coreTaskPoolSize,
            @Value("${milky.db.max.task.pool.size}") int maxTaskPoolSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreTaskPoolSize);
        executor.setMaxPoolSize(maxTaskPoolSize);
        return executor;
    }

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().disable();
//		http.authorizeRequests()
//			.anyRequest().permitAll()
//			.antMatchers("/authorization-code").access("#oauth2.hasScope('read')");
//	}
//	
//	@Bean
//	public TokenStore JdbcTokenStore(DataSource dataSource) {
//		return new JdbcTokenStore(dataSource);
//	}

}