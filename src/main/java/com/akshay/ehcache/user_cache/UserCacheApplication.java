package com.akshay.ehcache.user_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCacheApplication.class, args);
	}

}
