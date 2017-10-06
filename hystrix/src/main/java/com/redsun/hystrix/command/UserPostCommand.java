package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.redsun.hystrix.domain.User;
import org.springframework.web.client.RestTemplate;

public class UserPostCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private User user;

    /**
     * 类名作为默认的命令名称
     * 组名、命令名称和线程池名称
     *
     * 分别对应注解中的属性：commandKey，groupKey， threadPoolKey
     * @param restTemplate
     * @param user
     */
    protected UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupKey")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected User run() throws Exception {
        // 写操作
        User r = restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);

        // 刷新缓存，清理失效的User
        UserGetCommand.flushCache(r.getId());
        return r;
    }

}
