package com.qinyuan.cms.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import com.qinyuan.cms.BaseTestCase;
@SuppressWarnings("rawtypes")
public class KafkaTest extends BaseTestCase{
	
	@Resource
	private KafkaTemplate kafkaTemplate;
	
	@Test
	public void testKafka(){
		
		kafkaTemplate.send("test", "haha", "我是你爸爸");
	}
}
