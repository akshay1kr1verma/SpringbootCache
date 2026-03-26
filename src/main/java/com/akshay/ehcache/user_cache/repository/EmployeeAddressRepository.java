package com.akshay.ehcache.user_cache.repository;

import com.akshay.ehcache.user_cache.entities.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long>,
        JpaSpecificationExecutor<EmployeeAddress> {
}
