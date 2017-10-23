package com.redsun.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Turbine是聚合服务器发送事件流数据的一个工具，用来监控集群下hystrix的metrics情况。
 */

@EnableDiscoveryClient
@EnableTurbine
@SpringBootApplication(scanBasePackages = "com.redsun")
public class ApplicationTurbine {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTurbine.class, args);
    }
}
