package com.akshay.ehcache.user_cache.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE_BANK_DETAIL", schema = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/*
When Spring Boot (via Jackson) tries to convert to JSON:
It looks for getters
Finds none ❌
So it serializes → {}
*/
public class EmployeeBankDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    @OneToOne(mappedBy = "bankDetail")
    private EmployeeDetails employeeDetails;

    @Override
    public String toString() {
        return "accountNumber='" + accountNumber+ '\'';
    }
}
