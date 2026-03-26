package com.akshay.ehcache.user_cache.repository;

import com.akshay.ehcache.user_cache.entities.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long>,
        JpaSpecificationExecutor<EmployeeDetails> {
}
