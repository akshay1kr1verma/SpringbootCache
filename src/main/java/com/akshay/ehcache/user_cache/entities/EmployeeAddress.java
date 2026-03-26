package com.akshay.ehcache.user_cache.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "EMPLOYEE_ADDRESS", schema = "EMPLOYEE")
@Getter
@Setter
/*
When Spring Boot (via Jackson) tries to convert to JSON:
It looks for getters
Finds none ❌
So it serializes → {}
*/

//this generator will allow child to have parent but not in repeated format.
public class EmployeeAddress {


    //@EmbeddedId
    //private EmployeeAddressCK employeeAddress;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String pinCode;
    private String city;
    private String state;
    private String country;
    @OneToOne(mappedBy = "employeeAddress"/*, fetch = FetchType.LAZY*/)
    @JsonBackReference
    //when back reference used then employee details will not be shown
    private EmployeeDetails employeeDetails;

    public EmployeeAddress(String street, String pinCode, String city, String state, String country) {
        this.street = street;
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "street='" + street + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'';
    }
}
