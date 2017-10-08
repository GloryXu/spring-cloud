package com.redsun.hystrix.service;

import com.redsun.hystrix.domain.User;

import java.util.List;

public interface UserServiceI {

    User find(Long id);

    List<User> findAll(List<Long> ids);
}
