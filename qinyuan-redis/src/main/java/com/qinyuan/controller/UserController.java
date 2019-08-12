package com.qinyuan.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.qinyuan.Utils.RandomUitl;
import com.qinyuan.Utils.StringUtil;
import com.qinyuan.bean.User;

@Controller
public class UserController {
	
	@Resource
	private RedisTemplate redisTemplate;
	/**
	 * 这个是序列化jdk所需的毫秒值
	 */
	@RequestMapping("testjdk")
	public void testjdk() {
		JdkSerializationRedisSerializer jd = new JdkSerializationRedisSerializer();
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
	/**
	 * 这个是序列化json所需的毫秒值
	 */
	@RequestMapping("testjson")
	public void testjson() {
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
			map.put("user-json", user);
		}
		Object json = JSON.toJSON(map);
		value.set("user_json", json);
		long end = System.currentTimeMillis();
		System.out.println(end-begin+"这是序列化json所消耗的毫秒数");
		
	}
	/**
	 * 这个是序列化hash所需的毫秒值
	 */
	@RequestMapping("testhash")
	public void testhash() {
		StringRedisSerializer j = new StringRedisSerializer();
		HashOperations hash = redisTemplate.opsForHash();
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
			map.put("user-hash", user);
		}
		hash.putAll("user_hash", map);
		long end = System.currentTimeMillis();
		System.out.println(end-begin+"这是序列化hash所消耗的毫秒数");
		
	}
}
