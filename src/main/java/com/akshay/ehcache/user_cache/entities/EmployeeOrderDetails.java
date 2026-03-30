package com.akshay.ehcache.user_cache.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDER_DETAILS", schema = "EMPLOYEE")
@NoArgsConstructor
@Getter
@Setter
public class EmployeeOrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
}
