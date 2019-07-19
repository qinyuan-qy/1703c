/**
 * 
 */
package com.qinyuan.cms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qinyuan.cms.core.Page;
import com.qinyuan.cms.dao.ArticleMapper;
import com.qinyuan.cms.domain.Article;
import com.qinyuan.cms.domain.Category;
import com.qinyuan.cms.domain.Channel;
import com.qinyuan.cms.service.ArticleService;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年4月21日 下午9:06:07
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	ArticleMapper articleMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders) {
		List<Article> articles = articleMapper.selects(conditions, orders, page);
		if(page != null && page.getPageCount() == 0){
			int totalCount = articleMapper.count(conditions);
			page.setTotalCount(totalCount);
		}
		return articles;
	}

	@Override
	public void increaseHit(Integer id) {
		articleMapper.increaseHit(id);
		
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Article> queryAll(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.queryAll(article);
	}

	@Override
	public void updateByKey(Article article) {
		
		articleMapper.updateByKey(article);
	}

	@Override
	public void save(Article article) {
		
		articleMapper.save(article);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByPrimaryKey(id);
	}
	
	
}
