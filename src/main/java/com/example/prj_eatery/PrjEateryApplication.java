package com.example.prj_eatery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"authorization", "entity"})
@EnableJpaRepositories(basePackages = {"repository"} )
@ComponentScan(basePackages = { "com.example.prj_eatery","authorization", "controller", "configuration", "interceptor", "service" } )
public class PrjEateryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrjEateryApplication.class, args);
    }

}
