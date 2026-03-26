package com.akshay.ehcache.user_cache.controller;

import com.akshay.ehcache.user_cache.entities.EmployeeAddress;
import com.akshay.ehcache.user_cache.service.EmployeeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeAddressController {

    @Autowired
    EmployeeAddressService employeeAddressService;

    @GetMapping(path = "/emp-address/{id}")
    public EmployeeAddress fetchEmployee(@PathVariable Long id) {
        return employeeAddressService.fetchEmployeeAddress(id);
    }
}
