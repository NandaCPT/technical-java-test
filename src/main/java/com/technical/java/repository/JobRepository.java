package com.technical.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technical.java.model.JobModel;

@Repository
public interface JobRepository extends JpaRepository<JobModel, String> {

}
