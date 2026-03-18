package com.akshay.ehcache.user_cache.repository;

import com.akshay.ehcache.user_cache.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
