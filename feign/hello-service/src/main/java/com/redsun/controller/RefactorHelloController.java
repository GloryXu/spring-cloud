package com.redsun.controller;

import com.redsun.domain.User;
import com.redsun.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {

    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello(User user) {
        return "Hello " + user.getName() + "," + user.getAge();
    }
}
