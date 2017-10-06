package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.redsun.hystrix.domain.User;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    /**
     * Construct a {@link HystrixCommand} with defined {@link HystrixCommandGroupKey}.
     * <p>
     * The {@link HystrixCommandKey} will be derived from the implementing class name.
     *
     * @param group {@link HystrixCommandGroupKey} used to group together multiple {@link HystrixCommand} objects.
     *              <p>
     *              The {@link HystrixCommandGroupKey} is used to represent a common relationship between commands. For example, a library or team name, the system all related commands interact with,
     *              common business purpose etc.
     */
    protected UserCommand(HystrixCommandGroupKey group, RestTemplate restTemplate, Long id) {
        super(group);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    /**
     * Implement this method with code to be executed when {@link #execute()} or {@link #queue()} are invoked.
     *
     * @return R response type
     * @throws Exception if command execution fails
     */
    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
    }
}
