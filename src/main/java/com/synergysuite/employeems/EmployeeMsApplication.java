package com.synergysuite.employeems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EmployeeMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeMsApplication.class, args);
    }

}
