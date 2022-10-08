package com.technical.java.service;

import com.technical.java.dto.EmployeeDTO;

public interface EmployeeService {
	
	public String addEmployee(EmployeeDTO employeeDto);
	public void editEmployee(EmployeeDTO employeeDto);

}
