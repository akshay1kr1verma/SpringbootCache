package com.akshay.ehcache.user_cache.service;

import com.akshay.ehcache.user_cache.entities.UserDetails;
import com.akshay.ehcache.user_cache.repository.UserDetailsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManagerFactory emf;

    @CachePut(cacheNames = "cacheStore", key = "#result.id")
    public UserDetails saveUser(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    @CachePut(cacheNames = "cacheStore", key = "#id")
    public UserDetails updateUser(Long id, UserDetails userDetails) {
        UserDetails existingUser = userDetailsRepository.findById(id).get();
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        return userDetailsRepository.save(existingUser);
    }

    @Cacheable(cacheNames = "cacheStore", key = "#primaryKey")
    public UserDetails getUser(Long primaryKey) {
        return userDetailsRepository.findById(primaryKey).get();
    }

    @CacheEvict(cacheNames = "cacheStore", key = "#primaryKey")
    public void evictUser(Long primaryKey) {
        userDetailsRepository.deleteById(primaryKey);
    }

    public UserDetails saveUserUsingEntityManager(UserDetails userDetails) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userDetails);
        em.find(UserDetails.class, 1L);
        UserDetails output = em.find(UserDetails.class, 1L);
        System.out.println("session 1: I am able to find the data, name is " + output.getName());
        em.getTransaction().commit();
        em.close();

        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        em2.find(UserDetails.class, 1L);
        UserDetails output2 = em2.find(UserDetails.class, 1L);
        System.out.println("session 2 : I am able to find the data, name is " + output2.getName());
        em2.getTransaction().commit();
        em2.close();
        return output2;
    }

    public UserDetails saveUserUsingJdbcTemplate(String name, String email) {
        String insertQuery = "insert into ONBOARDING.User_Details(name, email) values(?, ?)";
        jdbcTemplate.update(insertQuery, name, email);
        String createQuery = "select * from ONBOARDING.User_Details where name = (?)";
        UserDetails ud = new UserDetails();
        List<UserDetails> userDetailsList = jdbcTemplate.query(createQuery,  ps -> {
            ps.setString(1, name);
        }, (rs, rowNum) -> {
            ud.setId(rs.getLong("id"));
            ud.setEmail(rs.getString("email"));
            ud.setName(rs.getString("name"));
            return ud;
        });
        return userDetailsList.get(0);
    }
}
