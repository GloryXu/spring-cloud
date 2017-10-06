package com.redsun.hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.redsun.hystrix.domain.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

public class Main {

    /**
     * 记录同步/异步调用，无法执行
     *
     * @param args
     */
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HystrixCommandGroupKey hystrixCommandGroupKey = () -> "XUGR-KEY";
        // 同步执行
        User user = new UserCommand(hystrixCommandGroupKey, restTemplate, 1L).execute();

        // 异步执行
        Future<User> futureUser = new UserCommand(hystrixCommandGroupKey, restTemplate, 1L).queue();

        // Hot observable 当observe()调用的时候立即执行
        Observable<User> ho = new UserCommand(hystrixCommandGroupKey, restTemplate, 1L).observe();
        // Cold observable 当toObservable()执行之后，命令不会被立即执行，只有当所有订阅者都订阅之后才会执行
        Observable<User> co = new UserCommand(hystrixCommandGroupKey, restTemplate, 1L).toObservable();
    }
}
