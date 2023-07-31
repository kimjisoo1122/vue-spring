package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class VueSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueSpringApplication.class, args);
    }

}
