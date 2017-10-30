package com.redsun.controller;

import com.redsun.domain.User;
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

    @GetMapping(value = "/feign-consumer2")
    public String helloConsumer2() {
        StringBuffer sb = new StringBuffer();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("XUGR")).append("\n");
        sb.append(helloService.hello("XUGR", 25)).append("\n");
        sb.append(helloService.hello(new User("XUGR", 25))).append("\n");
        return sb.toString();
    }
}
