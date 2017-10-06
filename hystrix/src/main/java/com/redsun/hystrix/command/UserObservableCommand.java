package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.redsun.hystrix.domain.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

public class UserObservableCommand extends HystrixObservableCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    /**
     * Construct a {@link HystrixObservableCommand} with defined {@link HystrixCommandGroupKey}.
     * <p>
     * The {@link HystrixCommandKey} will be derived from the implementing class name.
     *
     * @param group {@link HystrixCommandGroupKey} used to group together multiple {@link HystrixObservableCommand} objects.
     *              <p>
     *              The {@link HystrixCommandGroupKey} is used to represent a common relationship between commands. For example, a library or team name, the system all related commands interace with,
     *              common business purpose etc.
     */
    protected UserObservableCommand(HystrixCommandGroupKey group, RestTemplate restTemplate, Long id) {
        super(group);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    /**
     * Implement this method with code to be executed when {@link #observe()} or {@link #toObservable()} are invoked.
     *
     * @return R response type
     */
    @Override
    protected Observable<User> construct() {
        return Observable.create(observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    User user = restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
                    observer.onNext(user);
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

    /**
     * If {@link #observe()} or {@link #toObservable()} fails in any way then this method will be invoked to provide an opportunity to return a fallback response.
     * <p>
     * This should do work that does not require network transport to produce.
     * <p>
     * In other words, this should be a static or cached result that can immediately be returned upon failure.
     * <p>
     * If network traffic is wanted for fallback (such as going to MemCache) then the fallback implementation should invoke another {@link HystrixObservableCommand} instance that protects against
     * that network
     * access and possibly has another level of fallback that does not involve network access.
     * <p>
     * DEFAULT BEHAVIOR: It throws UnsupportedOperationException.
     *
     * @return R or UnsupportedOperationException if not implemented
     */
    @Override
    protected Observable<User> resumeWithFallback() {
        return super.resumeWithFallback();
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
