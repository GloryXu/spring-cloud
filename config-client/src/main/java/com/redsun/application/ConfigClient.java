package com.redsun.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.redsun")
@RestController
public class ConfigClient {

    @RequestMapping("/")
    public String home() {
        return "Hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }
}
