package com.technical.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job")
public class JobModel {
	
	@Id
	@Column(name = "job_code")
	private String jobCode;
	@Column(name = "job_desc")
	private String jobDesc;
	

}
