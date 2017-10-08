package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.redsun.hystrix.domain.User;
import org.springframework.web.client.RestTemplate;

public class UserGetCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    private RestTemplate restTemplate;
    private Long id;

    /**
     * 类名作为默认的命令名称
     * 组名、命令名称和线程池名称
     *
     * 分别对应注解中的属性：commandKey，groupKey， threadPoolKey
     * @param restTemplate
     * @param id
     */
    protected UserGetCommand(RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupKey"))
                .andCommandKey(GETTER_KEY));
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
        // 根据id置入缓存
        return String.valueOf(id);
    }

    public static void flushCache(Long id) {
        // 刷新缓存，根据id清理
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }

}
