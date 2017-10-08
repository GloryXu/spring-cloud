package com.redsun.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableDiscoveryClient
@EnableTurbine
@SpringBootApplication(scanBasePackages = "com.redsun")
public class ApplicationTurbine {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTurbine.class, args);
    }
}
