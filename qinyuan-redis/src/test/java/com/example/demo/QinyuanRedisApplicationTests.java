package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.qinyuan.Utils.RandomUitl;
import com.qinyuan.Utils.StringUtil;
import com.qinyuan.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QinyuanRedisApplicationTests {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void contextLoads() {
		
		ValueOperations value = redisTemplate.opsForValue();
		long begin = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < 100000; i++) {
			User user = new User();
			user.setId(i+1);
			user.setName(StringUtil.generateChineseName()+StringUtil.randomChineseString(2));
			user.setBirthday("new Date()");
			user.setEmail(RandomUitl.randomString(10)+"@qq.com");
			user.setPhone("13"+RandomUitl.randomString(9));
			user.setSex("男");
			map.put("user-jdk", user);
		}
		value.set("user_jdk", map);
		long end = System.currentTimeMillis();
		System.out.println(end-begin+"这是序列化JDK所消耗的毫秒数");
	
	}

}
