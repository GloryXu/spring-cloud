package com.redsun.controller;

import com.redsun.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/feign-consumer")
    public String helloConsumer() {
        return helloService.hello();
    }
}
