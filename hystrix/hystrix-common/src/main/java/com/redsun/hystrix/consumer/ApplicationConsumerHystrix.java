package com.redsun.hystrix.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 开启熔断器
 */
//@SpringCloudApplication 意味着一个Spring Cloud标准应用包含服务发现以及熔断器
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.redsun")
public class ApplicationConsumerHystrix {

    /**
     * @Bean 作用于方法上，创建一个对象（Bean）注入到IoC容器中
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsumerHystrix.class, args);
    }
}
