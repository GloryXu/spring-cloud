package com.redsun.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.redsun.hystrix.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 同步实现
     *
     * @param id
     * @return
     */
    @HystrixCommand
    public User getUserById(Long id) {
//  public User getUserById(@CacheKey("id") Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
    }

    @HystrixCommand
    public User getUserById(@CacheKey("id") User user) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, user.getId());
    }

    /**
     * commandKey 必须指定，用来指定请求缓存的请求命令，因为只有通过该属性的配置，Hystrix才能找到正确的请求命令缓存位置
     */
    @CacheRemove(commandKey = "getUserById")
    @HystrixCommand
    public void update(@CacheKey("id") User user) {
        restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);
    }

    /**
     * 异步实现
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackException")
    public Future<User> getUserByIdAsync(final String id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
            }
        };
    }

    /**
     * fallbackMethod的值必须与方法名相同，所有服务降级的方法修饰符没要求
     * ignoreExceptions
     *
     * CacheResult必须与HystrixCommand结合使用
     * @param id
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "defaultUser")
    @CacheResult(cacheKeyMethod = "genCacheKey")
    public Observable<User> getUserById( final String id) {
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
     * 可实现二级降级
     *
     * @return
     */
//    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser() {
        return new User();
    }

    public void fallbackException(String id, Throwable throwable) {
        assert "getUserById command failed".equals(throwable.getMessage());
    }

    private String genCacheKey(String id) {
        return id;
    }
}
