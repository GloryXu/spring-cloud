package com.redsun.hystrix.command.collapser;

import com.netflix.hystrix.HystrixCommand;
import com.redsun.hystrix.domain.User;
import com.redsun.hystrix.service.UserServiceI;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

public class UserBatchCommand extends HystrixCommand<List<User>> {

    UserServiceI userServiceImpl;
    List<Long> ids;

    protected UserBatchCommand(UserServiceI userServiceImpl, List<Long> ids) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userServiceImpl = userServiceImpl;
        this.ids = ids;
    }


    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected List<User> run() throws Exception {
        return userServiceImpl.findAll(ids);
    }
}
