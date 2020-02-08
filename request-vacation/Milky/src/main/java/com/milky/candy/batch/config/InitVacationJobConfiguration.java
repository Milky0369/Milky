package com.milky.candy.batch.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.milky.candy.batch.listener.InitVacationJobListener;
import com.milky.candy.batch.processor.InitVacationProcessor;
import com.milky.candy.batch.writer.InitVacationItemWriter;
import com.milky.candy.domain.Vacation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class InitVacationJobConfiguration {
	private static final String JOB_NAME = "initVacationJob";
    
    private final DataSource dataSource;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskExecutor initVacationBatchThreadPool;
    
    @Bean(name= JOB_NAME)
    public Job initVacationJob(Step exportInitVacationStep,
                               InitVacationJobListener initVacationJobListener) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(exportInitVacationStep)
                .listener(initVacationJobListener)
                .build();
    }
    
    @Bean(name="exportInitVacationStep")
    public Step exportInitVacationStep(
            @Value("${milky.chunk.size}") int chunkSize,
            @Value("${milky.throttle.limit}") int throttleLimit,
            InitVacationProcessor initVacationProcessor,
            InitVacationItemWriter initVacationItemWriter) throws Exception {

        return stepBuilderFactory.get("exportInitVacationStep")
                .<Vacation, Vacation>chunk(chunkSize)
                .reader(initVacationItemReader(null, null))
                .processor(initVacationProcessor)
                .writer(initVacationItemWriter)
                .throttleLimit(throttleLimit)
                .taskExecutor(initVacationBatchThreadPool)
                .build();
    }

    @Bean(name = "initVacationItemReader")
    @StepScope
    public JdbcPagingItemReader<Vacation> initVacationItemReader(
    		@Value("${milky.page.size}") Integer pageSize,
    		@Value("${milky.fetch.size}") Integer fetchSize
    		) throws Exception {
        Map<String, Object> parameterValues = new HashMap<>();

        return new JdbcPagingItemReaderBuilder<Vacation>()
                .pageSize(pageSize)
                .fetchSize(fetchSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Vacation.class))
                .queryProvider(initVacationItemCreateQueryProvider())
                .parameterValues(parameterValues)
                .name("initVacationItemReader")
                .build();
    }
    
    @Bean
    @StepScope
    public PagingQueryProvider initVacationItemCreateQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause(
        		"SELECT " +
                "EMP_NUM AS empNum, " +
        		"VACATION AS vacation, " +
                "REG_DATE AS regDate, " +
                "UPT_DATE AS uptDate ");
        queryProvider.setFromClause("FROM VACATION");

        Map<String, Order> sortKeys = new HashMap<>();
        
        sortKeys.put("uptDate", Order.ASCENDING);
        sortKeys.put("empNum", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }
    
}
