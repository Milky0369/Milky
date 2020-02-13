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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.milky.candy.batch.listener.VacationJobListener;
import com.milky.candy.batch.processor.UseVacationProcessor;
import com.milky.candy.batch.writer.UseVacationItemWriter;
import com.milky.candy.domain.VacationHistory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class UseVacationJobConfiguration {
	private static final String JOB_NAME = "useVacationJob";
    
    private final DataSource dataSource;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskExecutor vacationBatchThreadPool;
    
    @Bean(name= JOB_NAME)
    public Job useVacationJob(Step exportUseVacationStep,
                              VacationJobListener vacationJobListener) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(exportUseVacationStep)
                .listener(vacationJobListener)
                .build();
    }
    
    @Bean(name="exportUseVacationStep")
    public Step exportUseVacationStep(
            @Value("${milky.chunk.size}") int chunkSize,
            @Value("${milky.throttle.limit}") int throttleLimit,
            UseVacationProcessor useVacationProcessor,
            UseVacationItemWriter useVacationItemWriter) throws Exception {

        return stepBuilderFactory.get("exportUseVacationStep")
                .<VacationHistory, VacationHistory>chunk(chunkSize)
                .reader(useVacationItemReader(null, null))
                .processor(useVacationProcessor)
                .writer(useVacationItemWriter)
                .throttleLimit(throttleLimit)
                .taskExecutor(vacationBatchThreadPool)
                .build();
    }
    
    @Bean(name = "useVacationItemReader")
    @StepScope
    public JdbcPagingItemReader<VacationHistory> useVacationItemReader(
    		@Value("${milky.page.size}") Integer pageSize,
    		@Value("${milky.fetch.size}") Integer fetchSize
    		) throws Exception {
        Map<String, Object> parameterValues = new HashMap<>();

        return new JdbcPagingItemReaderBuilder<VacationHistory>()
                .pageSize(pageSize)
                .fetchSize(fetchSize)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(VacationHistory.class))
                .queryProvider(useVacationItemCreateQueryProvider())
                .parameterValues(parameterValues)
                .name("useVacationItemReader")
                .build();
    }
    
    @Bean
    @StepScope
    public PagingQueryProvider useVacationItemCreateQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause(
        		"SELECT " +
        		"SEQ AS seq," +
                "EMP_NUM AS empNum, " +
        		"TIME_TYPE AS timeType, " +
        		"START_DATE AS startDate, " +
        		"END_DATE AS endDate, " +
        		"USE_FLAG AS useFlag, " +
        		"CANCEL_FLAG AS cancelFlag, " +
                "REG_DATE AS regDate, " +
                "UPT_DATE AS uptDate ");
        queryProvider.setFromClause("FROM VACATION_HISTORY");
        queryProvider.setWhereClause("WHERE CANCEL_FLAG = 'N'"
        							  + "AND USE_FLAG = 'N'"
        							  + "AND START_DATE < NOW()");
        Map<String, Order> sortKeys = new HashMap<>();
        
        sortKeys.put("seq", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }
}
