package com.example.jpaplayground;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"study.spring.setups", "com.example.jpaplayground"})
@EnableJpaRepositories(basePackages = {"study.spring.setups", "com.example.jpaplayground"})
public class JpaPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPlaygroundApplication.class, args);
    }

}
