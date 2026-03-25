package com.akshay.ehcache.user_cache.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "USER_DETAILS_DTO", schema = "ONBOARDING",
        uniqueConstraints = {@UniqueConstraint(columnNames = "phone"),
                @UniqueConstraint(columnNames = {"name", "email"})},
        indexes = {@Index(name = "index_phone", columnList = "phone"),
        @Index(name = "index_name_email", columnList = "full_name, email")})
public class UserDetailsDTO implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_user_seq")
    @SequenceGenerator(name = "unique_user_seq", sequenceName = "db_seq_name", initialValue = 100, allocationSize = 5)
    private Long id;

    @Column(name = "full_name", unique = true, nullable = false, length = 255)
    private String name;
    private String email;
    private String phone;

    public UserDetailsDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
