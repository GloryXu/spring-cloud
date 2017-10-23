package com.redsun.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.redsun")
public class SpringIntegration {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegration.class, args);
    }
}
