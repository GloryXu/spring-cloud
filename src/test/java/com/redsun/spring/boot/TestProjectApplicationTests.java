package com.redsun.spring.boot;

import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestProjectApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void testMapRemove(){
		Map<String,String> map = Maps.newHashMap("key","value");

		System.out.println(map.remove("key"));
	}

}
