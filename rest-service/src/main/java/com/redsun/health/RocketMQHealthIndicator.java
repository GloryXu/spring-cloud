package com.redsun.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class RocketMQHealthIndicator implements HealthIndicator {

    /**
     * Return an indication of health.
     *
     * @return the health for
     */
    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            try {
                return Health.down().withDetail("Error Code", errorCode).withDetail("ip", InetAddress.getLocalHost()).build();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return Health.up().build();
    }

    private int check() {
        // 对监控对象的检测操作
        return 0;
    }
}
