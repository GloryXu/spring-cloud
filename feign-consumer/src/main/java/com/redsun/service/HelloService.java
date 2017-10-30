package com.redsun.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 通过@FeignClient来指定服务名来绑定服务
 * 不区分大小写
 */
@FeignClient(name = "hello-service")
public interface HelloService {

    @GetMapping("/hello")
    String hello();
}
