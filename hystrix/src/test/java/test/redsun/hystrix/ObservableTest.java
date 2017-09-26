package test.redsun.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

public class ObservableTest {

    @Test
    public void testObservable () {
        // 创建事件源
        Observable<String> observable = Observable.create(subscriber -> {
            subscriber.onNext("hello RxJava!");
            subscriber.onNext("I am Glory!");
            subscriber.onCompleted();
        });

        /*Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello RxJava!");
                subscriber.onNext("I am Glory!");
                subscriber.onCompleted();
            }
        });*/

        // 创建订阅者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber : " + s);
            }
        };

        // 订阅
        observable.subscribe(subscriber);
    }
}
