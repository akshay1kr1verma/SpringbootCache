package com.akshay.ehcache.user_cache.entities;

import com.akshay.ehcache.user_cache.dto.EmployeeDetailsDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
When Spring Boot (via Jackson) tries to convert to JSON:
It looks for getters
Finds none ❌
So it serializes → {}
*/
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "EMPLOYEE_DETAILS", schema = "EMPLOYEE",
        uniqueConstraints = {@UniqueConstraint(name = "unique_employee_details_dto_phone", columnNames = "phone"),
                @UniqueConstraint(name = "unique_employee_details_dto_full_name_email",
                        columnNames = {"full_name", "email"})},
        indexes = {@Index(name = "index_phone", columnList = "phone"),
        @Index(name = "index_email_full_name", columnList = "full_name, email")})
public class EmployeeDetails implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_employee_seq")
    @SequenceGenerator(name = "unique_employee_seq", sequenceName = "db_seq_name", initialValue = 100, allocationSize = 5)
    private Long id;

    @Column(name = "full_name", unique = true, nullable = false, length = 255)
    private String name;

    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL/*, fetch = FetchType.LAZY*/)
    //with lazy enabled query will call at time of DTO setting
    @JoinColumn(name = "bank_detail_id", referencedColumnName = "id")
    private EmployeeBankDetail bankDetail;

    @OneToOne(cascade = CascadeType.ALL/*, fetch = FetchType.LAZY*/)
    /*@JoinColumns(value = {@JoinColumn(name = "employee_address_street", referencedColumnName = "street"),
            @JoinColumn(name = "employee_address_pinCode", referencedColumnName = "pinCode")})*/
    @JoinColumn(name = "employee_address_id", referencedColumnName = "id")
    @JsonManagedReference
    // when managed reference used complete child object will be shown
    private EmployeeAddress employeeAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "emp_id_fk", referencedColumnName = "id")
    private List<EmployeeOrderDetails> employeeOrderDetails = new ArrayList<>();

    public EmployeeDetailsDto toDto() {
        return new EmployeeDetailsDto(this);
    }
}
