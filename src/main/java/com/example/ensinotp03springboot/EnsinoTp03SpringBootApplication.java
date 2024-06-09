package com.example.ensinotp03springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ensinotp03springboot.repository")
public class EnsinoTp03SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsinoTp03SpringBootApplication.class, args);
    }

}
