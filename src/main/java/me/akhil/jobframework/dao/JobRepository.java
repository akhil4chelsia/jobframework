package me.akhil.jobframework.dao;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobDAO, Long> {
    
}

