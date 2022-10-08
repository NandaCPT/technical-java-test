package com.technical.java.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.technical.java.dto.EmployeeDTO;
import com.technical.java.model.EmployeeModel;
import com.technical.java.model.JobModel;
import com.technical.java.repository.EmployeeRepository;
import com.technical.java.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository repository;
	
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	public String addEmployee(EmployeeDTO employeeDto) {
		
		EmployeeModel employee = new EmployeeModel();
		String result = "";
		
		try {
			JobModel jobModel = new JobModel();
			jobModel.setJobCode(employeeDto.getJobCode());
			
			int getYear = Calendar.getInstance().get(Calendar.YEAR);
			long currentEmployee = repository.count()+1;
			String year = String.valueOf(getYear);
			String employeeId = String.format("%04d", currentEmployee);
			employeeId = year+employeeId;
			
			employee.setEmployeeId(employeeId);
			employee.setFullName(employeeDto.getFullName());
			employee.setAge(employeeDto.getAge());
			employee.setBirthDate(employeeDto.getBirthDate());
			employee.setEmail(employeeDto.getEmail());
			employee.setGender(employeeDto.getGender());
			employee.setReligion(employeeDto.getReligion());
			employee.setJobModel(jobModel);
			employee.setInsertBy(employeeDto.getInsertBy());
			repository.save(employee);
			result = employeeId;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	public void editEmployee(EmployeeDTO employeeDto) {
		JobModel jobModel = new JobModel();
		jobModel.setJobCode(employeeDto.getJobCode());
		
		EmployeeModel getEmployee = repository.getById(employeeDto.getEmployeeId());
		
		getEmployee.setFullName(employeeDto.getFullName());
		getEmployee.setAge(employeeDto.getAge());
		getEmployee.setBirthDate(employeeDto.getBirthDate());
		getEmployee.setReligion(employeeDto.getReligion());
		getEmployee.setJobModel(jobModel);
		getEmployee.setUpdateBy(employeeDto.getUpdateBy());
		getEmployee.setDeleted(employeeDto.isDeleted());
		repository.save(getEmployee);
	}
}
