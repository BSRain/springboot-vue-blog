package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@MapperScan("com.example.mapper") //扫描的mapper
public class StudyProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectBackendApplication.class, args);
    }
}
