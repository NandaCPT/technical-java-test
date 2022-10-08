package com.technical.java.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

	private String employeeId;
	private String fullName;
	private String gender;
	private String religion;
	private String email;
	private String age;
	private LocalDate birthDate;
	private String jobCode;
	private String jobDesc;
	private String insertBy;
	private String updateBy;
	private boolean deleted;
	
}
