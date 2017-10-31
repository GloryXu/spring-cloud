package com.redsun.controller;

import com.redsun.domain.User;
import com.redsun.service.RefactorHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    RefactorHelloService refactorHelloService;

    @GetMapping(value = "/feign-consumer")
    public String helloConsumer() {
        return refactorHelloService.hello();
    }

    @GetMapping(value = "/feign-consumer3")
    public String helloConsumer2() {
        StringBuffer sb = new StringBuffer();
        sb.append(refactorHelloService.hello()).append(" \n");
        sb.append(refactorHelloService.hello("XUGR")).append(" \n");
        sb.append(refactorHelloService.hello("XUGR", 25)).append(" \n");
        sb.append(refactorHelloService.hello(new User("XUGR", 25))).append(" \n");
        String retInfo = sb.toString();
        log.info("retInfo={}", retInfo);
        return retInfo;
    }
}
