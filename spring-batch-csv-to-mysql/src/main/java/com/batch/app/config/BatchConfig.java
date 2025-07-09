package com.batch.app.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.app.entity.EmployeeEntity;
import com.batch.app.model.Employee;
import com.batch.app.processor.EmployeeProcessor;

@Configuration
public class BatchConfig {

    @Bean
    public Job importEmployeesJob(JobRepository jobRepository,
                                  Step step1) {
        return new JobBuilder("importEmployees", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<Employee> reader,
                      EmployeeProcessor processor,
                      RepositoryItemWriter<EmployeeEntity> writer) 
    
    {
        return new StepBuilder("step1", jobRepository)
                .<Employee, EmployeeEntity>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}