package com.redsun.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.redsun.hystrix.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
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
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
    }

    /**
     * 异步实现
     *
     * @param id
     * @return
     */
    @HystrixCommand
    public Future<User> getUserByIdAsync(final String id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
            }
        };
    }

    /**
     *
     * @param id
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    public Observable<User> getUserById(final String id) {
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
}
