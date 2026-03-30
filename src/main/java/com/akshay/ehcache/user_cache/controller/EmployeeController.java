package com.akshay.ehcache.user_cache.controller;

import com.akshay.ehcache.user_cache.dto.EmployeeDetailsDto;
import com.akshay.ehcache.user_cache.entities.EmployeeDetails;
import com.akshay.ehcache.user_cache.entities.UserDetails;
import com.akshay.ehcache.user_cache.service.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeDetailService employeeDetailService;

    @PostMapping(path = "/employee")
    public EmployeeDetails saveEmployee(@RequestBody EmployeeDetails employeeDetails) {
        return employeeDetailService.saveEmployee(employeeDetails);
    }

    @GetMapping(path = "/employee/{id}")
    public EmployeeDetailsDto fetchEmployee(@PathVariable Long id) {
        return employeeDetailService.fetchEmployee(id);
    }

    @GetMapping(path = "/employee-remove-orphan/{id}")
    public EmployeeDetailsDto fetchEmployeeAfterOrphanRemoval(@PathVariable Long id) {
        return employeeDetailService.fetchEmployeeAfterOrphanRemoval(id);
    }
}
