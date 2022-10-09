package com.technical.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technical.java.dto.JobDTO;
import com.technical.java.model.JobModel;
import com.technical.java.repository.JobRepository;
import com.technical.java.service.JobService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/job")
public class JobController {
	
	private final JobRepository repository;
	private final JobService service;
	
	public JobController(JobRepository repository,JobService service) {
		this.repository = repository;
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Object> addJob(@RequestBody JobDTO jobDto){
		Map<String, Object> result = new HashMap<>();
		if(repository.existsById(jobDto.getJobCode())) {
			result.put("message", "Job Code Already Exist!");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		service.addJob(jobDto);
		result.put("message", "Add Job Success!");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Object> getJob(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		
		List<JobDTO> listDto = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<JobModel> jobModel = repository.findAll(paging);
		
		jobModel.stream().forEach(e -> {
			JobDTO dto = new JobDTO();
			try {
				dto.setJobCode(e.getJobCode());
				dto.setJobDesc(e.getJobDesc());
				listDto.add(dto);
			} catch (Exception e2) {
				log.error(e2.getMessage(),e2);
			}
		});
		
		Map<String, Object> result = new HashMap<>();
		result.put("data", listDto);
		result.put("Page", jobModel.getNumber());
		result.put("TotalItem", jobModel.getTotalElements());
		
		if(listDto.size() == 0) {
			result.put("message", "No Data Found!");
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}else {
			result.put("message", "Get Job Success!");
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

}
