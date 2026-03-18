package com.akshay.ehcache.user_cache.controller;

import com.akshay.ehcache.user_cache.entities.UserDetails;
import com.akshay.ehcache.user_cache.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    UserDetailService userDetailService;

    @GetMapping(path = "/test-jpa")
    public UserDetails getUser() {
        UserDetails userDetails = new UserDetails("xyx", "xyx@conceptcoding.com");
        UserDetails result = userDetailService.saveUser(userDetails);
        return userDetailService.getUser(result.getId());
    }

    @GetMapping(path = "/read-jpa")
    public UserDetails getUser2() {
        return userDetailService.getUser(1L);
    }

    @GetMapping(path = "/entitymanager/test-jpa")
    public UserDetails getUserThroughEntityManager() {
        UserDetails userDetails = new UserDetails("xyx", "xyx@conceptcoding.com");
        UserDetails result = userDetailService.saveUserUsingEntityManager(userDetails);
        return userDetailService.getUser(result.getId());
    }


    //EhCache testing
    @PostMapping(path = "/user")
    public UserDetails insertUser(@RequestBody UserDetails userDetails) {
        return userDetailService.saveUser(userDetails);
    }

    @PutMapping(path = "/user/{id}")
    public UserDetails updateUser(@PathVariable Long id, @RequestBody UserDetails userDetails) {
        return userDetailService.updateUser(id, userDetails);
    }

    @GetMapping(path = "/user/{id}")
    public UserDetails getUser2(@PathVariable Long id) {
        return userDetailService.getUser(id);
    }

   @DeleteMapping(path = "/user/{id}")
    public void evictUser(@PathVariable Long id) {
        userDetailService.evictUser(id);
    }
}
