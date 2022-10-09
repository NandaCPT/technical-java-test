package com.technical.java.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.technical.java.dto.JobDTO;
import com.technical.java.model.JobModel;
import com.technical.java.repository.JobRepository;
import com.technical.java.service.JobService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService {
	
	private final JobRepository repository;
	
	public JobServiceImpl(JobRepository repository) {
		this.repository = repository;
	}
	
	public void addJob(JobDTO jobDto) {
		JobModel jobModel = new JobModel();
		try {
			jobModel.setJobCode(jobDto.getJobCode());
			jobModel.setJobDesc(jobDto.getJobDesc());
			repository.save(jobModel);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}
