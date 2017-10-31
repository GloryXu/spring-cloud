package com.redsun.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.redsun")
@SpringBootApplication(scanBasePackages = "com.redsun")
@EnableDiscoveryClient
public class ConsumerFeign {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeign.class, args);
    }
}
