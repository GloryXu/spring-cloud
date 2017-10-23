package com.redsun.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.redsun")
public class HelloStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloStreamApplication.class, args);
    }
}
