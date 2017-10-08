package test.redsun.eureka;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    @org.junit.Test
    public void test() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
    }
}
