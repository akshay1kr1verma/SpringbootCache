package com.akshay.ehcache.user_cache.dto;

import com.akshay.ehcache.user_cache.entities.EmployeeDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDetailsDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String bankDetail;
    private String employeeAddress;

    public EmployeeDetailsDto(Long id, String name, String email, String phone,
                              String bankDetail, String employeeAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bankDetail = bankDetail;
        this.employeeAddress = employeeAddress;
    }

    public EmployeeDetailsDto (EmployeeDetails employeeDetails) {
        this.id = employeeDetails.getId();
        this.name = employeeDetails.getName();
        this.email = employeeDetails.getEmail();
        this.phone = employeeDetails.getPhone();
        System.out.println("now we are going to query employee address");
        this.employeeAddress = employeeDetails.getEmployeeAddress() != null
                ? employeeDetails.getEmployeeAddress().toString() : null;
        System.out.println("now we are going to query employee bank details");
        this.bankDetail = employeeDetails.getBankDetail() != null
                ? employeeDetails.getBankDetail().toString() : null;
    }
}
