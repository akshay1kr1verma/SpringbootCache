package com.akshay.ehcache.user_cache.service;

import com.akshay.ehcache.user_cache.dto.EmployeeDetailsDto;
import com.akshay.ehcache.user_cache.entities.EmployeeDetails;
import com.akshay.ehcache.user_cache.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailService {
    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeDetails saveEmployee(EmployeeDetails employeeDetails) {
        return employeeDetailsRepository.save(employeeDetails);
    }

    public EmployeeDetailsDto fetchEmployee(Long id) {
        Optional<EmployeeDetails> employeeDetailOptional = employeeDetailsRepository.findById(id);
        return employeeDetailOptional.get().toDto();
    }
}
