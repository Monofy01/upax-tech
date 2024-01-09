package com.brian.company.repository;

import com.brian.company.models.Gender;
import com.brian.company.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
