package com.example.configs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.dtos.BatchFileMessageDto;
import com.example.entities.HelloWorldEntity;
import com.example.repositories.HelloWorldRepository;

@Configuration
public class BatchConfig {

    private final HelloWorldRepository helloWorldRepository;
    
	@Autowired
	public BatchConfig(HelloWorldRepository helloWorldRepository) {
		this.helloWorldRepository = helloWorldRepository;
	}
	
	@Bean
    public Job saveMessagesJob(JobRepository jobRepository, Step importMessagesStep) {
		
		return new JobBuilder("saveMessagesJob", jobRepository)
				.incrementer(new RunIdIncrementer())
                .start(importMessagesStep)
                .build();
		
    }	

	@Bean
    public Step saveMessagesStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		
		return new StepBuilder("saveMessagesStep", jobRepository)
                .<BatchFileMessageDto, HelloWorldEntity>chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();

    }
    
	@Bean
    public ItemReader<BatchFileMessageDto> reader() {
		
        return new FlatFileItemReaderBuilder<BatchFileMessageDto>()
                .name("messageReader")
                .resource(new ClassPathResource("messages.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter(";")
                .names("id", "message")
                .targetType(BatchFileMessageDto.class)
                .build();
        
	}
	
	@Bean
    public ItemProcessor<BatchFileMessageDto, HelloWorldEntity> processor() {
		
        return item -> {
            return new HelloWorldEntity(item.getMessage());            
        };
        
    }
	
	@Bean
    public ItemWriter<HelloWorldEntity> writer() {
        return helloWorldRepository::saveAll;
    }

}
