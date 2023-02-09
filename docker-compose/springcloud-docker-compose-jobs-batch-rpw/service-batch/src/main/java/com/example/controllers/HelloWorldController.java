package com.example.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.BatchDto;
import com.example.services.HelloWorldService;

@RestController
public class HelloWorldController {
	
	private final HelloWorldService helloWorldService;
	private final Job job;
    private final JobLauncher jobLauncher;

	@Autowired
	public HelloWorldController(HelloWorldService helloWorldService, Job job, JobLauncher jobLauncher) {
		this.helloWorldService = helloWorldService;
		this.job = job;
		this.jobLauncher = jobLauncher;
	}

	@RequestMapping(value="/")
	public BatchDto helloWorld() {
		
		String text = helloWorldService.readAllMessages();		
		return new BatchDto(text);		
		
	}
	
	

	@RequestMapping(value="/job")
	public BatchDto job() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		jobLauncher.run(job, new JobParameters());		
		return new BatchDto("Job was run");
		
	}
	
}