package com.akshay.ehcache.user_cache.service;

import com.akshay.ehcache.user_cache.dto.EmployeeDetailsDto;
import com.akshay.ehcache.user_cache.entities.EmployeeDetails;
import com.akshay.ehcache.user_cache.entities.EmployeeOrderDetails;
import com.akshay.ehcache.user_cache.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public EmployeeDetailsDto fetchEmployeeAfterOrphanRemoval(Long id) {
        Optional<EmployeeDetails> employeeDetailOptional = employeeDetailsRepository.findById(id);
        List<EmployeeOrderDetails> employeeOrderDetails = employeeDetailOptional.get().getEmployeeOrderDetails();
        employeeOrderDetails.remove(0);
        // at this point you will notice only update call goes to db and no find call happens, because persistence context
        //updates it value during save and that only is returned, this way db call is not happening again.
        EmployeeDetails employeeDetails = employeeDetailsRepository.save(employeeDetailOptional.get());
        return employeeDetails.toDto();
    }
}
