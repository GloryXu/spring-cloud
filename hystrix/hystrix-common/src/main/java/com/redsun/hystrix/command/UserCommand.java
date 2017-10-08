package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
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
     * 类名作为默认的命令名称
     * 组名、命令名称和线程池名称
     *
     * 分别对应注解中的属性：commandKey，groupKey， threadPoolKey
     * @param restTemplate
     * @param id
     */
    protected UserCommand(RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
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

    /**
     * If {@link #execute()} or {@link #queue()} fails in any way then this method will be invoked to provide an opportunity to return a fallback response.
     * <p>
     * This should do work that does not require network transport to produce.
     * <p>
     * In other words, this should be a static or cached result that can immediately be returned upon failure.
     * <p>
     * If network traffic is wanted for fallback (such as going to MemCache) then the fallback implementation should invoke another {@link HystrixCommand} instance that protects against that network
     * access and possibly has another level of fallback that does not involve network access.
     * <p>
     * DEFAULT BEHAVIOR: It throws UnsupportedOperationException.
     *
     * @return R or throw UnsupportedOperationException if not implemented
     */
    @Override
    protected User getFallback() {
        return new User();
    }

    /**
     * 优点：
     *  1.减少重复的请求数，降低依赖服务的并发度
     *  2.在同一用户请求的上下文中，相同依赖服务的返回始终保持一致
     *  3.请求缓存在run()和construct()执行之前生效，所以可以有效减少不必要的线程开销
     *
     * Key to be used for request caching.
     * <p>
     * By default this returns null which means "do not cache".
     * <p>
     * To enable caching override this method and return a string key uniquely representing the state of a command instance.
     * <p>
     * If multiple command instances in the same request scope match keys then only the first will be executed and all others returned from cache.
     *
     * @return cacheKey
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
