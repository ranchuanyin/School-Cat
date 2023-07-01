package com.ranchuanyin.schoolcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling


public class SchoolCatApplication {
    public static void main(String[] args) {
       SpringApplication.run(SchoolCatApplication.class, args);
    }

}
