package com.example.ensinotp03springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EnsinoTp03SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsinoTp03SpringBootApplication.class, args);
    }

}
