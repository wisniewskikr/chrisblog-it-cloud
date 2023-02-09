package com.example.configs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.dtos.BatchFileMessageDto;
import com.example.entities.HelloWorldEntity;
import com.example.repositories.HelloWorldRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final HelloWorldRepository helloWorldRepository;
    
	@Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			HelloWorldRepository helloWorldRepository) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.helloWorldRepository = helloWorldRepository;
	}
	
	@Bean
    public Job saveMessagesJob(Step importMessagesStep) {
        return jobBuilderFactory.get("saveMessagesJob")
                .incrementer(new RunIdIncrementer())
                .start(importMessagesStep)
                .build();
    }
	
	@Bean
    public Step saveMessagesStep() {
        return stepBuilderFactory.get("saveMessagesStep")
                .<BatchFileMessageDto, HelloWorldEntity>chunk(100)
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

            return new HelloWorldEntity(
            		Long.valueOf(item.getId()),
                    item.getMessage()
                    );
            
        };
    }
	
	@Bean
    public ItemWriter<HelloWorldEntity> writer() {
        return helloWorldRepository::saveAll;
    }

}
