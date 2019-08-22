package com.qinyuan.cms.es.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.qinyuan.cms.domain.Article;

public interface ArticleEsDao extends ElasticsearchRepository<Article, Integer>{
	
	public List<Article> findByTitleLike(String title);
	
	public List<Article> findByTitleLike(String title,Pageable pageable);
}
