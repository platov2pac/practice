package com.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication

public class practice {
    public static void main(String[] args) {
        SpringApplication.run(practice.class, args);
    }
}