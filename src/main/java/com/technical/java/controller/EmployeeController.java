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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technical.java.dto.EmployeeDTO;
import com.technical.java.model.EmployeeModel;
import com.technical.java.repository.EmployeeRepository;
import com.technical.java.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
	
	private final EmployeeService service;
	private final EmployeeRepository repository;
	
	public EmployeeController(EmployeeService service,EmployeeRepository repository) {
		this.service = service;
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<Object> addEmployee (@RequestBody EmployeeDTO employeeDto){
		Map<String, Object> result = new HashMap<>();
		EmployeeDTO employee = new EmployeeDTO();
		
		String employeeId = service.addEmployee(employeeDto);
		
		if(!employeeId.equals("")) {
			employee.setEmployeeId(employeeId);
			result.put("message", "Add Employee Success!");
			result.put("data", employee);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			result.put("message", "Add Employee Failed!");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllEmployee(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		
		List<EmployeeDTO> listEmployee = new ArrayList<>();
		Pageable paging = PageRequest.of(page, size);
		Page<EmployeeModel> employeeModel = repository.findAllByDeletedFalse(paging);
		
		employeeModel.stream().forEach(e -> {
			EmployeeDTO dto = new EmployeeDTO();
			try {
				dto.setAge(e.getAge());
				dto.setBirthDate(e.getBirthDate());
				dto.setEmail(e.getEmail());
				dto.setEmployeeId(e.getEmployeeId());
				dto.setFullName(e.getFullName());
				dto.setGender(e.getGender());
				dto.setJobCode(e.getJobModel().getJobCode());
				dto.setJobDesc(e.getJobModel().getJobDesc());
				dto.setReligion(e.getReligion());
				dto.setInsertBy(e.getInsertBy());
				listEmployee.add(dto);
			} catch (Exception e2) {
				log.error(e2.getMessage(),e2);
			}
		});
		
		Map<String, Object> result = new HashMap<>();
		result.put("data", listEmployee);
		result.put("Page", employeeModel.getNumber());
		result.put("TotalItem", employeeModel.getTotalElements());
		
		if(listEmployee.size() != 0) {
			result.put("message", "Get Data Success!");
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			result.put("message", "No Data Found!");
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		
	}

	@PutMapping
	public ResponseEntity<Object> editEmployee(@RequestBody EmployeeDTO employeeDto){
		Map<String, Object> result = new HashMap<>();
		try {
			String employeeId = employeeDto.getEmployeeId();
			if(!repository.existsById(employeeId)) {
				result.put("message", "Employee Not Found!");
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}
			
			service.editEmployee(employeeDto);
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		result.put("message", "Edit Employee Success!");
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
}
