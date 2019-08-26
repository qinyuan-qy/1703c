package com.qinyuan.cms.kafka;

import javax.annotation.Resource;

import kafka.utils.Json;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.es.dao.ArticleEsDao;
import com.qinyuan.cms.service.ArticleService;

public class TestCMSKafka implements MessageListener<String, String>{
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ArticleEsDao articleEsDao;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		/*if(record.key().equals("addhis")){
			String value = record.value();
			articleService.increaseHit(Integer.parseInt(value));
		}*/
		String value = record.value();
		Article article = JSON.parseObject(value, Article.class);
		articleService.insert(article);
		
		articleEsDao.save(article);
		
	}

}
