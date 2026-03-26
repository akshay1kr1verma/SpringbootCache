package com.akshay.ehcache.user_cache.service;

import com.akshay.ehcache.user_cache.entities.EmployeeAddress;
import com.akshay.ehcache.user_cache.repository.EmployeeAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeAddressService {
    @Autowired
    EmployeeAddressRepository employeeAddressRepository;

    public EmployeeAddress fetchEmployeeAddress(Long id) {
        return employeeAddressRepository.findById(id).get();
    }
}
